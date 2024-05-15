---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1908.Game%20of%20Nim/README.md
tags:
    - 位运算
    - 脑筋急转弯
    - 数组
    - 数学
    - 动态规划
    - 博弈
---

# [1908. Nim 游戏 II 🔒](https://leetcode.cn/problems/game-of-nim)

[English Version](/solution/1900-1999/1908.Game%20of%20Nim/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Alice 和&nbsp;Bob 交替进行一个游戏，<strong>由 Alice 先手</strong>。</p>

<p>在游戏中，共有&nbsp;<code>n</code>&nbsp;堆石头。在每个玩家的回合中，玩家需要 <strong>选择</strong> 任一非空石头堆，从中移除任意 <strong>非零</strong> 数量的石头。如果不能移除任意的石头，就输掉游戏，同时另一人获胜。</p>

<p>给定一个整数数组&nbsp;<code>piles</code> ，<code>piles[i]</code> 为 第&nbsp;<code>i</code>&nbsp;堆石头的数量，如果 Alice 能获胜返回&nbsp;<code>true</code><em>&nbsp;</em>，反之返回&nbsp;<code>false</code><em>&nbsp;。</em></p>

<p>Alice 和 Bob 都会采取<strong> 最优策略 </strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>piles = [1]
<strong>输出：</strong>true
<strong>解释：</strong>只有一种可能的情况：
- 第一回合，Alice 移除了第 1 堆中 1 块石头。piles = [0]。
- 第二回合，Bob 没有任何石头可以移除。Alice 获胜。
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>piles = [1,1]
<strong>输出：</strong>false
<strong>解释：</strong>可以证明，Bob一定能获胜。一种可能的情况：
- 第一回合，Alice 移除了第 1 堆中 1 块石头。 piles = [0,1]。
- 第二回合，Bob 移除了第 2 堆中 1 块石头。 piles = [0,0]。
- 第三回合，Alice 没有任何石头可以移除。Bob 获胜。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>piles = [1,2,3]
<strong>输出：</strong>false
<strong>解释：</strong>可以证明，Bob一定能获胜。一种可能的情况：
- 第一回合，Alice 移除了第 3 堆中 3 块石头。 piles = [1,2,0]。
- 第二回合，Bob 移除了第 2 堆中 1 块石头。 piles = [1,1,0]。
- 第三回合，Alice 移除了第 1 堆中 1 块石头。piles = [0,1,0]。
- 第四回合，Bob 移除了第 2 堆中 1 块石头。 piles = [0,0,0]。
- 第三回合，Alice 没有任何石头可以移除。Bob 获胜。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == piles.length</code></li>
	<li><code>1 &lt;= n &lt;= 7</code></li>
	<li><code>1 &lt;= piles[i] &lt;= 7</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你能想出一个&nbsp;<strong>线性时间&nbsp;</strong>的解决方案吗？虽然这一答案可能超出了面试所需的范围，但了解它可能会很有趣。</p>

## 解法

### 方法一：记忆化搜索

我们发现，一共最多有 $7$ 堆石头，每堆石头最多有 $7$ 个，那么一共有 $7^7$ 种状态，因此我们可以用一个八进制数来表示当前的状态。

接下来，我们用记忆化搜索的方法来解决这个问题。定义一个函数 $dfs(piles)$，表示当前的状态为 $piles$ 时，当前玩家是否能获胜。

函数 $dfs(piles)$ 的执行过程如下：

-   如果 $piles$ 所表示的状态已经被计算过，直接返回结果；
-   否则，我们枚举每一堆石头，尝试移除 $1,2,3,...,x$ 个石头，如果移除后的状态 $piles'$ 不能获胜，那么当前玩家就能获胜，返回结果。
-   如果所有的移除方案都不能获胜，那么当前玩家不能获胜，返回结果。

时间复杂度 $(7^7 \times 7^2)$，空间复杂度 $O(7^7)$。

<!-- tabs:start -->

```python
class Solution:
    def nimGame(self, piles: List[int]) -> bool:
        @cache
        def dfs(st):
            lst = list(st)
            for i, x in enumerate(lst):
                for j in range(1, x + 1):
                    lst[i] -= j
                    if not dfs(tuple(lst)):
                        return True
                    lst[i] += j
            return False

        return dfs(tuple(piles))
```

```java
class Solution {
    private Map<Integer, Boolean> memo = new HashMap<>();
    private int[] p = new int[8];

    public Solution() {
        p[0] = 1;
        for (int i = 1; i < 8; ++i) {
            p[i] = p[i - 1] * 8;
        }
    }

    public boolean nimGame(int[] piles) {
        return dfs(piles);
    }

    private boolean dfs(int[] piles) {
        int st = f(piles);
        if (memo.containsKey(st)) {
            return memo.get(st);
        }
        for (int i = 0; i < piles.length; ++i) {
            for (int j = 1; j <= piles[i]; ++j) {
                piles[i] -= j;
                if (!dfs(piles)) {
                    piles[i] += j;
                    memo.put(st, true);
                    return true;
                }
                piles[i] += j;
            }
        }
        memo.put(st, false);
        return false;
    }

    private int f(int[] piles) {
        int st = 0;
        for (int i = 0; i < piles.length; ++i) {
            st += piles[i] * p[i];
        }
        return st;
    }
}
```

```cpp
class Solution {
public:
    bool nimGame(vector<int>& piles) {
        unordered_map<int, int> memo;
        int p[8] = {1};
        for (int i = 1; i < 8; ++i) {
            p[i] = p[i - 1] * 8;
        }
        auto f = [&](vector<int>& piles) {
            int st = 0;
            for (int i = 0; i < piles.size(); ++i) {
                st += piles[i] * p[i];
            }
            return st;
        };
        function<bool(vector<int>&)> dfs = [&](vector<int>& piles) {
            int st = f(piles);
            if (memo.count(st)) {
                return memo[st];
            }
            for (int i = 0; i < piles.size(); ++i) {
                for (int j = 1; j <= piles[i]; ++j) {
                    piles[i] -= j;
                    if (!dfs(piles)) {
                        piles[i] += j;
                        return memo[st] = true;
                    }
                    piles[i] += j;
                }
            }
            return memo[st] = false;
        };
        return dfs(piles);
    }
};
```

```go
func nimGame(piles []int) bool {
	memo := map[int]bool{}
	p := make([]int, 8)
	p[0] = 1
	for i := 1; i < 8; i++ {
		p[i] = p[i-1] * 8
	}
	f := func(piles []int) int {
		st := 0
		for i, x := range piles {
			st += x * p[i]
		}
		return st
	}
	var dfs func(piles []int) bool
	dfs = func(piles []int) bool {
		st := f(piles)
		if v, ok := memo[st]; ok {
			return v
		}
		for i, x := range piles {
			for j := 1; j <= x; j++ {
				piles[i] -= j
				if !dfs(piles) {
					piles[i] += j
					memo[st] = true
					return true
				}
				piles[i] += j
			}
		}
		memo[st] = false
		return false
	}
	return dfs(piles)
}
```

```ts
function nimGame(piles: number[]): boolean {
    const p: number[] = Array(8).fill(1);
    for (let i = 1; i < 8; ++i) {
        p[i] = p[i - 1] * 8;
    }
    const f = (piles: number[]): number => {
        let st = 0;
        for (let i = 0; i < piles.length; ++i) {
            st += piles[i] * p[i];
        }
        return st;
    };
    const memo: Map<number, boolean> = new Map();
    const dfs = (piles: number[]): boolean => {
        const st = f(piles);
        if (memo.has(st)) {
            return memo.get(st)!;
        }
        for (let i = 0; i < piles.length; ++i) {
            for (let j = 1; j <= piles[i]; ++j) {
                piles[i] -= j;
                if (!dfs(piles)) {
                    piles[i] += j;
                    memo.set(st, true);
                    return true;
                }
                piles[i] += j;
            }
        }
        memo.set(st, false);
        return false;
    };
    return dfs(piles);
}
```

<!-- tabs:end -->

<!-- end -->
