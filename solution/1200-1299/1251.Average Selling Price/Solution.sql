SELECT
    p.product_id,
    Round((Sum(u.units * p.price) + 0.0) / (Sum(units) + 0.0), 2) AS average_price
FROM
    Prices AS p
    INNER JOIN UnitsSold AS u ON p.product_id = u.product_id
WHERE u.purchase_date BETWEEN p.start_date AND p.end_date
GROUP BY p.product_id;
