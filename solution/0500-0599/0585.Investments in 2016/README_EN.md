# [585. Investments in 2016](https://leetcode.com/problems/investments-in-2016)

[中文文档](/solution/0500-0599/0585.Investments%20in%202016/README.md)

## Description

<p>Table: <code>Insurance</code></p>

<pre>
+-------------+-------+
| Column Name | Type  |
+-------------+-------+
| pid         | int   |
| tiv_2015    | float |
| tiv_2016    | float |
| lat         | float |
| lon         | float |
+-------------+-------+
pid is the primary key column for this table.
Each row of this table contains information about one policy where:
pid is the policyholder&#39;s policy ID.
tiv_2015 is the total investment value in 2015 and tiv_2016 is the total investment value in 2016.
lat is the latitude of the policy holder&#39;s city. It&#39;s guaranteed that lat is not NULL.
lon is the longitude of the policy holder&#39;s city. It&#39;s guaranteed that lon is not NULL.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report the sum of all total investment values in 2016 <code>tiv_2016</code>, for all policyholders who:</p>

<ul>
	<li>have the same <code>tiv_2015</code> value as one or more other policyholders, and</li>
	<li>are not located in the same city like any other policyholder (i.e., the (<code>lat, lon</code>) attribute pairs must be unique).</li>
</ul>

<p>Round <code>tiv_2016</code> to <strong>two decimal places</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Insurance table:
+-----+----------+----------+-----+-----+
| pid | tiv_2015 | tiv_2016 | lat | lon |
+-----+----------+----------+-----+-----+
| 1   | 10       | 5        | 10  | 10  |
| 2   | 20       | 20       | 20  | 20  |
| 3   | 10       | 30       | 20  | 20  |
| 4   | 10       | 40       | 40  | 40  |
+-----+----------+----------+-----+-----+
<strong>Output:</strong> 
+----------+
| tiv_2016 |
+----------+
| 45.00    |
+----------+
<strong>Explanation:</strong> 
The first record in the table, like the last record, meets both of the two criteria.
The tiv_2015 value 10 is the same as the third and fourth records, and its location is unique.

The second record does not meet any of the two criteria. Its tiv_2015 is not like any other policyholders and its location is the same as the third record, which makes the third record fail, too.
So, the result is the sum of tiv_2016 of the first and last record, which is 45.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
