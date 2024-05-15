---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1575.Count%20All%20Possible%20Routes/README.md
rating: 2055
tags:
    - 记忆化搜索
    - 数组
    - 动态规划
---

# [1575. 统计所有可行路径](https://leetcode.cn/problems/count-all-possible-routes)

[English Version](/solution/1500-1599/1575.Count%20All%20Possible%20Routes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <strong>互不相同</strong>&nbsp;的整数数组，其中&nbsp;<code>locations[i]</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;个城市的位置。同时给你&nbsp;<code>start</code>，<code>finish</code>&nbsp;和&nbsp;<code>fuel</code>&nbsp;分别表示出发城市、目的地城市和你初始拥有的汽油总量</p>

<p>每一步中，如果你在城市 <code>i</code>&nbsp;，你可以选择任意一个城市 <code>j</code>&nbsp;，满足 &nbsp;<code>j != i</code>&nbsp;且&nbsp;<code>0 &lt;= j &lt; locations.length</code>&nbsp;，并移动到城市&nbsp;<code>j</code>&nbsp;。从城市&nbsp;<code>i</code>&nbsp;移动到&nbsp;<code>j</code>&nbsp;消耗的汽油量为&nbsp;<code>|locations[i] - locations[j]|</code>，<code>|x|</code>&nbsp;表示&nbsp;<code>x</code>&nbsp;的绝对值。</p>

<p>请注意，&nbsp;<code>fuel</code>&nbsp;任何时刻都&nbsp;<strong>不能</strong>&nbsp;为负，且你&nbsp;<strong>可以</strong>&nbsp;经过任意城市超过一次（包括&nbsp;<code>start</code>&nbsp;和&nbsp;<code>finish</code>&nbsp;）。</p>

<p>请你返回从<em>&nbsp;</em><code>start</code>&nbsp;到&nbsp;<code>finish</code>&nbsp;所有可能路径的数目。</p>

<p>由于答案可能很大， 请将它对&nbsp;<code>10^9 + 7</code>&nbsp;取余后返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>locations = [2,3,6,8,4], start = 1, finish = 3, fuel = 5
<strong>输出：</strong>4
<strong>解释：</strong>以下为所有可能路径，每一条都用了 5 单位的汽油：
1 -&gt; 3
1 -&gt; 2 -&gt; 3
1 -&gt; 4 -&gt; 3
1 -&gt; 4 -&gt; 2 -&gt; 3
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>locations = [4,3,1], start = 1, finish = 0, fuel = 6
<strong>输出：</strong>5
<strong>解释：</strong>以下为所有可能的路径：
1 -&gt; 0，使用汽油量为 fuel = 1
1 -&gt; 2 -&gt; 0，使用汽油量为 fuel = 5
1 -&gt; 2 -&gt; 1 -&gt; 0，使用汽油量为 fuel = 5
1 -&gt; 0 -&gt; 1 -&gt; 0，使用汽油量为 fuel = 3
1 -&gt; 0 -&gt; 1 -&gt; 0 -&gt; 1 -&gt; 0，使用汽油量为 fuel = 5
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>locations = [5,2,1], start = 0, finish = 2, fuel = 3
<strong>输出：</strong>0
<strong>解释：</strong>没有办法只用 3 单位的汽油从 0 到达 2 。因为最短路径需要 4 单位的汽油。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= locations.length &lt;= 100</code></li>
	<li><code>1 &lt;= locations[i] &lt;= 10<sup>9</sup></code></li>
	<li>所有&nbsp;<code>locations</code>&nbsp;中的整数 <strong>互不相同</strong>&nbsp;。</li>
	<li><code>0 &lt;= start, finish &lt;&nbsp;locations.length</code></li>
	<li><code>1 &lt;= fuel &lt;= 200</code></li>
</ul>

## 解法

### 方法一：记忆化搜索

我们设计一个函数 $dfs(i, k)$，表示从城市 $i$ 出发，剩余汽油量为 $k$ 时，到达目的地 $finish$ 的路径数。那么答案就是 $dfs(start, fuel)$。

函数 $dfs(i, k)$ 的计算过程如下：

-   如果 $k \lt |locations[i] - locations[finish]|$，那么返回 $0$。
-   如果 $i = finish$，那么答案路径数初始时为 $1$，否则为 $0$。
-   然后，我们遍历所有城市 $j$，如果 $j \ne i$，那么我们可以从城市 $i$ 移动到城市 $j$，此时剩余汽油量为 $k - |locations[i] - locations[j]|$，那么我们可以将答案路径数加上 $dfs(j, k - |locations[i] - locations[j]|)$。
-   最后，我们返回答案路径数。

为了避免重复计算，我们可以使用记忆化搜索。

时间复杂度 $O(n^2 \times m)$，空间复杂度 $O(n \times m)$。其中 $n$ 和 $m$ 分别是数组 $locations$ 和 $fuel$ 的大小。

<!-- tabs:start -->

```python
class Solution:
    def countRoutes(
        self, locations: List[int], start: int, finish: int, fuel: int
    ) -> int:
        @cache
        def dfs(i: int, k: int) -> int:
            if k < abs(locations[i] - locations[finish]):
                return 0
            ans = int(i == finish)
            for j, x in enumerate(locations):
                if j != i:
                    ans = (ans + dfs(j, k - abs(locations[i] - x))) % mod
            return ans

        mod = 10**9 + 7
        return dfs(start, fuel)
```

```java
class Solution {
    private int[] locations;
    private int finish;
    private int n;
    private Integer[][] f;
    private final int mod = (int) 1e9 + 7;

    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        n = locations.length;
        this.locations = locations;
        this.finish = finish;
        f = new Integer[n][fuel + 1];
        return dfs(start, fuel);
    }

    private int dfs(int i, int k) {
        if (k < Math.abs(locations[i] - locations[finish])) {
            return 0;
        }
        if (f[i][k] != null) {
            return f[i][k];
        }
        int ans = i == finish ? 1 : 0;
        for (int j = 0; j < n; ++j) {
            if (j != i) {
                ans = (ans + dfs(j, k - Math.abs(locations[i] - locations[j]))) % mod;
            }
        }
        return f[i][k] = ans;
    }
}
```

```cpp
class Solution {
public:
    int countRoutes(vector<int>& locations, int start, int finish, int fuel) {
        int n = locations.size();
        int f[n][fuel + 1];
        memset(f, -1, sizeof(f));
        const int mod = 1e9 + 7;
        function<int(int, int)> dfs = [&](int i, int k) -> int {
            if (k < abs(locations[i] - locations[finish])) {
                return 0;
            }
            if (f[i][k] != -1) {
                return f[i][k];
            }
            int ans = i == finish;
            for (int j = 0; j < n; ++j) {
                if (j != i) {
                    ans = (ans + dfs(j, k - abs(locations[i] - locations[j]))) % mod;
                }
            }
            return f[i][k] = ans;
        };
        return dfs(start, fuel);
    }
};
```

```go
func countRoutes(locations []int, start int, finish int, fuel int) int {
	n := len(locations)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, fuel+1)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	const mod = 1e9 + 7
	var dfs func(int, int) int
	dfs = func(i, k int) (ans int) {
		if k < abs(locations[i]-locations[finish]) {
			return 0
		}
		if f[i][k] != -1 {
			return f[i][k]
		}
		if i == finish {
			ans = 1
		}
		for j, x := range locations {
			if j != i {
				ans = (ans + dfs(j, k-abs(locations[i]-x))) % mod
			}
		}
		f[i][k] = ans
		return
	}
	return dfs(start, fuel)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function countRoutes(locations: number[], start: number, finish: number, fuel: number): number {
    const n = locations.length;
    const f = Array.from({ length: n }, () => Array(fuel + 1).fill(-1));
    const mod = 1e9 + 7;
    const dfs = (i: number, k: number): number => {
        if (k < Math.abs(locations[i] - locations[finish])) {
            return 0;
        }
        if (f[i][k] !== -1) {
            return f[i][k];
        }
        let ans = i === finish ? 1 : 0;
        for (let j = 0; j < n; ++j) {
            if (j !== i) {
                const x = Math.abs(locations[i] - locations[j]);
                ans = (ans + dfs(j, k - x)) % mod;
            }
        }
        return (f[i][k] = ans);
    };
    return dfs(start, fuel);
}
```

<!-- tabs:end -->

### 方法二：动态规划

我们也可以将方法一的记忆化搜索转换为动态规划。

我们定义 $f[i][k]$ 表示从城市 $i$ 出发，剩余汽油量为 $k$ 时，到达目的地 $finish$ 的路径数。那么答案就是 $f[start][fuel]$。初始时 $f[finish][k]=1$，其余均为 $0$。

接下来，我们从小到大枚举剩余汽油量 $k$，然后枚举所有的城市 $i$，对于每个城市 $i$，我们枚举所有的城市 $j$，如果 $j \ne i$，并且 $|locations[i] - locations[j]| \le k$，那么我们可以从城市 $i$ 移动到城市 $j$，此时剩余汽油量为 $k - |locations[i] - locations[j]|$，那么我们可以将答案路径数加上 $f[j][k - |locations[i] - locations[j]|]$。

最后，我们返回答案路径数 $f[start][fuel]$ 即可。

时间复杂度 $O(n^2 \times m)$，空间复杂度 $O(n \times m)$。其中 $n$ 和 $m$ 分别是数组 $locations$ 和 $fuel$ 的大小。

<!-- tabs:start -->

```python
class Solution:
    def countRoutes(
        self, locations: List[int], start: int, finish: int, fuel: int
    ) -> int:
        mod = 10**9 + 7
        n = len(locations)
        f = [[0] * (fuel + 1) for _ in range(n)]
        for k in range(fuel + 1):
            f[finish][k] = 1
        for k in range(fuel + 1):
            for i in range(n):
                for j in range(n):
                    if j != i and abs(locations[i] - locations[j]) <= k:
                        f[i][k] = (
                            f[i][k] + f[j][k - abs(locations[i] - locations[j])]
                        ) % mod
        return f[start][fuel]
```

```java
class Solution {
    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        final int mod = (int) 1e9 + 7;
        int n = locations.length;
        int[][] f = new int[n][fuel + 1];
        for (int k = 0; k <= fuel; ++k) {
            f[finish][k] = 1;
        }
        for (int k = 0; k <= fuel; ++k) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (j != i && Math.abs(locations[i] - locations[j]) <= k) {
                        f[i][k] = (f[i][k] + f[j][k - Math.abs(locations[i] - locations[j])]) % mod;
                    }
                }
            }
        }
        return f[start][fuel];
    }
}
```

```cpp
class Solution {
public:
    int countRoutes(vector<int>& locations, int start, int finish, int fuel) {
        const int mod = 1e9 + 7;
        int n = locations.size();
        int f[n][fuel + 1];
        memset(f, 0, sizeof(f));
        for (int k = 0; k <= fuel; ++k) {
            f[finish][k] = 1;
        }
        for (int k = 0; k <= fuel; ++k) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (j != i && abs(locations[i] - locations[j]) <= k) {
                        f[i][k] = (f[i][k] + f[j][k - abs(locations[i] - locations[j])]) % mod;
                    }
                }
            }
        }
        return f[start][fuel];
    }
};
```

```go
func countRoutes(locations []int, start int, finish int, fuel int) int {
	n := len(locations)
	const mod = 1e9 + 7
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, fuel+1)
	}
	for k := 0; k <= fuel; k++ {
		f[finish][k] = 1
	}
	for k := 0; k <= fuel; k++ {
		for i := 0; i < n; i++ {
			for j := 0; j < n; j++ {
				if j != i && abs(locations[i]-locations[j]) <= k {
					f[i][k] = (f[i][k] + f[j][k-abs(locations[i]-locations[j])]) % mod
				}
			}
		}
	}
	return f[start][fuel]
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function countRoutes(locations: number[], start: number, finish: number, fuel: number): number {
    const n = locations.length;
    const f = Array.from({ length: n }, () => Array(fuel + 1).fill(0));
    for (let k = 0; k <= fuel; ++k) {
        f[finish][k] = 1;
    }
    const mod = 1e9 + 7;
    for (let k = 0; k <= fuel; ++k) {
        for (let i = 0; i < n; ++i) {
            for (let j = 0; j < n; ++j) {
                if (j !== i && Math.abs(locations[i] - locations[j]) <= k) {
                    f[i][k] = (f[i][k] + f[j][k - Math.abs(locations[i] - locations[j])]) % mod;
                }
            }
        }
    }
    return f[start][fuel];
}
```

<!-- tabs:end -->

<!-- end -->
