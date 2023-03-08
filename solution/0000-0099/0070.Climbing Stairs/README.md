# [70. 爬楼梯](https://leetcode.cn/problems/climbing-stairs)

[English Version](/solution/0000-0099/0070.Climbing%20Stairs/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>假设你正在爬楼梯。需要 <code>n</code>&nbsp;阶你才能到达楼顶。</p>

<p>每次你可以爬 <code>1</code> 或 <code>2</code> 个台阶。你有多少种不同的方法可以爬到楼顶呢？</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 2
<strong>输出：</strong>2
<strong>解释：</strong>有两种方法可以爬到楼顶。
1. 1 阶 + 1 阶
2. 2 阶</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>3
<strong>解释：</strong>有三种方法可以爬到楼顶。
1. 1 阶 + 1 阶 + 1 阶
2. 1 阶 + 2 阶
3. 2 阶 + 1 阶
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 45</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：递推**

我们定义 $f[i]$ 表示爬到第 $i$ 阶楼梯的方法数，那么 $f[i]$ 可以由 $f[i - 1]$ 和 $f[i - 2]$ 转移而来，即：

$$
f[i] = f[i - 1] + f[i - 2]
$$

初始条件为 $f[0] = 1$，$f[1] = 1$，即爬到第 0 阶楼梯的方法数为 1，爬到第 1 阶楼梯的方法数也为 1。

答案即为 $f[n]$。

由于 $f[i]$ 只与 $f[i - 1]$ 和 $f[i - 2]$ 有关，因此我们可以只用两个变量 $a$ 和 $b$ 来维护当前的方法数，空间复杂度降低为 $O(1)$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def climbStairs(self, n: int) -> int:
        a, b = 0, 1
        for _ in range(n):
            a, b = b, a + b
        return b
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int climbStairs(int n) {
        int a = 0, b = 1;
        for (int i = 0; i < n; ++i) {
            int c = a + b;
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
    int climbStairs(int n) {
        int a = 0, b = 1;
        for (int i = 0; i < n; ++i) {
            int c = a + b;
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
var climbStairs = function (n) {
    let a = 0,
        b = 1;
    for (let i = 0; i < n; ++i) {
        const c = a + b;
        a = b;
        b = c;
    }
    return b;
};
```

### **Go**

```go
func climbStairs(n int) int {
    a, b := 0, 1
    for i := 0; i < n; i++ {
        a, b = b, a + b
    }
    return b
}
```

### **TypeScript**

```ts
function climbStairs(n: number): number {
    let p = 1;
    let q = 1;
    for (let i = 1; i < n; i++) {
        [p, q] = [q, p + q];
    }
    return q;
}
```

### **Rust**

```rust
impl Solution {
    pub fn climb_stairs(n: i32) -> i32 {
        let (mut p, mut q) = (1, 1);
        for i in 1..n {
            let t = p + q;
            p = q;
            q = t;
        }
        q
    }
}
```

### **PHP**

```php
class Solution {
    /**
     * @param Integer $n
     * @return Integer
     */
    function climbStairs($n) {
        if ($n <= 2) {
            return $n;
        }
        $dp = [0, 1, 2];
        for ($i = 3; $i <= $n; $i++) {
            $dp[$i] = $dp[$i - 2] + $dp[$i - 1];
        }
        return $dp[$n];
    }
}
```

### **...**

```

```

<!-- tabs:end -->
