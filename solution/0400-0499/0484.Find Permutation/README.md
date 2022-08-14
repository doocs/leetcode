# [484. 寻找排列](https://leetcode.cn/problems/find-permutation)

[English Version](/solution/0400-0499/0484.Find%20Permutation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>由范围 <code>[1,n]</code> 内所有整数组成的 <code>n</code> 个整数的排列&nbsp;<code>perm</code>&nbsp;可以表示为长度为 <code>n - 1</code> 的字符串 <code>s</code> ，其中:</p>

<ul>
	<li>如果 <code>perm[i] &lt; perm[i + 1]</code> ，那么 <code>s[i] == ' i '</code></li>
	<li>如果&nbsp;<code>perm[i] &gt; perm[i + 1]</code>&nbsp;，那么 <code>s[i] == 'D'</code>&nbsp;。</li>
</ul>

<p>给定一个字符串 <code>s</code> ，重构字典序上最小的排列&nbsp;<code>perm</code>&nbsp;并返回它。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong> s = "I"
<strong>输出：</strong> [1,2]
<strong>解释：</strong> [1,2] 是唯一合法的可以生成秘密签名 "I" 的特定串，数字 1 和 2 构成递增关系。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong> s = "DI"
<strong>输出：</strong> [2,1,3]
<strong>解释：</strong> [2,1,3] 和 [3,1,2] 可以生成秘密签名 "DI"，
但是由于我们要找字典序最小的排列，因此你需要输出 [2,1,3]。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code>&nbsp;只会包含字符 <code>'D'</code> 和 <code>'I'</code>。</li>
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
