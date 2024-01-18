# [2861. 最大合金数](https://leetcode.cn/problems/maximum-number-of-alloys)

[English Version](/solution/2800-2899/2861.Maximum%20Number%20of%20Alloys/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>假设你是一家合金制造公司的老板，你的公司使用多种金属来制造合金。现在共有 <code>n</code> 种不同类型的金属可以使用，并且你可以使用 <code>k</code> 台机器来制造合金。每台机器都需要特定数量的每种金属来创建合金。</p>

<p>对于第 <code>i</code> 台机器而言，创建合金需要 <code>composition[i][j]</code> 份 <code>j</code> 类型金属。最初，你拥有 <code>stock[i]</code> 份 <code>i</code> 类型金属，而每购入一份 <code>i</code> 类型金属需要花费 <code>cost[i]</code> 的金钱。</p>

<p>给你整数 <code>n</code>、<code>k</code>、<code>budget</code>，下标从 <strong>1</strong> 开始的二维数组 <code>composition</code>，两个下标从 <strong>1</strong> 开始的数组 <code>stock</code> 和 <code>cost</code>，请你在预算不超过 <code>budget</code> 金钱的前提下，<strong>最大化</strong> 公司制造合金的数量。</p>

<p><strong>所有合金都需要由同一台机器制造。</strong></p>

<p>返回公司可以制造的最大合金数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3, k = 2, budget = 15, composition = [[1,1,1],[1,1,10]], stock = [0,0,0], cost = [1,2,3]
<strong>输出：</strong>2
<strong>解释：</strong>最优的方法是使用第 1 台机器来制造合金。
要想制造 2 份合金，我们需要购买：
- 2 份第 1 类金属。
- 2 份第 2 类金属。
- 2 份第 3 类金属。
总共需要 2 * 1 + 2 * 2 + 2 * 3 = 12 的金钱，小于等于预算 15 。
注意，我们最开始时候没有任何一类金属，所以必须买齐所有需要的金属。
可以证明在示例条件下最多可以制造 2 份合金。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 3, k = 2, budget = 15, composition = [[1,1,1],[1,1,10]], stock = [0,0,100], cost = [1,2,3]
<strong>输出：</strong>5
<strong>解释：</strong>最优的方法是使用第 2 台机器来制造合金。 
要想制造 5 份合金，我们需要购买： 
- 5 份第 1 类金属。
- 5 份第 2 类金属。 
- 0 份第 3 类金属。 
总共需要 5 * 1 + 5 * 2 + 0 * 3 = 15 的金钱，小于等于预算 15 。 
可以证明在示例条件下最多可以制造 5 份合金。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 2, k = 3, budget = 10, composition = [[2,1],[1,2],[1,1]], stock = [1,1], cost = [5,5]
<strong>输出：</strong>2
<strong>解释：</strong>最优的方法是使用第 3 台机器来制造合金。
要想制造 2 份合金，我们需要购买：
- 1 份第 1 类金属。
- 1 份第 2 类金属。
总共需要 1 * 5 + 1 * 5 = 10 的金钱，小于等于预算 10 。
可以证明在示例条件下最多可以制造 2 份合金。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n, k &lt;= 100</code></li>
	<li><code>0 &lt;= budget &lt;= 10<sup>8</sup></code></li>
	<li><code>composition.length == k</code></li>
	<li><code>composition[i].length == n</code></li>
	<li><code>1 &lt;= composition[i][j] &lt;= 100</code></li>
	<li><code>stock.length == cost.length == n</code></li>
	<li><code>0 &lt;= stock[i] &lt;= 10<sup>8</sup></code></li>
	<li><code>1 &lt;= cost[i] &lt;= 100</code></li>
</ul>

## 解法

### 方法一：二分查找

我们注意到，所有合金都需要由同一台机器制造，因此我们可以枚举使用哪一台机器来制造合金。

对于每一台机器，我们可以使用二分查找的方法找出最大的整数 $x$，使得我们可以使用这台机器制造 $x$ 份合金。找出所有 $x$ 中的最大值即为答案。

时间复杂度 $O(n \times k \times \log M)$，其中 $M$ 是二分查找的上界，本题中 $M \leq 2 \times 10^8$。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def maxNumberOfAlloys(
        self,
        n: int,
        k: int,
        budget: int,
        composition: List[List[int]],
        stock: List[int],
        cost: List[int],
    ) -> int:
        ans = 0
        for c in composition:
            l, r = 0, budget + stock[0]
            while l < r:
                mid = (l + r + 1) >> 1
                s = sum(max(0, mid * x - y) * z for x, y, z in zip(c, stock, cost))
                if s <= budget:
                    l = mid
                else:
                    r = mid - 1
            ans = max(ans, l)
        return ans
```

```java
class Solution {
    int n;
    int budget;
    List<List<Integer>> composition;
    List<Integer> stock;
    List<Integer> cost;

    boolean isValid(long target) {
        for (List<Integer> currMachine : composition) {
            long remain = budget;
            for (int j = 0; j < n && remain >= 0; j++) {
                long need = Math.max(0, currMachine.get(j) * target - stock.get(j));
                remain -= need * cost.get(j);
            }
            if (remain >= 0) {
                return true;
            }
        }
        return false;
    }

    public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> composition,
        List<Integer> stock, List<Integer> cost) {
        this.n = n;
        this.budget = budget;
        this.composition = composition;
        this.stock = stock;
        this.cost = cost;
        int l = -1;
        int r = budget / cost.get(0) + stock.get(0);
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (isValid(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
}
```

```cpp
class Solution {
public:
    int maxNumberOfAlloys(int n, int k, int budget, vector<vector<int>>& composition, vector<int>& stock, vector<int>& cost) {
        auto isValid = [&](long long target) {
            for (int i = 0; i < k; i++) {
                long long remain = budget;
                auto currMachine = composition[i];
                for (int j = 0; j < n && remain >= 0; j++) {
                    long long need = max(0LL, target * currMachine[j] - stock[j]);
                    remain -= need * cost[j];
                }
                if (remain >= 0) {
                    return true;
                }
            }
            return false;
        };
        long long l = 0, r = budget + stock[0];
        while (l < r) {
            long long mid = (l + r + 1) >> 1;
            if (isValid(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
};
```

```go
func maxNumberOfAlloys(n int, k int, budget int, composition [][]int, stock []int, cost []int) int {
	isValid := func(target int) bool {
		for _, currMachine := range composition {
			remain := budget
			for i, x := range currMachine {
				need := max(0, x*target-stock[i])
				remain -= need * cost[i]
			}
			if remain >= 0 {
				return true
			}
		}
		return false
	}

	l, r := 0, budget+stock[0]
	for l < r {
		mid := (l + r + 1) >> 1
		if isValid(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}
```

```ts
function maxNumberOfAlloys(
    n: number,
    k: number,
    budget: number,
    composition: number[][],
    stock: number[],
    cost: number[],
): number {
    let l = 0;
    let r = budget + stock[0];
    const isValid = (target: number): boolean => {
        for (const currMachine of composition) {
            let remain = budget;
            for (let i = 0; i < n; ++i) {
                let need = Math.max(0, target * currMachine[i] - stock[i]);
                remain -= need * cost[i];
            }
            if (remain >= 0) {
                return true;
            }
        }
        return false;
    };
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (isValid(mid)) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }
    return l;
}
```

<!-- tabs:end -->

<!-- end -->
