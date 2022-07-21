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

尽可能将绳子以长度 3 等分剪为多段时，乘积最大。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def cuttingRope(self, n: int) -> int:
        if n < 4:
            return n - 1
        ans = 1
        while n > 4:
            ans *= 3
            n -= 3
        ans *= n
        return ans
```

### **Java**

```java
class Solution {
    public int cuttingRope(int n) {
        if (n < 4) {
            return n - 1;
        }
        int ans = 1;
        while (n > 4) {
            ans *= 3;
            n -= 3;
        }
        ans *= n;
        return ans;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {number}
 */
var cuttingRope = function (n) {
    if (n < 4) return n - 1;
    let ans = 1;
    while (n > 4) {
        ans *= 3;
        n -= 3;
    }
    ans *= n;
    return ans;
};
```

### **Go**

```go
func cuttingRope(n int) int {
	if n < 4 {
		return n - 1
	}
	ans := 1
	for n > 4 {
		ans *= 3
		n -= 3
	}
	ans *= n
	return ans
}
```

### **C++**

```cpp
class Solution {
public:
    int cuttingRope(int n) {
        vector<int> dp(n + 1);
        dp[0] = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = i; j <= n; ++j) {
                dp[j] = max(dp[j], dp[j - i] * i);
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
        if (n < 4) return n - 1;
        int ans = 1;
        while (n > 4)
        {
            ans *= 3;
            n -= 3;
        }
        ans *= n;
        return ans;
    }
};
```

### **Rust**

```rust
impl Solution {
    pub fn cutting_rope(mut n: i32) -> i32 {
        if n < 4 {
            return n - 1;
        }
        let mut res = 1;
        while n > 4 {
            res *= 3;
            n -= 3;
        }
        res * n
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
        int ans = 1;
        while (n > 4) {
            ans *= 3;
            n -= 3;
        }
        ans *= n;
        return ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
