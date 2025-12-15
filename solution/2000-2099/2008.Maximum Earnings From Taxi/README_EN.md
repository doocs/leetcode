---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2008.Maximum%20Earnings%20From%20Taxi/README_EN.md
rating: 1871
source: Biweekly Contest 61 Q3
tags:
    - Array
    - Hash Table
    - Binary Search
    - Dynamic Programming
    - Sorting
---

<!-- problem:start -->

# [2008. Maximum Earnings From Taxi](https://leetcode.com/problems/maximum-earnings-from-taxi)

[中文文档](/solution/2000-2099/2008.Maximum%20Earnings%20From%20Taxi/README.md)

## Description

<!-- description:start -->

<p>There are <code>n</code> points on a road you are driving your taxi on. The <code>n</code> points on the road are labeled from <code>1</code> to <code>n</code> in the direction you are going, and you want to drive from point <code>1</code> to point <code>n</code> to make money by picking up passengers. You cannot change the direction of the taxi.</p>

<p>The passengers are represented by a <strong>0-indexed</strong> 2D integer array <code>rides</code>, where <code>rides[i] = [start<sub>i</sub>, end<sub>i</sub>, tip<sub>i</sub>]</code> denotes the <code>i<sup>th</sup></code> passenger requesting a ride from point <code>start<sub>i</sub></code> to point <code>end<sub>i</sub></code> who is willing to give a <code>tip<sub>i</sub></code> dollar tip.</p>

<p>For<strong> each </strong>passenger <code>i</code> you pick up, you <strong>earn</strong> <code>end<sub>i</sub> - start<sub>i</sub> + tip<sub>i</sub></code> dollars. You may only drive <b>at most one </b>passenger at a time.</p>

<p>Given <code>n</code> and <code>rides</code>, return <em>the <strong>maximum</strong> number of dollars you can earn by picking up the passengers optimally.</em></p>

<p><strong>Note:</strong> You may drop off a passenger and pick up a different passenger at the same point.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 5, rides = [<u>[2,5,4]</u>,[1,5,1]]
<strong>Output:</strong> 7
<strong>Explanation:</strong> We can pick up passenger 0 to earn 5 - 2 + 4 = 7 dollars.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 20, rides = [[1,6,1],<u>[3,10,2]</u>,<u>[10,12,3]</u>,[11,12,2],[12,15,2],<u>[13,18,1]</u>]
<strong>Output:</strong> 20
<strong>Explanation:</strong> We will pick up the following passengers:
- Drive passenger 1 from point 3 to point 10 for a profit of 10 - 3 + 2 = 9 dollars.
- Drive passenger 2 from point 10 to point 12 for a profit of 12 - 10 + 3 = 5 dollars.
- Drive passenger 5 from point 13 to point 18 for a profit of 18 - 13 + 1 = 6 dollars.
We earn 9 + 5 + 6 = 20 dollars in total.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= rides.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>rides[i].length == 3</code></li>
	<li><code>1 &lt;= start<sub>i</sub> &lt; end<sub>i</sub> &lt;= n</code></li>
	<li><code>1 &lt;= tip<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoization Search + Binary Search

First, we sort $rides$ in ascending order by $start$. Then we design a function $dfs(i)$, which represents the maximum tip that can be obtained from accepting orders starting from the $i$-th passenger. The answer is $dfs(0)$.

The calculation process of the function $dfs(i)$ is as follows:

For the $i$-th passenger, we can choose to accept or not to accept the order. If we don't accept the order, the maximum tip that can be obtained is $dfs(i + 1)$. If we accept the order, we can use binary search to find the first passenger encountered after the drop-off point of the $i$-th passenger, denoted as $j$. The maximum tip that can be obtained is $dfs(j) + end_i - start_i + tip_i$. Take the larger of the two. That is:

$$
dfs(i) = \max(dfs(i + 1), dfs(j) + end_i - start_i + tip_i)
$$

Where $j$ is the smallest index that satisfies $start_j \ge end_i$, which can be obtained by binary search.

