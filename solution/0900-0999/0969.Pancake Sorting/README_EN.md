# [969. Pancake Sorting](https://leetcode.com/problems/pancake-sorting)

[中文文档](/solution/0900-0999/0969.Pancake%20Sorting/README.md)

## Description
<p>Given an array <code>A</code>, we can perform a&nbsp;<em>pancake flip</em>:&nbsp;We choose some positive integer&nbsp;<code><strong>k</strong> &lt;= A.length</code>, then reverse the order of the first <strong>k</strong> elements of <code>A</code>.&nbsp; We want to perform zero or more pancake flips (doing them one after another in succession) to sort the array <code>A</code>.</p>



<p>Return the k-values corresponding to a sequence of pancake flips that sort <code>A</code>.&nbsp; Any&nbsp;valid answer that sorts the array within <code>10 * A.length</code> flips will be judged as correct.</p>



<p>&nbsp;</p>



<p><strong>Example 1:</strong></p>



<pre>

<strong>Input: </strong><span id="example-input-1-1">[3,2,4,1]</span>

<strong>Output: </strong><span id="example-output-1">[4,2,4,3]</span>

<strong>Explanation: </strong>

We perform 4 pancake flips, with k values 4, 2, 4, and 3.

Starting state: A = [3, 2, 4, 1]

After 1st flip (k=4): A = [1, 4, 2, 3]

After 2nd flip (k=2): A = [4, 1, 2, 3]

After 3rd flip (k=4): A = [3, 2, 1, 4]

After 4th flip (k=3): A = [1, 2, 3, 4], which is sorted. 

</pre>



<div>

<p><strong>Example 2:</strong></p>



<pre>

<strong>Input: </strong><span id="example-input-2-1">[1,2,3]</span>

<strong>Output: </strong><span id="example-output-2">[]</span>

<strong>Explanation: </strong>The input is already sorted, so there is no need to flip anything.

Note that other answers, such as [3, 3], would also be accepted.

</pre>



<p>&nbsp;</p>

</div>



<p><strong>Note:</strong></p>



<ol>

	<li><code>1 &lt;= A.length &lt;= 100</code></li>

	<li><code>A[i]</code> is a permutation of <code>[1, 2, ..., A.length]</code></li>

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