# [936. 戳印序列](https://leetcode.cn/problems/stamping-the-sequence)

[English Version](/solution/0900-0999/0936.Stamping%20The%20Sequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你想要用<strong>小写字母</strong>组成一个目标字符串&nbsp;<code>target</code>。&nbsp;</p>

<p>开始的时候，序列由&nbsp;<code>target.length</code>&nbsp;个&nbsp;<code>&#39;?&#39;</code>&nbsp;记号组成。而你有一个小写字母印章&nbsp;<code>stamp</code>。</p>

<p>在每个回合，你可以将印章放在序列上，并将序列中的每个字母替换为印章上的相应字母。你最多可以进行&nbsp;<code>10 * target.length</code>&nbsp; 个回合。</p>

<p>举个例子，如果初始序列为 &quot;?????&quot;，而你的印章 <code>stamp</code>&nbsp;是&nbsp;<code>&quot;abc&quot;</code>，那么在第一回合，你可以得到&nbsp;&quot;abc??&quot;、&quot;?abc?&quot;、&quot;??abc&quot;。（请注意，印章必须完全包含在序列的边界内才能盖下去。）</p>

<p>如果可以印出序列，那么返回一个数组，该数组由每个回合中被印下的最左边字母的索引组成。如果不能印出序列，就返回一个空数组。</p>

<p>例如，如果序列是 &quot;ababc&quot;，印章是 <code>&quot;abc&quot;</code>，那么我们就可以返回与操作&nbsp;&quot;?????&quot; -&gt; &quot;abc??&quot; -&gt; &quot;ababc&quot; 相对应的答案 <code>[0, 2]</code>；</p>

<p>另外，如果可以印出序列，那么需要保证可以在 <code>10 * target.length</code>&nbsp;个回合内完成。任何超过此数字的答案将不被接受。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>stamp = &quot;abc&quot;, target = &quot;ababc&quot;
<strong>输出：</strong>[0,2]
（[1,0,2] 以及其他一些可能的结果也将作为答案被接受）
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>stamp = &quot;abca&quot;, target = &quot;aabcaca&quot;
<strong>输出：</strong>[3,0,1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= stamp.length &lt;= target.length &lt;= 1000</code></li>
	<li><code>stamp</code> 和&nbsp;<code>target</code>&nbsp;只包含小写字母。</li>
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
