# [面试题 14- I. 剪绳子](https://leetcode.cn/problems/jian-sheng-zi-lcof/)

## 题目描述

<p>给你一根长度为 <code>n</code> 的绳子，请把绳子剪成整数长度的 <code>m</code> 段（m、n都是整数，n&gt;1并且m&gt;1），每段绳子的长度记为 <code>k[0],k[1]...k[m-1]</code> 。请问 <code>k[0]*k[1]*...*k[m-1]</code> 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入: </strong>2
<strong>输出: </strong>1
<strong>解释: </strong>2 = 1 + 1, 1 &times; 1 = 1</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入: </strong>10
<strong>输出: </strong>36
<strong>解释: </strong>10 = 3 + 3 + 4, 3 &times;&nbsp;3 &times;&nbsp;4 = 36</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 58</code></li>
</ul>

<p>注意：本题与主站 343 题相同：<a href="https://leetcode.cn/problems/integer-break/">https://leetcode.cn/problems/integer-break/</a></p>

## 解法

**方法一：动态规划**

我们定义 $dp[i]$ 表示正整数 $n$ 能获得的最大乘积，初始化 $dp[1] = 1$。答案即为 $dp[n]$。

状态转移方程为：

$$
dp[i] = max(dp[i], dp[i - j] \times j, (i - j) \times j) \quad (j \in [0, i))
$$

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为正整数 $n$。

**方法二：数学**

当 $n \lt 4$，此时 $n$ 不能拆分成至少两个正整数的和，因此 $n - 1$ 是最大乘积。当 $n \ge 4$ 时，我们尽可能多地拆分 $3$，当剩下的最后一段为 $4$ 时，我们将其拆分为 $2 + 2$，这样乘积最大。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def cuttingRope(self, n: int) -> int:
        dp = [1] * (n + 1)
        for i in range(2, n + 1):
            for j in range(1, i):
                dp[i] = max(dp[i], dp[i - j] * j, (i - j) * j)
        return dp[n]
```

```python
class Solution:
    def cuttingRope(self, n: int) -> int:
        if n < 4:
            return n - 1
        if n % 3 == 0:
            return pow(3, n // 3)
        if n % 3 == 1:
            return pow(3, n // 3 - 1) * 4
        return pow(3, n // 3) * 2
```

### **Java**

```java
class Solution {
    public int cuttingRope(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j < i; ++j) {
                dp[i] = Math.max(Math.max(dp[i], dp[i - j] * j), (i - j) * j);
            }
        }
        return dp[n];
    }
}
```

```java
class Solution {
    public int cuttingRope(int n) {
        if (n < 4) {
            return n - 1;
        }
        if (n % 3 == 0) {
            return (int) Math.pow(3, n / 3);
        }
        if (n % 3 == 1) {
            return (int) Math.pow(3, n / 3 - 1) * 4;
        }
        return (int) Math.pow(3, n / 3) * 2;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int cuttingRope(int n) {
        vector<int> dp(n + 1);
        dp[1] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j < i; ++j) {
                dp[i] = max(max(dp[i], dp[i - j] * j), (i - j) * j);
            }
        }
        return dp[n];
    }
};
```

```cpp
class Solution {
public:
    int cuttingRope(int n) {
        if (n < 4) {
            return n - 1;
        }
        if (n % 3 == 0) {
            return pow(3, n / 3);
        }
        if (n % 3 == 1) {
            return pow(3, n / 3 - 1) * 4;
        }
        return pow(3, n / 3) * 2;
    }
};
```

### **Go**

```go
func cuttingRope(n int) int {
    dp := make([]int, n+1)
	dp[1] = 1
	for i := 2; i <= n; i++ {
		for j := 1; j < i; j++ {
			dp[i] = max(max(dp[i], dp[i-j]*j), (i-j)*j)
		}
	}
	return dp[n]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

```go
func cuttingRope(n int) int {
	if n < 4 {
		return n - 1
	}
	if n%3 == 0 {
		return int(math.Pow(3, float64(n/3)))
	}
	if n%3 == 1 {
		return int(math.Pow(3, float64(n/3-1))) * 4
	}
	return int(math.Pow(3, float64(n/3))) * 2
}
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {number}
 */
var cuttingRope = function (n) {
    if (n < 4) {
        return n - 1;
    }
    const m = Math.floor(n / 3);
    if (n % 3 == 0) {
        return 3 ** m;
    }
    if (n % 3 == 1) {
        return 3 ** (m - 1) * 4;
    }
    return 3 ** m * 2;
};
```

### **TypeScript**

```ts
function cuttingRope(n: number): number {
    if (n < 4) {
        return n - 1;
    }
    const m = Math.floor(n / 3);
    if (n % 3 == 0) {
        return 3 ** m;
    }
    if (n % 3 == 1) {
        return 3 ** (m - 1) * 4;
    }
    return 3 ** m * 2;
}
```

### **Rust**

```rust
impl Solution {
    pub fn cutting_rope(n: i32) -> i32 {
        if n < 4 {
            return n - 1;
        }
        let count = (n - 2) / 3;
        3i32.pow(count as u32) * (n - count * 3)
    }
}
```

### **C#**

```cs
public class Solution {
    public int CuttingRope(int n) {
        if (n < 4) {
            return n - 1;
        }
        if (n % 3 == 0) {
            return (int) Math.Pow(3, n / 3);
        }
        if (n % 3 == 1) {
            return (int) Math.Pow(3, n / 3 - 1) * 4;
        }
        return (int) Math.Pow(3, n / 3) * 2;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
