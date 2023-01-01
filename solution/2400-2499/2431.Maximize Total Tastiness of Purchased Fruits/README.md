# [2431. 最大限度地提高购买水果的口味](https://leetcode.cn/problems/maximize-total-tastiness-of-purchased-fruits)

[English Version](/solution/2400-2499/2431.Maximize%20Total%20Tastiness%20of%20Purchased%20Fruits/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有两个非负整数数组 <code>price</code> 和 <code>tastiness</code>，两个数组的长度都是 <code>n</code>。同时给你两个非负整数 <code>maxAmount</code> 和 <code>maxCoupons</code>。</p>

<p data-group="1-1">对于范围 <code>[0, n - 1]</code>&nbsp;中的每一个整数 <code>i</code>:</p>

<ul>
	<li>
	<p data-group="1-1"><code>price[i]</code>&nbsp;描述了第 <code>i</code> 个水果的价格。</p>
	</li>
	<li><code>tastiness[i]</code> 描述了第 <code>i</code> 个水果的味道。</li>
</ul>

<p>你想购买一些水果，这样总的味道是最大的，总价不超过 <code>maxAmount</code>。</p>

<p>此外，你还可以用优惠券以&nbsp;<strong>半价&nbsp;</strong>购买水果 (四舍五入到最接近的整数)。您最多可以使用 <code>maxCoupons</code>&nbsp;次该优惠券。</p>

<p>返回可购买的最大总口味。</p>

<p><strong>注意:</strong></p>

<ul>
	<li>每种水果最多只能购买一次。</li>
	<li>一些水果你最多只能用一次折价券。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> price = [10,20,20], tastiness = [5,8,8], maxAmount = 20, maxCoupons = 1
<strong>输出:</strong> 13
<strong>解释:</strong> 可以用以下方法来达到总口味:
- 无优惠券买第一个水果，总价= 0 + 10，总口味= 0 + 5。
- 用优惠券买第二个水果，总价= 10 + 10，总口味= 5 + 8。
- 不购买第三个水果，总价= 20，总口味= 13。
可以证明 13 是所能得到的最大总口味。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> price = [10,15,7], tastiness = [5,8,20], maxAmount = 10, maxCoupons = 2
<strong>输出:</strong> 28
<strong>解释:</strong> 可以用以下方法使总口味达到 20:
- 不买第一个水果，这样总价= 0，总口味= 0。
- 用优惠券买第二个水果，总价= 0 + 7，总口味= 0 + 8。
- 用优惠券买第三个水果，总价= 7 + 3，总口味= 8 + 20。
可以证明，28 是所能得到的最大总口味。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == price.length == tastiness.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= price[i], tastiness[i], maxAmount &lt;= 1000</code></li>
	<li><code>0 &lt;= maxCoupons &lt;= 5</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

设计函数 $dfs(i, j, k)$ 表示从第 $i$ 个水果开始，剩余 $j$ 元钱，剩余 $k$ 张优惠券时，最大的总美味度。

对于第 $i$ 个水果，可以选择购买或者不购买，如果购买，那么可以选择使用优惠券或者不使用优惠券。

如果不购买，那么最大总美味度是 $dfs(i + 1, j, k)$；

如果购买，如果不使用优惠券（需要满足 $j\ge price[i]$），那么最大总美味度是 $dfs(i + 1, j - price[i], k) + tastiness[i]$；如果使用优惠券（需要满足 $k\gt 0$ 并且 $j\ge \lfloor \frac{price[i]}{2} \rfloor$），那么最大总美味度是 $dfs(i + 1, j - \lfloor \frac{price[i]}{2} \rfloor, k - 1) + tastiness[i]$。

最终的答案是 $dfs(0, maxAmount, maxCoupons)$。

时间复杂度 $O(n \times maxAmount \times maxCoupons)$。其中 $n$ 是水果的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxTastiness(self, price: List[int], tastiness: List[int], maxAmount: int, maxCoupons: int) -> int:
        @cache
        def dfs(i, j, k):
            if i == len(price):
                return 0
            ans = dfs(i + 1, j, k)
            if j >= price[i]:
                ans = max(ans, dfs(i + 1, j - price[i], k) + tastiness[i])
            if j >= price[i] // 2 and k:
                ans = max(
                    ans, dfs(i + 1, j - price[i] // 2, k - 1) + tastiness[i])
            return ans

        return dfs(0, maxAmount, maxCoupons)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[][][] f;
    private int[] price;
    private int[] tastiness;
    private int n;

    public int maxTastiness(int[] price, int[] tastiness, int maxAmount, int maxCoupons) {
        n = price.length;
        this.price = price;
        this.tastiness = tastiness;
        f = new int[n][maxAmount + 1][maxCoupons + 1];
        return dfs(0, maxAmount, maxCoupons);
    }

    private int dfs(int i, int j, int k) {
        if (i == n) {
            return 0;
        }
        if (f[i][j][k] != 0) {
            return f[i][j][k];
        }
        int ans = dfs(i + 1, j, k);
        if (j >= price[i]) {
            ans = Math.max(ans, dfs(i + 1, j - price[i], k) + tastiness[i]);
        }
        if (j >= price[i] / 2 && k > 0) {
            ans = Math.max(ans, dfs(i + 1, j - price[i] / 2, k - 1) + tastiness[i]);
        }
        f[i][j][k] = ans;
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxTastiness(vector<int>& price, vector<int>& tastiness, int maxAmount, int maxCoupons) {
        int n = price.size();
        memset(f, 0, sizeof f);
        function<int(int i, int j, int k)> dfs;
        dfs = [&](int i, int j, int k) {
            if (i == n) return 0;
            if (f[i][j][k]) return f[i][j][k];
            int ans = dfs(i + 1, j, k);
            if (j >= price[i])  ans = max(ans, dfs(i + 1, j - price[i], k) + tastiness[i]);
            if (j >= price[i] / 2 && k) ans = max(ans, dfs(i + 1, j - price[i] / 2, k - 1) + tastiness[i]);
            f[i][j][k] = ans;
            return ans;
        };
        return dfs(0, maxAmount, maxCoupons);
    }
private:
    int f[101][1001][6];
};
```

### **Go**

```go
func maxTastiness(price []int, tastiness []int, maxAmount int, maxCoupons int) int {
	n := len(price)
	f := make([][][]int, n+1)
	for i := range f {
		f[i] = make([][]int, maxAmount+1)
		for j := range f[i] {
			f[i][j] = make([]int, maxCoupons+1)
		}
	}
	var dfs func(i, j, k int) int
	dfs = func(i, j, k int) int {
		if i == n {
			return 0
		}
		if f[i][j][k] != 0 {
			return f[i][j][k]
		}
		ans := dfs(i+1, j, k)
		if j >= price[i] {
			ans = max(ans, dfs(i+1, j-price[i], k)+tastiness[i])
		}
		if j >= price[i]/2 && k > 0 {
			ans = max(ans, dfs(i+1, j-price[i]/2, k-1)+tastiness[i])
		}
		f[i][j][k] = ans
		return ans
	}
	return dfs(0, maxAmount, maxCoupons)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
