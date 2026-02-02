---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3784.Minimum%20Deletion%20Cost%20to%20Make%20All%20Characters%20Equal/README_EN.md
rating: 1387
source: Weekly Contest 481 Q2
tags:
    - Array
    - Hash Table
    - String
    - Enumeration
---

<!-- problem:start -->

# [3784. Minimum Deletion Cost to Make All Characters Equal](https://leetcode.com/problems/minimum-deletion-cost-to-make-all-characters-equal)

[中文文档](/solution/3700-3799/3784.Minimum%20Deletion%20Cost%20to%20Make%20All%20Characters%20Equal/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> of length <code>n</code> and an integer array <code>cost</code> of the same length, where <code>cost[i]</code> is the cost to <strong>delete</strong> the <code>i<sup>th</sup></code> character of <code>s</code>.</p>

<p>You may delete any number of characters from <code>s</code> (possibly none), such that the resulting string is <strong>non-empty</strong> and consists of <strong>equal</strong> characters.</p>

<p>Return an integer denoting the <strong>minimum</strong> total deletion cost required.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aabaac&quot;, cost = [1,2,3,4,1,10]</span></p>

<p><strong>Output:</strong> <span class="example-io">11</span></p>

<p><strong>Explanation:</strong></p>

<p>Deleting the characters at indices 0, 1, 2, 3, 4 results in the string <code>&quot;c&quot;</code>, which consists of equal characters, and the total cost is <code>cost[0] + cost[1] + cost[2] + cost[3] + cost[4] = 1 + 2 + 3 + 4 + 1 = 11</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abc&quot;, cost = [10,5,8]</span></p>

<p><strong>Output:</strong> <span class="example-io">13</span></p>

<p><strong>Explanation:</strong></p>

<p>Deleting the characters at indices 1 and 2 results in the string <code>&quot;a&quot;</code>, which consists of equal characters, and the total cost is <code>cost[1] + cost[2] = 5 + 8 = 13</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;zzzzz&quot;, cost = [67,67,67,67,67]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>All characters in <code>s</code> are equal, so the deletion cost is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == s.length == cost.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= cost[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Grouping + Enumeration

We calculate the total deletion cost for each character in the string and store it in a hash table $g$, where the key is the character and the value is the corresponding total deletion cost. We also calculate the total cost $\textit{tot}$ of deleting all characters.

Next, we iterate through the hash table $g$. For each character $c$, we calculate the minimum deletion cost required to keep that character, which is $\textit{tot} - g[c]$. The final answer is the minimum of all the minimum deletion costs corresponding to each character.

The time complexity is $O(n)$ and the space complexity is $O(|\Sigma|)$, where $n$ is the length of the string $s$, and $\Sigma$ is the set of distinct characters in the string.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCost(self, s: str, cost: List[int]) -> int:
        tot = 0
        g = defaultdict(int)
        for c, v in zip(s, cost):
            tot += v
            g[c] += v
        return min(tot - x for x in g.values())
```

#### Java

```java
class Solution {
    public long minCost(String s, int[] cost) {
        long tot = 0;
        Map<Character, Long> g = new HashMap<>(26);
        for (int i = 0; i < cost.length; ++i) {
            tot += cost[i];
            g.merge(s.charAt(i), (long) cost[i], Long::sum);
        }
        long ans = tot;
        for (long v : g.values()) {
            ans = Math.min(ans, tot - v);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minCost(string s, vector<int>& cost) {
        long long tot = 0;
        unordered_map<char, long long> g;
        for (int i = 0; i < cost.size(); ++i) {
            tot += cost[i];
            g[s[i]] += cost[i];
        }
        long long ans = tot;
        for (auto [_, v] : g) {
            ans = min(ans, tot - v);
        }
        return ans;
    }
};
```

#### Go

```go
func minCost(s string, cost []int) int64 {
	tot := int64(0)
	g := map[byte]int64{}
	for i, v := range cost {
		tot += int64(v)
		g[s[i]] += int64(v)
	}
	ans := tot
	for _, x := range g {
		ans = min(ans, tot-x)
	}
	return ans
}
```

#### TypeScript

```ts
function minCost(s: string, cost: number[]): number {
    let tot = 0;
    const g: Map<string, number> = new Map();
    for (let i = 0; i < s.length; i++) {
        const c = s[i];
        const v = cost[i];
        tot += v;
        g.set(c, (g.get(c) ?? 0) + v);
    }
    let ans = tot;
    for (const x of g.values()) {
        ans = Math.min(ans, tot - x);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
