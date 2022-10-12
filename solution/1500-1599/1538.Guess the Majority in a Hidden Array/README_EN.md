# [1538. Guess the Majority in a Hidden Array](https://leetcode.com/problems/guess-the-majority-in-a-hidden-array)

[中文文档](/solution/1500-1599/1538.Guess%20the%20Majority%20in%20a%20Hidden%20Array/README.md)

## Description

<p>We have an integer array <code>nums</code>, where all the integers in <code>nums</code> are <strong>0</strong> or <strong>1</strong>. You will not be given direct access to the array, instead, you will have an <strong>API</strong> <code>ArrayReader</code> which have the following functions:</p>

<ul>
	<li><code>int query(int a, int b, int c, int d)</code>: where <code>0 &lt;= a &lt; b &lt; c &lt; d &lt; ArrayReader.length()</code>. The function returns the distribution of the value of the 4 elements and returns:

    <ul>
    	<li><strong>4 </strong>: if the values of the 4 elements are the same (0 or 1).</li>
    	<li><strong>2</strong> : if three elements have a value equal to 0 and one element has value equal to 1 or vice versa.</li>
    	<li><strong>0 </strong>: if two element have a value equal to 0 and two elements have a value equal to 1.</li>
    </ul>
    </li>
    <li><code>int length()</code>: Returns the size of the array.</li>

</ul>

<p>You are allowed to call <code>query()</code> <b>2 * n times</b> at most where n is equal to <code>ArrayReader.length()</code>.</p>

<p>Return <strong>any</strong> index of the most frequent value in <code>nums</code>, in case of tie, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,0,1,0,1,1,1,1]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The following calls to the API
reader.length() // returns 8 because there are 8 elements in the hidden array.
reader.query(0,1,2,3) // returns 2 this is a query that compares the elements nums[0], nums[1], nums[2], nums[3]
// Three elements have a value equal to 0 and one element has value equal to 1 or viceversa.
reader.query(4,5,6,7) // returns 4 because nums[4], nums[5], nums[6], nums[7] have the same value.
we can infer that the most frequent value is found in the last 4 elements.
Index 2, 4, 6, 7 is also a correct answer.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,0,1,1,0]
<strong>Output:</strong> 0
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,0,1,0,1,0,1,0]
<strong>Output:</strong> -1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>5 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> What is the minimum number of calls needed to find the majority element?</p>

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
