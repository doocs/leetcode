---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3360.Stone%20Removal%20Game/README.md
---

<!-- problem:start -->

# [3360. 移除石头游戏](https://leetcode.cn/problems/stone-removal-game)

[English Version](/solution/3300-3399/3360.Stone%20Removal%20Game/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Alice 和 Bob 在玩一个游戏，他们俩轮流从一堆石头中移除石头，Alice 先进行操作。</p>

<ul>
	<li>Alice 在第一次操作中移除 <strong>恰好</strong>&nbsp;10 个石头。</li>
	<li>接下来的每次操作中，每位玩家移除的石头数 <strong>恰好</strong>&nbsp;为另一位玩家上一次操作的石头数减 1 。</li>
</ul>

<p>第一位没法进行操作的玩家输掉这个游戏。</p>

<p>给你一个正整数&nbsp;<code>n</code>&nbsp;表示一开始石头的数目，如果 Alice 赢下这个游戏，请你返回&nbsp;<code>true</code>&nbsp;，否则返回 <code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 12</span></p>

<p><span class="example-io"><b>输出：</b>true</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>Alice 第一次操作中移除 10 个石头，剩下 2 个石头给 Bob 。</li>
	<li>Bob 无法移除 9 个石头，所以 Alice 赢下游戏。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 1</span></p>

<p><span class="example-io"><b>输出：</b>false</span></p>

<p><b>解释：</b></p>

<ul>
	<li>Alice 无法移除 10 个石头，所以 Alice 输掉游戏。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 50</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们根据题意模拟游戏的过程，直到无法继续游戏为止。

具体地，我们维护两个变量 $x$ 和 $k$，分别表示当前可以移除的石头数和已经进行的操作次数。初始时 $x = 10$, $k = 0$。

在每一轮操作中，如果当前可以移除的石头数 $x$ 不超过剩余的石头数 $n$，那么我们移除 $x$ 个石头，并将 $x$ 减小 $1$，然后将 $k$ 增加 $1$。否则，我们无法进行操作，游戏结束。

最后，我们判断 $k$ 的奇偶性，如果 $k$ 是奇数，那么 Alice 赢得了游戏，否则 Bob 赢得了游戏。

时间复杂度 $O(\sqrt{n})$。其中 $n$ 是石头的数目。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canAliceWin(self, n: int) -> bool:
        x, k = 10, 0
        while n >= x:
            n -= x
            x -= 1
            k += 1
        return k % 2 == 1
```

#### Java

```java
class Solution {
    public boolean canAliceWin(int n) {
        int x = 10, k = 0;
        while (n >= x) {
            n -= x;
            --x;
            ++k;
        }
        return k % 2 == 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canAliceWin(int n) {
        int x = 10, k = 0;
        while (n >= x) {
            n -= x;
            --x;
            ++k;
        }
        return k % 2;
    }
};
```

#### Go

```go
func canAliceWin(n int) bool {
	x, k := 10, 0
	for n >= x {
		n -= x
		x--
		k++
	}
	return k%2 == 1
}
```

#### TypeScript

```ts
function canAliceWin(n: number): boolean {
    let [x, k] = [10, 0];
    while (n >= x) {
        n -= x;
        --x;
        ++k;
    }
    return k % 2 === 1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
