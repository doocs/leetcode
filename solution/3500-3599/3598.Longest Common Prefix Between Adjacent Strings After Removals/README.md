---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3598.Longest%20Common%20Prefix%20Between%20Adjacent%20Strings%20After%20Removals/README.md
---

<!-- problem:start -->

# [3598. 相邻字符串之间的最长公共前缀](https://leetcode.cn/problems/longest-common-prefix-between-adjacent-strings-after-removals)

[English Version](/solution/3500-3599/3598.Longest%20Common%20Prefix%20Between%20Adjacent%20Strings%20After%20Removals/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串数组 <code>words</code>，对于范围 <code>[0, words.length - 1]</code> 内的每个下标&nbsp;<code>i</code>，执行以下步骤：</p>

<ul>
	<li>从 <code>words</code> 数组中移除下标&nbsp;<code>i</code> 处的元素。</li>
	<li>计算修改后的数组中所有&nbsp;<strong>相邻对&nbsp;</strong>之间的&nbsp;<strong>最长公共前缀&nbsp;</strong>的长度。</li>
</ul>

<p>返回一个数组 <code>answer</code>，其中 <code>answer[i]</code> 是移除下标&nbsp;<code>i</code> 后，相邻对之间最长公共前缀的长度。如果 <strong>不存在&nbsp;</strong>相邻对，或者&nbsp;<strong>不存在&nbsp;</strong>公共前缀，则 <code>answer[i]</code> 应为 0。</p>

<p>字符串的前缀是从字符串的开头开始延伸到任意位置的子字符串。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">words = ["jump","run","run","jump","run"]</span></p>

<p><strong>输出：</strong> <span class="example-io">[3,0,0,3,3]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>移除下标&nbsp;0：
	<ul>
		<li><code>words</code> 变为 <code>["run", "run", "jump", "run"]</code></li>
		<li>最长的相邻对是 <code>["run", "run"]</code>，其公共前缀为 <code>"run"</code>（长度为 3）</li>
	</ul>
	</li>
	<li>移除下标&nbsp;1：
	<ul>
		<li><code>words</code> 变为 <code>["jump", "run", "jump", "run"]</code></li>
		<li>没有相邻对有公共前缀（长度为 0）</li>
	</ul>
	</li>
	<li>移除下标&nbsp;2：
	<ul>
		<li><code>words</code> 变为 <code>["jump", "run", "jump", "run"]</code></li>
		<li>没有相邻对有公共前缀（长度为 0）</li>
	</ul>
	</li>
	<li>移除下标&nbsp;3：
	<ul>
		<li><code>words</code> 变为 <code>["jump", "run", "run", "run"]</code></li>
		<li>最长的相邻对是 <code>["run", "run"]</code>，其公共前缀为 <code>"run"</code>（长度为 3）</li>
	</ul>
	</li>
	<li>移除下标&nbsp;4：
	<ul>
		<li><code>words</code> 变为 <code>["jump", "run", "run", "jump"]</code></li>
		<li>最长的相邻对是 <code>["run", "run"]</code>，其公共前缀为 <code>"run"</code>（长度为 3）</li>
	</ul>
	</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">words = ["dog","racer","car"]</span></p>

<p><strong>输出：</strong> <span class="example-io">[0,0,0]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>移除任意下标都会导致答案为 0。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10<sup>4</sup></code></li>
	<li><code>words[i]</code> 仅由小写英文字母组成。</li>
	<li><code>words[i]</code> 的长度总和不超过 <code>10<sup>5</sup></code>。</li>
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
