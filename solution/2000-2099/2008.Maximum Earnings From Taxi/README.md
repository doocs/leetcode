---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2008.Maximum%20Earnings%20From%20Taxi/README.md
rating: 1871
source: 第 61 场双周赛 Q3
tags:
    - 数组
    - 哈希表
    - 二分查找
    - 动态规划
    - 排序
---

# [2008. 出租车的最大盈利](https://leetcode.cn/problems/maximum-earnings-from-taxi)

[English Version](/solution/2000-2099/2008.Maximum%20Earnings%20From%20Taxi/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你驾驶出租车行驶在一条有 <code>n</code>&nbsp;个地点的路上。这 <code>n</code>&nbsp;个地点从近到远编号为&nbsp;<code>1</code>&nbsp;到&nbsp;<code>n</code>&nbsp;，你想要从 <code>1</code>&nbsp;开到 <code>n</code>&nbsp;，通过接乘客订单盈利。你只能沿着编号递增的方向前进，不能改变方向。</p>

<p>乘客信息用一个下标从 <strong>0</strong>&nbsp;开始的二维数组&nbsp;<code>rides</code>&nbsp;表示，其中&nbsp;<code>rides[i] = [start<sub>i</sub>, end<sub>i</sub>, tip<sub>i</sub>]</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;位乘客需要从地点&nbsp;<code>start<sub>i</sub></code>&nbsp;前往&nbsp;<code>end<sub>i</sub></code>&nbsp;，愿意支付&nbsp;<code>tip<sub>i</sub></code>&nbsp;元的小费。</p>

<p><strong>每一位</strong> 你选择接单的乘客&nbsp;<code>i</code>&nbsp;，你可以 <strong>盈利</strong>&nbsp;<code>end<sub>i</sub> - start<sub>i</sub> + tip<sub>i</sub></code>&nbsp;元。你同时&nbsp;<strong>最多</strong>&nbsp;只能接一个订单。</p>

<p>给你 <code>n</code>&nbsp;和 <code>rides</code>&nbsp;，请你返回在最优接单方案下，你能盈利&nbsp;<strong>最多</strong>&nbsp;多少元。</p>

<p><strong>注意：</strong>你可以在一个地点放下一位乘客，并在同一个地点接上另一位乘客。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>n = 5, rides = [<em><strong>[2,5,4]</strong></em>,[1,5,1]]
<b>输出：</b>7
<b>解释：</b>我们可以接乘客 0 的订单，获得 5 - 2 + 4 = 7 元。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>n = 20, rides = [[1,6,1],<strong><em>[3,10,2]</em></strong>,<em><strong>[10,12,3]</strong></em>,[11,12,2],[12,15,2],<strong><em>[13,18,1]</em></strong>]
<b>输出：</b>20
<b>解释：</b>我们可以接以下乘客的订单：
- 将乘客 1 从地点 3 送往地点 10 ，获得 10 - 3 + 2 = 9 元。
- 将乘客 2 从地点 10 送往地点 12 ，获得 12 - 10 + 3 = 5 元。
- 将乘客 5 从地点 13 送往地点 18 ，获得 18 - 13 + 1 = 6 元。
我们总共获得 9 + 5 + 6 = 20 元。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= rides.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>rides[i].length == 3</code></li>
	<li><code>1 &lt;= start<sub>i</sub> &lt; end<sub>i</sub> &lt;= n</code></li>
	<li><code>1 &lt;= tip<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一：记忆化搜索 + 二分查找

我们先将 $rides$ 按照$start$ 从小到大排序，然后设计一个函数 $dfs(i)$，表示从第 $i$ 个乘客开始接单，最多能获得的小费。答案即为 $dfs(0)$。

函数 $dfs(i)$ 的计算过程如下：

对于第 $i$ 个乘客，我们可以选择接单，也可以选择不接单。如果不接单，那么最多能获得的小费为 $dfs(i + 1)$；如果接单，那么我们可以通过二分查找，找到在第 $i$ 个乘客下车地点之后遇到的第一个乘客，记为 $j$，那么最多能获得的小费为 $dfs(j) + end_i - start_i + tip_i$。取两者的较大值即可。即：

$$
dfs(i) = \max(dfs(i + 1), dfs(j) + end_i - start_i + tip_i)
$$

其中 $j$ 是满足 $start_j \ge end_i$ 的最小的下标，可以通过二分查找得到。

此过程中，我们可以使用记忆化搜索，将每个状态的答案保存下来，避免重复计算。

时间复杂度 $O(m \times \log m)$，空间复杂度 $O(m)$。其中 $m$ 为$rides$ 的长度。

<!-- tabs:start -->

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

### 方法二：动态规划 + 二分查找

我们可以将方法一中的记忆化搜索改为动态规划。

先将 $rides$ 排序，这次我们按照 $end$ 从小到大排序。然后定义 $f[i]$，表示前 $i$ 个乘客中，最多能获得的小费。初始时 $f[0] = 0$，答案为 $f[m]$。

对于第 $i$ 个乘客，我们可以选择接单，也可以选择不接单。如果不接单，那么最多能获得的小费为 $f[i-1]$；如果接单，我们可以通过二分查找，找到在第 $i$ 个乘客上车地点之前，最后一个下车地点不大于 $start_i$ 的乘客，记为 $j$，那么最多能获得的小费为 $f[j] + end_i - start_i + tip_i$。取两者的较大值即可。即：

$$
f[i] = \max(f[i - 1], f[j] + end_i - start_i + tip_i)
$$

其中 $j$ 是满足 $end_j \le start_i$ 的最大的下标，可以通过二分查找得到。

时间复杂度 $O(m \times \log m)$，空间复杂度 $O(m)$。其中 $m$ 为$rides$ 的长度。

相似题目：

-   [1235. 规划兼职工作](https://github.com/doocs/leetcode/blob/main/solution/1200-1299/1235.Maximum%20Profit%20in%20Job%20Scheduling/README.md)
-   [1751. 最多可以参加的会议数目 II](https://github.com/doocs/leetcode/blob/main/solution/1700-1799/1751.Maximum%20Number%20of%20Events%20That%20Can%20Be%20Attended%20II/README.md)

<!-- tabs:start -->

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

<!-- end -->
