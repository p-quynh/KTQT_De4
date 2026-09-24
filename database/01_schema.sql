/* SQL Server schema for exam 04. Safe to run more than once. Save/run as UTF-8. */
IF DB_ID(N'KTQT_De4') IS NULL
    EXEC(N'CREATE DATABASE KTQT_De4');
GO

USE KTQT_De4;
GO
SET ANSI_NULLS ON;
SET QUOTED_IDENTIFIER ON;
GO

IF OBJECT_ID(N'dbo.Users', N'U') IS NULL
BEGIN
    CREATE TABLE dbo.Users (
        Username nvarchar(50) NOT NULL CONSTRAINT PK_Users PRIMARY KEY,
        [Password] nvarchar(255) NOT NULL,
        Phone nvarchar(15) NULL,
        Fullname nvarchar(50) NULL,
        Email nvarchar(150) NULL,
        [Admin] bit NOT NULL CONSTRAINT DF_Users_Admin DEFAULT 0,
        Active bit NOT NULL CONSTRAINT DF_Users_Active DEFAULT 0,
        Images nvarchar(500) NULL
    );
END;
GO

/* The diagram uses nvarchar(50), but PBKDF2 hashes need more space. */
IF EXISTS (
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
    WHERE TABLE_SCHEMA=N'dbo' AND TABLE_NAME=N'Users' AND COLUMN_NAME=N'Password'
      AND CHARACTER_MAXIMUM_LENGTH < 255
)
    ALTER TABLE dbo.Users ALTER COLUMN [Password] nvarchar(255) NOT NULL;
GO

IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE object_id=OBJECT_ID(N'dbo.Users') AND name=N'UX_Users_Email')
    CREATE UNIQUE INDEX UX_Users_Email ON dbo.Users(Email) WHERE Email IS NOT NULL;
GO

IF OBJECT_ID(N'dbo.Category', N'U') IS NULL
BEGIN
    CREATE TABLE dbo.Category (
        CategoryId int IDENTITY(1,1) NOT NULL CONSTRAINT PK_Category PRIMARY KEY,
        Categoryname nvarchar(100) NULL,
        Categorycode nvarchar(100) NULL,
        Images nvarchar(500) NULL,
        Status bit NOT NULL CONSTRAINT DF_Category_Status DEFAULT 1
    );
END;
GO

IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE object_id=OBJECT_ID(N'dbo.Category') AND name=N'UX_Category_Code')
    CREATE UNIQUE INDEX UX_Category_Code ON dbo.Category(Categorycode) WHERE Categorycode IS NOT NULL;
GO

IF OBJECT_ID(N'dbo.Videos', N'U') IS NULL
BEGIN
    CREATE TABLE dbo.Videos (
        VideoId nvarchar(50) NOT NULL CONSTRAINT PK_Videos PRIMARY KEY,
        Title nvarchar(200) NULL,
        Poster nvarchar(500) NULL,
        Views int NOT NULL CONSTRAINT DF_Videos_Views DEFAULT 0,
        [Description] nvarchar(500) NULL,
        Active bit NOT NULL CONSTRAINT DF_Videos_Active DEFAULT 1,
        CategoryId int NULL,
        CONSTRAINT FK_Videos_Category FOREIGN KEY(CategoryId) REFERENCES dbo.Category(CategoryId),
        CONSTRAINT CK_Videos_Views CHECK(Views >= 0)
    );
END;
GO

IF OBJECT_ID(N'dbo.Shares', N'U') IS NULL
BEGIN
    CREATE TABLE dbo.Shares (
        ShareId int IDENTITY(1,1) NOT NULL CONSTRAINT PK_Shares PRIMARY KEY,
        Emails nvarchar(50) NULL,
        SharedDate date NULL,
        Username nvarchar(50) NULL,
        VideoId nvarchar(50) NULL,
        CONSTRAINT FK_Shares_Users FOREIGN KEY(Username) REFERENCES dbo.Users(Username),
        CONSTRAINT FK_Shares_Videos FOREIGN KEY(VideoId) REFERENCES dbo.Videos(VideoId)
    );
END;
GO

IF OBJECT_ID(N'dbo.Favorites', N'U') IS NULL
BEGIN
    CREATE TABLE dbo.Favorites (
        FavoriteId int IDENTITY(1,1) NOT NULL CONSTRAINT PK_Favorites PRIMARY KEY,
        LikedDate date NULL,
        VideoId nvarchar(50) NULL,
        Username nvarchar(50) NULL,
        CONSTRAINT FK_Favorites_Videos FOREIGN KEY(VideoId) REFERENCES dbo.Videos(VideoId),
        CONSTRAINT FK_Favorites_Users FOREIGN KEY(Username) REFERENCES dbo.Users(Username),
        CONSTRAINT UQ_Favorites_UserVideo UNIQUE(Username, VideoId)
    );
END;
GO

IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE object_id=OBJECT_ID(N'dbo.Videos') AND name=N'IX_Videos_Category_Active')
    CREATE INDEX IX_Videos_Category_Active ON dbo.Videos(CategoryId, Active, VideoId);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE object_id=OBJECT_ID(N'dbo.Shares') AND name=N'IX_Shares_VideoId')
    CREATE INDEX IX_Shares_VideoId ON dbo.Shares(VideoId);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE object_id=OBJECT_ID(N'dbo.Favorites') AND name=N'IX_Favorites_VideoId')
    CREATE INDEX IX_Favorites_VideoId ON dbo.Favorites(VideoId);
GO
