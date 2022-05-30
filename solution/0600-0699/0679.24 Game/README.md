# [679. 24 点游戏](https://leetcode.cn/problems/24-game)

[English Version](/solution/0600-0699/0679.24%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个长度为4的整数数组&nbsp;<code>cards</code>&nbsp;。你有 <code>4</code> 张卡片，每张卡片上都包含一个范围在 <code>[1,9]</code> 的数字。您应该使用运算符&nbsp;<code>['+', '-', '*', '/']</code>&nbsp;和括号&nbsp;<code>'('</code>&nbsp;和&nbsp;<code>')'</code>&nbsp;将这些卡片上的数字排列成数学表达式，以获得值24。</p>

<p>你须遵守以下规则:</p>

<ul>
	<li>除法运算符 <code>'/'</code> 表示实数除法，而不是整数除法。
    <ul>
    	<li>例如，&nbsp;<code>4 /(1 - 2 / 3)= 4 /(1 / 3)= 12</code>&nbsp;。</li>
    </ul>
    </li>
    <li>每个运算都在两个数字之间。特别是，不能使用 <code>“-”</code> 作为一元运算符。
    <ul>
    	<li>例如，如果 <code>cards =[1,1,1,1]</code> ，则表达式 <code>“-1 -1 -1 -1”</code> 是 <strong>不允许</strong> 的。</li>
    </ul>
    </li>
    <li>你不能把数字串在一起
    <ul>
    	<li>例如，如果 <code>cards =[1,2,1,2]</code> ，则表达式 <code>“12 + 12”</code> 无效。</li>
    </ul>
    </li>
</ul>

<p>如果可以得到这样的表达式，其计算结果为 <code>24</code> ，则返回 <code>true </code>，否则返回 <code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> cards = [4, 1, 8, 7]
<strong>输出:</strong> true
<strong>解释:</strong> (8-4) * (7-1) = 24
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> cards = [1, 2, 1, 2]
<strong>输出:</strong> false
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>cards.length == 4</code></li>
	<li><code>1 &lt;= cards[i] &lt;= 9</code></li>
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
