# [面试题 14- II. 剪绳子 II](这里是题目链接，如：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/)

## 题目描述

给你一根长度为 `n` 的绳子，请把绳子剪成整数长度的 `m` 段（m、n 都是整数，n>1 并且 m>1），每段绳子的长度记为 `k[0],k[1]...k[m-1]` 。请问 `k[0]*k[1]*...*k[m-1]` 可能的最大乘积是多少？例如，当绳子的长度是 8 时，我们把它剪成长度分别为 2、3、3 的三段，此时得到的最大乘积是 18。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

**示例 1：**

```
输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1
```

**示例  2:**

```
输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
```

**提示：**

- `2 <= n <= 1000`

## 解法

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def cuttingRope(self, n: int) -> int:
        if n < 4:
            return n - 1
        res = 1
        while n > 4:
            res *= 3
            n -= 3
        if n == 4:
            return (res << 2) % 1000000007
        return (res * n) % 1000000007
```

### **Java**

```java
class Solution {
    public int cuttingRope(int n) {
        if (n < 4) {
            return n - 1;
        }
        int s1 = n / 3;
        int m = n % 3;
        if (m == 1) {
            s1 -= 1;
            m = 4;
        }
        long res = 1;
        while (s1-- > 0) {
            res *= 3;
            res %= 1000000007;
        }
        return (int) ((res * (m == 0 ? 1 : m)) % 1000000007);
    }
}
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {number}
 */
var cuttingRope = function (n) {
    if (n <= 3) return n - 1;
    let a = ~~(n / 3);
    let b = n % 3;
    const MOD = 1e9 + 7;
    function myPow(x) {
        let r = 1;
        for (let i = 0; i < x; i++) {
            r = (r * 3) % MOD;
        }
        return r;
    }
    if (b === 1) {
        return (myPow(a - 1) * 4) % MOD;
    }
    if (b === 0) return myPow(a) % MOD;
    return (myPow(a) * 2) % MOD;
};
```

### **Go**

```go
func cuttingRope(n int) int {
	if n <= 3 {
		return n - 1
	}
	sum := 1
	for n > 4 {
		sum *= 3
		sum = sum % 1000000007
		n -= 3
	}
	return sum * n % 1000000007
}
```

### **C++**

```cpp
class Solution {
public:
    int cuttingRope(int n) {
        const int mod = 1000000007;
        if (n < 4) return n - 1;
        long long ans = 1;
        while (n > 4) {
            ans = ans * 3 % mod;
            n -= 3;
        }
        if (n == 4)
            return ans * 4 % mod;
        return ans * n % mod;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
