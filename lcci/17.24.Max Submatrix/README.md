# [面试题 17.24. 最大子矩阵](https://leetcode.cn/problems/max-submatrix-lcci)

[English Version](/lcci/17.24.Max%20Submatrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个正整数和负整数组成的 N &times; M&nbsp;矩阵，编写代码找出元素总和最大的子矩阵。</p>

<p>返回一个数组 <code>[r1, c1, r2, c2]</code>，其中 <code>r1</code>, <code>c1</code> 分别代表子矩阵左上角的行号和列号，<code>r2</code>, <code>c2</code> 分别代表右下角的行号和列号。若有多个满足条件的子矩阵，返回任意一个均可。</p>

<p><strong>注意：</strong>本题相对书上原题稍作改动</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:
</strong><code>[
&nbsp;  [-1,<strong>0</strong>],
&nbsp;  [0,-1]
]</code>
<strong>输出: </strong>[0,1,0,1]
<strong>解释: </strong>输入中标粗的元素即为输出所表示的矩阵</pre>

<p><strong>说明：</strong></p>

<ul>
	<li><code>1 &lt;= matrix.length, matrix[0].length &lt;= 200</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

双指针 i1, i2 遍历所有可能的“行对”，即子矩阵的上下两条边，这决定了矩阵的高，然后枚举 i1~i2 高度的每一列，看成一维数组的一项，求和最大的子数组即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def getMaxMatrix(self, matrix: List[List[int]]) -> List[int]:
        m, n = len(matrix), len(matrix[0])
        s = [[0] * n for _ in range(m + 1)]
        for i in range(m):
            for j in range(n):
                # 构造列前缀和
                s[i + 1][j] = s[i][j] + matrix[i][j]

        mx = matrix[0][0]
        ans = [0, 0, 0, 0]
        for i1 in range(m):
            for i2 in range(i1, m):
                nums = [0] * n
                for j in range(n):
                    nums[j] = s[i2 + 1][j] - s[i1][j]

                start = 0
                f = nums[0]
                for j in range(1, n):
                    if f > 0:
                        f += nums[j]
                    else:
                        f = nums[j]
                        start = j
                    if f > mx:
                        mx = f
                        ans = [i1, start, i2, j]
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] getMaxMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] s = new int[m + 1][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                s[i + 1][j] = s[i][j] + matrix[i][j];
            }
        }
        int mx = matrix[0][0];
        int[] ans = new int[]{0, 0, 0, 0};
        for (int i1 = 0; i1 < m; ++i1) {
            for (int i2 = i1; i2 < m; ++i2) {
                int[] nums = new int[n];
                for (int j = 0; j < n; ++j) {
                    nums[j] = s[i2 + 1][j] - s[i1][j];
                }
                int start = 0;
                int f = nums[0];
                for (int j = 1; j < n; ++j) {
                    if (f > 0) {
                        f += nums[j];
                    } else {
                        f = nums[j];
                        start = j;
                    }
                    if (f > mx) {
                        mx = f;
                        ans = new int[]{i1, start, i2, j};
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
    vector<int> getMaxMatrix(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        vector<vector<int>> s(m + 1, vector<int>(n));
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                s[i + 1][j] = s[i][j] + matrix[i][j];
        int mx = matrix[0][0];
        vector<int> ans(4);
        for (int i1 = 0; i1 < m; ++i1) {
            for (int i2 = i1; i2 < m; ++i2) {
                vector<int> nums;
                for (int j = 0; j < n; ++j)
                    nums.push_back(s[i2 + 1][j] - s[i1][j]);
                int start = 0;
                int f = nums[0];
                for (int j = 1; j < n; ++j) {
                    if (f > 0)
                        f += nums[j];
                    else {
                        f = nums[j];
                        start = j;
                    }
                    if (f > mx) {
                        mx = f;
                        ans[0] = i1;
                        ans[1] = start;
                        ans[2] = i2;
                        ans[3] = j;
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
func getMaxMatrix(matrix [][]int) []int {
	m, n := len(matrix), len(matrix[0])
	s := make([][]int, m+1)
	for i := range s {
		s[i] = make([]int, n)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			s[i+1][j] = s[i][j] + matrix[i][j]
		}
	}
	mx := matrix[0][0]
	ans := make([]int, 4)
	for i1 := 0; i1 < m; i1++ {
		for i2 := i1; i2 < m; i2++ {
			var nums []int
			for j := 0; j < n; j++ {
				nums = append(nums, s[i2+1][j]-s[i1][j])
			}
			start := 0
			f := nums[0]
			for j := 1; j < n; j++ {
				if f > 0 {
					f += nums[j]
				} else {
					f = nums[j]
					start = j
				}
				if f > mx {
					mx = f
					ans = []int{i1, start, i2, j}
				}
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
