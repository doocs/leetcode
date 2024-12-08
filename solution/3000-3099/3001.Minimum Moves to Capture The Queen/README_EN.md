---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3001.Minimum%20Moves%20to%20Capture%20The%20Queen/README_EN.md
rating: 1796
source: Weekly Contest 379 Q2
tags:
    - Math
    - Enumeration
---

<!-- problem:start -->

# [3001. Minimum Moves to Capture The Queen](https://leetcode.com/problems/minimum-moves-to-capture-the-queen)

[中文文档](/solution/3000-3099/3001.Minimum%20Moves%20to%20Capture%20The%20Queen/README.md)

## Description

<!-- description:start -->

<p>There is a <strong>1-indexed</strong> <code>8 x 8</code> chessboard containing <code>3</code> pieces.</p>

<p>You are given <code>6</code> integers <code>a</code>, <code>b</code>, <code>c</code>, <code>d</code>, <code>e</code>, and <code>f</code> where:</p>

<ul>
	<li><code>(a, b)</code> denotes the position of the white rook.</li>
	<li><code>(c, d)</code> denotes the position of the white bishop.</li>
	<li><code>(e, f)</code> denotes the position of the black queen.</li>
</ul>

<p>Given that you can only move the white pieces, return <em>the <strong>minimum</strong> number of moves required to capture the black queen</em>.</p>

<p><strong>Note</strong> that:</p>

<ul>
	<li>Rooks can move any number of squares either vertically or horizontally, but cannot jump over other pieces.</li>
	<li>Bishops can move any number of squares diagonally, but cannot jump over other pieces.</li>
	<li>A rook or a bishop can capture the queen if it is located in a square that they can move to.</li>
	<li>The queen does not move.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3001.Minimum%20Moves%20to%20Capture%20The%20Queen/images/ex1.png" style="width: 600px; height: 600px; padding: 10px; background: #fff; border-radius: .5rem;" />
<pre>
<strong>Input:</strong> a = 1, b = 1, c = 8, d = 8, e = 2, f = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can capture the black queen in two moves by moving the white rook to (1, 3) then to (2, 3).
It is impossible to capture the black queen in less than two moves since it is not being attacked by any of the pieces at the beginning.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3001.Minimum%20Moves%20to%20Capture%20The%20Queen/images/ex2.png" style="width: 600px; height: 600px;padding: 10px; background: #fff; border-radius: .5rem;" />
<pre>
<strong>Input:</strong> a = 5, b = 3, c = 3, d = 4, e = 5, f = 2
<strong>Output:</strong> 1
<strong>Explanation:</strong> We can capture the black queen in a single move by doing one of the following: 
- Move the white rook to (5, 2).
- Move the white bishop to (5, 2).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= a, b, c, d, e, f &lt;= 8</code></li>
	<li>No two pieces are on the same square.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Case Analysis

According to the problem description, we can categorize the scenarios for capturing the black queen as follows:

1. The white rook and the black queen are in the same row with no other pieces in between. In this case, the white rook only needs to move once.
2. The white rook and the black queen are in the same column with no other pieces in between. In this case, the white rook only needs to move once.
3. The white bishop and the black queen are on the same diagonal `\` with no other pieces in between. In this case, the white bishop only needs to move once.
4. The white bishop and the black queen are on the same diagonal `/` with no other pieces in between. In this case, the white bishop only needs to move once.
5. In other cases, only two moves are needed.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

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
