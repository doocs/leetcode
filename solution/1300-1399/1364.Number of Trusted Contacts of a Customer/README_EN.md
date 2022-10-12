# [1364. Number of Trusted Contacts of a Customer](https://leetcode.com/problems/number-of-trusted-contacts-of-a-customer)

[中文文档](/solution/1300-1399/1364.Number%20of%20Trusted%20Contacts%20of%20a%20Customer/README.md)

## Description

<p>Table: <code>Customers</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| customer_id   | int     |
| customer_name | varchar |
| email         | varchar |
+---------------+---------+
customer_id is the primary key for this table.
Each row of this table contains the name and the email of a customer of an online shop.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Contacts</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| user_id       | id      |
| contact_name  | varchar |
| contact_email | varchar |
+---------------+---------+
(user_id, contact_email) is the primary key for this table.
Each row of this table contains the name and email of one contact of customer with user_id.
This table contains information about people each customer trust. The contact may or may not exist in the Customers table.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Invoices</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| invoice_id   | int     |
| price        | int     |
| user_id      | int     |
+--------------+---------+
invoice_id is the primary key for this table.
Each row of this table indicates that user_id has an invoice with invoice_id and a price.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to find the following for each <code>invoice_id</code>:</p>

<ul>
	<li><code>customer_name</code>: The name of the customer the invoice is related to.</li>
	<li><code>price</code>: The price of the invoice.</li>
	<li><code>contacts_cnt</code>: The number of contacts related to the customer.</li>
	<li><code>trusted_contacts_cnt</code>: The number of contacts related to the customer and at the same time they are customers to the shop. (i.e their email exists in the <code>Customers</code> table.)</li>
</ul>

<p>Return the result table <strong>ordered</strong> by <code>invoice_id</code>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Customers table:
+-------------+---------------+--------------------+
| customer_id | customer_name | email              |
+-------------+---------------+--------------------+
| 1           | Alice         | alice@leetcode.com |
| 2           | Bob           | bob@leetcode.com   |
| 13          | John          | john@leetcode.com  |
| 6           | Alex          | alex@leetcode.com  |
+-------------+---------------+--------------------+
Contacts table:
+-------------+--------------+--------------------+
| user_id     | contact_name | contact_email      |
+-------------+--------------+--------------------+
| 1           | Bob          | bob@leetcode.com   |
| 1           | John         | john@leetcode.com  |
| 1           | Jal          | jal@leetcode.com   |
| 2           | Omar         | omar@leetcode.com  |
| 2           | Meir         | meir@leetcode.com  |
| 6           | Alice        | alice@leetcode.com |
+-------------+--------------+--------------------+
Invoices table:
+------------+-------+---------+
| invoice_id | price | user_id |
+------------+-------+---------+
| 77         | 100   | 1       |
| 88         | 200   | 1       |
| 99         | 300   | 2       |
| 66         | 400   | 2       |
| 55         | 500   | 13      |
| 44         | 60    | 6       |
+------------+-------+---------+
<strong>Output:</strong> 
+------------+---------------+-------+--------------+----------------------+
| invoice_id | customer_name | price | contacts_cnt | trusted_contacts_cnt |
+------------+---------------+-------+--------------+----------------------+
| 44         | Alex          | 60    | 1            | 1                    |
| 55         | John          | 500   | 0            | 0                    |
| 66         | Bob           | 400   | 2            | 0                    |
| 77         | Alice         | 100   | 3            | 2                    |
| 88         | Alice         | 200   | 3            | 2                    |
| 99         | Bob           | 300   | 2            | 0                    |
+------------+---------------+-------+--------------+----------------------+
<strong>Explanation:</strong> 
Alice has three contacts, two of them are trusted contacts (Bob and John).
Bob has two contacts, none of them is a trusted contact.
Alex has one contact and it is a trusted contact (Alice).
John doesn&#39;t have any contacts.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
