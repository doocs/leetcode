---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/16.19.Pond%20Sizes/README.md
---

# [面试题 16.19. 水域大小](https://leetcode.cn/problems/pond-sizes-lcci)

[English Version](/lcci/16.19.Pond%20Sizes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>你有一个用于表示一片土地的整数矩阵<code>land</code>，该矩阵中每个点的值代表对应地点的海拔高度。若值为0则表示水域。由垂直、水平或对角连接的水域为池塘。池塘的大小是指相连接的水域的个数。编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。</p>
<p><strong>示例：</strong></p>
<pre><strong>输入：</strong>
[
  [0,2,1,0],
  [0,1,0,1],
  [1,1,0,1],
  [0,1,0,1]
]
<strong>输出：</strong> [1,2,4]
</pre>
<p><strong>提示：</strong></p>
<ul>
<li><code>0 < len(land) <= 1000</code></li>
<li><code>0 < len(land[i]) <= 1000</code></li>
</ul>

## 解法

### 方法一：DFS

我们可以遍历整数矩阵 $land$ 中的每个点 $(i, j)$，如果该点的值为 $0$，则从该点开始进行深度优先搜索，直到搜索到的点的值不为 $0$，则停止搜索，此时搜索到的点的个数即为池塘的大小，将其加入答案数组中。

> 注意：在进行深度优先搜索时，为了避免重复搜索，我们将搜索到的点的值置为 $1$。

最后，我们对答案数组进行排序，即可得到最终答案。

时间复杂度 $O(m \times n \times \log (m \times n))，空间复杂度 O(m \times n)$。其中 $m$ 和 $n$ 分别为矩阵 $land$ 的行数和列数。

<!-- tabs:start -->

```python
class Solution:
    def pondSizes(self, land: List[List[int]]) -> List[int]:
        def dfs(i: int, j: int) -> int:
            res = 1
            land[i][j] = 1
            for x in range(i - 1, i + 2):
                for y in range(j - 1, j + 2):
                    if 0 <= x < m and 0 <= y < n and land[x][y] == 0:
                        res += dfs(x, y)
            return res

        m, n = len(land), len(land[0])
        return sorted(dfs(i, j) for i in range(m) for j in range(n) if land[i][j] == 0)
```

```java
class Solution {
    private int m;
    private int n;
    private int[][] land;

    public int[] pondSizes(int[][] land) {
        m = land.length;
        n = land[0].length;
        this.land = land;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (land[i][j] == 0) {
                    ans.add(dfs(i, j));
                }
            }
        }
        return ans.stream().sorted().mapToInt(Integer::valueOf).toArray();
    }

    private int dfs(int i, int j) {
        int res = 1;
        land[i][j] = 1;
        for (int x = i - 1; x <= i + 1; ++x) {
            for (int y = j - 1; y <= j + 1; ++y) {
                if (x >= 0 && x < m && y >= 0 && y < n && land[x][y] == 0) {
                    res += dfs(x, y);
                }
            }
        }
        return res;
    }
}
```

```cpp
class Solution {
public:
    vector<int> pondSizes(vector<vector<int>>& land) {
        int m = land.size(), n = land[0].size();
        function<int(int, int)> dfs = [&](int i, int j) -> int {
            int res = 1;
            land[i][j] = 1;
            for (int x = i - 1; x <= i + 1; ++x) {
                for (int y = j - 1; y <= j + 1; ++y) {
                    if (x >= 0 && x < m && y >= 0 && y < n && land[x][y] == 0) {
                        res += dfs(x, y);
                    }
                }
            }
            return res;
        };
        vector<int> ans;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (land[i][j] == 0) {
                    ans.push_back(dfs(i, j));
                }
            }
        }
        sort(ans.begin(), ans.end());
        return ans;
    }
};
```

```go
func pondSizes(land [][]int) (ans []int) {
	m, n := len(land), len(land[0])
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		res := 1
		land[i][j] = 1
		for x := i - 1; x <= i+1; x++ {
			for y := j - 1; y <= j+1; y++ {
				if x >= 0 && x < m && y >= 0 && y < n && land[x][y] == 0 {
					res += dfs(x, y)
				}
			}
		}
		return res
	}
	for i := range land {
		for j := range land[i] {
			if land[i][j] == 0 {
				ans = append(ans, dfs(i, j))
			}
		}
	}
	sort.Ints(ans)
	return
}
```

```ts
function pondSizes(land: number[][]): number[] {
    const m = land.length;
    const n = land[0].length;
    const dfs = (i: number, j: number): number => {
        let res = 1;
        land[i][j] = 1;
        for (let x = i - 1; x <= i + 1; ++x) {
            for (let y = j - 1; y <= j + 1; ++y) {
                if (x >= 0 && x < m && y >= 0 && y < n && land[x][y] === 0) {
                    res += dfs(x, y);
                }
            }
        }
        return res;
    };
    const ans: number[] = [];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (land[i][j] === 0) {
                ans.push(dfs(i, j));
            }
        }
    }
    ans.sort((a, b) => a - b);
    return ans;
}
```

```swift
class Solution {
    private var m: Int = 0
    private var n: Int = 0
    private var land: [[Int]] = []

    func pondSizes(_ land: [[Int]]) -> [Int] {
        self.land = land
        m = land.count
        n = land[0].count
        var ans: [Int] = []

        for i in 0..<m {
            for j in 0..<n {
                if self.land[i][j] == 0 {
                    ans.append(dfs(i, j))
                }
            }
        }
        return ans.sorted()
    }

    private func dfs(_ i: Int, _ j: Int) -> Int {
        var res = 1
        self.land[i][j] = 1
        for x in max(i - 1, 0)...min(i + 1, m - 1) {
            for y in max(j - 1, 0)...min(j + 1, n - 1) {
                if self.land[x][y] == 0 {
                    res += dfs(x, y)
                }
            }
        }
        return res
    }
}
```

<!-- tabs:end -->

<!-- end -->
