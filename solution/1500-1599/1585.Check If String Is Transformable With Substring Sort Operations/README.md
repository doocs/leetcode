# [1585. 检查字符串是否可以通过排序子字符串得到另一个字符串](https://leetcode.cn/problems/check-if-string-is-transformable-with-substring-sort-operations)

[English Version](/solution/1500-1599/1585.Check%20If%20String%20Is%20Transformable%20With%20Substring%20Sort%20Operations/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串&nbsp;<code>s</code> 和&nbsp;<code>t</code>&nbsp;，请你通过若干次以下操作将字符串&nbsp;<code>s</code>&nbsp;转化成字符串&nbsp;<code>t</code>&nbsp;：</p>

<ul>
	<li>选择 <code>s</code>&nbsp;中一个 <strong>非空</strong>&nbsp;子字符串并将它包含的字符就地 <strong>升序</strong>&nbsp;排序。</li>
</ul>

<p>比方说，对下划线所示的子字符串进行操作可以由&nbsp;<code>&quot;1<strong>4234</strong>&quot;</code>&nbsp;得到&nbsp;<code>&quot;1<strong>2344</strong>&quot;</code>&nbsp;。</p>

<p>如果可以将字符串 <code>s</code>&nbsp;变成 <code>t</code>&nbsp;，返回 <code>true</code>&nbsp;。否则，返回 <code>false</code>&nbsp;。</p>

<p>一个 <strong>子字符串</strong>&nbsp;定义为一个字符串中连续的若干字符。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;84532&quot;, t = &quot;34852&quot;
<strong>输出：</strong>true
<strong>解释：</strong>你可以按以下操作将 s 转变为 t ：
&quot;84<strong>53</strong>2&quot; （从下标 2 到下标 3）-&gt; &quot;84<strong>35</strong>2&quot;
&quot;<strong>843</strong>52&quot; （从下标 0 到下标 2） -&gt; &quot;<strong>348</strong>52&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;34521&quot;, t = &quot;23415&quot;
<strong>输出：</strong>true
<strong>解释：</strong>你可以按以下操作将 s 转变为 t ：
&quot;<strong>3452</strong>1&quot; -&gt; &quot;<strong>2345</strong>1&quot;
&quot;234<strong>51</strong>&quot; -&gt; &quot;234<strong>15</strong>&quot;
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;12345&quot;, t = &quot;12435&quot;
<strong>输出：</strong>false
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;1&quot;, t = &quot;2&quot;
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>s.length == t.length</code></li>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 和&nbsp;<code>t</code>&nbsp;都只包含数字字符，即&nbsp;<code>&#39;0&#39;</code>&nbsp;到&nbsp;<code>&#39;9&#39;</code> 。</li>
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
