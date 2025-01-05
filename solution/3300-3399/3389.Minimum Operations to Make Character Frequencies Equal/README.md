---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3389.Minimum%20Operations%20to%20Make%20Character%20Frequencies%20Equal/README.md
rating: 2940
source: 第 428 场周赛 Q4
tags:
    - 哈希表
    - 字符串
    - 动态规划
    - 计数
    - 枚举
---

<!-- problem:start -->

# [3389. 使字符频率相等的最少操作次数](https://leetcode.cn/problems/minimum-operations-to-make-character-frequencies-equal)

[English Version](/solution/3300-3399/3389.Minimum%20Operations%20to%20Make%20Character%20Frequencies%20Equal/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;。</p>

<p>如果字符串 <code>t</code>&nbsp;中的字符出现次数相等，那么我们称&nbsp;<code>t</code>&nbsp;为 <strong>好的</strong>&nbsp;。</p>

<p>你可以执行以下操作 <strong>任意次</strong>&nbsp;：</p>

<ul>
	<li>从&nbsp;<code>s</code>&nbsp;中删除一个字符。</li>
	<li>往&nbsp;<code>s</code>&nbsp;中添加一个字符。</li>
	<li>将&nbsp;<code>s</code>&nbsp;中一个字母变成字母表中下一个字母。</li>
</ul>

<p><b>注意</b>&nbsp;，第三个操作不能将&nbsp;<code>'z'</code>&nbsp;变为&nbsp;<code>'a'</code>&nbsp;。</p>

<p>请你返回将 <code>s</code>&nbsp;变 <strong>好</strong>&nbsp;的 <strong>最少</strong>&nbsp;操作次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "acab"</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><b>解释：</b></p>

<p>删掉一个字符&nbsp;<code>'a'</code>&nbsp;，<code>s</code>&nbsp;变为好的。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "wddw"</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><strong>解释：</strong></p>

<p><code>s</code>&nbsp;一开始就是好的，所以不需要执行任何操作。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "aaabc"</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong></p>

<p>通过以下操作，将&nbsp;<code>s</code>&nbsp;变好：</p>

<ul>
	<li>将一个&nbsp;<code>'a'</code>&nbsp;变为&nbsp;<code>'b'</code>&nbsp;。</li>
	<li>往 <code>s</code>&nbsp;中插入一个&nbsp;<code>'c'</code>&nbsp;。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2&nbsp;* 10<sup>4</sup></code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
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
