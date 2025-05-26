---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3528.Unit%20Conversion%20I/README_EN.md
tags:
    - Depth-First Search
    - Breadth-First Search
    - Graph
---

<!-- problem:start -->

# [3528. Unit Conversion I](https://leetcode.com/problems/unit-conversion-i)

[中文文档](/solution/3500-3599/3528.Unit%20Conversion%20I/README.md)

## Description

<!-- description:start -->

<p>There are <code>n</code> types of units indexed from <code>0</code> to <code>n - 1</code>. You are given a 2D integer array <code>conversions</code> of length <code>n - 1</code>, where <code>conversions[i] = [sourceUnit<sub>i</sub>, targetUnit<sub>i</sub>, conversionFactor<sub>i</sub>]</code>. This indicates that a single unit of type <code>sourceUnit<sub>i</sub></code> is equivalent to <code>conversionFactor<sub>i</sub></code> units of type <code>targetUnit<sub>i</sub></code>.</p>

<p>Return an array <code>baseUnitConversion</code> of length <code>n</code>, where <code>baseUnitConversion[i]</code> is the number of units of type <code>i</code> equivalent to a single unit of type 0. Since the answer may be large, return each <code>baseUnitConversion[i]</code> <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">conversions = [[0,1,2],[1,2,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,2,6]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Convert a single unit of type 0 into 2 units of type 1 using <code>conversions[0]</code>.</li>
	<li>Convert a single unit of type 0 into 6 units of type 2 using <code>conversions[0]</code>, then <code>conversions[1]</code>.</li>
</ul>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3528.Unit%20Conversion%20I/images/example1.png" style="width: 545px; height: 118px;" /></div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">conversions = [[0,1,2],[0,2,3],[1,3,4],[1,4,5],[2,5,2],[4,6,3],[5,7,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,2,3,8,10,6,30,24]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Convert a single unit of type 0 into 2 units of type 1 using <code>conversions[0]</code>.</li>
	<li>Convert a single unit of type 0 into 3 units of type 2 using <code>conversions[1]</code>.</li>
	<li>Convert a single unit of type 0 into 8 units of type 3 using <code>conversions[0]</code>, then <code>conversions[2]</code>.</li>
	<li>Convert a single unit of type 0 into 10 units of type 4 using <code>conversions[0]</code>, then <code>conversions[3]</code>.</li>
	<li>Convert a single unit of type 0 into 6 units of type 5 using <code>conversions[1]</code>, then <code>conversions[4]</code>.</li>
	<li>Convert a single unit of type 0 into 30 units of type 6 using <code>conversions[0]</code>, <code>conversions[3]</code>, then <code>conversions[5]</code>.</li>
	<li>Convert a single unit of type 0 into 24 units of type 7 using <code>conversions[1]</code>, <code>conversions[4]</code>, then <code>conversions[6]</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>conversions.length == n - 1</code></li>
	<li><code>0 &lt;= sourceUnit<sub>i</sub>, targetUnit<sub>i</sub> &lt; n</code></li>
	<li><code>1 &lt;= conversionFactor<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li>It is guaranteed that unit 0 can be converted into any other unit through a <strong>unique</strong> combination of conversions without using any conversions in the opposite direction.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS

Since the problem guarantees that unit 0 can be converted to any other unit through a unique conversion path, we can use Depth-First Search (DFS) to traverse all unit conversion relationships. Additionally, since the length of the $\textit{conversions}$ array is $n - 1$, representing $n - 1$ conversion relationships, we can treat the unit conversion relationships as a tree, where the root node is unit 0, and the other nodes are the other units.

We can use an adjacency list $g$ to represent the unit conversion relationships, where $g[i]$ represents the units that unit $i$ can convert to and the corresponding conversion factors.

Then, we start the DFS from the root node $0$, i.e., call the function $\textit{dfs}(s, \textit{mul})$, where $s$ represents the current unit, and $\textit{mul}$ represents the conversion factor from unit $0$ to unit $s$. Initially, $s = 0$, $\textit{mul} = 1$. In each recursion, we store the conversion factor $\textit{mul}$ of the current unit $s$ into the answer array, then traverse all adjacent units $t$ of the current unit $s$, and recursively call $\textit{dfs}(t, \textit{mul} \times w \mod (10^9 + 7))$, where $w$ is the conversion factor from unit $s$ to unit $t$.

Finally, we return the answer array.

The complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the number of units.

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
