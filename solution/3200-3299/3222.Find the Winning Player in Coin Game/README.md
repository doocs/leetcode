---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3222.Find%20the%20Winning%20Player%20in%20Coin%20Game/README.md
rating: 1269
source: 第 135 场双周赛 Q1
tags:
    - 数学
    - 博弈
    - 模拟
---

<!-- problem:start -->

# [3222. 求出硬币游戏的赢家](https://leetcode.cn/problems/find-the-winning-player-in-coin-game)

[English Version](/solution/3200-3299/3222.Find%20the%20Winning%20Player%20in%20Coin%20Game/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个 <strong>正</strong>&nbsp;整数&nbsp;<code>x</code>&nbsp;和&nbsp;<code>y</code>&nbsp;，分别表示价值为 75 和 10 的硬币的数目。</p>

<p>Alice 和 Bob 正在玩一个游戏。每一轮中，Alice&nbsp;先进行操作，Bob 后操作。每次操作中，玩家需要拿出价值 <b>总和</b>&nbsp;为 115 的硬币。如果一名玩家无法执行此操作，那么这名玩家 <strong>输掉</strong>&nbsp;游戏。</p>

<p>两名玩家都采取 <strong>最优</strong>&nbsp;策略，请你返回游戏的赢家。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>x = 2, y = 7</span></p>

<p><span class="example-io"><b>输出：</b>"Alice"</span></p>

<p><strong>解释：</strong></p>

<p>游戏一次操作后结束：</p>

<ul>
	<li>Alice 拿走 1 枚价值为 75 的硬币和 4 枚价值为 10 的硬币。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>x = 4, y = 11</span></p>

<p><span class="example-io"><b>输出：</b>"Bob"</span></p>

<p><strong>解释：</strong></p>

<p>游戏 2 次操作后结束：</p>

<ul>
	<li>Alice 拿走&nbsp;1 枚价值为 75 的硬币和 4 枚价值为 10 的硬币。</li>
	<li>Bob 拿走&nbsp;1 枚价值为 75 的硬币和 4 枚价值为 10 的硬币。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= x, y &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学

由于每一轮的操作，会消耗 $2$ 枚价值为 $75$ 的硬币和 $8$ 枚价值为 $10$ 的硬币，因此，我们可以计算得到操作的轮数 $k = \min(x / 2, y / 8)$，然后更新 $x$ 和 $y$ 的值，此时 $x$ 和 $y$ 就是经过 $k$ 轮操作后剩余的硬币数目。

如果 $x > 0$ 且 $y \geq 4$，那么 Alice 还可以继续操作，此时 Bob 就输了，返回 "Alice"；否则，返回 "Bob"。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def losingPlayer(self, x: int, y: int) -> str:
        k = min(x // 2, y // 8)
        x -= k * 2
        y -= k * 8
        return "Alice" if x and y >= 4 else "Bob"
```

#### Java

```java
class Solution {
    public String losingPlayer(int x, int y) {
        int k = Math.min(x / 2, y / 8);
        x -= k * 2;
        y -= k * 8;
        return x > 0 && y >= 4 ? "Alice" : "Bob";
    }
}
```

#### C++

```cpp
class Solution {
public:
    string losingPlayer(int x, int y) {
        int k = min(x / 2, y / 8);
        x -= k * 2;
        y -= k * 8;
        return x && y >= 4 ? "Alice" : "Bob";
    }
};
```

#### Go

```go
func losingPlayer(x int, y int) string {
	k := min(x/2, y/8)
	x -= 2 * k
	y -= 8 * k
	if x > 0 && y >= 4 {
		return "Alice"
	}
	return "Bob"
}
```

#### TypeScript

```ts
function losingPlayer(x: number, y: number): string {
    const k = Math.min((x / 2) | 0, (y / 8) | 0);
    x -= k * 2;
    y -= k * 8;
    return x && y >= 4 ? 'Alice' : 'Bob';
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
