---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3484.Design%20Spreadsheet/README.md
---

<!-- problem:start -->

# [3484. 设计电子表格](https://leetcode.cn/problems/design-spreadsheet)

[English Version](/solution/3400-3499/3484.Design%20Spreadsheet/README_EN.md)

## 题目描述

<!-- description:start -->

<p>电子表格是一个网格，它有 26 列（从 <code>'A'</code> 到 <code>'Z'</code>）和指定数量的 <code>rows</code>。每个单元格可以存储一个 0 到 10<sup>5</sup>&nbsp;之间的整数值。</p>

<p>请你实现一个&nbsp;<code>Spreadsheet</code> 类：</p>

<ul>
	<li><code>Spreadsheet(int rows)</code> 初始化一个具有 26 列（从 <code>'A'</code> 到 <code>'Z'</code>）和指定行数的电子表格。所有单元格最初的值都为 0 。</li>
	<li><code>void setCell(String cell, int value)</code> 设置指定单元格的值。单元格引用以 <code>"AX"</code> 的格式提供（例如，<code>"A1"</code>，<code>"B10"</code>），其中字母表示列（从 <code>'A'</code> 到 <code>'Z'</code>），数字表示从<strong>&nbsp;</strong><strong>1</strong>&nbsp;开始的行号。</li>
	<li><code>void resetCell(String cell)</code> 重置指定单元格的值为 0 。</li>
	<li><code>int getValue(String formula)</code> 计算一个公式的值，格式为 <code>"=X+Y"</code>，其中 <code>X</code> 和 <code>Y</code>&nbsp;<strong>要么</strong> 是单元格引用，要么非负整数，返回计算的和。</li>
</ul>

<p><strong>注意：</strong> 如果 <code>getValue</code> 引用一个未通过 <code>setCell</code> 明确设置的单元格，则该单元格的值默认为 0 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><br />
<span class="example-io">["Spreadsheet", "getValue", "setCell", "getValue", "setCell", "getValue", "resetCell", "getValue"]<br />
[[3], ["=5+7"], ["A1", 10], ["=A1+6"], ["B2", 15], ["=A1+B2"], ["A1"], ["=A1+B2"]]</span></p>

<p><strong>输出：</strong><br />
<span class="example-io">[null, 12, null, 16, null, 25, null, 15] </span></p>

<p><strong>解释</strong></p>
Spreadsheet spreadsheet = new Spreadsheet(3); // 初始化一个具有 3 行和 26 列的电子表格<br data-end="321" data-start="318" />
spreadsheet.getValue("=5+7"); // 返回 12 (5+7)<br data-end="373" data-start="370" />
spreadsheet.setCell("A1", 10); // 设置 A1 为 10<br data-end="423" data-start="420" />
spreadsheet.getValue("=A1+6"); // 返回 16 (10+6)<br data-end="477" data-start="474" />
spreadsheet.setCell("B2", 15); // 设置 B2 为 15<br data-end="527" data-start="524" />
spreadsheet.getValue("=A1+B2"); // 返回 25 (10+15)<br data-end="583" data-start="580" />
spreadsheet.resetCell("A1"); // 重置 A1 为 0<br data-end="634" data-start="631" />
spreadsheet.getValue("=A1+B2"); // 返回 15 (0+15)</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= rows &lt;= 10<sup>3</sup></code></li>
	<li><code>0 &lt;= value &lt;= 10<sup>5</sup></code></li>
	<li>公式保证采用 <code>"=X+Y"</code> 格式，其中 <code>X</code> 和 <code>Y</code> 要么是有效的单元格引用，要么是小于等于 <code>10<sup>5</sup></code> 的 <strong>非负</strong> 整数。</li>
	<li>每个单元格引用由一个大写字母 <code>'A'</code> 到 <code>'Z'</code> 和一个介于 <code>1</code> 和 <code>rows</code> 之间的行号组成。</li>
	<li><strong>总共</strong> 最多会对 <code>setCell</code>、<code>resetCell</code> 和 <code>getValue</code> 调用 <code>10<sup>4</sup></code> 次。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
