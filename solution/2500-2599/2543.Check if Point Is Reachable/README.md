# [2543. 判断一个点是否可以到达](https://leetcode.cn/problems/check-if-point-is-reachable)

[English Version](/solution/2500-2599/2543.Check%20if%20Point%20Is%20Reachable/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个无穷大的网格图。一开始你在&nbsp;<code>(1, 1)</code>&nbsp;，你需要通过有限步移动到达点&nbsp;<code>(targetX, targetY)</code>&nbsp;。</p>

<p><b>每一步</b>&nbsp;，你可以从点&nbsp;<code>(x, y)</code>&nbsp;移动到以下点之一：</p>

<ul>
	<li><code>(x, y - x)</code></li>
	<li><code>(x - y, y)</code></li>
	<li><code>(2 * x, y)</code></li>
	<li><code>(x, 2 * y)</code></li>
</ul>

<p>给你两个整数&nbsp;<code>targetX</code> 和&nbsp;<code>targetY</code>&nbsp;，分别表示你最后需要到达点的 X 和 Y 坐标。如果你可以从&nbsp;<code>(1, 1)</code>&nbsp;出发到达这个点，请你返回<code>true</code> ，否则返回<em>&nbsp;</em><code>false</code><em>&nbsp;</em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>targetX = 6, targetY = 9
<b>输出：</b>false
<b>解释：</b>没法从 (1,1) 出发到达 (6,9) ，所以返回 false 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>targetX = 4, targetY = 7
<b>输出：</b>true
<b>解释：</b>你可以按照以下路径到达：(1,1) -&gt; (1,2) -&gt; (1,4) -&gt; (1,8) -&gt; (1,7) -&gt; (2,7) -&gt; (4,7) 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= targetX, targetY&nbsp;&lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学**

我们注意到，前两种移动方式不会改变横、纵坐标的最大公约数，而后两种移动方式可以使得横、纵坐标的最大公约数乘上 $2$ 的幂次。也就是说，最后的横、纵坐标的最大公约数必须是 $2$ 的幂次。最大公约数不是 $2$ 的幂次，那么就无法到达。

接下来，我们证明，任意满足 $gcd(x, y)=2^k$ 的 $(x, y)$ 均可达。

我们将移动方式反转一下，即从终点往回走，那么 $(x, y)$ 可以移动到 $(x, x+y)$, $(x+y, y)$, $(\frac{x}{2}, y)$ 和 $(x, \frac{y}{2})$。

只要 $x$ 或 $y$ 是偶数，我们就将其除以 $2$，直到 $x$ 和 $y$ 均为奇数。此时，若 $x \neq y$，不妨设 $x \gt y$，那么 $\frac{x+y}{2} \lt x$。由于 $x+y$ 是偶数，我们可以通过操作从 $(x, y)$ 移动到 $(x+y, y)$，再移动到 $(\frac{x+y}{2}, y)$。也就是说，我们总能让 $x$ 和 $y$ 不断变小。循环结束时，如果 $x=y=1$，说明可以到达。

时间复杂度 $O(\log(min(targetX, targetY)))$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isReachable(self, targetX: int, targetY: int) -> bool:
        x = gcd(targetX, targetY)
        return x & (x - 1) == 0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isReachable(int targetX, int targetY) {
        int x = gcd(targetX, targetY);
        return (x & (x - 1)) == 0;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isReachable(int targetX, int targetY) {
        int x = gcd(targetX, targetY);
        return (x & (x - 1)) == 0;
    }
};
```

### **Go**

```go
func isReachable(targetX int, targetY int) bool {
	x := gcd(targetX, targetY)
	return x&(x-1) == 0
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

### **TypeScript**

```ts
function isReachable(targetX: number, targetY: number): boolean {
    const x = gcd(targetX, targetY);
    return (x & (x - 1)) === 0;
}

function gcd(a: number, b: number): number {
    return b == 0 ? a : gcd(b, a % b);
}
```

### **...**

```

```

<!-- tabs:end -->
