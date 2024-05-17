---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcp/LCP%2056.%20%E4%BF%A1%E7%89%A9%E4%BC%A0%E9%80%81/README.md
---

<!-- problem:start -->

# [LCP 56. 信物传送](https://leetcode.cn/problems/6UEx57)

## 题目描述

<!-- description:start -->

欢迎各位勇者来到力扣城，本次试炼主题为「信物传送」。

本次试炼场地设有若干传送带，`matrix[i][j]` 表示第 `i` 行 `j` 列的传送带运作方向，`"^","v","<",">"` 这四种符号分别表示 **上、下、左、右** 四个方向。信物会随传送带的方向移动。勇者**每一次**施法操作，可**临时**变更一处传送带的方向，在物品经过后传送带恢复原方向。
![lcp (2).gif](<https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2056.%20%E4%BF%A1%E7%89%A9%E4%BC%A0%E9%80%81/images/1649835246-vfupSL-lcp%20(2).gif>){:width=300px}

通关信物初始位于坐标 `start`处，勇者需要将其移动到坐标 `end` 处，请返回勇者施法操作的最少次数。

**注意：**

-   `start` 和 `end` 的格式均为 `[i,j]`

**示例 1:**

> 输入：`matrix = [">>v","v^<","<><"], start = [0,1], end = [2,0]`
>
> 输出：`1`
>
> 解释：
> 如上图所示
> 当信物移动到 `[1,1]` 时，勇者施法一次将 `[1,1]` 的传送方向 `^` 从变更为 `<`
> 从而信物移动到 `[1,0]`，后续到达 `end` 位置
> 因此勇者最少需要施法操作 1 次

**示例 2:**

> 输入：`matrix = [">>v",">>v","^<<"], start = [0,0], end = [1,1]`
>
> 输出：`0`
>
> 解释：勇者无需施法，信物将自动传送至 `end` 位置

**示例 3:**

> 输入：`matrix = [">^^>","<^v>","^v^<"], start = [0,0], end = [1,3]`
>
> 输出：`3`

**提示：**

-   `matrix` 中仅包含 `'^'、'v'、'<'、'>'`
-   `0 < matrix.length <= 100`
-   `0 < matrix[i].length <= 100`
-   `0 <= start[0],end[0] < matrix.length`
-   `0 <= start[1],end[1] < matrix[i].length`

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双端队列 BFS(0-1 BFS)

每走到一个格子 $(i, j)$，有 $4$ 个方向可以走，如果方向与当前格子的方向相同，那么不需要施法，否则需要施法一次。

因此，我们可以使用 BFS 求出从起点到终点的最短路径。

我们定义一个双端队列 $q$，存储当前可以到达的格子，每次从队首取出一个格子 $(i, j)$，然后向四个方向扩展，如果扩展的格子 $(x, y)$ 不需要施法，那么将其加入队首，否则加入队尾。当我们第一次到达终点时，就得到了最短路径。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

```python
class Solution:
    def conveyorBelt(self, matrix: List[str], start: List[int], end: List[int]) -> int:
        dirs = (-1, 0, 1, 0, -1)
        d = {"^": 0, "v": 2, "<": 3, ">": 1}
        i, j = start
        q = deque([(i, j)])
        m, n = len(matrix), len(matrix[0])
        dist = [[inf] * n for _ in range(m)]
        dist[i][j] = 0
        while 1:
            i, j = q.popleft()
            if i == end[0] and j == end[1]:
                return int(dist[i][j])
            for k in range(4):
                x, y = i + dirs[k], j + dirs[k + 1]
                t = dist[i][j] + int(k != d[matrix[i][j]])
                if 0 <= x < m and 0 <= y < n and t < dist[x][y]:
                    dist[x][y] = t
                    if dist[x][y] == dist[i][j]:
                        q.appendleft((x, y))
                    else:
                        q.append((x, y))
```

