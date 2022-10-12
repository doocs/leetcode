# [874. Walking Robot Simulation](https://leetcode.com/problems/walking-robot-simulation)

[中文文档](/solution/0800-0899/0874.Walking%20Robot%20Simulation/README.md)

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

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def robotSim(self, commands: List[int], obstacles: List[List[int]]) -> int:
        dirs = [[-1, 0], [0, 1], [1, 0], [0, -1]]
        s = {(x, y) for x, y in obstacles}
        ans, p = 0, 1
        x = y = 0
        for v in commands:
            if v == -2:
                p = (p + 3) % 4
            elif v == -1:
                p = (p + 1) % 4
            else:
                for _ in range(v):
                    nx, ny = x + dirs[p][0], y + dirs[p][1]
                    if (nx, ny) in s:
                        break
                    x, y = nx, ny
                    ans = max(ans, x * x + y * y)
        return ans
```

### **Java**

```java
class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Set<String> s = new HashSet<>();
        for (int[] v : obstacles) {
            s.add(v[0] + "." + v[1]);
        }
        int ans = 0, p = 1;
        int x = 0, y = 0;
        for (int v : commands) {
            if (v == -2) {
                p = (p + 3) % 4;
            } else if (v == -1) {
                p = (p + 1) % 4;
            } else {
                while (v-- > 0) {
                    int nx = x + dirs[p][0], ny = y + dirs[p][1];
                    if (s.contains(nx + "." + ny)) {
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
}
```

### **C++**

```cpp
class Solution {
public:
    int robotSim(vector<int>& commands, vector<vector<int>>& obstacles) {
        vector<vector<int>> dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        unordered_set<string> s;
        for (auto v : obstacles) s.insert(to_string(v[0]) + "." + to_string(v[1]));
        int ans = 0, p = 1;
        int x = 0, y = 0;
        for (int v : commands) {
            if (v == -2)
                p = (p + 3) % 4;
            else if (v == -1)
                p = (p + 1) % 4;
            else {
                while (v--) {
                    int nx = x + dirs[p][0], ny = y + dirs[p][1];
                    if (s.count(to_string(nx) + "." + to_string(ny))) break;
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

### **Go**

```go
func robotSim(commands []int, obstacles [][]int) int {
	dirs := [][]int{{-1, 0}, {0, 1}, {1, 0}, {0, -1}}
	s := map[string]bool{}
	for _, v := range obstacles {
		t := strconv.Itoa(v[0]) + "." + strconv.Itoa(v[1])
		s[t] = true
	}
	ans, p := 0, 1
	x, y := 0, 0
	for _, v := range commands {
		if v == -2 {
			p = (p + 3) % 4
		} else if v == -1 {
			p = (p + 1) % 4
		} else {
			for i := 0; i < v; i++ {
				nx, ny := x+dirs[p][0], y+dirs[p][1]
				t := strconv.Itoa(nx) + "." + strconv.Itoa(ny)
				if s[t] {
					break
				}
				x, y = nx, ny
				ans = max(ans, x*x+y*y)
			}
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
