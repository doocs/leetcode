---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3597.Partition%20String/README.md
---

<!-- problem:start -->

# [3597. 分割字符串](https://leetcode.cn/problems/partition-string)

[English Version](/solution/3500-3599/3597.Partition%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code>，按照以下步骤将其分割为 <strong>互不相同的段&nbsp;</strong>：</p>

<ul>
	<li>从下标&nbsp;0 开始构建一个段。</li>
	<li>逐字符扩展当前段，直到该段之前未曾出现过。</li>
	<li>只要当前段是唯一的，就将其加入段列表，标记为已经出现过，并从下一个下标开始构建新的段。</li>
	<li>重复上述步骤，直到处理完整个字符串 <code>s</code>。</li>
</ul>

<p>返回字符串数组 <code>segments</code>，其中 <code>segments[i]</code> 表示创建的第 <code>i</code> 段。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "abbccccd"</span></p>

<p><strong>输出：</strong> <span class="example-io">["a","b","bc","c","cc","d"]</span></p>

<p><strong>解释：</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">下标</th>
			<th style="border: 1px solid black;">添加后的段</th>
			<th style="border: 1px solid black;">已经出现过的段</th>
			<th style="border: 1px solid black;">当前段是否已经出现过？</th>
			<th style="border: 1px solid black;">新段</th>
			<th style="border: 1px solid black;">更新后已经出现过的段</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">"a"</td>
			<td style="border: 1px solid black;">[]</td>
			<td style="border: 1px solid black;">否</td>
			<td style="border: 1px solid black;">""</td>
			<td style="border: 1px solid black;">["a"]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">"b"</td>
			<td style="border: 1px solid black;">["a"]</td>
			<td style="border: 1px solid black;">否</td>
			<td style="border: 1px solid black;">""</td>
			<td style="border: 1px solid black;">["a", "b"]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">"b"</td>
			<td style="border: 1px solid black;">["a", "b"]</td>
			<td style="border: 1px solid black;">是</td>
			<td style="border: 1px solid black;">"b"</td>
			<td style="border: 1px solid black;">["a", "b"]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">"bc"</td>
			<td style="border: 1px solid black;">["a", "b"]</td>
			<td style="border: 1px solid black;">否</td>
			<td style="border: 1px solid black;">""</td>
			<td style="border: 1px solid black;">["a", "b", "bc"]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">"c"</td>
			<td style="border: 1px solid black;">["a", "b", "bc"]</td>
			<td style="border: 1px solid black;">否</td>
			<td style="border: 1px solid black;">""</td>
			<td style="border: 1px solid black;">["a", "b", "bc", "c"]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">"c"</td>
			<td style="border: 1px solid black;">["a", "b", "bc", "c"]</td>
			<td style="border: 1px solid black;">是</td>
			<td style="border: 1px solid black;">"c"</td>
			<td style="border: 1px solid black;">["a", "b", "bc", "c"]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">6</td>
			<td style="border: 1px solid black;">"cc"</td>
			<td style="border: 1px solid black;">["a", "b", "bc", "c"]</td>
			<td style="border: 1px solid black;">否</td>
			<td style="border: 1px solid black;">""</td>
			<td style="border: 1px solid black;">["a", "b", "bc", "c", "cc"]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">7</td>
			<td style="border: 1px solid black;">"d"</td>
			<td style="border: 1px solid black;">["a", "b", "bc", "c", "cc"]</td>
			<td style="border: 1px solid black;">否</td>
			<td style="border: 1px solid black;">""</td>
			<td style="border: 1px solid black;">["a", "b", "bc", "c", "cc", "d"]</td>
		</tr>
	</tbody>
</table>

<p>因此，最终输出为 <code>["a", "b", "bc", "c", "cc", "d"]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "aaaa"</span></p>

<p><strong>输出：</strong> <span class="example-io">["a","aa"]</span></p>

<p><strong>解释：</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">下标</th>
			<th style="border: 1px solid black;">添加后的段</th>
			<th style="border: 1px solid black;">已经出现过的段</th>
			<th style="border: 1px solid black;">当前段是否已经出现过？</th>
			<th style="border: 1px solid black;">新段</th>
			<th style="border: 1px solid black;">更新后已经出现过的段</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">"a"</td>
			<td style="border: 1px solid black;">[]</td>
			<td style="border: 1px solid black;">否</td>
			<td style="border: 1px solid black;">""</td>
			<td style="border: 1px solid black;">["a"]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">"a"</td>
			<td style="border: 1px solid black;">["a"]</td>
			<td style="border: 1px solid black;">是</td>
			<td style="border: 1px solid black;">"a"</td>
			<td style="border: 1px solid black;">["a"]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">"aa"</td>
			<td style="border: 1px solid black;">["a"]</td>
			<td style="border: 1px solid black;">否</td>
			<td style="border: 1px solid black;">""</td>
			<td style="border: 1px solid black;">["a", "aa"]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">"a"</td>
			<td style="border: 1px solid black;">["a", "aa"]</td>
			<td style="border: 1px solid black;">是</td>
			<td style="border: 1px solid black;">"a"</td>
			<td style="border: 1px solid black;">["a", "aa"]</td>
		</tr>
	</tbody>
</table>

<p>因此，最终输出为 <code>["a", "aa"]</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 仅包含小写英文字母。</li>
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
