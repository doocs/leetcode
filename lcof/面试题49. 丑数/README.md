# [面试题 49. 丑数](https://leetcode.cn/problems/chou-shu-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。</p>

<p>&nbsp;</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> n = 10
<strong>输出:</strong> 12
<strong>解释: </strong><code>1, 2, 3, 4, 5, 6, 8, 9, 10, 12</code> 是前 10 个丑数。</pre>

<p><strong>说明:&nbsp;</strong>&nbsp;</p>

<ol>
	<li><code>1</code>&nbsp;是丑数。</li>
	<li><code>n</code>&nbsp;<strong>不超过</strong>1690。</li>
</ol>

<p>注意：本题与主站 264 题相同：<a href="https://leetcode.cn/problems/ugly-number-ii/">https://leetcode.cn/problems/ugly-number-ii/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

动态规划法。

定义数组 dp，`dp[i - 1]` 表示第 i 个丑数，那么第 n 个丑数就是 `dp[n - 1]`。最小的丑数是 1，所以 `dp[0] = 1`。

定义 3 个指针 p2，p3，p5，表示下一个丑数是当前指针指向的丑数乘以对应的质因数。初始时，三个指针的值都指向 0。

当 `i∈[1,n)`，`dp[i] = min(dp[p2] * 2, dp[p3] * 3, dp[p5] * 5)`，然后分别比较 `dp[i]` 与 `dp[p2] * 2`、`dp[p3] * 3`、`dp[p5] * 5` 是否相等，若是，则对应的指针加 1。

最后返回 `dp[n - 1]` 即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def nthUglyNumber(self, n: int) -> int:
        dp = [1] * n
        p2 = p3 = p5 = 0
        for i in range(1, n):
            next2, next3, next5 = dp[p2] * 2, dp[p3] * 3, dp[p5] * 5
            dp[i] = min(next2, next3, next5)
            if dp[i] == next2:
                p2 += 1
            if dp[i] == next3:
                p3 += 1
            if dp[i] == next5:
                p5 += 1
        return dp[n - 1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        for (int i = 1; i < n; ++i) {
            int next2 = dp[p2] * 2, next3 = dp[p3] * 3, next5 = dp[p5] * 5;
            dp[i] = Math.min(next2, Math.min(next3, next5));
            if (dp[i] == next2) ++p2;
            if (dp[i] == next3) ++p3;
            if (dp[i] == next5) ++p5;
        }
        return dp[n - 1];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int nthUglyNumber(int n) {
        vector<int> dp(n);
        dp[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        for (int i = 1; i < n; ++i) {
            int next2 = dp[p2] * 2, next3 = dp[p3] * 3, next5 = dp[p5] * 5;
            dp[i] = min(next2, min(next3, next5));
            if (dp[i] == next2) ++p2;
            if (dp[i] == next3) ++p3;
            if (dp[i] == next5) ++p5;
        }
        return dp[n - 1];
    }
};
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {number}
 */
var nthUglyNumber = function (n) {
    const dp = [1];
    let p2 = 0,
        p3 = 0,
        p5 = 0;
    for (let i = 1; i < n; ++i) {
        const next2 = dp[p2] * 2,
            next3 = dp[p3] * 3,
            next5 = dp[p5] * 5;
        dp[i] = Math.min(next2, next3, next5);
        if (dp[i] == next2) ++p2;
        if (dp[i] == next3) ++p3;
        if (dp[i] == next5) ++p5;
    }
    return dp[n - 1];
};
```

### **Go**

```go
func nthUglyNumber(n int) int {
    dp := make([]int, n)
    dp[0] = 1
    p2, p3, p5 := 0, 0, 0
    for i := 1; i < n; i++ {
        next2, next3, next5 := dp[p2]*2, dp[p3]*3, dp[p5]*5
        dp[i] = min(next2, min(next3, next5))
        if dp[i] == next2 {
            p2++
        }
        if dp[i] == next3 {
            p3++
        }
        if dp[i] == next5 {
            p5++
        }
    }
    return dp[n-1]
}

func min(a, b int) int {
    if a < b {
        return a
    }
    return b
}
```

### **Rust**

```rust
impl Solution {
    pub fn nth_ugly_number(n: i32) -> i32 {
        let n = n as usize;
        let mut dp = vec![1; n];
        let mut p2 = 0;
        let mut p3 = 0;
        let mut p5 = 0;
        for i in 1..n {
            let n2 = dp[p2] * 2;
            let n3 = dp[p3] * 3;
            let n5 = dp[p5] * 5;
            dp[i] = n2.min(n3.min(n5));

            if dp[i] == n2 {
                p2 += 1;
            };
            if dp[i] == n3 {
                p3 += 1;
            };
            if dp[i] == n5 {
                p5 += 1;
            };
        }
        dp[n - 1]
    }
}
```

### **C#**

```cs
public class Solution {
    public int NthUglyNumber(int n) {
        if (n < 0) {
            return 0;
        }
        var dp = new int[n];
        dp[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        for (int i=1; i<n; i++) {
            int next2 = dp[p2] * 2, next3 = dp[p3] * 3, next5 = dp[p5] * 5;
            dp[i] = Math.Min(next2, Math.Min(next3, next5));
            if (dp[i] == next2) {
                p2 += 1;
            }
            if (dp[i] == next3) {
                p3 += 1;
            }
            if (dp[i] == next5) {
                p5 += 1;
            }
        }
        return dp[n - 1];
    }
}
```

### **...**

```

```

<!-- tabs:end -->
