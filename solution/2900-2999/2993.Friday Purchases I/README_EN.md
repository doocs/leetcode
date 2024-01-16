# [2993. Friday Purchases I](https://leetcode.com/problems/friday-purchases-i)

[中文文档](/solution/2900-2999/2993.Friday%20Purchases%20I/README.md)

## Description

<p>Table: <code>Purchases</code></p>

<pre>
+---------------+------+
| Column Name   | Type |
+---------------+------+
| user_id       | int  |
| purchase_date | date |
| amount_spend  | int  |
+---------------+------+
(user_id, purchase_date, amount_spend) is the primary key (combination of columns with unique values) for this table.
purchase_date will range from November 1, 2023, to November 30, 2023, inclusive of both dates.
Each row contains user id, purchase date, and amount spend.
</pre>

<p>Write a solution to calculate the <strong>total spending</strong> by users on <strong>each Friday</strong> of <strong>every week</strong> in <strong>November 2023</strong>. Output only weeks that include <strong>at least one</strong> purchase on a <strong>Friday</strong>.</p>

<p>Return <em>the result table ordered by week of month</em><em> in <strong>ascending</strong></em><em><strong> </strong>order.</em></p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Purchases table:
+---------+---------------+--------------+
| user_id | purchase_date | amount_spend |
+---------+---------------+--------------+
| 11      | 2023-11-07    | 1126         |
| 15      | 2023-11-30    | 7473         |
| 17      | 2023-11-14    | 2414         |
| 12      | 2023-11-24    | 9692         |
| 8       | 2023-11-03    | 5117         |
| 1       | 2023-11-16    | 5241         |
| 10      | 2023-11-12    | 8266         |
| 13      | 2023-11-24    | 12000        |
+---------+---------------+--------------+
<strong>Output:</strong> 
+---------------+---------------+--------------+
| week_of_month | purchase_date | total_amount |
+---------------+---------------+--------------+
| 1             | 2023-11-03    | 5117         |
| 4             | 2023-11-24    | 21692        |
+---------------+---------------+--------------+ 
<strong>Explanation:</strong> 
- During the first week of November 2023, transactions amounting to $5,117 occurred on Friday, 2023-11-03.
- For the second week of November 2023, there were no transactions on Friday, 2023-11-10.
- Similarly, during the third week of November 2023, there were no transactions on Friday, 2023-11-17.
- In the fourth week of November 2023, two transactions took place on Friday, 2023-11-24, amounting to $12,000 and $9,692 respectively, summing up to a total of $21,692.
Output table is ordered by week_of_month in ascending order.</pre>

## Solutions

### Solution 1: Date Functions

The date functions we use include:

-   `DATE_FORMAT(date, format)`: Formats a date as a string
-   `DAYOFWEEK(date)`: Returns the day of the week for a date, where 1 represents Sunday, 2 represents Monday, and so on
-   `DAYOFMONTH(date)`: Returns the day of the month for a date

First, we use the `DATE_FORMAT` function to format the date in the form of `YYYYMM`, then filter out the records of November 2023 that fall on a Friday. Next, we group the records by `purchase_date` and calculate the total consumption amount for each Friday.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    CEIL(DAYOFMONTH(purchase_date) / 7) AS week_of_month,
    purchase_date,
    SUM(amount_spend) AS total_amount
FROM Purchases
WHERE DATE_FORMAT(purchase_date, '%Y%m') = '202311' AND DAYOFWEEK(purchase_date) = 6
GROUP BY 2
ORDER BY 1;
```

<!-- tabs:end -->

<!-- end -->
