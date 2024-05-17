---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3137.Minimum%20Number%20of%20Operations%20to%20Make%20Word%20K-Periodic/README_EN.md
rating: 1491
source: Weekly Contest 396 Q2
tags:
    - Hash Table
    - String
    - Counting
---

<!-- problem:start -->

# [3137. Minimum Number of Operations to Make Word K-Periodic](https://leetcode.com/problems/minimum-number-of-operations-to-make-word-k-periodic)

[中文文档](/solution/3100-3199/3137.Minimum%20Number%20of%20Operations%20to%20Make%20Word%20K-Periodic/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>word</code> of size <code>n</code>, and an integer <code>k</code> such that <code>k</code> divides <code>n</code>.</p>

<p>In one operation, you can pick any two indices <code>i</code> and <code>j</code>, that are divisible by <code>k</code>, then replace the <span data-keyword="substring">substring</span> of length <code>k</code> starting at <code>i</code> with the substring of length <code>k</code> starting at <code>j</code>. That is, replace the substring <code>word[i..i + k - 1]</code> with the substring <code>word[j..j + k - 1]</code>.<!-- notionvc: 49ac84f7-0724-452a-ab43-0c5e53f1db33 --></p>

<p>Return <em>the <strong>minimum</strong> number of operations required to make</em> <code>word</code> <em><strong>k-periodic</strong></em>.</p>

<p>We say that <code>word</code> is <strong>k-periodic</strong> if there is some string <code>s</code> of length <code>k</code> such that <code>word</code> can be obtained by concatenating <code>s</code> an arbitrary number of times. For example, if <code>word == &ldquo;ababab&rdquo;</code>, then <code>word</code> is 2-periodic for <code>s = &quot;ab&quot;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">word = &quot;leetcodeleet&quot;, k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io" style="
font-family: Menlo,sans-serif;
font-size: 0.85rem;
">1</span></p>

<p><strong>Explanation:</strong></p>

<p>We can obtain a 4-periodic string by picking i = 4 and j = 0. After this operation, word becomes equal to &quot;leetleetleet&quot;.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">word = &quot;</span>leetcoleet<span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">&quot;, k = 2</span></p>

<p><strong>Output:</strong> 3</p>

<p><strong>Explanation:</strong></p>

<p>We can obtain a 2-periodic string by applying the operations in the table below.</p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" height="146" style="border-collapse:collapse; text-align: center; vertical-align: middle;">
	<tbody>
		<tr>
			<th>i</th>
			<th>j</th>
			<th>word</th>
		</tr>
		<tr>
			<td style="padding: 5px 15px;">0</td>
			<td style="padding: 5px 15px;">2</td>
			<td style="padding: 5px 15px;">etetcoleet</td>
		</tr>
		<tr>
			<td style="padding: 5px 15px;">4</td>
			<td style="padding: 5px 15px;">0</td>
			<td style="padding: 5px 15px;">etetetleet</td>
		</tr>
		<tr>
			<td style="padding: 5px 15px;">6</td>
			<td style="padding: 5px 15px;">0</td>
			<td style="padding: 5px 15px;">etetetetet</td>
		</tr>
	</tbody>
</table>
</div>

<div id="gtx-trans" style="position: absolute; left: 107px; top: 238.5px;">
<div class="gtx-trans-icon">&nbsp;</div>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == word.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= word.length</code></li>
	<li><code>k</code> divides <code>word.length</code>.</li>
	<li><code>word</code> consists only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We can divide the string `word` into substrings of length $k$, then count the occurrence of each substring, and finally return $n/k$ minus the count of the most frequently occurring substring.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the length of the string `word`.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumOperationsToMakeKPeriodic(self, word: str, k: int) -> int:
        n = len(word)
        return n // k - max(Counter(word[i : i + k] for i in range(0, n, k)).values())
```

#### Java

```java
class Solution {
    public int minimumOperationsToMakeKPeriodic(String word, int k) {
        Map<String, Integer> cnt = new HashMap<>();
        int n = word.length();
        int mx = 0;
        for (int i = 0; i < n; i += k) {
            mx = Math.max(mx, cnt.merge(word.substring(i, i + k), 1, Integer::sum));
        }
        return n / k - mx;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumOperationsToMakeKPeriodic(string word, int k) {
        unordered_map<string, int> cnt;
        int n = word.size();
        int mx = 0;
        for (int i = 0; i < n; i += k) {
            mx = max(mx, ++cnt[word.substr(i, k)]);
        }
        return n / k - mx;
    }
};
```

#### Go

```go
func minimumOperationsToMakeKPeriodic(word string, k int) int {
	cnt := map[string]int{}
	n := len(word)
	mx := 0
	for i := 0; i < n; i += k {
		s := word[i : i+k]
		cnt[s]++
		mx = max(mx, cnt[s])
	}
	return n/k - mx
}
```

#### TypeScript

```ts
function minimumOperationsToMakeKPeriodic(word: string, k: number): number {
    const cnt: Map<string, number> = new Map();
    const n: number = word.length;
    let mx: number = 0;
    for (let i = 0; i < n; i += k) {
        const s = word.slice(i, i + k);
        cnt.set(s, (cnt.get(s) || 0) + 1);
        mx = Math.max(mx, cnt.get(s)!);
    }
    return Math.floor(n / k) - mx;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
