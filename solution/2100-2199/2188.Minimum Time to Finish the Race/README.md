# [2188. 完成比赛的最少时间](https://leetcode.cn/problems/minimum-time-to-finish-the-race)

[English Version](/solution/2100-2199/2188.Minimum%20Time%20to%20Finish%20the%20Race/README_EN.md)

<!-- tags:数组,动态规划 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的二维整数数组&nbsp;<code>tires</code>&nbsp;，其中&nbsp;<code>tires[i] = [f<sub>i</sub>, r<sub>i</sub>]</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;种轮胎如果连续使用，第&nbsp;<code>x</code>&nbsp;圈需要耗时&nbsp;<code>f<sub>i</sub> * r<sub>i</sub><sup>(x-1)</sup></code>&nbsp;秒。</p>

<ul>
	<li>比方说，如果&nbsp;<code>f<sub>i</sub> = 3</code>&nbsp;且&nbsp;<code>r<sub>i</sub> = 2</code>&nbsp;，且一直使用这种类型的同一条轮胎，那么该轮胎完成第&nbsp;<code>1</code>&nbsp;圈赛道耗时 <code>3</code>&nbsp;秒，完成第 <code>2</code>&nbsp;圈耗时&nbsp;<code>3 * 2 = 6</code>&nbsp;秒，完成第 <code>3</code>&nbsp;圈耗时&nbsp;<code>3 * 2<sup>2</sup> = 12</code>&nbsp;秒，依次类推。</li>
</ul>

<p>同时给你一个整数&nbsp;<code>changeTime</code>&nbsp;和一个整数&nbsp;<code>numLaps</code>&nbsp;。</p>

<p>比赛总共包含&nbsp;<code>numLaps</code>&nbsp;圈，你可以选择 <strong>任意</strong>&nbsp;一种轮胎开始比赛。每一种轮胎都有 <strong>无数条</strong>&nbsp;。每一圈后，你可以选择耗费 <code>changeTime</code>&nbsp;秒 <strong>换成</strong>&nbsp;任意一种轮胎（也可以换成当前种类的新轮胎）。</p>

<p>请你返回完成比赛需要耗费的 <strong>最少</strong>&nbsp;时间。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>tires = [[2,3],[3,4]], changeTime = 5, numLaps = 4
<b>输出：</b>21
<b>解释：</b>
第 1 圈：使用轮胎 0 ，耗时 2 秒。
第 2 圈：继续使用轮胎 0 ，耗时 2 * 3 = 6 秒。
第 3 圈：耗费 5 秒换一条新的轮胎 0 ，然后耗时 2 秒完成这一圈。
第 4 圈：继续使用轮胎 0 ，耗时 2 * 3 = 6 秒。
总耗时 = 2 + 6 + 5 + 2 + 6 = 21 秒。
完成比赛的最少时间为 21 秒。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>tires = [[1,10],[2,2],[3,4]], changeTime = 6, numLaps = 5
<b>输出：</b>25
<b>解释：</b>
第 1 圈：使用轮胎 1 ，耗时 2 秒。
第 2 圈：继续使用轮胎 1 ，耗时 2 * 2 = 4 秒。
第 3 圈：耗时 6 秒换一条新的轮胎 1 ，然后耗时 2 秒完成这一圈。
第 4 圈：继续使用轮胎 1 ，耗时 2 * 2 = 4 秒。
第 5 圈：耗时 6 秒换成轮胎 0 ，然后耗时 1 秒完成这一圈。
总耗时 = 2 + 4 + 6 + 2 + 4 + 6 + 1 = 25 秒。
完成比赛的最少时间为 25 秒。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= tires.length &lt;= 10<sup>5</sup></code></li>
	<li><code>tires[i].length == 2</code></li>
	<li><code>1 &lt;= f<sub>i</sub>, changeTime &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= r<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= numLaps &lt;= 1000</code></li>
</ul>

## 解法

### 方法一：预处理 + 动态规划

我们注意到，连续使用同一个轮胎 $(f, r)$ 跑 $i$ 圈，那么第 $i$ 圈的耗时不应该超过 $changeTime + f$，否则我们可以在第 $i$ 圈的时候换轮胎，这样总耗时会更少。即：

$$
f \times r^{i-1} \leq changeTime + f
$$

我们可以求出满足上式的最大的 $i$，要使得 $i$ 最大，那么 $f$ 和 $r$ 应该尽可能小，根据题目的数据范围，我们取 $f=1$, $r=2$，那么 $2^{i-1} \leq changeTime + 1$，即 $i \leq \log_2(changeTime + 1) + 1$。根据这个结论，以及题目中 $changeTime$ 的数据范围，我们可以知道 $i$ 最大为 $17$。

