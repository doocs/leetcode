---
comment: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcp/LCP%2079.%20%E6%8F%90%E5%8F%96%E5%92%92%E6%96%87/README.md
---

# [LCP 79. 提取咒文](https://leetcode.cn/problems/kjpLFZ)

## 题目描述

<!-- 这里写题目描述 -->

随着兽群逐渐远去，一座大升降机缓缓的从地下升到了远征队面前。借由这台升降机，他们将能够到达地底的永恒至森。
在升降机的操作台上，是一个由魔法符号组成的矩阵，为了便于辨识，我们用小写字母来表示。 `matrix[i][j]` 表示矩阵第 `i` 行 `j` 列的字母。该矩阵上有一个提取装置，可以对所在位置的字母提取。
提取装置初始位于矩阵的左上角 `[0,0]`，可以通过每次操作移动到上、下、左、右相邻的 1 格位置中。提取装置每次移动或每次提取均记为一次操作。

远征队需要按照顺序，从矩阵中逐一取出字母以组成 `mantra`，才能够成功的启动升降机。请返回他们 **最少** 需要消耗的操作次数。如果无法完成提取，返回 `-1`。

**注意：**

-   提取装置可对同一位置的字母重复提取，每次提取一个
-   提取字母时，需按词语顺序依次提取

**示例 1：**

> 输入：`matrix = ["sd","ep"], mantra = "speed"`
>
> 输出：`10`
>
> 解释：如下图所示
> ![矩阵 (2).gif](<https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2079.%20%E6%8F%90%E5%8F%96%E5%92%92%E6%96%87/images/1646288670-OTlvAl-%E7%9F%A9%E9%98%B5%20(2).gif>)

**示例 2：**

> 输入：`matrix = ["abc","daf","geg"]， mantra = "sad"`
>
> 输出：`-1`
>
> 解释：矩阵中不存在 `s` ，无法提取词语

**提示：**

-   `0 < matrix.length, matrix[i].length <= 100`
-   `0 < mantra.length <= 100`
-   `matrix 和 mantra` 仅由小写字母组成

## 解法

### 方法一：BFS

时间复杂度 $O(m \times n \times l)$，空间复杂度 $O(m \times n \times l)$。

<!-- tabs:start -->

```python
class Solution:
    def extractMantra(self, matrix: List[str], mantra: str) -> int:
        m, n = len(matrix), len(matrix[0])
        q = deque([(0, 0, 0)])
        vis = {q[0]}
        dirs = (-1, 0, 1, 0, -1)
        ans = 0
        while q:
            for _ in range(len(q)):
                i, j, k = q.popleft()
                if k == len(mantra):
                    return ans
                if matrix[i][j] == mantra[k]:
                    t = (i, j, k + 1)
                    if t not in vis:
                        vis.add(t)
                        q.append(t)
                else:
                    for a, b in pairwise(dirs):
                        x, y = i + a, j + b
                        if 0 <= x < m and 0 <= y < n:
                            t = (x, y, k)
                            if t not in vis:
                                vis.add(t)
                                q.append(t)
            ans += 1
        return -1
```

```java
class Solution {
    public int extractMantra(String[] matrix, String mantra) {
        int m = matrix.length, n = matrix[0].length();
        int l = mantra.length();
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0, 0});
        boolean[][][] vis = new boolean[m][n][l + 1];
        vis[0][0][0] = true;
        int[] dirs = {-1, 0, 1, 0, -1};
        int ans = 0;
        for (; !q.isEmpty(); ++ans) {
            for (int size = q.size(); size > 0; --size) {
                var p = q.poll();
                int i = p[0], j = p[1], k = p[2];
                if (k == l) {
                    return ans;
                }
                if (matrix[i].charAt(j) == mantra.charAt(k) && !vis[i][j][k + 1]) {
                    vis[i][j][k + 1] = true;
                    q.offer(new int[] {i, j, k + 1});
                } else {
                    for (int c = 0; c < 4; ++c) {
                        int x = i + dirs[c], y = j + dirs[c + 1];
                        if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y][k]) {
                            vis[x][y][k] = true;
                            q.offer(new int[] {x, y, k});
                        }
                    }
                }
            }
        }
        return -1;
    }
}
```

