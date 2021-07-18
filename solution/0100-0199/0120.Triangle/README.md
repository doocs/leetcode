# [120. 三角形最小路径和](https://leetcode-cn.com/problems/triangle)

[English Version](/solution/0100-0199/0120.Triangle/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个三角形 <code>triangle</code> ，找出自顶向下的最小路径和。</p>

<p>每一步只能移动到下一行中相邻的结点上。<strong>相邻的结点 </strong>在这里指的是 <strong>下标</strong> 与 <strong>上一层结点下标</strong> 相同或者等于 <strong>上一层结点下标 + 1</strong> 的两个结点。也就是说，如果正位于当前行的下标 <code>i</code> ，那么下一步可以移动到下一行的下标 <code>i</code> 或 <code>i + 1</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
<strong>输出：</strong>11
<strong>解释：</strong>如下面简图所示：
   <strong>2</strong>
  <strong>3</strong> 4
 6 <strong>5</strong> 7
4 <strong>1</strong> 8 3
自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>triangle = [[-10]]
<strong>输出：</strong>-10
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= triangle.length <= 200</code></li>
	<li><code>triangle[0].length == 1</code></li>
	<li><code>triangle[i].length == triangle[i - 1].length + 1</code></li>
	<li><code>-10<sup>4</sup> <= triangle[i][j] <= 10<sup>4</sup></code></li>
</ul>

<p> </p>

<p><strong>进阶：</strong></p>

<ul>
	<li>你可以只使用 <code>O(n)</code> 的额外空间（<code>n</code> 为三角形的总行数）来解决这个问题吗？</li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

动态规划。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        n = len(triangle)
        for i in range(1, n):
            for j in range(i + 1):
                mi = float('inf')
                if j > 0:
                    mi = min(mi, triangle[i - 1][j - 1])
                if j < i:
                    mi = min(mi, triangle[i - 1][j])
                triangle[i][j] += mi
        return min(triangle[n - 1])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i + 1; ++j) {
                int mi = Integer.MAX_VALUE;
                if (j > 0) {
                    mi = Math.min(mi, triangle.get(i - 1).get(j - 1));
                }
                if (j < i) {
                    mi = Math.min(mi, triangle.get(i - 1).get(j));
                }
                triangle.get(i).set(j, triangle.get(i).get(j) + mi);
            }
        }
        int res = Integer.MAX_VALUE;
        for (int val : triangle.get(n - 1)) {
            res = Math.min(res, val);
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumTotal(vector<vector<int>>& triangle) {
        int n = triangle.size();
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i + 1; ++j) {
                int mi = INT_MAX;
                if (j > 0) mi = min(mi, triangle[i - 1][j - 1]);
                if (j < i) mi = min(mi, triangle[i - 1][j]);
                triangle[i][j] += mi;
            }
        }
        int res = INT_MAX;
        for (int& val : triangle[n - 1]) {
            res = min(res, val);
        }
        return res;
    }
};
```

### **Go**

```go
func minimumTotal(triangle [][]int) int {
	n := len(triangle)
	for i := 1; i < n; i++ {
		for j := 0; j < i+1; j++ {
			mi := 2000000
			if j > 0 && mi > triangle[i-1][j-1] {
				mi = triangle[i-1][j-1]
			}
			if j < i && mi > triangle[i-1][j] {
				mi = triangle[i-1][j]
			}
			triangle[i][j] += mi
		}
	}

	res := 2000000
	for j := 0; j < n; j++ {
		if res > triangle[n-1][j] {
			res = triangle[n-1][j]
		}
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
