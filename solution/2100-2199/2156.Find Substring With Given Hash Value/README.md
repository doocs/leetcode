# [2156. 查找给定哈希值的子串](https://leetcode-cn.com/problems/find-substring-with-given-hash-value)

[English Version](/solution/2100-2199/2156.Find%20Substring%20With%20Given%20Hash%20Value/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定整数 <code>p</code>&nbsp;和 <code>m</code>&nbsp;，一个长度为 <code>k</code>&nbsp;且下标从 <strong>0</strong>&nbsp;开始的字符串&nbsp;<code>s</code>&nbsp;的哈希值按照如下函数计算：</p>

<ul>
	<li><code>hash(s, p, m) = (val(s[0]) * p<sup>0</sup> + val(s[1]) * p<sup>1</sup> + ... + val(s[k-1]) * p<sup>k-1</sup>) mod m</code>.</li>
</ul>

<p>其中&nbsp;<code>val(s[i])</code>&nbsp;表示&nbsp;<code>s[i]</code>&nbsp;在字母表中的下标，从&nbsp;<code>val('a') = 1</code> 到&nbsp;<code>val('z') = 26</code>&nbsp;。</p>

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;和整数&nbsp;<code>power</code>，<code>modulo</code>，<code>k</code>&nbsp;和&nbsp;<code>hashValue</code>&nbsp;。请你返回 <code>s</code>&nbsp;中 <strong>第一个</strong> 长度为 <code>k</code>&nbsp;的 <strong>子串</strong>&nbsp;<code>sub</code>&nbsp;，满足<em>&nbsp;</em><code>hash(sub, power, modulo) == hashValue</code>&nbsp;。</p>

<p>测试数据保证一定 <strong>存在</strong>&nbsp;至少一个这样的子串。</p>

<p><strong>子串</strong> 定义为一个字符串中连续非空字符组成的序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>s = "leetcode", power = 7, modulo = 20, k = 2, hashValue = 0
<strong>输出：</strong>"ee"
<strong>解释：</strong>"ee" 的哈希值为 hash("ee", 7, 20) = (5 * 1 + 5 * 7) mod 20 = 40 mod 20 = 0 。
"ee" 是长度为 2 的第一个哈希值为 0 的子串，所以我们返回 "ee" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>s = "fbxzaad", power = 31, modulo = 100, k = 3, hashValue = 32
<b>输出：</b>"fbx"
<b>解释：</b>"fbx" 的哈希值为 hash("fbx", 31, 100) = (6 * 1 + 2 * 31 + 24 * 31<sup>2</sup>) mod 100 = 23132 mod 100 = 32 。
"bxz" 的哈希值为 hash("bxz", 31, 100) = (2 * 1 + 24 * 31 + 26 * 31<sup>2</sup>) mod 100 = 25732 mod 100 = 32 。
"fbx" 是长度为 3 的第一个哈希值为 32 的子串，所以我们返回 "fbx" 。
注意，"bxz" 的哈希值也为 32 ，但是它在字符串中比 "fbx" 更晚出现。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= s.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= power, modulo &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= hashValue &lt; modulo</code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
	<li>测试数据保证一定 <strong>存在</strong>&nbsp;满足条件的子串。</li>
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
