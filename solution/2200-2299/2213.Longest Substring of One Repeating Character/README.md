# [2213. 由单个字符重复的最长子字符串](https://leetcode-cn.com/problems/longest-substring-of-one-repeating-character)

[English Version](/solution/2200-2299/2213.Longest%20Substring%20of%20One%20Repeating%20Character/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的字符串 <code>s</code> 。另给你一个下标从 <strong>0</strong> 开始、长度为 <code>k</code> 的字符串 <code>queryCharacters</code> ，一个下标从 <code>0</code> 开始、长度也是 <code>k</code> 的整数 <strong>下标</strong> 数组&nbsp;<code>queryIndices</code> ，这两个都用来描述 <code>k</code> 个查询。</p>

<p>第 <code>i</code> 个查询会将 <code>s</code> 中位于下标 <code>queryIndices[i]</code> 的字符更新为 <code>queryCharacters[i]</code> 。</p>

<p>返回一个长度为 <code>k</code> 的数组 <code>lengths</code> ，其中 <code>lengths[i]</code> 是在执行第 <code>i</code> 个查询 <strong>之后</strong> <code>s</code> 中仅由 <strong>单个字符重复</strong> 组成的 <strong>最长子字符串</strong> 的 <strong>长度</strong> <em>。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "babacc", queryCharacters = "bcb", queryIndices = [1,3,3]
<strong>输出：</strong>[3,3,4]
<strong>解释：</strong>
- 第 1 次查询更新后 s = "<em>b<strong>b</strong>b</em>acc" 。由单个字符重复组成的最长子字符串是 "bbb" ，长度为 3 。
- 第 2 次查询更新后 s = "bbb<em><strong>c</strong>cc</em>" 。由单个字符重复组成的最长子字符串是 "bbb" 或 "ccc"，长度为 3 。
- 第 3 次查询更新后 s = "<em>bbb<strong>b</strong></em>cc" 。由单个字符重复组成的最长子字符串是 "bbbb" ，长度为 4 。
因此，返回 [3,3,4] 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abyzz", queryCharacters = "aa", queryIndices = [2,1]
<strong>输出：</strong>[2,3]
<strong>解释：</strong>
- 第 1 次查询更新后 s = "ab<strong>a</strong><em>zz</em>" 。由单个字符重复组成的最长子字符串是 "zz" ，长度为 2 。
- 第 2 次查询更新后 s = "<em>a<strong>a</strong>a</em>zz" 。由单个字符重复组成的最长子字符串是 "aaa" ，长度为 3 。
因此，返回 [2,3] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 由小写英文字母组成</li>
	<li><code>k == queryCharacters.length == queryIndices.length</code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
	<li><code>queryCharacters</code> 由小写英文字母组成</li>
	<li><code>0 &lt;= queryIndices[i] &lt; s.length</code></li>
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
