# [2977. 转换字符串的最小成本 II](https://leetcode.cn/problems/minimum-cost-to-convert-string-ii)

[English Version](/solution/2900-2999/2977.Minimum%20Cost%20to%20Convert%20String%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个下标从 <strong>0</strong> 开始的字符串 <code>source</code> 和 <code>target</code> ，它们的长度均为 <code>n</code> 并且由 <strong>小写 </strong>英文字母组成。</p>

<p>另给你两个下标从 <strong>0</strong> 开始的字符串数组 <code>original</code> 和 <code>changed</code> ，以及一个整数数组 <code>cost</code> ，其中 <code>cost[i]</code> 代表将字符串 <code>original[i]</code> 更改为字符串 <code>changed[i]</code> 的成本。</p>

<p>你从字符串 <code>source</code> 开始。在一次操作中，<strong>如果 </strong>存在 <strong>任意</strong> 下标 <code>j</code> 满足 <code>cost[j] == z</code>&nbsp; 、<code>original[j] == x</code> 以及 <code>changed[j] == y</code> ，你就可以选择字符串中的 <strong>子串</strong> <code>x</code> 并以 <code>z</code> 的成本将其更改为 <code>y</code> 。 你可以执行 <strong>任意数量 </strong>的操作，但是任两次操作必须满足<strong> 以下两个 </strong>条件 <strong>之一</strong> ：</p>

<ul>
	<li>在两次操作中选择的子串分别是 <code>source[a..b]</code> 和 <code>source[c..d]</code> ，满足 <code>b &lt; c</code>&nbsp; <strong>或</strong> <code>d &lt; a</code> 。换句话说，两次操作中选择的下标<strong> 不相交 </strong>。</li>
	<li>在两次操作中选择的子串分别是 <code>source[a..b]</code> 和 <code>source[c..d]</code> ，满足 <code>a == c</code> <strong>且</strong> <code>b == d</code> 。换句话说，两次操作中选择的下标<strong> 相同 </strong>。</li>
</ul>

<p>返回将字符串 <code>source</code> 转换为字符串 <code>target</code> 所需的<strong> 最小 </strong>成本。如果不可能完成转换，则返回 <code>-1</code> 。</p>

<p><strong>注意</strong>，可能存在下标 <code>i</code> 、<code>j</code> 使得 <code>original[j] == original[i]</code> 且 <code>changed[j] == changed[i]</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>source = "abcd", target = "acbe", original = ["a","b","c","c","e","d"], changed = ["b","c","b","e","b","e"], cost = [2,5,5,1,2,20]
<strong>输出：</strong>28
<strong>解释：</strong>将 "abcd" 转换为 "acbe"，执行以下操作：
- 将子串 source[1..1] 从 "b" 改为 "c" ，成本为 5 。
- 将子串 source[2..2] 从 "c" 改为 "e" ，成本为 1 。
- 将子串 source[2..2] 从 "e" 改为 "b" ，成本为 2 。
- 将子串 source[3..3] 从 "d" 改为 "e" ，成本为 20 。
产生的总成本是 5 + 1 + 2 + 20 = 28 。 
可以证明这是可能的最小成本。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>source = "abcdefgh", target = "acdeeghh", original = ["bcd","fgh","thh"], changed = ["cde","thh","ghh"], cost = [1,3,5]
<strong>输出：</strong>9
<strong>解释：</strong>将 "abcdefgh" 转换为 "acdeeghh"，执行以下操作：
- 将子串 source[1..3] 从 "bcd" 改为 "cde" ，成本为 1 。
- 将子串 source[5..7] 从 "fgh" 改为 "thh" ，成本为 3 。可以执行此操作，因为下标 [5,7] 与第一次操作选中的下标不相交。
- 将子串 source[5..7] 从 "thh" 改为 "ghh" ，成本为 5 。可以执行此操作，因为下标 [5,7] 与第一次操作选中的下标不相交，且与第二次操作选中的下标相同。
产生的总成本是 1 + 3 + 5 = 9 。
可以证明这是可能的最小成本。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>source = "abcdefgh", target = "addddddd", original = ["bcd","defgh"], changed = ["ddd","ddddd"], cost = [100,1578]
<strong>输出：</strong>-1
<strong>解释：</strong>无法将 "abcdefgh" 转换为 "addddddd" 。
如果选择子串 source[1..3] 执行第一次操作，以将 "abcdefgh" 改为 "adddefgh" ，你无法选择子串 source[3..7] 执行第二次操作，因为两次操作有一个共用下标 3 。
如果选择子串 source[3..7] 执行第一次操作，以将 "abcdefgh" 改为 "abcddddd" ，你无法选择子串 source[1..3] 执行第二次操作，因为两次操作有一个共用下标 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= source.length == target.length &lt;= 1000</code></li>
	<li><code>source</code>、<code>target</code> 均由小写英文字母组成</li>
	<li><code>1 &lt;= cost.length == original.length == changed.length &lt;= 100</code></li>
	<li><code>1 &lt;= original[i].length == changed[i].length &lt;= source.length</code></li>
	<li><code>original[i]</code>、<code>changed[i]</code> 均由小写英文字母组成</li>
	<li><code>original[i] != changed[i]</code></li>
	<li><code>1 &lt;= cost[i] &lt;= 10<sup>6</sup></code></li>
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

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
