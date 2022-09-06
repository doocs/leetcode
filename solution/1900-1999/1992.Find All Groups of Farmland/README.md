# [1992. 找到所有的农场组](https://leetcode.cn/problems/find-all-groups-of-farmland)

[English Version](/solution/1900-1999/1992.Find%20All%20Groups%20of%20Farmland/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始，大小为&nbsp;<code>m x n</code>&nbsp;的二进制矩阵&nbsp;<code>land</code>&nbsp;，其中 <code>0</code>&nbsp;表示一单位的森林土地，<code>1</code>&nbsp;表示一单位的农场土地。</p>

<p>为了让农场保持有序，农场土地之间以矩形的 <strong>农场组</strong> 的形式存在。每一个农场组都 <strong>仅</strong>&nbsp;包含农场土地。且题目保证不会有两个农场组相邻，也就是说一个农场组中的任何一块土地都 <strong>不会</strong>&nbsp;与另一个农场组的任何一块土地在四个方向上相邻。</p>

<p><code>land</code>&nbsp;可以用坐标系统表示，其中 <code>land</code>&nbsp;左上角坐标为&nbsp;<code>(0, 0)</code>&nbsp;，右下角坐标为&nbsp;<code>(m-1, n-1)</code>&nbsp;。请你找到所有 <b>农场组</b>&nbsp;最左上角和最右下角的坐标。一个左上角坐标为&nbsp;<code>(r<sub>1</sub>, c<sub>1</sub>)</code>&nbsp;且右下角坐标为&nbsp;<code>(r<sub>2</sub>, c<sub>2</sub>)</code>&nbsp;的 <strong>农场组</strong> 用长度为 4 的数组&nbsp;<code>[r<sub>1</sub>, c<sub>1</sub>, r<sub>2</sub>, c<sub>2</sub>]</code>&nbsp;表示。</p>

<p>请你返回一个二维数组，它包含若干个长度为 4 的子数组，每个子数组表示 <code>land</code>&nbsp;中的一个 <strong>农场组</strong>&nbsp;。如果没有任何农场组，请你返回一个空数组。可以以 <strong>任意顺序</strong>&nbsp;返回所有农场组。</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1992.Find%20All%20Groups%20of%20Farmland/images/screenshot-2021-07-27-at-12-23-15-copy-of-diagram-drawio-diagrams-net.png" style="width: 300px; height: 300px;"></p>

<pre><b>输入：</b>land = [[1,0,0],[0,1,1],[0,1,1]]
<b>输出：</b>[[0,0,0,0],[1,1,2,2]]
<strong>解释：</strong>
第一个农场组的左上角为 land[0][0] ，右下角为 land[0][0] 。
第二个农场组的左上角为 land[1][1] ，右下角为 land[2][2] 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1992.Find%20All%20Groups%20of%20Farmland/images/screenshot-2021-07-27-at-12-30-26-copy-of-diagram-drawio-diagrams-net.png" style="width: 200px; height: 200px;"></p>

<pre><b>输入：</b>land = [[1,1],[1,1]]
<b>输出：</b>[[0,0,1,1]]
<strong>解释：</strong>
第一个农场组左上角为 land[0][0] ，右下角为 land[1][1] 。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1992.Find%20All%20Groups%20of%20Farmland/images/screenshot-2021-07-27-at-12-32-24-copy-of-diagram-drawio-diagrams-net.png" style="width: 100px; height: 100px;"></p>

<pre><b>输入：</b>land = [[0]]
<b>输出：</b>[]
<b>解释：</b>
没有任何农场组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == land.length</code></li>
	<li><code>n == land[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 300</code></li>
	<li><code>land</code>&nbsp;只包含&nbsp;<code>0</code>&nbsp;和&nbsp;<code>1</code>&nbsp;。</li>
	<li>农场组都是 <strong>矩形</strong>&nbsp;的形状。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

判断是否为矩形左上角，需要满足三个条件：

-   元素值为 1；
-   左边是边界或者是 0；
-   上边是边界或者是 0。

然后遍历找到矩形的右边界和下边界。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findFarmland(self, land: List[List[int]]) -> List[List[int]]:
        m, n = len(land), len(land[0])
        ans = []
        for i in range(m):
            for j in range(n):
                if (
                    land[i][j] == 0
                    or (j > 0 and land[i][j - 1] == 1)
                    or (i > 0 and land[i - 1][j] == 1)
                ):
                    continue
                x, y = i, j
                while x + 1 < m and land[x + 1][j] == 1:
                    x += 1
                while y + 1 < n and land[x][y + 1] == 1:
                    y += 1
                ans.append([i, j, x, y])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[][] findFarmland(int[][] land) {
        List<int[]> ans = new ArrayList<>();
        int m = land.length;
        int n = land[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (land[i][j] == 0 || (j > 0 && land[i][j - 1] == 1)
                    || (i > 0 && land[i - 1][j] == 1)) {
                    continue;
                }
                int x = i;
                int y = j;
                for (; x + 1 < m && land[x + 1][j] == 1; ++x)
                    ;
                for (; y + 1 < n && land[x][y + 1] == 1; ++y)
                    ;
                ans.add(new int[] {i, j, x, y});
            }
        }
        return ans.toArray(new int[ans.size()][4]);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> findFarmland(vector<vector<int>>& land) {
        vector<vector<int>> ans;
        int m = land.size();
        int n = land[0].size();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (land[i][j] == 0 || (j > 0 && land[i][j - 1] == 1) || (i > 0 && land[i - 1][j] == 1)) continue;
                int x = i;
                int y = j;
                for (; x + 1 < m && land[x + 1][j] == 1; ++x)
                    ;
                for (; y + 1 < n && land[x][y + 1] == 1; ++y)
                    ;
                ans.push_back({i, j, x, y});
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func findFarmland(land [][]int) [][]int {
	m, n := len(land), len(land[0])
	var ans [][]int
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if land[i][j] == 0 || (j > 0 && land[i][j-1] == 1) || (i > 0 && land[i-1][j] == 1) {
				continue
			}
			x, y := i, j
			for ; x+1 < m && land[x+1][j] == 1; x++ {
			}
			for ; y+1 < n && land[x][y+1] == 1; y++ {
			}
			ans = append(ans, []int{i, j, x, y})
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
