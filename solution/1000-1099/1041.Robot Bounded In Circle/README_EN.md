---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1041.Robot%20Bounded%20In%20Circle/README_EN.md
rating: 1521
source: Weekly Contest 136 Q1
tags:
    - Math
    - String
    - Simulation
---

<!-- problem:start -->

# [1041. Robot Bounded In Circle](https://leetcode.com/problems/robot-bounded-in-circle)

[中文文档](/solution/1000-1099/1041.Robot%20Bounded%20In%20Circle/README.md)

## Description

<!-- description:start -->

<p>On an infinite plane, a robot initially stands at <code>(0, 0)</code> and faces north. Note that:</p>

<ul>
	<li>The <strong>north direction</strong> is the positive direction of the y-axis.</li>
	<li>The <strong>south direction</strong> is the negative direction of the y-axis.</li>
	<li>The <strong>east direction</strong> is the positive direction of the x-axis.</li>
	<li>The <strong>west direction</strong> is the negative direction of the x-axis.</li>
</ul>

<p>The robot can receive one of three instructions:</p>

<ul>
	<li><code>&quot;G&quot;</code>: go straight 1 unit.</li>
	<li><code>&quot;L&quot;</code>: turn 90 degrees to the left (i.e., anti-clockwise direction).</li>
	<li><code>&quot;R&quot;</code>: turn 90 degrees to the right (i.e., clockwise direction).</li>
</ul>

<p>The robot performs the <code>instructions</code> given in order, and repeats them forever.</p>

<p>Return <code>true</code> if and only if there exists a circle in the plane such that the robot never leaves the circle.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> instructions = &quot;GGLLGG&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> The robot is initially at (0, 0) facing the north direction.
&quot;G&quot;: move one step. Position: (0, 1). Direction: North.
&quot;G&quot;: move one step. Position: (0, 2). Direction: North.
&quot;L&quot;: turn 90 degrees anti-clockwise. Position: (0, 2). Direction: West.
&quot;L&quot;: turn 90 degrees anti-clockwise. Position: (0, 2). Direction: South.
&quot;G&quot;: move one step. Position: (0, 1). Direction: South.
&quot;G&quot;: move one step. Position: (0, 0). Direction: South.
Repeating the instructions, the robot goes into the cycle: (0, 0) --&gt; (0, 1) --&gt; (0, 2) --&gt; (0, 1) --&gt; (0, 0).
Based on that, we return true.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> instructions = &quot;GG&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> The robot is initially at (0, 0) facing the north direction.
&quot;G&quot;: move one step. Position: (0, 1). Direction: North.
&quot;G&quot;: move one step. Position: (0, 2). Direction: North.
Repeating the instructions, keeps advancing in the north direction and does not go into cycles.
Based on that, we return false.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> instructions = &quot;GL&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> The robot is initially at (0, 0) facing the north direction.
&quot;G&quot;: move one step. Position: (0, 1). Direction: North.
&quot;L&quot;: turn 90 degrees anti-clockwise. Position: (0, 1). Direction: West.
&quot;G&quot;: move one step. Position: (-1, 1). Direction: West.
&quot;L&quot;: turn 90 degrees anti-clockwise. Position: (-1, 1). Direction: South.
&quot;G&quot;: move one step. Position: (-1, 0). Direction: South.
&quot;L&quot;: turn 90 degrees anti-clockwise. Position: (-1, 0). Direction: East.
&quot;G&quot;: move one step. Position: (0, 0). Direction: East.
&quot;L&quot;: turn 90 degrees anti-clockwise. Position: (0, 0). Direction: North.
Repeating the instructions, the robot goes into the cycle: (0, 0) --&gt; (0, 1) --&gt; (-1, 1) --&gt; (-1, 0) --&gt; (0, 0).
Based on that, we return true.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= instructions.length &lt;= 100</code></li>
	<li><code>instructions[i]</code> is <code>&#39;G&#39;</code>, <code>&#39;L&#39;</code> or, <code>&#39;R&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can simulate the robot's movement. Use a variable $k$ to represent the robot's direction, initialized to $0$, which means the robot is facing north. The variable $k$ can take values in the range $[0, 3]$, representing the robot facing north, west, south, and east, respectively. Additionally, we use an array $dist$ of length $4$ to record the distance the robot travels in the four directions, initialized to $[0, 0, 0, 0]$.

