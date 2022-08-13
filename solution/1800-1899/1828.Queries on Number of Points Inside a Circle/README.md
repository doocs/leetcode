# [1828. 统计一个圆中点的数目](https://leetcode.cn/problems/queries-on-number-of-points-inside-a-circle)

[English Version](/solution/1800-1899/1828.Queries%20on%20Number%20of%20Points%20Inside%20a%20Circle/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个数组 <code>points</code> ，其中 <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> ，表示第 <code>i</code> 个点在二维平面上的坐标。多个点可能会有 <strong>相同</strong> 的坐标。</p>

<p>同时给你一个数组 <code>queries</code> ，其中 <code>queries[j] = [x<sub>j</sub>, y<sub>j</sub>, r<sub>j</sub>]</code> ，表示一个圆心在 <code>(x<sub>j</sub>, y<sub>j</sub>)</code> 且半径为 <code>r<sub>j</sub></code><sub> </sub>的圆。</p>

<p>对于每一个查询 <code>queries[j]</code> ，计算在第 <code>j</code> 个圆 <strong>内</strong> 点的数目。如果一个点在圆的 <strong>边界上</strong> ，我们同样认为它在圆 <strong>内</strong> 。</p>

<p>请你返回一个数组<em> </em><code>answer</code> ，其中<em> </em><code>answer[j]</code>是第 <code>j</code> 个查询的答案。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1828.Queries%20on%20Number%20of%20Points%20Inside%20a%20Circle/images/chrome_2021-03-25_22-34-16.png" style="width: 500px; height: 418px;">
<pre><b>输入：</b>points = [[1,3],[3,3],[5,3],[2,2]], queries = [[2,3,1],[4,3,1],[1,1,2]]
<b>输出：</b>[3,2,2]
<b>解释：</b>所有的点和圆如上图所示。
queries[0] 是绿色的圆，queries[1] 是红色的圆，queries[2] 是蓝色的圆。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1828.Queries%20on%20Number%20of%20Points%20Inside%20a%20Circle/images/chrome_2021-03-25_22-42-07.png" style="width: 500px; height: 390px;">
<pre><b>输入：</b>points = [[1,1],[2,2],[3,3],[4,4],[5,5]], queries = [[1,2,2],[2,2,2],[4,3,2],[4,3,3]]
<b>输出：</b>[2,3,2,4]
<b>解释：</b>所有的点和圆如上图所示。
queries[0] 是绿色的圆，queries[1] 是红色的圆，queries[2] 是蓝色的圆，queries[3] 是紫色的圆。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= points.length &lt;= 500</code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>0 &lt;= x<sub>​​​​​​i</sub>, y<sub>​​​​​​i</sub> &lt;= 500</code></li>
	<li><code>1 &lt;= queries.length &lt;= 500</code></li>
	<li><code>queries[j].length == 3</code></li>
	<li><code>0 &lt;= x<sub>j</sub>, y<sub>j</sub> &lt;= 500</code></li>
	<li><code>1 &lt;= r<sub>j</sub> &lt;= 500</code></li>
	<li>所有的坐标都是整数。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

计算每个点与每个圆的圆心之间的距离，若距离小于此圆的半径，说明该点在圆中。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countPoints(
        self, points: List[List[int]], queries: List[List[int]]
    ) -> List[int]:
        ans = []
        for x0, y0, r in queries:
            count = 0
            for x, y in points:
                dx, dy = x - x0, y - y0
                if dx * dx + dy * dy <= r * r:
                    count += 1
            ans.append(count)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] countPoints(int[][] points, int[][] queries) {
        int[] ans = new int[queries.length];
        int i = 0;
        for (int[] query : queries) {
            int x0 = query[0], y0 = query[1], r = query[2];
            for (int[] point : points) {
                int x = point[0], y = point[1];
                int dx = x - x0, dy = y - y0;
                if (dx * dx + dy * dy <= r * r) {
                    ++ans[i];
                }
            }
            ++i;
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function countPoints(points: number[][], queries: number[][]): number[] {
    let ans = [];
    for (let [cx, cy, r] of queries) {
        let square = r ** 2;
        let count = 0;
        for (let [px, py] of points) {
            if ((px - cx) ** 2 + (py - cy) ** 2 <= square) {
                ++count;
            }
        }
        ans.push(count);
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> countPoints(vector<vector<int>>& points, vector<vector<int>>& queries) {
        vector<int> ans;
        for (auto& query : queries) {
            int x0 = query[0], y0 = query[1], r = query[2];
            int count = 0;
            for (auto& point : points) {
                int x = point[0], y = point[1];
                int dx = x - x0, dy = y - y0;
                if (dx * dx + dy * dy <= r * r) {
                    ++count;
                }
            }
            ans.push_back(count);
        }
        return ans;
    }
};
```

### **Go**

```go
func countPoints(points [][]int, queries [][]int) []int {
	ans := make([]int, len(queries))
	for i, query := range queries {
		x0, y0, r := query[0], query[1], query[2]
		for _, point := range points {
			x, y := point[0], point[1]
			dx, dy := x-x0, y-y0
			if dx*dx+dy*dy <= r*r {
				ans[i]++
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
