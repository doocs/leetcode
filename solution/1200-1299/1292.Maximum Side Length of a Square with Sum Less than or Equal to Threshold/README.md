# [1292. 元素和小于等于阈值的正方形的最大边长](https://leetcode-cn.com/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold)

[English Version](/solution/1200-1299/1292.Maximum%20Side%20Length%20of%20a%20Square%20with%20Sum%20Less%20than%20or%20Equal%20to%20Threshold/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个大小为 <code>m x n</code> 的矩阵 <code>mat</code> 和一个整数阈值 <code>threshold</code>。</p>

<p>请你返回元素总和小于或等于阈值的正方形区域的最大边长；如果没有这样的正方形区域，则返回 <strong>0 </strong>。<br />
 </p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1292.Maximum%20Side%20Length%20of%20a%20Square%20with%20Sum%20Less%20than%20or%20Equal%20to%20Threshold/images/e1.png" style="height: 186px; width: 335px;" /></p>

<pre>
<strong>输入：</strong>mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
<strong>输出：</strong>2
<strong>解释：</strong>总和小于或等于 4 的正方形的最大边长为 2，如图所示。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
<strong>输出：</strong>0
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>mat = [[1,1,1,1],[1,0,0,0],[1,0,0,0],[1,0,0,0]], threshold = 6
<strong>输出：</strong>3
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>mat = [[18,70],[61,1],[25,85],[14,40],[11,96],[97,96],[63,45]], threshold = 40184
<strong>输出：</strong>2
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= m, n <= 300</code></li>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>0 <= mat[i][j] <= 10000</code></li>
	<li><code>0 <= threshold <= 10^5</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

二维前缀和。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxSideLength(self, mat: List[List[int]], threshold: int) -> int:
        m, n = len(mat), len(mat[0])
        s = [[0] * 310 for _ in range(310)]
        for i in range(m):
            for j in range(n):
                s[i + 1][j + 1] = s[i][j + 1] + \
                    s[i + 1][j] - s[i][j] + mat[i][j]

        def check(l):
            for i in range(m):
                for j in range(n):
                    if i + l - 1 < m and j + l - 1 < n:
                        i1, j1 = i + l - 1, j + l - 1
                        t = s[i1 + 1][j1 + 1] - s[i1 + 1][j] - \
                            s[i][j1 + 1] + s[i][j]
                        if t <= threshold:
                            return True
            return False

        left, right = 0, min(m, n)
        while left < right:
            mid = (left + right + 1) >> 1
            if check(mid):
                left = mid
            else:
                right = mid - 1
        return left
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length, n = mat[0].length;
        int[][] s = new int[310][310];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                s[i + 1][j + 1] = s[i][j + 1] + s[i + 1][j] - s[i][j] + mat[i][j];
            }
        }
        int left = 0, right = Math.min(m, n);
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (check(mid, s, m, n, threshold)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int l, int[][] s, int m, int n, int threshold) {
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i + l - 1 < m && j + l - 1 < n) {
                    int i1 = i + l - 1, j1 = j + l - 1;
                    int t = s[i1 + 1][j1 + 1] - s[i1 + 1][j] - s[i][j1 + 1] + s[i][j];
                    if (t <= threshold) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxSideLength(vector<vector<int>>& mat, int threshold) {
        int m = mat.size(), n = mat[0].size();
        vector<vector<int>> s(310, vector<int>(310));
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                s[i + 1][j + 1] = s[i][j + 1] + s[i + 1][j] - s[i][j] + mat[i][j];
        int left = 0, right = min(m, n);
        while (left < right)
        {
            int mid = left + right + 1 >> 1;
            if (check(mid, s, m, n, threshold)) left = mid;
            else right = mid - 1;
        }
        return left;
    }

    bool check(int l, vector<vector<int>>& s, int m, int n, int threshold) {
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (i + l - 1 < m && j + l - 1 < n)
                {
                    int i1 = i + l - 1, j1 = j + l - 1;
                    int t = s[i1 + 1][j1 + 1] - s[i1 + 1][j] - s[i][j1 + 1] + s[i][j];
                    if (t <= threshold) return true;
                }
            }
        }
        return false;
    }
};
```

### **Go**

```go
func maxSideLength(mat [][]int, threshold int) int {
	m, n := len(mat), len(mat[0])
	s := make([][]int, 310)
	for i := 0; i < len(s); i++ {
		s[i] = make([]int, 310)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			s[i+1][j+1] = s[i][j+1] + s[i+1][j] - s[i][j] + mat[i][j]
		}
	}
	left, right := 0, min(m, n)
	check := func(l int) bool {
		for i := 0; i < m; i++ {
			for j := 0; j < n; j++ {
				if i+l-1 < m && j+l-1 < n {
					i1, j1 := i+l-1, j+l-1
					t := s[i1+1][j1+1] - s[i1+1][j] - s[i][j1+1] + s[i][j]
					if t <= threshold {
						return true
					}
				}
			}
		}
		return false
	}
	for left < right {
		mid := (left + right + 1) >> 1
		if check(mid) {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return left
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
