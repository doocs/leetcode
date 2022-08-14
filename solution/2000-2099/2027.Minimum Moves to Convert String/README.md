# [2027. 转换字符串的最少操作次数](https://leetcode.cn/problems/minimum-moves-to-convert-string)

[English Version](/solution/2000-2099/2027.Minimum%20Moves%20to%20Convert%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> ，由 <code>n</code> 个字符组成，每个字符不是 <code>'X'</code> 就是 <code>'O'</code> 。</p>

<p>一次<strong> 操作</strong> 定义为从 <code>s</code> 中选出 <strong>三个连续字符 </strong>并将选中的每个字符都转换为 <code>'O'</code> 。注意，如果字符已经是 <code>'O'</code> ，只需要保持 <strong>不变</strong> 。</p>

<p>返回将 <code>s</code> 中所有字符均转换为 <code>'O'</code> 需要执行的&nbsp;<strong>最少</strong>&nbsp;操作次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "XXX"
<strong>输出：</strong>1
<strong>解释：<em>XXX</em></strong> -&gt; OOO
一次操作，选中全部 3 个字符，并将它们转换为 <code>'O' 。</code>
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "XXOX"
<strong>输出：</strong>2
<strong>解释：<em>XXO</em></strong>X -&gt; O<em><strong>OOX</strong></em> -&gt; OOOO
第一次操作，选择前 3 个字符，并将这些字符转换为 <code>'O'</code> 。
然后，选中后 3 个字符，并执行转换。最终得到的字符串全由字符 <code>'O'</code> 组成。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "OOOO"
<strong>输出：</strong>0
<strong>解释：</strong>s 中不存在需要转换的 <code>'X' 。</code>
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s[i]</code> 为 <code>'X'</code> 或 <code>'O'</code></li>
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
