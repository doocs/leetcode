# [264. Ugly Number II](https://leetcode.com/problems/ugly-number-ii)

[中文文档](/solution/0200-0299/0264.Ugly%20Number%20II/README.md)

## Description

<p>Write a program to find the <code>n</code>-th ugly number.</p>

<p>Ugly numbers are<strong> positive numbers</strong> whose prime factors only include <code>2, 3, 5</code>.&nbsp;</p>

<p><strong>Example:</strong></p>

<pre>

<strong>Input:</strong> n = 10

<strong>Output:</strong> 12

<strong>Explanation: </strong><code>1, 2, 3, 4, 5, 6, 8, 9, 10, 12</code> is the sequence of the first <code>10</code> ugly numbers.</pre>

<p><strong>Note: </strong>&nbsp;</p>

<ol>
    <li><code>1</code> is typically treated as an ugly number.</li>
    <li><code>n</code> <b>does not exceed 1690</b>.</li>
</ol>

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
        return dp[n - 1]
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

### **...**

```

```

<!-- tabs:end -->
