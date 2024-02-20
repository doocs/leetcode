# [874. Walking Robot Simulation](https://leetcode.com/problems/walking-robot-simulation)

[中文文档](/solution/0800-0899/0874.Walking%20Robot%20Simulation/README.md)

<!-- tags:Array,Hash Table,Simulation -->

## Description

<p>A robot on an infinite XY-plane starts at point <code>(0, 0)</code> facing north. The robot can receive a sequence of these three possible types of <code>commands</code>:</p>

<ul>
	<li><code>-2</code>: Turn left <code>90</code> degrees.</li>
	<li><code>-1</code>: Turn right <code>90</code> degrees.</li>
	<li><code>1 &lt;= k &lt;= 9</code>: Move forward <code>k</code> units, one unit at a time.</li>
</ul>

<p>Some of the grid squares are <code>obstacles</code>. The <code>i<sup>th</sup></code> obstacle is at grid point <code>obstacles[i] = (x<sub>i</sub>, y<sub>i</sub>)</code>. If the robot runs into an obstacle, then it will instead stay in its current location and move on to the next command.</p>

<p>Return <em>the <strong>maximum Euclidean distance</strong> that the robot ever gets from the origin <strong>squared</strong> (i.e. if the distance is </em><code>5</code><em>, return </em><code>25</code><em>)</em>.</p>

<p><strong>Note:</strong></p>

<ul>
	<li>North means +Y direction.</li>
	<li>East means +X direction.</li>
	<li>South means -Y direction.</li>
	<li>West means -X direction.</li>
	<li>There can be obstacle in&nbsp;[0,0].</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> commands = [4,-1,3], obstacles = []
<strong>Output:</strong> 25
<strong>Explanation:</strong> The robot starts at (0, 0):
1. Move north 4 units to (0, 4).
2. Turn right.
3. Move east 3 units to (3, 4).
The furthest point the robot ever gets from the origin is (3, 4), which squared is 3<sup>2</sup> + 4<sup>2</sup> = 25 units away.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> commands = [4,-1,4,-2,4], obstacles = [[2,4]]
<strong>Output:</strong> 65
<strong>Explanation:</strong> The robot starts at (0, 0):
1. Move north 4 units to (0, 4).
2. Turn right.
3. Move east 1 unit and get blocked by the obstacle at (2, 4), robot is at (1, 4).
4. Turn left.
5. Move north 4 units to (1, 8).
The furthest point the robot ever gets from the origin is (1, 8), which squared is 1<sup>2</sup> + 8<sup>2</sup> = 65 units away.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> commands = [6,-1,-1,6], obstacles = []
<strong>Output:</strong> 36
<strong>Explanation:</strong> The robot starts at (0, 0):
1. Move north 6 units to (0, 6).
2. Turn right.
3. Turn right.
4. Move south 6 units to (0, 0).
The furthest point the robot ever gets from the origin is (0, 6), which squared is 6<sup>2</sup> = 36 units away.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= commands.length &lt;= 10<sup>4</sup></code></li>
	<li><code>commands[i]</code> is either <code>-2</code>, <code>-1</code>, or an integer in the range <code>[1, 9]</code>.</li>
	<li><code>0 &lt;= obstacles.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-3 * 10<sup>4</sup> &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 3 * 10<sup>4</sup></code></li>
	<li>The answer is guaranteed to be less than <code>2<sup>31</sup></code>.</li>
</ul>

## Solutions

### Solution 1: Hash table + simulation

We define a direction array $dirs=[0, 1, 0, -1, 0]$ of length $5$, where adjacent elements in the array represent a direction. That is, $(dirs[0], dirs[1])$ represents north, and $(dirs[1], dirs[2])$ represents east, and so on.

We use a hash table $s$ to store the coordinates of all obstacles, so that we can determine in $O(1)$ time whether the next step will encounter an obstacle.

We use two variables $x$ and $y$ to represent the current coordinates of the robot, initially $x = y = 0$. The variable $k$ represents the current direction of the robot, and the answer variable $ans$ represents the maximum Euclidean distance squared of the robot from the origin.

Next, we traverse each element $c$ in the array $commands$:

-   If $c = -2$, it means that the robot turns left by $90$ degrees, that is, $k = (k + 3) \bmod 4$;
-   If $c = -1$, it means that the robot turns right by $90$ degrees, that is, $k = (k + 1) \bmod 4$;
-   Otherwise, it means that the robot moves forward by $c$ units of length. We combine the robot's current direction $k$ with the direction array $dirs$, and we can get the increment of the robot on the $x$ axis and the $y$ axis. We add the increment of $c$ units of length to $x$ and $y$ respectively, and judge whether the new coordinates $(x, y)$ after each move are in the coordinates of obstacles. If not, update the answer $ans$, otherwise stop simulating and perform the simulation of the next instruction.