Traverse the instruction string $\textit{instructions}$. If the current instruction is `'L'`, the robot turns west, i.e., $k = (k + 1) \bmod 4$; if the instruction is `'R'`, the robot turns east, i.e., $k = (k + 3) \bmod 4$; otherwise, the robot moves one step in the current direction, i.e., $dist[k]++$.

If the given instruction string $\textit{instructions}$ is executed once and the robot returns to the origin, i.e., $dist[0] = dist[2]$ and $dist[1] = dist[3]$, then the robot will definitely enter a loop. This is because no matter how many times the instructions are repeated, the robot always returns to the origin, so it must enter a loop.

If the given instruction string $\textit{instructions}$ is executed once and the robot does not return to the origin, suppose the robot is at $(x, y)$ and its direction is $k$.

- If $k=0$, i.e., the robot is facing north, then after executing the instructions a second time, the coordinate change is $(x, y)$; after executing the instructions a third time, the coordinate change is still $(x, y)$... Accumulating these changes, the robot will eventually reach $(n \times x, n \times y)$, where $n$ is a positive integer. Since the robot does not return to the origin, i.e., $x \neq 0$ or $y \neq 0$, it follows that $n \times x \neq 0$ or $n \times y \neq 0$, so the robot will not enter a loop;
- If $k=1$, i.e., the robot is facing west, then after executing the instructions a second time, the coordinate change is $(-y, x)$; after executing the instructions a third time, the coordinate change is $(-x, -y)$; after executing the instructions a fourth time, the coordinate change is $(y, -x)$. Accumulating these coordinate changes, we find that the robot will eventually return to the origin $(0, 0)$;
- If $k=2$, i.e., the robot is facing south, then after executing the instructions a second time, the coordinate change is $(-x, -y)$. Accumulating these two coordinate changes, we find that the robot will eventually return to the origin $(0, 0)$;
- If $k=3$, i.e., the robot is facing east, then after executing the instructions a second time, the coordinate change is $(y, -x)$; after executing the instructions a third time, the coordinate change is $(-x, -y)$; after executing the instructions a fourth time, the coordinate change is $(-y, x)$. Accumulating these coordinate changes, we find that the robot will eventually return to the origin $(0, 0)$.

In conclusion, if the given instruction string $\textit{instructions}$ is executed once and the robot returns to the origin, or if the robot's direction is different from the initial direction, then the robot will definitely enter a loop.

The time complexity is $O(n)$, and the space complexity is $O(1)$, where $n$ is the length of the instruction string $\textit{instructions}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isRobotBounded(self, instructions: str) -> bool:
        k = 0
        dist = [0] * 4
        for c in instructions:
            if c == 'L':
                k = (k + 1) % 4
            elif c == 'R':
                k = (k + 3) % 4
            else:
                dist[k] += 1
        return (dist[0] == dist[2] and dist[1] == dist[3]) or k != 0
```

#### Java

```java
class Solution {
    public boolean isRobotBounded(String instructions) {
        int k = 0;
        int[] dist = new int[4];
        for (int i = 0; i < instructions.length(); ++i) {
            char c = instructions.charAt(i);
            if (c == 'L') {
                k = (k + 1) % 4;
            } else if (c == 'R') {
                k = (k + 3) % 4;
            } else {
                ++dist[k];
            }
        }
        return (dist[0] == dist[2] && dist[1] == dist[3]) || (k != 0);
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isRobotBounded(string instructions) {
        int dist[4]{};
        int k = 0;
        for (char& c : instructions) {
            if (c == 'L') {
                k = (k + 1) % 4;
            } else if (c == 'R') {
                k = (k + 3) % 4;
            } else {
                ++dist[k];
            }
        }
        return (dist[0] == dist[2] && dist[1] == dist[3]) || k;
    }
};
```

#### Go

```go
func isRobotBounded(instructions string) bool {
	dist := [4]int{}
	k := 0
	for _, c := range instructions {
		if c == 'L' {
			k = (k + 1) % 4
		} else if c == 'R' {
			k = (k + 3) % 4
		} else {
			dist[k]++
		}
	}
	return (dist[0] == dist[2] && dist[1] == dist[3]) || k != 0
}
```

#### TypeScript

```ts
function isRobotBounded(instructions: string): boolean {
    const dist: number[] = new Array(4).fill(0);
    let k = 0;
    for (const c of instructions) {
        if (c === 'L') {
            k = (k + 1) % 4;
        } else if (c === 'R') {
            k = (k + 3) % 4;
        } else {
            ++dist[k];
        }
    }
    return (dist[0] === dist[2] && dist[1] === dist[3]) || k !== 0;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
