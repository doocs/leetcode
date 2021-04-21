# [170. Two Sum III - Data structure design](https://leetcode.com/problems/two-sum-iii-data-structure-design)

[中文文档](/solution/0100-0199/0170.Two%20Sum%20III%20-%20Data%20structure%20design/README.md)

## Description

<p>Design a data structure that accepts a stream of integers and checks if it has a pair of integers that sum up to a particular value.</p>

<p>Implement the <code>TwoSum</code> class:</p>

<ul>
	<li><code>TwoSum()</code> Initializes the <code>TwoSum</code> object, with an empty array initially.</li>
	<li><code>void add(int number)</code> Adds <code>number</code> to the data structure.</li>
	<li><code>boolean find(int value)</code> Returns <code>true</code> if there exists any pair of numbers whose sum is equal to <code>value</code>, otherwise, it returns <code>false</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;TwoSum&quot;, &quot;add&quot;, &quot;add&quot;, &quot;add&quot;, &quot;find&quot;, &quot;find&quot;]
[[], [1], [3], [5], [4], [7]]
<strong>Output</strong>
[null, null, null, null, true, false]

<strong>Explanation</strong>
TwoSum twoSum = new TwoSum();
twoSum.add(1);   // [] --&gt; [1]
twoSum.add(3);   // [1] --&gt; [1,3]
twoSum.add(5);   // [1,3] --&gt; [1,3,5]
twoSum.find(4);  // 1 + 3 = 4, return true
twoSum.find(7);  // No two integers sum up to 7, return false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>-10<sup>5</sup> &lt;= number &lt;= 10<sup>5</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= value &lt;= 2<sup>31</sup> - 1</code></li>
	<li>At most <code>5 * 10<sup>4</sup></code> calls will be made to <code>add</code> and <code>find</code>.</li>
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
