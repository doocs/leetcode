# [1227. 飞机座位分配概率](https://leetcode.cn/problems/airplane-seat-assignment-probability)

[English Version](/solution/1200-1299/1227.Airplane%20Seat%20Assignment%20Probability/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有 <code>n</code> 位乘客即将登机，飞机正好有 <code>n</code> 个座位。第一位乘客的票丢了，他随便选了一个座位坐下。</p>

<p>剩下的乘客将会：</p>

<ul>
	<li>
	<p>如果他们自己的座位还空着，就坐到自己的座位上，</p>
	</li>
	<li>当他们自己的座位被占用时，随机选择其他座位</li>
</ul>

<p>第 <code>n</code>&nbsp;位乘客坐在自己的座位上的概率是多少？</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>1.00000
<strong>解释：</strong>第一个人只会坐在自己的位置上。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入:</strong> n = 2
<strong>输出:</strong> 0.50000
<strong>解释：</strong>在第一个人选好座位坐下后，第二个人坐在自己的座位上的概率是 0.5。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10^5</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学**

用 $f(n)$ 表示当有 $n$ 位乘客登机时，第 $n$ 位乘客坐在自己的座位上的概率。从最简单的情况开始考虑：

-   当 $n=1$ 时，只有 $1$ 位乘客和 $1$ 个座位，因此第 $1$ 位乘客只能坐在第 $1$ 个座位上，$f(1)=1$；

-   当 $n=2$ 时，有 $2$ 个座位，每个座位有 $0.5$ 的概率被第 $1$ 位乘客选中，当第 $1$ 位乘客选中座位之后，第 $2$ 位乘客只能选择剩下的座位，因此第 $2$ 位乘客有 $0.5$ 的概率坐在自己的座位上，$f(2)=0.5$。

当 $n>2$ 时，如何计算 $f(n)$ 的值？考虑第 $1$ 位乘客选择的座位，有以下三种情况。

-   第 $1$ 位乘客有 $\frac{1}{n}$ 的概率选择第 $1$ 个座位，则所有乘客都可以坐在自己的座位上，此时第 $n$ 位乘客坐在自己的座位上的概率是 $1.0$。

-   第 $1$ 位乘客有 $\frac{1}{n}$ 的概率选择第 $n$ 个座位，则第 $2$ 位乘客到第 $n-1$ 位乘客都可以坐在自己的座位上，第 $n$ 位乘客只能坐在第 $1$ 个座位上，此时第 $n$ 位乘客坐在自己的座位上的概率是 $0.0$。

-   第 $1$ 位乘客有 $\frac{n-2}{n}$ 的概率选择其余的座位，每个座位被选中的概率是 $\frac{1}{n}$。
    假设第 $1$ 位乘客选择第 $i$ 个座位，其中 $2 \le i \le n-1$，则第 $2$ 位乘客到第 $i-1$ 位乘客都可以坐在自己的座位上，第 $i$ 位乘客到第 $n$ 位乘客的座位不确定，第 $i$ 位乘客会在剩下的 $n-(i-1)=n-i+1$ 个座位中随机选择（包括第 $1$ 个座位和第 $i+1$ 个座位到第 $n$ 个座位）。由于此时剩下的乘客数和座位数都是 $n-i+1$，有 $1$ 位乘客会随机选择座位，因此问题规模从 $n$ 减小至 $n-i+1$。

结合上述三种情况，可以得到 $f(n)$ 的递推式：

$$
\begin{aligned}
f(n) &= \frac{1}{n} \times 1.0 + \frac{1}{n} \times 0.0 + \frac{1}{n} \times \sum_{i=2}^{n-1} f(n-i+1) \\
&= \frac{1}{n}(1.0+\sum_{i=2}^{n-1} f(n-i+1))
\end{aligned}
$$

上述递推式中，$i$ 的取值个数有 $n-2$ 个，由于 $i$ 的取值个数必须是非负整数，因此只有当 $n-2 \ge 0$ 即 $n \ge 2$ 时，上述递推式才成立。

如果直接利用上述递推式计算 $f(n)$ 的值，则时间复杂度为 $O(n^2)$，无法通过全部测试用例，因此需要优化。

将上述递推式中的 $n$ 换成 $n-1$，可以得到递推式：

$$
f(n-1) = \frac{1}{n-1}(1.0+\sum_{i=2}^{n-2} f(n-i))
$$

上述递推式中，$i$ 的取值个数有 $n-3$ 个，只有当 $n-3 \ge 0$ 即 $n \ge 3$ 时，上述递推式才成立。

当 $n \ge 3$ 时，上述两个递推式可以写成如下形式：

$$
\begin{aligned}
n \times f(n) &= 1.0+\sum_{i=2}^{n-1} f(n-i+1) \\
(n-1) \times f(n-1) &= 1.0+\sum_{i=2}^{n-2} f(n-i)
\end{aligned}
$$

将上述两式相减：

$$
\begin{aligned}
&~~~~~ n \times f(n) - (n-1) \times f(n-1) \\
&= (1.0+\sum_{i=2}^{n-1} f(n-i+1)) - (1.0+\sum_{i=2}^{n-2} f(n-i)) \\
&= \sum_{i=2}^{n-1} f(n-i+1) - \sum_{i=2}^{n-2} f(n-i) \\
&= f(2)+f(3)+...+f(n-1) - (f(2)+f(3)+...+f(n-2)) \\
&= f(n-1)
\end{aligned}
$$

整理后得到简化的递推式：

$$
\begin{aligned}
n \times f(n) &= n \times f(n-1) \\
f(n) &= f(n-1)
\end{aligned}
$$

由于已知 $f(1)=1$ 和 $f(2)=0.5$，因此当 $n \ge 3$ 时，根据 $f(n) = f(n-1)$ 可知，对任意正整数 $n$ 都有 $f(n)=0.5$。又由于 $f(2)=0.5$，因此当 $n \ge 2$ 时，对任意正整数 $n$ 都有 $f(n)=0.5$。

由此可以得到 $f(n)$ 的结果：

$$
f(n) = \begin{cases}
1.0, & n = 1 \\
0.5, & n \ge 2
\end{cases}
$$

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def nthPersonGetsNthSeat(self, n: int) -> float:
        return 1 if n == 1 else 0.5
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public double nthPersonGetsNthSeat(int n) {
        return n == 1 ? 1 : .5;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    double nthPersonGetsNthSeat(int n) {
        return n == 1 ? 1 : .5;
    }
};
```

### **Go**

```go
func nthPersonGetsNthSeat(n int) float64 {
	if n == 1 {
		return 1
	}
	return .5
}
```

### **...**

```

```

<!-- tabs:end -->
