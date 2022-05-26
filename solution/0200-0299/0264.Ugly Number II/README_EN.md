# [264. Ugly Number II](https://leetcode.com/problems/ugly-number-ii)

[中文文档](/solution/0200-0299/0264.Ugly%20Number%20II/README.md)

## Description

<p>An <strong>ugly number</strong> is a positive integer whose prime factors are limited to <code>2</code>, <code>3</code>, and <code>5</code>.</p>

<p>Given an integer <code>n</code>, return <em>the</em> <code>n<sup>th</sup></code> <em><strong>ugly number</strong></em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 10
<strong>Output:</strong> 12
<strong>Explanation:</strong> [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 1
<strong>Explanation:</strong> 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1690</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
        return dp[-1]
```

### **Java**

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
    let dp = [1];
    let p2 = 0,
        p3 = 0,
        p5 = 0;
    for (let i = 1; i < n; ++i) {
        const next2 = dp[p2] * 2,
            next3 = dp[p3] * 3,
            next5 = dp[p5] * 5;
        dp[i] = Math.min(next2, Math.min(next3, next5));
        if (dp[i] == next2) ++p2;
        if (dp[i] == next3) ++p3;
        if (dp[i] == next5) ++p5;
        dp.push(dp[i]);
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

### **C#**

```cs
public class Solution {
    public int NthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        for (int i = 1; i < n; ++i)
        {
            int next2 = dp[p2] * 2, next3 = dp[p3] * 3, next5 = dp[p5] * 5;
            dp[i] = Math.Min(next2, Math.Min(next3, next5));
            if (dp[i] == next2)
            {
                ++p2;
            }
            if (dp[i] == next3)
            {
                ++p3;
            }
            if (dp[i] == next5)
            {
                ++p5;
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
