# [50. Pow(x, n)](https://leetcode.cn/problems/powx-n)

[English Version](/solution/0000-0099/0050.Pow%28x%2C%20n%29/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>实现&nbsp;<a href="https://www.cplusplus.com/reference/valarray/pow/" target="_blank">pow(<em>x</em>, <em>n</em>)</a>&nbsp;，即计算 <code>x</code> 的整数&nbsp;<code>n</code> 次幂函数（即，<code>x<sup>n</sup></code><sup><span style="font-size:10.8333px"> </span></sup>）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>x = 2.00000, n = 10
<strong>输出：</strong>1024.00000
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>x = 2.10000, n = 3
<strong>输出：</strong>9.26100
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>x = 2.00000, n = -2
<strong>输出：</strong>0.25000
<strong>解释：</strong>2<sup>-2</sup> = 1/2<sup>2</sup> = 1/4 = 0.25
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-100.0 &lt; x &lt; 100.0</code></li>
	<li><code>-2<sup>31</sup> &lt;= n &lt;= 2<sup>31</sup>-1</code></li>
	<li><code>-10<sup>4</sup> &lt;= x<sup>n</sup> &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def myPow(self, x: float, n: int) -> float:
        if n == 0:
            return 1
        if n < 0:
            return 1 / self.myPow(x, -n)
        y = self.myPow(x, n >> 1)
        return y * y if (n & 1) == 0 else y * y * x
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? pow(x, N) : 1.0 / pow(x, -N);
    }

    public double pow(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = pow(x, N >> 1);
        return (N & 1) == 0 ? y * y : y * y * x;
    }
}
```

### **TypeScript**

```ts
function myPow(x: number, n: number): number {
    let res = 1;
    if (n < 0) {
        n = -n;
        x = 1 / x;
    }
    for (let i = n; i != 0; i = Math.floor(i / 2)) {
        if ((i & 1) == 1) {
            res *= x;
        }
        x *= x;
    }
    return res;
}
```

### **...**

```

```

<!-- tabs:end -->
