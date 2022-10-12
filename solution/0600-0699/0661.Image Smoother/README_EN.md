# [661. Image Smoother](https://leetcode.com/problems/image-smoother)

[中文文档](/solution/0600-0699/0661.Image%20Smoother/README.md)

## Description

<p>An <strong>image smoother</strong> is a filter of the size <code>3 x 3</code> that can be applied to each cell of an image by rounding down the average of the cell and the eight surrounding cells (i.e., the average of the nine cells in the blue smoother). If one or more of the surrounding cells of a cell is not present, we do not consider it in the average (i.e., the average of the four cells in the red smoother).</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0661.Image%20Smoother/images/smoother-grid.jpg" style="width: 493px; height: 493px;" />
<p>Given an <code>m x n</code> integer matrix <code>img</code> representing the grayscale of an image, return <em>the image after applying the smoother on each cell of it</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0661.Image%20Smoother/images/smooth-grid.jpg" style="width: 613px; height: 253px;" />
<pre>
<strong>Input:</strong> img = [[1,1,1],[1,0,1],[1,1,1]]
<strong>Output:</strong> [[0,0,0],[0,0,0],[0,0,0]]
<strong>Explanation:</strong>
For the points (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
For the points (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
For the point (1,1): floor(8/9) = floor(0.88888889) = 0
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0661.Image%20Smoother/images/smooth2-grid.jpg" style="width: 613px; height: 253px;" />
<pre>
<strong>Input:</strong> img = [[100,200,100],[200,50,200],[100,200,100]]
<strong>Output:</strong> [[137,141,137],[141,138,141],[137,141,137]]
<strong>Explanation:</strong>
For the points (0,0), (0,2), (2,0), (2,2): floor((100+200+200+50)/4) = floor(137.5) = 137
For the points (0,1), (1,0), (1,2), (2,1): floor((200+200+50+200+100+100)/6) = floor(141.666667) = 141
For the point (1,1): floor((50+200+200+200+200+100+100+100+100)/9) = floor(138.888889) = 138
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == img.length</code></li>
	<li><code>n == img[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>0 &lt;= img[i][j] &lt;= 255</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def imageSmoother(self, img: List[List[int]]) -> List[List[int]]:
        m, n = len(img), len(img[0])
        ans = [[0] * n for _ in range(m)]
        for i in range(m):
            for j in range(n):
                s = cnt = 0
                for x in range(i - 1, i + 2):
                    for y in range(j - 1, j + 2):
                        if 0 <= x < m and 0 <= y < n:
                            cnt += 1
                            s += img[x][y]
                ans[i][j] = s // cnt
        return ans
```

### **Java**

```java
class Solution {
    public int[][] imageSmoother(int[][] img) {
        int m = img.length;
        int n = img[0].length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int s = 0;
                int cnt = 0;
                for (int x = i - 1; x <= i + 1; ++x) {
                    for (int y = j - 1; y <= j + 1; ++y) {
                        if (x >= 0 && x < m && y >= 0 && y < n) {
                            ++cnt;
                            s += img[x][y];
                        }
                    }
                }
                ans[i][j] = s / cnt;
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function imageSmoother(img: number[][]): number[][] {
    const m = img.length;
    const n = img[0].length;
    const locations = [
        [-1, -1],
        [-1, 0],
        [-1, 1],
        [0, -1],
        [0, 0],
        [0, 1],
        [1, -1],
        [1, 0],
        [1, 1],
    ];

    const res = [];
    for (let i = 0; i < m; i++) {
        res.push([]);
        for (let j = 0; j < n; j++) {
            let sum = 0;
            let count = 0;
            for (const [y, x] of locations) {
                if ((img[i + y] || [])[j + x] != null) {
                    sum += img[i + y][j + x];
                    count++;
                }
            }
            res[i].push(Math.floor(sum / count));
        }
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn image_smoother(img: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let m = img.len();
        let n = img[0].len();
        let locations = [
            [-1, -1],
            [-1, 0],
            [-1, 1],
            [0, -1],
            [0, 0],
            [0, 1],
            [1, -1],
            [1, 0],
            [1, 1],
        ];

        let mut res = vec![];
        for i in 0..m {
            res.push(vec![]);
            for j in 0..n {
                let mut sum = 0;
                let mut count = 0;
                for [y, x] in locations.iter() {
                    let i = i as i32 + y;
                    let j = j as i32 + x;
                    if i < 0 || i == m as i32 || j < 0 || j == n as i32 {
                        continue;
                    }
                    count += 1;
                    sum += img[i as usize][j as usize];
                }
                res[i].push(sum / count);
            }
        }
        res
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> imageSmoother(vector<vector<int>>& img) {
        int m = img.size(), n = img[0].size();
        vector<vector<int>> ans(m, vector<int>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int s = 0, cnt = 0;
                for (int x = i - 1; x <= i + 1; ++x) {
                    for (int y = j - 1; y <= j + 1; ++y) {
                        if (x < 0 || x >= m || y < 0 || y >= n) continue;
                        ++cnt;
                        s += img[x][y];
                    }
                }
                ans[i][j] = s / cnt;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func imageSmoother(img [][]int) [][]int {
	m, n := len(img), len(img[0])
	ans := make([][]int, m)
	for i, row := range img {
		ans[i] = make([]int, n)
		for j := range row {
			s, cnt := 0, 0
			for x := i - 1; x <= i+1; x++ {
				for y := j - 1; y <= j+1; y++ {
					if x >= 0 && x < m && y >= 0 && y < n {
						cnt++
						s += img[x][y]
					}
				}
			}
			ans[i][j] = s / cnt
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
