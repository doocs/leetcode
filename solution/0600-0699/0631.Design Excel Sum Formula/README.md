# [631. 设计 Excel 求和公式](https://leetcode-cn.com/problems/design-excel-sum-formula)

[English Version](/solution/0600-0699/0631.Design%20Excel%20Sum%20Formula/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你的任务是实现 Excel 的求和功能，具体的操作如下：</p>

<p><code>Excel(int H, char W):</code>&nbsp;这是一个构造函数，输入表明了 Excel 的高度和宽度。H 是一个正整数，范围从 1 到 26，代表高度。W 是一个字符，范围从 &#39;A&#39; 到 &#39;Z&#39;，宽度等于从 &#39;A&#39; 到 W 的字母个数。Excel 表格是一个高度 * 宽度的二维整数数组，数组中元素初始化为 0。第一行下标从 1 开始，第一列下标从 &#39;A&#39; 开始。</p>

<p>&nbsp;</p>

<p><code>void Set(int row, char column, int val):</code>&nbsp;设置&nbsp;<code>C(row, column)</code> 中的值为 val。</p>

<p>&nbsp;</p>

<p><code>int Get(int row, char column):</code> 返回&nbsp;<code>C(row, column)</code>&nbsp;中的值。</p>

<p>&nbsp;</p>

<p><code>int Sum(int row, char column, List of Strings : numbers):</code> 这个函数会将计算的结果放入 <code>C(row, column)</code>&nbsp;中，计算的结果等于在 <code>numbers</code> 中代表的所有元素之和，这个函数同时也会将这个结果返回。求和公式会一直计算更新结果直到这个公式被其他的值或者公式覆盖。</p>

<p><code>numbers</code>&nbsp;是若干字符串的集合，每个字符串代表单个位置或一个区间。如果这个字符串表示单个位置，它的格式如下：<code>ColRow</code>，例如 &quot;F7&quot; 表示位置&nbsp;(7, F) 。如果这个字符串表示一个区间，它的格式如下：<code>ColRow1:ColRow2</code>。区间就是左上角为 ColRow1 右下角为 ColRow2 的长方形。</p>

<p>&nbsp;</p>

<p><strong>样例 1 ：</strong></p>

<p>&nbsp;</p>

<pre>Excel(3,&quot;C&quot;); 
// 构造一个 3*3 的二维数组，初始化全是 0。
//   A B C
// 1 0 0 0
// 2 0 0 0
// 3 0 0 0

Set(1, &quot;A&quot;, 2);
// 设置 C(1,&quot;A&quot;) 为 2。
//   A B C
// 1 2 0 0
// 2 0 0 0
// 3 0 0 0

Sum(3, &quot;C&quot;, [&quot;A1&quot;, &quot;A1:B2&quot;]);
// 将 C(3,&quot;C&quot;) 的值设为 C(1,&quot;A&quot;) 单点，左上角为 C(1,&quot;A&quot;) 右下角为 C(2,&quot;B&quot;) 的长方形，所有元素之和。返回值 4。 
//   A B C
// 1 2 0 0
// 2 0 0 0
// 3 0 0 4

Set(2, &quot;B&quot;, 2);
// 将 C(2,&quot;B&quot;) 设为 2。 注意 C(3, &quot;C&quot;) 的值也同时改变。
//   A B C
// 1 2 0 0
// 2 0 2 0
// 3 0 0 6
</pre>

<p>&nbsp;</p>

<p><strong>注释 ：</strong></p>

<ol>
	<li>你可以认为不会出现循环求和的定义，比如说：&nbsp;A1 = sum(B1) ，B1 = sum(A1)。</li>
	<li>测试数据中，字母表示用双引号。</li>
	<li>请记住<strong>清零</strong>&nbsp;Excel 类中的变量，因为静态变量、类变量会在多组测试数据中保存之前结果。详情请看<a href="http://leetcode.com/faq/#different-output" target="_blank">这里</a>。</li>
</ol>

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
