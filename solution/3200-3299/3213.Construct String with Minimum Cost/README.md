---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3213.Construct%20String%20with%20Minimum%20Cost/README.md
---

<!-- problem:start -->

# [3213. 最小代价构造字符串](https://leetcode.cn/problems/construct-string-with-minimum-cost)

[English Version](/solution/3200-3299/3213.Construct%20String%20with%20Minimum%20Cost/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>target</code>、一个字符串数组 <code>words</code> 以及一个整数数组 <code>costs</code>，这两个数组长度相同。</p>

<p>设想一个空字符串 <code>s</code>。</p>

<p>你可以执行以下操作任意次数（包括<strong>零</strong>次）：</p>

<ul>
	<li>选择一个在范围&nbsp; <code>[0, words.length - 1]</code> 的索引 <code>i</code>。</li>
	<li>将 <code>words[i]</code> 追加到 <code>s</code>。</li>
	<li>该操作的成本是 <code>costs[i]</code>。</li>
</ul>

<p>返回使 <code>s</code> 等于 <code>target</code> 的 <strong>最小</strong> 成本。如果不可能，返回 <code>-1</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">target = "abcdef", words = ["abdef","abc","d","def","ef"], costs = [100,1,1,10,5]</span></p>

<p><strong>输出：</strong> <span class="example-io">7</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>选择索引 1 并以成本 1 将 <code>"abc"</code> 追加到 <code>s</code>，得到 <code>s = "abc"</code>。</li>
	<li>选择索引 2 并以成本 1 将 <code>"d"</code> 追加到 <code>s</code>，得到 <code>s = "abcd"</code>。</li>
	<li>选择索引 4 并以成本 5 将 <code>"ef"</code> 追加到 <code>s</code>，得到 <code>s = "abcdef"</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">target = "aaaa", words = ["z","zz","zzz"], costs = [1,10,100]</span></p>

<p><strong>输出：</strong> <span class="example-io">-1</span></p>

<p><strong>解释：</strong></p>

<p>无法使 <code>s</code> 等于 <code>target</code>，因此返回 -1。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= target.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words.length == costs.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words[i].length &lt;= target.length</code></li>
	<li>所有 <code>words[i].length</code> 的总和小于或等于 <code>5 * 10<sup>4</sup></code></li>
	<li><code>target</code> 和 <code>words[i]</code> 仅由小写英文字母组成。</li>
	<li><code>1 &lt;= costs[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
