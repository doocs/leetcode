# [面试题 10- I. 斐波那契数列](https://leetcode.cn/problems/fei-bo-na-qi-shu-lie-lcof/)

## 题目描述

<p>写一个函数，输入 <code>n</code> ，求斐波那契（Fibonacci）数列的第 <code>n</code> 项（即 <code>F(N)</code>）。斐波那契数列的定义如下：</p>

<pre>
F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), 其中 N > 1.</pre>

<p>斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。</p>

<p>答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 2
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 5
<strong>输出：</strong>5
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= n <= 100</code></li>
</ul>

## 解法

**方法一：递推**

我们定义初始项 $a=0$, $b=1$，接下来执行 $n$ 次循环，每次循环中，计算 $c=a+b$，并更新 $a=b$, $b=c$，循环 $n$ 次后，答案即为 $a$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为输入的整数。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def fib(self, n: int) -> int:
        a, b = 0, 1
        for _ in range(n):
            a, b = b, (a + b) % 1000000007
        return a
```

### **Java**

```java
class Solution {
    public int fib(int n) {
        int a = 0, b = 1;
        while (n-- > 0) {
            int c = (a + b) % 1000000007;
            a = b;
            b = c;
        }
        return a;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int fib(int n) {
        int a = 0, b = 1;
        while (n--) {
            int c = (a + b) % 1000000007;
            a = b;
            b = c;
        }
        return a;
    }
};
```

### **Go**

```go
func fib(n int) int {
	a, b := 0, 1
	for i := 0; i < n; i++ {
		a, b = b, (a+b)%1000000007
	}
	return a
}
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {number}
 */
var fib = function (n) {
    let a = 0;
    let b = 1;
    while (n--) {
        [a, b] = [b, (a + b) % (1e9 + 7)];
    }
    return a;
};
```

### **TypeScript**

```ts
function fib(n: number): number {
    let a: number = 0,
        b: number = 1;
    for (let i: number = 0; i < n; i++) {
        let c: number = (a + b) % 1000000007;
        [a, b] = [b, c];
    }
    return a;
}
```

### **Rust**

```rust
impl Solution {
    pub fn fib(n: i32) -> i32 {
        let mut tup = (0, 1);
        for _ in 0..n {
            tup = (tup.1, (tup.0 + tup.1) % 1000000007);
        }
        return tup.0;
    }
}
```

### **C#**

```cs
public class Solution {
    public int Fib(int n) {
        int a = 0, b = 1, tmp;
        for (int i = 0; i < n; i++) {
            tmp = a;
            a = b;
            b = (tmp + b) % 1000000007;
        }
        return a % 1000000007;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
