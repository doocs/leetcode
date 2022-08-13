# [面试题 08.01. 三步问题](https://leetcode.cn/problems/three-steps-problem-lcci)

[English Version](/lcci/08.01.Three%20Steps%20Problem/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模1000000007。</p>

<p> <strong>示例1:</strong></p>

<pre>
<strong> 输入</strong>：n = 3 
<strong> 输出</strong>：4
<strong> 说明</strong>: 有四种走法
</pre>

<p> <strong>示例2:</strong></p>

<pre>
<strong> 输入</strong>：n = 5
<strong> 输出</strong>：13
</pre>

<p> <strong>提示:</strong></p>

<ol>
<li>n范围在[1, 1000000]之间</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

递推法。`f(n)=f(n-1)+f(n-2)+f(n-3)`

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def waysToStep(self, n: int) -> int:
        if n < 3:
            return n
        a, b, c = 1, 2, 4
        for _ in range(4, n + 1):
            a, b, c = b, c, (a + b + c) % 1000000007
        return c
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int waysToStep(int n) {
        if (n < 3) {
            return n;
        }
        int a = 1, b = 2, c = 4;
        for (int i = 4; i <= n; ++i) {
            int t = a;
            a = b;
            b = c;
            c = ((a + b) % 1000000007 + t) % 1000000007;
        }
        return c;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {number}
 */
var waysToStep = function (n) {
    if (n < 3) return n;
    let a = 1,
        b = 2,
        c = 4;
    for (let i = 3; i < n; i++) {
        [a, b, c] = [b, c, (a + b + c) % 1000000007];
    }
    return c;
};
```

### **C**

```c
int waysToStep(int n) {
    if (n < 3) {
        return n;
    }
    int a = 1, b = 2, c = 4, i = 4;
    while (i++ <= n) {
        int t = ((a + b) % 1000000007 + c) % 1000000007;
        a = b;
        b = c;
        c = t;
    }
    return c;
}
```

### **C++**

```cpp
class Solution {
public:
    int waysToStep(int n) {
        if (n < 3) {
            return n;
        }
        int a = 1, b = 2, c = 4, i = 4;
        while (i++ <= n) {
            int t = ((a + b) % 1000000007 + c) % 1000000007;
            a = b;
            b = c;
            c = t;
        }
        return c;
    }
};
```

### **Rust**

```rust
impl Solution {
    pub fn ways_to_step(n: i32) -> i32 {
        let mut dp = [1, 2, 4];
        let n = n as usize;
        if n <= 3 {
            return dp[n - 1];
        }
        for _ in 3..n {
            dp = [
                dp[1],
                dp[2],
                (((dp[0] + dp[1]) % 1000000007) + dp[2]) % 1000000007,
            ];
        }
        dp[2]
    }
}
```

<!-- tabs:end -->
