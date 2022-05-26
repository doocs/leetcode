SELECT name
FROM salesperson
WHERE sales_id NOT IN (
        SELECT s.sales_id
        FROM orders o
            INNER JOIN salesperson s ON o.sales_id = s.sales_id
            INNER JOIN company c ON o.com_id = c.com_id
        WHERE c.name = 'RED'
    );