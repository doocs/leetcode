# [712. 两个字符串的最小 ASCII 删除和](https://leetcode-cn.com/problems/minimum-ascii-delete-sum-for-two-strings)

[English Version](/solution/0700-0799/0712.Minimum%20ASCII%20Delete%20Sum%20for%20Two%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个字符串<code>s1, s2</code>，找到使两个字符串相等所需删除字符的ASCII值的最小和。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> s1 = &quot;sea&quot;, s2 = &quot;eat&quot;
<strong>输出:</strong> 231
<strong>解释:</strong> 在 &quot;sea&quot; 中删除 &quot;s&quot; 并将 &quot;s&quot; 的值(115)加入总和。
在 &quot;eat&quot; 中删除 &quot;t&quot; 并将 116 加入总和。
结束时，两个字符串相等，115 + 116 = 231 就是符合条件的最小和。
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> s1 = &quot;delete&quot;, s2 = &quot;leet&quot;
<strong>输出:</strong> 403
<strong>解释:</strong> 在 &quot;delete&quot; 中删除 &quot;dee&quot; 字符串变成 &quot;let&quot;，
将 100[d]+101[e]+101[e] 加入总和。在 &quot;leet&quot; 中删除 &quot;e&quot; 将 101[e] 加入总和。
结束时，两个字符串都等于 &quot;let&quot;，结果即为 100+101+101+101 = 403 。
如果改为将两个字符串转换为 &quot;lee&quot; 或 &quot;eet&quot;，我们会得到 433 或 417 的结果，比答案更大。
</pre>

<p><strong>注意:</strong></p>

<ul>
	<li><code>0 &lt; s1.length, s2.length &lt;= 1000</code>。</li>
	<li>所有字符串中的字符ASCII值在<code>[97, 122]</code>之间。</li>
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
