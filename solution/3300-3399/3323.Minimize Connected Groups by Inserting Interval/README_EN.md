---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3323.Minimize%20Connected%20Groups%20by%20Inserting%20Interval/README_EN.md
tags:
    - Array
    - Binary Search
    - Sorting
    - Sliding Window
---

<!-- problem:start -->

# [3323. Minimize Connected Groups by Inserting Interval 🔒](https://leetcode.com/problems/minimize-connected-groups-by-inserting-interval)

[中文文档](/solution/3300-3399/3323.Minimize%20Connected%20Groups%20by%20Inserting%20Interval/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D array <code>intervals</code>, where <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> represents the start and the end of interval <code>i</code>. You are also given an integer <code>k</code>.</p>

<p>You must add <strong>exactly one</strong> new interval <code>[start<sub>new</sub>, end<sub>new</sub>]</code> to the array such that:</p>

<ul>
	<li>The length of the new interval, <code>end<sub>new</sub> - start<sub>new</sub></code>, is at most <code>k</code>.</li>
	<li>After adding, the number of <strong>connected groups</strong> in <code>intervals</code> is <strong>minimized</strong>.</li>
</ul>

<p>A <strong>connected group</strong> of intervals is a maximal collection of intervals that, when considered together, cover a continuous range from the smallest point to the largest point with no gaps between them. Here are some examples:</p>

<ul>
	<li>A group of intervals <code>[[1, 2], [2, 5], [3, 3]]</code> is connected because together they cover the range from 1 to 5 without any gaps.</li>
	<li>However, a group of intervals <code>[[1, 2], [3, 4]]</code> is not connected because the segment <code>(2, 3)</code> is not covered.</li>
</ul>

<p>Return the <strong>minimum</strong> number of connected groups after adding <strong>exactly one</strong> new interval to the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">intervals = [[1,3],[5,6],[8,10]], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>After adding the interval <code>[3, 5]</code>, we have two connected groups: <code>[[1, 3], [3, 5], [5, 6]]</code> and <code>[[8, 10]]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">intervals = [[5,10],[1,1],[3,3]], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>After adding the interval <code>[1, 1]</code>, we have three connected groups: <code>[[1, 1], [1, 1]]</code>, <code>[[3, 3]]</code>, and <code>[[5, 10]]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= intervals.length &lt;= 10<sup>5</sup></code></li>
	<li><code>intervals[i] == [start<sub>i</sub>, end<sub>i</sub>]</code></li>
	<li><code>1 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Binary Search

First, we sort the given set of intervals $\textit{intervals}$ by their left endpoints, then merge all overlapping intervals to obtain a new set of intervals $\textit{merged}$.

We can then set the initial answer to the length of $\textit{merged}$.

Next, we enumerate each interval $[\_, e]$ in $\textit{merged}$. Using binary search, we find the first interval in $\textit{merged}$ whose left endpoint is greater than or equal to $e + k + 1$, and let its index be $j$. We can then update the answer as $\textit{ans} = \min(\textit{ans}, |\textit{merged}| - (j - i - 1))$.

Finally, we return the answer $\textit{ans}$.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the number of intervals.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minConnectedGroups(self, intervals: List[List[int]], k: int) -> int:
        intervals.sort()
        merged = [intervals[0]]
        for s, e in intervals[1:]:
            if merged[-1][1] < s:
                merged.append([s, e])
            else:
                merged[-1][1] = max(merged[-1][1], e)
        ans = len(merged)
        for i, (_, e) in enumerate(merged):
            j = bisect_left(merged, [e + k + 1, 0])
            ans = min(ans, len(merged) - (j - i - 1))
        return ans
```

#### Java

```java
class Solution {
    public int minConnectedGroups(int[][] intervals, int k) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> merged = new ArrayList<>();
        merged.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            int[] last = merged.get(merged.size() - 1);
            if (last[1] < interval[0]) {
                merged.add(interval);
            } else {
                last[1] = Math.max(last[1], interval[1]);
            }
        }

        int ans = merged.size();
        for (int i = 0; i < merged.size(); i++) {
            int[] interval = merged.get(i);
            int j = binarySearch(merged, interval[1] + k + 1);
            ans = Math.min(ans, merged.size() - (j - i - 1));
        }

        return ans;
    }

    private int binarySearch(List<int[]> nums, int x) {
        int l = 0, r = nums.size();
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums.get(mid)[0] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minConnectedGroups(vector<vector<int>>& intervals, int k) {
        sort(intervals.begin(), intervals.end());
        vector<vector<int>> merged;
        for (const auto& interval : intervals) {
            int s = interval[0], e = interval[1];
            if (merged.empty() || merged.back()[1] < s) {
                merged.emplace_back(interval);
            } else {
                merged.back()[1] = max(merged.back()[1], e);
            }
        }
        int ans = merged.size();
        for (int i = 0; i < merged.size(); ++i) {
            auto& interval = merged[i];
            int j = lower_bound(merged.begin(), merged.end(), vector<int>{interval[1] + k + 1, 0}) - merged.begin();
            ans = min(ans, (int) merged.size() - (j - i - 1));
        }
        return ans;
    }
};
```

#### Go

```go
func minConnectedGroups(intervals [][]int, k int) int {
	sort.Slice(intervals, func(i, j int) bool { return intervals[i][0] < intervals[j][0] })
	merged := [][]int{}
	for _, interval := range intervals {
		s, e := interval[0], interval[1]
		if len(merged) == 0 || merged[len(merged)-1][1] < s {
			merged = append(merged, interval)
		} else {
			merged[len(merged)-1][1] = max(merged[len(merged)-1][1], e)
		}
	}
	ans := len(merged)
	for i, interval := range merged {
		j := sort.Search(len(merged), func(j int) bool { return merged[j][0] >= interval[1]+k+1 })
		ans = min(ans, len(merged)-(j-i-1))
	}
	return ans
}
```

#### TypeScript

```ts
function minConnectedGroups(intervals: number[][], k: number): number {
    intervals.sort((a, b) => a[0] - b[0]);
    const merged: number[][] = [];
    for (const interval of intervals) {
        const [s, e] = interval;
        if (merged.length === 0 || merged.at(-1)![1] < s) {
            merged.push(interval);
        } else {
            merged.at(-1)![1] = Math.max(merged.at(-1)![1], e);
        }
    }
    const search = (x: number): number => {
        let [l, r] = [0, merged.length];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (merged[mid][0] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    let ans = merged.length;
    for (let i = 0; i < merged.length; ++i) {
        const j = search(merged[i][1] + k + 1);
        ans = Math.min(ans, merged.length - (j - i - 1));
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
