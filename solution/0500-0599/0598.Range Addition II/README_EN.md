# [598. Range Addition II](https://leetcode.com/problems/range-addition-ii)

[中文文档](/solution/0500-0599/0598.Range%20Addition%20II/README.md)

## Description

<p>Given an m * n matrix <b>M</b> initialized with all <b>0</b>'s and several update operations.</p>

<p>Operations are represented by a 2D array, and each operation is represented by an array with two <b>positive</b> integers <b>a</b> and <b>b</b>, which means <b>M[i][j]</b> should be <b>added by one</b> for all <b>0 <= i < a</b> and <b>0 <= j < b</b>. </p>

<p>You need to count and return the number of maximum integers in the matrix after performing all the operations.</p>

<p><b>Example 1:</b><br />

<pre>

<b>Input:</b> 

m = 3, n = 3

operations = [[2,2],[3,3]]

<b>Output:</b> 4

<b>Explanation:</b> 

Initially, M = 

[[0, 0, 0],

 [0, 0, 0],

 [0, 0, 0]]



After performing [2,2], M = 

[[1, 1, 0],

 [1, 1, 0],

 [0, 0, 0]]



After performing [3,3], M = 

[[2, 2, 1],

 [2, 2, 1],

 [1, 1, 1]]



So the maximum integer in M is 2, and there are four of it in M. So return 4.

</pre>

</p>

<p><b>Note:</b><br>

<ol>

<li>The range of m and n is [1,40000].</li>

<li>The range of a is [1,m], and the range of b is [1,n].</li>

<li>The range of operations size won't exceed 10,000.</li>

</ol>

</p>

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
