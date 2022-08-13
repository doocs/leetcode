# [2285. 道路的最大总重要性](https://leetcode.cn/problems/maximum-total-importance-of-roads)

[English Version](/solution/2200-2299/2285.Maximum%20Total%20Importance%20of%20Roads/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>n</code>&nbsp;，表示一个国家里的城市数目。城市编号为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;。</p>

<p>给你一个二维整数数组&nbsp;<code>roads</code>&nbsp;，其中&nbsp;<code>roads[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;表示城市&nbsp;<code>a<sub>i</sub></code>&nbsp;和&nbsp;<code>b<sub>i</sub></code>&nbsp;之间有一条&nbsp;<strong>双向</strong>&nbsp;道路。</p>

<p>你需要给每个城市安排一个从 <code>1</code>&nbsp;到 <code>n</code>&nbsp;之间的整数值，且每个值只能被使用 <strong>一次</strong>&nbsp;。道路的 <strong>重要性</strong>&nbsp;定义为这条道路连接的两座城市数值 <strong>之和</strong>&nbsp;。</p>

<p>请你返回在最优安排下，<strong>所有道路重要性</strong> 之和 <strong>最大</strong>&nbsp;为多少。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2285.Maximum%20Total%20Importance%20of%20Roads/images/ex1drawio.png" style="width: 290px; height: 215px;"></p>

<pre><b>输入：</b>n = 5, roads = [[0,1],[1,2],[2,3],[0,2],[1,3],[2,4]]
<b>输出：</b>43
<b>解释：</b>上图展示了国家图和每个城市被安排的值 [2,4,5,3,1] 。
- 道路 (0,1) 重要性为 2 + 4 = 6 。
- 道路 (1,2) 重要性为 4 + 5 = 9 。
- 道路 (2,3) 重要性为 5 + 3 = 8 。
- 道路 (0,2) 重要性为 2 + 5 = 7 。
- 道路 (1,3) 重要性为 4 + 3 = 7 。
- 道路 (2,4) 重要性为 5 + 1 = 6 。
所有道路重要性之和为 6 + 9 + 8 + 7 + 7 + 6 = 43 。
可以证明，重要性之和不可能超过 43 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2285.Maximum%20Total%20Importance%20of%20Roads/images/ex2drawio.png" style="width: 281px; height: 151px;"></p>

<pre><b>输入：</b>n = 5, roads = [[0,3],[2,4],[1,3]]
<b>输出：</b>20
<b>解释：</b>上图展示了国家图和每个城市被安排的值 [4,3,2,5,1] 。
- 道路 (0,3) 重要性为 4 + 5 = 9 。
- 道路 (2,4) 重要性为 2 + 1 = 3 。
- 道路 (1,3) 重要性为 3 + 5 = 8 。
所有道路重要性之和为 9 + 3 + 8 = 20 。
可以证明，重要性之和不可能超过 20 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= roads.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>roads[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li>没有重复道路。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序**

考虑每个城市对所有道路的总重要性的贡献度，按贡献度从小到大排序，为城市依次分配 $[1, 2, ..., n]$。

时间复杂度 $O(nlogn)$，其中 $n$ 表示城市数目。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumImportance(self, n: int, roads: List[List[int]]) -> int:
        deg = [0] * n
        for a, b in roads:
            deg[a] += 1
            deg[b] += 1
        deg.sort()
        return sum(i * v for i, v in enumerate(deg, 1))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long maximumImportance(int n, int[][] roads) {
        int[] deg = new int[n];
        for (int[] r : roads) {
            ++deg[r[0]];
            ++deg[r[1]];
        }
        Arrays.sort(deg);
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += (long) (i + 1) * deg[i];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long maximumImportance(int n, vector<vector<int>>& roads) {
        vector<int> deg(n);
        for (auto& r : roads) {
            ++deg[r[0]];
            ++deg[r[1]];
        }
        sort(deg.begin(), deg.end());
        long long ans = 0;
        for (int i = 0; i < n; ++i) ans += 1ll * (i + 1) * deg[i];
        return ans;
    }
};
```

### **Go**

```go
func maximumImportance(n int, roads [][]int) int64 {
	deg := make([]int, n)
	for _, r := range roads {
		deg[r[0]]++
		deg[r[1]]++
	}
	sort.Ints(deg)
	var ans int64
	for i := 0; i < n; i++ {
		ans += int64((i + 1) * deg[i])
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
