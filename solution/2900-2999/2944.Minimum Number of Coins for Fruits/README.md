# [2944. 购买水果需要的最少金币数](https://leetcode.cn/problems/minimum-number-of-coins-for-fruits)

[English Version](/solution/2900-2999/2944.Minimum%20Number%20of%20Coins%20for%20Fruits/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你在一个水果超市里，货架上摆满了玲琅满目的奇珍异果。</p>

<p>给你一个下标从 <strong>1</strong>&nbsp;开始的数组&nbsp;<code>prices</code>&nbsp;，其中&nbsp;<code>prices[i]</code>&nbsp;表示你购买第 <code>i</code>&nbsp;个水果需要花费的金币数目。</p>

<p>水果超市有如下促销活动：</p>

<ul>
	<li>如果你花费 <code>price[i]</code>&nbsp;购买了水果&nbsp;<code>i</code>&nbsp;，那么接下来的 <code>i</code>&nbsp;个水果你都可以免费获得。</li>
</ul>

<p><strong>注意</strong>&nbsp;，即使你&nbsp;<strong>可以</strong>&nbsp;免费获得水果&nbsp;<code>j</code>&nbsp;，你仍然可以花费&nbsp;<code>prices[j]</code>&nbsp;个金币去购买它以便能免费获得接下来的 <code>j</code>&nbsp;个水果。</p>

<p>请你返回获得所有水果所需要的 <strong>最少</strong>&nbsp;金币数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>prices = [3,1,2]
<b>输出：</b>4
<b>解释</b><strong>：</strong>你可以按如下方法获得所有水果：
- 花 3 个金币购买水果 1 ，然后免费获得水果 2 。
- 花 1 个金币购买水果 2 ，然后免费获得水果 3 。
- 免费获得水果 3 。
注意，虽然你可以免费获得水果 2 ，但你还是花 1 个金币去购买它，因为这样的总花费最少。
购买所有水果需要最少花费 4 个金币。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>prices = [1,10,1,1]
<b>输出：</b>2
<b>解释：</b>你可以按如下方法获得所有水果：
- 花 1 个金币购买水果 1 ，然后免费获得水果 2 。
- 免费获得水果 2 。
- 花 1 个金币购买水果 3 ，然后免费获得水果 4 。
- 免费获得水果 4 。
购买所有水果需要最少花费 2 个金币。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 1000</code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

我们定义一个函数 $dfs(i)$，表示从第 $i$ 个水果开始购买所有水果所需要的最少金币数。那么答案就是 $dfs(1)$。

函数 $dfs(i)$ 的执行逻辑如下：

-   如果 $i \gt n$，说明已经购买完所有水果，返回 $0$。
-   否则，我们可以购买水果 $i$，然后在接下来的 $i + 1$ 到 $2i + 1$ 个水果中选择一个水果 $j$ 开始购买，那么 $dfs(i) = prices[i - 1] + \min_{i + 1 \le j \le 2i + 1} dfs(j)$。

为了避免重复计算，我们使用记忆化搜索的方法，将已经计算过的结果保存起来，下次遇到相同的情况时，直接返回结果即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $prices$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
