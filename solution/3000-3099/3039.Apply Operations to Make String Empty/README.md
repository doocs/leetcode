# [3039. 进行操作使字符串为空](https://leetcode.cn/problems/apply-operations-to-make-string-empty)

[English Version](/solution/3000-3099/3039.Apply%20Operations%20to%20Make%20String%20Empty/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;。</p>

<p>请你进行以下操作直到 <code>s</code>&nbsp;为 <strong>空</strong>&nbsp;：</p>

<ul>
	<li>每次操作 <strong>依次</strong> 遍历 <code>'a'</code> 到 <code>'z'</code>，如果当前字符出现在 <code>s</code> 中，那么删除出现位置&nbsp;<strong>最早</strong>&nbsp;的该字符。</li>
</ul>

<p>请你返回进行 <strong>最后</strong>&nbsp;一次操作 <strong>之前</strong>&nbsp;的字符串<em>&nbsp;</em><code>s</code><em>&nbsp;</em>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>s = "aabcbbca"
<b>输出：</b>"ba"
<b>解释：</b>我们进行以下操作：
- 删除 s = "<em><strong>a</strong></em>a<em><strong>bc</strong></em>bbca" 中加粗加斜字符，得到字符串 s = "abbca" 。
- 删除 s = "<em><strong>ab</strong></em>b<em><strong>c</strong></em>a" 中加粗加斜字符，得到字符串 s = "ba" 。
- 删除 s = "<em><strong>ba</strong></em>" 中加粗加斜字符，得到字符串 s = "" 。
进行最后一次操作之前的字符串为 "ba" 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>s = "abcd"
<b>输出：</b>"abcd"
<b>解释：</b>我们进行以下操作：
- 删除 s = "<em><strong>abcd</strong></em>" 中加粗加斜字符，得到字符串 s = "" 。
进行最后一次操作之前的字符串为 "abcd" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
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
