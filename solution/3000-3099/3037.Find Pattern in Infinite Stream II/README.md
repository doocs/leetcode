# [3037. 在无限流中寻找模式 II](https://leetcode.cn/problems/find-pattern-in-infinite-stream-ii)

[English Version](/solution/3000-3099/3037.Find%20Pattern%20in%20Infinite%20Stream%20II/README_EN.md)

<!-- tags:数组,字符串匹配,滑动窗口,哈希函数,滚动哈希 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二进制数组&nbsp;<code>pattern</code>&nbsp;和一个类 <code>InfiniteStream</code>&nbsp;的对象&nbsp;<code>stream</code>&nbsp;表示一个下标从 <strong>0</strong>&nbsp;开始的二进制位无限流。</p>

<p>类&nbsp;<code>InfiniteStream</code>&nbsp;包含下列函数：</p>

<ul>
	<li><code>int next()</code>：从流中读取 <strong>一个</strong>&nbsp;二进制位&nbsp;（是&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code>）并返回。</li>
</ul>

<p>返回<em>&nbsp;<strong>第一个使得模式匹配流中读取的二进制位的 </strong>开始下标</em>。例如，如果模式是&nbsp;<code>[1, 0]</code>，第一个匹配是流中的高亮部分&nbsp;<code>[0, <strong><u>1, 0</u></strong>, 1, ...]</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> stream = [1,1,1,0,1,1,1,...], pattern = [0,1]
<strong>输出:</strong> 3
<strong>解释:</strong> 模式 [0,1] 的第一次出现在流中高亮 [1,1,1,<strong><u>0,1</u></strong>,...]，从下标 3 开始。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入:</strong> stream = [0,0,0,0,...], pattern = [0]
<strong>输出:</strong> 0
<strong>解释:</strong> 模式 [0] 的第一次出现在流中高亮 [<strong><u>0</u></strong>,...]，从下标 0 开始。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入:</strong> stream = [1,0,1,1,0,1,1,0,1,...], pattern = [1,1,0,1]
<strong>输出:</strong> 2
<strong>解释:</strong> 模式 [1,1,0,1] 的第一次出现在流中高亮 [1,0,<strong><u>1,1,0,1</u></strong>,...]，从下标 2 开始。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= pattern.length &lt;= 10<sup>4</sup></code></li>
	<li><code>pattern</code>&nbsp;只包含&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code>。</li>
	<li><code>stream</code> 只包含&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code>。</li>
	<li>生成的输入使模式的开始下标在流的前&nbsp;<code>10<sup>5</sup></code>&nbsp;个二进制位中。</li>
</ul>

<p>&nbsp;</p>

## 解法

### 方法一

<!-- tabs:start -->

```python

```

```java

```

```cpp

```

```go

```

<!-- tabs:end -->

<!-- end -->