```java
class Solution {
    public int conveyorBelt(String[] matrix, int[] start, int[] end) {
        int[] dirs = {-1, 0, 1, 0, -1};
        Map<Character, Integer> d = new HashMap<>(4);
        d.put('^', 0);
        d.put('>', 1);
        d.put('v', 2);
        d.put('<', 3);
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {start[0], start[1]});
        int m = matrix.length, n = matrix[0].length();
        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, 1 << 30);
        }
        dist[start[0]][start[1]] = 0;
        while (true) {
            int[] p = q.poll();
            int i = p[0], j = p[1];
            if (i == end[0] && j == end[1]) {
                return dist[i][j];
            }
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                int t = dist[i][j] + (k == d.get(matrix[i].charAt(j)) ? 0 : 1);
                if (x >= 0 && x < m && y >= 0 && y < n && t < dist[x][y]) {
                    dist[x][y] = t;
                    if (dist[x][y] == dist[i][j]) {
                        q.offerFirst(new int[] {x, y});
                    } else {
                        q.offerLast(new int[] {x, y});
                    }
                }
            }
        }
    }
}
```

```cpp
class Solution {
public:
    int conveyorBelt(vector<string>& matrix, vector<int>& start, vector<int>& end) {
        int dirs[5] = {-1, 0, 1, 0, -1};
        unordered_map<char, int> d;
        d['^'] = 0;
        d['>'] = 1;
        d['v'] = 2;
        d['<'] = 3;
        deque<pair<int, int>> q;
        q.emplace_back(start[0], start[1]);
        int m = matrix.size(), n = matrix[0].size();
        int dist[m][n];
        memset(dist, 0x3f, sizeof(dist));
        dist[start[0]][start[1]] = 0;
        while (1) {
            auto [i, j] = q.front();
            q.pop_front();
            if (i == end[0] && j == end[1]) {
                return dist[i][j];
            }
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                int t = dist[i][j] + (k == d[matrix[i][j]] ? 0 : 1);
                if (x >= 0 && x < m && y >= 0 && y < n && t < dist[x][y]) {
                    dist[x][y] = t;
                    if (dist[x][y] == dist[i][j]) {
                        q.emplace_front(x, y);
                    } else {
                        q.emplace_back(x, y);
                    }
                }
            }
        }
    }
};
```

```go
func conveyorBelt(matrix []string, start []int, end []int) int {
	dirs := [5]int{-1, 0, 1, 0, -1}
	d := map[byte]int{
		'^': 0,
		'>': 1,
		'v': 2,
		'<': 3,
	}
	q := [][2]int{[2]int{start[0], start[1]}}
	m, n := len(matrix), len(matrix[0])
	dist := make([][]int, m)
	for i := range dist {
		dist[i] = make([]int, n)
		for j := range dist[i] {
			dist[i][j] = 1 << 30
		}
	}
	dist[start[0]][start[1]] = 0
	for {
		p := q[0]
		i, j := p[0], p[1]
		if i == end[0] && j == end[1] {
			return dist[i][j]
		}
		q = q[1:]
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			t := dist[i][j]
			if k != d[matrix[i][j]] {
				t++
			}
			if x >= 0 && x < m && y >= 0 && y < n && t < dist[x][y] {
				dist[x][y] = t
				if dist[x][y] == dist[i][j] {
					q = append([][2]int{[2]int{x, y}}, q...)
				} else {
					q = append(q, [2]int{x, y})
				}
			}
		}
	}
}
```

```ts
function conveyorBelt(matrix: string[], start: number[], end: number[]): number {
    const dirs = [-1, 0, 1, 0, -1];
    const d: Map<string, number> = new Map();
    d.set('^', 0);
    d.set('>', 1);
    d.set('v', 2);
    d.set('<', 3);
    const q: number[][] = [start];
    const m = matrix.length;
    const n = matrix[0].length;
    const dist: number[][] = Array(m)
        .fill(0)
        .map(() => Array(n).fill(1 << 30));
    dist[start[0]][start[1]] = 0;
    while (true) {
        const [i, j] = q.shift()!;
        if (i === end[0] && j === end[1]) {
            return dist[i][j];
        }
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            const t = dist[i][j] + (d.get(matrix[i][j]) === k ? 0 : 1);
            if (x >= 0 && x < m && y >= 0 && y < n && t < dist[x][y]) {
                dist[x][y] = t;
                if (t == dist[i][j]) {
                    q.unshift([x, y]);
                } else {
                    q.push([x, y]);
                }
            }
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
