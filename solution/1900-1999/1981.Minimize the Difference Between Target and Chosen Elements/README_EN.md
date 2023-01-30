# [1981. Minimize the Difference Between Target and Chosen Elements](https://leetcode.com/problems/minimize-the-difference-between-target-and-chosen-elements)

[中文文档](/solution/1900-1999/1981.Minimize%20the%20Difference%20Between%20Target%20and%20Chosen%20Elements/README.md)

## Description

<p>You are given an <code>m x n</code> integer matrix <code>mat</code> and an integer <code>target</code>.</p>

<p>Choose one integer from <strong>each row</strong> in the matrix such that the <strong>absolute difference</strong> between <code>target</code> and the <strong>sum</strong> of the chosen elements is <strong>minimized</strong>.</p>

<p>Return <em>the <strong>minimum absolute difference</strong></em>.</p>

<p>The <strong>absolute difference</strong> between two numbers <code>a</code> and <code>b</code> is the absolute value of <code>a - b</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1981.Minimize%20the%20Difference%20Between%20Target%20and%20Chosen%20Elements/images/matrix1.png" style="width: 181px; height: 181px;" />
<pre>
<strong>Input:</strong> mat = [[1,2,3],[4,5,6],[7,8,9]], target = 13
<strong>Output:</strong> 0
<strong>Explanation:</strong> One possible choice is to:
- Choose 1 from the first row.
- Choose 5 from the second row.
- Choose 7 from the third row.
The sum of the chosen elements is 13, which equals the target, so the absolute difference is 0.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1981.Minimize%20the%20Difference%20Between%20Target%20and%20Chosen%20Elements/images/matrix1-1.png" style="width: 61px; height: 181px;" />
<pre>
<strong>Input:</strong> mat = [[1],[2],[3]], target = 100
<strong>Output:</strong> 94
<strong>Explanation:</strong> The best possible choice is to:
- Choose 1 from the first row.
- Choose 2 from the second row.
- Choose 3 from the third row.
The sum of the chosen elements is 6, and the absolute difference is 94.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1981.Minimize%20the%20Difference%20Between%20Target%20and%20Chosen%20Elements/images/matrix1-3.png" style="width: 301px; height: 61px;" />
<pre>
<strong>Input:</strong> mat = [[1,2,9,8,7]], target = 6
<strong>Output:</strong> 1
<strong>Explanation:</strong> The best choice is to choose 7 from the first row.
The absolute difference is 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 70</code></li>
	<li><code>1 &lt;= mat[i][j] &lt;= 70</code></li>
	<li><code>1 &lt;= target &lt;= 800</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimizeTheDifference(self, mat: List[List[int]], target: int) -> int:
        f = {0}
        for row in mat:
            f = set(a + b for a in f for b in row)
        return min(abs(v - target) for v in f)
```

### **Java**

```java
class Solution {
    public int minimizeTheDifference(int[][] mat, int target) {
        Set<Integer> f = new HashSet<>();
        f.add(0);
        for (var row : mat) {
            Set<Integer> g = new HashSet<>();
            for (int a : f) {
                for (int b : row) {
                    g.add(a + b);
                }
            }
            f = g;
        }
        int ans = 1 << 30;
        for (int v : f) {
            ans = Math.min(ans, Math.abs(v - target));
        }
        return ans;
    }
}
```

```java
class Solution {
    public int minimizeTheDifference(int[][] mat, int target) {
        boolean[] f = {true};
        for (var row : mat) {
            int mx = 0;
            for (int x : row) {
                mx = Math.max(mx, x);
            }
            boolean[] g = new boolean[f.length + mx];
            for (int x : row) {
                for (int j = x; j < f.length + x; ++j) {
                    g[j] |= f[j - x];
                }
            }
            f = g;
        }
        int ans = 1 << 30;
        for (int j = 0; j < f.length; ++j) {
            if (f[j]) {
                ans = Math.min(ans, Math.abs(j - target));
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
    int minimizeTheDifference(vector<vector<int>>& mat, int target) {
        vector<int> f = {1};
        for (auto& row : mat) {
            int mx = *max_element(row.begin(), row.end());
            vector<int> g(f.size() + mx);
            for (int x : row) {
                for (int j = x; j < f.size() + x; ++j) {
                    g[j] |= f[j - x];
                }
            }
            f = move(g);
        }
        int ans = 1 << 30;
        for (int j = 0; j < f.size(); ++j) {
            if (f[j]) {
                ans = min(ans, abs(j - target));
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func minimizeTheDifference(mat [][]int, target int) int {
	f := []int{1}
	for _, row := range mat {
		mx := 0
		for _, x := range row {
			mx = max(mx, x)
		}
		g := make([]int, len(f)+mx)
		for _, x := range row {
			for j := x; j < len(f)+x; j++ {
				g[j] |= f[j-x]
			}
		}
		f = g
	}
	ans := 1 << 30
	for j, v := range f {
		if v == 1 {
			ans = min(ans, abs(j-target))
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **...**

```

```

<!-- tabs:end -->
