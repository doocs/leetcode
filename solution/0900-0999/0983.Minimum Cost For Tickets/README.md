---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0983.Minimum%20Cost%20For%20Tickets/README.md
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [983. 最低票价](https://leetcode.cn/problems/minimum-cost-for-tickets)

[English Version](/solution/0900-0999/0983.Minimum%20Cost%20For%20Tickets/README_EN.md)

## 题目描述

<!-- description:start -->

<p>在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行。在接下来的一年里，你要旅行的日子将以一个名为&nbsp;<code>days</code>&nbsp;的数组给出。每一项是一个从&nbsp;<code>1</code>&nbsp;到&nbsp;<code>365</code>&nbsp;的整数。</p>

<p>火车票有 <strong>三种不同的销售方式</strong> ：</p>

<ul>
	<li>一张 <strong>为期一天</strong> 的通行证售价为&nbsp;<code>costs[0]</code> 美元；</li>
	<li>一张 <strong>为期七天</strong> 的通行证售价为&nbsp;<code>costs[1]</code> 美元；</li>
	<li>一张 <strong>为期三十天</strong> 的通行证售价为&nbsp;<code>costs[2]</code> 美元。</li>
</ul>

<p>通行证允许数天无限制的旅行。 例如，如果我们在第 <code>2</code> 天获得一张 <strong>为期 7 天</strong> 的通行证，那么我们可以连着旅行 7 天：第 <code>2</code> 天、第 <code>3</code> 天、第 <code>4</code> 天、第 <code>5</code> 天、第 <code>6</code> 天、第 <code>7</code> 天和第 <code>8</code> 天。</p>

<p>返回 <em>你想要完成在给定的列表&nbsp;<code>days</code>&nbsp;中列出的每一天的旅行所需要的最低消费&nbsp;</em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>days = [1,4,6,7,8,20], costs = [2,7,15]
<strong>输出：</strong>11
<strong>解释： </strong>
例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划：
在第 1 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 1 天生效。
在第 3 天，你花了 costs[1] = $7 买了一张为期 7 天的通行证，它将在第 3, 4, ..., 9 天生效。
在第 20 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 20 天生效。
你总共花了 $11，并完成了你计划的每一天旅行。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
<strong>输出：</strong>17
<strong>解释：
</strong>例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划： 
在第 1 天，你花了 costs[2] = $15 买了一张为期 30 天的通行证，它将在第 1, 2, ..., 30 天生效。
在第 31 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 31 天生效。 
你总共花了 $17，并完成了你计划的每一天旅行。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= days.length &lt;= 365</code></li>
	<li><code>1 &lt;= days[i] &lt;= 365</code></li>
	<li><code>days</code>&nbsp;按顺序严格递增</li>
	<li><code>costs.length == 3</code></li>
	<li><code>1 &lt;= costs[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索 + 二分查找

我们定义一个函数 $\textit{dfs(i)}$，表示从第 $i$ 次出行开始到最后一次出行结束所需的最小花费。那么答案为 $\textit{dfs(0)}$。

函数 $\textit{dfs(i)}$ 的执行过程如下：

- 如果 $i \geq n$，表示所有出行已经结束，返回 $0$；
- 否则，我们需要考虑三种购买方式，分别是购买 $1$ 天通行证、购买 $7$ 天通行证和购买 $30$ 天通行证。我们分别计算这三种购买方式的花费，并且利用二分查找，找到下一次出行的下标 $j$，然后递归调用 $\textit{dfs(j)}$，最后返回这三种购买方式的最小花费。

为了避免重复计算，我们使用记忆化搜索，将已经计算过的结果保存起来。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 表示出行的次数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def mincostTickets(self, days: List[int], costs: List[int]) -> int:
        @cache
        def dfs(i: int) -> int:
            if i >= n:
                return 0
            ans = inf
            for c, v in zip(costs, valid):
                j = bisect_left(days, days[i] + v)
                ans = min(ans, c + dfs(j))
            return ans

        n = len(days)
        valid = [1, 7, 30]
        return dfs(0)
```

#### Java

```java
class Solution {
    private final int[] valid = {1, 7, 30};
    private int[] days;
    private int[] costs;
    private Integer[] f;
    private int n;

    public int mincostTickets(int[] days, int[] costs) {
        n = days.length;
        f = new Integer[n];
        this.days = days;
        this.costs = costs;
        return dfs(0);
    }

    private int dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        f[i] = Integer.MAX_VALUE;
        for (int k = 0; k < 3; ++k) {
            int j = Arrays.binarySearch(days, days[i] + valid[k]);
            j = j < 0 ? -j - 1 : j;
            f[i] = Math.min(f[i], dfs(j) + costs[k]);
        }
        return f[i];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int mincostTickets(vector<int>& days, vector<int>& costs) {
        int valid[3] = {1, 7, 30};
        int n = days.size();
        int f[n];
        memset(f, 0, sizeof(f));
        function<int(int)> dfs = [&](int i) {
            if (i >= n) {
                return 0;
            }
            if (f[i]) {
                return f[i];
            }
            f[i] = INT_MAX;
            for (int k = 0; k < 3; ++k) {
                int j = lower_bound(days.begin(), days.end(), days[i] + valid[k]) - days.begin();
                f[i] = min(f[i], dfs(j) + costs[k]);
            }
            return f[i];
        };
        return dfs(0);
    }
};
```

#### Go

```go
func mincostTickets(days []int, costs []int) int {
	valid := [3]int{1, 7, 30}
	n := len(days)
	f := make([]int, n)
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if f[i] > 0 {
			return f[i]
		}
		f[i] = 1 << 30
		for k := 0; k < 3; k++ {
			j := sort.SearchInts(days, days[i]+valid[k])
			f[i] = min(f[i], dfs(j)+costs[k])
		}
		return f[i]
	}
	return dfs(0)
}
```

#### TypeScript

```ts
function mincostTickets(days: number[], costs: number[]): number {
    const n = days.length;
    const f: number[] = Array(n).fill(0);
    const valid: number[] = [1, 7, 30];
    const search = (x: number): number => {
        let [l, r] = [0, n];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (days[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    const dfs = (i: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i]) {
            return f[i];
        }
        f[i] = Infinity;
        for (let k = 0; k < 3; ++k) {
            const j = search(days[i] + valid[k]);
            f[i] = Math.min(f[i], dfs(j) + costs[k]);
        }
        return f[i];
    };
    return dfs(0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：动态规划

我们不妨记 $\textit{days}$ 数组中的最后一天为 $m$，那么我们可以定义一个长度为 $m + 1$ 的数组 $f$，其中 $f[i]$ 表示从第 $1$ 天到第 $i$ 天的最小花费。

我们可以按照 $\textit{days}$ 数组中的日期递增的顺序，从第 $1$ 天开始，依次计算 $f[i]$ 的值。如果第 $i$ 天是出行的日期，那么我们可以考虑三种购买方式，分别是购买 $1$ 天通行证、购买 $7$ 天通行证和购买 $30$ 天通行证。我们分别计算这三种购买方式的花费，并且取这三种购买方式的最小花费作为 $f[i]$ 的值。如果第 $i$ 天不是出行的日期，那么 $f[i] = f[i - 1]$。

最终答案为 $f[m]$。

时间复杂度 $O(m)$，空间复杂度 $O(m)$。其中 $m$ 表示出行的最后一天。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def mincostTickets(self, days: List[int], costs: List[int]) -> int:
        m = days[-1]
        f = [0] * (m + 1)
        valid = [1, 7, 30]
        j = 0
        for i in range(1, m + 1):
            if i == days[j]:
                f[i] = inf
                for c, v in zip(costs, valid):
                    f[i] = min(f[i], f[max(0, i - v)] + c)
                j += 1
            else:
                f[i] = f[i - 1]
        return f[m]
```

#### Java

```java
class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int m = days[days.length - 1];
        int[] f = new int[m + 1];
        final int[] valid = {1, 7, 30};
        for (int i = 1, j = 0; i <= m; ++i) {
            if (i == days[j]) {
                f[i] = Integer.MAX_VALUE;
                for (int k = 0; k < 3; ++k) {
                    int c = costs[k], v = valid[k];
                    f[i] = Math.min(f[i], f[Math.max(0, i - v)] + c);
                }
                ++j;
            } else {
                f[i] = f[i - 1];
            }
        }
        return f[m];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int mincostTickets(vector<int>& days, vector<int>& costs) {
        int m = days.back();
        int f[m + 1];
        f[0] = 0;
        int valid[3] = {1, 7, 30};
        for (int i = 1, j = 0; i <= m; ++i) {
            if (i == days[j]) {
                f[i] = INT_MAX;
                for (int k = 0; k < 3; ++k) {
                    int c = costs[k], v = valid[k];
                    f[i] = min(f[i], f[max(0, i - v)] + c);
                }
                ++j;
            } else {
                f[i] = f[i - 1];
            }
        }
        return f[m];
    }
};
```

#### Go

```go
func mincostTickets(days []int, costs []int) int {
	m := days[len(days)-1]
	f := make([]int, m+1)
	valid := [3]int{1, 7, 30}
	for i, j := 1, 0; i <= m; i++ {
		if i == days[j] {
			f[i] = 1 << 30
			for k, v := range valid {
				c := costs[k]
				f[i] = min(f[i], f[max(0, i-v)]+c)
			}
			j++
		} else {
			f[i] = f[i-1]
		}
	}
	return f[m]
}
```

#### TypeScript

```ts
function mincostTickets(days: number[], costs: number[]): number {
    const m = days.at(-1)!;
    const f: number[] = Array(m).fill(0);
    const valid: number[] = [1, 7, 30];
    for (let i = 1, j = 0; i <= m; ++i) {
        if (i === days[j]) {
            f[i] = Infinity;
            for (let k = 0; k < 3; ++k) {
                const [c, v] = [costs[k], valid[k]];
                f[i] = Math.min(f[i], f[Math.max(0, i - v)] + c);
            }
            ++j;
        } else {
            f[i] = f[i - 1];
        }
    }
    return f[m];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
