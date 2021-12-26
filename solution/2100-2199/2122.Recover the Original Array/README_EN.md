# [2122. Recover the Original Array](https://leetcode.com/problems/recover-the-original-array)

[中文文档](/solution/2100-2199/2122.Recover%20the%20Original%20Array/README.md)

## Description

<p>Alice had a <strong>0-indexed</strong> array <code>arr</code> consisting of <code>n</code> <strong>positive</strong> integers. She chose an arbitrary <strong>positive integer</strong> <code>k</code> and created two new <strong>0-indexed</strong> integer arrays <code>lower</code> and <code>higher</code> in the following manner:</p>

<ol>
	<li><code>lower[i] = arr[i] - k</code>, for every index <code>i</code> where <code>0 &lt;= i &lt; n</code></li>
	<li><code>higher[i] = arr[i] + k</code>, for every index <code>i</code> where <code>0 &lt;= i &lt; n</code></li>
</ol>

<p>Unfortunately, Alice lost all three arrays. However, she remembers the integers that were present in the arrays <code>lower</code> and <code>higher</code>, but not the array each integer belonged to. Help Alice and recover the original array.</p>

<p>Given an array <code>nums</code> consisting of <code>2n</code> integers, where <strong>exactly</strong> <code>n</code> of the integers were present in <code>lower</code> and the remaining in <code>higher</code>, return <em>the <strong>original</strong> array</em> <code>arr</code>. In case the answer is not unique, return <em><strong>any</strong> valid array</em>.</p>

<p><strong>Note:</strong> The test cases are generated such that there exists <strong>at least one</strong> valid array <code>arr</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,10,6,4,8,12]
<strong>Output:</strong> [3,7,11]
<strong>Explanation:</strong>
If arr = [3,7,11] and k = 1, we get lower = [2,6,10] and higher = [4,8,12].
Combining lower and higher gives us [2,6,10,4,8,12], which is a permutation of nums.
Another valid possibility is that arr = [5,7,9] and k = 3. In that case, lower = [2,4,6] and higher = [8,10,12]. 
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,3,3]
<strong>Output:</strong> [2,2]
<strong>Explanation:</strong>
If arr = [2,2] and k = 1, we get lower = [1,1] and higher = [3,3].
Combining lower and higher gives us [1,1,3,3], which is equal to nums.
Note that arr cannot be [1,3] because in that case, the only possible way to obtain [1,1,3,3] is with k = 0.
This is invalid since k must be positive.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,435]
<strong>Output:</strong> [220]
<strong>Explanation:</strong>
The only possible combination is arr = [220] and k = 215. Using them, we get lower = [5] and higher = [435].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 * n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li>The test cases are generated such that there exists <strong>at least one</strong> valid array <code>arr</code>.</li>
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
