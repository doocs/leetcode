# [1969. Minimum Non-Zero Product of the Array Elements](https://leetcode.com/problems/minimum-non-zero-product-of-the-array-elements)

[中文文档](/solution/1900-1999/1969.Minimum%20Non-Zero%20Product%20of%20the%20Array%20Elements/README.md)

## Description

<p>You are given a positive integer <code>p</code>. Consider an array <code>nums</code> (<strong>1-indexed</strong>) that consists of the integers in the <strong>inclusive</strong> range <code>[1, 2<sup>p</sup> - 1]</code> in their binary representations. You are allowed to do the following operation <strong>any</strong> number of times:</p>

<ul>
	<li>Choose two elements <code>x</code> and <code>y</code> from <code>nums</code>.</li>
	<li>Choose a bit in <code>x</code> and swap it with its corresponding bit in <code>y</code>. Corresponding bit refers to the bit that is in the <strong>same position</strong> in the other integer.</li>
</ul>

<p>For example, if <code>x = 11<u>0</u>1</code> and <code>y = 00<u>1</u>1</code>, after swapping the <code>2<sup>nd</sup></code> bit from the right, we have <code>x = 11<u>1</u>1</code> and <code>y = 00<u>0</u>1</code>.</p>

<p>Find the <strong>minimum non-zero</strong> product of <code>nums</code> after performing the above operation <strong>any</strong> number of times. Return <em>this product</em><em> <strong>modulo</strong> </em><code>10<sup>9</sup> + 7</code>.</p>

<p><strong>Note:</strong> The answer should be the minimum product <strong>before</strong> the modulo operation is done.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> p = 1
<strong>Output:</strong> 1
<strong>Explanation:</strong> nums = [1].
There is only one element, so the product equals that element.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> p = 2
<strong>Output:</strong> 6
<strong>Explanation:</strong> nums = [01, 10, 11].
Any swap would either make the product 0 or stay the same.
Thus, the array product of 1 * 2 * 3 = 6 is already minimized.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> p = 3
<strong>Output:</strong> 1512
<strong>Explanation:</strong> nums = [001, 010, 011, 100, 101, 110, 111]
- In the first operation we can swap the leftmost bit of the second and fifth elements.
    - The resulting array is [001, <u>1</u>10, 011, 100, <u>0</u>01, 110, 111].
- In the second operation we can swap the middle bit of the third and fourth elements.
    - The resulting array is [001, 110, 0<u>0</u>1, 1<u>1</u>0, 001, 110, 111].
The array product is 1 * 6 * 1 * 6 * 1 * 6 * 7 = 1512, which is the minimum possible product.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= p &lt;= 60</code></li>
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
