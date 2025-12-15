---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3773.Maximum%20Number%20of%20Equal%20Length%20Runs/README.md
tags:
    - 哈希表
    - 字符串
    - 计数
---

<!-- problem:start -->

# [3773. 最大等长连续字符组 🔒](https://leetcode.cn/problems/maximum-number-of-equal-length-runs)

[English Version](/solution/3700-3799/3773.Maximum%20Number%20of%20Equal%20Length%20Runs/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个由小写英文字母组成的字符串&nbsp;<code>s</code>。</p>

<p><code>s</code>&nbsp;中的一个 <strong>连续字符组</strong> 是一个由无法再扩展的 <strong>相同</strong> 字符组成的 <strong><span data-keyword="substring-nonempty">子串</span></strong>。例如，<code>"hello"</code>&nbsp;中的连续字符组是&nbsp;<code>"h"</code>，<code>"e"</code>，<code>"ll"</code>&nbsp;和&nbsp;<code>"o"</code>。</p>

<p>你可以 <strong>选择</strong>&nbsp;<code>s</code>&nbsp;中&nbsp;<strong>相同</strong>&nbsp;长度的字符组。</p>

<p>返回一个整数，表示你可以在 <code>s</code> 中选择的最多连续字符组。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "hello"</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><strong>解释：</strong></p>

<p><code>s</code>&nbsp;中的连续字符组是&nbsp;<code>"h"</code>，<code>"e"</code>，<code>"ll"</code>&nbsp;和&nbsp;<code>"o"</code>。你可以选择&nbsp;<code>"h"</code>，<code>"e"</code>&nbsp;和&nbsp;<code>"o"</code>&nbsp;因为它们有相同的长度 1。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "aaabaaa"</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong></p>

<p><code>s</code>&nbsp;中的连续字符组是&nbsp;<code>"aaa"</code>，<code>"b"</code>&nbsp;和&nbsp;<code>"aaa"</code>。你可以选择&nbsp;<code>"aaa"</code>&nbsp;和&nbsp;<code>"aaa"</code>&nbsp;因为它们有相同的长度 3。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们可以用一个哈希表 $\textit{cnt}$ 来记录每个连续字符组长度出现的次数。遍历字符串 $s$，对于每个连续字符组，计算其长度 $m$，并将 $\textit{cnt}[m]$ 加 $1$。最后，答案即为 $\textit{cnt}$ 中的最大值。

时间复杂度 $O(n)$，空间复杂度 $O(n)$，其中 $n$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSameLengthRuns(self, s: str) -> int:
        cnt = Counter()
        for _, g in groupby(s):
            cnt[len(list(g))] += 1
        return max(cnt.values())
```

#### Java

```java
class Solution {
    public int maxSameLengthRuns(String s) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                ++j;
            }
            int m = j - i;
            ans = Math.max(ans, cnt.merge(m, 1, Integer::sum));
            i = j;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxSameLengthRuns(string s) {
        unordered_map<int, int> cnt;
        int ans = 0;
        int n = s.size();
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && s[j] == s[i]) {
                ++j;
            }
            int m = j - i;
            ans = max(ans, ++cnt[m]);
            i = j;
        }
        return ans;
    }
};
```

#### Go

```go
func maxSameLengthRuns(s string) (ans int) {
	cnt := map[int]int{}
	n := len(s)
	for i := 0; i < n; {
		j := i + 1
		for j < n && s[j] == s[i] {
			j++
		}
		m := j - i
		cnt[m]++
		ans = max(ans, cnt[m])
		i = j
	}
	return
}
```

#### TypeScript

```ts
function maxSameLengthRuns(s: string): number {
    const cnt: Record<number, number> = {};
    const n = s.length;
    let ans = 0;
    for (let i = 0; i < n; ) {
        let j = i + 1;
        while (j < n && s[j] === s[i]) {
            ++j;
        }
        const m = j - i;
        cnt[m] = (cnt[m] || 0) + 1;
        ans = Math.max(ans, cnt[m]);
        i = j;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
