# [1828. Queries on Number of Points Inside a Circle](https://leetcode.com/problems/queries-on-number-of-points-inside-a-circle)

[中文文档](/solution/1800-1899/1828.Queries%20on%20Number%20of%20Points%20Inside%20a%20Circle/README.md)

## Description

<p>You are given an array <code>points</code> where <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> is the coordinates of the <code>i<sup>th</sup></code> point on a 2D plane. Multiple points can have the <strong>same</strong> coordinates.</p>

<p>You are also given an array <code>queries</code> where <code>queries[j] = [x<sub>j</sub>, y<sub>j</sub>, r<sub>j</sub>]</code> describes a circle centered at <code>(x<sub>j</sub>, y<sub>j</sub>)</code> with a radius of <code>r<sub>j</sub></code>.</p>

<p>For each query <code>queries[j]</code>, compute the number of points <strong>inside</strong> the <code>j<sup>th</sup></code> circle. Points <strong>on the border</strong> of the circle are considered <strong>inside</strong>.</p>

<p>Return <em>an array </em><code>answer</code><em>, where </em><code>answer[j]</code><em> is the answer to the </em><code>j<sup>th</sup></code><em> query</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1828.Queries%20on%20Number%20of%20Points%20Inside%20a%20Circle/images/chrome_2021-03-25_22-34-16.png" style="width: 500px; height: 418px;" />
<pre>
<strong>Input:</strong> points = [[1,3],[3,3],[5,3],[2,2]], queries = [[2,3,1],[4,3,1],[1,1,2]]
<strong>Output:</strong> [3,2,2]
<b>Explanation: </b>The points and circles are shown above.
queries[0] is the green circle, queries[1] is the red circle, and queries[2] is the blue circle.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1828.Queries%20on%20Number%20of%20Points%20Inside%20a%20Circle/images/chrome_2021-03-25_22-42-07.png" style="width: 500px; height: 390px;" />
<pre>
<strong>Input:</strong> points = [[1,1],[2,2],[3,3],[4,4],[5,5]], queries = [[1,2,2],[2,2,2],[4,3,2],[4,3,3]]
<strong>Output:</strong> [2,3,2,4]
<b>Explanation: </b>The points and circles are shown above.
queries[0] is green, queries[1] is red, queries[2] is blue, and queries[3] is purple.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= points.length &lt;= 500</code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>0 &lt;= x<sub>​​​​​​i</sub>, y<sub>​​​​​​i</sub> &lt;= 500</code></li>
	<li><code>1 &lt;= queries.length &lt;= 500</code></li>
	<li><code>queries[j].length == 3</code></li>
	<li><code>0 &lt;= x<sub>j</sub>, y<sub>j</sub> &lt;= 500</code></li>
	<li><code>1 &lt;= r<sub>j</sub> &lt;= 500</code></li>
	<li>All coordinates are integers.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you find the answer for each query in better complexity than <code>O(n)</code>?</p>

## Solutions

<!-- tabs:start -->

### **Python3**

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
