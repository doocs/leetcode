---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0638.Shopping%20Offers/README_EN.md
tags:
    - Bit Manipulation
    - Memoization
    - Array
    - Dynamic Programming
    - Backtracking
    - Bitmask
---

<!-- problem:start -->

# [638. Shopping Offers](https://leetcode.com/problems/shopping-offers)

[中文文档](/solution/0600-0699/0638.Shopping%20Offers/README.md)

## Description

<!-- description:start -->

<p>In LeetCode Store, there are <code>n</code> items to sell. Each item has a price. However, there are some special offers, and a special offer consists of one or more different kinds of items with a sale price.</p>

<p>You are given an integer array <code>price</code> where <code>price[i]</code> is the price of the <code>i<sup>th</sup></code> item, and an integer array <code>needs</code> where <code>needs[i]</code> is the number of pieces of the <code>i<sup>th</sup></code> item you want to buy.</p>

<p>You are also given an array <code>special</code> where <code>special[i]</code> is of size <code>n + 1</code> where <code>special[i][j]</code> is the number of pieces of the <code>j<sup>th</sup></code> item in the <code>i<sup>th</sup></code> offer and <code>special[i][n]</code> (i.e., the last integer in the array) is the price of the <code>i<sup>th</sup></code> offer.</p>

<p>Return <em>the lowest price you have to pay for exactly certain items as given, where you could make optimal use of the special offers</em>. You are not allowed to buy more items than you want, even if that would lower the overall price. You could use any of the special offers as many times as you want.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> price = [2,5], special = [[3,0,5],[1,2,10]], needs = [3,2]
<strong>Output:</strong> 14
<strong>Explanation:</strong> There are two kinds of items, A and B. Their prices are $2 and $5 respectively. 
In special offer 1, you can pay $5 for 3A and 0B
In special offer 2, you can pay $10 for 1A and 2B. 
You need to buy 3A and 2B, so you may pay $10 for 1A and 2B (special offer #2), and $4 for 2A.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> price = [2,3,4], special = [[1,1,0,4],[2,2,1,9]], needs = [1,2,1]
<strong>Output:</strong> 11
<strong>Explanation:</strong> The price of A is $2, and $3 for B, $4 for C. 
You may pay $4 for 1A and 1B, and $9 for 2A ,2B and 1C. 
You need to buy 1A ,2B and 1C, so you may pay $4 for 1A and 1B (special offer #1), and $3 for 1B, $4 for 1C. 
You cannot add more items, though only $9 for 2A ,2B and 1C.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == price.length == needs.length</code></li>
	<li><code>1 &lt;= n &lt;= 6</code></li>
	<li><code>0 &lt;= price[i], needs[i] &lt;= 10</code></li>
	<li><code>1 &lt;= special.length &lt;= 100</code></li>
	<li><code>special[i].length == n + 1</code></li>
	<li><code>0 &lt;= special[i][j] &lt;= 50</code></li>
	<li>The input is generated that at least one of <code>special[i][j]</code> is non-zero for <code>0 &lt;= j &lt;= n - 1</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: State Compression + Memoization Search

We notice that the number of types of items $n \leq 6$ in the problem, and the quantity of each item needed does not exceed $10$. We can use $4$ binary bits to represent the quantity of each item needed. Thus, we only need at most $6 \times 4 = 24$ binary bits to represent the entire shopping list.

First, we convert the shopping list $\textit{needs}$ into an integer $\textit{mask}$, where the quantity of the $i$-th item needed is stored in the $i \times 4$ to $(i + 1) \times 4 - 1$ bits of $\textit{mask}$. For example, when $\textit{needs} = [1, 2, 1]$, we have $\textit{mask} = 0b0001 0010 0001$.

Then, we design a function $\textit{dfs}(cur)$, representing the minimum amount of money we need to spend when the current state of the shopping list is $\textit{cur}$. Therefore, the answer is $\textit{dfs}(\textit{mask})$.

The calculation method of the function $\textit{dfs}(cur)$ is as follows:

-   First, we calculate the cost of the current shopping list $\textit{cur}$ without using any bundles, denoted as $\textit{ans}$.
-   Then, we iterate through each bundle $\textit{offer}$. If the current shopping list $\textit{cur}$ can use the bundle $\textit{offer}$, i.e., the quantity of each item in $\textit{cur}$ is not less than that in the bundle $\textit{offer}$, then we can try to use this bundle. We subtract the quantity of each item in the bundle $\textit{offer}$ from $\textit{cur}$, obtaining a new shopping list $\textit{nxt}$, then recursively calculate the minimum cost of $\textit{nxt}$ and add the price of the bundle $\textit{offer}[n]$, updating $\textit{ans}$, i.e., $\textit{ans} = \min(\textit{ans}, \textit{offer}[n] + \textit{dfs}(\textit{nxt}))$.
-   Finally, return $\textit{ans}$.

To avoid repeated calculations, we use a hash table $\textit{f}$ to record the minimum cost corresponding to each state $\textit{cur}$.

The time complexity is $O(n \times k \times m^n)$, where $n$ represents the types of items, and $k$ and $m$ respectively represent the number of bundles and the maximum demand for each type of item. The space complexity is $O(n \times m^n)$.

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
