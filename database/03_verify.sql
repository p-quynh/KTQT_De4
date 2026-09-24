/* Read-only checks after running 01_schema.sql and 02_seed.sql. */
USE KTQT_De4;
GO
SET NOCOUNT ON;

SELECT
    (SELECT COUNT(*) FROM dbo.Users) AS UsersCount,
    (SELECT COUNT(*) FROM dbo.Category) AS CategoriesCount,
    (SELECT COUNT(*) FROM dbo.Videos) AS VideosCount,
    (SELECT COUNT(*) FROM dbo.Shares) AS SharesCount,
    (SELECT COUNT(*) FROM dbo.Favorites) AS FavoritesCount;

SELECT c.CategoryId,c.Categoryname,COUNT(v.VideoId) AS ActiveVideoCount
FROM dbo.Category c
LEFT JOIN dbo.Videos v ON v.CategoryId=c.CategoryId AND v.Active=1
WHERE c.Status=1
GROUP BY c.CategoryId,c.Categoryname
ORDER BY c.CategoryId;

SELECT v.VideoId,v.Title,c.Categoryname,v.Views,
       (SELECT COUNT(*) FROM dbo.Shares s WHERE s.VideoId=v.VideoId) AS ShareCount,
       (SELECT COUNT(*) FROM dbo.Favorites f WHERE f.VideoId=v.VideoId) AS LikeCount
FROM dbo.Videos v
JOIN dbo.Category c ON c.CategoryId=v.CategoryId
WHERE v.VideoId=N'VID001';

SELECT Username,Fullname,Email,[Admin],Active
FROM dbo.Users
ORDER BY Username
OFFSET 0 ROWS FETCH NEXT 6 ROWS ONLY;
GO
