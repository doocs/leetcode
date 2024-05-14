---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3030.Find%20the%20Grid%20of%20Region%20Average/README.md
rating: 1896
tags:
    - 数组
    - 矩阵
---

# [3030. 找出网格的区域平均强度](https://leetcode.cn/problems/find-the-grid-of-region-average)

[English Version](/solution/3000-3099/3030.Find%20the%20Grid%20of%20Region%20Average/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始、大小为 <code>m x n</code> 的网格 <code>image</code> ，表示一个灰度图像，其中 <code>image[i][j]</code> 表示在范围 <code>[0..255]</code> 内的某个像素强度。另给你一个<strong> 非负 </strong>整数 <code>threshold</code> 。</p>

<p>如果 <code>image[a][b]</code> 和 <code>image[c][d]</code> 满足 <code>|a - c| + |b - d| == 1</code> ，则称这两个像素是<strong> 相邻像素</strong> 。</p>

<p><strong>区域 </strong>是一个 <code>3 x 3</code> 的子网格，且满足区域中任意两个 <strong>相邻</strong> 像素之间，像素强度的<strong> 绝对差 </strong><strong> 小于或等于 </strong><code>threshold</code> 。</p>

<p><strong>区域</strong> 内的所有像素都认为属于该区域，而一个像素 <strong>可以 </strong>属于 <strong>多个</strong> 区域。</p>

<p>你需要计算一个下标从 <strong>0</strong> 开始、大小为 <code>m x n</code> 的网格 <code>result</code> ，其中 <code>result[i][j]</code> 是 <code>image[i][j]</code> 所属区域的 <strong>平均 </strong>强度，<strong>向下取整 </strong>到最接近的整数。如果 <code>image[i][j]</code> 属于多个区域，<code>result[i][j]</code> 是这些区域的<strong> </strong><strong>“取整后的平均强度”</strong> 的<strong> 平均值</strong>，也 <strong>向下取整 </strong>到最接近的整数。如果 <code>image[i][j]</code> 不属于任何区域，则 <code>result[i][j]</code><strong> 等于 </strong><code>image[i][j]</code> 。</p>

<p>返回网格 <code>result</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3030.Find%20the%20Grid%20of%20Region%20Average/images/example0corrected.png" style="width: 832px; height: 275px;" />
<pre>
<strong>输入：</strong>image = [[5,6,7,10],[8,9,10,10],[11,12,13,10]], threshold = 3
<strong>输出：</strong>[[9,9,9,9],[9,9,9,9],[9,9,9,9]]
<strong>解释：</strong>图像中存在两个区域，如图片中的阴影区域所示。第一个区域的平均强度为 9 ，而第二个区域的平均强度为 9.67 ，向下取整为 9 。两个区域的平均强度为 (9 + 9) / 2 = 9 。由于所有像素都属于区域 1 、区域 2 或两者，因此 result 中每个像素的强度都为 9 。
注意，在计算多个区域的平均值时使用了向下取整的值，因此使用区域 2 的平均强度 9 来进行计算，而不是 9.67 。
</pre>

<p><strong class="example">示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3030.Find%20the%20Grid%20of%20Region%20Average/images/example1corrected.png" style="width: 805px; height: 377px;" />
<pre>
<strong>输入：</strong>image = [[10,20,30],[15,25,35],[20,30,40],[25,35,45]], threshold = 12
<strong>输出：</strong>[[25,25,25],[27,27,27],[27,27,27],[30,30,30]]
<strong>解释：</strong>图像中存在两个区域，如图片中的阴影区域所示。第一个区域的平均强度为 25 ，而第二个区域的平均强度为 30 。两个区域的平均强度为 (25 + 30) / 2 = 27.5 ，向下取整为 27 。图像中第 0 行的所有像素属于区域 1 ，因此 result 中第 0 行的所有像素为 25 。同理，result 中第 3 行的所有像素为 30 。图像中第 1 行和第 2 行的像素属于区域 1 和区域 2 ，因此它们在 result 中的值为 27 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>image = [[5,6,7],[8,9,10],[11,12,13]], threshold = 1
<strong>输出：</strong>[[5,6,7],[8,9,10],[11,12,13]]
<strong>解释：</strong>图像中不存在任何区域，因此对于所有像素，result[i][j] == image[i][j] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= n, m &lt;= 500</code></li>
	<li><code>0 &lt;= image[i][j] &lt;= 255</code></li>
	<li><code>0 &lt;= threshold &lt;= 255</code></li>
</ul>

## 解法

### 方法一

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
