---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1197.Minimum%20Knight%20Moves/README_EN.md
rating: 1722
source: Biweekly Contest 9 Q2
tags:
    - Breadth-First Search
---

# [1197. Minimum Knight Moves 🔒](https://leetcode.com/problems/minimum-knight-moves)

[中文文档](/solution/1100-1199/1197.Minimum%20Knight%20Moves/README.md)

## Description

<p>In an <strong>infinite</strong> chess board with coordinates from <code>-infinity</code> to <code>+infinity</code>, you have a <strong>knight</strong> at square <code>[0, 0]</code>.</p>

<p>A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.</p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1197.Minimum%20Knight%20Moves/images/knight.png" style="height: 250px; width: 250px;" />
<p>Return <em>the minimum number of steps needed to move the knight to the square</em> <code>[x, y]</code>. It is guaranteed the answer exists.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> x = 2, y = 1
<strong>Output:</strong> 1
<strong>Explanation: </strong>[0, 0] &rarr; [2, 1]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> x = 5, y = 5
<strong>Output:</strong> 4
<strong>Explanation: </strong>[0, 0] &rarr; [2, 1] &rarr; [4, 2] &rarr; [3, 4] &rarr; [5, 5]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>-300 &lt;= x, y &lt;= 300</code></li>
	<li><code>0 &lt;= |x| + |y| &lt;= 300</code></li>
</ul>

## Solutions

### Solution 1: BFS

This problem can be solved using the BFS shortest path model. The search space for this problem is not large, so we can directly use the naive BFS. The solution below also provides the code for bidirectional BFS for reference.

Bidirectional BFS is a common optimization method for BFS. The main implementation ideas are as follows:

1. Create two queues, q1 and q2, for "start -> end" and "end -> start" search directions, respectively.
2. Create two hash maps, m1 and m2, to record the visited nodes and their corresponding expansion times (steps).
3. During each search, prioritize the queue with fewer elements for search expansion. If a node visited from the other direction is found during the expansion, it means the shortest path has been found.
4. If one of the queues is empty, it means that the search in the current direction cannot continue, indicating that the start and end points are not connected, and there is no need to continue the search.

<!-- tabs:start -->

```python
class Solution:
    def minKnightMoves(self, x: int, y: int) -> int:
        q = deque([(0, 0)])
        ans = 0
        vis = {(0, 0)}
        dirs = ((-2, 1), (-1, 2), (1, 2), (2, 1), (2, -1), (1, -2), (-1, -2), (-2, -1))
        while q:
            for _ in range(len(q)):
                i, j = q.popleft()
                if (i, j) == (x, y):
                    return ans
                for a, b in dirs:
                    c, d = i + a, j + b
                    if (c, d) not in vis:
                        vis.add((c, d))
                        q.append((c, d))
            ans += 1
        return -1
```

```java
class Solution {
    public int minKnightMoves(int x, int y) {
        x += 310;
        y += 310;
        int ans = 0;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {310, 310});
        boolean[][] vis = new boolean[700][700];
        vis[310][310] = true;
        int[][] dirs = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
        while (!q.isEmpty()) {
            for (int k = q.size(); k > 0; --k) {
                int[] p = q.poll();
                if (p[0] == x && p[1] == y) {
                    return ans;
                }
                for (int[] dir : dirs) {
                    int c = p[0] + dir[0];
                    int d = p[1] + dir[1];
                    if (!vis[c][d]) {
                        vis[c][d] = true;
                        q.offer(new int[] {c, d});
                    }
                }
            }
            ++ans;
        }
        return -1;
    }
}
```

```cpp
class Solution {
public:
    int minKnightMoves(int x, int y) {
        x += 310;
        y += 310;
        int ans = 0;
        queue<pair<int, int>> q;
        q.push({310, 310});
        vector<vector<bool>> vis(700, vector<bool>(700));
        vis[310][310] = true;
        vector<vector<int>> dirs = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
        while (!q.empty()) {
            for (int k = q.size(); k > 0; --k) {
                auto p = q.front();
                q.pop();
                if (p.first == x && p.second == y) return ans;
                for (auto& dir : dirs) {
                    int c = p.first + dir[0], d = p.second + dir[1];
                    if (!vis[c][d]) {
                        vis[c][d] = true;
                        q.push({c, d});
                    }
                }
            }
            ++ans;
        }
        return -1;
    }
};
```

```go
func minKnightMoves(x int, y int) int {
	x, y = x+310, y+310
	ans := 0
	q := [][]int{{310, 310}}
	vis := make([][]bool, 700)
	for i := range vis {
		vis[i] = make([]bool, 700)
	}
	dirs := [][]int{{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}}
	for len(q) > 0 {
		for k := len(q); k > 0; k-- {
			p := q[0]
			q = q[1:]
			if p[0] == x && p[1] == y {
				return ans
			}
			for _, dir := range dirs {
				c, d := p[0]+dir[0], p[1]+dir[1]
				if !vis[c][d] {
					vis[c][d] = true
					q = append(q, []int{c, d})
				}
			}
		}
		ans++
	}
	return -1
}
```

