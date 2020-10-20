# [1385. Find the Distance Value Between Two Arrays](https://leetcode.com/problems/find-the-distance-value-between-two-arrays)

[中文文档](/solution/1300-1399/1385.Find%20the%20Distance%20Value%20Between%20Two%20Arrays/README.md)

## Description

<p>Given two integer arrays <code>arr1</code> and <code>arr2</code>, and the integer <code>d</code>, <em>return the distance value between the two&nbsp;arrays</em>.</p>

<p>The distance value is defined as the number of elements <code>arr1[i]</code> such that there is not any element <code>arr2[j]</code> where <code>|arr1[i]-arr2[j]| &lt;= d</code>.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> arr1 = [4,5,8], arr2 = [10,9,1,8], d = 2

<strong>Output:</strong> 2

<strong>Explanation:</strong> 

For arr1[0]=4 we have: 

|4-10|=6 &gt; d=2 

|4-9|=5 &gt; d=2 

|4-1|=3 &gt; d=2 

|4-8|=4 &gt; d=2 

For arr1[1]=5 we have: 

|5-10|=5 &gt; d=2 

|5-9|=4 &gt; d=2 

|5-1|=4 &gt; d=2 

|5-8|=3 &gt; d=2

For arr1[2]=8 we have:

<strong>|8-10|=2 &lt;= d=2</strong>

<strong>|8-9|=1 &lt;= d=2</strong>

|8-1|=7 &gt; d=2

<strong>|8-8|=0 &lt;= d=2</strong>

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> arr1 = [1,4,2,3], arr2 = [-4,-3,6,10,20,30], d = 3

<strong>Output:</strong> 2

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> arr1 = [2,1,100,3], arr2 = [-5,-2,10,-3,7], d = 6

<strong>Output:</strong> 1

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li><code>1 &lt;= arr1.length, arr2.length &lt;= 500</code></li>

    <li><code>-10^3 &lt;= arr1[i], arr2[j] &lt;= 10^3</code></li>

    <li><code>0 &lt;= d &lt;= 100</code></li>

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
