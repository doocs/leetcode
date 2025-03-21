---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0568.Maximum%20Vacation%20Days/README.md
tags:
    - 数组
    - 动态规划
    - 矩阵
---

<!-- problem:start -->

# [568. 最大休假天数 🔒](https://leetcode.cn/problems/maximum-vacation-days)

[English Version](/solution/0500-0599/0568.Maximum%20Vacation%20Days/README_EN.md)

## 题目描述

<!-- description:start -->

<p>力扣想让一个最优秀的员工在 <strong>N</strong> 个城市间旅行来收集算法问题。 但只工作不玩耍，聪明的孩子也会变傻，所以您可以在某些特定的城市和星期休假。您的工作就是安排旅行使得最大化你可以休假的天数，但是您需要遵守一些规则和限制。</p>

<p><strong>规则和限制：</strong></p>

<ol>
	<li>您只能在 <strong>N</strong> 个城市之间旅行，用 <code>0</code> 到 <code>n-1</code> 的索引表示。一开始，您在索引为 <code>0</code> 的城市，并且那天是<strong>星期一</strong>。</li>
	<li>这些城市通过航班相连。这些航班用&nbsp;<code>n x n</code>&nbsp;矩阵<strong> flights</strong>（不一定是对称的）表示，<strong>flights[i][j] </strong>代表城市 <code>i</code> 到城市 <code>j</code> 的航空状态。如果没有城市 <code>i</code> 到城市 <code>j</code> 的航班，<code>flights[i][j] = 0</code>&nbsp;；否则，<code>flights[i][j] = 1</code>&nbsp;。同时，对于所有的 <code>i</code> ，<code>flights[i][i] = 0</code>&nbsp;<strong>。</strong></li>
	<li>您总共有 <code>k</code>&nbsp;周（<strong>每周7天</strong>）的时间旅行。您<strong>每天</strong>最多只能乘坐一次航班，并且只能在每周的<strong>星期一</strong>上午乘坐航班。由于飞行时间很短，我们不考虑飞行时间的影响。</li>
	<li>对于每个城市，不同的星期您休假天数是不同的，给定一个 <strong>N*K</strong> 矩阵 <strong>days</strong> 代表这种限制，<strong>days[i][j] </strong>代表您在第j个星期在城市i能休假的最长天数。</li>
	<li>如果您从 <code>A</code> 市飞往 <code>B</code> 市，并在当天休假，扣除的假期天数将计入 <code>B</code> 市当周的休假天数。</li>
	<li>我们不考虑飞行时数对休假天数计算的影响。</li>
</ol>

<p>给定 <code>flights</code> 矩阵和 <code>days</code> 矩阵，您需要输出 <code>k</code>&nbsp;周内可以休假的最长天数。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong>flights = [[0,1,1],[1,0,1],[1,1,0]], days = [[1,3,1],[6,0,3],[3,3,3]]
<strong>输出:</strong> 12
<strong>解释:</strong> 
最好的策略之一：
第一个星期 : 星期一从城市 0 飞到城市 1，玩 6 天，工作 1 天。 
（虽然你是从城市 0 开始，但因为是星期一，我们也可以飞到其他城市。） 
第二个星期 : 星期一从城市 1 飞到城市 2，玩 3 天，工作 4 天。
第三个星期 : 呆在城市 2，玩 3 天，工作 4 天。
Ans = 6 + 3 + 3 = 12. 
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong>flights = [[0,0,0],[0,0,0],[0,0,0]], days = [[1,1,1],[7,7,7],[7,7,7]]
<strong>输出:</strong> 3
<strong>解释:</strong> 
由于没有航班可以让您飞到其他城市，你必须在城市 0 呆整整 3 个星期。 
对于每一个星期，你只有一天时间玩，剩下六天都要工作。 
所以最大休假天数为 3.
Ans = 1 + 1 + 1 = 3. 
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong>flights = [[0,1,1],[1,0,1],[1,1,0]], days = [[7,0,0],[0,7,0],[0,0,7]]
<strong>输出:</strong> 21
<strong>解释:</strong>
最好的策略之一是：
第一个星期 : 呆在城市 0，玩 7 天。 
第二个星期 : 星期一从城市 0 飞到城市 1，玩 7 天。
第三个星期 : 星期一从城市 1 飞到城市 2，玩 7 天。
Ans = 7 + 7 + 7 = 21
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == flights.length</code></li>
	<li><code>n == flights[i].length</code></li>
	<li><code>n == days.length</code></li>
	<li><code>k == days[i].length</code></li>
	<li><code>1 &lt;= n, k &lt;= 100</code></li>
	<li><code>flights[i][j]</code>&nbsp;不是&nbsp;<code>0</code>&nbsp;就是&nbsp;<code>1</code></li>
	<li><code>0 &lt;= days[i] &lt;= 7</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[k][j]$ 表示前 $k$ 周，且最后一周在城市 $j$ 休假的最长天数。初始时 $f[0][0]=0$，其它 $f[0][j]=-\infty$。答案为 $\max_{j=0}^{n-1} f[K][j]$。

