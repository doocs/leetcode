# [756. 金字塔转换矩阵](https://leetcode.cn/problems/pyramid-transition-matrix)

[English Version](/solution/0700-0799/0756.Pyramid%20Transition%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你正在把积木堆成金字塔。每个块都有一个颜色，用一个字母表示。每一行的块比它下面的行 <strong>少一个块</strong> ，并且居中。</p>

<p>为了使金字塔美观，只有特定的 <strong>三角形图案</strong> 是允许的。一个三角形的图案由&nbsp;<strong>两个块</strong>&nbsp;和叠在上面的 <strong>单个块</strong> 组成。模式是以三个字母字符串的列表形式&nbsp;<code>allowed</code>&nbsp;给出的，其中模式的前两个字符分别表示左右底部块，第三个字符表示顶部块。</p>

<ul>
	<li>例如，<code>"ABC"</code>&nbsp;表示一个三角形图案，其中一个 <code>“C”</code> 块堆叠在一个&nbsp;<code>'A'</code>&nbsp;块(左)和一个&nbsp;<code>'B'</code>&nbsp;块(右)之上。请注意，这与 <code>"BAC"</code>&nbsp;不同，<code>"B"</code>&nbsp;在左下角，<code>"A"</code>&nbsp;在右下角。</li>
</ul>

<p>你从底部的一排积木&nbsp;<code>bottom</code>&nbsp;开始，作为一个单一的字符串，你 <strong>必须</strong> 使用作为金字塔的底部。</p>

<p>在给定&nbsp;<code>bottom</code>&nbsp;和&nbsp;<code>allowed</code>&nbsp;的情况下，如果你能一直构建到金字塔顶部，使金字塔中的 <strong>每个三角形图案</strong> 都是允许的，则返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0756.Pyramid%20Transition%20Matrix/images/pyramid1-grid.jpg" style="height: 232px; width: 600px;" /></p>

<pre>
<strong>输入：</strong>bottom = "BCD", allowed = ["BCG", "CDE", "GEA", "FFF"]
<strong>输出：</strong>true
<strong>解释：</strong>允许的三角形模式显示在右边。
从最底层(第3层)开始，我们可以在第2层构建“CE”，然后在第1层构建“E”。
金字塔中有三种三角形图案，分别是“BCC”、“CDE”和“CEA”。都是允许的。
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0756.Pyramid%20Transition%20Matrix/images/pyramid2-grid.jpg" style="height: 359px; width: 600px;" /></p>

<pre>
<strong>输入：</strong>bottom = "AABA", allowed = ["AAA", "AAB", "ABA", "ABB", "BAC"]
<strong>输出：</strong>false
<strong>解释：</strong>允许的三角形模式显示在右边。
从最底层(游戏邦注:即第4个关卡)开始，创造第3个关卡有多种方法，但如果尝试所有可能性，你便会在创造第1个关卡前陷入困境。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= bottom.length &lt;= 6</code></li>
	<li><code>0 &lt;= allowed.length &lt;= 216</code></li>
	<li><code>allowed[i].length == 3</code></li>
	<li>所有输入字符串中的字母来自集合&nbsp;<code>{'A', 'B', 'C', 'D', 'E', 'F', 'G'}</code>。</li>
	<li>&nbsp;<code>allowed</code>&nbsp;中所有值都是 <strong>唯一的</strong></li>
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
