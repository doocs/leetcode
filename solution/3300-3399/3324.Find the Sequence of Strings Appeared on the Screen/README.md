---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3324.Find%20the%20Sequence%20of%20Strings%20Appeared%20on%20the%20Screen/README.md
---

<!-- problem:start -->

# [3324. 出现在屏幕上的字符串序列](https://leetcode.cn/problems/find-the-sequence-of-strings-appeared-on-the-screen)

[English Version](/solution/3300-3399/3324.Find%20the%20Sequence%20of%20Strings%20Appeared%20on%20the%20Screen/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>target</code>。</p>

<p>Alice 将会使用一种特殊的键盘在她的电脑上输入 <code>target</code>，这个键盘<strong> 只有两个 </strong>按键：</p>

<ul>
	<li>按键 1：在屏幕上的字符串后追加字符 <code>'a'</code>。</li>
	<li>按键 2：将屏幕上字符串的 <strong>最后一个 </strong>字符更改为英文字母表中的 <strong>下一个</strong> 字符。例如，<code>'c'</code> 变为 <code>'d'</code>，<code>'z'</code> 变为 <code>'a'</code>。</li>
</ul>

<p><strong>注意</strong>，最初屏幕上是一个<em>空</em>字符串 <code>""</code>，所以她<strong> 只能</strong> 按按键 1。</p>

<p>请你考虑按键次数 <strong>最少</strong> 的情况，按字符串出现顺序，返回 Alice 输入 <code>target</code> 时屏幕上出现的所有字符串列表。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">target = "abc"</span></p>

<p><strong>输出：</strong> <span class="example-io">["a","aa","ab","aba","abb","abc"]</span></p>

<p><strong>解释：</strong></p>

<p>Alice 按键的顺序如下：</p>

<ul>
	<li>按下按键 1，屏幕上的字符串变为 <code>"a"</code>。</li>
	<li>按下按键 1，屏幕上的字符串变为 <code>"aa"</code>。</li>
	<li>按下按键 2，屏幕上的字符串变为 <code>"ab"</code>。</li>
	<li>按下按键 1，屏幕上的字符串变为 <code>"aba"</code>。</li>
	<li>按下按键 2，屏幕上的字符串变为 <code>"abb"</code>。</li>
	<li>按下按键 2，屏幕上的字符串变为 <code>"abc"</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">target = "he"</span></p>

<p><strong>输出：</strong> <span class="example-io">["a","b","c","d","e","f","g","h","ha","hb","hc","hd","he"]</span></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= target.length &lt;= 400</code></li>
	<li><code>target</code> 仅由小写英文字母组成。</li>
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
