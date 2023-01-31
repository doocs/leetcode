# [343. 整数拆分](https://leetcode.cn/problems/integer-break)

[English Version](/solution/0300-0399/0343.Integer%20Break/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个正整数&nbsp;<code>n</code>&nbsp;，将其拆分为 <code>k</code> 个 <strong>正整数</strong> 的和（&nbsp;<code>k &gt;= 2</code>&nbsp;），并使这些整数的乘积最大化。</p>

<p>返回 <em>你可以获得的最大乘积</em>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入: </strong>n = 2
<strong>输出: </strong>1
<strong>解释: </strong>2 = 1 + 1, 1 × 1 = 1。</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入: </strong>n = 10
<strong>输出: </strong>36
<strong>解释: </strong>10 = 3 + 3 + 4, 3 ×&nbsp;3 ×&nbsp;4 = 36。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 58</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们定义 $dp[i]$ 表示正整数 $n$ 能获得的最大乘积，初始化 $dp[1] = 1$。答案即为 $dp[n]$。

状态转移方程为：

$$
dp[i] = max(dp[i], dp[i - j] \times j, (i - j) \times j) \quad (j \in [0, i))
$$

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为正整数 $n$。

**方法二：数学**

当 $n \lt 4$ 时，$n$ 不能拆分成至少两个正整数的和，因此 $n - 1$ 是最大乘积。当 $n \ge 4$ 时，我们尽可能多地拆分 $3$，当剩下的最后一段为 $4$ 时，我们将其拆分为 $2 + 2$，这样乘积最大。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def integerBreak(self, n: int) -> int:
        dp = [1] * (n + 1)
        for i in range(2, n + 1):
            for j in range(1, i):
                dp[i] = max(dp[i], dp[i - j] * j, (i - j) * j)
        return dp[n]
```

```python
class Solution:
    def integerBreak(self, n: int) -> int:
        if n < 4:
            return n - 1
        if n % 3 == 0:
            return pow(3, n // 3)
        if n % 3 == 1:
            return pow(3, n // 3 - 1) * 4
        return pow(3, n // 3) * 2
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int integerBreak(int n) {
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
    public int integerBreak(int n) {
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
    int integerBreak(int n) {
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
    int integerBreak(int n) {
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
func integerBreak(n int) int {
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
func integerBreak(n int) int {
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

### **C**

```c
int integerBreak(int n) {
    if (n < 4) {
        return n - 1;
    }
    int count = (n - 2) / 3;
    return pow(3, count) * (n - count * 3);
}
```

### **Rust**

```rust
impl Solution {
    pub fn integer_break(n: i32) -> i32 {
        if n < 4 {
            return n - 1;
        }
        let count = (n - 2) / 3;
        3i32.pow(count as u32) * (n - count * 3)
    }
}
```

### **TypeScript**

```ts
function integerBreak(n: number): number {
    let dp = new Array(n + 1).fill(1);
    for (let i = 3; i <= n; i++) {
        for (let j = 1; j < i; j++) {
            dp[i] = Math.max(dp[i], j * (i - j), j * dp[i - j]);
        }
    }
    return dp.pop();
}
```

```ts
function integerBreak(n: number): number {
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

### **...**

```

```

<!-- tabs:end -->
