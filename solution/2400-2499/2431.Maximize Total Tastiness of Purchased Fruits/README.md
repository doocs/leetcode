# [2431. Maximize Total Tastiness of Purchased Fruits](https://leetcode.cn/problems/maximize-total-tastiness-of-purchased-fruits)

[English Version](/solution/2400-2499/2431.Maximize%20Total%20Tastiness%20of%20Purchased%20Fruits/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>You are given two non-negative integer arrays <code>price</code> and <code>tastiness</code>, both arrays have the same length <code>n</code>. You are also given two non-negative integers <code>maxAmount</code> and <code>maxCoupons</code>.</p>

<p>For every integer <code>i</code> in range <code>[0, n - 1]</code>:</p>

<ul>
	<li><code>price[i]</code> describes the price of <code>i<sup>th</sup></code> fruit.</li>
	<li><code>tastiness[i]</code> describes the tastiness of <code>i<sup>th</sup></code> fruit.</li>
</ul>

<p>You want to purchase some fruits such that total tastiness is maximized and the total price does not exceed <code>maxAmount</code>.</p>

<p>Additionally, you can use a coupon to purchase fruit for <strong>half of its price</strong> (rounded down to the closest integer). You can use at most <code>maxCoupons</code> of such coupons.</p>

<p>Return <em>the maximum total tastiness that can be purchased</em>.</p>

<p><strong>Note that:</strong></p>

<ul>
	<li>You can purchase each fruit at most once.</li>
	<li>You can use coupons on some fruit at most once.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> price = [10,20,20], tastiness = [5,8,8], maxAmount = 20, maxCoupons = 1
<strong>Output:</strong> 13
<strong>Explanation:</strong> It is possible to make total tastiness 13 in following way:
- Buy first fruit without coupon, so that total price = 0 + 10 and total tastiness = 0 + 5.
- Buy second fruit with coupon, so that total price = 10 + 10 and total tastiness = 5 + 8.
- Do not buy third fruit, so that total price = 20 and total tastiness = 13.
It can be proven that 13 is the maximum total tastiness that can be obtained.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> price = [10,15,7], tastiness = [5,8,20], maxAmount = 10, maxCoupons = 2
<strong>Output:</strong> 28
<strong>Explanation:</strong> It is possible to make total tastiness 20 in following way:
- Do not buy first fruit, so that total price = 0 and total tastiness = 0.
- Buy second fruit with coupon, so that total price = 0 + 7 and total tastiness = 0 + 8.
- Buy third fruit with coupon, so that total price = 7 + 3 and total tastiness = 8 + 20.
It can be proven that 28 is the maximum total tastiness that can be obtained.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

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
