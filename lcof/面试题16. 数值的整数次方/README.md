# [面试题 16. 数值的整数次方](https://leetcode.cn/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/)

## 题目描述

<p>实现 <a href="https://www.cplusplus.com/reference/valarray/pow/">pow(<em>x</em>, <em>n</em>)</a> ，即计算 x 的 n 次幂函数（即，x<sup>n</sup>）。不得使用库函数，同时不需要考虑大数问题。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>x = 2.00000, n = 10
<strong>输出：</strong>1024.00000
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>x = 2.10000, n = 3
<strong>输出：</strong>9.26100</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>x = 2.00000, n = -2
<strong>输出：</strong>0.25000
<strong>解释：</strong>2<sup>-2</sup> = 1/2<sup>2</sup> = 1/4 = 0.25</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-100.0 < x < 100.0</code></li>
	<li><code>-2<sup>31</sup> <= n <= 2<sup>31</sup>-1</code></li>
	<li><code>-10<sup>4</sup> <= x<sup>n</sup> <= 10<sup>4</sup></code></li>
</ul>

<p> </p>

<p>注意：本题与主站 50 题相同：<a href="https://leetcode.cn/problems/powx-n/">https://leetcode.cn/problems/powx-n/</a></p>

## 解法

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def myPow(self, x: float, n: int) -> float:
        if n == 0:
            return 1
        if n == 1:
            return x
        if n == -1:
            return 1 / x
        half = self.myPow(x, n // 2)
        return half * half * self.myPow(x, n % 2)
```

### **Java**

```java
class Solution {
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n == -1) return 1 / x;
        double half = myPow(x, n / 2);
        return half * half * myPow(x, n % 2);
    }
}
```

### **JavaScript**

```js
/**
 * @param {number} x
 * @param {number} n
 * @return {number}
 */
var myPow = function (x, n) {
    let r = 1;
    let tmp = x;
    let tag = 0;
    if (n < 0) {
        tag = 1;
        n = -n;
    }
    while (n) {
        if (n & 1) {
            r *= tmp;
        }
        tmp *= tmp;
        n >>>= 1;
    }
    return tag ? 1 / r : r;
};
```

### **Go**

```go
func myPow(x float64, n int) float64 {
    p := abs(n)
    res := 1.0
    for p != 0 {
        if p&1 == 1 {
            res *= x
        }
        x *= x
        p = p >>1
    }
    if n < 0 {
        return 1/res
    }
    return res
}

func abs(x int) int {
    if x > 0 {
        return x
    }
    return -x
}

```

### **C++**

```cpp
class Solution {
public:
    double myPow(double x, int n) {
        long m = n;
        if (m < 0) {
            x = 1 / x;
            m = -m;
        }
        double ans = 1;
        while (m) {
            if (m & 1) {
                ans *= x;
            }
            x *= x;
            m >>= 1;
        }
        return ans;
    }
};
```

### **C#**

```cs
public class Solution {
    public double MyPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        if (n == -1) {
            return 1 / x;
        }
        double half = MyPow(x, n / 2);
        return half * half * MyPow(x, n % 2);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