我们定义 $cost[i]$ 表示使用同一个轮胎跑 $i$ 圈的最小耗时，那么我们可以预处理出 $cost$ 数组，然后使用动态规划求解即可。定义 $f[i]$ 表示跑 $i$ 圈的最小耗时，那么我们可以得到状态转移方程：

$$
f[i] = (\min_{1 \leq j \leq \min(17, i)} f[i-j] + cost[j]) + changeTime
$$

初始时 $f[0] = -changeTime$，最终答案为 $f[numLaps]$。

时间复杂度 $O((n + numLaps) \times \log T_{max})$，空间复杂度 $O(n + \log T_{max})$，其中 $T_{max}$ 是题目中 $f_i$, $r_i$ 和 $changeTime$ 的最大值。本题中 $\log T_{max} \approx 17$。

<!-- tabs:start -->

```python
class Solution:
    def minimumFinishTime(
        self, tires: List[List[int]], changeTime: int, numLaps: int
    ) -> int:
        cost = [inf] * 18
        for f, r in tires:
            i, s, t = 1, 0, f
            while t <= changeTime + f:
                s += t
                cost[i] = min(cost[i], s)
                t *= r
                i += 1
        f = [inf] * (numLaps + 1)
        f[0] = -changeTime
        for i in range(1, numLaps + 1):
            for j in range(1, min(18, i + 1)):
                f[i] = min(f[i], f[i - j] + cost[j])
            f[i] += changeTime
        return f[numLaps]
```

```java
class Solution {
    public int minimumFinishTime(int[][] tires, int changeTime, int numLaps) {
        final int inf = 1 << 30;
        int[] cost = new int[18];
        Arrays.fill(cost, inf);
        for (int[] e : tires) {
            int f = e[0], r = e[1];
            int s = 0, t = f;
            for (int i = 1; t <= changeTime + f; ++i) {
                s += t;
                cost[i] = Math.min(cost[i], s);
                t *= r;
            }
        }
        int[] f = new int[numLaps + 1];
        Arrays.fill(f, inf);
        f[0] = -changeTime;
        for (int i = 1; i <= numLaps; ++i) {
            for (int j = 1; j < Math.min(18, i + 1); ++j) {
                f[i] = Math.min(f[i], f[i - j] + cost[j]);
            }
            f[i] += changeTime;
        }
        return f[numLaps];
    }
}
```

```cpp
class Solution {
public:
    int minimumFinishTime(vector<vector<int>>& tires, int changeTime, int numLaps) {
        int cost[18];
        memset(cost, 0x3f, sizeof(cost));
        for (auto& e : tires) {
            int f = e[0], r = e[1];
            int s = 0;
            long long t = f;
            for (int i = 1; t <= changeTime + f; ++i) {
                s += t;
                cost[i] = min(cost[i], s);
                t *= r;
            }
        }
        int f[numLaps + 1];
        memset(f, 0x3f, sizeof(f));
        f[0] = -changeTime;
        for (int i = 1; i <= numLaps; ++i) {
            for (int j = 1; j < min(18, i + 1); ++j) {
                f[i] = min(f[i], f[i - j] + cost[j]);
            }
            f[i] += changeTime;
        }
        return f[numLaps];
    }
};
```

```go
func minimumFinishTime(tires [][]int, changeTime int, numLaps int) int {
	const inf = 1 << 30
	cost := [18]int{}
	for i := range cost {
		cost[i] = inf
	}
	for _, e := range tires {
		f, r := e[0], e[1]
		s, t := 0, f
		for i := 1; t <= changeTime+f; i++ {
			s += t
			cost[i] = min(cost[i], s)
			t *= r
		}
	}
	f := make([]int, numLaps+1)
	for i := range f {
		f[i] = inf
	}
	f[0] = -changeTime
	for i := 1; i <= numLaps; i++ {
		for j := 1; j < min(18, i+1); j++ {
			f[i] = min(f[i], f[i-j]+cost[j])
		}
		f[i] += changeTime
	}
	return f[numLaps]
}
```

```ts
function minimumFinishTime(tires: number[][], changeTime: number, numLaps: number): number {
    const cost: number[] = Array(18).fill(Infinity);
    for (const [f, r] of tires) {
        let s = 0;
        let t = f;
        for (let i = 1; t <= changeTime + f; ++i) {
            s += t;
            cost[i] = Math.min(cost[i], s);
            t *= r;
        }
    }
    const f: number[] = Array(numLaps + 1).fill(Infinity);
    f[0] = -changeTime;
    for (let i = 1; i <= numLaps; ++i) {
        for (let j = 1; j < Math.min(18, i + 1); ++j) {
            f[i] = Math.min(f[i], f[i - j] + cost[j]);
        }
        f[i] += changeTime;
    }
    return f[numLaps];
}
```

<!-- tabs:end -->

<!-- end -->
