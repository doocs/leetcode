# [585. Investments in 2016](https://leetcode.com/problems/investments-in-2016)

[中文文档](/solution/0500-0599/0585.Investments%20in%202016/README.md)

## Description

<p>Write a query to print the sum of all total investment values in 2016 (<b>TIV_2016</b>), to a scale of 2 decimal places, for all policy holders who meet the following criteria:</p>

<ol>
	<li>Have the same <b>TIV_2015</b> value as one or more other policyholders.</li>
	<li>Are not located in the same city as any other policyholder (i.e.: the (latitude, longitude) attribute pairs must be unique).</li>
</ol>

<p><b>Input Format:</b><br />

The <b><i>insurance</i></b> table is described as follows:</p>

<pre>

| Column Name | Type          |

|-------------|---------------|

| PID         | INTEGER(11)   |

| TIV_2015    | NUMERIC(15,2) |

| TIV_2016    | NUMERIC(15,2) |

| LAT         | NUMERIC(5,2)  |

| LON         | NUMERIC(5,2)  |

</pre>

<p>where <b>PID</b> is the policyholder&#39;s policy ID, <b>TIV_2015</b> is the total investment value in 2015, <b>TIV_2016</b> is the total investment value in 2016, <b>LAT</b> is the latitude of the policy holder&#39;s city, and <b>LON</b> is the longitude of the policy holder&#39;s city.</p>

<p><b>Sample Input</b></p>

<pre>

| PID | TIV_2015 | TIV_2016 | LAT | LON |

|-----|----------|----------|-----|-----|

| 1   | 10       | 5        | 10  | 10  |

| 2   | 20       | 20       | 20  | 20  |

| 3   | 10       | 30       | 20  | 20  |

| 4   | 10       | 40       | 40  | 40  |

</pre>

<p><b>Sample Output</b></p>

<pre>

| TIV_2016 |

|----------|

| 45.00    |

</pre>

<p><b>Explanation</b></p>

<pre>

The first record in the table, like the last record, meets both of the two criteria.

The <b>TIV_2015</b> value &#39;10&#39; is as the same as the third and forth record, and its location unique.



The second record does not meet any of the two criteria. Its <b>TIV_2015</b> is not like any other policyholders.



And its location is the same with the third record, which makes the third record fail, too.



So, the result is the sum of <b>TIV_2016</b> of the first and last record, which is 45.</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
