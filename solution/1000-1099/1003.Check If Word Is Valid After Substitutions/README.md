# [1003. 检查替换后的词是否有效](https://leetcode.cn/problems/check-if-word-is-valid-after-substitutions)

[English Version](/solution/1000-1099/1003.Check%20If%20Word%20Is%20Valid%20After%20Substitutions/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

给你一个字符串 <code>s</code> ，请你判断它是否 <strong>有效</strong> 。

<p>字符串 <code>s</code> <strong>有效</strong> 需要满足：假设开始有一个空字符串 <code>t = ""</code> ，你可以执行 <strong>任意次</strong> 下述操作将<strong> </strong><code>t</code><strong> 转换为 </strong><code>s</code> ：</p>

<ul>
	<li>将字符串 <code>"abc"</code> 插入到 <code>t</code> 中的任意位置。形式上，<code>t</code> 变为 <code>t<sub>left</sub> + "abc" + t<sub>right</sub></code>，其中 <code>t == t<sub>left</sub> + t<sub>right</sub></code> 。注意，<code>t<sub>left</sub></code> 和 <code>t<sub>right</sub></code> 可能为 <strong>空</strong> 。</li>
</ul>

<p>如果字符串 <code>s</code> 有效，则返回 <code>true</code>；否则，返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aabcbc"
<strong>输出：</strong>true
<strong>解释：</strong>
"" -&gt; "<strong>abc</strong>" -&gt; "a<strong>abc</strong>bc"
因此，"aabcbc" 有效。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abcabcababcc"
<strong>输出：</strong>true
<strong>解释：</strong>
"" -&gt; "<strong>abc</strong>" -&gt; "abc<strong>abc</strong>" -&gt; "abcabc<strong>abc</strong>" -&gt; "abcabcab<strong>abc</strong>c"
因此，"abcabcababcc" 有效。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "abccba"
<strong>输出：</strong>false
<strong>解释：</strong>执行操作无法得到 "abccba" 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>s</code> 由字母 <code>'a'</code>、<code>'b'</code> 和 <code>'c'</code> 组成</li>
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
