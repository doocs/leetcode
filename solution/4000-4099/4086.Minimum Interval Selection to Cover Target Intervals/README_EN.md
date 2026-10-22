---
comments: true
difficulty: Hard
---

<!-- problem:start -->

# [4086. Minimum Interval Selection to Cover Target Intervals](https://leetcode.com/problems/minimum-interval-selection-to-cover-target-intervals)

[中文文档](/solution/4000-4099/4086.Minimum%20Interval%20Selection%20to%20Cover%20Target%20Intervals/README.md)

## Description

<!-- description:start -->

<p>You are given two 2D integer arrays, <code>availableIntervals</code> and <code>targetIntervals</code>, each representing a list of <strong>closed intervals</strong>.</p>

<p>Select a <strong><span data-keyword="subset">subset</span></strong> of intervals from <code>availableIntervals</code> so that <strong>every real point</strong> contained in any interval in <code>targetIntervals</code> belongs to at least one selected interval.</p>

<p>Points outside the target intervals do not need to be covered. Selected intervals may overlap or extend beyond the target intervals.</p>

<p>Return the <strong>minimum</strong> number of intervals you need to select. If it is impossible to cover all the target intervals, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">availableIntervals = [[1,4],[1,7],[3,9],[5,10],[10,15]], targetIntervals = [[1,3],[2,5],[7,8],[10,13],[11,15],[12,13]]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The target intervals cover the ranges <code>[1, 5]</code>, <code>[7, 8]</code>, and <code>[10, 15]</code>. By selecting <code>[1, 4]</code>, <code>[3, 9]</code>, and <code>[10, 15]</code> from <code>availableIntervals</code>, all target intervals can be fully covered with three intervals.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">availableIntervals = [[4,6],[1,4],[2,5]], targetIntervals = [[3,4],[2,5],[6,7]]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>It is impossible to select intervals from <code>availableIntervals</code> to cover all the intervals in <code>targetIntervals</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">availableIntervals = [[1,2],[3,4]], targetIntervals = [[1,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>Selecting both available intervals leaves every real point strictly between 2 and 3 uncovered. For example, 2.5 belongs to the target interval but to neither available interval.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= availableIntervals.length, targetIntervals.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>availableIntervals[i].length == targetIntervals[j].length == 2</code></li>
	<li><code>1 &lt;= availableIntervals[i][0] &lt;= availableIntervals[i][1] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= targetIntervals[j][0] &lt;= targetIntervals[j][1] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Greedy

<!-- thinking:start -->

> **Thinking**
>
> Both arrays can contain $5 \times 10^4$ intervals, so enumerating subsets cannot check that every real point of every target is covered. Coordinates only go up to $10^5$, and a difference array could mark the union of the targets, but points in the gaps between those pieces have no coverage requirement.
>
> Targets overlap, and the gaps separate them. Once a left-to-right sweep records how far the selected intervals already reach, the next choice depends only on the next real point that must still lie in some selected interval.
>
> After both arrays are sorted by left endpoint, if coverage already reaches $\textit{cur}$, the next required point of target $[l, r]$ is $\max(l, \textit{cur})$. Extend from $\textit{cur}$ when that point lies inside the target. When $l$ is farther right, the open interval between them belongs to no target, so those points do not need to be covered. Every available interval that contains the point has a left endpoint no larger than the point, and the one with the farthest right endpoint can replace any other choice without increasing how many intervals are still needed.
>
> The required point only moves right, so one pointer can scan the available intervals by left endpoint. An interval that does not extend past the covered position cannot fill the real points that remain inside the target.

<!-- thinking:end -->

Sort $\textit{availableIntervals}$ and $\textit{targetIntervals}$ by left endpoint. $\textit{cur}$ is the rightmost point already covered and starts at $0$. Pointer $i$ scans the available intervals from left to right.

Process each target $[l, r]$ in that order. The target is already covered when $\textit{cur} \ge r$. Otherwise set $\textit{uncover} = \max(l, \textit{cur})$, and let $\textit{nxt}$ be the maximum right endpoint among available intervals whose left endpoint is at most $\textit{uncover}$. If $\textit{nxt} < \textit{uncover}$, no available interval contains that point. If $\textit{nxt} = \textit{cur}$, coverage cannot move any farther right. Both cases return $-1$. Otherwise select the interval with that farthest right endpoint, set $\textit{cur} = \textit{nxt}$, and add $1$ to the answer, until $\textit{cur} \ge r$.

