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

<!-- tabs:start -->

### **Python3**

```python
class Solution:

    def __init__(self):
        self._cnt = 0

    def movingCount(self, m: int, n: int, k: int) -> int:
        self._cnt = 0
        visited = [[False for j in range(n)] for i in range(m)]
        self.visit(0, 0, m, n, k, visited)
        return self._cnt

    def visit(self, i, j, m, n, k, visited):
        if i < 0 or i >= m or j < 0 or j >= n or visited[i][j] or self.cal(i, j) > k:
            return
        visited[i][j] = True
        self._cnt += 1
        self.visit(i - 1, j, m, n, k, visited)
        self.visit(i + 1, j, m, n, k, visited)
        self.visit(i, j - 1, m, n, k, visited)
        self.visit(i, j + 1, m, n, k, visited)

    def cal(self, m, n) -> int:
        s = str(m) + str(n)
        return sum([int(i) for i in s])
```

### **Java**

```java
class Solution {
    private int cnt;

    public int movingCount(int m, int n, int k) {
        cnt = 0;
        boolean[][] visited = new boolean[m][n];
        visit(0, 0, m, n, k, visited);
        return cnt;
    }

    private void visit(int i, int j, int m, int n, int k, boolean[][] visited) {
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || cal(i, j) > k) {
            return;
        }
        visited[i][j] = true;
        ++cnt;
        visit(i - 1, j, m, n, k, visited);
        visit(i + 1, j, m, n, k, visited);
        visit(i, j - 1, m, n, k, visited);
        visit(i, j + 1, m, n, k, visited);
    }


    private int cal(int i, int j) {
        return cal(i) + cal(j);
    }

    private int cal(int val) {
        int s = 0;
        while (val != 0) {
            s += (val % 10);
            val /= 10;
        }
        return s;
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

### **...**

```

```

<!-- tabs:end -->
