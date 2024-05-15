---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3001.Minimum%20Moves%20to%20Capture%20The%20Queen/README_EN.md
rating: 1796
tags:
    - Array
    - Enumeration
---

# [3001. Minimum Moves to Capture The Queen](https://leetcode.com/problems/minimum-moves-to-capture-the-queen)

[中文文档](/solution/3000-3099/3001.Minimum%20Moves%20to%20Capture%20The%20Queen/README.md)

## Description

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

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def minMovesToCaptureTheQueen(
        self, a: int, b: int, c: int, d: int, e: int, f: int
    ) -> int:
        def check(dirs, sx, sy, bx, by) -> bool:
            for dx, dy in pairwise(dirs):
                for k in range(1, 8):
                    x = sx + dx * k
                    y = sy + dy * k
                    if not (1 <= x <= 8 and 1 <= y <= 8) or (x, y) == (bx, by):
                        break
                    if (x, y) == (e, f):
                        return True
            return False

        dirs1 = (-1, 0, 1, 0, -1)
        dirs2 = (-1, 1, 1, -1, -1)
        return 1 if check(dirs1, a, b, c, d) or check(dirs2, c, d, a, b) else 2
```

```java
class Solution {
    private final int[] dirs1 = {-1, 0, 1, 0, -1};
    private final int[] dirs2 = {-1, 1, 1, -1, -1};
    private int e, f;

    public int minMovesToCaptureTheQueen(int a, int b, int c, int d, int e, int f) {
        this.e = e;
        this.f = f;
        return check(dirs1, a, b, c, d) || check(dirs2, c, d, a, b) ? 1 : 2;
    }

    private boolean check(int[] dirs, int sx, int sy, int bx, int by) {
        for (int d = 0; d < 4; ++d) {
            for (int k = 1; k < 8; ++k) {
                int x = sx + dirs[d] * k;
                int y = sy + dirs[d + 1] * k;
                if (x < 1 || x > 8 || y < 1 || y > 8 || (x == bx && y == by)) {
                    break;
                }
                if (x == e && y == f) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

```cpp
class Solution {
public:
    int minMovesToCaptureTheQueen(int a, int b, int c, int d, int e, int f) {
        int dirs[2][5] = {{-1, 0, 1, 0, -1}, {-1, 1, 1, -1, -1}};
        auto check = [&](int i, int sx, int sy, int bx, int by) {
            for (int d = 0; d < 4; ++d) {
                for (int k = 1; k < 8; ++k) {
                    int x = sx + dirs[i][d] * k;
                    int y = sy + dirs[i][d + 1] * k;
                    if (x < 1 || x > 8 || y < 1 || y > 8 || (x == bx && y == by)) {
                        break;
                    }
                    if (x == e && y == f) {
                        return true;
                    }
                }
            }
            return false;
        };
        return check(0, a, b, c, d) || check(1, c, d, a, b) ? 1 : 2;
    }
};
```

```go
func minMovesToCaptureTheQueen(a int, b int, c int, d int, e int, f int) int {
	dirs := [2][5]int{{-1, 0, 1, 0, -1}, {-1, 1, 1, -1, -1}}
	check := func(i, sx, sy, bx, by int) bool {
		for d := 0; d < 4; d++ {
			for k := 1; k < 8; k++ {
				x := sx + dirs[i][d]*k
				y := sy + dirs[i][d+1]*k
				if x < 1 || x > 8 || y < 1 || y > 8 || (x == bx && y == by) {
					break
				}
				if x == e && y == f {
					return true
				}
			}
		}
		return false
	}
	if check(0, a, b, c, d) || check(1, c, d, a, b) {
		return 1
	}
	return 2
}
```

```ts
function minMovesToCaptureTheQueen(
    a: number,
    b: number,
    c: number,
    d: number,
    e: number,
    f: number,
): number {
    const dirs: number[][] = [
        [-1, 0, 1, 0, -1],
        [-1, 1, 1, -1, -1],
    ];
    const check = (i: number, sx: number, sy: number, bx: number, by: number): boolean => {
        for (let d = 0; d < 4; ++d) {
            for (let k = 1; k < 8; ++k) {
                const x = sx + dirs[i][d] * k;
                const y = sy + dirs[i][d + 1] * k;
                if (x < 1 || x > 8 || y < 1 || y > 8) {
                    break;
                }
                if (x === bx && y === by) {
                    break;
                }
                if (x === e && y === f) {
                    return true;
                }
            }
        }
        return false;
    };
    return check(0, a, b, c, d) || check(1, c, d, a, b) ? 1 : 2;
}
```

<!-- tabs:end -->

<!-- end -->
