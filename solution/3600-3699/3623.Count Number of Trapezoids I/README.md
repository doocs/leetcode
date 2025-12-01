---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3623.Count%20Number%20of%20Trapezoids%20I/README.md
rating: 1579
source: 第 459 场周赛 Q2
tags:
    - 几何
    - 数组
    - 哈希表
    - 数学
---

<!-- problem:start -->

# [3623. 统计梯形的数目 I](https://leetcode.cn/problems/count-number-of-trapezoids-i)

[English Version](/solution/3600-3699/3623.Count%20Number%20of%20Trapezoids%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p data-end="189" data-start="146">给你一个二维整数数组 <code>points</code>，其中 <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> 表示第 <code>i</code> 个点在笛卡尔平面上的坐标。</p>

<p data-end="579" data-start="405"><strong>水平梯形</strong> 是一种凸四边形，具有&nbsp;<strong data-end="496" data-start="475">至少一对&nbsp;</strong>水平边（即平行于 x 轴的边）。两条直线平行当且仅当它们的斜率相同。</p>

<p data-end="579" data-start="405">返回可以从 <code>points</code> 中任意选择四个不同点组成的&nbsp;<strong>水平梯形 </strong>数量。</p>

<p>由于答案可能非常大，请返回结果对 <code>10<sup>9</sup> + 7</code> 取余数后的值。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">points = [[1,0],[2,0],[3,0],[2,2],[3,2]]</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3623.Count%20Number%20of%20Trapezoids%20I/images/desmos-graph-6.png" style="width: 250px; height: 250px;" /> <img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3623.Count%20Number%20of%20Trapezoids%20I/images/desmos-graph-7.png" style="width: 250px; height: 250px;" /> <img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3623.Count%20Number%20of%20Trapezoids%20I/images/desmos-graph-8.png" style="width: 250px; height: 250px;" /></p>

<p>有三种不同方式选择四个点组成一个水平梯形：</p>

<ul>
	<li data-end="247" data-start="193">使用点 <code data-end="213" data-start="206">[1,0]</code>、<code data-end="222" data-start="215">[2,0]</code>、<code data-end="231" data-start="224">[3,2]</code> 和 <code data-end="244" data-start="237">[2,2]</code>。</li>
	<li data-end="305" data-start="251">使用点 <code data-end="271" data-start="264">[2,0]</code>、<code data-end="280" data-start="273">[3,0]</code>、<code data-end="289" data-start="282">[3,2]</code> 和 <code data-end="302" data-start="295">[2,2]</code>。</li>
	<li data-end="361" data-start="309">使用点 <code data-end="329" data-start="322">[1,0]</code>、<code data-end="338" data-start="331">[3,0]</code>、<code data-end="347" data-start="340">[3,2]</code> 和 <code data-end="360" data-start="353">[2,2]</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">points = [[0,0],[1,0],[0,1],[2,1]]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3623.Count%20Number%20of%20Trapezoids%20I/images/desmos-graph-5.png" style="width: 250px; height: 250px;" /></p>

<p>只有一种方式可以组成一个水平梯形。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>4 &lt;= points.length &lt;= 10<sup>5</sup></code></li>
	<li><code>–10<sup>8</sup> &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>8</sup></code></li>
	<li>所有点两两不同。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

根据题目描述，水平边满足 $y$ 坐标相同，因此我们可以根据 $y$ 坐标将点进行分组，统计每个 $y$ 坐标对应的点的数量。

我们用一个哈希表 $\textit{cnt}$ 来存储每个 $y$ 坐标对应的点的数量。对于每个 $y$ 坐标 $y_i$，假设对应的点的数量为 $v$，那么从这些点中选择两点作为水平边的方式有 $\binom{v}{2} = \frac{v(v-1)}{2}$ 种，记为 $t$。

我们用一个变量 $s$ 来记录之前所有 $y$ 坐标对应的水平边的数量之和。那么，我们可以将当前 $y$ 坐标对应的水平边的数量 $t$ 与之前所有 $y$ 坐标对应的水平边的数量之和 $s$ 相乘，得到以当前 $y$ 坐标为一对水平边的梯形的数量，并将其累加到答案中。最后，我们将当前 $y$ 坐标对应的水平边的数量 $t$ 累加到 $s$ 中，以便后续计算。

注意，由于答案可能非常大，我们需要对 $10^9 + 7$ 取余数。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是点的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countTrapezoids(self, points: List[List[int]]) -> int:
        mod = 10**9 + 7
        cnt = Counter(p[1] for p in points)
        ans = s = 0
        for v in cnt.values():
            t = v * (v - 1) // 2
            ans = (ans + s * t) % mod
            s += t
        return ans
```

#### Java

```java
class Solution {
    public int countTrapezoids(int[][] points) {
        final int mod = (int) 1e9 + 7;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (var p : points) {
            cnt.merge(p[1], 1, Integer::sum);
        }
        long ans = 0, s = 0;
        for (int v : cnt.values()) {
            long t = 1L * v * (v - 1) / 2;
            ans = (ans + s * t) % mod;
            s += t;
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countTrapezoids(vector<vector<int>>& points) {
        const int mod = 1e9 + 7;
        unordered_map<int, int> cnt;
        for (auto& p : points) {
            cnt[p[1]]++;
        }
        long long ans = 0, s = 0;
        for (auto& [_, v] : cnt) {
            long long t = 1LL * v * (v - 1) / 2;
            ans = (ans + s * t) % mod;
            s += t;
        }
        return (int) ans;
    }
};
```

#### Go

```go
func countTrapezoids(points [][]int) int {
	const mod = 1_000_000_007
	cnt := make(map[int]int)
	for _, p := range points {
		cnt[p[1]]++
	}

	var ans, s int64
	for _, v := range cnt {
		t := int64(v) * int64(v-1) / 2
		ans = (ans + s*t) % mod
		s += t
	}
	return int(ans)
}
```

#### TypeScript

```ts
function countTrapezoids(points: number[][]): number {
    const mod = 1_000_000_007;
    const cnt = new Map<number, number>();

    for (const p of points) {
        cnt.set(p[1], (cnt.get(p[1]) ?? 0) + 1);
    }

    let ans = 0;
    let s = 0;
    for (const v of cnt.values()) {
        const t = (v * (v - 1)) / 2;
        const mul = BigInt(s) * BigInt(t);
        ans = Number((BigInt(ans) + mul) % BigInt(mod));
        s += t;
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
