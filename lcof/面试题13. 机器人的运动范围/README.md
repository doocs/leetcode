# [面试题 13. 机器人的运动范围](https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/)

## 题目描述

地上有一个 m 行 n 列的方格，从坐标 `[0,0]` 到坐标 `[m-1,n-1]` 。一个机器人从坐标 `[0, 0]` 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于 k 的格子。例如，当 k 为 18 时，机器人能够进入方格 `[35, 37]` ，因为 3+5+3+7=18。但它不能进入方格 `[35, 38]`，因为 3+5+3+8=19。请问该机器人能够到达多少个格子？

**示例 1：**

```
输入：m = 2, n = 3, k = 1
输出：3
```

**示例 2：**

```
输入：m = 3, n = 1, k = 0
输出：1
```

**提示：**

- `1 <= n,m <= 100`
- `0 <= k <= 20`

## 解法

深度优先搜索 DFS 实现。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    cnt = 0
    def movingCount(self, m: int, n: int, k: int) -> int:
        def cal(m, n):
            s = str(m) + str(n)
            return sum([int(i) for i in s])
        def dfs(i, j):
            if i < 0 or i >= m or j < 0 or j >= n or cal(i, j) > k or visited[i][j]:
                return
            self.cnt += 1
            visited[i][j] = True
            dfs(i + 1, j)
            dfs(i - 1, j)
            dfs(i, j + 1)
            dfs(i, j - 1)
        self.cnt = 0
        visited = [[False for _ in range(n)] for _ in range(m)]
        dfs(0, 0)
        return self.cnt
```

### **Java**

```java
class Solution {
    private int m;
    private int n;
    private boolean[][] visited;
    private int cnt;
    public int movingCount(int m, int n, int k) {
        visited = new boolean[m][n];
        this.m = m;
        this.n = n;
        cnt = 0;
        dfs(0, 0, k);
        return cnt;
    }

    private void dfs(int i, int j, int k) {
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || cal(i, j) > k) return;
        ++cnt;
        visited[i][j] = true;
        dfs(i + 1, j, k);
        dfs(i - 1, j, k);
        dfs(i, j + 1, k);
        dfs(i, j - 1, k);
    }

    private int cal(int i, int j) {
        int res = 0;
        while (i != 0) {
            res += (i % 10);
            i /= 10;
        }
        while (j != 0) {
            res += (j % 10);
            j /= 10;
        }
        return res;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number} m
 * @param {number} n
 * @param {number} k
 * @return {number}
 */
var movingCount = function (m, n, k) {
    let res = 0;
    let isRead = [...new Array(m)].map(() => Array(n).fill(0));
    let moving = [
        [0, -1],
        [0, 1],
        [1, 0],
        [-1, 0],
    ];
    let queue = [[0, 0]];
    isRead[0][0] = 1;
    while (queue.length) {
        let [x, y] = queue.shift();
        for (let [dx, dy] of moving) {
            let X = x + dx;
            let Y = y + dy;
            if (
                X >= 0 &&
                Y >= 0 &&
                X < m &&
                Y < n &&
                !isRead[X][Y] &&
                isValid(X, Y)
            ) {
                queue.push([X, Y]);
                isRead[X][Y] = 1;
            }
        }
        res++;
    }
    function isValid(x, y) {
        let r = 0;
        r +=
            x
                .toString()
                .split("")
                .reduce((acc, cur) => acc + +cur, 0) +
            y
                .toString()
                .split("")
                .reduce((acc, cur) => acc + +cur, 0);
        if (r <= k) return true;
        else return false;
    }
    return res;
};
```

### **Go**

```go
func movingCount(m int, n int, k int) int {
	var visited [][]bool
	visited = make([][]bool, m)
	for i := 0; i < m; i++ {
		visited[i] = make([]bool, n)
	}
	return dfs(0, 0, m, n, k, visited)
}

func dfs(x, y, m, n, k int, visited [][]bool) int {
	if x >= m || y >= n || visited[x][y] || (x%10+x/10+y%10+y/10) > k {
		return 0
	}
	visited[x][y] = true
	return 1 + dfs(x+1, y, m, n, k, visited) + dfs(x, y+1, m, n, k, visited)
}
```

### **C++**

```cpp
class Solution {
public:
    int checksum(int m, int n, int target) {
        int a = 0;
        while (m > 0) {
            a += m % 10;
            m /= 10;
        }

        int b = 0;
        while (n > 0) {
            b += n % 10;
            n /= 10;
        }

        return a + b <= target;
    }

    int moving(int row, int col, vector<vector<int>>& arr, int i, int j, int target) {
        int count = 0;
        if (checksum(i, j, target)
            && i>=0 && i < row && j>=0 && j < col
            && arr[i][j] == 0) {
            arr[i][j] = 1;
            count = 1 + moving(row, col, arr, i-1, j, target)
                    + moving(row, col, arr, i, j-1, target)
                    + moving(row, col, arr, i+1, j, target)
                    + moving(row, col, arr, i, j+1, target);
        }

        return count;
    }

    int movingCount(int m, int n, int k) {
        if (m == 0 || n == 0) {
            return 0;
        }

        vector<vector<int>> arr(m, vector<int>(n, 0));
        int cnt = moving(m, n, arr, 0, 0, k);
        return cnt;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
