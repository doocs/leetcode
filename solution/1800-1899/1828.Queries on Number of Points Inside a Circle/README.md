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

**方法一：枚举**

枚举所有的圆点 $(x, y, r)$，对于每个圆点，计算在圆内的点的个数，即可得到答案。

时间复杂度 $O(m \times n)$，其中 $m$ 和 $n$ 分别为数组 `queries` 的长度和 `points` 的长度。忽略答案的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countPoints(
        self, points: List[List[int]], queries: List[List[int]]
    ) -> List[int]:
        ans = []
        for x, y, r in queries:
            cnt = 0
            for i, j in points:
                dx, dy = i - x, j - y
                cnt += dx * dx + dy * dy <= r * r
            ans.append(cnt)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] countPoints(int[][] points, int[][] queries) {
        int m = queries.length;
        int[] ans = new int[m];
        for (int k = 0; k < m; ++k) {
            int x = queries[k][0], y = queries[k][1], r = queries[k][2];
            for (var p : points) {
                int i = p[0], j = p[1];
                int dx = i - x, dy = j - y;
                if (dx * dx + dy * dy <= r * r) {
                    ++ans[k];
                }
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
    vector<int> countPoints(vector<vector<int>>& points, vector<vector<int>>& queries) {
        vector<int> ans;
        for (auto& q : queries) {
            int x = q[0], y = q[1], r = q[2];
            int cnt = 0;
            for (auto& p : points) {
                int i = p[0], j = p[1];
                int dx = i - x, dy = j - y;
                cnt += dx * dx + dy * dy <= r * r;
            }
            ans.emplace_back(cnt);
        }
        return ans;
    }
};
```

### **Go**

```go
func countPoints(points [][]int, queries [][]int) (ans []int) {
	for _, q := range queries {
		x, y, r := q[0], q[1], q[2]
		cnt := 0
		for _, p := range points {
			i, j := p[0], p[1]
			dx, dy := i-x, j-y
			if dx*dx+dy*dy <= r*r {
				cnt++
			}
		}
		ans = append(ans, cnt)
	}
	return
}
```

### **TypeScript**

```ts
function countPoints(points: number[][], queries: number[][]): number[] {
    return queries.map(([cx, cy, r]) => {
        let res = 0;
        for (const [px, py] of points) {
            if (Math.sqrt((cx - px) ** 2 + (cy - py) ** 2) <= r) {
                res++;
            }
        }
        return res;
    });
}
```

### **Rust**

```rust
impl Solution {
    pub fn count_points(points: Vec<Vec<i32>>, queries: Vec<Vec<i32>>) -> Vec<i32> {
        queries
            .iter()
            .map(|v| {
                let cx = v[0];
                let cy = v[1];
                let r = v[2].pow(2);
                let mut count = 0;
                for p in points.iter() {
                    if ((p[0] - cx).pow(2) + (p[1] - cy).pow(2)) <= r {
                        count += 1;
                    }
                }
                count
            })
            .collect()
    }
}
```

### **C**

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int *countPoints(int **points, int pointsSize, int *pointsColSize, int **queries, int queriesSize, int *queriesColSize,
                 int *returnSize) {
    int *ans = malloc(sizeof(int) * queriesSize);
    for (int i = 0; i < queriesSize; i++) {
        int cx = queries[i][0];
        int cy = queries[i][1];
        int r = queries[i][2];
        int count = 0;
        for (int j = 0; j < pointsSize; j++) {
            if (sqrt(pow(points[j][0] - cx, 2) + pow(points[j][1] - cy, 2)) <= r) {
                count++;
            }
        }
        ans[i] = count;
    }
    *returnSize = queriesSize;
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