接下来，我们考虑如何计算 $f[k][j]$。对于当前这一周，我们可以枚举上一周所在的城市 $i$，城市 $i$ 可以和城市 $j$ 相等，那么 $f[k][j] = f[k-1][i]$；也可以和城市 $j$ 不相等，如果不相等，我们需要判断是否可以从城市 $i$ 飞到城市 $j$，如果可以，那么 $f[k][j] = \max(f[k][j], f[k-1][i])$。最后，我们还需要加上这一周在城市 $j$ 休假的天数 $\textit{days}[j][k-1]$。

最终的答案即为 $\max_{j=0}^{n-1} f[K][j]$。

时间复杂度 $O(K \times n^2)$，空间复杂度 $O(K \times n)$。其中 $K$ 和 $n$ 分别为周数和城市数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxVacationDays(self, flights: List[List[int]], days: List[List[int]]) -> int:
        n = len(flights)
        K = len(days[0])
        f = [[-inf] * n for _ in range(K + 1)]
        f[0][0] = 0
        for k in range(1, K + 1):
            for j in range(n):
                f[k][j] = f[k - 1][j]
                for i in range(n):
                    if flights[i][j]:
                        f[k][j] = max(f[k][j], f[k - 1][i])
                f[k][j] += days[j][k - 1]
        return max(f[-1][j] for j in range(n))
```

#### Java

```java
class Solution {
    public int maxVacationDays(int[][] flights, int[][] days) {
        int n = flights.length;
        int K = days[0].length;
        final int inf = 1 << 30;
        int[][] f = new int[K + 1][n];
        for (var g : f) {
            Arrays.fill(g, -inf);
        }
        f[0][0] = 0;
        for (int k = 1; k <= K; ++k) {
            for (int j = 0; j < n; ++j) {
                f[k][j] = f[k - 1][j];
                for (int i = 0; i < n; ++i) {
                    if (flights[i][j] == 1) {
                        f[k][j] = Math.max(f[k][j], f[k - 1][i]);
                    }
                }
                f[k][j] += days[j][k - 1];
            }
        }
        int ans = 0;
        for (int j = 0; j < n; ++j) {
            ans = Math.max(ans, f[K][j]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxVacationDays(vector<vector<int>>& flights, vector<vector<int>>& days) {
        int n = flights.size();
        int K = days[0].size();
        int f[K + 1][n];
        memset(f, -0x3f, sizeof(f));
        f[0][0] = 0;
        for (int k = 1; k <= K; ++k) {
            for (int j = 0; j < n; ++j) {
                f[k][j] = f[k - 1][j];
                for (int i = 0; i < n; ++i) {
                    if (flights[i][j] == 1) {
                        f[k][j] = max(f[k][j], f[k - 1][i]);
                    }
                }
                f[k][j] += days[j][k - 1];
            }
        }
        int ans = 0;
        for (int j = 0; j < n; ++j) {
            ans = max(ans, f[K][j]);
        }
        return ans;
    }
};
```

#### Go

```go
func maxVacationDays(flights [][]int, days [][]int) (ans int) {
	n, K := len(flights), len(days[0])
	f := make([][]int, K+1)
	for i := range f {
		f[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = -(1 << 30)
		}
	}
	f[0][0] = 0
	for k := 1; k <= K; k++ {
		for j := 0; j < n; j++ {
			f[k][j] = f[k-1][j]
			for i := 0; i < n; i++ {
				if flights[i][j] == 1 {
					f[k][j] = max(f[k][j], f[k-1][i])
				}
			}
			f[k][j] += days[j][k-1]
		}
	}
	for j := 0; j < n; j++ {
		ans = max(ans, f[K][j])
	}
	return
}
```

#### TypeScript

```ts
function maxVacationDays(flights: number[][], days: number[][]): number {
    const n = flights.length;
    const K = days[0].length;
    const inf = Number.NEGATIVE_INFINITY;
    const f: number[][] = Array.from({ length: K + 1 }, () => Array(n).fill(inf));
    f[0][0] = 0;
    for (let k = 1; k <= K; k++) {
        for (let j = 0; j < n; j++) {
            f[k][j] = f[k - 1][j];
            for (let i = 0; i < n; i++) {
                if (flights[i][j]) {
                    f[k][j] = Math.max(f[k][j], f[k - 1][i]);
                }
            }
            f[k][j] += days[j][k - 1];
        }
    }
    return Math.max(...f[K]);
}
```

#### Rust

```rust
impl Solution {
    pub fn max_vacation_days(flights: Vec<Vec<i32>>, days: Vec<Vec<i32>>) -> i32 {
        let n = flights.len();
        let k = days[0].len();
        let inf = i32::MIN;

        let mut f = vec![vec![inf; n]; k + 1];
        f[0][0] = 0;

        for step in 1..=k {
            for j in 0..n {
                f[step][j] = f[step - 1][j];
                for i in 0..n {
                    if flights[i][j] == 1 {
                        f[step][j] = f[step][j].max(f[step - 1][i]);
                    }
                }
                f[step][j] += days[j][step - 1];
            }
        }

        *f[k].iter().max().unwrap()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
