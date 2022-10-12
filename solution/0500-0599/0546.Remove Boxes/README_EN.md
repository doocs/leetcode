# [546. Remove Boxes](https://leetcode.com/problems/remove-boxes)

[中文文档](/solution/0500-0599/0546.Remove%20Boxes/README.md)

## Description

<p>You are given several <code>boxes</code> with different colors represented by different positive numbers.</p>

<p>You may experience several rounds to remove boxes until there is no box left. Each time you can choose some continuous boxes with the same color (i.e., composed of <code>k</code> boxes, <code>k &gt;= 1</code>), remove them and get <code>k * k</code> points.</p>

<p>Return <em>the maximum points you can get</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> boxes = [1,3,2,2,2,3,4,3,1]
<strong>Output:</strong> 23
<strong>Explanation:</strong>
[1, 3, 2, 2, 2, 3, 4, 3, 1] 
----&gt; [1, 3, 3, 4, 3, 1] (3*3=9 points) 
----&gt; [1, 3, 3, 3, 1] (1*1=1 points) 
----&gt; [1, 1] (3*3=9 points) 
----&gt; [] (2*2=4 points)
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> boxes = [1,1,1]
<strong>Output:</strong> 9
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> boxes = [1]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= boxes.length &lt;= 100</code></li>
	<li><code>1 &lt;= boxes[i]&nbsp;&lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def removeBoxes(self, boxes: List[int]) -> int:
        @cache
        def dfs(i, j, k):
            if i > j:
                return 0
            while i < j and boxes[j] == boxes[j - 1]:
                j, k = j - 1, k + 1
            ans = dfs(i, j - 1, 0) + (k + 1) * (k + 1)
            for h in range(i, j):
                if boxes[h] == boxes[j]:
                    ans = max(ans, dfs(h + 1, j - 1, 0) + dfs(i, h, k + 1))
            return ans

        n = len(boxes)
        ans = dfs(0, n - 1, 0)
        dfs.cache_clear()
        return ans
```

### **Java**

```java
class Solution {
    private int[][][] f;
    private int[] b;

    public int removeBoxes(int[] boxes) {
        b = boxes;
        int n = b.length;
        f = new int[n][n][n];
        return dfs(0, n - 1, 0);
    }

    private int dfs(int i, int j, int k) {
        if (i > j) {
            return 0;
        }
        while (i < j && b[j] == b[j - 1]) {
            --j;
            ++k;
        }
        if (f[i][j][k] > 0) {
            return f[i][j][k];
        }
        int ans = dfs(i, j - 1, 0) + (k + 1) * (k + 1);
        for (int h = i; h < j; ++h) {
            if (b[h] == b[j]) {
                ans = Math.max(ans, dfs(h + 1, j - 1, 0) + dfs(i, h, k + 1));
            }
        }
        f[i][j][k] = ans;
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int removeBoxes(vector<int>& boxes) {
        int n = boxes.size();
        vector<vector<vector<int>>> f(n, vector<vector<int>>(n, vector<int>(n)));
        function<int(int, int, int)> dfs;
        dfs = [&](int i, int j, int k) {
            if (i > j) return 0;
            while (i < j && boxes[j] == boxes[j - 1]) {
                --j;
                ++k;
            }
            if (f[i][j][k]) return f[i][j][k];
            int ans = dfs(i, j - 1, 0) + (k + 1) * (k + 1);
            for (int h = i; h < j; ++h) {
                if (boxes[h] == boxes[j]) {
                    ans = max(ans, dfs(h + 1, j - 1, 0) + dfs(i, h, k + 1));
                }
            }
            f[i][j][k] = ans;
            return ans;
        };
        return dfs(0, n - 1, 0);
    }
};
```

### **Go**

```go
func removeBoxes(boxes []int) int {
	n := len(boxes)
	f := make([][][]int, n)
	for i := range f {
		f[i] = make([][]int, n)
		for j := range f[i] {
			f[i][j] = make([]int, n)
		}
	}
	var dfs func(i, j, k int) int
	dfs = func(i, j, k int) int {
		if i > j {
			return 0
		}
		for i < j && boxes[j] == boxes[j-1] {
			j, k = j-1, k+1
		}
		if f[i][j][k] > 0 {
			return f[i][j][k]
		}
		ans := dfs(i, j-1, 0) + (k+1)*(k+1)
		for h := i; h < j; h++ {
			if boxes[h] == boxes[j] {
				ans = max(ans, dfs(h+1, j-1, 0)+dfs(i, h, k+1))
			}
		}
		f[i][j][k] = ans
		return ans
	}
	return dfs(0, n-1, 0)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
