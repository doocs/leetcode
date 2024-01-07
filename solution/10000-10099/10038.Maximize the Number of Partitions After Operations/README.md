# [10038. 执行操作后的最大分割数量](https://leetcode.cn/problems/maximize-the-number-of-partitions-after-operations)

[English Version](/solution/10000-10099/10038.Maximize%20the%20Number%20of%20Partitions%20After%20Operations/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的字符串&nbsp;<code>s</code>&nbsp;和一个整数&nbsp;<code>k</code>。</p>

<p>你需要执行以下分割操作，直到字符串&nbsp;<code>s&nbsp;</code>变为&nbsp;<strong>空</strong>：</p>

<ul>
	<li>选择&nbsp;<code>s</code>&nbsp;的最长<strong>前缀</strong>，该前缀最多包含&nbsp;<code>k&nbsp;</code>个&nbsp;<strong>不同&nbsp;</strong>字符。</li>
	<li><strong>删除&nbsp;</strong>这个前缀，并将分割数量加一。如果有剩余字符，它们在&nbsp;<code>s</code>&nbsp;中保持原来的顺序。</li>
</ul>

<p>执行操作之 <strong>前</strong> ，你可以将&nbsp;<code>s</code>&nbsp;中&nbsp;<strong>至多一处 </strong>下标的对应字符更改为另一个小写英文字母。</p>

<p>在最优选择情形下改变至多一处下标对应字符后，用整数表示并返回操作结束时得到的最大分割数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "accca", k = 2
<strong>输出：</strong>3
<strong>解释：</strong>在此示例中，为了最大化得到的分割数量，可以将 s[2] 改为 'b'。
s 变为 "acbca"。
按照以下方式执行操作，直到 s 变为空：
- 选择最长且至多包含 2 个不同字符的前缀，"<em><strong>ac</strong></em>bca"。
- 删除该前缀，s 变为 "bca"。现在分割数量为 1。
- 选择最长且至多包含 2 个不同字符的前缀，"<em><strong>bc</strong></em>a"。
- 删除该前缀，s 变为 "a"。现在分割数量为 2。
- 选择最长且至多包含 2 个不同字符的前缀，"<strong><em>a</em></strong>"。
- 删除该前缀，s 变为空。现在分割数量为 3。
因此，答案是 3。
可以证明，分割数量不可能超过 3。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "aabaab", k = 3
<strong>输出：</strong>1
<strong>解释：</strong>在此示例中，为了最大化得到的分割数量，可以保持 s 不变。
按照以下方式执行操作，直到 s 变为空： 
- 选择最长且至多包含 3 个不同字符的前缀，"<em><strong>aabaab</strong></em>"。
- 删除该前缀，s 变为空。现在分割数量为 1。
因此，答案是 1。
可以证明，分割数量不可能超过 1。</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "xxyz", k = 1
<strong>输出：</strong>4
<strong>解释：</strong>在此示例中，为了最大化得到的分割数量，可以将 s[1] 改为 'a'。
s 变为 "xayz"。
按照以下方式执行操作，直到 s 变为空：
- 选择最长且至多包含 1 个不同字符的前缀，"<em><strong>x</strong></em>ayz"。
- 删除该前缀，s 变为 "ayz"。现在分割数量为 1。
- 选择最长且至多包含 1 个不同字符的前缀，"<em><strong>a</strong></em>yz"。
- 删除该前缀，s 变为 "yz"，现在分割数量为 2。
- 选择最长且至多包含 1 个不同字符的前缀，"<em><strong>y</strong></em>z"。
- 删除该前缀，s 变为 "z"。现在分割数量为 3。
- 选择最且至多包含 1 个不同字符的前缀，"<em>z</em>"。
- 删除该前缀，s 变为空。现在分割数量为 4。
因此，答案是 4。
可以证明，分割数量不可能超过 4。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
	<li><code>1 &lt;= k &lt;= 26</code></li>
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
