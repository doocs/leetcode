---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3995.Minimum%20Cost%20to%20Convert%20String%20III/README_EN.md
---

<!-- problem:start -->

# [3995. Minimum Cost to Convert String III](https://leetcode.com/problems/minimum-cost-to-convert-string-iii)

[中文文档](/solution/3900-3999/3995.Minimum%20Cost%20to%20Convert%20String%20III/README.md)

## Description

<!-- description:start -->

<p>You are given two strings, <code>source</code> and <code>target</code>.</p>

<p>You are also given a 2D string array <code>rules</code>, where <code>rules[i] = [pattern<sub>i</sub>, replacement<sub>i</sub>]</code>, and an integer array <code>costs</code>, where <code>costs[i]</code> is the base cost of applying <code>rules[i]</code>. Both arrays have the same length. Additionally, <code>pattern<sub>i</sub></code> and <code>replacement<sub>i</sub></code> have the same length.</p>

<p>You may apply <strong>any</strong> rule <strong>any</strong> number of times. Each rule application works as follows:</p>

<ul>
	<li>Choose an index <code>l</code> such that the range of positions from <code>l</code> to <code>l + pattern<sub>i</sub>.length - 1</code> exists in the current string and <strong>none</strong> of these positions has been used in a previous rule application.</li>
	<li>For each index <code>j</code>, the character <code>pattern<sub>i</sub>[j]</code> must either be <strong>equal</strong> to the current character at position <code>l + j</code>, or be <code>&#39;*&#39;</code>.</li>
	<li>Replace the characters in this range with <code>replacement<sub>i</sub></code>. The replacement is used <strong>exactly</strong> as given and does not contain wildcards.</li>
	<li>The cost of this rule application is <code>costs[i]</code> <strong>plus</strong> the number of <code>&#39;*&#39;</code> characters in <code>pattern<sub>i</sub></code>.</li>
	<li>Once a character position has been used in a rule application, it <strong>cannot</strong> be used in any <strong>later</strong> rule application.</li>
</ul>

<p>Since every <code>pattern<sub>i</sub></code> and <code>replacement<sub>i</sub></code> have the same length, character positions are preserved after every rule application.</p>

<p>Return the <strong>minimum</strong> total cost required to transform <code>source</code> into <code>target</code>. If it is impossible, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">source = &quot;hello&quot;, target = &quot;world&quot;, rules = [[&quot;he&quot;,&quot;wo&quot;],[&quot;llo&quot;,&quot;rld&quot;]], costs = [3,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Apply <code>rules[0]</code> to replace <code>&quot;he&quot;</code> with <code>&quot;wo&quot;</code> at cost 3, so the string becomes <code>&quot;wollo&quot;</code>.</li>
	<li>Apply <code>rules[1]</code> to replace <code>&quot;llo&quot;</code> with <code>&quot;rld&quot;</code> at cost 4, so the string becomes <code>&quot;world&quot;</code>.</li>
	<li>The total cost is <code>3 + 4 = 7</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">source = &quot;cat&quot;, target = &quot;dog&quot;, rules = [[&quot;c*t&quot;,&quot;dog&quot;]], costs = [2]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Apply <code>rules[0]</code> to replace <code>&quot;cat&quot;</code> with <code>&quot;dog&quot;</code>. The wildcard <code>&#39;*&#39;</code> matches <code>&#39;a&#39;</code>, adding 1 to the base cost 2.</li>
	<li>The total cost is <code>2 + 1 = 3</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">source = &quot;test&quot;, target = &quot;next&quot;, rules = [[&quot;*e*t&quot;,&quot;next&quot;]], costs = [4]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Apply <code>rules[0]</code> to replace <code>&quot;test&quot;</code> with <code>&quot;next&quot;</code>. The first wildcard matches <code>&#39;t&#39;</code> and the second wildcard matches <code>&#39;s&#39;</code>, adding 2 to the base cost 4.</li>
	<li>The total cost is <code>4 + 2 = 6</code>.</li>
</ul>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">source = &quot;ab&quot;, target = &quot;bc&quot;, rules = [[&quot;a*&quot;,&quot;bd&quot;]], costs = [9]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>No sequence of rule applications can transform <code>source</code> into <code>target</code>, so the answer is -1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= source.length, target.length &lt;= 5000</code></li>
	<li><code>source</code> and <code>target</code> consist of lowercase English letters.</li>
	<li><code>1 &lt;= rules.length == costs.length &lt;= 200</code></li>
	<li><code>rules[i] = [pattern<sub>i</sub>, replacement<sub>i</sub>]</code></li>
	<li><code>1 &lt;= pattern<sub>i</sub>.length == replacement<sub>i</sub>.length &lt;= 20</code></li>
	<li><code>pattern<sub>i</sub></code> contains at least one lowercase English letter and at most 5 <code>&#39;*&#39;</code> characters.</li>
	<li><code>replacement<sub>i</sub></code> contains only lowercase English letters.</li>
	<li><code>1 &lt;= costs[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

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
