# [436. 寻找右区间](https://leetcode-cn.com/problems/find-right-interval)

[English Version](/solution/0400-0499/0436.Find%20Right%20Interval/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>给定一组区间，对于每一个区间 i，检查是否存在一个区间 j，它的起始点大于或等于区间&nbsp;i 的终点，这可以称为 j 在 i 的&ldquo;右侧&rdquo;。</p>

<p>对于任何区间，你需要存储的满足条件的区间&nbsp;j 的最小索引，这意味着区间 j 有最小的起始点可以使其成为&ldquo;右侧&rdquo;区间。如果区间&nbsp;j 不存在，则将区间 i 存储为 -1。最后，你需要输出一个值为存储的区间值的数组。</p>

<p><strong>注意:</strong></p>

<ol>
	<li>你可以假设区间的终点总是大于它的起始点。</li>
	<li>你可以假定这些区间都不具有相同的起始点。</li>
</ol>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> [ [1,2] ]
<strong>输出:</strong> [-1]

<strong>解释:</strong>集合中只有一个区间，所以输出-1。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> [ [3,4], [2,3], [1,2] ]
<strong>输出:</strong> [-1, 0, 1]

<strong>解释:</strong>对于[3,4]，没有满足条件的&ldquo;右侧&rdquo;区间。
对于[2,3]，区间[3,4]具有最小的&ldquo;右&rdquo;起点;
对于[1,2]，区间[2,3]具有最小的&ldquo;右&rdquo;起点。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> [ [1,4], [2,3], [3,4] ]
<strong>输出:</strong> [-1, 2, -1]

<strong>解释:对于</strong>区间[1,4]和[3,4]，没有满足条件的&ldquo;右侧&rdquo;区间。
对于[2,3]，区间[3,4]有最小的&ldquo;右&rdquo;起点。
</pre>



## 解法
<!-- 这里可写通用的实现逻辑 -->


<!-- tabs:start -->

### **Python3**
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**
```

```

<!-- tabs:end -->