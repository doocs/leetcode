# [1152. Analyze User Website Visit Pattern](https://leetcode.com/problems/analyze-user-website-visit-pattern)

[中文文档](/solution/1100-1199/1152.Analyze%20User%20Website%20Visit%20Pattern/README.md)

## Description

<p>We are given some website visits: the user with name&nbsp;<code>username[i]</code> visited the website&nbsp;<code>website[i]</code> at time <code>timestamp[i]</code>.</p>

<p>A <em>3-sequence</em>&nbsp;is a list of&nbsp;websites of length 3 sorted in ascending order&nbsp;by the time of their visits.&nbsp; (The websites in a 3-sequence are not necessarily distinct.)</p>

<p>Find the 3-sequence visited&nbsp;by the largest number of users. If there is more than one solution, return the lexicographically smallest such 3-sequence.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>
<strong>Input: </strong>username = <span>[&quot;joe&quot;,&quot;joe&quot;,&quot;joe&quot;,&quot;james&quot;,&quot;james&quot;,&quot;james&quot;,&quot;james&quot;,&quot;mary&quot;,&quot;mary&quot;,&quot;mary&quot;]</span>, timestamp = <span id="example-input-1-2">[1,2,3,4,5,6,7,8,9,10]</span>, website = <span id="example-input-1-3">[&quot;home&quot;,&quot;about&quot;,&quot;career&quot;,&quot;home&quot;,&quot;cart&quot;,&quot;maps&quot;,&quot;home&quot;,&quot;home&quot;,&quot;about&quot;,&quot;career&quot;]</span>
<strong>Output: </strong><span id="example-output-1">[&quot;home&quot;,&quot;about&quot;,&quot;career&quot;]</span>
<strong>Explanation: </strong>
The tuples in this example are:
[&quot;joe&quot;, 1, &quot;home&quot;]
[&quot;joe&quot;, 2, &quot;about&quot;]
[&quot;joe&quot;, 3, &quot;career&quot;]
[&quot;james&quot;, 4, &quot;home&quot;]
[&quot;james&quot;, 5, &quot;cart&quot;]
[&quot;james&quot;, 6, &quot;maps&quot;]
[&quot;james&quot;, 7, &quot;home&quot;]
[&quot;mary&quot;, 8, &quot;home&quot;]
[&quot;mary&quot;, 9, &quot;about&quot;]
[&quot;mary&quot;, 10, &quot;career&quot;]
The 3-sequence (&quot;home&quot;, &quot;about&quot;, &quot;career&quot;) was visited at least once by <strong>2</strong> users.
The 3-sequence (&quot;home&quot;, &quot;cart&quot;, &quot;maps&quot;) was visited at least once by 1 user.
The 3-sequence (&quot;home&quot;, &quot;cart&quot;, &quot;home&quot;) was visited at least once by 1 user.
The 3-sequence (&quot;home&quot;, &quot;maps&quot;, &quot;home&quot;) was visited at least once by 1 user.
The 3-sequence (&quot;cart&quot;, &quot;maps&quot;, &quot;home&quot;) was visited at least once by 1 user.
</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>3 &lt;= N = username.length = timestamp.length = website.length &lt;= 50</code></li>
	<li><code>1 &lt;= username[i].length &lt;= 10</code></li>
	<li><code>0 &lt;= timestamp[i] &lt;= 10^9</code></li>
	<li><code>1 &lt;= website[i].length &lt;= 10</code></li>
	<li>Both <code>username[i]</code> and <code>website[i]</code> contain only lowercase characters.</li>
	<li>It is guaranteed that there is at least one user who visited at least 3 websites.</li>
	<li>No user visits two websites at the same time.</li>
</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
