---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0436.Find%20Right%20Interval/README_EN.md
tags:
    - Array
    - Binary Search
    - Sorting
---

<!-- problem:start -->

# [436. Find Right Interval](https://leetcode.com/problems/find-right-interval)

[中文文档](/solution/0400-0499/0436.Find%20Right%20Interval/README.md)

## Description

<!-- description:start -->

<p>You are given an array of <code>intervals</code>, where <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> and each <code>start<sub>i</sub></code> is <strong>unique</strong>.</p>

<p>The <strong>right interval</strong> for an interval <code>i</code> is an interval <code>j</code> such that <code>start<sub>j</sub> &gt;= end<sub>i</sub></code> and <code>start<sub>j</sub></code> is <strong>minimized</strong>. Note that <code>i</code> may equal <code>j</code>.</p>

<p>Return <em>an array of <strong>right interval</strong> indices for each interval <code>i</code></em>. If no <strong>right interval</strong> exists for interval <code>i</code>, then put <code>-1</code> at index <code>i</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[1,2]]
<strong>Output:</strong> [-1]
<strong>Explanation:</strong> There is only one interval in the collection, so it outputs -1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[3,4],[2,3],[1,2]]
<strong>Output:</strong> [-1,0,1]
<strong>Explanation:</strong> There is no right interval for [3,4].
The right interval for [2,3] is [3,4] since start<sub>0</sub> = 3 is the smallest start that is &gt;= end<sub>1</sub> = 3.
The right interval for [1,2] is [2,3] since start<sub>1</sub> = 2 is the smallest start that is &gt;= end<sub>2</sub> = 2.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[1,4],[2,3],[3,4]]
<strong>Output:</strong> [-1,2,-1]
<strong>Explanation:</strong> There is no right interval for [1,4] and [3,4].
The right interval for [2,3] is [3,4] since start<sub>2</sub> = 3 is the smallest start that is &gt;= end<sub>1</sub> = 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= intervals.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>intervals[i].length == 2</code></li>
	<li><code>-10<sup>6</sup> &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
	<li>The start point of each interval is <strong>unique</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Binary Search

We can store the start point and index of each interval into an array `arr`, and sort it by the start point. Then we iterate through the interval array, for each interval `[_, ed]`, we can use binary search to find the first interval whose start point is greater than or equal to `ed`, which is its right-side interval. If found, we store its index into the answer array, otherwise, we store `-1`.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Where $n$ is the length of the intervals.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findRightInterval(self, intervals: List[List[int]]) -> List[int]:
        n = len(intervals)
        ans = [-1] * n
        arr = sorted((st, i) for i, (st, _) in enumerate(intervals))
        for i, (_, ed) in enumerate(intervals):
            j = bisect_left(arr, (ed, -inf))
            if j < n:
                ans[i] = arr[j][1]
        return ans
```

#### Java

```java
class Solution {
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[][] arr = new int[n][0];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[] {intervals[i][0], i};
        }
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int j = search(arr, intervals[i][1]);
            ans[i] = j < n ? arr[j][1] : -1;
        }
        return ans;
    }

    private int search(int[][] arr, int x) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (arr[mid][0] >= x) {
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
    vector<int> findRightInterval(vector<vector<int>>& intervals) {
        int n = intervals.size();
        vector<pair<int, int>> arr;
        for (int i = 0; i < n; ++i) {
            arr.emplace_back(intervals[i][0], i);
        }
        sort(arr.begin(), arr.end());
        vector<int> ans;
        for (auto& e : intervals) {
            int j = lower_bound(arr.begin(), arr.end(), make_pair(e[1], -1)) - arr.begin();
            ans.push_back(j == n ? -1 : arr[j].second);
        }
        return ans;
    }
};
```

#### Go

```go
func findRightInterval(intervals [][]int) (ans []int) {
	arr := make([][2]int, len(intervals))
	for i, v := range intervals {
		arr[i] = [2]int{v[0], i}
	}
	sort.Slice(arr, func(i, j int) bool { return arr[i][0] < arr[j][0] })
	for _, e := range intervals {
		j := sort.Search(len(arr), func(i int) bool { return arr[i][0] >= e[1] })
		if j < len(arr) {
			ans = append(ans, arr[j][1])
		} else {
			ans = append(ans, -1)
		}
	}
	return
}
```

#### TypeScript

```ts
function findRightInterval(intervals: number[][]): number[] {
    const n = intervals.length;
    const arr: number[][] = Array.from({ length: n }, (_, i) => [intervals[i][0], i]);
    arr.sort((a, b) => a[0] - b[0]);
    return intervals.map(([_, ed]) => {
        let [l, r] = [0, n];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (arr[mid][0] >= ed) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l < n ? arr[l][1] : -1;
    });
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
