---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0657.Robot%20Return%20to%20Origin/README_EN.md
tags:
    - String
    - Simulation
---

<!-- problem:start -->

# [657. Robot Return to Origin](https://leetcode.com/problems/robot-return-to-origin)

[中文文档](/solution/0600-0699/0657.Robot%20Return%20to%20Origin/README.md)

## Description

<!-- description:start -->

<p>There is a robot starting at the position <code>(0, 0)</code>, the origin, on a 2D plane. Given a sequence of its moves, judge if this robot <strong>ends up at </strong><code>(0, 0)</code> after it completes its moves.</p>

<p>You are given a string <code>moves</code> that represents the move sequence of the robot where <code>moves[i]</code> represents its <code>i<sup>th</sup></code> move. Valid moves are <code>&#39;R&#39;</code> (right), <code>&#39;L&#39;</code> (left), <code>&#39;U&#39;</code> (up), and <code>&#39;D&#39;</code> (down).</p>

<p>Return <code>true</code><em> if the robot returns to the origin after it finishes all of its moves, or </em><code>false</code><em> otherwise</em>.</p>

<p><strong>Note</strong>: The way that the robot is &quot;facing&quot; is irrelevant. <code>&#39;R&#39;</code> will always make the robot move to the right once, <code>&#39;L&#39;</code> will always make it move left, etc. Also, assume that the magnitude of the robot&#39;s movement is the same for each move.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> moves = &quot;UD&quot;
<strong>Output:</strong> true
<strong>Explanation</strong>: The robot moves up once, and then down once. All moves have the same magnitude, so it ended up at the origin where it started. Therefore, we return true.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> moves = &quot;LL&quot;
<strong>Output:</strong> false
<strong>Explanation</strong>: The robot moves left twice. It ends up two &quot;moves&quot; to the left of the origin. We return false because it is not at the origin at the end of its moves.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= moves.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>moves</code> only contains the characters <code>&#39;U&#39;</code>, <code>&#39;D&#39;</code>, <code>&#39;L&#39;</code> and <code>&#39;R&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Maintain Coordinates

We can maintain a coordinate $(x, y)$ to represent the robot's movement in the horizontal and vertical directions.

Traverse the string $\textit{moves}$ and update the coordinate $(x, y)$ based on the current character:

-   If the current character is `'U'`, then $y$ increases by $1$;
-   If the current character is `'D'$, then $y$ decreases by $1$;
-   If the current character is `'L'$, then $x$ decreases by $1$;
-   If the current character is `'R'$, then $x$ increases by $1$.

Finally, check if both $x$ and $y$ are $0$.

The time complexity is $O(n)$, where $n$ is the length of the string $\textit{moves}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def judgeCircle(self, moves: str) -> bool:
        x = y = 0
        for c in moves:
            match c:
                case "U":
                    y += 1
                case "D":
                    y -= 1
                case "L":
                    x -= 1
                case "R":
                    x += 1
        return x == 0 and y == 0
```

#### Java

```java
class Solution {
    public boolean judgeCircle(String moves) {
        int x = 0, y = 0;
        for (char c : moves.toCharArray()) {
            switch (c) {
                case 'U' -> y++;
                case 'D' -> y--;
                case 'L' -> x--;
                case 'R' -> x++;
            }
        }
        return x == 0 && y == 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool judgeCircle(string moves) {
        int x = 0, y = 0;
        for (char c : moves) {
            switch (c) {
            case 'U': y++; break;
            case 'D': y--; break;
            case 'L': x--; break;
            case 'R': x++; break;
            }
        }
        return x == 0 && y == 0;
    }
};
```

#### Go

```go
func judgeCircle(moves string) bool {
	x, y := 0, 0
	for _, c := range moves {
		switch c {
		case 'U':
			y++
		case 'D':
			y--
		case 'L':
			x--
		case 'R':
			x++
		}
	}
	return x == 0 && y == 0
}
```

#### TypeScript

```ts
function judgeCircle(moves: string): boolean {
    let [x, y] = [0, 0];
    for (const c of moves) {
        if (c === 'U') {
            y++;
        } else if (c === 'D') {
            y--;
        } else if (c === 'L') {
            x--;
        } else {
            x++;
        }
    }
    return x === 0 && y === 0;
}
```

#### JavaScript

```js
/**
 * @param {string} moves
 * @return {boolean}
 */
var judgeCircle = function (moves) {
    let [x, y] = [0, 0];
    for (const c of moves) {
        if (c === 'U') {
            y++;
        } else if (c === 'D') {
            y--;
        } else if (c === 'L') {
            x--;
        } else {
            x++;
        }
    }
    return x === 0 && y === 0;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
