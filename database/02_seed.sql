/* Sample data for exam 04. Safe to run more than once. Save/run as UTF-8. */
USE KTQT_De4;
GO
SET ANSI_NULLS ON;
SET QUOTED_IDENTIFIER ON;
SET XACT_ABORT ON;

BEGIN TRY
    BEGIN TRANSACTION;

    MERGE dbo.Category WITH (HOLDLOCK) AS target
    USING (VALUES
        (N'Học Java', N'JAVA', N'/assets/poster-java.svg', CAST(1 AS bit)),
        (N'Kỹ năng lập trình', N'CODE', N'/assets/poster-code.svg', CAST(1 AS bit)),
        (N'Cơ sở dữ liệu', N'DB', N'/assets/poster-db.svg', CAST(1 AS bit))
    ) AS source(Categoryname, Categorycode, Images, Status)
    ON target.Categorycode=source.Categorycode
    WHEN MATCHED THEN UPDATE SET
        Categoryname=source.Categoryname, Images=source.Images, Status=source.Status
    WHEN NOT MATCHED THEN INSERT(Categoryname,Categorycode,Images,Status)
        VALUES(source.Categoryname,source.Categorycode,source.Images,source.Status);

    MERGE dbo.Videos WITH (HOLDLOCK) AS target
    USING (
        SELECT sample.VideoId,sample.Title,sample.Poster,sample.Views,sample.[Description],sample.Active,c.CategoryId
        FROM (VALUES
            (N'VID001',N'Giới thiệu Servlet',N'/assets/poster-java.svg',101,N'Khái niệm Servlet và vòng đời.',CAST(1 AS bit),N'JAVA'),
            (N'VID002',N'JSP cơ bản',N'/assets/poster-java.svg',93,N'Cách hiển thị dữ liệu bằng JSP và JSTL.',CAST(1 AS bit),N'JAVA'),
            (N'VID003',N'JDBC với SQL Server',N'/assets/poster-java.svg',76,N'Kết nối và truy vấn cơ sở dữ liệu bằng JDBC.',CAST(1 AS bit),N'JAVA'),
            (N'VID004',N'Mô hình MVC',N'/assets/poster-java.svg',88,N'Tổ chức ứng dụng theo Presentation, Service và DAO.',CAST(1 AS bit),N'JAVA'),
            (N'VID005',N'Phân trang bằng SQL',N'/assets/poster-java.svg',61,N'Sử dụng OFFSET và FETCH NEXT.',CAST(1 AS bit),N'JAVA'),
            (N'VID006',N'Session trong Servlet',N'/assets/poster-java.svg',45,N'Quản lý phiên đăng nhập của người dùng.',CAST(1 AS bit),N'JAVA'),
            (N'VID007',N'SiteMesh Decorators',N'/assets/poster-java.svg',37,N'Tạo bố cục dùng chung cho User và Admin.',CAST(1 AS bit),N'JAVA'),
            (N'VID008',N'Giải thuật sắp xếp',N'/assets/poster-code.svg',83,N'Các giải thuật sắp xếp cơ bản.',CAST(1 AS bit),N'CODE'),
            (N'VID009',N'Thiết kế bảng SQL',N'/assets/poster-db.svg',55,N'Khóa chính, khóa ngoại và chỉ mục.',CAST(1 AS bit),N'DB')
        ) AS sample(VideoId,Title,Poster,Views,[Description],Active,Categorycode)
        JOIN dbo.Category c ON c.Categorycode=sample.Categorycode
    ) AS source
    ON target.VideoId=source.VideoId
    WHEN MATCHED THEN UPDATE SET
        Title=source.Title, Poster=source.Poster, Views=source.Views,
        [Description]=source.[Description], Active=source.Active, CategoryId=source.CategoryId
    WHEN NOT MATCHED THEN INSERT(VideoId,Title,Poster,Views,[Description],Active,CategoryId)
        VALUES(source.VideoId,source.Title,source.Poster,source.Views,source.[Description],source.Active,source.CategoryId);

    /* All sample accounts use Demo1234! (testing only). */
    MERGE dbo.Users WITH (HOLDLOCK) AS target
    USING (VALUES
        (N'admin',N'210000:grT4RiGCDcfGDtEKhgSPHA==:eL8b4mOLg/CyglqPR0iHSgFCrWOhAplvy8NHanVjf/k=',N'0900000000',N'Quản trị viên',N'admin@example.test',CAST(1 AS bit),CAST(1 AS bit),CAST(NULL AS nvarchar(500))),
        (N'student01',N'210000:grT4RiGCDcfGDtEKhgSPHA==:eL8b4mOLg/CyglqPR0iHSgFCrWOhAplvy8NHanVjf/k=',N'0900000001',N'Người dùng mẫu 01',N'student01@example.test',CAST(0 AS bit),CAST(1 AS bit),CAST(NULL AS nvarchar(500))),
        (N'student02',N'210000:/IORQkoS1bcKPfqVw32NlA==:BD7eV/i6CVjwWNujj9A54GKcNEHsNCRELMmnzDsUwCU=',N'0900000002',N'Người dùng mẫu 02',N'student02@example.test',CAST(0 AS bit),CAST(1 AS bit),CAST(NULL AS nvarchar(500))),
        (N'student03',N'210000:RVLzRKXaAM06JlLV2v+6OQ==:djVaMcDkWc115f5n1twrWSsICnDZkZXzr5I/u29RHyI=',N'0900000003',N'Người dùng mẫu 03',N'student03@example.test',CAST(0 AS bit),CAST(1 AS bit),CAST(NULL AS nvarchar(500))),
        (N'student04',N'210000:Ap11lUV+nJ8Qpu6C8m5Pcg==:A6NE3Kn1nZA8CEf5Vu2Hi7iW5ozz3fHsFNdWbjTQMSc=',N'0900000004',N'Người dùng mẫu 04',N'student04@example.test',CAST(0 AS bit),CAST(1 AS bit),CAST(NULL AS nvarchar(500))),
        (N'student05',N'210000:/Ep/bpKp/hHVRnfntjshWQ==:eqVuJKKSulid0AoovWTU3a+kiaPaXuPwRv6yQACtdUk=',N'0900000005',N'Người dùng mẫu 05',N'student05@example.test',CAST(0 AS bit),CAST(1 AS bit),CAST(NULL AS nvarchar(500))),
        (N'student06',N'210000:hbf2e8vK7w06DjL93pXLmQ==:/gkeVGBhr0Xaov1/m1dHcM1tMwlytz0NasPGa51cHzk=',N'0900000006',N'Người dùng mẫu 06',N'student06@example.test',CAST(0 AS bit),CAST(1 AS bit),CAST(NULL AS nvarchar(500))),
        (N'student07',N'210000:o5kKOxdZp4qJCMvCblHrmQ==:18KPO7cWr0hMN7wYvbEYeY1bnNptfNtxZjgMXO3Wq8M=',N'0900000007',N'Người dùng mẫu 07',N'student07@example.test',CAST(0 AS bit),CAST(1 AS bit),CAST(NULL AS nvarchar(500))),
        (N'student08',N'210000:s6zpXDDNabOCTm6iue0x+w==:VHfhLmxm5MgtbloeqlasfBMmXkvyDeskuelN+/q6yMw=',N'0900000008',N'Người dùng mẫu 08',N'student08@example.test',CAST(0 AS bit),CAST(1 AS bit),CAST(NULL AS nvarchar(500))),
        (N'student09',N'210000:7bCSuXYv3cYxGDITP6movg==:cL83kYA1I8kb14i09dTZmrZDORHfLMgwAA3pLhiiuAE=',N'0900000009',N'Người dùng mẫu 09',N'student09@example.test',CAST(0 AS bit),CAST(1 AS bit),CAST(NULL AS nvarchar(500))),
        (N'student10',N'210000:cAAUK+GgSB7A1gtdywZsbw==:naaxXoUUF/Q++lDrAcNlgtGVyjhLnkaFFzZOt6B88BA=',N'0900000010',N'Người dùng mẫu 10',N'student10@example.test',CAST(0 AS bit),CAST(1 AS bit),CAST(NULL AS nvarchar(500))),
        (N'student11',N'210000:fMN3v5XwgDeE9jQzv9BTSQ==:mKjgKxc/GHoWoeUfJ6o0Y69j4ekUurUp1bnTRgDGwLU=',N'0900000011',N'Người dùng mẫu 11',N'student11@example.test',CAST(0 AS bit),CAST(1 AS bit),CAST(NULL AS nvarchar(500))),
        (N'student12',N'210000:XIXYBXBZFYEe2aYRnZlkaA==:bo1ke+fV1uQX3Xl0S4YodLmEVZfpAXzHYWbAvAE1E+o=',N'0900000012',N'Người dùng mẫu 12',N'student12@example.test',CAST(0 AS bit),CAST(1 AS bit),CAST(NULL AS nvarchar(500)))
    ) AS source(Username,[Password],Phone,Fullname,Email,[Admin],Active,Images)
    ON target.Username=source.Username
    WHEN MATCHED THEN UPDATE SET
        [Password]=source.[Password],Phone=source.Phone,Fullname=source.Fullname,Email=source.Email,
        [Admin]=source.[Admin],Active=source.Active,Images=source.Images
    WHEN NOT MATCHED THEN INSERT(Username,[Password],Phone,Fullname,Email,[Admin],Active,Images)
        VALUES(source.Username,source.[Password],source.Phone,source.Fullname,source.Email,source.[Admin],source.Active,source.Images);

    INSERT dbo.Favorites(LikedDate,VideoId,Username)
    SELECT sample.LikedDate,sample.VideoId,sample.Username
    FROM (VALUES
        (CONVERT(date,'2026-09-20'),N'VID001',N'student01'),
        (CONVERT(date,'2026-09-21'),N'VID001',N'student02'),
        (CONVERT(date,'2026-09-22'),N'VID002',N'student03'),
        (CONVERT(date,'2026-09-22'),N'VID003',N'student01'),
        (CONVERT(date,'2026-09-23'),N'VID008',N'student04')
    ) AS sample(LikedDate,VideoId,Username)
    WHERE NOT EXISTS (SELECT 1 FROM dbo.Favorites f WHERE f.VideoId=sample.VideoId AND f.Username=sample.Username);

    INSERT dbo.Shares(Emails,SharedDate,Username,VideoId)
    SELECT sample.Emails,sample.SharedDate,sample.Username,sample.VideoId
    FROM (VALUES
        (N'friend01@example.test',CONVERT(date,'2026-09-20'),N'student01',N'VID001'),
        (N'friend02@example.test',CONVERT(date,'2026-09-21'),N'student02',N'VID001'),
        (N'friend03@example.test',CONVERT(date,'2026-09-22'),N'student03',N'VID002'),
        (N'friend04@example.test',CONVERT(date,'2026-09-23'),N'student04',N'VID008')
    ) AS sample(Emails,SharedDate,Username,VideoId)
    WHERE NOT EXISTS (
        SELECT 1 FROM dbo.Shares s
        WHERE s.Emails=sample.Emails AND s.Username=sample.Username AND s.VideoId=sample.VideoId
    );

    COMMIT TRANSACTION;
END TRY
BEGIN CATCH
    IF @@TRANCOUNT > 0 ROLLBACK TRANSACTION;
    THROW;
END CATCH;
GO
