---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2237.Count%20Positions%20on%20Street%20With%20Required%20Brightness/README_EN.md
tags:
    - Array
    - Prefix Sum
---

<!-- problem:start -->

# [2237. Count Positions on Street With Required Brightness 🔒](https://leetcode.com/problems/count-positions-on-street-with-required-brightness)

[中文文档](/solution/2200-2299/2237.Count%20Positions%20on%20Street%20With%20Required%20Brightness/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code>. A perfectly straight street is represented by a number line ranging from <code>0</code> to <code>n - 1</code>. You are given a 2D integer array <code>lights</code> representing the street lamp(s) on the street. Each <code>lights[i] = [position<sub>i</sub>, range<sub>i</sub>]</code> indicates that there is a street lamp at position <code>position<sub>i</sub></code> that lights up the area from <code>[max(0, position<sub>i</sub> - range<sub>i</sub>), min(n - 1, position<sub>i</sub> + range<sub>i</sub>)]</code> (<strong>inclusive</strong>).</p>

<p>The <strong>brightness</strong> of a position <code>p</code> is defined as the number of street lamps that light up the position <code>p</code>. You are given a <strong>0-indexed</strong> integer array <code>requirement</code> of size <code>n</code> where <code>requirement[i]</code> is the minimum <strong>brightness</strong> of the <code>i<sup>th</sup></code> position on the street.</p>

<p>Return <em>the number of positions </em><code>i</code><em> on the street between </em><code>0</code><em> and </em><code>n - 1</code><em> that have a <strong>brightness</strong> </em><em>of <strong>at least</strong> </em><code>requirement[i]</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2237.Count%20Positions%20on%20Street%20With%20Required%20Brightness/images/screenshot-2022-04-11-at-22-24-43-diagramdrawio-diagramsnet.png" style="height: 150px; width: 579px;" />
<pre>
<strong>Input:</strong> n = 5, lights = [[0,1],[2,1],[3,2]], requirement = [0,2,1,4,1]
<strong>Output:</strong> 4
<strong>Explanation:</strong>
- The first street lamp lights up the area from [max(0, 0 - 1), min(n - 1, 0 + 1)] = [0, 1] (inclusive).
- The second street lamp lights up the area from [max(0, 2 - 1), min(n - 1, 2 + 1)] = [1, 3] (inclusive).
- The third street lamp lights up the area from [max(0, 3 - 2), min(n - 1, 3 + 2)] = [1, 4] (inclusive).

-   Position 0 is covered by the first street lamp. It is covered by 1 street lamp which is greater than requirement[0].
-   Position 1 is covered by the first, second, and third street lamps. It is covered by 3 street lamps which is greater than requirement[1].
-   Position 2 is covered by the second and third street lamps. It is covered by 2 street lamps which is greater than requirement[2].
-   Position 3 is covered by the second and third street lamps. It is covered by 2 street lamps which is less than requirement[3].
-   Position 4 is covered by the third street lamp. It is covered by 1 street lamp which is equal to requirement[4].

Positions 0, 1, 2, and 4 meet the requirement so we return 4.

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1, lights = [[0,1]], requirement = [2]
<strong>Output:</strong> 0
<strong>Explanation:</strong>
- The first street lamp lights up the area from [max(0, 0 - 1), min(n - 1, 0 + 1)] = [0, 0] (inclusive).
- Position 0 is covered by the first street lamp. It is covered by 1 street lamp which is less than requirement[0].
- We return 0 because no position meets their brightness requirement.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= lights.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= position<sub>i</sub> &lt; n</code></li>
	<li><code>0 &lt;= range<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>requirement.length == n</code></li>
	<li><code>0 &lt;= requirement[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Difference Array

To add a value $v$ to a continuous interval $[i, j]$ simultaneously, we can use a difference array.

We define an array $\textit{d}$ of length $n + 1$. For each streetlight, we calculate its left boundary $i = \max(0, p - r)$ and right boundary $j = \min(n - 1, p + r)$, then add $1$ to $\textit{d}[i]$ and subtract $1$ from $\textit{d}[j + 1]$.

Next, we perform a prefix sum operation on $\textit{d}$. For each position $i$, if the prefix sum of $\textit{d}[i]$ is greater than or equal to $\textit{requirement}[i]$, it means that the position meets the requirement, and we increment the answer by one.

Finally, return the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the number of streetlights.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def meetRequirement(
        self, n: int, lights: List[List[int]], requirement: List[int]
    ) -> int:
        d = [0] * (n + 1)
        for p, r in lights:
            i, j = max(0, p - r), min(n - 1, p + r)
            d[i] += 1
            d[j + 1] -= 1
        return sum(s >= r for s, r in zip(accumulate(d), requirement))
```

#### Java

```java
class Solution {
    public int meetRequirement(int n, int[][] lights, int[] requirement) {
        int[] d = new int[n + 1];
        for (int[] e : lights) {
            int i = Math.max(0, e[0] - e[1]);
            int j = Math.min(n - 1, e[0] + e[1]);
            ++d[i];
            --d[j + 1];
        }
        int s = 0;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            s += d[i];
            if (s >= requirement[i]) {
                ++ans;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int meetRequirement(int n, vector<vector<int>>& lights, vector<int>& requirement) {
        vector<int> d(n + 1);
        for (const auto& e : lights) {
            int i = max(0, e[0] - e[1]), j = min(n - 1, e[0] + e[1]);
            ++d[i];
            --d[j + 1];
        }
        int s = 0, ans = 0;
        for (int i = 0; i < n; ++i) {
            s += d[i];
            if (s >= requirement[i]) {
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func meetRequirement(n int, lights [][]int, requirement []int) (ans int) {
	d := make([]int, n+1)
	for _, e := range lights {
		i, j := max(0, e[0]-e[1]), min(n-1, e[0]+e[1])
		d[i]++
		d[j+1]--
	}
	s := 0
	for i, r := range requirement {
		s += d[i]
		if s >= r {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function meetRequirement(n: number, lights: number[][], requirement: number[]): number {
    const d: number[] = Array(n + 1).fill(0);
    for (const [p, r] of lights) {
        const [i, j] = [Math.max(0, p - r), Math.min(n - 1, p + r)];
        ++d[i];
        --d[j + 1];
    }
    let [ans, s] = [0, 0];
    for (let i = 0; i < n; ++i) {
        s += d[i];
        if (s >= requirement[i]) {
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