```cpp
class Solution {
public:
    int extractMantra(vector<string>& matrix, string mantra) {
        int m = matrix.size(), n = matrix[0].size();
        int l = mantra.size();
        queue<tuple<int, int, int>> q;
        q.push({0, 0, 0});
        bool vis[m][n][l + 1];
        memset(vis, 0, sizeof(vis));
        int dirs[5] = {-1, 0, 1, 0, -1};
        int ans = 0;
        for (; q.size(); ++ans) {
            for (int size = q.size(); size; --size) {
                auto [i, j, k] = q.front();
                q.pop();
                if (k == l) {
                    return ans;
                }
                if (matrix[i][j] == mantra[k] && !vis[i][j][k + 1]) {
                    vis[i][j][k + 1] = true;
                    q.push({i, j, k + 1});
                } else {
                    for (int c = 0; c < 4; ++c) {
                        int x = i + dirs[c], y = j + dirs[c + 1];
                        if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y][k]) {
                            vis[x][y][k] = true;
                            q.push({x, y, k});
                        }
                    }
                }
            }
        }
        return -1;
    }
};
```

```go
func extractMantra(matrix []string, mantra string) (ans int) {
	m, n, l := len(matrix), len(matrix[0]), len(mantra)
	q := [][3]int{[3]int{0, 0, 0}}
	vis := make([][][]bool, m)
	for i := range vis {
		vis[i] = make([][]bool, n)
		for j := range vis[i] {
			vis[i][j] = make([]bool, l+1)
		}
	}
	vis[0][0][0] = true
	dirs := [5]int{-1, 0, 1, 0, -1}
	for ; len(q) > 0; ans++ {
		for size := len(q); size > 0; size-- {
			p := q[0]
			q = q[1:]
			i, j, k := p[0], p[1], p[2]
			if k == l {
				return ans
			}
			if matrix[i][j] == mantra[k] && !vis[i][j][k+1] {
				vis[i][j][k+1] = true
				q = append(q, [3]int{i, j, k + 1})
			} else {
				for c := 0; c < 4; c++ {
					x, y := i+dirs[c], j+dirs[c+1]
					if x >= 0 && x < m && y >= 0 && y < n && !vis[x][y][k] {
						vis[x][y][k] = true
						q = append(q, [3]int{x, y, k})
					}
				}
			}
		}
	}
	return -1
}
```

```ts
function extractMantra(matrix: string[], mantra: string): number {
    const [m, n, l] = [matrix.length, matrix[0].length, mantra.length];
    const q: number[][] = [[0, 0, 0]];
    const vis: boolean[][][] = Array(m)
        .fill(0)
        .map(() =>
            Array(n)
                .fill(0)
                .map(() => Array(l + 1).fill(false)),
        );
    const dirs: number[] = [-1, 0, 1, 0, -1];
    let ans = 0;
    for (; q.length > 0; ++ans) {
        for (let size = q.length; size > 0; --size) {
            const [i, j, k] = q.shift()!;
            if (k === l) {
                return ans;
            }
            if (matrix[i][j] === mantra[k] && !vis[i][j][k + 1]) {
                vis[i][j][k + 1] = true;
                q.push([i, j, k + 1]);
            } else {
                for (let c = 0; c < 4; ++c) {
                    const [x, y] = [i + dirs[c], j + dirs[c + 1]];
                    if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y][k]) {
                        vis[x][y][k] = true;
                        q.push([x, y, k]);
                    }
                }
            }
        }
    }
    return -1;
}
```

<!-- tabs:end -->

<!-- end -->
