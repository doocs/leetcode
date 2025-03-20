---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1057.Campus%20Bikes/README.md
tags:
    - 数组
    - 排序
    - 堆（优先队列）
---

<!-- problem:start -->

# [1057. 校园自行车分配 🔒](https://leetcode.cn/problems/campus-bikes)

[English Version](/solution/1000-1099/1057.Campus%20Bikes/README_EN.md)

## 题目描述

<!-- description:start -->

<p>在 X-Y 平面上表示的校园中，有 <code>n</code> 名工人和 <code>m</code> 辆自行车，其中 <code>n &lt;= m</code>。</p>

<p>给定一个长度为 <code>n</code> 的数组&nbsp;<code>workers</code>&nbsp;，其中 <code>worker [i] = [xi, yi]</code>&nbsp;表示第 <code>i</code>&nbsp;个工人的位置。你也得到一个长度为 <code>m</code> 的自行车数组 <code>bikers</code> ，其中 <code>bikes[j] = [x<sub>j</sub>, y<sub>j</sub>]</code>&nbsp;是第 <code>j</code> 辆自行车的位置。所有给定的位置都是 <strong>唯一</strong> 的。</p>

<p>我们需要为每位工人分配一辆自行车。在所有可用的自行车和工人中，我们选取彼此之间 <strong>曼哈顿距离</strong> 最短的工人自行车对&nbsp;<code>(worker<sub>i</sub>, bike<sub>j</sub>)</code>&nbsp;，并将其中的自行车分配給工人。</p>

<p>如果有多个&nbsp;<code>(worker<sub>i</sub>, bike<sub>j</sub>)</code> 对之间的 <strong>曼哈顿距离</strong> 相同，那么我们选择 <strong>工人索引最小</strong> 的那对。类似地，如果有多种不同的分配方法，则选择 <strong>自行车索引最小</strong> 的一对。不断重复这一过程，直到所有工人都分配到自行车为止。</p>

<p>返回长度为 <code>n</code> 的向量 <code>answer</code>，其中 <code>answer[i]</code>&nbsp;是第 <code>i</code>&nbsp;位工人分配到的自行车的索引（<strong>从 0 开始</strong>）。</p>

<p>给定两点&nbsp;<code>p1</code>&nbsp;和&nbsp;<code>p2</code>&nbsp;之间的 <strong>曼哈顿距离</strong> 为&nbsp;<code>Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1057.Campus%20Bikes/images/1261_example_1_v2.png" /></p>

<pre>
<strong>输入：</strong>workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
<strong>输出：</strong>[1,0]
<strong>解释：</strong>工人 1 分配到自行车 0，因为他们最接近且不存在冲突，工人 0 分配到自行车 1 。所以输出是 [1,0]。
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1057.Campus%20Bikes/images/1261_example_2_v2.png" /></p>

<pre>
<strong>输入：</strong>workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
<strong>输出：</strong>[0,2,1]
<strong>解释：</strong>工人 0 首先分配到自行车 0 。工人 1 和工人 2 与自行车 2 距离相同，因此工人 1 分配到自行车 2，工人 2 将分配到自行车 1 。因此输出为 [0,2,1]。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == workers.length</code></li>
	<li><code>m == bikes.length</code></li>
	<li><code>1 &lt;= n &lt;= m &lt;= 1000</code></li>
	<li><code>workers[i].length == bikes[j].length == 2</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub>&nbsp;&lt; 1000</code></li>
	<li><code>0 &lt;= x<sub>j</sub>, y<sub>j</sub>&nbsp;&lt; 1000</code></li>
	<li>所有工人和自行车的位置都<strong>不相同</strong></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序

先计算每个工人和每个自行车之间的曼哈顿距离，然后按照曼哈顿距离从小到大排序，遍历排序后的数组，如果当前工人和自行车都未被分配，则分配给当前工人和自行车。

时间复杂度 $O(n\times m\times \log (n\times m))$。其中 $n$ 和 $m$ 分别为工人和自行车的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def assignBikes(
        self, workers: List[List[int]], bikes: List[List[int]]
    ) -> List[int]:
        n, m = len(workers), len(bikes)
        arr = []
        for i, j in product(range(n), range(m)):
            dist = abs(workers[i][0] - bikes[j][0]) + abs(workers[i][1] - bikes[j][1])
            arr.append((dist, i, j))
        arr.sort()
        vis1 = [False] * n
        vis2 = [False] * m
        ans = [0] * n
        for _, i, j in arr:
            if not vis1[i] and not vis2[j]:
                vis1[i] = vis2[j] = True
                ans[i] = j
        return ans
```

#### Java

```java
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length, m = bikes.length;
        int[][] arr = new int[m * n][3];
        for (int i = 0, k = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                int dist
                    = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
                arr[k++] = new int[] {dist, i, j};
            }
        }
        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            return a[2] - b[2];
        });
        boolean[] vis1 = new boolean[n];
        boolean[] vis2 = new boolean[m];
        int[] ans = new int[n];
        for (var e : arr) {
            int i = e[1], j = e[2];
            if (!vis1[i] && !vis2[j]) {
                vis1[i] = true;
                vis2[j] = true;
                ans[i] = j;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> assignBikes(vector<vector<int>>& workers, vector<vector<int>>& bikes) {
        int n = workers.size(), m = bikes.size();
        vector<tuple<int, int, int>> arr(n * m);
        for (int i = 0, k = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                int dist = abs(workers[i][0] - bikes[j][0]) + abs(workers[i][1] - bikes[j][1]);
                arr[k++] = {dist, i, j};
            }
        }
        sort(arr.begin(), arr.end());
        vector<bool> vis1(n), vis2(m);
        vector<int> ans(n);
        for (auto& [_, i, j] : arr) {
            if (!vis1[i] && !vis2[j]) {
                vis1[i] = true;
                vis2[j] = true;
                ans[i] = j;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func assignBikes(workers [][]int, bikes [][]int) []int {
	n, m := len(workers), len(bikes)
	type tuple struct{ d, i, j int }
	arr := make([]tuple, n*m)
	for i, k := 0, 0; i < n; i++ {
		for j := 0; j < m; j++ {
			d := abs(workers[i][0]-bikes[j][0]) + abs(workers[i][1]-bikes[j][1])
			arr[k] = tuple{d, i, j}
			k++
		}
	}
	sort.Slice(arr, func(i, j int) bool {
		if arr[i].d != arr[j].d {
			return arr[i].d < arr[j].d
		}
		if arr[i].i != arr[j].i {
			return arr[i].i < arr[j].i
		}
		return arr[i].j < arr[j].j
	})
	vis1, vis2 := make([]bool, n), make([]bool, m)
	ans := make([]int, n)
	for _, e := range arr {
		i, j := e.i, e.j
		if !vis1[i] && !vis2[j] {
			vis1[i], vis2[j] = true, true
			ans[i] = j
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
