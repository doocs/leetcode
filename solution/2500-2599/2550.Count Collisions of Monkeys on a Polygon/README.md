# [2550. 猴子碰撞的方法数](https://leetcode.cn/problems/count-collisions-of-monkeys-on-a-polygon)

[English Version](/solution/2500-2599/2550.Count%20Collisions%20of%20Monkeys%20on%20a%20Polygon/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现在有一个正凸多边形，其上共有 <code>n</code> 个顶点。顶点按顺时针方向从 <code>0</code> 到 <code>n - 1</code> 依次编号。每个顶点上 <strong>正好有一只猴子</strong> 。下图中是一个 6 个顶点的凸多边形。</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2550.Count%20Collisions%20of%20Monkeys%20on%20a%20Polygon/images/hexagon.jpg" style="width: 300px; height: 293px;" /></p>

<p>每个猴子同时移动到相邻的顶点。顶点 <code>i</code> 的相邻顶点可以是：</p>

<ul>
	<li>顺时针方向的顶点 <code>(i + 1) % n</code> ，或</li>
	<li>逆时针方向的顶点 <code>(i - 1 + n) % n</code> 。</li>
</ul>

<p>如果移动后至少有两只猴子停留在同一个顶点上或者相交在一条边上，则会发生 <strong>碰撞</strong> 。</p>

<p>返回猴子至少发生 <strong>一次碰撞 </strong>的移动方法数。由于答案可能非常大，请返回对 <code>10<sup>9</sup>+7</code> 取余后的结果。</p>

<p><strong>注意</strong>，每只猴子只能移动一次。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>6
<strong>解释：</strong>共计 8 种移动方式。
下面列出两种会发生碰撞的方式：
- 猴子 1 顺时针移动；猴子 2 逆时针移动；猴子 3 顺时针移动。猴子 1 和猴子 2 碰撞。
- 猴子 1 逆时针移动；猴子 2 逆时针移动；猴子 3 顺时针移动。猴子 1 和猴子 3 碰撞。
可以证明，有 6 种让猴子碰撞的方法。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 4
<strong>输出：</strong>14
<strong>解释：</strong>可以证明，有 14 种让猴子碰撞的方法。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学（快速幂）**

每一只猴子都有两种移动方式，即顺时针或逆时针。因此，一共有 $2^n$ 种移动方式。不碰撞的移动方式只有两种，即所有猴子都顺时针移动或所有猴子都逆时针移动。因此，碰撞的移动方式有 $2^n - 2$ 种。

我们可以用快速幂求出 $2^n$ 的值，然后用 $2^n - 2$ 求出碰撞的移动方式数，最后对 $10^9 + 7$ 取余即可。

时间复杂度为 $O(\log n)$，空间复杂度为 $O(1)$。其中 $n$ 为猴子的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def monkeyMove(self, n: int) -> int:
        mod = 10**9 + 7
        return (pow(2, n, mod) - 2) % mod
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int monkeyMove(int n) {
        final int mod = (int) 1e9 + 7;
        return (int) (qmi(2, n, mod) - 2 + mod) % mod;
    }

    public long qmi(long a, long k, long p) {
        long res = 1;
        while (k != 0) {
            if ((k & 1) == 1) {
                res = res * a % p;
            }
            k >>= 1;
            a = a * a % p;
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int monkeyMove(int n) {
        const int mod = 1e9 + 7;
        return (qmi(2, n, mod) - 2 + mod) % mod;
    }

    long qmi(long a, long k, long p) {
        long res = 1;
        while (k != 0) {
            if ((k & 1) == 1) {
                res = res * a % p;
            }
            k >>= 1;
            a = a * a % p;
        }
        return res;
    }
};
```

### **Go**

```go
func monkeyMove(n int) int {
	const mod = 1e9 + 7
	return (qmi(2, n, mod) - 2 + mod) % mod
}

func qmi(a, k, p int) int {
	res := 1
	for k != 0 {
		if k&1 == 1 {
			res = res * a % p
		}
		k >>= 1
		a = a * a % p
	}
	return res
}
```

### **TypeScript**

```ts
function monkeyMove(n: number): number {
    const mod = BigInt(10 ** 9 + 7);
    return Number((qmi(2n, n, mod) - 2n + mod) % mod);
}

function qmi(a: bigint, k: number, p: bigint): bigint {
    let res = 1n;
    while (k) {
        if ((k & 1) === 1) {
            res = (res * a) % p;
        }
        k >>= 1;
        a = (a * a) % p;
    }
    return res;
}
```

### **...**

```

```

<!-- tabs:end -->
