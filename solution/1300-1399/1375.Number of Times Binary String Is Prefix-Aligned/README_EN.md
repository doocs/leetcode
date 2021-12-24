# [1375. Number of Times Binary String Is Prefix-Aligned](https://leetcode.com/problems/number-of-times-binary-string-is-prefix-aligned)

[中文文档](/solution/1300-1399/1375.Number%20of%20Times%20Binary%20String%20Is%20Prefix-Aligned/README.md)

## Description

<p>You have a <strong>1-indexed</strong> binary string of length <code>n</code> where all the bits are <code>0</code> initially. We will flip all the bits of this binary string (i.e., change them from <code>0</code> to <code>1</code>) one by one. You are given a <strong>1-indexed</strong> integer array <code>flips</code> where <code>flips[i]</code> indicates that the bit at index <code>i</code> will be flipped in the <code>i<sup>th</sup></code> step.</p>

<p>A binary string is <strong>prefix-aligned</strong> if, after the <code>i<sup>th</sup></code> step, all the bits in the <strong>inclusive</strong> range <code>[1, i]</code> are ones and all the other bits are zeros.</p>

<p>Return <em>the number of times the binary string is <strong>prefix-aligned</strong> during the flipping process</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> flips = [3,2,4,1,5]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The binary string is initially &quot;00000&quot;.
After applying step 1: The string becomes &quot;00100&quot;, which is not prefix-aligned.
After applying step 2: The string becomes &quot;01100&quot;, which is not prefix-aligned.
After applying step 3: The string becomes &quot;01110&quot;, which is not prefix-aligned.
After applying step 4: The string becomes &quot;11110&quot;, which is prefix-aligned.
After applying step 5: The string becomes &quot;11111&quot;, which is prefix-aligned.
We can see that the string was prefix-aligned 2 times, so we return 2.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> flips = [4,1,2,3]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The binary string is initially &quot;0000&quot;.
After applying step 1: The string becomes &quot;0001&quot;, which is not prefix-aligned.
After applying step 2: The string becomes &quot;1001&quot;, which is not prefix-aligned.
After applying step 3: The string becomes &quot;1101&quot;, which is not prefix-aligned.
After applying step 4: The string becomes &quot;1111&quot;, which is prefix-aligned.
We can see that the string was prefix-aligned 1 time, so we return 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == flips.length</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>flips</code> is a permutation of the integers in the range <code>[1, n]</code>.</li>
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
