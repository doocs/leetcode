---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2345.Finding%20the%20Number%20of%20Visible%20Mountains/README_EN.md
tags:
    - Stack
    - Array
    - Sorting
    - Monotonic Stack
---

<!-- problem:start -->

# [2345. Finding the Number of Visible Mountains 🔒](https://leetcode.com/problems/finding-the-number-of-visible-mountains)

[中文文档](/solution/2300-2399/2345.Finding%20the%20Number%20of%20Visible%20Mountains/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> 2D integer array <code>peaks</code> where <code>peaks[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> states that mountain <code>i</code> has a peak at coordinates <code>(x<sub>i</sub>, y<sub>i</sub>)</code>. A mountain can be described as a right-angled isosceles triangle, with its base along the <code>x</code>-axis and a right angle at its peak. More formally, the <strong>gradients</strong> of ascending and descending the mountain are <code>1</code> and <code>-1</code> respectively.</p>

<p>A mountain is considered <strong>visible</strong> if its peak does not lie within another mountain (including the border of other mountains).</p>

<p>Return <em>the number of visible mountains</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2345.Finding%20the%20Number%20of%20Visible%20Mountains/images/ex1.png" style="width: 402px; height: 210px;" />
<pre>
<strong>Input:</strong> peaks = [[2,2],[6,3],[5,4]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The diagram above shows the mountains.
- Mountain 0 is visible since its peak does not lie within another mountain or its sides.
- Mountain 1 is not visible since its peak lies within the side of mountain 2.
- Mountain 2 is visible since its peak does not lie within another mountain or its sides.
There are 2 mountains that are visible.</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2345.Finding%20the%20Number%20of%20Visible%20Mountains/images/ex2new1.png" style="width: 300px; height: 180px;" />
<pre>
<strong>Input:</strong> peaks = [[1,3],[1,3]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The diagram above shows the mountains (they completely overlap).
Both mountains are not visible since their peaks lie within each other.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= peaks.length &lt;= 10<sup>5</sup></code></li>
	<li><code>peaks[i].length == 2</code></li>
	<li><code>1 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Interval Sorting + Traversal

We first convert each mountain $(x, y)$ into a horizontal interval $(x - y, x + y)$, then sort the intervals by left endpoint in ascending order and right endpoint in descending order.

Next, we initialize the right endpoint of the current interval as $-\infty$. We traverse each mountain. If the right endpoint of the current mountain is less than or equal to the right endpoint of the current interval, we skip this mountain. Otherwise, we update the right endpoint of the current interval to the right endpoint of the current mountain. If the interval of the current mountain appears only once, we increment the answer.

Finally, we return the answer.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the number of mountains.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def visibleMountains(self, peaks: List[List[int]]) -> int:
        arr = [(x - y, x + y) for x, y in peaks]
        cnt = Counter(arr)
        arr.sort(key=lambda x: (x[0], -x[1]))
        ans, cur = 0, -inf
        for l, r in arr:
            if r <= cur:
                continue
            cur = r
            if cnt[(l, r)] == 1:
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int visibleMountains(int[][] peaks) {
        int n = peaks.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            int x = peaks[i][0], y = peaks[i][1];
            arr[i] = new int[] {x - y, x + y};
        }
        Arrays.sort(arr, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int ans = 0;
        int cur = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            int l = arr[i][0], r = arr[i][1];
            if (r <= cur) {
                continue;
            }
            cur = r;
            if (!(i < n - 1 && arr[i][0] == arr[i + 1][0] && arr[i][1] == arr[i + 1][1])) {
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
    int visibleMountains(vector<vector<int>>& peaks) {
        vector<pair<int, int>> arr;
        for (auto& e : peaks) {
            int x = e[0], y = e[1];
            arr.emplace_back(x - y, -(x + y));
        }
        sort(arr.begin(), arr.end());
        int n = arr.size();
        int ans = 0, cur = INT_MIN;
        for (int i = 0; i < n; ++i) {
            int l = arr[i].first, r = -arr[i].second;
            if (r <= cur) {
                continue;
            }
            cur = r;
            ans += i == n - 1 || (i < n - 1 && arr[i] != arr[i + 1]);
        }
        return ans;
    }
};
```

#### Go

```go
func visibleMountains(peaks [][]int) (ans int) {
	n := len(peaks)
	type pair struct{ l, r int }
	arr := make([]pair, n)
	for _, p := range peaks {
		x, y := p[0], p[1]
		arr = append(arr, pair{x - y, x + y})
	}
	sort.Slice(arr, func(i, j int) bool { return arr[i].l < arr[j].l || (arr[i].l == arr[j].l && arr[i].r > arr[j].r) })
	cur := math.MinInt32
	for i, e := range arr {
		l, r := e.l, e.r
		if r <= cur {
			continue
		}
		cur = r
		if !(i < n-1 && l == arr[i+1].l && r == arr[i+1].r) {
			ans++
		}
	}
	return
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
