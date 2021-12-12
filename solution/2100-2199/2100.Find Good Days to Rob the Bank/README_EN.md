# [2100. Find Good Days to Rob the Bank](https://leetcode.com/problems/find-good-days-to-rob-the-bank)

[中文文档](/solution/2100-2199/2100.Find%20Good%20Days%20to%20Rob%20the%20Bank/README.md)

## Description

<p>You and a gang of thieves are planning on robbing a bank. You are given a <strong>0-indexed</strong> integer array <code>security</code>, where <code>security[i]</code> is the number of guards on duty on the <code>i<sup>th</sup></code> day. The days are numbered starting from <code>0</code>. You are also given an integer <code>time</code>.</p>

<p>The <code>i<sup>th</sup></code> day is a good day to rob the bank if:</p>

<ul>
	<li>There are at least <code>time</code> days before and after the <code>i<sup>th</sup></code> day,</li>
	<li>The number of guards at the bank for the <code>time</code> days <strong>before</strong> <code>i</code> are <strong>non-increasing</strong>, and</li>
	<li>The number of guards at the bank for the <code>time</code> days <strong>after</strong> <code>i</code> are <strong>non-decreasing</strong>.</li>
</ul>

<p>More formally, this means day <code>i</code> is a good day to rob the bank if and only if <code>security[i - time] &gt;= security[i - time + 1] &gt;= ... &gt;= security[i] &lt;= ... &lt;= security[i + time - 1] &lt;= security[i + time]</code>.</p>

<p>Return <em>a list of <strong>all</strong> days <strong>(0-indexed) </strong>that are good days to rob the bank</em>.<em> The order that the days are returned in does<strong> </strong><strong>not</strong> matter.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> security = [5,3,3,3,5,6,2], time = 2
<strong>Output:</strong> [2,3]
<strong>Explanation:</strong>
On day 2, we have security[0] &gt;= security[1] &gt;= security[2] &lt;= security[3] &lt;= security[4].
On day 3, we have security[1] &gt;= security[2] &gt;= security[3] &lt;= security[4] &lt;= security[5].
No other days satisfy this condition, so days 2 and 3 are the only good days to rob the bank.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> security = [1,1,1,1,1], time = 0
<strong>Output:</strong> [0,1,2,3,4]
<strong>Explanation:</strong>
Since time equals 0, every day is a good day to rob the bank, so return every day.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> security = [1,2,3,4,5,6], time = 2
<strong>Output:</strong> []
<strong>Explanation:</strong>
No day has 2 days before it that have a non-increasing number of guards.
Thus, no day is a good day to rob the bank, so return an empty list.
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> security = [1], time = 5
<strong>Output:</strong> []
<strong>Explanation:</strong>
No day has 5 days before and after it.
Thus, no day is a good day to rob the bank, so return an empty list.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= security.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= security[i], time &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
