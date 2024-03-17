# [3085. 成为 K 特殊字符串需要删除的最少字符数](https://leetcode.cn/problems/minimum-deletions-to-make-string-k-special)

[English Version](/solution/3000-3099/3085.Minimum%20Deletions%20to%20Make%20String%20K-Special/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>word</code> 和一个整数 <code>k</code>。</p>

<p>如果&nbsp;<code>|freq(word[i]) - freq(word[j])| &lt;= k</code> 对于字符串中所有下标 <code>i</code> 和 <code>j</code>&nbsp; 都成立，则认为 <code>word</code> 是 <strong>k 特殊字符串</strong>。</p>

<p>此处，<code>freq(x)</code> 表示字符 <code>x</code> 在 <code>word</code> 中的<span data-keyword="frequency-letter">出现频率</span>，而 <code>|y|</code> 表示 <code>y</code> 的绝对值。</p>

<p>返回使 <code>word</code> 成为 <strong>k 特殊字符串</strong> 需要删除的字符的最小数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>输入：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">word = "aabcaba", k = 0</span></p>

<p><strong>输出：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">3</span></p>

<p><strong>解释：</strong>可以删除 <code>2</code> 个 <code>"a"</code> 和 <code>1</code> 个 <code>"c"</code> 使 <code>word</code> 成为 <code>0</code> 特殊字符串。<code>word</code> 变为 <code>"baba"</code>，此时 <code>freq('a') == freq('b') == 2</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>输入：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">word = "dabdcbdcdcd", k = 2</span></p>

<p><strong>输出：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">2</span></p>

<p><strong>解释：</strong>可以删除 <code>1</code> 个 <code>"a"</code> 和 <code>1</code> 个 <code>"d"</code> 使 <code>word</code> 成为 <code>2</code> 特殊字符串。<code>word</code> 变为 <code>"bdcbdcdcd"</code>，此时 <code>freq('b') == 2</code>，<code>freq('c') == 3</code>，<code>freq('d') == 4</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>输入：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">word = "aaabaaa", k = 2</span></p>

<p><strong>输出：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">1</span></p>

<p><strong>解释：</strong>可以删除<strong> </strong>1 个 <code>"b"</code> 使 <code>word</code> 成为 <code>2</code>特殊字符串。因此，<code>word</code> 变为 <code>"aaaaaa"</code>，此时每个字母的频率都是 <code>6</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>5</sup></code></li>
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
