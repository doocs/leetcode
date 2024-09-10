---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20100.%20%E4%B8%89%E8%A7%92%E5%BD%A2%E4%B8%AD%E6%9C%80%E5%B0%8F%E8%B7%AF%E5%BE%84%E4%B9%8B%E5%92%8C/README.md
---

<!-- problem:start -->

# [剑指 Offer II 100. 三角形中最小路径之和](https://leetcode.cn/problems/IlPe0q)

## 题目描述

<!-- description:start -->

<p>给定一个三角形 <code>triangle</code> ，找出自顶向下的最小路径和。</p>

<p>每一步只能移动到下一行中相邻的结点上。<strong>相邻的结点 </strong>在这里指的是 <strong>下标</strong> 与 <strong>上一层结点下标</strong> 相同或者等于 <strong>上一层结点下标 + 1</strong> 的两个结点。也就是说，如果正位于当前行的下标 <code>i</code> ，那么下一步可以移动到下一行的下标 <code>i</code> 或 <code>i + 1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
<strong>输出：</strong>11
<strong>解释：</strong>如下面简图所示：
   <strong>2</strong>
  <strong>3</strong> 4
 6 <strong>5</strong> 7
4 <strong>1</strong> 8 3
自顶向下的最小路径和为&nbsp;11（即，2&nbsp;+&nbsp;3&nbsp;+&nbsp;5&nbsp;+&nbsp;1&nbsp;= 11）。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>triangle = [[-10]]
<strong>输出：</strong>-10
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= triangle.length &lt;= 200</code></li>
	<li><code>triangle[0].length == 1</code></li>
	<li><code>triangle[i].length == triangle[i - 1].length + 1</code></li>
	<li><code>-10<sup>4</sup> &lt;= triangle[i][j] &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong></p>

<ul>
	<li>你可以只使用 <code>O(n)</code>&nbsp;的额外空间（<code>n</code> 为三角形的总行数）来解决这个问题吗？</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 120&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/triangle/">https://leetcode.cn/problems/triangle/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        n = len(triangle)
        dp = [[0] * (n + 1) for _ in range(n + 1)]
        for i in range(n - 1, -1, -1):
            for j in range(i + 1):
                dp[i][j] = min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle[i][j]
        return dp[0][0]
```

#### Java

```java
class Solution {

    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j <= i; ++j) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumTotal(vector<vector<int>>& triangle) {
        int n = triangle.size();
        vector<int> dp(n + 1);
        for (int i = n - 1; i >= 0; --i)
            for (int j = 0; j <= i; ++j)
                dp[j] = min(dp[j], dp[j + 1]) + triangle[i][j];
        return dp[0];
    }
};
```

#### Go

```go
func minimumTotal(triangle [][]int) int {
	n := len(triangle)
	dp := make([]int, n+1)
	for i := n - 1; i >= 0; i-- {
		for j := 0; j <= i; j++ {
			dp[j] = min(dp[j], dp[j+1]) + triangle[i][j]
		}
	}
	return dp[0]
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start-->

### 方法二

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        n = len(triangle)
        dp = [0] * (n + 1)
        for i in range(n - 1, -1, -1):
            for j in range(i + 1):
                dp[j] = min(dp[j], dp[j + 1]) + triangle[i][j]
        return dp[0]
```

#### Swift

```swift
class Solution {
    func minimumTotal(_ triangle: [[Int]]) -> Int {
        let n = triangle.count
        var dp = Array(repeating: 0, count: n + 1)

        for i in (0..<n).reversed() {
            for j in 0...i {
                dp[j] = min(dp[j], dp[j + 1]) + triangle[i][j]
            }
        }

        return dp[0]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
