---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1751.Maximum%20Number%20of%20Events%20That%20Can%20Be%20Attended%20II/README_EN.md
rating: 2040
source: Biweekly Contest 45 Q4
tags:
    - Array
    - Binary Search
    - Dynamic Programming
    - Sorting
---

<!-- problem:start -->

# [1751. Maximum Number of Events That Can Be Attended II](https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended-ii)

[中文文档](/solution/1700-1799/1751.Maximum%20Number%20of%20Events%20That%20Can%20Be%20Attended%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an array of <code>events</code> where <code>events[i] = [startDay<sub>i</sub>, endDay<sub>i</sub>, value<sub>i</sub>]</code>. The <code>i<sup>th</sup></code> event starts at <code>startDay<sub>i</sub></code><sub> </sub>and ends at <code>endDay<sub>i</sub></code>, and if you attend this event, you will receive a value of <code>value<sub>i</sub></code>. You are also given an integer <code>k</code> which represents the maximum number of events you can attend.</p>

<p>You can only attend one event at a time. If you choose to attend an event, you must attend the <strong>entire</strong> event. Note that the end day is <strong>inclusive</strong>: that is, you cannot attend two events where one of them starts and the other ends on the same day.</p>

<p>Return <em>the <strong>maximum sum</strong> of values that you can receive by attending events.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1751.Maximum%20Number%20of%20Events%20That%20Can%20Be%20Attended%20II/images/screenshot-2021-01-11-at-60048-pm.png" style="width: 400px; height: 103px;" /></p>

<pre>
<strong>Input:</strong> events = [[1,2,4],[3,4,3],[2,3,1]], k = 2
<strong>Output:</strong> 7
<strong>Explanation: </strong>Choose the green events, 0 and 1 (0-indexed) for a total value of 4 + 3 = 7.</pre>

<p><strong class="example">Example 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1751.Maximum%20Number%20of%20Events%20That%20Can%20Be%20Attended%20II/images/screenshot-2021-01-11-at-60150-pm.png" style="width: 400px; height: 103px;" /></p>

<pre>
<strong>Input:</strong> events = [[1,2,4],[3,4,3],[2,3,10]], k = 2
<strong>Output:</strong> 10
<strong>Explanation:</strong> Choose event 2 for a total value of 10.
Notice that you cannot attend any other event as they overlap, and that you do <strong>not</strong> have to attend k events.</pre>

<p><strong class="example">Example 3:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1751.Maximum%20Number%20of%20Events%20That%20Can%20Be%20Attended%20II/images/screenshot-2021-01-11-at-60703-pm.png" style="width: 400px; height: 126px;" /></strong></p>

<pre>
<strong>Input:</strong> events = [[1,1,1],[2,2,2],[3,3,3],[4,4,4]], k = 3
<strong>Output:</strong> 9
<strong>Explanation:</strong> Although the events do not overlap, you can only attend 3 events. Pick the highest valued three.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= events.length</code></li>
	<li><code>1 &lt;= k * events.length &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= startDay<sub>i</sub> &lt;= endDay<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= value<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoization + Binary Search

First, we sort the events by their start time in ascending order. Then, we define a function $\text{dfs}(i, k)$, which represents the maximum total value achievable by attending at most $k$ events starting from the $i$-th event. The answer is $\text{dfs}(0, k)$.

The calculation process of the function $\text{dfs}(i, k)$ is as follows:

If we do not attend the $i$-th event, the maximum value is $\text{dfs}(i + 1, k)$. If we attend the $i$-th event, we can use binary search to find the first event whose start time is greater than the end time of the $i$-th event, denoted as $j$. Then, the maximum value is $\text{dfs}(j, k - 1) + \text{value}[i]$. We take the maximum of the two options:

$$
\text{dfs}(i, k) = \max(\text{dfs}(i + 1, k), \text{dfs}(j, k - 1) + \text{value}[i])
$$

Here, $j$ is the index of the first event whose start time is greater than the end time of the $i$-th event, which can be found using binary search.

Since the calculation of $\text{dfs}(i, k)$ involves calls to $\text{dfs}(i + 1, k)$ and $\text{dfs}(j, k - 1)$, we can use memoization to store the computed values and avoid redundant calculations.

The time complexity is $O(n \times \log n + n \times k)$, and the space complexity is $O(n \times k)$, where $n$ is the number of events.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxValue(self, events: List[List[int]], k: int) -> int:
        @cache
        def dfs(i: int, k: int) -> int:
            if i >= len(events):
                return 0
            _, ed, val = events[i]
            ans = dfs(i + 1, k)
            if k:
                j = bisect_right(events, ed, lo=i + 1, key=lambda x: x[0])
                ans = max(ans, dfs(j, k - 1) + val)
            return ans

        events.sort()
        return dfs(0, k)
```

#### Java

```java
class Solution {
    private int[][] events;
    private int[][] f;
    private int n;

    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        this.events = events;
        n = events.length;
        f = new int[n][k + 1];
        return dfs(0, k);
    }

    private int dfs(int i, int k) {
        if (i >= n || k <= 0) {
            return 0;
        }
        if (f[i][k] != 0) {
            return f[i][k];
        }
        int j = search(events, events[i][1], i + 1);
        int ans = Math.max(dfs(i + 1, k), dfs(j, k - 1) + events[i][2]);
        return f[i][k] = ans;
    }

    private int search(int[][] events, int x, int lo) {
        int l = lo, r = n;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (events[mid][0] > x) {
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
    int maxValue(vector<vector<int>>& events, int k) {
        ranges::sort(events);
        int n = events.size();
        int f[n][k + 1];
        memset(f, 0, sizeof(f));
        auto dfs = [&](this auto&& dfs, int i, int k) -> int {
            if (i >= n || k <= 0) {
                return 0;
            }
            if (f[i][k] > 0) {
                return f[i][k];
            }

            int ed = events[i][1], val = events[i][2];
            vector<int> t = {ed};

            int p = upper_bound(events.begin() + i + 1, events.end(), t,
                        [](const auto& a, const auto& b) { return a[0] < b[0]; })
                - events.begin();

            f[i][k] = max(dfs(i + 1, k), dfs(p, k - 1) + val);
            return f[i][k];
        };

        return dfs(0, k);
    }
};
```

#### Go

```go
func maxValue(events [][]int, k int) int {
	sort.Slice(events, func(i, j int) bool { return events[i][0] < events[j][0] })
	n := len(events)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, k+1)
	}
	var dfs func(i, k int) int
	dfs = func(i, k int) int {
		if i >= n || k <= 0 {
			return 0
		}
		if f[i][k] > 0 {
			return f[i][k]
		}
		j := sort.Search(n, func(h int) bool { return events[h][0] > events[i][1] })
		ans := max(dfs(i+1, k), dfs(j, k-1)+events[i][2])
		f[i][k] = ans
		return ans
	}
	return dfs(0, k)
}
```

#### TypeScript

```ts
function maxValue(events: number[][], k: number): number {
    events.sort((a, b) => a[0] - b[0]);
    const n = events.length;
    const f: number[][] = Array.from({ length: n }, () => Array(k + 1).fill(0));

    const dfs = (i: number, k: number): number => {
        if (i >= n || k <= 0) {
            return 0;
        }
        if (f[i][k] > 0) {
            return f[i][k];
        }

        const ed = events[i][1],
            val = events[i][2];

        let left = i + 1,
            right = n;
        while (left < right) {
            const mid = (left + right) >> 1;
            if (events[mid][0] > ed) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        const p = left;

        f[i][k] = Math.max(dfs(i + 1, k), dfs(p, k - 1) + val);
        return f[i][k];
    };

    return dfs(0, k);
}
```

#### Rust

```rust
impl Solution {
    pub fn max_value(mut events: Vec<Vec<i32>>, k: i32) -> i32 {
        events.sort_by_key(|e| e[0]);
        let n = events.len();
        let mut f = vec![vec![0; (k + 1) as usize]; n];

        fn dfs(i: usize, k: i32, events: &Vec<Vec<i32>>, f: &mut Vec<Vec<i32>>, n: usize) -> i32 {
            if i >= n || k <= 0 {
                return 0;
            }
            if f[i][k as usize] != 0 {
                return f[i][k as usize];
            }
            let j = search(events, events[i][1], i + 1, n);
            let ans = dfs(i + 1, k, events, f, n).max(dfs(j, k - 1, events, f, n) + events[i][2]);
            f[i][k as usize] = ans;
            ans
        }

        fn search(events: &Vec<Vec<i32>>, x: i32, lo: usize, n: usize) -> usize {
            let mut l = lo;
            let mut r = n;
            while l < r {
                let mid = (l + r) / 2;
                if events[mid][0] > x {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            l
        }

        dfs(0, k, &events, &mut f, n)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming + Binary Search

We can convert the memoization approach in Solution 1 to dynamic programming.

First, sort the events, this time by end time in ascending order. Then define $f[i][j]$ as the maximum total value by attending at most $j$ events among the first $i$ events. The answer is $f[n][k]$.

For the $i$-th event, we can choose to attend it or not. If we do not attend, the maximum value is $f[i][j]$. If we attend, we can use binary search to find the last event whose end time is less than the start time of the $i$-th event, denoted as $h$. Then the maximum value is $f[h + 1][j - 1] + \text{value}[i]$. We take the maximum of the two options:

$$
f[i + 1][j] = \max(f[i][j], f[h + 1][j - 1] + \text{value}[i])
$$

Here, $h$ is the last event whose end time is less than the start time of the $i$-th event, which can be found by binary search.

The time complexity is $O(n \times \log n + n \times k)$, and the space complexity is $O(n \times k)$, where $n$ is the number of events.

Related problems:

- [1235. Maximum Profit in Job Scheduling](https://github.com/doocs/leetcode/blob/main/solution/1200-1299/1235.Maximum%20Profit%20in%20Job%20Scheduling/README_EN.md)
- [2008. Maximum Earnings From Taxi](https://github.com/doocs/leetcode/blob/main/solution/2000-2099/2008.Maximum%20Earnings%20From%20Taxi/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxValue(self, events: List[List[int]], k: int) -> int:
        events.sort(key=lambda x: x[1])
        n = len(events)
        f = [[0] * (k + 1) for _ in range(n + 1)]
        for i, (st, _, val) in enumerate(events, 1):
            p = bisect_left(events, st, hi=i - 1, key=lambda x: x[1])
            for j in range(1, k + 1):
                f[i][j] = max(f[i - 1][j], f[p][j - 1] + val)
        return f[n][k]
```

#### Java

```java
class Solution {
    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> a[1] - b[1]);
        int n = events.length;
        int[][] f = new int[n + 1][k + 1];
        for (int i = 1; i <= n; ++i) {
            int st = events[i - 1][0], val = events[i - 1][2];
            int p = search(events, st, i - 1);
            for (int j = 1; j <= k; ++j) {
                f[i][j] = Math.max(f[i - 1][j], f[p][j - 1] + val);
            }
        }
        return f[n][k];
    }

    private int search(int[][] events, int x, int hi) {
        int l = 0, r = hi;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (events[mid][1] >= x) {
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
    int maxValue(vector<vector<int>>& events, int k) {
        sort(events.begin(), events.end(), [](const auto& a, const auto& b) { return a[1] < b[1]; });
        int n = events.size();
        int f[n + 1][k + 1];
        memset(f, 0, sizeof(f));
        for (int i = 1; i <= n; ++i) {
            int st = events[i - 1][0], val = events[i - 1][2];
            vector<int> t = {st};
            int p = lower_bound(events.begin(), events.begin() + i - 1, t, [](const auto& a, const auto& b) { return a[1] < b[0]; }) - events.begin();
            for (int j = 1; j <= k; ++j) {
                f[i][j] = max(f[i - 1][j], f[p][j - 1] + val);
            }
        }
        return f[n][k];
    }
};
```

#### Go

```go
func maxValue(events [][]int, k int) int {
	sort.Slice(events, func(i, j int) bool { return events[i][1] < events[j][1] })
	n := len(events)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, k+1)
	}
	for i := 1; i <= n; i++ {
		st, val := events[i-1][0], events[i-1][2]
		p := sort.Search(i, func(j int) bool { return events[j][1] >= st })
		for j := 1; j <= k; j++ {
			f[i][j] = max(f[i-1][j], f[p][j-1]+val)
		}
	}
	return f[n][k]
}
```

#### TypeScript

```ts
function maxValue(events: number[][], k: number): number {
    events.sort((a, b) => a[1] - b[1]);
    const n = events.length;
    const f: number[][] = new Array(n + 1).fill(0).map(() => new Array(k + 1).fill(0));
    const search = (x: number, hi: number): number => {
        let l = 0;
        let r = hi;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (events[mid][1] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    for (let i = 1; i <= n; ++i) {
        const [st, _, val] = events[i - 1];
        const p = search(st, i - 1);
        for (let j = 1; j <= k; ++j) {
            f[i][j] = Math.max(f[i - 1][j], f[p][j - 1] + val);
        }
    }
    return f[n][k];
}
```

#### Rust

```rust
impl Solution {
    pub fn max_value(mut events: Vec<Vec<i32>>, k: i32) -> i32 {
        events.sort_by_key(|e| e[1]);
        let n = events.len();
        let mut f = vec![vec![0; (k + 1) as usize]; n + 1];

        for i in 1..=n {
            let st = events[i - 1][0];
            let val = events[i - 1][2];
            let p = search(&events, st, i - 1);
            for j in 1..=k as usize {
                f[i][j] = f[i - 1][j].max(f[p][j - 1] + val);
            }
        }

        f[n][k as usize]
    }
}

fn search(events: &Vec<Vec<i32>>, x: i32, hi: usize) -> usize {
    let mut l = 0;
    let mut r = hi;
    while l < r {
        let mid = (l + r) / 2;
        if events[mid][1] >= x {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    l
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
