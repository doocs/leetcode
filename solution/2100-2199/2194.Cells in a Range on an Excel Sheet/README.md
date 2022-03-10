# [2194. Excel 表中某个范围内的单元格](https://leetcode-cn.com/problems/cells-in-a-range-on-an-excel-sheet)

[English Version](/solution/2100-2199/2194.Cells%20in%20a%20Range%20on%20an%20Excel%20Sheet/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Excel 表中的一个单元格 <code>(r, c)</code> 会以字符串 <code>"&lt;col&gt;&lt;row&gt;"</code> 的形式进行表示，其中：</p>

<ul>
	<li><code>&lt;col&gt;</code> 即单元格的列号 <code>c</code> 。用英文字母表中的 <strong>字母</strong> 标识。
    <ul>
    	<li>例如，第 <code>1</code> 列用 <code>'A'</code> 表示，第 <code>2</code> 列用 <code>'B'</code> 表示，第 <code>3</code> 列用 <code>'C'</code> 表示，以此类推。</li>
    </ul>
    </li>
    <li><code>&lt;row&gt;</code> 即单元格的行号 <code>r</code> 。第 <code>r</code> 行就用 <strong>整数</strong> <code>r</code> 标识。</li>

</ul>

<p>给你一个格式为 <code>"&lt;col1&gt;&lt;row1&gt;:&lt;col2&gt;&lt;row2&gt;"</code> 的字符串 <code>s</code> ，其中 <code>&lt;col1&gt;</code> 表示 <code>c1</code> 列，<code>&lt;row1&gt;</code> 表示 <code>r1</code> 行，<code>&lt;col2&gt;</code> 表示 <code>c2</code> 列，<code>&lt;row2&gt;</code> 表示 <code>r2</code> 行，并满足 <code>r1 &lt;= r2</code> 且 <code>c1 &lt;= c2</code> 。</p>

<p>找出所有满足&nbsp;<code>r1 &lt;= x &lt;= r2</code> 且 <code>c1 &lt;= y &lt;= c2</code> 的单元格，并以列表形式返回。单元格应该按前面描述的格式用 <strong>字符串</strong> 表示，并以 <strong>非递减</strong> 顺序排列（先按列排，再按行排）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2194.Cells%20in%20a%20Range%20on%20an%20Excel%20Sheet/images/ex1drawio.png" style="width: 250px; height: 160px;" /></p>

<pre>
<strong>输入：</strong>s = "K1:L2"
<strong>输出：</strong>["K1","K2","L1","L2"]
<strong>解释：</strong>
上图显示了列表中应该出现的单元格。
红色箭头指示单元格的出现顺序。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2194.Cells%20in%20a%20Range%20on%20an%20Excel%20Sheet/images/exam2drawio.png" style="width: 500px; height: 50px;" /></p>

<pre>
<strong>输入：</strong>s = "A1:F1"
<strong>输出：</strong>["A1","B1","C1","D1","E1","F1"]
<strong>解释：</strong>
上图显示了列表中应该出现的单元格。 
红色箭头指示单元格的出现顺序。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>s.length == 5</code></li>
	<li><code>'A' &lt;= s[0] &lt;= s[3] &lt;= 'Z'</code></li>
	<li><code>'1' &lt;= s[1] &lt;= s[4] &lt;= '9'</code></li>
	<li><code>s</code> 由大写英文字母、数字、和 <code>':'</code> 组成</li>
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

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
