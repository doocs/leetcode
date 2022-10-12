# [1521. Find a Value of a Mysterious Function Closest to Target](https://leetcode.com/problems/find-a-value-of-a-mysterious-function-closest-to-target)

[中文文档](/solution/1500-1599/1521.Find%20a%20Value%20of%20a%20Mysterious%20Function%20Closest%20to%20Target/README.md)

## Description

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1521.Find%20a%20Value%20of%20a%20Mysterious%20Function%20Closest%20to%20Target/images/change.png" style="width: 635px; height: 312px;" /></p>

<p>Winston was given the above mysterious function <code>func</code>. He has an integer array <code>arr</code> and an integer <code>target</code> and he wants to find the values <code>l</code> and <code>r</code> that make the value <code>|func(arr, l, r) - target|</code> minimum possible.</p>

<p>Return <em>the minimum possible value</em> of <code>|func(arr, l, r) - target|</code>.</p>

<p>Notice that <code>func</code> should be called with the values <code>l</code> and <code>r</code> where <code>0 &lt;= l, r &lt; arr.length</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [9,12,3,7,15], target = 5
<strong>Output:</strong> 2
<strong>Explanation:</strong> Calling func with all the pairs of [l,r] = [[0,0],[1,1],[2,2],[3,3],[4,4],[0,1],[1,2],[2,3],[3,4],[0,2],[1,3],[2,4],[0,3],[1,4],[0,4]], Winston got the following results [9,12,3,7,15,8,0,3,7,0,0,3,0,0,0]. The value closest to 5 is 7 and 3, thus the minimum difference is 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1000000,1000000,1000000], target = 1
<strong>Output:</strong> 999999
<strong>Explanation:</strong> Winston called the func with all possible values of [l,r] and he always got 1000000, thus the min difference is 999999.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,4,8,16], target = 0
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= arr[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>0 &lt;= target &lt;= 10<sup>7</sup></code></li>
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
