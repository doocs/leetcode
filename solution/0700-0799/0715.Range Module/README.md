# [715. Range 模块](https://leetcode-cn.com/problems/range-module)

[English Version](/solution/0700-0799/0715.Range%20Module/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Range模块是跟踪数字范围的模块。设计一个数据结构来跟踪表示为 <strong>半开区间</strong> 的范围并查询它们。</p>

<p><strong>半开区间</strong>&nbsp;<code>[left, right)</code>&nbsp;表示所有&nbsp;<code>left &lt;= x &lt; right</code>&nbsp;的实数 <code>x</code> 。</p>

<p>实现 <code>RangeModule</code> 类:</p>

<ul>
	<li><code>RangeModule()</code>&nbsp;初始化数据结构的对象。</li>
	<li><code>void addRange(int left, int right)</code> 添加 <strong>半开区间</strong>&nbsp;<code>[left, right)</code>，跟踪该区间中的每个实数。添加与当前跟踪的数字部分重叠的区间时，应当添加在区间&nbsp;<code>[left, right)</code>&nbsp;中尚未跟踪的任何数字到该区间中。</li>
	<li><code>boolean queryRange(int left, int right)</code>&nbsp;只有在当前正在跟踪区间&nbsp;<code>[left, right)</code>&nbsp;中的每一个实数时，才返回 <code>true</code>&nbsp;，否则返回 <code>false</code> 。</li>
	<li><code>void removeRange(int left, int right)</code>&nbsp;停止跟踪 <strong>半开区间</strong>&nbsp;<code>[left, right)</code>&nbsp;中当前正在跟踪的每个实数。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入</strong>
["RangeModule", "addRange", "removeRange", "queryRange", "queryRange", "queryRange"]
[[], [10, 20], [14, 16], [10, 14], [13, 15], [16, 17]]
<strong>输出</strong>
[null, null, null, true, false, true]

<strong>解释</strong>
RangeModule rangeModule = new RangeModule();
rangeModule.addRange(10, 20);
rangeModule.removeRange(14, 16);
rangeModule.queryRange(10, 14); 返回 true （区间 [10, 14) 中的每个数都正在被跟踪）
rangeModule.queryRange(13, 15); 返回 false（未跟踪区间 [13, 15) 中像 14, 14.03, 14.17 这样的数字）
rangeModule.queryRange(16, 17); 返回 true （尽管执行了删除操作，区间 [16, 17) 中的数字 16 仍然会被跟踪）
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= left &lt; right &lt;= 10<sup>9</sup></code></li>
	<li>在单个测试用例中，对&nbsp;<code>addRange</code>&nbsp;、&nbsp; <code>queryRange</code>&nbsp;和 <code>removeRange</code> 的调用总数不超过&nbsp;<code>10<sup>4</sup></code>&nbsp;次</li>
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
