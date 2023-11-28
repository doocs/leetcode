# [2944. Minimum Number of Coins for Fruits](https://leetcode.com/problems/minimum-number-of-coins-for-fruits)

[中文文档](/solution/2900-2999/2944.Minimum%20Number%20of%20Coins%20for%20Fruits/README.md)

## Description

<p>You are at a fruit market with different types of exotic fruits on display.</p>

<p>You are given a <strong>1-indexed</strong> array <code>prices</code>, where <code>prices[i]</code> denotes the number of coins needed to purchase the <code>i<sup>th</sup></code> fruit.</p>

<p>The fruit market has the following offer:</p>

<ul>
	<li>If you purchase the <code>i<sup>th</sup></code> fruit at <code>prices[i]</code> coins, you can get the next <code>i</code> fruits for free.</li>
</ul>

<p><strong>Note</strong> that even if you <strong>can</strong> take fruit <code>j</code> for free, you can still purchase it for <code>prices[j]</code> coins to receive a new offer.</p>

<p>Return <em>the <strong>minimum</strong> number of coins needed to acquire all the fruits</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> prices = [3,1,2]
<strong>Output:</strong> 4
<strong>Explanation:</strong> You can acquire the fruits as follows:
- Purchase the 1<sup>st</sup> fruit with 3 coins, you are allowed to take the 2<sup>nd</sup> fruit for free.
- Purchase the 2<sup>nd</sup> fruit with 1 coin, you are allowed to take the 3<sup>rd</sup> fruit for free.
- Take the 3<sup>rd</sup> fruit for free.
Note that even though you were allowed to take the 2<sup>nd</sup> fruit for free, you purchased it because it is more optimal.
It can be proven that 4 is the minimum number of coins needed to acquire all the fruits.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> prices = [1,10,1,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> You can acquire the fruits as follows:
- Purchase the 1<sup>st</sup> fruit with 1 coin, you are allowed to take the 2<sup>nd</sup> fruit for free.
- Take the 2<sup>nd</sup> fruit for free.
- Purchase the 3<sup>rd</sup> fruit for 1 coin, you are allowed to take the 4<sup>th</sup> fruit for free.
- Take the 4<sup>t</sup><sup>h</sup> fruit for free.
It can be proven that 2 is the minimum number of coins needed to acquire all the fruits.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 1000</code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

**Solution 1: Memoization Search**

We define a function $dfs(i)$, which represents the minimum number of coins needed to buy all fruits starting from the $i$th fruit. The answer is $dfs(1)$.

The execution logic of the function $dfs(i)$ is as follows:

-   If $i > n$, it means that all fruits have been bought, so return $0$.
-   Otherwise, we can buy the $i$th fruit, and then choose a fruit $j$ from the next $i + 1$ to $2i + 1$ fruits to start buying. So, $dfs(i) = prices[i - 1] + \min_{i + 1 \le j \le 2i + 1} dfs(j)$.

To avoid repeated calculations, we use the method of memoization search, saving the results that have been calculated. When we encounter the same situation next time, we can directly return the result.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $prices$.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumCoins(self, prices: List[int]) -> int:
        @cache
        def dfs(i: int) -> int:
            if i > len(prices):
                return 0
            return prices[i - 1] + min(dfs(j) for j in range(i + 1, i * 2 + 2))

        return dfs(1)
```

### **Java**

```java
class Solution {
    private int[] prices;
    private int[] f;
    private int n;

    public int minimumCoins(int[] prices) {
        n = prices.length;
        f = new int[n + 1];
        this.prices = prices;
        return dfs(1);
    }

    private int dfs(int i) {
        if (i > n) {
            return 0;
        }
        if (f[i] == 0) {
            f[i] = 1 << 30;
            for (int j = i + 1; j <= i * 2 + 1; ++j) {
                f[i] = Math.min(f[i], prices[i - 1] + dfs(j));
            }
        }
        return f[i];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumCoins(vector<int>& prices) {
        int n = prices.size();
        int f[n + 1];
        memset(f, 0x3f, sizeof(f));
        function<int(int)> dfs = [&](int i) {
            if (i > n) {
                return 0;
            }
            if (f[i] == 0x3f3f3f3f) {
                for (int j = i + 1; j <= i * 2 + 1; ++j) {
                    f[i] = min(f[i], prices[i - 1] + dfs(j));
                }
            }
            return f[i];
        };
        return dfs(1);
    }
};
```

### **Go**

```go
func minimumCoins(prices []int) int {
	n := len(prices)
	f := make([]int, n)
	var dfs func(int) int
	dfs = func(i int) int {
		if i > n {
			return 0
		}
		if f[i] == 0 {
			f[i] = 1 << 30
			for j := i + 1; j <= i*2+1; j++ {
				f[i] = min(f[i], dfs(j)+prices[j-1])
			}
		}
		return f[i]
	}
	return dfs(1)
}
```

### **TypeScript**

```ts
function minimumCoins(prices: number[]): number {
    const n = prices.length;
    const f: number[] = Array(n + 1).fill(0);
    const dfs = (i: number): number => {
        if (i > n) {
            return 0;
        }
        if (f[i] === 0) {
            f[i] = 1 << 30;
            for (let j = i + 1; j <= i * 2 + 1; ++j) {
                f[i] = Math.min(f[i], prices[i - 1] + dfs(j));
            }
        }
        return f[i];
    };
    return dfs(1);
}
```

### **...**

```

```

<!-- tabs:end -->
