# [1157. Online Majority Element In Subarray](https://leetcode.com/problems/online-majority-element-in-subarray)

[中文文档](/solution/1100-1199/1157.Online%20Majority%20Element%20In%20Subarray/README.md)

## Description

<p>Implementing the class <code>MajorityChecker</code>, which has the following API:</p>

<ul>
	<li><code>MajorityChecker(int[] arr)</code> constructs an instance of MajorityChecker with the given array <code>arr</code>;</li>
	<li><code>int query(int left, int right, int threshold)</code>&nbsp;has arguments&nbsp;such that:
	<ul>
		<li><code>0 &lt;= left&nbsp;&lt;= right&nbsp;&lt; arr.length</code> representing a subarray of <code>arr</code>;</li>
		<li><code>2 * threshold &gt; right - left + 1</code>, ie. the threshold is always a strict majority of the length of&nbsp;the subarray</li>
	</ul>
	</li>
</ul>

<p>Each&nbsp;<code>query(...)</code> returns the element in <code>arr[left], arr[left+1], ..., arr[right]</code> that occurs at least <code>threshold</code> times, or <code>-1</code> if no such element exists.</p>

<p>&nbsp;</p>

<p><strong>Example:</strong></p>

<pre>

MajorityChecker majorityChecker = new MajorityChecker([1,1,2,2,1,1]);

majorityChecker.query(0,5,4); // returns 1

majorityChecker.query(0,3,3); // returns -1

majorityChecker.query(2,3,2); // returns 2

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;=&nbsp;20000</code></li>
	<li><code>1 &lt;= arr[i]&nbsp;&lt;=&nbsp;20000</code></li>
	<li>For each query, <code>0 &lt;= left &lt;= right &lt; len(arr)</code></li>
	<li>For each query, <code>2 * threshold &gt; right - left + 1</code></li>
	<li>The number of queries is at most <code>10000</code></li>
</ul>

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
