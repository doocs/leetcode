# [396. Rotate Function](https://leetcode.com/problems/rotate-function)

[中文文档](/solution/0300-0399/0396.Rotate%20Function/README.md)

## Description

<p>

Given an array of integers <code>A</code> and let <i>n</i> to be its length.

</p>

<p>

Assume <code>B<sub>k</sub></code> to be an array obtained by rotating the array <code>A</code> <i>k</i> positions clock-wise, we define a "rotation function" <code>F</code> on <code>A</code> as follow:

</p>

<p>

<code>F(k) = 0 _ B<sub>k</sub>[0] + 1 _ B<sub>k</sub>[1] + ... + (n-1) \* B<sub>k</sub>[n-1]</code>.</p>

<p>Calculate the maximum value of <code>F(0), F(1), ..., F(n-1)</code>.

</p>

<p><b>Note:</b><br />

<i>n</i> is guaranteed to be less than 10<sup>5</sup>.

</p>

<p><b>Example:</b>

<pre>

A = [4, 3, 2, 6]



F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25

F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16

F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23

F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26



So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.

</pre>

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
