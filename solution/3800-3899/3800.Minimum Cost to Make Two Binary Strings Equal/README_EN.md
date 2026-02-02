---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3800.Minimum%20Cost%20to%20Make%20Two%20Binary%20Strings%20Equal/README_EN.md
rating: 1840
source: Weekly Contest 483 Q3
tags:
    - Greedy
    - String
---

<!-- problem:start -->

# [3800. Minimum Cost to Make Two Binary Strings Equal](https://leetcode.com/problems/minimum-cost-to-make-two-binary-strings-equal)

[中文文档](/solution/3800-3899/3800.Minimum%20Cost%20to%20Make%20Two%20Binary%20Strings%20Equal/README.md)

## Description

<!-- description:start -->

<p>You are given two binary strings <code>s</code> and <code>t</code>, both of length <code>n</code>, and three <strong>positive</strong> integers <code>flipCost</code>, <code>swapCost</code>, and <code>crossCost</code>.</p>

<p>You are allowed to apply the following operations any number of times (in any order) to the strings <code>s</code> and <code>t</code>:</p>

<ul>
	<li>Choose any index <code>i</code> and flip <code>s[i]</code> or <code>t[i]</code> (change <code>&#39;0&#39;</code> to <code>&#39;1&#39;</code> or <code>&#39;1&#39;</code> to <code>&#39;0&#39;</code>). The cost of this operation is <code>flipCost</code>.</li>
	<li>Choose two <strong>distinct</strong> indices <code>i</code> and <code>j</code>, and swap either <code>s[i]</code> and <code>s[j]</code> or <code>t[i]</code> and <code>t[j]</code>. The cost of this operation is <code>swapCost</code>.</li>
	<li>Choose an index <code>i</code> and swap <code>s[i]</code> with <code>t[i]</code>. The cost of this operation is <code>crossCost</code>.</li>
</ul>

<p>Return an integer denoting the <strong>minimum</strong> total cost needed to make the strings <code>s</code> and <code>t</code> equal.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;01000&quot;, t = &quot;10111&quot;, flipCost = 10, swapCost = 2, crossCost = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">16</span></p>

<p><strong>Explanation:</strong></p>

<p>We can perform the following operations:</p>

<ul>
	<li>Swap <code>s[0]</code> and <code>s[1]</code> (<code>swapCost = 2</code>). After this operation, <code>s = &quot;10000&quot;</code> and <code>t = &quot;10111&quot;</code>.</li>
	<li>Cross swap <code>s[2]</code> and <code>t[2]</code> (<code>crossCost = 2</code>). After this operation, <code>s = &quot;10100&quot;</code> and <code>t = &quot;10011&quot;</code>.</li>
	<li>Swap <code>s[2]</code> and <code>s[3]</code> (<code>swapCost = 2</code>). After this operation, <code>s = &quot;10010&quot;</code> and <code>t = &quot;10011&quot;</code>.</li>
	<li>Flip <code>s[4]</code> (<code>flipCost = 10</code>). After this operation, <code>s = t = &quot;10011&quot;</code>.</li>
</ul>

<p>The total cost is <code>2 + 2 + 2 + 10 = 16</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;001&quot;, t = &quot;110&quot;, flipCost = 2, swapCost = 100, crossCost = 100</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>Flipping all the bits of <code>s</code> makes the strings equal, and the total cost is <code>3 * flipCost = 3 * 2 = 6</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;1010&quot;, t = &quot;1010&quot;, flipCost = 5, swapCost = 5, crossCost = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The strings are already equal, so no operations are required.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == s.length == t.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code>​​​​​​​</li>
	<li><code>1 &lt;= flipCost, swapCost, crossCost &lt;= 10<sup>9</sup></code></li>
	<li><code>s</code> and <code>t</code> consist only of the characters <code>&#39;0&#39;</code> and <code>&#39;1&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumCost(
        self, s: str, t: str, flipCost: int, swapCost: int, crossCost: int
    ) -> int:
        diff = [0] * 2
        for c1, c2 in zip(s, t):
            if c1 != c2:
                diff[int(c1)] += 1
        ans = (diff[0] + diff[1]) * flipCost
        mx = max(diff)
        mn = min(diff)
        ans = min(ans, mn * swapCost + (mx - mn) * flipCost)
        avg = (mx + mn) // 2
        ans = min(
            ans,
            (avg - mn) * crossCost + avg * swapCost + (mx + mn - avg * 2) * flipCost,
        )
        return ans
```

#### Java

```java
class Solution {
    public long minimumCost(String s, String t, int flipCost, int swapCost, int crossCost) {
        long[] diff = new long[2];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c1 = s.charAt(i), c2 = t.charAt(i);
            if (c1 != c2) {
                diff[c1 - '0']++;
            }
        }

        long ans = (diff[0] + diff[1]) * flipCost;

        long mx = Math.max(diff[0], diff[1]);
        long mn = Math.min(diff[0], diff[1]);
        ans = Math.min(ans, mn * swapCost + (mx - mn) * flipCost);

        long avg = (mx + mn) / 2;
        ans = Math.min(
            ans, (avg - mn) * crossCost + avg * swapCost + (mx + mn - avg * 2) * flipCost);
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minimumCost(string s, string t, int flipCost, int swapCost, int crossCost) {
        long long diff[2] = {0, 0};
        int n = s.size();
        for (int i = 0; i < n; i++) {
            if (s[i] != t[i]) {
                diff[s[i] - '0']++;
            }
        }

        long long ans = (diff[0] + diff[1]) * flipCost;

        long long mx = max(diff[0], diff[1]);
        long long mn = min(diff[0], diff[1]);
        ans = min(ans, mn * 1LL * swapCost + (mx - mn) * flipCost);

        long long avg = (mx + mn) / 2;
        ans = min(ans, (avg - mn) * crossCost + avg * swapCost + (mx + mn - avg * 2) * flipCost);

        return ans;
    }
};
```

#### Go

```go
func minimumCost(s string, t string, flipCost int, swapCost int, crossCost int) int64 {
	var diff [2]int64
	n := len(s)
	for i := 0; i < n; i++ {
		if s[i] != t[i] {
			diff[s[i]-'0']++
		}
	}

	ans := (diff[0] + diff[1]) * int64(flipCost)

	mx := max(diff[0], diff[1])
	mn := min(diff[0], diff[1])
	ans = min(ans, mn*int64(swapCost)+(mx-mn)*int64(flipCost))

	avg := (mx + mn) / 2
	ans = min(ans, (avg-mn)*int64(crossCost)+avg*int64(swapCost)+(mx+mn-avg*2)*int64(flipCost))

	return ans
}
```

#### TypeScript

```ts
function minimumCost(
    s: string,
    t: string,
    flipCost: number,
    swapCost: number,
    crossCost: number,
): number {
    const diff: number[] = [0, 0];
    const n = s.length;

    for (let i = 0; i < n; i++) {
        if (s[i] !== t[i]) {
            diff[s.charCodeAt(i) - 48]++;
        }
    }

    let ans = (diff[0] + diff[1]) * flipCost;

    const mx = Math.max(diff[0], diff[1]);
    const mn = Math.min(diff[0], diff[1]);
    ans = Math.min(ans, mn * swapCost + (mx - mn) * flipCost);

    const avg = (mx + mn) >> 1;
    ans = Math.min(ans, (avg - mn) * crossCost + avg * swapCost + (mx + mn - avg * 2) * flipCost);

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
