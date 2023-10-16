# Write your MySQL query statement below
SELECT
    invoice_id,
    t2.customer_name,
    price,
    COUNT(t3.user_id) AS contacts_cnt,
    COUNT(t4.email) AS trusted_contacts_cnt
FROM
    Invoices AS t1
    LEFT JOIN Customers AS t2 ON t1.user_id = t2.customer_id
    LEFT JOIN Contacts AS t3 ON t1.user_id = t3.user_id
    LEFT JOIN Customers AS t4 ON t3.contact_email = t4.email
GROUP BY invoice_id
ORDER BY invoice_id;
