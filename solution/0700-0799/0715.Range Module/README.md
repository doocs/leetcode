# [715. Range 模块](https://leetcode-cn.com/problems/range-module)

[English Version](/solution/0700-0799/0715.Range%20Module/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Range 模块是跟踪数字范围的模块。你的任务是以一种有效的方式设计和实现以下接口。</p>

<ul>
	<li><code>addRange(int left, int right)</code> 添加半开区间&nbsp;<code>[left, right)</code>，跟踪该区间中的每个实数。添加与当前跟踪的数字部分重叠的区间时，应当添加在区间&nbsp;<code>[left, right)</code>&nbsp;中尚未跟踪的任何数字到该区间中。</li>
	<li><code>queryRange(int left, int right)</code>&nbsp;只有在当前正在跟踪区间&nbsp;<code>[left, right)</code>&nbsp;中的每一个实数时，才返回 true。</li>
	<li><code>removeRange(int left, int right)</code>&nbsp;停止跟踪区间&nbsp;<code>[left, right)</code>&nbsp;中当前正在跟踪的每个实数。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>addRange(10, 20)</strong>: null
<strong>removeRange(14, 16)</strong>: null
<strong>queryRange(10, 14)</strong>: true （区间 [10, 14) 中的每个数都正在被跟踪）
<strong>queryRange(13, 15)</strong>: false （未跟踪区间 [13, 15) 中像 14, 14.03, 14.17 这样的数字）
<strong>queryRange(16, 17)</strong>: true （尽管执行了删除操作，区间 [16, 17) 中的数字 16 仍然会被跟踪）
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>半开区间&nbsp;<code>[left, right)</code>&nbsp;表示所有满足&nbsp;<code>left &lt;= x &lt; right</code>&nbsp;的实数。</li>
	<li>对&nbsp;<code>addRange, queryRange, removeRange</code>&nbsp;的所有调用中&nbsp;<code>0 &lt; left &lt; right &lt; 10^9</code>。</li>
	<li>在单个测试用例中，对&nbsp;<code>addRange</code>&nbsp;的调用总数不超过&nbsp;<code>1000</code>&nbsp;次。</li>
	<li>在单个测试用例中，对&nbsp; <code>queryRange</code> 的调用总数不超过 <code>5000</code> 次。</li>
	<li>在单个测试用例中，对 <code>removeRange</code> 的调用总数不超过&nbsp;<code>1000</code>&nbsp;次。</li>
</ul>

<p>&nbsp;</p>

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
