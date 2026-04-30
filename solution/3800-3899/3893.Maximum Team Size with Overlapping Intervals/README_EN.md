---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3893.Maximum%20Team%20Size%20with%20Overlapping%20Intervals/README_EN.md
---

<!-- problem:start -->

# [3893. Maximum Team Size with Overlapping Intervals 🔒](https://leetcode.com/problems/maximum-team-size-with-overlapping-intervals)

[中文文档](/solution/3800-3899/3893.Maximum%20Team%20Size%20with%20Overlapping%20Intervals/README.md)

## Description

<!-- description:start -->

<p data-end="767" data-start="694">You are given two integer arrays <code>startTime</code> and <code>endTime</code> of length <code>n</code>.</p>

<ul>
	<li><code>startTime[i]</code> represents the start time of the <code>i<sup>th</sup></code> employee.</li>
	<li><code>endTime[i]</code> represents the end time of the <code>i<sup>th</sup></code> employee.</li>
</ul>

<p>Two employees <code>i</code> and <code>j</code> can interact if their time intervals <strong>overlap</strong>. Two intervals are considered overlapping if they share <strong>at least one</strong> common time point.</p>

<p>A team is <strong>valid</strong> if there exists <strong>at least one</strong> employee in the team who can interact with every other member of the team.</p>

<p>Return an integer denoting the <strong>maximum</strong> possible size of such a team.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">startTime = [1,2,3], endTime = [4,5,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>i = 0</code> with interval <code>[1, 4]</code>.</li>
	<li>It overlaps with <code>i = 1</code> having interval <code>[2, 5]</code> and <code>i = 2</code> having interval <code>[3, 6]</code>.</li>
	<li>Thus, index 0 can interact with all other indices, so the team size is 3.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">startTime = [2,5,8], endTime = [3,7,9]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>i = 0</code>, interval <code>[2, 3]</code> does not overlap with <code>[5, 7]</code> or <code>[8, 9]</code>.</li>
	<li>For <code>i = 1</code>, interval <code>[5, 7]</code> does not overlap with <code>[2, 3]</code> or <code>[8, 9]</code>.</li>
	<li>For <code>i = 2</code>, interval <code>[8, 9]</code> does not overlap with <code>[2, 3]</code> or <code>[5, 7]</code>.</li>
	<li>Thus, no index can interact with others, so the maximum team size is 1.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">startTime = [3,4,6], endTime = [8,5,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>i = 0</code> with interval <code>[3, 8]</code>.</li>
	<li>It overlaps with <code>i = 1</code> having interval <code>[4, 5]</code> and <code>i = 2</code> having interval <code>[6, 7]</code>.</li>
	<li>Thus, index 0 can interact with all other indices, so the team size is 3.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == startTime.length == endTime.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= startTime[i] &lt;= endTime[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search

We first combine each employee's start and end times into an interval array, $\textit{intervals}$, and sort all start times and end times separately.

For each employee $i$, we use binary search to compute how many employees have end times not earlier than employee $i$'s start time, and how many employees have start times not later than employee $i$'s end time. The difference between these two counts is the number of employees whose intervals overlap with employee $i$. We iterate through all employees, compute the overlap count for each one, and take the maximum as the answer.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$, where $n$ is the number of employees.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumTeamSize(self, startTime: list[int], endTime: list[int]) -> int:
        intervals = list(zip(startTime, endTime))
        startTime.sort()
        endTime.sort()
        ans = 0
        for l, r in intervals:
            i = bisect_right(endTime, l - 1)
            j = bisect_right(startTime, r)
            ans = max(ans, j - i)
        return ans
```

#### Java

```java
class Solution {
    public int maximumTeamSize(int[] startTime, int[] endTime) {
        int n = startTime.length;
        int[][] intervals = new int[n][2];
        for (int i = 0; i < n; i++) {
            intervals[i][0] = startTime[i];
            intervals[i][1] = endTime[i];
        }
        Arrays.sort(startTime);
        Arrays.sort(endTime);

        int ans = 0;
        for (int[] it : intervals) {
            int l = it[0], r = it[1];

            int i = search(endTime, l - 1);
            int j = search(startTime, r);

            ans = Math.max(ans, j - i);
        }

        return ans;
    }

    private int search(int[] arr, int x) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] > x) {
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
    int maximumTeamSize(vector<int>& startTime, vector<int>& endTime) {
        int n = startTime.size();
        vector<pair<int, int>> intervals(n);
        for (int i = 0; i < n; i++) {
            intervals[i] = {startTime[i], endTime[i]};
        }

        sort(startTime.begin(), startTime.end());
        sort(endTime.begin(), endTime.end());

        int ans = 0;
        for (const auto& [l, r] : intervals) {
            int i = upper_bound(endTime.begin(), endTime.end(), l - 1) - endTime.begin();
            int j = upper_bound(startTime.begin(), startTime.end(), r) - startTime.begin();
            ans = max(ans, j - i);
        }

        return ans;
    }
};

```

#### Go

```go
func maximumTeamSize(startTime []int, endTime []int) int {
	n := len(startTime)
	intervals := make([][2]int, n)
	for i := 0; i < n; i++ {
		intervals[i] = [2]int{startTime[i], endTime[i]}
	}

	sort.Ints(startTime)
	sort.Ints(endTime)

	ans := 0
	for _, it := range intervals {
		l, r := it[0], it[1]

		i := sort.Search(len(endTime), func(k int) bool { return endTime[k] > l-1 })
		j := sort.Search(len(startTime), func(k int) bool { return startTime[k] > r })

		ans = max(ans, j-i)
	}

	return ans
}
```

#### TypeScript

```ts
function maximumTeamSize(startTime: number[], endTime: number[]): number {
    const n = startTime.length;
    const intervals: [number, number][] = Array.from({ length: n }, (_, i) => [
        startTime[i],
        endTime[i],
    ]);

    startTime.sort((a, b) => a - b);
    endTime.sort((a, b) => a - b);

    let ans = 0;
    for (const [l, r] of intervals) {
        const i = search(endTime, l - 1);
        const j = search(startTime, r);

        ans = Math.max(ans, j - i);
    }

    return ans;
}

function search(arr: number[], x: number): number {
    let l = 0;
    let r = arr.length;
    while (l < r) {
        const mid = (l + r) >> 1;
        if (arr[mid] > x) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
