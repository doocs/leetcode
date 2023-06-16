SELECT DISTINCT (author_id) AS id
FROM Views
WHERE author_id = viewer_id
ORDER BY id;
