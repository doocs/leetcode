# [16.19. Pond Sizes](https://leetcode.cn/problems/pond-sizes-lcci)

[中文文档](/lcci/16.19.Pond%20Sizes/README.md)

## Description

<p>You have an integer matrix representing a plot of land, where the value at that loca&shy;tion represents the height above sea level. A value of zero indicates water. A pond is a region of water connected vertically, horizontally, or diagonally. The size of the pond is the total number of connected water cells. Write a method to compute the sizes of all ponds in the matrix.</p>

<p><strong>Example: </strong></p>

<pre>

<strong>Input: </strong>

[

  [0,2,1,0],

  [0,1,0,1],

  [1,1,0,1],

  [0,1,0,1]

]

<strong>Output: </strong> [1,2,4]

</pre>

<p><strong>Note: </strong></p>

<ul>
	<li><code>0 &lt; len(land) &lt;= 1000</code></li>
	<li><code>0 &lt; len(land[i]) &lt;= 1000</code></li>
</ul>

## Solutions

### Solution 1: DFS

We can traverse each point $(i, j)$ in the integer matrix $land$. If the value of the point is $0$, we start a depth-first search from this point until we reach a point with a non-zero value. The number of points searched during this process is the size of the pond, which is added to the answer array.

> Note: To avoid duplicate searches, we set the value of the searched points to $1$.

Finally, we sort the answer array to obtain the final answer.

The time complexity is $O(m \times n \times \log (m \times n))$, and the space complexity is $O(m \times n)$. Here, $m$ and $n$ are the number of rows and columns in the matrix $land$, respectively.

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
