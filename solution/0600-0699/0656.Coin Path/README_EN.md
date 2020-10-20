# [656. Coin Path](https://leetcode.com/problems/coin-path)

[中文文档](/solution/0600-0699/0656.Coin%20Path/README.md)

## Description

<p>Given an array <code>A</code> (index starts at <code>1</code>) consisting of N integers: A<sub>1</sub>, A<sub>2</sub>, ..., A<sub>N</sub> and an integer <code>B</code>. The integer <code>B</code> denotes that from any place (suppose the index is <code>i</code>) in the array <code>A</code>, you can jump to any one of the place in the array <code>A</code> indexed <code>i+1</code>, <code>i+2</code>, …, <code>i+B</code> if this place can be jumped to. Also, if you step on the index <code>i</code>, you have to pay A<sub>i</sub> coins. If A<sub>i</sub> is -1, it means you can’t jump to the place indexed <code>i</code> in the array.</p>

<p>Now, you start from the place indexed <code>1</code> in the array <code>A</code>, and your aim is to reach the place indexed <code>N</code> using the minimum coins. You need to return the path of indexes (starting from 1 to N) in the array you should take to get to the place indexed <code>N</code> using minimum coins.</p>

<p>If there are multiple paths with the same cost, return the lexicographically smallest such path.</p>

<p>If it's not possible to reach the place indexed N then you need to return an empty array.</p>

<p><b>Example 1:</b></p>

<pre>
<b>Input:</b> [1,2,4,-1,2], 2
<b>Output:</b> [1,3,5]
</pre>

<p> </p>

<p><b>Example 2:</b></p>

<pre>
<b>Input:</b> [1,2,4,-1,2], 1
<b>Output:</b> []
</pre>

<p> </p>

<p><b>Note:</b></p>

<ol>
	<li>Path Pa<sub>1</sub>, Pa<sub>2</sub>, ..., Pa<sub>n</sub> is lexicographically smaller than Pb<sub>1</sub>, Pb<sub>2</sub>, ..., Pb<sub>m</sub>, if and only if at the first <code>i</code> where Pa<sub>i</sub> and Pb<sub>i</sub> differ, Pa<sub>i</sub> < Pb<sub>i</sub>; when no such <code>i</code> exists, then <code>n</code> < <code>m</code>.</li>
	<li>A<sub>1</sub> >= 0. A<sub>2</sub>, ..., A<sub>N</sub> (if exist) will in the range of [-1, 100].</li>
	<li>Length of A is in the range of [1, 1000].</li>
	<li>B is in the range of [1, 100].</li>
</ol>

<p> </p>

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
