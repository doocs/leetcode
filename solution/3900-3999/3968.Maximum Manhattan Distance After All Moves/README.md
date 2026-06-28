---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3968.Maximum%20Manhattan%20Distance%20After%20All%20Moves/README.md
rating: 1278
source: 第 507 场周赛 Q1
---

<!-- problem:start -->

# [3968. 移动后的最大曼哈顿距离](https://leetcode.cn/problems/maximum-manhattan-distance-after-all-moves)

[English Version](/solution/3900-3999/3968.Maximum%20Manhattan%20Distance%20After%20All%20Moves/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由字符 <code>'U'</code>、<code>'D'</code>、<code>'L'</code>、<code>'R'</code> 和 <code>'_'</code> 组成的字符串 <code>moves</code>。</p>

<p>从原点 <code>(0, 0)</code> 出发，每个字符表示二维平面上的一次移动：</p>

<ul>
	<li><code>'U'</code>：向上移动 1 个单位。</li>
	<li><code>'D'</code>：向下移动 1 个单位。</li>
	<li><code>'L'</code>：向左移动 1 个单位。</li>
	<li><code>'R'</code>：向右移动 1 个单位。</li>
	<li><code>'_'</code>：可以独立地替换为 <code>'U'</code>、<code>'D'</code>、<code>'L'</code> 或 <code>'R'</code> 中的任意一个字符。</li>
</ul>

<p>返回执行完所有移动后，能够达到的距离原点的<strong>&nbsp;最大曼哈顿距离&nbsp;</strong>。</p>

<p>两点 <code>(x<sub>1</sub>, y<sub>1</sub>)</code> 和 <code>(x<sub>2</sub>, y<sub>2</sub>)</code> 之间的<strong>&nbsp;曼哈顿距离</strong>&nbsp;为 <code>|x<sub>1</sub> - x<sub>2</sub>| + |y<sub>1</sub> - y<sub>2</sub>|</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">moves = "L_D_"</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<p>一种最优选择为：</p>

<ul>
	<li><code>'L'</code>：<code>(0, 0) -&gt; (-1, 0)</code></li>
	<li>将 <code>'_'</code> 视为 <code>'D'</code>：<code>(-1, 0) -&gt; (-1, -1)</code></li>
	<li><code>'D'</code>：<code>(-1, -1) -&gt; (-1, -2)</code></li>
	<li>将 <code>'_'</code> 视为 <code>'L'</code>：<code>(-1, -2) -&gt; (-2, -2)</code></li>
</ul>

<p>最终位置到原点的曼哈顿距离为 <code>|0 - (-2)| + |0 - (-2)| = 4</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">moves = "U_R"</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p>一种最优选择为：</p>

<ul>
	<li><code>'U'</code>：<code>(0, 0) -&gt; (0, 1)</code></li>
	<li>将 <code>'_'</code> 视为 <code>'U'</code>：<code>(0, 1) -&gt; (0, 2)</code></li>
	<li><code>'R'</code>：<code>(0, 2) -&gt; (1, 2)</code></li>
</ul>

<p>最终位置到原点的曼哈顿距离为 <code>|0 - 1| + |0 - 2| = 3</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= moves.length &lt;= 10<sup>5</sup></code></li>
	<li><code>moves</code> 仅由 <code>'U'</code>、<code>'D'</code>、<code>'L'</code>、<code>'R'</code> 和 <code>'_'</code> 组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

我们可以用一个变量 $x$ 来记录上下移动的距离，用一个变量 $y$ 来记录左右移动的距离，用一个变量 $z$ 来记录可以替换的次数。

那么最终的曼哈顿距离就是 $|x| + |y| + z$。

时间复杂度 $O(n)$，其中 $n$ 是字符串 $\textit{moves}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxDistance(self, moves: str) -> int:
        x = y = z = 0
        for c in moves:
            if c == "U":
                x -= 1
            elif c == "D":
                x += 1
            elif c == "L":
                y -= 1
            elif c == "R":
                y += 1
            else:
                z += 1
        return abs(x) + abs(y) + z
```

#### Java

```java
class Solution {
    public int maxDistance(String moves) {
        int x = 0, y = 0, z = 0;
        for (char c : moves.toCharArray()) {
            if (c == 'U') {
                x -= 1;
            } else if (c == 'D') {
                x += 1;
            } else if (c == 'L') {
                y -= 1;
            } else if (c == 'R') {
                y += 1;
            } else {
                z += 1;
            }
        }
        return Math.abs(x) + Math.abs(y) + z;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxDistance(string moves) {
        int x = 0, y = 0, z = 0;
        for (char c : moves) {
            if (c == 'U') {
                x -= 1;
            } else if (c == 'D') {
                x += 1;
            } else if (c == 'L') {
                y -= 1;
            } else if (c == 'R') {
                y += 1;
            } else {
                z += 1;
            }
        }
        return abs(x) + abs(y) + z;
    }
};
```

#### Go

```go
func maxDistance(moves string) int {
    x, y, z := 0, 0, 0
    for _, c := range moves {
        if c == 'U' {
            x -= 1
        } else if c == 'D' {
            x += 1
        } else if c == 'L' {
            y -= 1
        } else if c == 'R' {
            y += 1
        } else {
            z += 1
        }
    }
    return abs(x) + abs(y) + z
}

func abs(x int) int {
    if x < 0 {
        return -x
    }
    return x
}
```

#### TypeScript

```ts
function maxDistance(moves: string): number {
    let [x, y, z] = [0, 0, 0];
    for (const c of moves) {
        if (c === 'U') {
            x -= 1;
        } else if (c === 'D') {
            x += 1;
        } else if (c === 'L') {
            y -= 1;
        } else if (c === 'R') {
            y += 1;
        } else {
            z += 1;
        }
    }
    return Math.abs(x) + Math.abs(y) + z;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
