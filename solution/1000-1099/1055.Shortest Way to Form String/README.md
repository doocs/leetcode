# [1055. 形成字符串的最短路径](https://leetcode-cn.com/problems/shortest-way-to-form-string)

[English Version](/solution/1000-1099/1055.Shortest%20Way%20to%20Form%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>对于任何字符串，我们可以通过删除其中一些字符（也可能不删除）来构造该字符串的子序列。</p>

<p>给定源字符串 <code>source</code> 和目标字符串 <code>target</code>，找出源字符串中能通过串联形成目标字符串的子序列的最小数量。如果无法通过串联源字符串中的子序列来构造目标字符串，则返回 <code>-1</code>。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>source = "abc", target = "abcbc"
<strong>输出：</strong>2
<strong>解释：</strong>目标字符串 "abcbc" 可以由 "abc" 和 "bc" 形成，它们都是源字符串 "abc" 的子序列。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>source = "abc", target = "acdbc"
<strong>输出：</strong>-1
<strong>解释：</strong>由于目标字符串中包含字符 "d"，所以无法由源字符串的子序列构建目标字符串。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>source = "xyz", target = "xzyxz"
<strong>输出：</strong>3
<strong>解释：</strong>目标字符串可以按如下方式构建： "xz" + "y" + "xz"。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>source</code> 和 <code>target</code> 两个字符串都只包含 "a"-"z" 的英文小写字母。</li>
	<li><code>source</code> 和 <code>target</code> 两个字符串的长度介于 <code>1</code> 和 <code>1000</code> 之间。</li>
</ol>

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
