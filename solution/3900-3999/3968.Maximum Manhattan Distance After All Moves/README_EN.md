---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3968.Maximum%20Manhattan%20Distance%20After%20All%20Moves/README_EN.md
---

<!-- problem:start -->

# [3968. Maximum Manhattan Distance After All Moves](https://leetcode.com/problems/maximum-manhattan-distance-after-all-moves)

[中文文档](/solution/3900-3999/3968.Maximum%20Manhattan%20Distance%20After%20All%20Moves/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>moves</code> consisting of the characters <code>&#39;U&#39;</code>, <code>&#39;D&#39;</code>, <code>&#39;L&#39;</code>, <code>&#39;R&#39;</code>, and <code>&#39;_&#39;</code>.</p>

<p>Starting from the origin <code>(0, 0)</code>, each character represents one move on a 2D plane:</p>

<ul>
	<li><code>&#39;U&#39;</code>: Move up by 1 unit.</li>
	<li><code>&#39;D&#39;</code>: Move down by 1 unit.</li>
	<li><code>&#39;L&#39;</code>: Move left by 1 unit.</li>
	<li><code>&#39;R&#39;</code>: Move right by 1 unit.</li>
	<li><code>&#39;_&#39;</code>: Can be independently replaced with any one of <code>&#39;U&#39;</code>, <code>&#39;D&#39;</code>, <code>&#39;L&#39;</code>, or <code>&#39;R&#39;</code>.</li>
</ul>

<p>Return the maximum <span data-keyword="manhattan-distance"><strong>Manhattan distance</strong></span> from the origin that can be achieved after all moves have been performed.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">moves = &quot;L_D_&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal choice is:</p>

<ul>
	<li><code>&#39;L&#39;</code>: <code>(0, 0) -&gt; (-1, 0)</code></li>
	<li><code>&#39;_&#39;</code> treated as <code>&#39;D&#39;</code>: <code>(-1, 0) -&gt; (-1, -1)</code></li>
	<li><code>&#39;D&#39;</code>: <code>(-1, -1) -&gt; (-1, -2)</code></li>
	<li><code>&#39;_&#39;</code> treated as <code>&#39;L&#39;</code>: <code>(-1, -2) -&gt; (-2, -2)</code></li>
</ul>

<p>The final Manhattan distance from the origin is <code>|0 - (-2)| + |0 - (-2)| = 4</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">moves = &quot;U_R&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal choice is:</p>

<ul>
	<li><code>&#39;U&#39;</code>: <code>(0, 0) -&gt; (0, 1)</code></li>
	<li><code>&#39;_&#39;</code> treated as <code>&#39;U&#39;</code>: <code>(0, 1) -&gt; (0, 2)</code></li>
	<li><code>&#39;R&#39;</code>: <code>(0, 2) -&gt; (1, 2)</code></li>
</ul>

<p>The final Manhattan distance from the origin is <code>|0 - 1| + |0 - 2| = 3</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= moves.length &lt;= 10<sup>5</sup></code></li>
	<li><code>moves</code> consists of only <code>&#39;U&#39;</code>, <code>&#39;D&#39;</code>, <code>&#39;L&#39;</code>, <code>&#39;R&#39;</code>, and <code>&#39;_&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

We can use a variable $x$ to record the vertical distance, a variable $y$ to record the horizontal distance, and a variable $z$ to record the number of replaceable moves.

Then the final Manhattan distance is $|x| + |y| + z$.

The time complexity is $O(n)$, where $n$ is the length of the string $\textit{moves}$. The space complexity is $O(1)$.

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
