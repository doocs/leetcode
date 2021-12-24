# [800. 相似 RGB 颜色](https://leetcode-cn.com/problems/similar-rgb-color)

[English Version](/solution/0800-0899/0800.Similar%20RGB%20Color/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>RGB 颜色用十六进制来表示的话，每个大写字母都代表了某个从 <code>0</code>&nbsp;到&nbsp;<code>f</code> 的 16 进制数。</p>

<p>RGB 颜色 <code>&quot;#AABBCC&quot;</code>&nbsp;可以简写成&nbsp;<code>&quot;#ABC&quot;</code> 。例如，<code>&quot;#15c&quot;</code>&nbsp;其实是&nbsp;<code>&quot;#1155cc&quot;</code> 的简写。</p>

<p>现在，假如我们分别定义两个颜色 <code>&quot;#ABCDEF&quot;</code>&nbsp;和&nbsp;<code>&quot;#UVWXYZ&quot;</code>，则他们的相似度可以通过这个表达式&nbsp;<code>-(AB - UV)^2 -&nbsp;(CD - WX)^2 -&nbsp;(EF - YZ)^2</code>&nbsp;来计算。</p>

<p>那么给定颜色 <code>&quot;#ABCDEF&quot;</code>，请你返回一个与 <code>#ABCDEF</code> 最相似的&nbsp;7 个字符代表的颜色，并且它是可以被简写形式表达的。（比如，可以表示成类似 <code>&quot;#XYZ&quot;</code> 的形式）</p>

<pre><strong>示例 1：</strong>
<strong>输入：</strong>color = &quot;#09f166&quot;
<strong>输出：</strong>&quot;#11ee66&quot;
<strong>解释：</strong> 
因为相似度计算得出 -(0x09 - 0x11)^2 -(0xf1 - 0xee)^2 - (0x66 - 0x66)^2 = -64 -9 -0 = -73
这已经是所有可以简写的颜色中最相似的了
</pre>

<p><strong>注意：</strong></p>

<ul>
	<li><code>color</code> 是一个长度为&nbsp;<code>7</code>&nbsp;的字符串</li>
	<li><code>color</code> 是一个有效的 RGB 颜色：对于仍和&nbsp;<code>i &gt; 0</code>，<code>color[i]</code>&nbsp;都是一个在&nbsp;<code>0</code>&nbsp;到&nbsp;<code>f</code>&nbsp;范围的 16 进制数</li>
	<li>假如答案具有相同的（最大）相似度的话，都是可以被接受的</li>
	<li>所有输入、输出都必须使用小写字母，并且输出为 7 个字符</li>
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
