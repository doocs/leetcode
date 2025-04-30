---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3528.Unit%20Conversion%20I/README.md
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 图
---

<!-- problem:start -->

# [3528. 单位转换 I](https://leetcode.cn/problems/unit-conversion-i)

[English Version](/solution/3500-3599/3528.Unit%20Conversion%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有 <code>n</code> 种单位，编号从 <code>0</code> 到 <code>n - 1</code>。给你一个二维整数数组 <code>conversions</code>，长度为 <code>n - 1</code>，其中 <code>conversions[i] = [sourceUnit<sub>i</sub>, targetUnit<sub>i</sub>, conversionFactor<sub>i</sub>]</code>&nbsp;，表示一个&nbsp;<code>sourceUnit<sub>i</sub></code> 类型的单位等于 <code>conversionFactor<sub>i</sub></code> 个 <code>targetUnit<sub>i</sub></code> 类型的单位。</p>

<p>请你返回一个长度为 <code>n</code> 的数组 <code>baseUnitConversion</code>，其中 <code>baseUnitConversion[i]</code> 表示 <strong>一个</strong> 0 类型单位等于多少个 i 类型单位。由于结果可能很大，请返回每个 <code>baseUnitConversion[i]</code> 对 <code>10<sup>9</sup> + 7</code> 取模后的值。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">conversions = [[0,1,2],[1,2,3]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[1,2,6]</span></p>

<p><strong>解释：</strong></p>

<ul>
 <li>使用 <code>conversions[0]</code>：将一个 0 类型单位转换为 2 个 1 类型单位。</li>
 <li>使用&nbsp;<code>conversions[0]</code>&nbsp;和&nbsp;<code>conversions[1]</code>&nbsp;将一个 0 类型单位转换为 6 个 2 类型单位。</li>
</ul>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3528.Unit%20Conversion%20I/images/1745660099-FZhVTM-example1.png" style="width: 545px; height: 119px;" /></div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">conversions = [[0,1,2],[0,2,3],[1,3,4],[1,4,5],[2,5,2],[4,6,3],[5,7,4]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[1,2,3,8,10,6,30,24]</span></p>

<p><strong>解释：</strong></p>

<ul>
 <li>使用 <code>conversions[0]</code>&nbsp;将一个 0 类型单位转换为 2 个 1 类型单位。</li>
 <li>使用 <code>conversions[1]</code>&nbsp;将一个 0 类型单位转换为 3 个 2 类型单位。</li>
 <li>使用 <code>conversions[0]</code> 和 <code>conversions[2]</code>&nbsp;将一个 0 类型单位转换为 8 个 3 类型单位。</li>
 <li>使用 <code>conversions[0]</code> 和 <code>conversions[3]</code>&nbsp;将一个 0 类型单位转换为 10 个 4 类型单位。</li>
 <li>使用 <code>conversions[1]</code> 和 <code>conversions[4]</code>&nbsp;将一个 0 类型单位转换为 6 个 5 类型单位。</li>
 <li>使用 <code>conversions[0]</code>、<code>conversions[3]</code> 和 <code>conversions[5]</code>&nbsp;将一个 0 类型单位转换为 30 个 6 类型单位。</li>
 <li>使用 <code>conversions[1]</code>、<code>conversions[4]</code> 和 <code>conversions[6]</code>&nbsp;将一个 0 类型单位转换为 24 个 7 类型单位。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
 <li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
 <li><code>conversions.length == n - 1</code></li>
 <li><code>0 &lt;= sourceUnit<sub>i</sub>, targetUnit<sub>i</sub> &lt; n</code></li>
 <li><code>1 &lt;= conversionFactor<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
 <li>保证单位&nbsp;0 可以通过&nbsp;<strong>唯一&nbsp;</strong>的转换路径（不需要反向转换）转换为任何其他单位。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

由于题目保证了单位 0 可以通过唯一的转换路径转换为其他单位，因此我们可以使用深度优先搜索（DFS）来遍历所有单位的转换关系。另外，由于 $\textit{conversions}$ 数组的长度为 $n - 1$，表示有 $n - 1$ 条转换关系，因此我们可以将单位转换关系看作一棵树，根节点为单位 0，其他节点为其他单位。

我们可以用一个邻接表 $g$ 来表示单位转换关系，其中 $g[i]$ 表示单位 $i$ 可以转换到的单位和对应的转换因子。

然后，我们从根节点 $0$ 开始进行深度优先搜索，即调函数 $\textit{dfs}(s, \textit{mul})$，其中 $s$ 表示当前单位，$\textit{mul}$ 表示从单位 $0$ 转换到单位 $s$ 的转换因子。初始时 $s = 0$, $\textit{mul} = 1$。在每次递归中，我们将当前单位 $s$ 的转换因子 $\textit{mul}$ 存储到答案数组中，然后遍历当前单位 $s$ 的所有邻接单位 $t$，递归调用 $\textit{dfs}(t, \textit{mul} \times w \mod (10^9 + 7))$，其中 $w$ 为单位 $s$ 转换到单位 $t$ 的转换因子。

最后，我们返回答案数组即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为单位的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def baseUnitConversions(self, conversions: List[List[int]]) -> List[int]:
        def dfs(s: int, mul: int) -> None:
            ans[s] = mul
            for t, w in g[s]:
                dfs(t, mul * w % mod)

        mod = 10**9 + 7
        n = len(conversions) + 1
        g = [[] for _ in range(n)]
        for s, t, w in conversions:
            g[s].append((t, w))
        ans = [0] * n
        dfs(0, 1)
        return ans
```

#### Java

```java
class Solution {
    private final int mod = (int) 1e9 + 7;
    private List<int[]>[] g;
    private int[] ans;
    private int n;

    public int[] baseUnitConversions(int[][] conversions) {
        n = conversions.length + 1;
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        ans = new int[n];
        for (var e : conversions) {
            g[e[0]].add(new int[] {e[1], e[2]});
        }
        dfs(0, 1);
        return ans;
    }

    private void dfs(int s, long mul) {
        ans[s] = (int) mul;
        for (var e : g[s]) {
            dfs(e[0], mul * e[1] % mod);
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> baseUnitConversions(vector<vector<int>>& conversions) {
        const int mod = 1e9 + 7;
        int n = conversions.size() + 1;
        vector<vector<pair<int, int>>> g(n);
        vector<int> ans(n);
        for (const auto& e : conversions) {
            g[e[0]].push_back({e[1], e[2]});
        }
        auto dfs = [&](this auto&& dfs, int s, long long mul) -> void {
            ans[s] = mul;
            for (auto [t, w] : g[s]) {
                dfs(t, mul * w % mod);
            }
        };
        dfs(0, 1);
        return ans;
    }
};
```

#### Go

```go
func baseUnitConversions(conversions [][]int) []int {
	const mod = int(1e9 + 7)
	n := len(conversions) + 1

	g := make([][]struct{ t, w int }, n)
	for _, e := range conversions {
		s, t, w := e[0], e[1], e[2]
		g[s] = append(g[s], struct{ t, w int }{t, w})
	}

	ans := make([]int, n)

	var dfs func(s int, mul int)
	dfs = func(s int, mul int) {
		ans[s] = mul
		for _, e := range g[s] {
			dfs(e.t, mul*e.w%mod)
		}
	}

	dfs(0, 1)
	return ans
}
```

#### TypeScript

```ts
function baseUnitConversions(conversions: number[][]): number[] {
    const mod = BigInt(1e9 + 7);
    const n = conversions.length + 1;
    const g: { t: number; w: number }[][] = Array.from({ length: n }, () => []);
    for (const [s, t, w] of conversions) {
        g[s].push({ t, w });
    }
    const ans: number[] = Array(n).fill(0);
    const dfs = (s: number, mul: number) => {
        ans[s] = mul;
        for (const { t, w } of g[s]) {
            dfs(t, Number((BigInt(mul) * BigInt(w)) % mod));
        }
    };
    dfs(0, 1);
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
