# [972. 相等的有理数](https://leetcode.cn/problems/equal-rational-numbers)

[English Version](/solution/0900-0999/0972.Equal%20Rational%20Numbers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个字符串&nbsp;<code>s</code>&nbsp;和&nbsp;<code>t</code>&nbsp;，每个字符串代表一个非负有理数，只有当它们表示相同的数字时才返回 <code>true</code>&nbsp;。字符串中可以使用括号来表示有理数的重复部分。</p>

<p><strong>有理数</strong>&nbsp;最多可以用三个部分来表示：<em>整数部分</em>&nbsp;<code>&lt;IntegerPart&gt;</code>、<em>小数非重复部分</em>&nbsp;<code>&lt;NonRepeatingPart&gt;</code>&nbsp;和<em>小数重复部分</em>&nbsp;<code>&lt;(&gt;&lt;RepeatingPart&gt;&lt;)&gt;</code>。数字可以用以下三种方法之一来表示：</p>

<ul>
	<li><code>&lt;IntegerPart&gt;</code>&nbsp;
    <ul>
    	<li>例：&nbsp;<code>0</code>&nbsp;,<code>12</code>&nbsp;和&nbsp;<code>123</code>&nbsp;</li>
    </ul>
    </li>
    <li><code>&lt;IntegerPart&gt;&lt;.&gt;&lt;NonRepeatingPart&gt;</code>
    <ul>
    	<li>例： <code>0.5<font color="#333333"><font face="Helvetica Neue, Helvetica, Arial, sans-serif"><span style="font-size:14px"><span style="background-color:#ffffff">&nbsp;, </span></span></font></font></code><font color="#333333"><font face="Helvetica Neue, Helvetica, Arial, sans-serif"><span style="font-size:14px"><span style="background-color:#ffffff"><code>1.</code>&nbsp;,&nbsp;</span></span></font></font><code>2.12</code>&nbsp;和&nbsp;<code>123.0001</code></li>
    </ul>
    </li>
    <li><code>&lt;IntegerPart&gt;&lt;.&gt;&lt;NonRepeatingPart&gt;&lt;(&gt;&lt;RepeatingPart&gt;&lt;)&gt;</code>&nbsp;
    <ul>
    	<li>例： <code>0.1(6)</code> ， <code>1.(9)</code>， <code>123.00(1212)</code></li>
    </ul>
    </li>
</ul>

<p>十进制展开的重复部分通常在一对圆括号内表示。例如：</p>

<ul>
	<li><code>1 / 6 = 0.16666666... = 0.1(6) = 0.1666(6) = 0.166(66)</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "0.(52)", t = "0.5(25)"
<strong>输出：</strong>true
<strong>解释：</strong>因为 "0.(52)" 代表 0.52525252...，而 "0.5(25)" 代表 0.52525252525.....，则这两个字符串表示相同的数字。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "0.1666(6)", t = "0.166(66)"
<strong>输出：</strong>true
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "0.9(9)", t = "1."
<strong>输出：</strong>true
<strong>解释：</strong>"0.9(9)" 代表 0.999999999... 永远重复，等于 1 。[<a href="https://baike.baidu.com/item/0.999…/5615429?fr=aladdin" target="_blank">有关说明，请参阅此链接</a>]
"1." 表示数字 1，其格式正确：(IntegerPart) = "1" 且 (NonRepeatingPart) = "" 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>每个部分仅由数字组成。</li>
	<li>整数部分&nbsp;<code>&lt;IntegerPart&gt;</code>&nbsp;不会以零开头。（零本身除外）</li>
	<li><code>1 &lt;= &lt;IntegerPart&gt;.length &lt;= 4 </code></li>
	<li><code>0 &lt;= &lt;NonRepeatingPart&gt;.length &lt;= 4 </code></li>
	<li><code>1 &lt;= &lt;RepeatingPart&gt;.length &lt;= 4 </code></li>
</ul>
<span style="display:block"><span style="height:0px"><span style="position:absolute">​​​​​</span></span></span>

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