Finally return the answer $ans$.

Time complexity is $O(C \times n + m)$, space complexity is $O(m)$. Where $C$ represents the maximum number of steps that can be moved each time, and $n$ and $m$ respectively represent the lengths of arrays $commands$ and arrays $obstacles$.

<!-- tabs:start -->

```python
class Solution:
    def robotSim(self, commands: List[int], obstacles: List[List[int]]) -> int:
        dirs = (0, 1, 0, -1, 0)
        s = {(x, y) for x, y in obstacles}
        ans = k = 0
        x = y = 0
        for c in commands:
            if c == -2:
                k = (k + 3) % 4
            elif c == -1:
                k = (k + 1) % 4
            else:
                for _ in range(c):
                    nx, ny = x + dirs[k], y + dirs[k + 1]
                    if (nx, ny) in s:
                        break
                    x, y = nx, ny
                    ans = max(ans, x * x + y * y)
        return ans
```

```java
class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        int[] dirs = {0, 1, 0, -1, 0};
        Set<Integer> s = new HashSet<>(obstacles.length);
        for (var e : obstacles) {
            s.add(f(e[0], e[1]));
        }
        int ans = 0, k = 0;
        int x = 0, y = 0;
        for (int c : commands) {
            if (c == -2) {
                k = (k + 3) % 4;
            } else if (c == -1) {
                k = (k + 1) % 4;
            } else {
                while (c-- > 0) {
                    int nx = x + dirs[k], ny = y + dirs[k + 1];
                    if (s.contains(f(nx, ny))) {
                        break;
                    }
                    x = nx;
                    y = ny;
                    ans = Math.max(ans, x * x + y * y);
                }
            }
        }
        return ans;
    }

    private int f(int x, int y) {
        return x * 60010 + y;
    }
}
```

```cpp
class Solution {
public:
    int robotSim(vector<int>& commands, vector<vector<int>>& obstacles) {
        int dirs[5] = {0, 1, 0, -1, 0};
        auto f = [](int x, int y) {
            return x * 60010 + y;
        };
        unordered_set<int> s;
        for (auto& e : obstacles) {
            s.insert(f(e[0], e[1]));
        }
        int ans = 0, k = 0;
        int x = 0, y = 0;
        for (int c : commands) {
            if (c == -2) {
                k = (k + 3) % 4;
            } else if (c == -1) {
                k = (k + 1) % 4;
            } else {
                while (c--) {
                    int nx = x + dirs[k], ny = y + dirs[k + 1];
                    if (s.count(f(nx, ny))) {
                        break;
                    }
                    x = nx;
                    y = ny;
                    ans = max(ans, x * x + y * y);
                }
            }
        }
        return ans;
    }
};
```

```go
func robotSim(commands []int, obstacles [][]int) (ans int) {
	dirs := [5]int{0, 1, 0, -1, 0}
	type pair struct{ x, y int }
	s := map[pair]bool{}
	for _, e := range obstacles {
		s[pair{e[0], e[1]}] = true
	}
	var x, y, k int
	for _, c := range commands {
		if c == -2 {
			k = (k + 3) % 4
		} else if c == -1 {
			k = (k + 1) % 4
		} else {
			for ; c > 0 && !s[pair{x + dirs[k], y + dirs[k+1]}]; c-- {
				x += dirs[k]
				y += dirs[k+1]
				ans = max(ans, x*x+y*y)
			}
		}
	}
	return
}
```

```ts
function robotSim(commands: number[], obstacles: number[][]): number {
    const dirs = [0, 1, 0, -1, 0];
    const s: Set<number> = new Set();
    const f = (x: number, y: number) => x * 60010 + y;
    for (const [x, y] of obstacles) {
        s.add(f(x, y));
    }
    let [ans, x, y, k] = [0, 0, 0, 0];
    for (let c of commands) {
        if (c === -2) {
            k = (k + 3) % 4;
        } else if (c === -1) {
            k = (k + 1) % 4;
        } else {
            while (c-- > 0) {
                const [nx, ny] = [x + dirs[k], y + dirs[k + 1]];
                if (s.has(f(nx, ny))) {
                    break;
                }
                [x, y] = [nx, ny];
                ans = Math.max(ans, x * x + y * y);
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
