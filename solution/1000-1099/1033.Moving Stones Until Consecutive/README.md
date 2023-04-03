# [1033. 移动石子直到连续](https://leetcode.cn/problems/moving-stones-until-consecutive)

[English Version](/solution/1000-1099/1033.Moving%20Stones%20Until%20Consecutive/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>三枚石子放置在数轴上，位置分别为 <code>a</code>，<code>b</code>，<code>c</code>。</p>

<p>每一回合，你可以从两端之一拿起一枚石子（位置最大或最小），并将其放入两端之间的任一空闲位置。形式上，假设这三枚石子当前分别位于位置 <code>x, y, z</code> 且 <code>x < y < z</code>。那么就可以从位置 <code>x</code> 或者是位置 <code>z</code> 拿起一枚石子，并将该石子移动到某一整数位置 <code>k</code> 处，其中 <code>x < k < z</code> 且 <code>k != y</code>。</p>

<p>当你无法进行任何移动时，即，这些石子的位置连续时，游戏结束。</p>

<p>要使游戏结束，你可以执行的最小和最大移动次数分别是多少？ 以长度为 2 的数组形式返回答案：<code>answer = [minimum_moves, maximum_moves]</code></p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>a = 1, b = 2, c = 5
<strong>输出：</strong>[1, 2]
<strong>解释：</strong>将石子从 5 移动到 4 再移动到 3，或者我们可以直接将石子移动到 3。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>a = 4, b = 3, c = 2
<strong>输出：</strong>[0, 0]
<strong>解释：</strong>我们无法进行任何移动。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 <= a <= 100</code></li>
	<li><code>1 <= b <= 100</code></li>
	<li><code>1 <= c <= 100</code></li>
	<li><code>a != b, b != c, c != a</code></li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：分类讨论**

我们先将 $a, b, c$ 排序，记为 $x, y, z$，即 $x \lt y \lt z$。

接下来分情况讨论：

1. 如果 $z - x \leq 2$，说明 $3$ 个数已经相邻，不用移动，结果为 $[0, 0]$；
1. 否则，如果 $y - x \lt 3$，或者 $z - y \lt 3$，说明有两个数只间隔一个位置，我们只需要把另一个数移动到这两个数的中间，最小移动次数为 $1$；其他情况，最小移动次数为 $2$；
1. 最大移动次数就是两边的数字逐个往中间靠，最多移动 $z - x - 2$ 次。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numMovesStones(self, a: int, b: int, c: int) -> List[int]:
        x, z = min(a, b, c), max(a, b, c)
        y = a + b + c - x - z
        mi = mx = 0
        if z - x > 2:
            mi = 1 if y - x < 3 or z - y < 3 else 2
            mx = z - x - 2
        return [mi, mx]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] numMovesStones(int a, int b, int c) {
        int x = Math.min(a, Math.min(b, c));
        int z = Math.max(a, Math.max(b, c));
        int y = a + b + c - x - z;
        int mi = 0, mx = 0;
        if (z - x > 2) {
            mi = y - x < 3 || z - y < 3 ? 1 : 2;
            mx = z - x - 2;
        }
        return new int[] {mi, mx};
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> numMovesStones(int a, int b, int c) {
        int x = min({a, b, c});
        int z = max({a, b, c});
        int y = a + b + c - x - z;
        int mi = 0, mx = 0;
        if (z - x > 2) {
            mi = y - x < 3 || z - y < 3 ? 1 : 2;
            mx = z - x - 2;
        }
        return {mi, mx};
    }
};
```

### **Go**

```go
func numMovesStones(a int, b int, c int) []int {
	x := min(a, min(b, c))
	z := max(a, max(b, c))
	y := a + b + c - x - z
	mi, mx := 0, 0
	if z-x > 2 {
		mi = 2
		if y-x < 3 || z-y < 3 {
			mi = 1
		}
		mx = z - x - 2
	}
	return []int{mi, mx}
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function numMovesStones(a: number, b: number, c: number): number[] {
    const x = Math.min(a, Math.min(b, c));
    const z = Math.max(a, Math.max(b, c));
    const y = a + b + c - x - z;
    let mi = 0,
        mx = 0;
    if (z - x > 2) {
        mi = y - x < 3 || z - y < 3 ? 1 : 2;
        mx = z - x - 2;
    }
    return [mi, mx];
}
```

### **...**

```

```

<!-- tabs:end -->