Closed intervals form one continuous cover only when they meet at an endpoint. If one selected interval ends where the next begins, no real point between them is missing. If the next left endpoint is at least $1$ larger than the previous right endpoint and that gap lies inside a target, the real points in the gap are uncovered and the search returns $-1$.

Each interval is scanned once. The time complexity is $O(n \log n + m \log m)$ and the space complexity is $O(\log n + \log m)$, where $n$ and $m$ are the numbers of available intervals and target intervals.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minSelection(
        self, availableIntervals: list[list[int]], targetIntervals: list[list[int]]
    ) -> int:
        availableIntervals.sort(key=lambda x: x[0])
        targetIntervals.sort(key=lambda x: x[0])
        ans = cur = 0
        i = 0
        for l, r in targetIntervals:
            while cur < r:
                uncover = max(l, cur)
                nxt = cur
                while (
                    i < len(availableIntervals) and availableIntervals[i][0] <= uncover
                ):
                    nxt = max(nxt, availableIntervals[i][1])
                    i += 1
                if nxt < uncover or nxt == cur:
                    return -1
                cur = nxt
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int minSelection(int[][] availableIntervals, int[][] targetIntervals) {
        Arrays.sort(availableIntervals, (a, b) -> a[0] - b[0]);
        Arrays.sort(targetIntervals, (a, b) -> a[0] - b[0]);
        int ans = 0, cur = 0, i = 0;
        for (int[] t : targetIntervals) {
            int l = t[0], r = t[1];
            while (cur < r) {
                int uncover = Math.max(l, cur);
                int nxt = cur;
                while (i < availableIntervals.length && availableIntervals[i][0] <= uncover) {
                    nxt = Math.max(nxt, availableIntervals[i][1]);
                    i++;
                }
                if (nxt < uncover || nxt == cur) {
                    return -1;
                }
                cur = nxt;
                ans++;
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
    int minSelection(vector<vector<int>>& availableIntervals, vector<vector<int>>& targetIntervals) {
        sort(availableIntervals.begin(), availableIntervals.end(), [](auto& a, auto& b) { return a[0] < b[0]; });
        sort(targetIntervals.begin(), targetIntervals.end(), [](auto& a, auto& b) { return a[0] < b[0]; });
        int ans = 0, cur = 0, i = 0;
        for (auto& t : targetIntervals) {
            int l = t[0], r = t[1];
            while (cur < r) {
                int uncover = max(l, cur);
                int nxt = cur;
                while (i < (int) availableIntervals.size() && availableIntervals[i][0] <= uncover) {
                    nxt = max(nxt, availableIntervals[i][1]);
                    i++;
                }
                if (nxt < uncover || nxt == cur) {
                    return -1;
                }
                cur = nxt;
                ans++;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minSelection(availableIntervals [][]int, targetIntervals [][]int) int {
	sort.Slice(availableIntervals, func(a, b int) bool { return availableIntervals[a][0] < availableIntervals[b][0] })
	sort.Slice(targetIntervals, func(a, b int) bool { return targetIntervals[a][0] < targetIntervals[b][0] })
	ans, cur, i := 0, 0, 0
	for _, t := range targetIntervals {
		l, r := t[0], t[1]
		for cur < r {
			uncover := max(l, cur)
			nxt := cur
			for i < len(availableIntervals) && availableIntervals[i][0] <= uncover {
				nxt = max(nxt, availableIntervals[i][1])
				i++
			}
			if nxt < uncover || nxt == cur {
				return -1
			}
			cur = nxt
			ans++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function minSelection(availableIntervals: number[][], targetIntervals: number[][]): number {
    availableIntervals.sort((a, b) => a[0] - b[0]);
    targetIntervals.sort((a, b) => a[0] - b[0]);
    let ans = 0,
        cur = 0,
        i = 0;
    for (const [l, r] of targetIntervals) {
        while (cur < r) {
            const uncover = Math.max(l, cur);
            let nxt = cur;
            while (i < availableIntervals.length && availableIntervals[i][0] <= uncover) {
                nxt = Math.max(nxt, availableIntervals[i][1]);
                i++;
            }
            if (nxt < uncover || nxt === cur) {
                return -1;
            }
            cur = nxt;
            ans++;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
