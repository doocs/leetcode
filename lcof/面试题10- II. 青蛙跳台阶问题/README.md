# [面试题 10- II. 青蛙跳台阶问题](https://leetcode.cn/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/)

## 题目描述

<p>一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 <code>n</code>&nbsp;级的台阶总共有多少种跳法。</p>

<p>答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 2
<strong>输出：</strong>2
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 7
<strong>输出：</strong>21
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 0
<strong>输出：</strong>1</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 100</code></li>
</ul>

<p>注意：本题与主站 70 题相同：<a href="https://leetcode.cn/problems/climbing-stairs/">https://leetcode.cn/problems/climbing-stairs/</a></p>

<p>&nbsp;</p>

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

### **TypeScript**

```ts
function numWays(n: number): number {
    let a = 0;
    let b = 1;
    for (let i = 0; i < n; i++) {
        [a, b] = [b, (a + b) % 1000000007];
    }
    return b;
}
```

### **Rust**

```rust
impl Solution {
    pub fn num_ways(n: i32) -> i32 {
        let mut tup = (0, 1);
        for _ in 0..n {
            tup = (tup.1, (tup.0 + tup.1) % 1000000007);
        }
        tup.1
    }
}
```

### **C#**

```cs
public class Solution {
    public int NumWays(int n) {
        int a = 1, b = 1, tmp;
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
