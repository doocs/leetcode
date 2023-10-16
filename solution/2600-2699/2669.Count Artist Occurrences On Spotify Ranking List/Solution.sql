# Write your MySQL query statement below
SELECT
    artist,
    COUNT(1) AS occurrences
FROM Spotify
GROUP BY artist
ORDER BY occurrences DESC, artist;