In this process, we can use memoization search to save the answer of each state to avoid repeated calculations.

The time complexity is $O(m \times \log m)$, and the space complexity is $O(m)$. Here, $m$ is the length of $rides$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxTaxiEarnings(self, n: int, rides: List[List[int]]) -> int:
        @cache
        def dfs(i: int) -> int:
            if i >= len(rides):
                return 0
            st, ed, tip = rides[i]
            j = bisect_left(rides, ed, lo=i + 1, key=lambda x: x[0])
            return max(dfs(i + 1), dfs(j) + ed - st + tip)

        rides.sort()
        return dfs(0)
```

#### Java

```java
class Solution {
    private int m;
    private int[][] rides;
    private Long[] f;

    public long maxTaxiEarnings(int n, int[][] rides) {
        Arrays.sort(rides, (a, b) -> a[0] - b[0]);
        m = rides.length;
        f = new Long[m];
        this.rides = rides;
        return dfs(0);
    }

    private long dfs(int i) {
        if (i >= m) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        int[] r = rides[i];
        int st = r[0], ed = r[1], tip = r[2];
        int j = search(ed, i + 1);
        return f[i] = Math.max(dfs(i + 1), dfs(j) + ed - st + tip);
    }

    private int search(int x, int l) {
        int r = m;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (rides[mid][0] >= x) {
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
    long long maxTaxiEarnings(int n, vector<vector<int>>& rides) {
        sort(rides.begin(), rides.end());
        int m = rides.size();
        long long f[m];
        memset(f, -1, sizeof(f));
        function<long long(int)> dfs = [&](int i) -> long long {
            if (i >= m) {
                return 0;
            }
            if (f[i] != -1) {
                return f[i];
            }
            auto& r = rides[i];
            int st = r[0], ed = r[1], tip = r[2];
            int j = lower_bound(rides.begin() + i + 1, rides.end(), ed, [](auto& a, int val) { return a[0] < val; }) - rides.begin();
            return f[i] = max(dfs(i + 1), dfs(j) + ed - st + tip);
        };
        return dfs(0);
    }
};
```

#### Go

```go
func maxTaxiEarnings(n int, rides [][]int) int64 {
	sort.Slice(rides, func(i, j int) bool { return rides[i][0] < rides[j][0] })
	m := len(rides)
	f := make([]int64, m)
	var dfs func(int) int64
	dfs = func(i int) int64 {
		if i >= m {
			return 0
		}
		if f[i] == 0 {
			st, ed, tip := rides[i][0], rides[i][1], rides[i][2]
			j := sort.Search(m, func(j int) bool { return rides[j][0] >= ed })
			f[i] = max(dfs(i+1), int64(ed-st+tip)+dfs(j))
		}
		return f[i]
	}
	return dfs(0)
}
```

#### TypeScript

```ts
function maxTaxiEarnings(n: number, rides: number[][]): number {
    rides.sort((a, b) => a[0] - b[0]);
    const m = rides.length;
    const f: number[] = Array(m).fill(-1);
    const search = (x: number, l: number): number => {
        let r = m;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (rides[mid][0] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    const dfs = (i: number): number => {
        if (i >= m) {
            return 0;
        }
        if (f[i] === -1) {
            const [st, ed, tip] = rides[i];
            const j = search(ed, i + 1);
            f[i] = Math.max(dfs(i + 1), dfs(j) + ed - st + tip);
        }
        return f[i];
    };
    return dfs(0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming + Binary Search

We can change the memoization search in Solution 1 to dynamic programming.

First, sort $rides$, this time we sort by $end$ in ascending order. Then define $f[i]$, which represents the maximum tip that can be obtained from the first $i$ passengers. Initially, $f[0] = 0$, and the answer is $f[m]$.

For the $i$-th passenger, we can choose to accept or not to accept the order. If we don't accept the order, the maximum tip that can be obtained is $f[i-1]$. If we accept the order, we can use binary search to find the last passenger whose drop-off point is not greater than $start_i$ before the $i$-th passenger gets on the car, denoted as $j$. The maximum tip that can be obtained is $f[j] + end_i - start_i + tip_i$. Take the larger of the two. That is:

$$
f[i] = \max(f[i - 1], f[j] + end_i - start_i + tip_i)
$$

Where $j$ is the largest index that satisfies $end_j \le start_i$, which can be obtained by binary search.

The time complexity is $O(m \times \log m)$, and the space complexity is $O(m)$. Here, $m$ is the length of $rides$.

Similar problems:

- [1235. Maximum Profit in Job Scheduling](https://github.com/doocs/leetcode/blob/main/solution/1200-1299/1235.Maximum%20Profit%20in%20Job%20Scheduling/README_EN.md)
- [1751. Maximum Number of Events That Can Be Attended II](https://github.com/doocs/leetcode/blob/main/solution/1700-1799/1751.Maximum%20Number%20of%20Events%20That%20Can%20Be%20Attended%20II/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxTaxiEarnings(self, n: int, rides: List[List[int]]) -> int:
        rides.sort(key=lambda x: x[1])
        f = [0] * (len(rides) + 1)
        for i, (st, ed, tip) in enumerate(rides, 1):
            j = bisect_left(rides, st + 1, hi=i, key=lambda x: x[1])
            f[i] = max(f[i - 1], f[j] + ed - st + tip)
        return f[-1]
```

#### Java

```java
class Solution {
    public long maxTaxiEarnings(int n, int[][] rides) {
        Arrays.sort(rides, (a, b) -> a[1] - b[1]);
        int m = rides.length;
        long[] f = new long[m + 1];
        for (int i = 1; i <= m; ++i) {
            int[] r = rides[i - 1];
            int st = r[0], ed = r[1], tip = r[2];
            int j = search(rides, st + 1, i);
            f[i] = Math.max(f[i - 1], f[j] + ed - st + tip);
        }
        return f[m];
    }

    private int search(int[][] nums, int x, int r) {
        int l = 0;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid][1] >= x) {
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
    long long maxTaxiEarnings(int n, vector<vector<int>>& rides) {
        sort(rides.begin(), rides.end(), [](const vector<int>& a, const vector<int>& b) { return a[1] < b[1]; });
        int m = rides.size();
        vector<long long> f(m + 1);
        for (int i = 1; i <= m; ++i) {
            auto& r = rides[i - 1];
            int st = r[0], ed = r[1], tip = r[2];
            auto it = lower_bound(rides.begin(), rides.begin() + i, st + 1, [](auto& a, int val) { return a[1] < val; });
            int j = distance(rides.begin(), it);
            f[i] = max(f[i - 1], f[j] + ed - st + tip);
        }
        return f.back();
    }
};
```

#### Go

```go
func maxTaxiEarnings(n int, rides [][]int) int64 {
	sort.Slice(rides, func(i, j int) bool { return rides[i][1] < rides[j][1] })
	m := len(rides)
	f := make([]int64, m+1)
	for i := 1; i <= m; i++ {
		r := rides[i-1]
		st, ed, tip := r[0], r[1], r[2]
		j := sort.Search(m, func(j int) bool { return rides[j][1] >= st+1 })
		f[i] = max(f[i-1], f[j]+int64(ed-st+tip))
	}
	return f[m]
}
```

#### TypeScript

```ts
function maxTaxiEarnings(n: number, rides: number[][]): number {
    rides.sort((a, b) => a[1] - b[1]);
    const m = rides.length;
    const f: number[] = Array(m + 1).fill(0);
    const search = (x: number, r: number): number => {
        let l = 0;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (rides[mid][1] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    for (let i = 1; i <= m; ++i) {
        const [st, ed, tip] = rides[i - 1];
        const j = search(st + 1, i);
        f[i] = Math.max(f[i - 1], f[j] + ed - st + tip);
    }
    return f[m];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
