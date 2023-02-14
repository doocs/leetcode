# [1253. Reconstruct a 2-Row Binary Matrix](https://leetcode.com/problems/reconstruct-a-2-row-binary-matrix)

[中文文档](/solution/1200-1299/1253.Reconstruct%20a%202-Row%20Binary%20Matrix/README.md)

## Description

<p>Given the following details of a matrix with <code>n</code> columns and <code>2</code> rows :</p>

<ul>
	<li>The matrix is a binary matrix, which means each element in the matrix can be <code>0</code> or <code>1</code>.</li>
	<li>The sum of elements of the 0-th(upper) row is given as <code>upper</code>.</li>
	<li>The sum of elements of the 1-st(lower) row is given as <code>lower</code>.</li>
	<li>The sum of elements in the i-th column(0-indexed) is <code>colsum[i]</code>, where <code>colsum</code> is given as an integer array with length <code>n</code>.</li>
</ul>

<p>Your task is to reconstruct the matrix with <code>upper</code>, <code>lower</code> and <code>colsum</code>.</p>

<p>Return it as a 2-D integer array.</p>

<p>If there are more than one valid solution, any of them will be accepted.</p>

<p>If no valid solution exists, return an empty 2-D array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> upper = 2, lower = 1, colsum = [1,1,1]
<strong>Output:</strong> [[1,1,0],[0,0,1]]
<strong>Explanation: </strong>[[1,0,1],[0,1,0]], and [[0,1,1],[1,0,0]] are also correct answers.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> upper = 2, lower = 3, colsum = [2,2,1,1]
<strong>Output:</strong> []
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> upper = 5, lower = 5, colsum = [2,1,2,0,1,0,1,2,0,1]
<strong>Output:</strong> [[1,1,1,0,1,0,0,1,0,0],[1,0,1,0,0,0,1,1,0,1]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= colsum.length &lt;= 10^5</code></li>
	<li><code>0 &lt;= upper, lower &lt;= colsum.length</code></li>
	<li><code>0 &lt;= colsum[i] &lt;= 2</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def reconstructMatrix(self, upper: int, lower: int, colsum: List[int]) -> List[List[int]]:
        n = len(colsum)
        ans = [[0] * n for _ in range(2)]
        for j, v in enumerate(colsum):
            if v == 2:
                ans[0][j] = ans[1][j] = 1
                upper, lower = upper - 1, lower - 1
            if v == 1:
                if upper > lower:
                    upper -= 1
                    ans[0][j] = 1
                else:
                    lower -= 1
                    ans[1][j] = 1
            if upper < 0 or lower < 0:
                return []
        return ans if lower == upper == 0 else []
```

### **Java**

```java
class Solution {
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        int n = colsum.length;
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();
        for (int j = 0; j < n; ++j) {
            if (colsum[j] == 2) {
                first.add(1);
                second.add(1);
                upper--;
                lower--;
            } else if (colsum[j] == 1) {
                if (upper > lower) {
                    upper--;
                    first.add(1);
                    second.add(0);
                } else {
                    lower--;
                    first.add(0);
                    second.add(1);
                }
            } else {
                first.add(0);
                second.add(0);
            }
            if (upper < 0 || lower < 0) {
                return ans;
            }
        }
        if (upper != 0 || lower != 0) {
            return ans;
        }
        ans.add(first);
        ans.add(second);
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> reconstructMatrix(int upper, int lower, vector<int>& colsum) {
        int n = colsum.size();
        vector<vector<int>> ans(2, vector<int>(n));
        for (int j = 0; j < n; ++j) {
            if (colsum[j] == 2) {
                ans[0][j] = ans[1][j] = 1;
                upper--;
                lower--;
            }
            if (colsum[j] == 1) {
                if (upper > lower) {
                    upper--;
                    ans[0][j] = 1;
                } else {
                    lower--;
                    ans[1][j] = 1;
                }
            }
            if (upper < 0 || lower < 0) {
                return {};
            }
        }
        return upper != 0 || lower != 0 ? vector<vector<int>>{} : ans;
    }
};
```

### **Go**

```go
func reconstructMatrix(upper int, lower int, colsum []int) [][]int {
	n := len(colsum)
	ans := make([][]int, 2)
	for i := range ans {
		ans[i] = make([]int, n)
	}
	for j, v := range colsum {
		if v == 2 {
			ans[0][j], ans[1][j] = 1, 1
			upper--
			lower--
		}
		if v == 1 {
			if upper > lower {
				upper--
				ans[0][j] = 1
			} else {
				lower--
				ans[1][j] = 1
			}
		}
		if upper < 0 || lower < 0 {
			return [][]int{}
		}
	}
	if upper != 0 || lower != 0 {
		return [][]int{}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
