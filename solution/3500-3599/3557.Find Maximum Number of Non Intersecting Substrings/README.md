---
comments: true
difficulty: 中等
rating: 1719
source: 第 157 场双周赛 Q2
tags:
    - 贪心
    - 哈希表
    - 字符串
    - 动态规划
---

<!-- problem:start -->

# [3557. 不相交子字符串的最大数量](https://leetcode.cn/problems/find-maximum-number-of-non-intersecting-substrings)

[English Version](/solution/3500-3599/3557.Find%20Maximum%20Number%20of%20Non%20Intersecting%20Substrings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>word</code>。</p>

<p>返回以&nbsp;<strong>首尾字母相同&nbsp;</strong>且&nbsp;<strong>长度至少为 4&nbsp;</strong>的&nbsp;<strong>不相交子字符串&nbsp;</strong>的最大数量。</p>

<p><strong>子字符串&nbsp;</strong>是字符串中连续的&nbsp;<b>非空&nbsp;</b>字符序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">word = "abcdeafdef"</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>两个子字符串是 <code>"abcdea"</code> 和 <code>"fdef"</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">word = "bcdaaaab"</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>唯一的子字符串是 <code>"aaaa"</code>。注意我们&nbsp;<strong>不能&nbsp;</strong>同时选择 <code>"bcdaaaab"</code>，因为它和另一个子字符串有重叠。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>word</code> 仅由小写英文字母组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 子串须首尾同字母且长度至少 $4$，并两两不交，求最多个数。$n \le 2 \cdot 10^5$，不能枚举所有区间。
>
> 贪心从左扫描：对每个字母记录上一次可用的起点，一旦当前位置与该起点距离至少 $3$ 即可收割一段并清空该字母的起点。先结束的短段不妨碍后面再选，个数最大。

<!-- thinking:end -->

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
