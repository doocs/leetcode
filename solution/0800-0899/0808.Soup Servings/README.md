# [808. 分汤](https://leetcode-cn.com/problems/soup-servings)

[English Version](/solution/0800-0899/0808.Soup%20Servings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有&nbsp;A&nbsp;和&nbsp;B 两种类型的汤。一开始每种类型的汤有&nbsp;<code>N</code>&nbsp;毫升。有四种分配操作：</p>

<ol>
	<li>提供 100ml 的汤A 和 0ml 的汤B。</li>
	<li>提供 75ml 的汤A 和 25ml 的汤B。</li>
	<li>提供 50ml 的汤A 和 50ml 的汤B。</li>
	<li>提供 25ml 的汤A 和 75ml 的汤B。</li>
</ol>

<p>当我们把汤分配给某人之后，汤就没有了。每个回合，我们将从四种概率同为0.25的操作中进行分配选择。如果汤的剩余量不足以完成某次操作，我们将尽可能分配。当两种类型的汤都分配完时，停止操作。</p>

<p>注意不存在先分配100 ml汤B的操作。</p>

<p>需要返回的值：&nbsp;汤A先分配完的概率 + 汤A和汤B同时分配完的概率 / 2。</p>

<pre>
<strong>示例:</strong>
<strong>输入:</strong> N = 50
<strong>输出:</strong> 0.625
<strong>解释:
</strong>如果我们选择前两个操作<strong>，</strong>A将首先变为空。对于第三个操作，A和B会同时变为空。对于第四个操作，B将首先变为空。<strong>
</strong>所以A变为空的总概率加上A和B同时变为空的概率的一半是 0.25 *(1 + 1 + 0.5 + 0)= 0.625。
</pre>

<p><strong>注释: </strong></p>

<ul>
	<li><code>0 &lt;= N &lt;= 10^9</code>。</li>
	<li>
	<p>返回值在&nbsp;<code>10^-6</code>&nbsp;的范围将被认为是正确的。</p>
	</li>
</ul>

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
