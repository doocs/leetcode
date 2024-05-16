---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3021.Alice%20and%20Bob%20Playing%20Flower%20Game/README.md
rating: 1581
source: 第 382 场周赛 Q3
tags:
    - 数学
---

<!-- problem:start -->

# [3021. Alice 和 Bob 玩鲜花游戏](https://leetcode.cn/problems/alice-and-bob-playing-flower-game)

[English Version](/solution/3000-3099/3021.Alice%20and%20Bob%20Playing%20Flower%20Game/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Alice 和 Bob 在一个长满鲜花的环形草地玩一个回合制游戏。环形的草地上有一些鲜花，Alice 到&nbsp;Bob 之间顺时针有 <code>x</code>&nbsp;朵鲜花，逆时针有 <code>y</code>&nbsp;朵鲜花。</p>

<p>游戏过程如下：</p>

<ol>
	<li>Alice 先行动。</li>
	<li>每一次行动中，当前玩家必须选择顺时针或者逆时针，然后在这个方向上摘一朵鲜花。</li>
	<li>一次行动结束后，如果所有鲜花都被摘完了，那么 <strong>当前</strong>&nbsp;玩家抓住对手并赢得游戏的胜利。</li>
</ol>

<p>给你两个整数&nbsp;<code>n</code>&nbsp;和&nbsp;<code>m</code>&nbsp;，你的任务是求出满足以下条件的所有&nbsp;<code>(x, y)</code>&nbsp;对：</p>

<ul>
	<li>按照上述规则，Alice 必须赢得游戏。</li>
	<li>Alice 顺时针方向上的鲜花数目&nbsp;<code>x</code>&nbsp;必须在区间&nbsp;<code>[1,n]</code>&nbsp;之间。</li>
	<li>Alice 逆时针方向上的鲜花数目 <code>y</code>&nbsp;必须在区间&nbsp;<code>[1,m]</code>&nbsp;之间。</li>
</ul>

<p>请你返回满足题目描述的数对&nbsp;<code>(x, y)</code>&nbsp;的数目。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>n = 3, m = 2
<b>输出：</b>3
<b>解释：</b>以下数对满足题目要求：(1,2) ，(3,2) ，(2,1) 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>n = 1, m = 1
<b>输出：</b>0
<b>解释：</b>没有数对满足题目要求。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n, m &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学

根据题目描述，每一次行动，玩家都会选择顺时针或者逆时针方向，然后摘一朵鲜花。由于 Alice 先行动，因此当 $x + y$ 为奇数时，Alice 一定会赢得游戏。

因此，鲜花数目 $x$ 和 $y$ 满足以下条件：

1. $x + y$ 为奇数；
2. $1 \le x \le n$；
3. $1 \le y \le m$。

若 $x$ 为奇数，$y$ 一定为偶数。此时 $x$ 的取值个数为 $\lceil \frac{n}{2} \rceil$，$y$ 的取值个数为 $\lfloor \frac{m}{2} \rfloor$，因此满足条件的数对个数为 $\lceil \frac{n}{2} \rceil \times \lfloor \frac{m}{2} \rfloor$。

若 $x$ 为偶数，$y$ 一定为奇数。此时 $x$ 的取值个数为 $\lfloor \frac{n}{2} \rfloor$，$y$ 的取值个数为 $\lceil \frac{m}{2} \rceil$，因此满足条件的数对个数为 $\lfloor \frac{n}{2} \rfloor \times \lceil \frac{m}{2} \rceil$。

因此，满足条件的数对个数为 $\lceil \frac{n}{2} \rceil \times \lfloor \frac{m}{2} \rfloor + \lfloor \frac{n}{2} \rfloor \times \lceil \frac{m}{2} \rceil$，即 $\lfloor \frac{n + 1}{2} \rfloor \times \lfloor \frac{m}{2} \rfloor + \lfloor \frac{n}{2} \rfloor \times \lfloor \frac{m + 1}{2} \rfloor$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def flowerGame(self, n: int, m: int) -> int:
        a1 = (n + 1) // 2
        b1 = (m + 1) // 2
        a2 = n // 2
        b2 = m // 2
        return a1 * b2 + a2 * b1
```

```java
class Solution {
    public long flowerGame(int n, int m) {
        long a1 = (n + 1) / 2;
        long b1 = (m + 1) / 2;
        long a2 = n / 2;
        long b2 = m / 2;
        return a1 * b2 + a2 * b1;
    }
}
```

```cpp
class Solution {
public:
    long long flowerGame(int n, int m) {
        long long a1 = (n + 1) / 2;
        long long b1 = (m + 1) / 2;
        long long a2 = n / 2;
        long long b2 = m / 2;
        return a1 * b2 + a2 * b1;
    }
};
```

```go
func flowerGame(n int, m int) int64 {
	a1, b1 := (n+1)/2, (m+1)/2
	a2, b2 := n/2, m/2
	return int64(a1*b2 + a2*b1)
}
```

```ts
function flowerGame(n: number, m: number): number {
    const [a1, b1] = [(n + 1) >> 1, (m + 1) >> 1];
    const [a2, b2] = [n >> 1, m >> 1];
    return a1 * b2 + a2 * b1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：数学（优化）

方法一得出的结果为 $\lfloor \frac{n + 1}{2} \rfloor \times \lfloor \frac{m}{2} \rfloor + \lfloor \frac{n}{2} \rfloor \times \lfloor \frac{m + 1}{2} \rfloor$。

如果 $n$ 和 $m$ 都是奇数，那么结果为 $\frac{n + 1}{2} \times \frac{m - 1}{2} + \frac{n - 1}{2} \times \frac{m + 1}{2}$，即 $\frac{n \times m - 1}{2}$。

如果 $n$ 和 $m$ 都是偶数，那么结果为 $\frac{n}{2} \times \frac{m}{2} + \frac{n}{2} \times \frac{m}{2}$，即 $\frac{n \times m}{2}$。

如果 $n$ 是奇数，且 $m$ 是偶数，那么结果为 $\frac{n + 1}{2} \times \frac{m}{2} + \frac{n - 1}{2} \times \frac{m}{2}$，即 $\frac{n \times m}{2}$。

如果 $n$ 是偶数，且 $m$ 是奇数，那么结果为 $\frac{n}{2} \times \frac{m - 1}{2} + \frac{n}{2} \times \frac{m + 1}{2}$，即 $\frac{n \times m}{2}$。

上面四种情况可以合并为 $\lfloor \frac{n \times m}{2} \rfloor$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def flowerGame(self, n: int, m: int) -> int:
        return (n * m) // 2
```

```java
class Solution {
    public long flowerGame(int n, int m) {
        return ((long) n * m) / 2;
    }
}
```

```cpp
class Solution {
public:
    long long flowerGame(int n, int m) {
        return ((long long) n * m) / 2;
    }
};
```

```go
func flowerGame(n int, m int) int64 {
	return int64((n * m) / 2)
}
```

```ts
function flowerGame(n: number, m: number): number {
    return Number(((BigInt(n) * BigInt(m)) / 2n) | 0n);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
