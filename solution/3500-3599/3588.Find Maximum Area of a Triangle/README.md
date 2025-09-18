---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3588.Find%20Maximum%20Area%20of%20a%20Triangle/README.md
rating: 1818
source: 第 159 场双周赛 Q2
tags:
    - 贪心
    - 几何
    - 数组
    - 哈希表
    - 数学
    - 枚举
---

<!-- problem:start -->

# [3588. 找到最大三角形面积](https://leetcode.cn/problems/find-maximum-area-of-a-triangle)

[English Version](/solution/3500-3599/3588.Find%20Maximum%20Area%20of%20a%20Triangle/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二维数组 <code>coords</code>，大小为 <code>n x 2</code>，表示一个无限笛卡尔平面上 <code>n</code> 个点的坐标。</p>

<p>找出一个 <strong>最大</strong>&nbsp;三角形的 <strong>两倍&nbsp;</strong>面积，其中三角形的三个顶点来自 <code>coords</code> 中的任意三个点，并且该三角形至少有一条边与 x 轴或 y 轴平行。严格地说，如果该三角形的最大面积为 <code>A</code>，则返回 <code>2 * A</code>。</p>

<p>如果不存在这样的三角形，返回 -1。</p>

<p><strong>注意</strong>，三角形的面积 <strong>不能</strong> 为零。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">coords = [[1,1],[1,2],[3,2],[3,3]]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3588.Find%20Maximum%20Area%20of%20a%20Triangle/images/image-20250420010047-1.png" style="width: 300px; height: 289px;" /></p>

<p>图中的三角形的底边为 1，高为 2。因此，它的面积为 <code>1/2 * 底边 * 高 = 1</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">coords = [[1,1],[2,2],[3,3]]</span></p>

<p><strong>输出：</strong> <span class="example-io">-1</span></p>

<p><strong>解释：</strong></p>

<p>唯一可能的三角形的顶点是 <code>(1, 1)</code>、<code>(2, 2)</code> 和 <code>(3, 3)</code>。它的任意边都不与 x 轴或 y 轴平行。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == coords.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= coords[i][0], coords[i][1] &lt;= 10<sup>6</sup></code></li>
	<li>所有 <code>coords[i]</code> 都是 <strong>唯一</strong> 的。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举 + 哈希表

题目要求三角形的两倍面积，因此我们可以直接计算三角形的底边和高的乘积。

又因为三角形至少有一条边与 $x$ 轴或 $y$ 轴平行，我们可以枚举与 $x$ 轴平行的边，计算所有可能的三角形的两倍面积，然后将 $\textit{coords}$ 横纵坐标交换后，重复上述过程，计算与 $y$ 轴平行的边的所有可能的三角形的两倍面积。

因此，我们设计一个函数 $\textit{calc}$ 来计算与 $y$轴平行的边的所有可能的三角形的两倍面积。

我们用两个哈希表 $\textit{f}$ 和 $\textit{g}$ 来记录每个横坐标对应的最小纵坐标和最大纵坐标。然后我们遍历 $\textit{coords}$，更新哈希表 $\textit{f}$ 和 $\textit{g}$，同时记录横坐标的最小值和最大值。最后，我们遍历哈希表 $\textit{f}$，计算每个横坐标对应的三角形的两倍面积，并更新答案。

在主函数中，我们先调用 $\textit{calc}$ 函数计算与 $y$ 轴平行的边的所有可能的三角形的两倍面积，然后将 $\textit{coords}$ 横纵坐标交换后，重复上述过程，计算与 $x$ 轴平行的边的所有可能的三角形的两倍面积。最后返回答案，如果答案为 0，则返回 -1。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是 $\textit{coords}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxArea(self, coords: List[List[int]]) -> int:
        def calc() -> int:
            mn, mx = inf, 0
            f = {}
            g = {}
            for x, y in coords:
                mn = min(mn, x)
                mx = max(mx, x)
                if x in f:
                    f[x] = min(f[x], y)
                    g[x] = max(g[x], y)
                else:
                    f[x] = g[x] = y
            ans = 0
            for x, y in f.items():
                d = g[x] - y
                ans = max(ans, d * max(mx - x, x - mn))
            return ans

        ans = calc()
        for c in coords:
            c[0], c[1] = c[1], c[0]
        ans = max(ans, calc())
        return ans if ans else -1
```

#### Java

```java
class Solution {
    public long maxArea(int[][] coords) {
        long ans = calc(coords);
        for (int[] c : coords) {
            int tmp = c[0];
            c[0] = c[1];
            c[1] = tmp;
        }
        ans = Math.max(ans, calc(coords));
        return ans > 0 ? ans : -1;
    }

    private long calc(int[][] coords) {
        int mn = Integer.MAX_VALUE, mx = 0;
        Map<Integer, Integer> f = new HashMap<>();
        Map<Integer, Integer> g = new HashMap<>();

        for (int[] c : coords) {
            int x = c[0], y = c[1];
            mn = Math.min(mn, x);
            mx = Math.max(mx, x);
            if (f.containsKey(x)) {
                f.put(x, Math.min(f.get(x), y));
                g.put(x, Math.max(g.get(x), y));
            } else {
                f.put(x, y);
                g.put(x, y);
            }
        }

        long ans = 0;
        for (var e : f.entrySet()) {
            int x = e.getKey();
            int y = e.getValue();
            int d = g.get(x) - y;
            ans = Math.max(ans, (long) d * Math.max(mx - x, x - mn));
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxArea(vector<vector<int>>& coords) {
        auto calc = [&]() -> long long {
            int mn = INT_MAX, mx = 0;
            unordered_map<int, int> f, g;
            for (auto& c : coords) {
                int x = c[0], y = c[1];
                mn = min(mn, x);
                mx = max(mx, x);
                if (f.count(x)) {
                    f[x] = min(f[x], y);
                    g[x] = max(g[x], y);
                } else {
                    f[x] = y;
                    g[x] = y;
                }
            }
            long long ans = 0;
            for (auto& [x, y] : f) {
                int d = g[x] - y;
                ans = max(ans, 1LL * d * max(mx - x, x - mn));
            }
            return ans;
        };

        long long ans = calc();
        for (auto& c : coords) {
            swap(c[0], c[1]);
        }
        ans = max(ans, calc());
        return ans > 0 ? ans : -1;
    }
};
```

#### Go

```go
func maxArea(coords [][]int) int64 {
	calc := func() int64 {
		mn, mx := int(1e9), 0
		f := make(map[int]int)
		g := make(map[int]int)
		for _, c := range coords {
			x, y := c[0], c[1]
			mn = min(mn, x)
			mx = max(mx, x)
			if _, ok := f[x]; ok {
				f[x] = min(f[x], y)
				g[x] = max(g[x], y)
			} else {
				f[x] = y
				g[x] = y
			}
		}
		var ans int64
		for x, y := range f {
			d := g[x] - y
			ans = max(ans, int64(d)*int64(max(mx-x, x-mn)))
		}
		return ans
	}

	ans := calc()
	for _, c := range coords {
		c[0], c[1] = c[1], c[0]
	}
	ans = max(ans, calc())
	if ans > 0 {
		return ans
	}
	return -1
}
```

#### TypeScript

```ts
function maxArea(coords: number[][]): number {
    function calc(): number {
        let [mn, mx] = [Infinity, 0];
        const f = new Map<number, number>();
        const g = new Map<number, number>();

        for (const [x, y] of coords) {
            mn = Math.min(mn, x);
            mx = Math.max(mx, x);
            if (f.has(x)) {
                f.set(x, Math.min(f.get(x)!, y));
                g.set(x, Math.max(g.get(x)!, y));
            } else {
                f.set(x, y);
                g.set(x, y);
            }
        }

        let ans = 0;
        for (const [x, y] of f) {
            const d = g.get(x)! - y;
            ans = Math.max(ans, d * Math.max(mx - x, x - mn));
        }
        return ans;
    }

    let ans = calc();
    for (const c of coords) {
        [c[0], c[1]] = [c[1], c[0]];
    }
    ans = Math.max(ans, calc());
    return ans > 0 ? ans : -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
