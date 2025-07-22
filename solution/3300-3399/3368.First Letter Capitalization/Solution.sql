WITH RECURSIVE
    capitalized_words AS (
        SELECT
            content_id,
            content_text,
            SUBSTRING_INDEX(content_text, ' ', 1) AS word,
            SUBSTRING(
                content_text,
                LENGTH(SUBSTRING_INDEX(content_text, ' ', 1)) + 2
            ) AS remaining_text,
            CONCAT(
                UPPER(LEFT(SUBSTRING_INDEX(content_text, ' ', 1), 1)),
                LOWER(SUBSTRING(SUBSTRING_INDEX(content_text, ' ', 1), 2))
            ) AS processed_word
        FROM user_content
        UNION ALL
        SELECT
            c.content_id,
            c.content_text,
            SUBSTRING_INDEX(c.remaining_text, ' ', 1),
            SUBSTRING(c.remaining_text, LENGTH(SUBSTRING_INDEX(c.remaining_text, ' ', 1)) + 2),
            CONCAT(
                c.processed_word,
                ' ',
                CONCAT(
                    UPPER(LEFT(SUBSTRING_INDEX(c.remaining_text, ' ', 1), 1)),
                    LOWER(SUBSTRING(SUBSTRING_INDEX(c.remaining_text, ' ', 1), 2))
                )
            )
        FROM capitalized_words c
        WHERE c.remaining_text != ''
    )
SELECT
    content_id,
    content_text AS original_text,
    MAX(processed_word) AS converted_text
FROM capitalized_words
GROUP BY 1, 2;
