# [3081. 替换字符串中的问号使分数最小](https://leetcode.cn/problems/replace-question-marks-in-string-to-minimize-its-value)

[English Version](/solution/3000-3099/3081.Replace%20Question%20Marks%20in%20String%20to%20Minimize%20Its%20Value/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;。<code>s[i]</code>&nbsp;要么是小写英文字母，要么是问号&nbsp;<code>'?'</code>&nbsp;。</p>

<p>对于长度为 <code>m</code>&nbsp;且 <strong>只</strong>&nbsp;含有小写英文字母的字符串 <code>t</code>&nbsp;，我们定义函数&nbsp;<code>cost(i)</code>&nbsp;为下标 <code>i</code>&nbsp;之前（也就是范围 <code>[0, i - 1]</code>&nbsp;中）出现过与&nbsp;<code>t[i]</code>&nbsp;<strong>相同</strong>&nbsp;字符出现的次数。</p>

<p>字符串 <code>t</code>&nbsp;的&nbsp;<strong>分数</strong>&nbsp;为所有下标&nbsp;<code>i</code>&nbsp;的&nbsp;<code>cost(i)</code>&nbsp;之 <strong>和</strong>&nbsp;。</p>

<p>比方说，字符串&nbsp;<code>t = "aab"</code>&nbsp;：</p>

<ul>
	<li><code>cost(0) = 0</code></li>
	<li><code>cost(1) = 1</code></li>
	<li><code>cost(2) = 0</code></li>
	<li>所以，字符串&nbsp;<code>"aab"</code>&nbsp;的分数为&nbsp;<code>0 + 1 + 0 = 1</code>&nbsp;。</li>
</ul>

<p>你的任务是用小写英文字母&nbsp;<strong>替换</strong> <code>s</code>&nbsp;中 <strong>所有</strong> 问号，使 <code>s</code>&nbsp;的 <strong>分数</strong><strong>最小&nbsp;</strong>。</p>

<p>请你返回替换所有问号<em>&nbsp;</em><code>'?'</code>&nbsp;之后且分数最小的字符串。如果有多个字符串的&nbsp;<strong>分数最小</strong>&nbsp;，那么返回字典序最小的一个。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>输入：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">s = "???" </span></p>

<p><strong>输出：</strong>&nbsp;<span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">"abc" </span></p>

<p><strong>解释：</strong>这个例子中，我们将 <code>s</code>&nbsp;中的问号&nbsp;<code>'?'</code>&nbsp;替换得到&nbsp;<code>"abc"</code>&nbsp;。</p>

<p>对于字符串&nbsp;<code>"abc"</code>&nbsp;，<code>cost(0) = 0</code>&nbsp;，<code>cost(1) = 0</code>&nbsp;和&nbsp;<code>cost(2) = 0</code>&nbsp;。</p>

<p><code>"abc"</code>&nbsp;的分数为&nbsp;<code>0</code>&nbsp;。</p>

<p>其他修改 <code>s</code>&nbsp;得到分数 <code>0</code>&nbsp;的字符串为&nbsp;<code>"cba"</code>&nbsp;，<code>"abz"</code>&nbsp;和&nbsp;<code>"hey"</code>&nbsp;。</p>

<p>这些字符串中，我们返回字典序最小的。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>输入：</strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">s = "a?a?"</span></p>

<p><strong>输出：</strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">"abac"</span></p>

<p><strong>解释：</strong>这个例子中，我们将&nbsp;<code>s</code>&nbsp;中的问号&nbsp;<code>'?'</code>&nbsp;替换得到&nbsp;<code>"abac"</code>&nbsp;。</p>

<p>对于字符串&nbsp;<code>"abac"</code>&nbsp;，<code>cost(0) = 0</code>&nbsp;，<code>cost(1) = 0</code>&nbsp;，<code>cost(2) = 1</code>&nbsp;和&nbsp;<code>cost(3) = 0</code>&nbsp;。</p>

<p><code>"abac"</code>&nbsp;的分数为&nbsp;<code>1</code>&nbsp;。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code>&nbsp;要么是小写英文字母，要么是&nbsp;<code>'?'</code> 。</li>
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
