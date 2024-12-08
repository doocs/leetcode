---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3001.Minimum%20Moves%20to%20Capture%20The%20Queen/README.md
rating: 1796
source: 第 379 场周赛 Q2
tags:
    - 数学
    - 枚举
---

<!-- problem:start -->

# [3001. 捕获黑皇后需要的最少移动次数](https://leetcode.cn/problems/minimum-moves-to-capture-the-queen)

[English Version](/solution/3000-3099/3001.Minimum%20Moves%20to%20Capture%20The%20Queen/README_EN.md)

## 题目描述

<!-- description:start -->

<p>现有一个下标从 <strong>1</strong> 开始的 <code>8 x 8</code> 棋盘，上面有 <code>3</code> 枚棋子。</p>

<p>给你 <code>6</code> 个整数 <code>a</code> 、<code>b</code> 、<code>c</code> 、<code>d</code> 、<code>e</code> 和 <code>f</code> ，其中：</p>

<ul>
	<li><code>(a, b)</code> 表示白色车的位置。</li>
	<li><code>(c, d)</code> 表示白色象的位置。</li>
	<li><code>(e, f)</code> 表示黑皇后的位置。</li>
</ul>

<p>假定你只能移动白色棋子，返回捕获黑皇后所需的<strong>最少</strong>移动次数。</p>

<p><strong>请注意</strong>：</p>

<ul>
	<li>车可以向垂直或水平方向移动任意数量的格子，但不能跳过其他棋子。</li>
	<li>象可以沿对角线方向移动任意数量的格子，但不能跳过其他棋子。</li>
	<li>如果车或象能移向皇后所在的格子，则认为它们可以捕获皇后。</li>
	<li>皇后不能移动。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3001.Minimum%20Moves%20to%20Capture%20The%20Queen/images/ex1.png" style="width: 600px; height: 600px; padding: 10px; background: #fff; border-radius: .5rem;" />
<pre>
<strong>输入：</strong>a = 1, b = 1, c = 8, d = 8, e = 2, f = 3
<strong>输出：</strong>2
<strong>解释：</strong>将白色车先移动到 (1, 3) ，然后移动到 (2, 3) 来捕获黑皇后，共需移动 2 次。
由于起始时没有任何棋子正在攻击黑皇后，要想捕获黑皇后，移动次数不可能少于 2 次。
</pre>

<p><strong class="example">示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3001.Minimum%20Moves%20to%20Capture%20The%20Queen/images/ex2.png" style="width: 600px; height: 600px;padding: 10px; background: #fff; border-radius: .5rem;" />
<pre>
<strong>输入：</strong>a = 5, b = 3, c = 3, d = 4, e = 5, f = 2
<strong>输出：</strong>1
<strong>解释：</strong>可以通过以下任一方式移动 1 次捕获黑皇后：
- 将白色车移动到 (5, 2) 。
- 将白色象移动到 (5, 2) 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= a, b, c, d, e, f &lt;= 8</code></li>
	<li>两枚棋子不会同时出现在同一个格子上。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分类讨论

根据题意，我们可以将捕获黑皇后的情况分为以下几种：

1. 白色车和黑皇后在同一行，且中间没有其他棋子，此时只需要移动白色车 $1$ 一次；
1. 白色车和黑皇后在同一列，且中间没有其他棋子，此时只需要移动白色车 $1$ 一次；
1. 白色象和黑皇后在对角线 `\` 上，且中间没有其他棋子，此时只需要移动白色象 $1$ 一次；
1. 白色象和黑皇后在对角线 `/` 上，且中间没有其他棋子，此时只需要移动白色象 $1$ 一次；
1. 其他情况，只需要移动两次。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minMovesToCaptureTheQueen(
        self, a: int, b: int, c: int, d: int, e: int, f: int
    ) -> int:
        if a == e and (c != a or (d - b) * (d - f) > 0):
            return 1
        if b == f and (d != b or (c - a) * (c - e) > 0):
            return 1
        if c - e == d - f and (a - e != b - f or (a - c) * (a - e) > 0):
            return 1
        if c - e == f - d and (a - e != f - b or (a - c) * (a - e) > 0):
            return 1
        return 2
```

#### Java

```java
class Solution {
    public int minMovesToCaptureTheQueen(int a, int b, int c, int d, int e, int f) {
        if (a == e && (c != a || (d - b) * (d - f) > 0)) {
            return 1;
        }
        if (b == f && (d != b || (c - a) * (c - e) > 0)) {
            return 1;
        }
        if (c - e == d - f && (a - e != b - f || (a - c) * (a - e) > 0)) {
            return 1;
        }
        if (c - e == f - d && (a - e != f - b || (a - c) * (a - e) > 0)) {
            return 1;
        }
        return 2;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minMovesToCaptureTheQueen(int a, int b, int c, int d, int e, int f) {
        if (a == e && (c != a || (d - b) * (d - f) > 0)) {
            return 1;
        }
        if (b == f && (d != b || (c - a) * (c - e) > 0)) {
            return 1;
        }
        if (c - e == d - f && (a - e != b - f || (a - c) * (a - e) > 0)) {
            return 1;
        }
        if (c - e == f - d && (a - e != f - b || (a - c) * (a - e) > 0)) {
            return 1;
        }
        return 2;
    }
};
```

#### Go

```go
func minMovesToCaptureTheQueen(a int, b int, c int, d int, e int, f int) int {
	if a == e && (c != a || (d-b)*(d-f) > 0) {
		return 1
	}
	if b == f && (d != b || (c-a)*(c-e) > 0) {
		return 1
	}
	if c-e == d-f && (a-e != b-f || (a-c)*(a-e) > 0) {
		return 1
	}
	if c-e == f-d && (a-e != f-b || (a-c)*(a-e) > 0) {
		return 1
	}
	return 2
}
```

#### TypeScript

```ts
function minMovesToCaptureTheQueen(
    a: number,
    b: number,
    c: number,
    d: number,
    e: number,
    f: number,
): number {
    if (a === e && (c !== a || (d - b) * (d - f) > 0)) {
        return 1;
    }
    if (b === f && (d !== b || (c - a) * (c - e) > 0)) {
        return 1;
    }
    if (c - e === d - f && (a - e !== b - f || (a - c) * (a - e) > 0)) {
        return 1;
    }
    if (c - e === f - d && (a - e !== f - b || (a - c) * (a - e) > 0)) {
        return 1;
    }
    return 2;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_moves_to_capture_the_queen(a: i32, b: i32, c: i32, d: i32, e: i32, f: i32) -> i32 {
        if a == e && (c != a || (d - b) * (d - f) > 0) {
            return 1;
        }
        if b == f && (d != b || (c - a) * (c - e) > 0) {
            return 1;
        }
        if c - e == d - f && (a - e != b - f || (a - c) * (a - e) > 0) {
            return 1;
        }
        if c - e == f - d && (a - e != f - b || (a - c) * (a - e) > 0) {
            return 1;
        }
        return 2;
    }
}
```

#### Cangjie

```cj
class Solution {
    func minMovesToCaptureTheQueen(a: Int64, b: Int64, c: Int64, d: Int64, e: Int64, f: Int64): Int64 {
        if (a == e && (c != a || (d - b) * (d - f) > 0)) {
            return 1
        }
        if (b == f && (d != b || (c - a) * (c - e) > 0)) {
            return 1
        }
        if (c - e == d - f && (a - e != b - f || (a - c) * (a - e) > 0)) {
            return 1
        }
        if (c - e == f - d && (a - e != f - b || (a - c) * (a - e) > 0)) {
            return 1
        }
        2
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
