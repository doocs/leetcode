# [1359. 有效的快递序列数目](https://leetcode.cn/problems/count-all-valid-pickup-and-delivery-options)

[English Version](/solution/1300-1399/1359.Count%20All%20Valid%20Pickup%20and%20Delivery%20Options/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你&nbsp;<code>n</code>&nbsp;笔订单，每笔订单都需要快递服务。</p>

<p>请你统计所有有效的 收件/配送 序列的数目，确保第 <code>i</code> 个物品的配送服务&nbsp;<code>delivery(i)</code> 总是在其收件服务&nbsp;<code>pickup(i)</code> 之后。</p>

<p>由于答案可能很大，请返回答案对 <code>10^9 + 7</code> 取余的结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 1
<strong>输出：</strong>1
<strong>解释：</strong>只有一种序列 (P1, D1)，物品 1 的配送服务（D1）在物品 1 的收件服务（P1）后。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 2
<strong>输出：</strong>6
<strong>解释：</strong>所有可能的序列包括：
(P1,P2,D1,D2)，(P1,P2,D2,D1)，(P1,D1,P2,D2)，(P2,P1,D1,D2)，(P2,P1,D2,D1) 和 (P2,D2,P1,D1)。
(P1,D2,P2,D1) 是一个无效的序列，因为物品 2 的收件服务（P2）不应在物品 2 的配送服务（D2）之后。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 3
<strong>输出：</strong>90
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 500</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们定义 $f[i]$ 表示 $i$ 个订单的所有有效的收件/配送序列的数目。初始时 $f[1] = 1$。

我们可以选择这 $i$ 个订单中的任意一个作为最后一个配送的订单 $D_i$，那么它的收件订单 $P_i$ 可以在之前 $2 \times i - 1$ 的任意一个位置，剩下的 $i - 1$ 个订单的配送/收件序列数目为 $f[i - 1]$，所以 $f[i]$ 可以表示为：

$$
f[i] = i \times (2 \times i - 1) \times f[i - 1]
$$

最终的答案即为 $f[n]$。

我们注意到 $f[i]$ 的值只与 $f[i - 1]$ 有关，所以可以用一个变量代替数组，降低空间复杂度。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为订单数目。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countOrders(self, n: int) -> int:
        mod = 10**9 + 7
        f = 1
        for i in range(2, n + 1):
            f = (f * i * (2 * i - 1)) % mod
        return f
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countOrders(int n) {
        final int mod = (int) 1e9 + 7;
        long f = 1;
        for (int i = 2; i <= n; ++i) {
            f = f * i * (2 * i - 1) % mod;
        }
        return (int) f;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countOrders(int n) {
        const int mod = 1e9 + 7;
        long long f = 1;
        for (int i = 2; i <= n; ++i) {
            f = f * i * (2 * i - 1) % mod;
        }
        return f;
    }
};
```

### **Go**

```go
func countOrders(n int) int {
	const mod = 1e9 + 7
	f := 1
	for i := 2; i <= n; i++ {
		f = f * i * (2*i - 1) % mod
	}
	return f
}
```

### **...**

```

```

<!-- tabs:end -->
