# [3030. Find the Grid of Region Average](https://leetcode.com/problems/find-the-grid-of-region-average)

[中文文档](/solution/3000-3099/3030.Find%20the%20Grid%20of%20Region%20Average/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> <code>m x n</code> grid <code>image</code> which represents a grayscale image, where <code>image[i][j]</code> represents a pixel with intensity in the range<code>[0..255]</code>. You are also given a <strong>non-negative</strong> integer <code>threshold</code>.</p>

<p>Two pixels <code>image[a][b]</code> and <code>image[c][d]</code> are said to be <strong>adjacent</strong> if <code>|a - c| + |b - d| == 1</code>.</p>

<p>A <strong>region</strong> is a <code>3 x 3</code> subgrid where the <strong>absolute difference</strong> in intensity between any two <strong>adjacent</strong> pixels is <strong>less than or equal to</strong> <code>threshold</code>.</p>

<p>All pixels in a <strong>region</strong> belong to that region, note that a pixel <strong>can</strong> belong to <strong>multiple</strong> regions.</p>

<p>You need to calculate a <strong>0-indexed</strong> <code>m x n</code> grid <code>result</code>, where <code>result[i][j]</code> is the <strong>average</strong> intensity of the region to which <code>image[i][j]</code> belongs, <strong>rounded down</strong> to the nearest integer. If <code>image[i][j]</code> belongs to multiple regions, <code>result[i][j]</code> is the <strong>average </strong>of the<strong> rounded down average </strong>intensities of these regions, <strong>rounded down</strong> to the nearest integer. If <code>image[i][j]</code> does<strong> not</strong> belong to any region, <code>result[i][j]</code> is <strong>equal to</strong> <code>image[i][j]</code>.</p>

<p>Return <em>the grid</em> <code>result</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3030.Find%20the%20Grid%20of%20Region%20Average/images/example0corrected.png" style="width: 832px; height: 275px;" />
<pre>
<strong>Input:</strong> image = [[5,6,7,10],[8,9,10,10],[11,12,13,10]], threshold = 3
<strong>Output:</strong> [[9,9,9,9],[9,9,9,9],[9,9,9,9]]
<strong>Explanation:</strong> There exist two regions in the image, which are shown as the shaded areas in the picture. The average intensity of the first region is 9, while the average intensity of the second region is 9.67 which is rounded down to 9. The average intensity of both of the regions is (9 + 9) / 2 = 9. As all the pixels belong to either region 1, region 2, or both of them, the intensity of every pixel in the result is 9. 
Please note that the rounded-down values are used when calculating the average of multiple regions, hence the calculation is done using 9 as the average intensity of region 2, not 9.67.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3030.Find%20the%20Grid%20of%20Region%20Average/images/example1corrected.png" style="width: 805px; height: 377px;" />
<pre>
<strong>Input:</strong> image = [[10,20,30],[15,25,35],[20,30,40],[25,35,45]], threshold = 12
<strong>Output:</strong> [[25,25,25],[27,27,27],[27,27,27],[30,30,30]]
<strong>Explanation:</strong> There exist two regions in the image, which are shown as the shaded areas in the picture. The average intensity of the first region is 25, while the average intensity of the second region is 30. The average intensity of both of the regions is (25 + 30) / 2 = 27.5 which is rounded down to 27. All the pixels in row 0 of the image belong to region 1, hence all the pixels in row 0 in the result are 25. Similarly, all the pixels in row 3 in the result are 30. The pixels in rows 1 and 2 of the image belong to region 1 and region 2, hence their assigned value is 27 in the result.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> image = [[5,6,7],[8,9,10],[11,12,13]], threshold = 1
<strong>Output:</strong> [[5,6,7],[8,9,10],[11,12,13]]
<strong>Explanation:</strong> There does not exist any region in image, hence result[i][j] == image[i][j] for all the pixels.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= n, m &lt;= 500</code></li>
	<li><code>0 &lt;= image[i][j] &lt;= 255</code></li>
	<li><code>0 &lt;= threshold &lt;= 255</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def resultGrid(self, image: List[List[int]], threshold: int) -> List[List[int]]:
        n, m = len(image), len(image[0])
        ans = [[0] * m for _ in range(n)]
        ct = [[0] * m for _ in range(n)]
        for i in range(n - 2):
            for j in range(m - 2):
                region = True
                for k in range(3):
                    for l in range(2):
                        region &= (
                            abs(image[i + k][j + l] - image[i + k][j + l + 1])
                            <= threshold
                        )
                for k in range(2):
                    for l in range(3):
                        region &= (
                            abs(image[i + k][j + l] - image[i + k + 1][j + l])
                            <= threshold
                        )

                if region:
                    tot = 0
                    for k in range(3):
                        for l in range(3):
                            tot += image[i + k][j + l]
                    for k in range(3):
                        for l in range(3):
                            ct[i + k][j + l] += 1
                            ans[i + k][j + l] += tot // 9

        for i in range(n):
            for j in range(m):
                if ct[i][j] == 0:
                    ans[i][j] = image[i][j]
                else:
                    ans[i][j] //= ct[i][j]

        return ans
```

```java
class Solution {
    public int[][] resultGrid(int[][] image, int threshold) {
        int n = image.length;
        int m = image[0].length;
        int[][] ans = new int[n][m];
        int[][] ct = new int[n][m];
        for (int i = 0; i + 2 < n; ++i) {
            for (int j = 0; j + 2 < m; ++j) {
                boolean region = true;
                for (int k = 0; k < 3; ++k) {
                    for (int l = 0; l < 2; ++l) {
                        region
                            &= Math.abs(image[i + k][j + l] - image[i + k][j + l + 1]) <= threshold;
                    }
                }
                for (int k = 0; k < 2; ++k) {
                    for (int l = 0; l < 3; ++l) {
                        region
                            &= Math.abs(image[i + k][j + l] - image[i + k + 1][j + l]) <= threshold;
                    }
                }
                if (region) {
                    int tot = 0;
                    for (int k = 0; k < 3; ++k) {
                        for (int l = 0; l < 3; ++l) {
                            tot += image[i + k][j + l];
                        }
                    }
                    for (int k = 0; k < 3; ++k) {
                        for (int l = 0; l < 3; ++l) {
                            ct[i + k][j + l]++;
                            ans[i + k][j + l] += tot / 9;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (ct[i][j] == 0) {
                    ans[i][j] = image[i][j];
                } else {
                    ans[i][j] /= ct[i][j];
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> resultGrid(vector<vector<int>>& image, int threshold) {
        int n = image.size(), m = image[0].size();
        vector<vector<int>> ans(n, vector<int>(m));
        vector<vector<int>> ct(n, vector<int>(m));
        for (int i = 0; i + 2 < n; ++i) {
            for (int j = 0; j + 2 < m; ++j) {
                bool region = true;
                for (int k = 0; k < 3; ++k) {
                    for (int l = 0; l < 2; ++l) {
                        region &= abs(image[i + k][j + l] - image[i + k][j + l + 1]) <= threshold;
                    }
                }
                for (int k = 0; k < 2; ++k) {
                    for (int l = 0; l < 3; ++l) {
                        region &= abs(image[i + k][j + l] - image[i + k + 1][j + l]) <= threshold;
                    }
                }
                if (region) {
                    int tot = 0;
                    for (int k = 0; k < 3; ++k) {
                        for (int l = 0; l < 3; ++l) {
                            tot += image[i + k][j + l];
                        }
                    }
                    for (int k = 0; k < 3; ++k) {
                        for (int l = 0; l < 3; ++l) {
                            ct[i + k][j + l]++;
                            ans[i + k][j + l] += tot / 9;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (ct[i][j] == 0) {
                    ans[i][j] = image[i][j];
                } else {
                    ans[i][j] /= ct[i][j];
                }
            }
        }
        return ans;
    }
};
```

```go
func resultGrid(image [][]int, threshold int) [][]int {
	n := len(image)
	m := len(image[0])
	ans := make([][]int, n)
	ct := make([][]int, n)
	for i := range ans {
		ans[i] = make([]int, m)
		ct[i] = make([]int, m)
	}
	for i := 0; i+2 < n; i++ {
		for j := 0; j+2 < m; j++ {
			region := true
			for k := 0; k < 3; k++ {
				for l := 0; l < 2; l++ {
					region = region && abs(image[i+k][j+l]-image[i+k][j+l+1]) <= threshold
				}
			}
			for k := 0; k < 2; k++ {
				for l := 0; l < 3; l++ {
					region = region && abs(image[i+k][j+l]-image[i+k+1][j+l]) <= threshold
				}
			}
			if region {
				tot := 0
				for k := 0; k < 3; k++ {
					for l := 0; l < 3; l++ {
						tot += image[i+k][j+l]
					}
				}
				for k := 0; k < 3; k++ {
					for l := 0; l < 3; l++ {
						ct[i+k][j+l]++
						ans[i+k][j+l] += tot / 9
					}
				}
			}
		}
	}
	for i := 0; i < n; i++ {
		for j := 0; j < m; j++ {
			if ct[i][j] == 0 {
				ans[i][j] = image[i][j]
			} else {
				ans[i][j] /= ct[i][j]
			}
		}
	}
	return ans
}
func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function resultGrid(image: number[][], threshold: number): number[][] {
    const n: number = image.length;
    const m: number = image[0].length;
    const ans: number[][] = new Array(n).fill(0).map(() => new Array(m).fill(0));
    const ct: number[][] = new Array(n).fill(0).map(() => new Array(m).fill(0));
    for (let i = 0; i + 2 < n; ++i) {
        for (let j = 0; j + 2 < m; ++j) {
            let region: boolean = true;
            for (let k = 0; k < 3; ++k) {
                for (let l = 0; l < 2; ++l) {
                    region &&= Math.abs(image[i + k][j + l] - image[i + k][j + l + 1]) <= threshold;
                }
            }
            for (let k = 0; k < 2; ++k) {
                for (let l = 0; l < 3; ++l) {
                    region &&= Math.abs(image[i + k][j + l] - image[i + k + 1][j + l]) <= threshold;
                }
            }
            if (region) {
                let tot: number = 0;

                for (let k = 0; k < 3; ++k) {
                    for (let l = 0; l < 3; ++l) {
                        tot += image[i + k][j + l];
                    }
                }
                for (let k = 0; k < 3; ++k) {
                    for (let l = 0; l < 3; ++l) {
                        ct[i + k][j + l]++;
                        ans[i + k][j + l] += Math.floor(tot / 9);
                    }
                }
            }
        }
    }
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < m; ++j) {
            if (ct[i][j] === 0) {
                ans[i][j] = image[i][j];
            } else {
                ans[i][j] = Math.floor(ans[i][j] / ct[i][j]);
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
