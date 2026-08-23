---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4026.Maximum%20Gap%20Between%20Stations/README_EN.md
rating: 1675
source: Weekly Contest 515 Q3
tags:
    - Greedy
    - Two Pointers
    - String
---

<!-- problem:start -->

# [4026. Maximum Gap Between Stations](https://leetcode.com/problems/maximum-gap-between-stations)

[中文文档](/solution/4000-4099/4026.Maximum%20Gap%20Between%20Stations/README.md)

## Description

<!-- description:start -->

<p>You are given two strings <code>skill</code> and <code>station</code> of lengths <code>n</code> and <code>m</code>, respectively.</p>

<p><code>skill[i]</code> represents the skill of worker <code>i</code>, and <code>station[j]</code> represents the skill supported by station <code>j</code>.</p>

<p>You must assign <strong>every</strong> worker to a <strong>distinct</strong> station. Let <code>j<sub>i</sub></code> be the index of the station assigned to worker <code>i</code>. A valid assignment must satisfy:</p>

<ul>
	<li><code>station[j<sub>i</sub>] == skill[i]</code> for every <code>0 &lt;= i &lt; n</code>.</li>
	<li>The assigned station indices must be <strong>strictly</strong> increasing in worker order, meaning <code>j<sub>0</sub> &lt; j<sub>1</sub> &lt; ... &lt; j<sub>n - 1</sub></code>.</li>
</ul>

<p>The <strong>gap</strong> of an assignment is the <strong>maximum difference</strong> between the station indices assigned to two <strong>consecutive</strong> workers. In other words, it is <code>max(j<sub>i</sub> - j<sub>i - 1</sub>)</code> over all <code>1 &lt;= i &lt; n</code>.</p>

<p>If there is only one worker, the gap is 0.</p>

<p>Return the <strong>maximum</strong> possible gap among all valid assignments. It is guaranteed that <strong>at least</strong> one valid assignment exists.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">skill = &quot;aa&quot;, station = &quot;aaaa&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The two workers must be assigned to two different <code>&#39;a&#39;</code> stations.</li>
	<li>Assigning them to stations <code>[0, 3]</code> gives a gap of 3.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">skill = &quot;xyz&quot;, station = &quot;xyzz&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Assign worker 0 to station <code>j = 0</code>, and worker 1 to station <code>j = 1</code>.</li>
	<li>To maximize the gap, assign worker 2 to station <code>j = 3</code>.</li>
	<li>This gives the assignment <code>[0, 1, 3]</code> with gaps <code>[1, 2]</code>, so the gap is 2.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">skill = &quot;cbc&quot;, station = &quot;cbcdbc&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Assign worker 0 to station <code>j = 0</code>, and worker 1 to station <code>j = 1</code>.</li>
	<li>To maximize the gap, assign worker 2 to station <code>j = 5</code>.</li>
	<li>This gives the assignment <code>[0, 1, 5]</code> with gaps <code>[1, 4]</code>, so the gap is 4.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>skill.length == n</code></li>
	<li><code>station.length == m</code></li>
	<li><code>1 &lt;= n &lt;= m &lt;= 10<sup>5</sup></code></li>
	<li><code>skill</code> and <code>station</code> consist of lowercase English letters.</li>
	<li>It is guaranteed that a valid assignment exists for every worker.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

The maximum gap must occur between some pair of consecutive workers $(i, i+1)$. To maximize this pair's gap, workers $0, 1, \ldots, i$ should be assigned as far left as possible, and workers $i+1, \ldots, n-1$ as far right as possible.

Thus, we scan from right to left and precompute $\textit{suf}[i]$: the rightmost station worker $i$ can take, assuming workers $i+1, \ldots, n-1$ occupy even righter stations. Then we scan from left to right, assign worker $i$ to the current leftmost matching station $\textit{pre}$, and update the answer with $\textit{suf}[i+1] - \textit{pre}$.

We take the maximum over all consecutive pairs. If there is only one worker, the answer is $0$.

The time complexity is $O(n + m)$, and the space complexity is $O(n)$, where $n$ and $m$ are the lengths of $\textit{skill}$ and $\textit{station}$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumGap(self, skill: str, station: str) -> int:
        n, m = len(skill), len(station)
        suf = [0] * n
        j = m - 1
        for i in range(n - 1, 0, -1):
            while station[j] != skill[i]:
                j -= 1
            suf[i] = j
            j -= 1

        ans = pre = 0
        for i in range(n - 1):
            while station[pre] != skill[i]:
                pre += 1
            ans = max(ans, suf[i + 1] - pre)
            pre += 1
        return ans
```

#### Java

```java
class Solution {
    public int maximumGap(String skill, String station) {
        int n = skill.length();
        int m = station.length();

        int[] suf = new int[n];
        int j = m - 1;

        for (int i = n - 1; i > 0; i--) {
            while (station.charAt(j) != skill.charAt(i)) {
                j--;
            }

            suf[i] = j;
            j--;
        }

        int ans = 0;
        int pre = 0;

        for (int i = 0; i < n - 1; i++) {
            while (station.charAt(pre) != skill.charAt(i)) {
                pre++;
            }

            ans = Math.max(ans, suf[i + 1] - pre);
            pre++;
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumGap(string skill, string station) {
        int n = skill.size();
        int m = station.size();

        vector<int> suf(n);
        int j = m - 1;

        for (int i = n - 1; i > 0; i--) {
            while (station[j] != skill[i]) {
                j--;
            }

            suf[i] = j;
            j--;
        }

        int ans = 0;
        int pre = 0;

        for (int i = 0; i < n - 1; i++) {
            while (station[pre] != skill[i]) {
                pre++;
            }

            ans = max(ans, suf[i + 1] - pre);
            pre++;
        }

        return ans;
    }
};
```

#### Go

```go
func maximumGap(skill string, station string) int {
	n, m := len(skill), len(station)

	suf := make([]int, n)
	j := m - 1

	for i := n - 1; i > 0; i-- {
		for station[j] != skill[i] {
			j--
		}

		suf[i] = j
		j--
	}

	ans := 0
	pre := 0

	for i := 0; i < n-1; i++ {
		for station[pre] != skill[i] {
			pre++
		}

		ans = max(ans, suf[i+1]-pre)

		pre++
	}

	return ans
}
```

#### TypeScript

```ts
function maximumGap(skill: string, station: string): number {
    const n = skill.length;
    const m = station.length;

    const suf: number[] = Array(n).fill(0);
    let j = m - 1;

    for (let i = n - 1; i > 0; i--) {
        while (station[j] !== skill[i]) {
            j--;
        }

        suf[i] = j;
        j--;
    }

    let ans = 0;
    let pre = 0;

    for (let i = 0; i < n - 1; i++) {
        while (station[pre] !== skill[i]) {
            pre++;
        }

        ans = Math.max(ans, suf[i + 1] - pre);
        pre++;
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
