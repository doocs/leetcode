# [715. Range Module](https://leetcode.com/problems/range-module)

[中文文档](/solution/0700-0799/0715.Range%20Module/README.md)

## Description

<p>A Range Module is a module that tracks ranges of numbers. Your task is to design and implement the following interfaces in an efficient manner.</p>

<p><li><code>addRange(int left, int right)</code> Adds the half-open interval <code>[left, right)</code>, tracking every real number in that interval.  Adding an interval that partially overlaps with currently tracked numbers should add any numbers in the interval <code>[left, right)</code> that are not already tracked.</li></p>

<p><li><code>queryRange(int left, int right)</code> Returns true if and only if every real number in the interval <code>[left, right)</code>

is currently being tracked.</li></p>

<p><li><code>removeRange(int left, int right)</code> Stops tracking every real number currently being tracked in the interval <code>[left, right)</code>.</li></p>

<p><b>Example 1:</b><br />

<pre>

<b>addRange(10, 20)</b>: null

<b>removeRange(14, 16)</b>: null

<b>queryRange(10, 14)</b>: true (Every number in [10, 14) is being tracked)

<b>queryRange(13, 15)</b>: false (Numbers like 14, 14.03, 14.17 in [13, 15) are not being tracked)

<b>queryRange(16, 17)</b>: true (The number 16 in [16, 17) is still being tracked, despite the remove operation)

</pre>

</p>

<p><b>Note:</b>

<li>A half open interval <code>[left, right)</code> denotes all real numbers <code>left <= x < right</code>.</li>

<li><code>0 < left < right < 10^9</code> in all calls to <code>addRange, queryRange, removeRange</code>.</li>

<li>The total number of calls to <code>addRange</code> in a single test case is at most <code>1000</code>.</li>

<li>The total number of calls to <code>queryRange</code> in a single test case is at most <code>5000</code>.</li>

<li>The total number of calls to <code>removeRange</code> in a single test case is at most <code>1000</code>.</li>

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
