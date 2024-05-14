---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0631.Design%20Excel%20Sum%20Formula/README.md
tags:
    - 图
    - 设计
    - 拓扑排序
    - 数组
    - 矩阵
---

# [631. 设计 Excel 求和公式 🔒](https://leetcode.cn/problems/design-excel-sum-formula)

[English Version](/solution/0600-0699/0631.Design%20Excel%20Sum%20Formula/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你设计 <strong>Excel</strong> 中的基本功能，并实现求和公式。</p>

<p>实现 <code>Excel</code> 类：</p>

<ul>
	<li><code>Excel(int height, char width)</code>：用高度&nbsp;<code>height</code> 和宽度&nbsp;<code>width</code> 初始化对象。该表格是一个大小为 <code>height x width</code> 的整数矩阵 <code>mat</code>，其中行下标范围是 <code>[1, height]</code> ，列下标范围是 <code>['A', width]</code> 。初始情况下，所有的值都应该为 <strong>零</strong> 。</li>
	<li><code>void set(int row, char column, int val)</code>：将 <code>mat[row][column]</code> 的值更改为 <code>val</code> 。</li>
	<li><code>int get(int row, char column)</code>：返回 <code>mat[row][column]</code> 的值。</li>
	<li><code>int sum(int row, char column, List&lt;String&gt; numbers)</code>：将 <code>mat[row][column]</code> 的值设为由 <code>numbers</code> 表示的单元格的和，并返回 <code>mat[row][column]</code> 的值。此求和公式应该 <strong>长期作用于</strong> 该单元格，直到该单元格被另一个值或另一个求和公式覆盖。其中，<code>numbers[i]</code> 的格式可以为：
	<ul>
		<li><code>"ColRow"</code>：表示某个单元格。
		<ul>
			<li>例如，<code>"F7"</code> 表示单元格 <code>mat[7]['F']</code> 。</li>
		</ul>
		</li>
		<li><code>"ColRow1:ColRow2"</code>：表示一组单元格。该范围将始终为一个矩形，其中 <code>"ColRow1"</code> 表示左上角单元格的位置，<code>"ColRow2"</code> 表示右下角单元格的位置。
		<ul>
			<li>例如，<code>"B3:F7"</code> 表示 <code>3 &lt;= i &lt;= 7</code> 和 <code>'B' &lt;= j &lt;= 'F'</code> 的单元格 <code>mat[i][j]</code> 。</li>
		</ul>
		</li>
	</ul>
	</li>
</ul>

<p><strong>注意：</strong>可以假设不会出现循环求和引用。</p>

<ul>
	<li>例如，<code>mat[1]['A'] == sum(1, "B")</code>，且 <code>mat[1]['B'] == sum(1, "A")</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：
</strong>["Excel", "set", "sum", "set", "get"]
[[3, "C"], [1, "A", 2], [3, "C", ["A1", "A1:B2"]], [2, "B", 2], [3, "C"]]
<strong>输出：</strong>
[null, null, 4, null, 6]

<strong>解释：</strong>
执行以下操作：
Excel excel = new Excel(3, "C");
 // 构造一个 3 * 3 的二维数组，所有值初始化为零。
 //   A B C
 // 1 0 0 0
 // 2 0 0 0
 // 3 0 0 0
excel.set(1, "A", 2);
 // 将 mat[1]["A"] 设置为 2 。
 //   A B C
 // 1 2 0 0
 // 2 0 0 0
 // 3 0 0 0
excel.sum(3, "C", ["A1", "A1:B2"]); // 返回 4
 // 将 mat[3]["C"] 设置为 mat[1]["A"] 的值与矩形范围的单元格和的和，该范围的左上角单元格位置为 mat[1]["A"] ，右下角单元格位置为 mat[2]["B"] 。
 //   A B C
 // 1 2 0 0
 // 2 0 0 0
 // 3 0 0 4
excel.set(2, "B", 2);
 // 将 mat[2]["B"] 设置为 2 。注意 mat[3]["C"] 也应该更改。
 //   A B C
 // 1 2 0 0
 // 2 0 2 0
 // 3 0 0 6
excel.get(3, "C"); // 返回 6
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= height &lt;= 26</code></li>
	<li><code>'A' &lt;= width &lt;= 'Z'</code></li>
	<li><code>1 &lt;= row &lt;= height</code></li>
	<li><code>'A' &lt;= column &lt;= width</code></li>
	<li><code>-100 &lt;= val &lt;= 100</code></li>
	<li><code>1 &lt;= numbers.length &lt;= 5</code></li>
	<li><code>numbers[i]</code> 的格式为 <code>"ColRow"</code> 或 <code>"ColRow1:ColRow2"</code> 。</li>
	<li>最多会对 <code>set</code> 、<code>get</code> 和 <code>sum</code> 进行 <code>100</code> 次调用。</li>
</ul>

## 解法

<!-- end -->