```rust
use std::collections::VecDeque;

const DIR: [(i32, i32); 8] = [
    (-2, 1),
    (2, 1),
    (-1, 2),
    (1, 2),
    (2, -1),
    (-2, -1),
    (1, -2),
    (-1, -2),
];

impl Solution {
    #[allow(dead_code)]
    pub fn min_knight_moves(x: i32, y: i32) -> i32 {
        // The original x, y are from [-300, 300]
        // Let's shift them to [0, 600]
        let x: i32 = x + 300;
        let y: i32 = y + 300;
        let mut ret = -1;
        let mut vis: Vec<Vec<bool>> = vec![vec![false; 618]; 618];
        // <X, Y, Current Steps>
        let mut q: VecDeque<(i32, i32, i32)> = VecDeque::new();

        q.push_back((300, 300, 0));

        while !q.is_empty() {
            let (i, j, s) = q.front().unwrap().clone();
            q.pop_front();
            if i == x && j == y {
                ret = s;
                break;
            }
            Self::enqueue(&mut vis, &mut q, i, j, s);
        }

        ret
    }

    #[allow(dead_code)]
    fn enqueue(
        vis: &mut Vec<Vec<bool>>,
        q: &mut VecDeque<(i32, i32, i32)>,
        i: i32,
        j: i32,
        cur_step: i32
    ) {
        let next_step = cur_step + 1;
        for (dx, dy) in DIR {
            let x = i + dx;
            let y = j + dy;
            if Self::check_bounds(x, y) || vis[x as usize][y as usize] {
                // This <X, Y> pair is either out of bound, or has been visited before
                // Just ignore this pair
                continue;
            }
            // Otherwise, add the pair to the queue
            // Also remember to update the vis vector
            vis[x as usize][y as usize] = true;
            q.push_back((x, y, next_step));
        }
    }

    #[allow(dead_code)]
    fn check_bounds(i: i32, j: i32) -> bool {
        i < 0 || i > 600 || j < 0 || j > 600
    }
}
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```python
class Solution:
    def minKnightMoves(self, x: int, y: int) -> int:
        def extend(m1, m2, q):
            for _ in range(len(q)):
                i, j = q.popleft()
                step = m1[(i, j)]
                for a, b in (
                    (-2, 1),
                    (-1, 2),
                    (1, 2),
                    (2, 1),
                    (2, -1),
                    (1, -2),
                    (-1, -2),
                    (-2, -1),
                ):
                    x, y = i + a, j + b
                    if (x, y) in m1:
                        continue
                    if (x, y) in m2:
                        return step + 1 + m2[(x, y)]
                    q.append((x, y))
                    m1[(x, y)] = step + 1
            return -1

        if (x, y) == (0, 0):
            return 0
        q1, q2 = deque([(0, 0)]), deque([(x, y)])
        m1, m2 = {(0, 0): 0}, {(x, y): 0}
        while q1 and q2:
            t = extend(m1, m2, q1) if len(q1) <= len(q2) else extend(m2, m1, q2)
            if t != -1:
                return t
        return -1
```

```java
class Solution {
    private int n = 700;

    public int minKnightMoves(int x, int y) {
        if (x == 0 && y == 0) {
            return 0;
        }
        x += 310;
        y += 310;
        Map<Integer, Integer> m1 = new HashMap<>();
        Map<Integer, Integer> m2 = new HashMap<>();
        m1.put(310 * n + 310, 0);
        m2.put(x * n + y, 0);
        Queue<int[]> q1 = new ArrayDeque<>();
        Queue<int[]> q2 = new ArrayDeque<>();
        q1.offer(new int[] {310, 310});
        q2.offer(new int[] {x, y});
        while (!q1.isEmpty() && !q2.isEmpty()) {
            int t = q1.size() <= q2.size() ? extend(m1, m2, q1) : extend(m2, m1, q2);
            if (t != -1) {
                return t;
            }
        }
        return -1;
    }

    private int extend(Map<Integer, Integer> m1, Map<Integer, Integer> m2, Queue<int[]> q) {
        int[][] dirs = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
        for (int k = q.size(); k > 0; --k) {
            int[] p = q.poll();
            int step = m1.get(p[0] * n + p[1]);
            for (int[] dir : dirs) {
                int x = p[0] + dir[0];
                int y = p[1] + dir[1];
                if (m1.containsKey(x * n + y)) {
                    continue;
                }
                if (m2.containsKey(x * n + y)) {
                    return step + 1 + m2.get(x * n + y);
                }
                m1.put(x * n + y, step + 1);
                q.offer(new int[] {x, y});
            }
        }
        return -1;
    }
}
```

```cpp
typedef pair<int, int> PII;

class Solution {
public:
    int n = 700;

