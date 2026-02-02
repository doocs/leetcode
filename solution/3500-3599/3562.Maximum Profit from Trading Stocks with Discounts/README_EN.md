---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3562.Maximum%20Profit%20from%20Trading%20Stocks%20with%20Discounts/README_EN.md
rating: 2458
source: Weekly Contest 451 Q3
tags:
    - Tree
    - Depth-First Search
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3562. Maximum Profit from Trading Stocks with Discounts](https://leetcode.com/problems/maximum-profit-from-trading-stocks-with-discounts)

[中文文档](/solution/3500-3599/3562.Maximum%20Profit%20from%20Trading%20Stocks%20with%20Discounts/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code>, representing the number of employees in a company. Each employee is assigned a unique ID from 1 to <code>n</code>, and employee 1 is the CEO, is the direct or indirect boss of every employee. You are given two <strong>1-based </strong>integer arrays, <code>present</code> and <code>future</code>, each of length <code>n</code>, where:</p>

<ul>
	<li><code>present[i]</code> represents the <strong>current</strong> price at which the <code>i<sup>th</sup></code> employee can buy a stock today.</li>
	<li><code>future[i]</code> represents the <strong>expected</strong> price at which the <code>i<sup>th</sup></code> employee can sell the stock tomorrow.</li>
</ul>

<p>The company&#39;s hierarchy is represented by a 2D integer array <code>hierarchy</code>, where <code>hierarchy[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> means that employee <code>u<sub>i</sub></code> is the direct boss of employee <code>v<sub>i</sub></code>.</p>

<p>Additionally, you have an integer <code>budget</code> representing the total funds available for investment.</p>

<p>However, the company has a discount policy: if an employee&#39;s direct boss purchases their own stock, then the employee can buy their stock at <strong>half</strong> the original price (<code>floor(present[v] / 2)</code>).</p>

<p>Return the <strong>maximum</strong> profit that can be achieved without exceeding the given budget.</p>

<p><strong>Note:</strong></p>

<ul>
	<li>You may buy each stock at most <strong>once</strong>.</li>
	<li>You <strong>cannot</strong> use any profit earned from future stock prices to fund additional investments and must buy only from <code>budget</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, present = [1,2], future = [4,3], hierarchy = [[1,2]], budget = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3562.Maximum%20Profit%20from%20Trading%20Stocks%20with%20Discounts/images/screenshot-2025-04-10-at-053641.png" style="width: 200px; height: 80px;" /></p>

<ul>
	<li>Employee 1 buys the stock at price 1 and earns a profit of <code>4 - 1 = 3</code>.</li>
	<li>Since Employee 1 is the direct boss of Employee 2, Employee 2 gets a discounted price of <code>floor(2 / 2) = 1</code>.</li>
	<li>Employee 2 buys the stock at price 1 and earns a profit of <code>3 - 1 = 2</code>.</li>
	<li>The total buying cost is <code>1 + 1 = 2 &lt;= budget</code>. Thus, the maximum total profit achieved is <code>3 + 2 = 5</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, present = [3,4], future = [5,8], hierarchy = [[1,2]], budget = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3562.Maximum%20Profit%20from%20Trading%20Stocks%20with%20Discounts/images/screenshot-2025-04-10-at-053641.png" style="width: 200px; height: 80px;" /></p>

<ul>
	<li>Employee 2 buys the stock at price 4 and earns a profit of <code>8 - 4 = 4</code>.</li>
	<li>Since both employees cannot buy together, the maximum profit is 4.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, present = [4,6,8], future = [7,9,11], hierarchy = [[1,2],[1,3]], budget = 10</span></p>

<p><strong>Output:</strong> 10</p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3562.Maximum%20Profit%20from%20Trading%20Stocks%20with%20Discounts/images/image.png" style="width: 180px; height: 153px;" /></p>

<ul>
	<li>Employee 1 buys the stock at price 4 and earns a profit of <code>7 - 4 = 3</code>.</li>
	<li>Employee 3 would get a discounted price of <code>floor(8 / 2) = 4</code> and earns a profit of <code>11 - 4 = 7</code>.</li>
	<li>Employee 1 and Employee 3 buy their stocks at a total cost of <code>4 + 4 = 8 &lt;= budget</code>. Thus, the maximum total profit achieved is <code>3 + 7 = 10</code>.</li>
</ul>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, present = [5,2,3], future = [8,5,6], hierarchy = [[1,2],[2,3]], budget = 7</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3562.Maximum%20Profit%20from%20Trading%20Stocks%20with%20Discounts/images/screenshot-2025-04-10-at-054114.png" style="width: 300px; height: 85px;" /></p>

<ul>
	<li>Employee 1 buys the stock at price 5 and earns a profit of <code>8 - 5 = 3</code>.</li>
	<li>Employee 2 would get a discounted price of <code>floor(2 / 2) = 1</code> and earns a profit of <code>5 - 1 = 4</code>.</li>
	<li>Employee 3 would get a discounted price of <code>floor(3 / 2) = 1</code> and earns a profit of <code>6 - 1 = 5</code>.</li>
	<li>The total cost becomes <code>5 + 1 + 1 = 7&nbsp;&lt;= budget</code>. Thus, the maximum total profit achieved is <code>3 + 4 + 5 = 12</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 160</code></li>
	<li><code>present.length, future.length == n</code></li>
	<li><code>1 &lt;= present[i], future[i] &lt;= 50</code></li>
	<li><code>hierarchy.length == n - 1</code></li>
	<li><code>hierarchy[i] == [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>1 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>1 &lt;= budget &lt;= 160</code></li>
	<li>There are no duplicate edges.</li>
	<li>Employee 1 is the direct or indirect boss of every employee.</li>
	<li>The input graph <code>hierarchy </code>is <strong>guaranteed</strong> to have no cycles.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Tree Dynamic Programming

For each node $u$, we maintain a 2D array $f_u[j][pre]$, representing the maximum profit that can be obtained in the subtree rooted at $u$ with a budget not exceeding $j$ and whether $u$'s manager purchased stocks (where $pre=1$ means purchased, and $pre=0$ means not purchased). The answer is $f_1[\text{budget}][0]$.

For node $u$, the function $\text{dfs}(u)$ returns a $(\text{budget}+1) \times 2$ 2D array $f$, representing the maximum profit that can be obtained in the subtree rooted at $u$ with a budget not exceeding $j$ and whether $u$'s manager purchased stocks.

For $u$, we need to consider two things:

1. Whether node $u$ itself buys stocks (which will consume part of the budget $\text{cost}$, where $\text{cost} = \lfloor \text{present}[u] / (pre + 1) \rfloor$), and increase the profit by $\text{future}[u] - \text{cost}$.
2. How to allocate the budget among node $u$'s child nodes $v$ to maximize profit. We treat each child node's $\text{dfs}(v)$ as an "item" and use a knapsack approach to merge the subtree profits into the current $u$'s $\text{nxt}$ array.

In the specific implementation, we first initialize a $(\text{budget}+1) \times 2$ 2D array $\text{nxt}$, representing the profits that have been merged from child nodes. Then for each child node $v$, we recursively call $\text{dfs}(v)$ to get the profit array $\text{fv}$ of the child node, and use a knapsack approach to merge $\text{fv}$ into $\text{nxt}$.

The merge formula is:

$$
\text{nxt}[j][pre] = \max(\text{nxt}[j][pre], \text{nxt}[j - j_v][pre] + \text{fv}[j_v][pre])
$$

where $j_v$ represents the budget allocated to child node $v$.

After merging all child nodes, $\text{nxt}[j][pre]$ represents the maximum profit that can be obtained by allocating the entire budget $j$ to child nodes when $u$ itself has not yet decided whether to buy stocks, and $u$'s manager's purchase state is $pre$.

Finally, we decide whether $u$ purchases stocks.

- If $j \lt \text{cost}$, then $u$ cannot purchase stocks, in which case $f[j][pre] = \text{nxt}[j][0]$.
- If $j \geq \text{cost}$, then $u$ can choose to purchase or not purchase stocks, in which case $f[j][pre] = \max(\text{nxt}[j][0], \text{nxt}[j - \text{cost}][1] + (\text{future}[u] - \text{cost}))$.

Finally, return $f$.

The answer is $\text{dfs}(1)[\text{budget}][0]$.

The time complexity is $O(n \times \text{budget}^2)$, and the space complexity is $O(n \times \text{budget})$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxProfit(
        self,
        n: int,
        present: List[int],
        future: List[int],
        hierarchy: List[List[int]],
        budget: int,
    ) -> int:
        max = lambda a, b: a if a > b else b
        g = [[] for _ in range(n + 1)]
        for u, v in hierarchy:
            g[u].append(v)

        def dfs(u: int):
            nxt = [[0, 0] for _ in range(budget + 1)]
            for v in g[u]:
                fv = dfs(v)
                for j in range(budget, -1, -1):
                    for jv in range(j + 1):
                        for pre in (0, 1):
                            val = nxt[j - jv][pre] + fv[jv][pre]
                            if val > nxt[j][pre]:
                                nxt[j][pre] = val

            f = [[0, 0] for _ in range(budget + 1)]
            price = future[u - 1]

            for j in range(budget + 1):
                for pre in (0, 1):
                    cost = present[u - 1] // (pre + 1)
                    if j >= cost:
                        f[j][pre] = max(nxt[j][0], nxt[j - cost][1] + (price - cost))
                    else:
                        f[j][pre] = nxt[j][0]

            return f

        return dfs(1)[budget][0]
```

#### Java

```java
class Solution {
    private List<Integer>[] g;
    private int[] present;
    private int[] future;
    private int budget;

    public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
        this.present = present;
        this.future = future;
        this.budget = budget;

        g = new ArrayList[n + 1];
        Arrays.setAll(g, k -> new ArrayList<>());

        for (int[] e : hierarchy) {
            g[e[0]].add(e[1]);
        }

        return dfs(1)[budget][0];
    }

    private int[][] dfs(int u) {
        int[][] nxt = new int[budget + 1][2];

        for (int v : g[u]) {
            int[][] fv = dfs(v);
            for (int j = budget; j >= 0; j--) {
                for (int jv = 0; jv <= j; jv++) {
                    for (int pre = 0; pre < 2; pre++) {
                        int val = nxt[j - jv][pre] + fv[jv][pre];
                        if (val > nxt[j][pre]) {
                            nxt[j][pre] = val;
                        }
                    }
                }
            }
        }

        int[][] f = new int[budget + 1][2];
        int price = future[u - 1];

        for (int j = 0; j <= budget; j++) {
            for (int pre = 0; pre < 2; pre++) {
                int cost = present[u - 1] / (pre + 1);
                if (j >= cost) {
                    f[j][pre] = Math.max(nxt[j][0], nxt[j - cost][1] + (price - cost));
                } else {
                    f[j][pre] = nxt[j][0];
                }
            }
        }

        return f;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxProfit(int n, vector<int>& present, vector<int>& future, vector<vector<int>>& hierarchy, int budget) {
        vector<vector<int>> g(n + 1);
        for (auto& e : hierarchy) {
            g[e[0]].push_back(e[1]);
        }

        auto dfs = [&](const auto& dfs, int u) -> vector<array<int, 2>> {
            vector<array<int, 2>> nxt(budget + 1);
            for (int j = 0; j <= budget; j++) nxt[j] = {0, 0};

            for (int v : g[u]) {
                auto fv = dfs(dfs, v);
                for (int j = budget; j >= 0; j--) {
                    for (int jv = 0; jv <= j; jv++) {
                        for (int pre = 0; pre < 2; pre++) {
                            int val = nxt[j - jv][pre] + fv[jv][pre];
                            if (val > nxt[j][pre]) {
                                nxt[j][pre] = val;
                            }
                        }
                    }
                }
            }

            vector<array<int, 2>> f(budget + 1);
            int price = future[u - 1];

            for (int j = 0; j <= budget; j++) {
                for (int pre = 0; pre < 2; pre++) {
                    int cost = present[u - 1] / (pre + 1);
                    if (j >= cost) {
                        f[j][pre] = max(nxt[j][0], nxt[j - cost][1] + (price - cost));
                    } else {
                        f[j][pre] = nxt[j][0];
                    }
                }
            }

            return f;
        };

        return dfs(dfs, 1)[budget][0];
    }
};
```

#### Go

```go
func maxProfit(n int, present []int, future []int, hierarchy [][]int, budget int) int {
	g := make([][]int, n+1)
	for _, e := range hierarchy {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
	}

	var dfs func(u int) [][2]int
	dfs = func(u int) [][2]int {
		nxt := make([][2]int, budget+1)

		for _, v := range g[u] {
			fv := dfs(v)
			for j := budget; j >= 0; j-- {
				for jv := 0; jv <= j; jv++ {
					for pre := 0; pre < 2; pre++ {
						nxt[j][pre] = max(nxt[j][pre], nxt[j-jv][pre]+fv[jv][pre])
					}
				}
			}
		}

		f := make([][2]int, budget+1)
		price := future[u-1]

		for j := 0; j <= budget; j++ {
			for pre := 0; pre < 2; pre++ {
				cost := present[u-1] / (pre + 1)
				if j >= cost {
					buyProfit := nxt[j-cost][1] + (price - cost)
					f[j][pre] = max(nxt[j][0], buyProfit)
				} else {
					f[j][pre] = nxt[j][0]
				}
			}
		}
		return f
	}

	return dfs(1)[budget][0]
}
```

#### TypeScript

```ts
function maxProfit(
    n: number,
    present: number[],
    future: number[],
    hierarchy: number[][],
    budget: number,
): number {
    const g: number[][] = Array.from({ length: n + 1 }, () => []);

    for (const [u, v] of hierarchy) {
        g[u].push(v);
    }

    const dfs = (u: number): number[][] => {
        const nxt: number[][] = Array.from({ length: budget + 1 }, () => [0, 0]);

        for (const v of g[u]) {
            const fv = dfs(v);
            for (let j = budget; j >= 0; j--) {
                for (let jv = 0; jv <= j; jv++) {
                    for (let pre = 0; pre < 2; pre++) {
                        nxt[j][pre] = Math.max(nxt[j][pre], nxt[j - jv][pre] + fv[jv][pre]);
                    }
                }
            }
        }

        const f: number[][] = Array.from({ length: budget + 1 }, () => [0, 0]);
        const price = future[u - 1];

        for (let j = 0; j <= budget; j++) {
            for (let pre = 0; pre < 2; pre++) {
                const cost = Math.floor(present[u - 1] / (pre + 1));
                if (j >= cost) {
                    const profitIfBuy = nxt[j - cost][1] + (price - cost);
                    f[j][pre] = Math.max(nxt[j][0], profitIfBuy);
                } else {
                    f[j][pre] = nxt[j][0];
                }
            }
        }

        return f;
    };

    return dfs(1)[budget][0];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
