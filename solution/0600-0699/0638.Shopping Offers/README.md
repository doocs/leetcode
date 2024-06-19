---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0638.Shopping%20Offers/README.md
tags:
    - 位运算
    - 记忆化搜索
    - 数组
    - 动态规划
    - 回溯
    - 状态压缩
---

<!-- problem:start -->

# [638. 大礼包](https://leetcode.cn/problems/shopping-offers)

[English Version](/solution/0600-0699/0638.Shopping%20Offers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>在 LeetCode 商店中， 有 <code>n</code> 件在售的物品。每件物品都有对应的价格。然而，也有一些大礼包，每个大礼包以优惠的价格捆绑销售一组物品。</p>

<p>给你一个整数数组 <code>price</code> 表示物品价格，其中 <code>price[i]</code> 是第 <code>i</code> 件物品的价格。另有一个整数数组 <code>needs</code> 表示购物清单，其中 <code>needs[i]</code> 是需要购买第 <code>i</code> 件物品的数量。</p>

<p>还有一个数组 <code>special</code> 表示大礼包，<code>special[i]</code> 的长度为 <code>n + 1</code> ，其中 <code>special[i][j]</code> 表示第 <code>i</code> 个大礼包中内含第 <code>j</code> 件物品的数量，且 <code>special[i][n]</code> （也就是数组中的最后一个整数）为第 <code>i</code> 个大礼包的价格。</p>

<p>返回<strong> 确切 </strong>满足购物清单所需花费的最低价格，你可以充分利用大礼包的优惠活动。你不能购买超出购物清单指定数量的物品，即使那样会降低整体价格。任意大礼包可无限次购买。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>price = [2,5], special = [[3,0,5],[1,2,10]], needs = [3,2]
<strong>输出：</strong>14
<strong>解释：</strong>有 A 和 B 两种物品，价格分别为 ¥2 和 ¥5 。 
大礼包 1 ，你可以以 ¥5 的价格购买 3A 和 0B 。 
大礼包 2 ，你可以以 ¥10 的价格购买 1A 和 2B 。 
需要购买 3 个 A 和 2 个 B ， 所以付 ¥10 购买 1A 和 2B（大礼包 2），以及 ¥4 购买 2A 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>price = [2,3,4], special = [[1,1,0,4],[2,2,1,9]], needs = [1,2,1]
<strong>输出：</strong>11
<strong>解释：</strong>A ，B ，C 的价格分别为 ¥2 ，¥3 ，¥4 。
可以用 ¥4 购买 1A 和 1B ，也可以用 ¥9 购买 2A ，2B 和 1C 。 
需要买 1A ，2B 和 1C ，所以付 ¥4 买 1A 和 1B（大礼包 1），以及 ¥3 购买 1B ， ¥4 购买 1C 。 
不可以购买超出待购清单的物品，尽管购买大礼包 2 更加便宜。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == price.length</code></li>
	<li><code>n == needs.length</code></li>
	<li><code>1 <= n <= 6</code></li>
	<li><code>0 <= price[i] <= 10</code></li>
	<li><code>0 <= needs[i] <= 10</code></li>
	<li><code>1 <= special.length <= 100</code></li>
	<li><code>special[i].length == n + 1</code></li>
	<li><code>0 <= special[i][j] <= 50</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：状态压缩 + 记忆化搜索

我们注意到，题目中物品的种类 $n \leq 6$，而每种物品需要购买的数量不超过 $10$，我们可以用 $4$ 个二进制位来表示每种物品需要购买的数量，这样，我们只需要最多 $6 \times 4 = 24$ 个二进制位来表示整个购物清单。

我们首先将购物清单 $\text{needs}$ 转换为一个整数 $\text{mask}$，其中第 $i$ 个物品需要购买的数量存储在 $\text{mask}$ 的第 $i \times 4$ 位到第 $(i + 1) \times 4 - 1$ 位。例如，当 $\text{needs} = [1, 2, 1]$ 时，有 $\text{mask} = 0b0001\_0010\_0001$。

然后，我们设计一个函数 $\text{dfs}(cur)$，表示当前购物清单的状态为 $\text{cur}$ 时，我们需要花费的最少金额。那么答案即为 $\text{dfs}(\text{mask})$。

函数 $\text{dfs}(cur)$ 的计算方法如下：

-   我们首先计算当前购物清单 $\text{cur}$ 不使用大礼包时的花费，记为 $\text{ans}$。
-   然后，我们遍历每一个大礼包 $\text{offer}$，如果当前购物清单 $\text{cur}$ 能够使用大礼包 $\text{offer}$，即 $\text{cur}$ 中每种物品的数量都不小于大礼包 $\text{offer}$ 中的数量，那么我们可以尝试使用这个大礼包。我们将 $\text{cur}$ 中每种物品的数量减去大礼包 $\text{offer}$ 中的数量，得到一个新的购物清单 $\text{nxt}$，然后递归计算 $\text{nxt}$ 的最少花费，并加上大礼包的价格 $\text{offer}[n]$，更新 $\text{ans}$，即 $\text{ans} = \min(\text{ans}, \text{offer}[n] + \text{dfs}(\text{nxt}))$。
-   最后，返回 $\text{ans}$。

为了避免重复计算，我们使用一个哈希表 $\text{f}$ 记录每一个状态 $\text{cur}$ 对应的最少花费。

时间复杂度 $O(n \times k \times m^n)$，其中 $n$ 表示物品的种类，而 $k$ 和 $m$ 分别表示大礼包的数量以及每种物品的最大需求量。空间复杂度 $O(n \times m^n)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def shoppingOffers(
        self, price: List[int], special: List[List[int]], needs: List[int]
    ) -> int:
        @cache
        def dfs(cur: int) -> int:
            ans = sum(p * (cur >> (i * bits) & 0xF) for i, p in enumerate(price))
            for offer in special:
                nxt = cur
                for j in range(len(needs)):
                    if (cur >> (j * bits) & 0xF) < offer[j]:
                        break
                    nxt -= offer[j] << (j * bits)
                else:
                    ans = min(ans, offer[-1] + dfs(nxt))
            return ans

        bits, mask = 4, 0
        for i, need in enumerate(needs):
            mask |= need << i * bits
        return dfs(mask)
```

#### Java

```java
class Solution {
    private final int bits = 4;
    private int n;
    private List<Integer> price;
    private List<List<Integer>> special;
    private Map<Integer, Integer> f = new HashMap<>();

    public int shoppingOffers(
        List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        n = needs.size();
        this.price = price;
        this.special = special;
        int mask = 0;
        for (int i = 0; i < n; ++i) {
            mask |= needs.get(i) << (i * bits);
        }
        return dfs(mask);
    }

    private int dfs(int cur) {
        if (f.containsKey(cur)) {
            return f.get(cur);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += price.get(i) * (cur >> (i * bits) & 0xf);
        }
        for (List<Integer> offer : special) {
            int nxt = cur;
            boolean ok = true;
            for (int j = 0; j < n; ++j) {
                if ((cur >> (j * bits) & 0xf) < offer.get(j)) {
                    ok = false;
                    break;
                }
                nxt -= offer.get(j) << (j * bits);
            }
            if (ok) {
                ans = Math.min(ans, offer.get(n) + dfs(nxt));
            }
        }
        f.put(cur, ans);
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int shoppingOffers(vector<int>& price, vector<vector<int>>& special, vector<int>& needs) {
        const int bits = 4;
        int n = needs.size();
        unordered_map<int, int> f;
        int mask = 0;
        for (int i = 0; i < n; ++i) {
            mask |= needs[i] << (i * bits);
        }
        function<int(int)> dfs = [&](int cur) {
            if (f.contains(cur)) {
                return f[cur];
            }
            int ans = 0;
            for (int i = 0; i < n; ++i) {
                ans += price[i] * ((cur >> (i * bits)) & 0xf);
            }
            for (const auto& offer : special) {
                int nxt = cur;
                bool ok = true;
                for (int j = 0; j < n; ++j) {
                    if (((cur >> (j * bits)) & 0xf) < offer[j]) {
                        ok = false;
                        break;
                    }
                    nxt -= offer[j] << (j * bits);
                }
                if (ok) {
                    ans = min(ans, offer[n] + dfs(nxt));
                }
            }
            f[cur] = ans;
            return ans;
        };
        return dfs(mask);
    }
};
```

#### Go

```go
func shoppingOffers(price []int, special [][]int, needs []int) int {
	const bits = 4
	n := len(needs)
	f := make(map[int]int)
	mask := 0
	for i, need := range needs {
		mask |= need << (i * bits)
	}

	var dfs func(int) int
	dfs = func(cur int) int {
		if v, ok := f[cur]; ok {
			return v
		}
		ans := 0
		for i := 0; i < n; i++ {
			ans += price[i] * ((cur >> (i * bits)) & 0xf)
		}
		for _, offer := range special {
			nxt := cur
			ok := true
			for j := 0; j < n; j++ {
				if ((cur >> (j * bits)) & 0xf) < offer[j] {
					ok = false
					break
				}
				nxt -= offer[j] << (j * bits)
			}
			if ok {
				ans = min(ans, offer[n]+dfs(nxt))
			}
		}
		f[cur] = ans
		return ans
	}

	return dfs(mask)
}
```

#### TypeScript

```ts
function shoppingOffers(price: number[], special: number[][], needs: number[]): number {
    const bits = 4;
    const n = needs.length;
    const f: Map<number, number> = new Map();

    let mask = 0;
    for (let i = 0; i < n; i++) {
        mask |= needs[i] << (i * bits);
    }

    const dfs = (cur: number): number => {
        if (f.has(cur)) {
            return f.get(cur)!;
        }
        let ans = 0;
        for (let i = 0; i < n; i++) {
            ans += price[i] * ((cur >> (i * bits)) & 0xf);
        }
        for (const offer of special) {
            let nxt = cur;
            let ok = true;
            for (let j = 0; j < n; j++) {
                if (((cur >> (j * bits)) & 0xf) < offer[j]) {
                    ok = false;
                    break;
                }
                nxt -= offer[j] << (j * bits);
            }
            if (ok) {
                ans = Math.min(ans, offer[n] + dfs(nxt));
            }
        }
        f.set(cur, ans);
        return ans;
    };

    return dfs(mask);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
