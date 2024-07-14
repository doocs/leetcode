---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0858.Mirror%20Reflection/README.md
tags:
    - 几何
    - 数学
    - 数论
---

<!-- problem:start -->

# [858. 镜面反射](https://leetcode.cn/problems/mirror-reflection)

[English Version](/solution/0800-0899/0858.Mirror%20Reflection/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有一个特殊的正方形房间，每面墙上都有一面镜子。除西南角以外，每个角落都放有一个接受器，编号为&nbsp;<code>0</code>，&nbsp;<code>1</code>，以及&nbsp;<code>2</code>。</p>

<p>正方形房间的墙壁长度为&nbsp;<code>p</code>，一束激光从西南角射出，首先会与东墙相遇，入射点到接收器 <code>0</code> 的距离为 <code>q</code> 。</p>

<p>返回光线最先遇到的接收器的编号（保证光线最终会遇到一个接收器）。</p>
&nbsp;

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0858.Mirror%20Reflection/images/reflection.png" style="width: 218px; height: 217px;" />
<pre>
<strong>输入：</strong>p = 2, q = 1
<strong>输出：</strong>2
<strong>解释：</strong>这条光线在第一次被反射回左边的墙时就遇到了接收器 2 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>p = 3, q = 1
<strong>输入：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= q &lt;= p &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学

根据题意，光线在每次反射时，都会向上或向下移动 $q$ 的距离，向右移动 $p$ 的距离。而由于光线最后一定会遇到接收器，因此，我们需要找到一个最小的 $k$，使得 $k \times q$ 是 $p$ 的倍数。

同时，根据 $k$ 的奇偶性，我们可以确定光线最终到达了西侧还是东侧。如果 $k$ 是偶数，那么光线最终到达的是西侧，否则光线最终到达的是东侧。

另外，根据 $k \times q$ 除以 $p$ 的结果的奇偶性，我们可以确定光线最终到达的是北侧还是南侧。如果 $k \times q$ 是 $p$ 的偶数倍，那么光线最终到达的是南侧，否则光线最终到达的是北侧。

时间复杂度 $O(\log p)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def mirrorReflection(self, p: int, q: int) -> int:
        g = gcd(p, q)
        p = (p // g) % 2
        q = (q // g) % 2
        if p == 1 and q == 1:
            return 1
        return 0 if p == 1 else 2
```

#### Java

```java
class Solution {
    public int mirrorReflection(int p, int q) {
        int g = gcd(p, q);
        p = (p / g) % 2;
        q = (q / g) % 2;
        if (p == 1 && q == 1) {
            return 1;
        }
        return p == 1 ? 0 : 2;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int mirrorReflection(int p, int q) {
        int g = __gcd(p, q);
        p = (p / g) % 2;
        q = (q / g) % 2;
        if (p == 1 && q == 1) {
            return 1;
        }
        return p == 1 ? 0 : 2;
    }
};
```

#### Go

```go
func mirrorReflection(p int, q int) int {
	g := gcd(p, q)
	p = (p / g) % 2
	q = (q / g) % 2
	if p == 1 && q == 1 {
		return 1
	}
	if p == 1 {
		return 0
	}
	return 2
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

#### TypeScript

```ts
function mirrorReflection(p: number, q: number): number {
    const g = gcd(p, q);
    p = Math.floor(p / g) % 2;
    q = Math.floor(q / g) % 2;
    if (p === 1 && q === 1) {
        return 1;
    }
    return p === 1 ? 0 : 2;
}

function gcd(a: number, b: number): number {
    return b === 0 ? a : gcd(b, a % b);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