    int minKnightMoves(int x, int y) {
        if (x == 0 && y == 0) return 0;
        x += 310;
        y += 310;
        unordered_map<int, int> m1;
        unordered_map<int, int> m2;
        m1[310 * n + 310] = 0;
        m2[x * n + y] = 0;
        queue<PII> q1;
        queue<PII> q2;
        q1.push({310, 310});
        q2.push({x, y});
        while (!q1.empty() && !q2.empty()) {
            int t = q1.size() <= q2.size() ? extend(m1, m2, q1) : extend(m2, m1, q2);
            if (t != -1) return t;
        }
        return -1;
    }

    int extend(unordered_map<int, int>& m1, unordered_map<int, int>& m2, queue<PII>& q) {
        vector<vector<int>> dirs = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
        for (int k = q.size(); k > 0; --k) {
            auto p = q.front();
            q.pop();
            int i = p.first, j = p.second;
            int step = m1[i * n + j];
            for (auto& dir : dirs) {
                int x = i + dir[0], y = j + dir[1];
                if (m1.count(x * n + y)) continue;
                if (m2.count(x * n + y)) return step + 1 + m2[x * n + y];
                m1[x * n + y] = step + 1;
                q.push({x, y});
            }
        }
        return -1;
    }
};
```

```go
func minKnightMoves(x int, y int) int {
	if x == 0 && y == 0 {
		return 0
	}
	n := 700
	x, y = x+310, y+310
	q1, q2 := []int{310*700 + 310}, []int{x*n + y}
	m1, m2 := map[int]int{310*700 + 310: 0}, map[int]int{x*n + y: 0}
	dirs := [][]int{{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}}
	extend := func() int {
		for k := len(q1); k > 0; k-- {
			p := q1[0]
			q1 = q1[1:]
			i, j := p/n, p%n
			step := m1[i*n+j]
			for _, dir := range dirs {
				x, y := i+dir[0], j+dir[1]
				t := x*n + y
				if _, ok := m1[t]; ok {
					continue
				}
				if v, ok := m2[t]; ok {
					return step + 1 + v
				}
				m1[t] = step + 1
				q1 = append(q1, t)
			}
		}
		return -1
	}
	for len(q1) > 0 && len(q2) > 0 {
		if len(q1) <= len(q2) {
			q1, q2 = q2, q1
			m1, m2 = m2, m1
		}
		t := extend()
		if t != -1 {
			return t
		}
	}
	return -1
}
```

```rust
use std::collections::VecDeque;
use std::collections::HashMap;

const DIR: [(i32, i32); 8] = [
    (-2, 1),
    (2, 1),
    (-1, 2),
    (1, 2),
    (2, -1),
    (-2, -1),
    (1, -2),
    (-1, -2),
];

impl Solution {
    #[allow(dead_code)]
    pub fn min_knight_moves(x: i32, y: i32) -> i32 {
        if x == 0 && y == 0 {
            return 0;
        }
        // Otherwise, let's shift <X, Y> from [-300, 300] -> [0, 600]
        let x = x + 300;
        let y = y + 300;
        let mut ret = -1;
        // Initialize the two hash map, used to track if a node has been visited
        let mut map_to: HashMap<i32, i32> = HashMap::new();
        let mut map_from: HashMap<i32, i32> = HashMap::new();
        // Input the original status
        map_to.insert(601 * 300 + 300, 0);
        map_from.insert(601 * x + y, 0);
        let mut q_to: VecDeque<(i32, i32)> = VecDeque::new();
        let mut q_from: VecDeque<(i32, i32)> = VecDeque::new();
        // Initialize the two queue
        q_to.push_back((300, 300));
        q_from.push_back((x, y));

        while !q_to.is_empty() && !q_from.is_empty() {
            let step = if q_to.len() < q_from.len() {
                Self::extend(&mut map_to, &mut map_from, &mut q_to)
            } else {
                Self::extend(&mut map_from, &mut map_to, &mut q_from)
            };
            if step != -1 {
                ret = step;
                break;
            }
        }

        ret
    }

    #[allow(dead_code)]
    fn extend(
        map_to: &mut HashMap<i32, i32>,
        map_from: &mut HashMap<i32, i32>,
        cur_q: &mut VecDeque<(i32, i32)>
    ) -> i32 {
        let n = cur_q.len();
        for _ in 0..n {
            let (i, j) = cur_q.front().unwrap().clone();
            cur_q.pop_front();
            // The cur_step here must exist
            let cur_step = map_to
                .get(&(601 * i + j))
                .unwrap()
                .clone();
            for (dx, dy) in DIR {
                let x = i + dx;
                let y = j + dy;
                // Check if this node has been visited
                if map_to.contains_key(&(601 * x + y)) {
                    // Just ignore this node
                    continue;
                }
                // Check if this node has been visited by the other side
                if map_from.contains_key(&(601 * x + y)) {
                    // We found the node
                    return (
                        cur_step +
                        1 +
                        map_from
                            .get(&(601 * x + y))
                            .unwrap()
                            .clone()
                    );
                }
                // Otherwise, update map_to and push the new node to queue
                map_to.insert(601 * x + y, cur_step + 1);
                cur_q.push_back((x, y));
            }
        }
        -1
    }
}
```

<!-- tabs:end -->

<!-- end -->
