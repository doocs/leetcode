# [2861. Maximum Number of Alloys](https://leetcode.com/problems/maximum-number-of-alloys)

[中文文档](/solution/2800-2899/2861.Maximum%20Number%20of%20Alloys/README.md)

## Description

<p>You are the owner of a company that creates alloys using various types of metals. There are <code>n</code> different types of metals available, and you have access to <code>k</code> machines that can be used to create alloys. Each machine requires a specific amount of each metal type to create an alloy.</p>

<p>For the <code>i<sup>th</sup></code> machine to create an alloy, it needs <code>composition[i][j]</code> units of metal of type <code>j</code>. Initially, you have <code>stock[i]</code> units of metal type <code>i</code>, and purchasing one unit of metal type <code>i</code> costs <code>cost[i]</code> coins.</p>

<p>Given integers <code>n</code>, <code>k</code>, <code>budget</code>, a <strong>1-indexed</strong> 2D array <code>composition</code>, and <strong>1-indexed</strong> arrays <code>stock</code> and <code>cost</code>, your goal is to <strong>maximize</strong> the number of alloys the company can create while staying within the budget of <code>budget</code> coins.</p>

<p><strong>All alloys must be created with the same machine.</strong></p>

<p>Return <em>the maximum number of alloys that the company can create</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3, k = 2, budget = 15, composition = [[1,1,1],[1,1,10]], stock = [0,0,0], cost = [1,2,3]
<strong>Output:</strong> 2
<strong>Explanation:</strong> It is optimal to use the 1<sup>st</sup> machine to create alloys.
To create 2 alloys we need to buy the:
- 2 units of metal of the 1<sup>st</sup> type.
- 2 units of metal of the 2<sup>nd</sup> type.
- 2 units of metal of the 3<sup>rd</sup> type.
In total, we need 2 * 1 + 2 * 2 + 2 * 3 = 12 coins, which is smaller than or equal to budget = 15.
Notice that we have 0 units of metal of each type and we have to buy all the required units of metal.
It can be proven that we can create at most 2 alloys.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, k = 2, budget = 15, composition = [[1,1,1],[1,1,10]], stock = [0,0,100], cost = [1,2,3]
<strong>Output:</strong> 5
<strong>Explanation:</strong> It is optimal to use the 2<sup>nd</sup> machine to create alloys.
To create 5 alloys we need to buy:
- 5 units of metal of the 1<sup>st</sup> type.
- 5 units of metal of the 2<sup>nd</sup> type.
- 0 units of metal of the 3<sup>rd</sup> type.
In total, we need 5 * 1 + 5 * 2 + 0 * 3 = 15 coins, which is smaller than or equal to budget = 15.
It can be proven that we can create at most 5 alloys.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 2, k = 3, budget = 10, composition = [[2,1],[1,2],[1,1]], stock = [1,1], cost = [5,5]
<strong>Output:</strong> 2
<strong>Explanation:</strong> It is optimal to use the 3<sup>rd</sup> machine to create alloys.
To create 2 alloys we need to buy the:
- 1 unit of metal of the 1<sup>st</sup> type.
- 1 unit of metal of the 2<sup>nd</sup> type.
In total, we need 1 * 5 + 1 * 5 = 10 coins, which is smaller than or equal to budget = 10.
It can be proven that we can create at most 2 alloys.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

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

## Solutions

**Solution 1: Binary Search**

We note that all alloys need to be made by the same machine, so we can enumerate which machine to use to make the alloy.

For each machine, we can use binary search to find the maximum integer $x$ such that we can use this machine to make $x$ alloys. The maximum of all $x$ is the answer.

The time complexity is $O(n \times k \times \log M)$, where $M$ is the upper bound of the binary search, and in this problem, $M \leq 2 \times 10^8$. The space complexity is $O(1)$.

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **C++**

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

### **Go**

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

### **TypeScript**

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

### **...**

```

```

<!-- tabs:end -->
