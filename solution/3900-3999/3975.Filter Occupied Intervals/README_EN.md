---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3975.Filter%20Occupied%20Intervals/README_EN.md
tags:
    - Array
    - Sorting
---

<!-- problem:start -->

# [3975. Filter Occupied Intervals](https://leetcode.com/problems/filter-occupied-intervals)

[中文文档](/solution/3900-3999/3975.Filter%20Occupied%20Intervals/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D integer array <code>occupiedIntervals</code>, where <code>occupiedIntervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> represents a time interval during which you are occupied. Each interval starts at <code>start<sub>i</sub></code> and ends at <code>end<sub>i</sub></code>, <strong>inclusive</strong>. These intervals may <strong>overlap</strong>.</p>

<p>You are also given two integers <code>freeStart</code> and <code>freeEnd</code>, which define a free time interval from <code>freeStart</code> to <code>freeEnd</code>, inclusive.</p>

<p>Your task is to merge <strong>all</strong> occupied intervals that overlap or touch, then remove <strong>all</strong> integer points in the free interval from the merged occupied intervals.</p>

<p>Two intervals touch if the second interval starts <strong>immediately after</strong> the first one ends. For example, <code>[1, 1]</code> and <code>[2, 2]</code> touch and should be merged into <code>[1, 2]</code>.</p>

<p>Return the <strong>remaining</strong> occupied intervals in <strong>sorted</strong> order. The returned intervals must be <strong>non-overlapping</strong> and must contain the <strong>minimum</strong> number of intervals possible. If there are no remaining occupied points, return an empty list.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">occupiedIntervals = [[2,6],[4,8],[10,10],[10,12],[14,16]], freeStart = 7, freeEnd = 11</span></p>

<p><strong>Output:</strong> <span class="example-io">[[2,6],[12,12],[14,16]]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>After merging, the occupied intervals are <code>[2, 8]</code>, <code>[10, 12]</code>, and <code>[14, 16]</code>.</li>
	<li>Excluding the free interval <code>[7, 11]</code> results in <code>[2, 6]</code>, <code>[12, 12]</code>, and <code>[14, 16]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">occupiedIntervals = [[1,5],[2,3]], freeStart = 3, freeEnd = 8</span></p>

<p><strong>Output:</strong> <span class="example-io">[[1,2]]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>After merging, the occupied interval is <code>[1, 5]</code>.</li>
	<li>Excluding the free interval <code>[3, 8]</code> results in <code>[1, 2]</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= occupiedIntervals.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>occupiedIntervals[i].length == 2</code></li>
	<li><code>1 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= freeStart &lt;= freeEnd &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Merge Intervals

We first sort all occupied intervals by their left endpoints, and then traverse all intervals. If the left endpoint of the current interval is greater than the right endpoint of the last interval plus $1$, we add the current interval to the result. Otherwise, we merge the current interval with the last interval, and update the right endpoint of the last interval to the larger value of the current interval and the last interval.

Next, we traverse all occupied intervals. If the right endpoint of the current interval is less than the left endpoint of the free interval or the left endpoint of the current interval is greater than the right endpoint of the free interval, we add the current interval to the result. Otherwise, we check if the left endpoint of the current interval is less than the left endpoint of the free interval. If it is, we update the left endpoint of the current interval to the left endpoint of the free interval minus $1$, and add it to the result. Then, we check if the right endpoint of the current interval is greater than the right endpoint of the free interval. If it is, we update the right endpoint of the current interval to the right endpoint of the free interval plus $1$, and add it to the result.

Finally, we return the result.

The time complexity is $O(n \log n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array $\textit{occupiedIntervals}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def filterOccupiedIntervals(
        self, occupiedIntervals: List[List[int]], freeStart: int, freeEnd: int
    ) -> List[List[int]]:
        occupiedIntervals.sort(key=lambda x: x[0])
        busy = [occupiedIntervals[0]]
        for interval in occupiedIntervals[1:]:
            if busy[-1][1] + 1 < interval[0]:
                busy.append(interval)
            else:
                busy[-1][1] = max(busy[-1][1], interval[1])
        ans = []
        for interval in busy:
            if interval[1] < freeStart or freeEnd < interval[0]:
                ans.append(interval)
            else:
                if interval[0] < freeStart:
                    ans.append([interval[0], freeStart - 1])
                if interval[1] > freeEnd:
                    ans.append([freeEnd + 1, interval[1]])
        return ans
```

#### Java

```java
class Solution {
    public List<List<Integer>> filterOccupiedIntervals(
        int[][] occupiedIntervals, int freeStart, int freeEnd) {
        Arrays.sort(occupiedIntervals, (a, b) -> a[0] - b[0]);

        List<int[]> busy = new ArrayList<>();
        busy.add(occupiedIntervals[0]);

        for (int i = 1; i < occupiedIntervals.length; i++) {
            int[] cur = occupiedIntervals[i];
            int[] last = busy.get(busy.size() - 1);

            if (last[1] + 1 < cur[0]) {
                busy.add(cur);
            } else {
                last[1] = Math.max(last[1], cur[1]);
            }
        }

        List<List<Integer>> ans = new ArrayList<>();

        for (int[] interval : busy) {
            int s = interval[0], e = interval[1];

            if (e < freeStart || s > freeEnd) {
                ans.add(Arrays.asList(s, e));
            } else {
                if (s < freeStart) {
                    ans.add(Arrays.asList(s, freeStart - 1));
                }
                if (e > freeEnd) {
                    ans.add(Arrays.asList(freeEnd + 1, e));
                }
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
    vector<vector<int>> filterOccupiedIntervals(vector<vector<int>>& occupiedIntervals, int freeStart, int freeEnd) {
        sort(occupiedIntervals.begin(), occupiedIntervals.end());

        vector<vector<int>> busy;
        busy.push_back(occupiedIntervals[0]);

        for (int i = 1; i < occupiedIntervals.size(); i++) {
            auto& cur = occupiedIntervals[i];
            auto& last = busy.back();

            if (last[1] + 1 < cur[0]) {
                busy.push_back(cur);
            } else {
                last[1] = max(last[1], cur[1]);
            }
        }

        vector<vector<int>> ans;

        for (auto& it : busy) {
            int s = it[0], e = it[1];

            if (e < freeStart || s > freeEnd) {
                ans.push_back({s, e});
            } else {
                if (s < freeStart) {
                    ans.push_back({s, freeStart - 1});
                }
                if (e > freeEnd) {
                    ans.push_back({freeEnd + 1, e});
                }
            }
        }

        return ans;
    }
};
```

#### Go

```go
func filterOccupiedIntervals(occupiedIntervals [][]int, freeStart int, freeEnd int) [][]int {
    sort.Slice(occupiedIntervals, func(i, j int) bool {
        return occupiedIntervals[i][0] < occupiedIntervals[j][0]
    })

    busy := [][]int{occupiedIntervals[0]}

    for i := 1; i < len(occupiedIntervals); i++ {
        cur := occupiedIntervals[i]
        last := &busy[len(busy)-1]

        if (*last)[1]+1 < cur[0] {
            busy = append(busy, cur)
        } else {
            if cur[1] > (*last)[1] {
                (*last)[1] = cur[1]
            }
        }
    }

    ans := [][]int{}

    for _, it := range busy {
        s, e := it[0], it[1]

        if e < freeStart || s > freeEnd {
            ans = append(ans, []int{s, e})
        } else {
            if s < freeStart {
                ans = append(ans, []int{s, freeStart - 1})
            }
            if e > freeEnd {
                ans = append(ans, []int{freeEnd + 1, e})
            }
        }
    }

    return ans
}
```

#### TypeScript

```ts
function filterOccupiedIntervals(
    occupiedIntervals: number[][],
    freeStart: number,
    freeEnd: number,
): number[][] {
    occupiedIntervals.sort((a, b) => a[0] - b[0]);

    const busy: number[][] = [occupiedIntervals[0]];

    for (let i = 1; i < occupiedIntervals.length; i++) {
        const cur = occupiedIntervals[i];
        const last = busy[busy.length - 1];

        if (last[1] + 1 < cur[0]) {
            busy.push(cur);
        } else {
            last[1] = Math.max(last[1], cur[1]);
        }
    }

    const ans: number[][] = [];

    for (const [s, e] of busy) {
        if (e < freeStart || s > freeEnd) {
            ans.push([s, e]);
        } else {
            if (s < freeStart) {
                ans.push([s, freeStart - 1]);
            }
            if (e > freeEnd) {
                ans.push([freeEnd + 1, e]);
            }
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
