# [面试题10- I. 斐波那契数列](https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/)

## 题目描述
写一个函数，输入 `n` ，求斐波那契（Fibonacci）数列的第 `n` 项。斐波那契数列的定义如下：

```
F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
```

斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

**示例 1：**

```
输入：n = 2
输出：1
```

**示例 2：**

```
输入：n = 5
输出：5
```

**提示：**

- `0 <= n <= 100`

## 解法
递推求解。

<!-- tabs:start -->

### **Python3**
```python
class Solution:
    def fib(self, n: int) -> int:
        a, b = 0, 1
        for _ in range(n):
            a, b = b, a + b
        return a % 1000000007
```

### **Java**
```java
class Solution {
    public int fib(int n) {
        int a = 0, b = 1;
        for (int i = 0; i < n; ++i) {
            int s = (a + b) % 1000000007;
            a = b;
            b = s;
        }
        return a;
    }
}
```

### **JavaScript**
```js
/**
 * @param {number} n
 * @return {number}
 */
var fib = function(n) {
    if(!n) return 0
    let pre = 0
    let cur = 1
    for(let i=2;i<=n;i++) {
        let c = (pre + cur)%(1e9+7)
        pre = cur
        cur = c
    }
    return cur
};
```



### **Go**

```go
func fib(n int) int {
    if n < 2 {
        return n
    }
    a := make([]int,n+1)
    a[0]=0
    a[1]=1
    for i := 2; i < n+1; i++ {
        a[i] = (a[i-1]+ a[i-2])%1000000007
    }
    return a[n]
}
```





### **...**

```

```

<!-- tabs:end -->