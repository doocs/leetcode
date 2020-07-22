# [面试题14- I. 剪绳子](https://leetcode-cn.com/problems/jian-sheng-zi-lcof/)

## 题目描述
给你一根长度为 `n` 的绳子，请把绳子剪成整数长度的 `m` 段（m、n 都是整数，n>1 并且 m>1），每段绳子的长度记为 `k[0],k[1]...k[m-1]` 。请问 `k[0]*k[1]*...*k[m-1]` 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为 2、3、3 的三段，此时得到的最大乘积是 18。

**示例 1：**
```
输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1
```

**示例 2:**

```
输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
```

**提示：**

- `2 <= n <= 58`

## 解法
<!-- tabs:start -->

### **Python3**
```python
class Solution:
    def cuttingRope(self, n: int) -> int:
        if n < 4:
            return n - 1
        s1, m = divmod(n, 3)
        if m == 1:
            s1 -= 1
            m = 4
        return pow(3, s1) * (1 if m == 0 else m)
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
        return (int) (Math.pow(3, s1) * ((m == 0) ? 1 : m));
    }
}
```

### **JavaScript**
```js
/**
 * @param {number} n
 * @return {number}
 */
var cuttingRope = function(n) {
    // 数学方法
    if(n <= 3) return n-1
    let a = ~~(n / 3)
    let b = n % 3
    if(b === 1) {
        return 3**(a-1) * 2 * 2
    }
    if(b === 0) return 3**a
    return 3**a * b
    // dp 方法
    // let dp = new Array(n+1).fill(0)
    // dp[0] = 1
    // for(let i=1;i<n;i++) {
    //     for(let j=i;j<=n;j++) {
    //         dp[j] = Math.max(dp[j],dp[j-i] * i)
    //     }
    // }
    // return dp[n]
};
```



### **Go**

```go
func cuttingRope(n int) int {
    if n <= 3 {
        return n-1
    }
    sum := 1
    for n > 4 {
        sum *= 3
        n -= 3
    }
    return sum*n
}
```



### **...**

```

```

<!-- tabs:end -->