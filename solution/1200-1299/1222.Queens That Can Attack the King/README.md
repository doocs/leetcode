# [1222. 可以攻击国王的皇后](https://leetcode.cn/problems/queens-that-can-attack-the-king)

[English Version](/solution/1200-1299/1222.Queens%20That%20Can%20Attack%20the%20King/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一个&nbsp;<strong>8x8</strong>&nbsp;的棋盘上，放置着若干「黑皇后」和一个「白国王」。</p>

<p>给定一个由整数坐标组成的数组&nbsp;<code>queens</code>&nbsp;，表示黑皇后的位置；以及一对坐标&nbsp;<code>king</code> ，表示白国王的位置，返回所有可以攻击国王的皇后的坐标(任意顺序)。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1222.Queens%20That%20Can%20Attack%20the%20King/images/untitled-diagram.jpg" /></p>

<pre>
<strong>输入：</strong>queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
<strong>输出：</strong>[[0,1],[1,0],[3,3]]
<strong>解释：</strong> 
[0,1] 的皇后可以攻击到国王，因为他们在同一行上。 
[1,0] 的皇后可以攻击到国王，因为他们在同一列上。 
[3,3] 的皇后可以攻击到国王，因为他们在同一条对角线上。 
[0,4] 的皇后无法攻击到国王，因为她被位于 [0,1] 的皇后挡住了。 
[4,0] 的皇后无法攻击到国王，因为她被位于 [1,0] 的皇后挡住了。 
[2,4] 的皇后无法攻击到国王，因为她和国王不在同一行/列/对角线上。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1222.Queens%20That%20Can%20Attack%20the%20King/images/untitled-diagram-1.jpg" /></strong></p>

<pre>
<strong>输入：</strong>queens = [[0,0],[1,1],[2,2],[3,4],[3,5],[4,4],[4,5]], king = [3,3]
<strong>输出：</strong>[[2,2],[3,4],[4,4]]
</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1222.Queens%20That%20Can%20Attack%20the%20King/images/untitled-diagram-2.jpg" /></strong></p>

<pre>
<strong>输入：</strong>queens = [[5,6],[7,7],[2,1],[0,7],[1,6],[5,1],[3,7],[0,3],[4,0],[1,2],[6,3],[5,0],[0,4],[2,2],[1,1],[6,4],[5,4],[0,0],[2,6],[4,5],[5,2],[1,4],[7,5],[2,3],[0,5],[4,2],[1,0],[2,7],[0,1],[4,6],[6,1],[0,6],[4,3],[1,7]], king = [3,4]
<strong>输出：</strong>[[2,3],[1,4],[1,6],[3,7],[4,3],[5,4],[4,5]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= queens.length&nbsp;&lt;= 63</code></li>
	<li><code>queens[i].length == 2</code></li>
	<li><code>0 &lt;= queens[i][j] &lt;&nbsp;8</code></li>
	<li><code>king.length == 2</code></li>
	<li><code>0 &lt;= king[0], king[1] &lt; 8</code></li>
	<li>一个棋盘格上最多只能放置一枚棋子。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：直接搜索**

我们先将所有皇后的位置存入哈希表或者二维数组 $s$ 中。

接下来，我们从国王的位置开始，依次向上、下、左、右、左上、右上、左下、右下八个方向搜索，如果某个方向上存在皇后，那么就将其位置加入答案中，并且停止继续搜索该方向。

搜索结束后，返回答案即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。本题中 $n = 8$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def queensAttacktheKing(
        self, queens: List[List[int]], king: List[int]
    ) -> List[List[int]]:
        n = 8
        s = {(i, j) for i, j in queens}
        ans = []
        for a in range(-1, 2):
            for b in range(-1, 2):
                if a or b:
                    x, y = king
                    while 0 <= x + a < n and 0 <= y + b < n:
                        x, y = x + a, y + b
                        if (x, y) in s:
                            ans.append([x, y])
                            break
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        final int n = 8;
        var s = new boolean[n][n];
        for (var q : queens) {
            s[q[0]][q[1]] = true;
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int a = -1; a <= 1; ++a) {
            for (int b = -1; b <= 1; ++b) {
                if (a != 0 || b != 0) {
                    int x = king[0] + a, y = king[1] + b;
                    while (x >= 0 && x < n && y >= 0 && y < n) {
                        if (s[x][y]) {
                            ans.add(List.of(x, y));
                            break;
                        }
                        x += a;
                        y += b;
                    }
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
    vector<vector<int>> queensAttacktheKing(vector<vector<int>>& queens, vector<int>& king) {
        int n = 8;
        bool s[8][8]{};
        for (auto& q : queens) {
            s[q[0]][q[1]] = true;
        }
        vector<vector<int>> ans;
        for (int a = -1; a <= 1; ++a) {
            for (int b = -1; b <= 1; ++b) {
                if (a || b) {
                    int x = king[0] + a, y = king[1] + b;
                    while (x >= 0 && x < n && y >= 0 && y < n) {
                        if (s[x][y]) {
                            ans.push_back({x, y});
                            break;
                        }
                        x += a;
                        y += b;
                    }
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func queensAttacktheKing(queens [][]int, king []int) (ans [][]int) {
	n := 8
	s := [8][8]bool{}
	for _, q := range queens {
		s[q[0]][q[1]] = true
	}
	for a := -1; a <= 1; a++ {
		for b := -1; b <= 1; b++ {
			if a != 0 || b != 0 {
				x, y := king[0]+a, king[1]+b
				for 0 <= x && x < n && 0 <= y && y < n {
					if s[x][y] {
						ans = append(ans, []int{x, y})
						break
					}
					x += a
					y += b
				}
			}
		}
	}
	return
}
```

### **TypeScript**

```ts
function queensAttacktheKing(queens: number[][], king: number[]): number[][] {
    const n = 8;
    const s: boolean[][] = Array.from({ length: n }, () => Array.from({ length: n }, () => false));
    queens.forEach(([x, y]) => (s[x][y] = true));
    const ans: number[][] = [];
    for (let a = -1; a <= 1; ++a) {
        for (let b = -1; b <= 1; ++b) {
            if (a || b) {
                let [x, y] = [king[0] + a, king[1] + b];
                while (x >= 0 && x < n && y >= 0 && y < n) {
                    if (s[x][y]) {
                        ans.push([x, y]);
                        break;
                    }
                    x += a;
                    y += b;
                }
            }
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
