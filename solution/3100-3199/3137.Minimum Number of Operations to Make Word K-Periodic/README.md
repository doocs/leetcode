# [3137. K 周期字符串需要的最少操作次数](https://leetcode.cn/problems/minimum-number-of-operations-to-make-word-k-periodic)

[English Version](/solution/3100-3199/3137.Minimum%20Number%20of%20Operations%20to%20Make%20Word%20K-Periodic/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 的字符串 <code>word</code> 和一个整数 <code>k</code> ，其中 <code>k</code> 是 <code>n</code> 的因数。</p>

<p>在一次操作中，你可以选择任意两个下标 <code>i</code> 和 <code>j</code>，其中 <code>0 &lt;= i, j &lt; n</code> ，且这两个下标都可以被 <code>k</code> 整除，然后用从 <code>j</code> 开始的长度为 <code>k</code> 的子串替换从 <code>i</code> 开始的长度为 <code>k</code> 的子串。也就是说，将子串 <code>word[i..i + k - 1]</code> 替换为子串 <code>word[j..j + k - 1]</code> 。</p>

<p>返回使 <code>word</code> 成为 <strong>K 周期字符串</strong> 所需的<strong> 最少</strong> 操作次数。</p>

<p>如果存在某个长度为 <code>k</code> 的字符串 <code>s</code>，使得 <code>word</code> 可以表示为任意次数连接 <code>s</code> ，则称字符串 <code>word</code> 是 <strong>K 周期字符串</strong> 。例如，如果 <code>word == "ababab"</code>，那么 <code>word</code> 就是 <code>s = "ab"</code> 时的 2 周期字符串 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block" style="
    border-color: var(--border-tertiary);
    border-left-width: 2px;
    color: var(--text-secondary);
    font-size: .875rem;
    margin-bottom: 1rem;
    margin-top: 1rem;
    overflow: visible;
    padding-left: 1rem;
">
<p><strong>输入：</strong><span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">word = "leetcodeleet", k = 4</span></p>

<p><strong>输出：</strong><span class="example-io" style="
font-family: Menlo,sans-serif;
font-size: 0.85rem;
">1</span></p>

<p><strong>解释：</strong>可以选择 i = 4 和 j = 0 获得一个 4 周期字符串。这次操作后，word 变为 "leetleetleet" 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block" style="
    border-color: var(--border-tertiary);
    border-left-width: 2px;
    color: var(--text-secondary);
    font-size: .875rem;
    margin-bottom: 1rem;
    margin-top: 1rem;
    overflow: visible;
    padding-left: 1rem;
">
<p><strong>输入：</strong><span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">word = "leetcoleet", k = 2</span></p>

<p><strong>输出：</strong>3</p>

<p><strong>解释：</strong>可以执行以下操作获得一个 2 周期字符串。</p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" height="146" style="border-collapse:collapse; text-align: center; vertical-align: middle;">
	<tbody>
		<tr>
			<th>i</th>
			<th>j</th>
			<th>word</th>
		</tr>
		<tr>
			<td style="padding: 5px 15px;">0</td>
			<td style="padding: 5px 15px;">2</td>
			<td style="padding: 5px 15px;">etetcoleet</td>
		</tr>
		<tr>
			<td style="padding: 5px 15px;">4</td>
			<td style="padding: 5px 15px;">0</td>
			<td style="padding: 5px 15px;">etetetleet</td>
		</tr>
		<tr>
			<td style="padding: 5px 15px;">6</td>
			<td style="padding: 5px 15px;">0</td>
			<td style="padding: 5px 15px;">etetetetet</td>
		</tr>
	</tbody>
</table>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == word.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= word.length</code></li>
	<li><code>k</code> 能整除 <code>word.length</code> 。</li>
	<li><code>word</code> 仅由小写英文字母组成。</li>
</ul>

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
