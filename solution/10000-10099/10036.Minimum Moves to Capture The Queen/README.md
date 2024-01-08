# [10036. 捕获黑皇后需要的最少移动次数](https://leetcode.cn/problems/minimum-moves-to-capture-the-queen)

[English Version](/solution/10000-10099/10036.Minimum%20Moves%20to%20Capture%20The%20Queen/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现有一个下标从 <strong>0</strong> 开始的 <code>8 x 8</code> 棋盘，上面有 <code>3</code> 枚棋子。</p>

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
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/10000-10099/10036.Minimum%20Moves%20to%20Capture%20The%20Queen/images//ex1.png" style="width: 600px; height: 600px; padding: 10px; background: #fff; border-radius: .5rem;" />
<pre>
<strong>输入：</strong>a = 1, b = 1, c = 8, d = 8, e = 2, f = 3
<strong>输出：</strong>2
<strong>解释：</strong>将白色车先移动到 (1, 3) ，然后移动到 (2, 3) 来捕获黑皇后，共需移动 2 次。
由于起始时没有任何棋子正在攻击黑皇后，要想捕获黑皇后，移动次数不可能少于 2 次。
</pre>

<p><strong class="example">示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/10000-10099/10036.Minimum%20Moves%20to%20Capture%20The%20Queen/images//ex2.png" style="width: 600px; height: 600px;padding: 10px; background: #fff; border-radius: .5rem;" />
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

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

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

### **Go**

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

### **TypeScript**

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

### **...**

```

```

<!-- tabs:end -->
