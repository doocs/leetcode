# [面试题 10- II. 青蛙跳台阶问题](https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/)

## 题目描述

一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级台阶。求该青蛙跳上一个 `n`  级的台阶总共有多少种跳法。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

**示例 1：**

```
输入：n = 2
输出：2
```

**示例 2：**

```
输入：n = 7
输出：21
```

**提示：**

- `0 <= n <= 100`

## 解法

青蛙想上第 `n` 级台阶，可从第 `n-1` 级台阶跳一级上去，也可从第 `n-2` 级台阶跳两级上去，即：`f(n) = f(n-1) + f(n-2)`。递推求解即可。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numWays(self, n: int) -> int:
        a, b = 0, 1
        for _ in range(n):
            a, b = b, a + b
        return b % 1000000007
```

### **Java**

```java
class Solution {
    public int numWays(int n) {
        int a = 0, b = 1;
        for (int i = 0; i < n; ++i) {
            int c = (a + b) % 1000000007;
            a = b;
            b = c;
        }
        return b;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numWays(int n) {
        int a = 0, b = 1;
        for (int i = 0; i < n; ++i) {
            int c = (a + b) % 1000000007;
            a = b;
            b = c;
        }
        return b;
    }
};
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {number}
 */
var numWays = function (n) {
    let a = 0,
        b = 1;
    for (let i = 0; i < n; ++i) {
        const c = (a + b) % (1e9 + 7);
        a = b;
        b = c;
    }
    return b;
};
```

### **Go**

```go
func numWays(n int) int {
    a, b := 0, 1
    for i := 0; i < n; i++ {
        a, b = b, (a + b) % 1000000007
    }
    return b
}
```

### **...**

```

```

<!-- tabs:end -->
