# [483. 最小好进制](https://leetcode.cn/problems/smallest-good-base)

[English Version](/solution/0400-0499/0483.Smallest%20Good%20Base/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>以字符串的形式给出 <code>n</code>&nbsp;, 以字符串的形式返回<em> <code>n</code> 的最小 <strong>好进制</strong> </em>&nbsp;。</p>

<p>如果 <code>n</code> 的 &nbsp;<code>k(k&gt;=2)</code>&nbsp;进制数的所有数位全为1，则称&nbsp;<code>k(k&gt;=2)</code>&nbsp;是 <code>n</code> 的一个&nbsp;<strong>好进制&nbsp;</strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = "13"
<strong>输出：</strong>"3"
<strong>解释：</strong>13 的 3 进制是 111。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = "4681"
<strong>输出：</strong>"8"
<strong>解释：</strong>4681 的 8 进制是 11111。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = "1000000000000000000"
<strong>输出：</strong>"999999999999999999"
<strong>解释：</strong>1000000000000000000 的 999999999999999999 进制是 11。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n</code> 的取值范围是&nbsp;<code>[3, 10<sup>18</sup>]</code></li>
	<li><code>n</code> 没有前导 0</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学**

假设 $n$ 在 $k$ 进制下的所有位数均为 $1$，且位数为 $m+1$，那么有式子 ①：

$$
n=k^0+k^1+k^2+...+k^m
$$

当 $m=0$ 时，上式 $n=1$，而题目 $n$ 取值范围为 $[3, 10^{18}]$，因此 $m>0$。

当 $m=1$ 时，上式 $n=k^0+k^1=1+k$，即 $k=n-1>=2$。

我们来证明一般情况下的两个结论，以帮助解决本题。

**结论一：** $m<\log _{k} n$

注意到式子 ① 是个首项为 $1$，且公比为 $k$ 的等比数列。利用等比数列求和公式，我们可以得出：

$$
n=\frac{1-k^{m+1}}{1-k}
$$

变形得：

$$
k^{m+1}=k \times n-n+1 < k \times n
$$

移项得：

$$
m<\log _{k} n
$$

题目 $n$ 取值范围为 $[3, 10^{18}]$，又因为 $k>=2$，因此 $m<\log _{k} n<\log _{2} 10^{18}<60$。

**结论二：** $k=\left \lfloor \sqrt[m]{n} \right \rfloor $

$$
n=k^0+k^1+k^2+...+k^m>k^m
$$

根据二项式定理：

$$
(a+b)^{n}=\sum_{k=0}^{n}\left(\begin{array}{l}
n \\
k
\end{array}\right) a^{n-k} b^{k}
$$

整合，可得：

$$
(k+1)^{m}=\left(\begin{array}{c}
m \\
0
\end{array}\right) k^{0}+\left(\begin{array}{c}
m \\
1
\end{array}\right) k^{1}+\left(\begin{array}{c}
m \\
2
\end{array}\right) k^{2}+\cdots+\left(\begin{array}{c}
m \\
m
\end{array}\right) k^{m}
$$

当 $m>1$ 时，满足：

$$
\forall i \in[1, m-1],\left(\begin{array}{c}
m \\
i
\end{array}\right)>1
$$

所以有：

$$
\begin{aligned}
(k+1)^{m} &=\left(\begin{array}{c}
m \\
0
\end{array}\right) k^{0}+\left(\begin{array}{c}
m \\
1
\end{array}\right) k^{1}+\left(\begin{array}{c}
m \\
2
\end{array}\right) k^{2}+\cdots+\left(\begin{array}{c}
m \\
m
\end{array}\right) k^{m} \\
&>k^{0}+k^{1}+k^{2}+\cdots+k^{m}=n
\end{aligned}
$$

即：

$$
k < \sqrt[m]{n} < k+1
$$

由于 $k$ 是整数，因此 $k=\left \lfloor \sqrt[m]{n} \right \rfloor $。

综上，依据结论一，我们知道 $m$ 的取值范围为 $[1,log_{k}n)$，且 $m=1$ 时必然有解。随着 $m$ 的增大，进制 $k$ 不断减小。所以我们只需要从大到小检查每一个 $m$ 可能的取值，利用结论二快速算出对应的 $k$ 值，然后校验计算出的 $k$ 值是否有效即可。如果 $k$ 值有效，我们即可返回结果。

时间复杂度 $O(log^{2}n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def smallestGoodBase(self, n: str) -> str:
        def cal(k, m):
            p = s = 1
            for i in range(m):
                p *= k
                s += p
            return s

        num = int(n)
        for m in range(63, 1, -1):
            l, r = 2, num - 1
            while l < r:
                mid = (l + r) >> 1
                if cal(mid, m) >= num:
                    r = mid
                else:
                    l = mid + 1
            if cal(l, m) == num:
                return str(l)
        return str(num - 1)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String smallestGoodBase(String n) {
        long num = Long.parseLong(n);
        for (int len = 63; len >= 2; --len) {
            long radix = getRadix(len, num);
            if (radix != -1) {
                return String.valueOf(radix);
            }
        }
        return String.valueOf(num - 1);
    }

    private long getRadix(int len, long num) {
        long l = 2, r = num - 1;
        while (l < r) {
            long mid = l + r >>> 1;
            if (calc(mid, len) >= num) r = mid;
            else l = mid + 1;
        }
        return calc(r, len) == num ? r : -1;
    }

    private long calc(long radix, int len) {
        long p = 1;
        long sum = 0;
        for (int i = 0; i < len; ++i) {
            if (Long.MAX_VALUE - sum < p) {
                return Long.MAX_VALUE;
            }
            sum += p;
            if (Long.MAX_VALUE / p < radix) {
                p = Long.MAX_VALUE;
            } else {
                p *= radix;
            }
        }
        return sum;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string smallestGoodBase(string n) {
        long v = stol(n);
        int mx = floor(log(v) / log(2));
        for (int m = mx; m > 1; --m) {
            int k = pow(v, 1.0 / m);
            long mul = 1, s = 1;
            for (int i = 0; i < m; ++i) {
                mul *= k;
                s += mul;
            }
            if (s == v) {
                return to_string(k);
            }
        }
        return to_string(v - 1);
    }
};
```

### **...**

```

```

<!-- tabs:end -->
