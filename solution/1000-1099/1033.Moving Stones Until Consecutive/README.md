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

**方法一：脑筋急转弯**

-   若 $3$ 个数已经相邻，则不用移动，直接返回结果 $[0,0]$；
-   若 $3$ 个数中存在两数之差小于 $3$，最小只需移动 $1$ 次；
-   其他情况最小只需移动 $2$ 次；
-   两边逐个往中间靠，就是最大移动次数 $c - a - 2$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numMovesStones(self, a: int, b: int, c: int) -> List[int]:
        a, b, c = sorted([a, b, c])
        ans = [0] * 2
        if c - a == 2:
            return ans
        if b - a < 3 or c - b < 3:
            ans[0] = 1
        else:
            ans[0] = 2
        ans[1] = c - a - 2
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] numMovesStones(int a, int b, int c) {
        int x = Math.min(a, Math.min(b, c));
        int z = Math.max(a, Math.max(b, c));
        int y = a + b + c - x - z;
        int max = z - x - 2;
        int min = y - x == 1 && z - y == 1 ? 0 : y - x <= 2 || z - y <= 2 ? 1 : 2;
        return new int[]{min, max};
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> numMovesStones(int a, int b, int c) {
        int x = min(min(a, b), c);
        int z = max(max(a, b), c);
        int y = a + b + c - x - z;
        if (z - x == 2) return {0, 0};
        int mx = z - x - 2;
        int mi = y - x < 3 || z - y < 3 ? 1 : 2;
        return {mi, mx};
    }
};
```

### **Go**

```go
func numMovesStones(a int, b int, c int) []int {
	x := min(min(a, b), c)
	z := max(max(a, b), c)
	y := a + b + c - x - z
	if z-x == 2 {
		return []int{0, 0}
	}
	mx := z - x - 2
	mi := 2
	if y-x < 3 || z-y < 3 {
		mi = 1
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

### **...**

```

```

<!-- tabs:end -->
