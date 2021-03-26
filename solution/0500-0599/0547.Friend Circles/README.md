# [547. 朋友圈](https://leetcode-cn.com/problems/friend-circles)

[English Version](/solution/0500-0599/0547.Friend%20Circles/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>班上有&nbsp;<strong>N&nbsp;</strong>名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B&nbsp;的朋友，B 是 C&nbsp;的朋友，那么我们可以认为 A 也是 C&nbsp;的朋友。所谓的朋友圈，是指所有朋友的集合。</p>

<p>给定一个&nbsp;<strong>N * N&nbsp;</strong>的矩阵&nbsp;<strong>M</strong>，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生<strong>互为</strong>朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
<strong>输出:</strong> 2 
<strong>说明：</strong>已知学生0和学生1互为朋友，他们在一个朋友圈。
第2个学生自己在一个朋友圈。所以返回2。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> 
[[1,1,0],
 [1,1,1],
 [0,1,1]]
<strong>输出:</strong> 1
<strong>说明：</strong>已知学生0和学生1互为朋友，学生1和学生2互为朋友，所以学生0和学生2也是朋友，所以他们三个在一个朋友圈，返回1。
</pre>

<p><strong>注意：</strong></p>

<ol>
	<li>N 在[1,200]的范围内。</li>
	<li>对于所有学生，有M[i][i] = 1。</li>
	<li>如果有M[i][j] = 1，则有M[j][i] = 1。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

深度优先搜索。判断学生与学生之间是否属于同一个连通分量，最后连通分量的总数即为结果。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findCircleNum(self, isConnected: List[List[int]]) -> int:
        def dfs(i):
            for j in range(n):
                if not visited[j] and isConnected[i][j] == 1:
                    visited[j] = True
                    dfs(j)

        n = len(isConnected)
        visited = [False] * n
        num = 0
        for i in range(n):
            if not visited[i]:
                dfs(i)
                num += 1
        return num
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int num = 0;
        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                dfs(isConnected, visited, i, n);
                ++num;
            }
        }
        return num;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int i, int n) {
        for (int j = 0; j < n; ++j) {
            if (!visited[j] && isConnected[i][j] == 1) {
                visited[j] = true;
                dfs(isConnected, visited, j, n);
            }
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
