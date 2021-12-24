# [1388. Pizza With 3n Slices](https://leetcode.com/problems/pizza-with-3n-slices)

[中文文档](/solution/1300-1399/1388.Pizza%20With%203n%20Slices/README.md)

## Description

<p>There is a pizza with 3n slices of varying size, you and your friends will take slices of pizza as follows:</p>

<ul>
	<li>You will pick <strong>any</strong> pizza slice.</li>
	<li>Your friend Alice&nbsp;will pick&nbsp;next slice in anti clockwise direction of your pick.&nbsp;</li>
	<li>Your friend Bob&nbsp;will&nbsp;pick&nbsp;next slice in clockwise direction of your pick.</li>
	<li>Repeat&nbsp;until&nbsp;there are no more slices of pizzas.</li>
</ul>

<p>Sizes of Pizza slices is represented by circular array <code>slices</code> in clockwise direction.</p>

<p>Return the maximum possible sum of slice sizes which you can have.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1388.Pizza%20With%203n%20Slices/images/sample_3_1723.png" style="width: 475px; height: 240px;" /></p>

<pre>
<strong>Input:</strong> slices = [1,2,3,4,5,6]
<strong>Output:</strong> 10
<strong>Explanation:</strong> Pick pizza slice of size 4, Alice and Bob will pick slices with size 3 and 5 respectively. Then Pick slices with size 6, finally Alice and Bob will pick slice of size 2 and 1 respectively. Total = 4 + 6.
</pre>

<p><strong>Example 2:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1388.Pizza%20With%203n%20Slices/images/sample_4_1723.png" style="width: 475px; height: 250px;" /></strong></p>

<pre>
<strong>Input:</strong> slices = [8,9,8,6,1,1]
<strong>Output:</strong> 16
<strong>Output:</strong> Pick pizza slice of size 8 in each turn. If you pick slice with size 9 your partners will pick slices of size 8.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> slices = [4,1,2,5,8,3,1,9,7]
<strong>Output:</strong> 21
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> slices = [3,1,2]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= slices.length &lt;= 500</code></li>
	<li><code>slices.length % 3 == 0</code></li>
	<li><code>1 &lt;= slices[i] &lt;= 1000</code></li>
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
