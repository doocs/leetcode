# [1036. Escape a Large Maze](https://leetcode.com/problems/escape-a-large-maze)

[中文文档](/solution/1000-1099/1036.Escape%20a%20Large%20Maze/README.md)

## Description

<p>There is a 1 million by 1 million grid on an XY-plane, and the coordinates of each grid square are <code>(x, y)</code>.</p>

<p>We start at the <code>source = [s<sub>x</sub>, s<sub>y</sub>]</code> square and want to reach the <code>target = [t<sub>x</sub>, t<sub>y</sub>]</code> square. There is also an array of <code>blocked</code> squares, where each <code>blocked[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> represents a blocked square with coordinates <code>(x<sub>i</sub>, y<sub>i</sub>)</code>.</p>

<p>Each move, we can walk one square north, east, south, or west if the square is <strong>not</strong> in the array of <code>blocked</code> squares. We are also not allowed to walk outside of the grid.</p>

<p>Return <code>true</code><em> if and only if it is possible to reach the </em><code>target</code><em> square from the </em><code>source</code><em> square through a sequence of valid moves</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
<strong>Output:</strong> false
<strong>Explanation:</strong> The target square is inaccessible starting from the source square because we cannot move.
We cannot move north or east because those squares are blocked.
We cannot move south or west because we cannot go outside of the grid.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> blocked = [], source = [0,0], target = [999999,999999]
<strong>Output:</strong> true
<strong>Explanation:</strong> Because there are no blocked cells, it is possible to reach the target square.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= blocked.length &lt;= 200</code></li>
	<li><code>blocked[i].length == 2</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt; 10<sup>6</sup></code></li>
	<li><code>source.length == target.length == 2</code></li>
	<li><code>0 &lt;= s<sub>x</sub>, s<sub>y</sub>, t<sub>x</sub>, t<sub>y</sub> &lt; 10<sup>6</sup></code></li>
	<li><code>source != target</code></li>
	<li>It is guaranteed that <code>source</code> and <code>target</code> are not blocked.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isEscapePossible(
        self, blocked: List[List[int]], source: List[int], target: List[int]
    ) -> bool:
        def dfs(source, target, seen):
            x, y = source
            if (
                not (0 <= x < 10**6 and 0 <= y < 10**6)
                or (x, y) in blocked
                or (x, y) in seen
            ):
                return False
            seen.add((x, y))
            if len(seen) > 20000 or source == target:
                return True
            for a, b in [[0, -1], [0, 1], [1, 0], [-1, 0]]:
                next = [x + a, y + b]
                if dfs(next, target, seen):
                    return True
            return False

        blocked = set((x, y) for x, y in blocked)
        return dfs(source, target, set()) and dfs(target, source, set())
```

### **Java**

```java
class Solution {
    private int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static final int N = (int) 1e6;
    private Set<Integer> blocked;

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        this.blocked = new HashSet<>();
        for (int[] b : blocked) {
            this.blocked.add(b[0] * N + b[1]);
        }
        return dfs(source, target, new HashSet<>()) && dfs(target, source, new HashSet<>());
    }

    private boolean dfs(int[] source, int[] target, Set<Integer> seen) {
        int sx = source[0], sy = source[1];
        int tx = target[0], ty = target[1];
        if (sx < 0 || sx >= N || sy < 0 || sy >= N || tx < 0 || tx >= N || ty < 0 || ty >= N || blocked.contains(sx * N + sy) || seen.contains(sx * N + sy)) {
            return false;
        }
        seen.add(sx * N + sy);
        if (seen.size() > 20000 || (sx == target[0] && sy == target[1])) {
            return true;
        }
        for (int[] dir : dirs) {
            if (dfs(new int[]{sx + dir[0], sy + dir[1]}, target, seen)) {
                return true;
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
typedef unsigned long long ULL;

class Solution {
public:
    vector<vector<int>> dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    unordered_set<ULL> blocked;
    int N = 1e6;

    bool isEscapePossible(vector<vector<int>>& blocked, vector<int>& source, vector<int>& target) {
        this->blocked.clear();
        for (auto& b : blocked) this->blocked.insert((ULL)b[0] * N + b[1]);
        unordered_set<ULL> s1;
        unordered_set<ULL> s2;
        return dfs(source, target, s1) && dfs(target, source, s2);
    }

    bool dfs(vector<int>& source, vector<int>& target, unordered_set<ULL>& seen) {
        int sx = source[0], sy = source[1];
        int tx = target[0], ty = target[1];
        if (sx < 0 || sx >= N || sy < 0 || sy >= N || tx < 0 || tx >= N || ty < 0 || ty >= N || blocked.count((ULL)sx * N + sy) || seen.count((ULL)sx * N + sy)) return 0;
        seen.insert((ULL)sx * N + sy);
        if (seen.size() > 20000 || (sx == target[0] && sy == target[1])) return 1;
        for (auto& dir : dirs) {
            vector<int> next = {sx + dir[0], sy + dir[1]};
            if (dfs(next, target, seen)) return 1;
        }
        return 0;
    }
};
```

### **Go**

```go
func isEscapePossible(blocked [][]int, source []int, target []int) bool {
	const N = 1e6
	dirs := [4][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}
	block := make(map[int]bool)
	for _, b := range blocked {
		block[b[0]*N+b[1]] = true
	}
	var dfs func(source, target []int, seen map[int]bool) bool
	dfs = func(source, target []int, seen map[int]bool) bool {
		sx, sy := source[0], source[1]
		tx, ty := target[0], target[1]
		if sx < 0 || sx >= N || sy < 0 || sy >= N || tx < 0 || tx >= N || ty < 0 || ty >= N || block[sx*N+sy] || seen[sx*N+sy] {
			return false
		}
		seen[sx*N+sy] = true
		if len(seen) > 20000 || (sx == target[0] && sy == target[1]) {
			return true
		}
		for _, dir := range dirs {
			next := []int{sx + dir[0], sy + dir[1]}
			if dfs(next, target, seen) {
				return true
			}
		}
		return false
	}
	s1, s2 := make(map[int]bool), make(map[int]bool)
	return dfs(source, target, s1) && dfs(target, source, s2)
}
```

### **Rust**

```rust
use std::collections::{HashSet, VecDeque};

const BOUNDARY: i32 = 1_000_000;
const MAX: usize = 20000;

impl Solution {
    pub fn is_escape_possible(blocked: Vec<Vec<i32>>, source: Vec<i32>, target: Vec<i32>) -> bool {
        let mut block = HashSet::with_capacity(blocked.len());
        for b in blocked.iter() {
            block.insert((b[0], b[1]));
        }
        bfs(&block, &source, &target) && bfs(&block, &target, &source)
    }
}

fn bfs(block: &HashSet<(i32, i32)>, source: &Vec<i32>, target: &Vec<i32>) -> bool {
    let dir = vec![(-1, 0), (1, 0), (0, -1), (0, 1)];

    let mut queue = VecDeque::new();
    let mut vis = HashSet::new();
    queue.push_back((source[0], source[1]));
    vis.insert((source[0], source[1]));

    while !queue.is_empty() && vis.len() < MAX {
        let (x, y) = queue.pop_front().unwrap();
        if x == target[0] && y == target[1] {
            return true;
        }
        for (dx, dy) in dir.iter() {
            let (nx, ny) = (x + dx, y + dy);
            if nx < 0
                || nx >= BOUNDARY
                || ny < 0
                || ny >= BOUNDARY
                || vis.contains(&(nx, ny))
                || block.contains(&(nx, ny))
            {
                continue;
            }
            queue.push_back((nx, ny));
            vis.insert((nx, ny));
        }
    }

    vis.len() >= MAX
}
```

### **...**

```

```

<!-- tabs:end -->
