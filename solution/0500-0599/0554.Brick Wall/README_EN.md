---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0554.Brick%20Wall/README_EN.md
tags:
    - Array
    - Hash Table
---

<!-- problem:start -->

# [554. Brick Wall](https://leetcode.com/problems/brick-wall)

[中文文档](/solution/0500-0599/0554.Brick%20Wall/README.md)

## Description

<!-- description:start -->

<p>There is a rectangular brick wall in front of you with <code>n</code> rows of bricks. The <code>i<sup>th</sup></code> row has some number of bricks each of the same height (i.e., one unit) but they can be of different widths. The total width of each row is the same.</p>

<p>Draw a vertical line from the top to the bottom and cross the least bricks. If your line goes through the edge of a brick, then the brick is not considered as crossed. You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.</p>

<p>Given the 2D array <code>wall</code> that contains the information about the wall, return <em>the minimum number of crossed bricks after drawing such a vertical line</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0554.Brick%20Wall/images/a.png" style="width: 400px; height: 384px;" />
<pre>
<strong>Input:</strong> wall = [[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> wall = [[1],[1],[1]]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == wall.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= wall[i].length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= sum(wall[i].length) &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>sum(wall[i])</code> is the same for each row <code>i</code>.</li>
	<li><code>1 &lt;= wall[i][j] &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Prefix Sum

We can use a hash table $\textit{cnt}$ to record the prefix sum of each row except for the last brick. The key is the value of the prefix sum, and the value is the number of times the prefix sum appears.

Traverse each row, and for each brick in the current row, add it to the current prefix sum and update $\textit{cnt}$.

Finally, we traverse $\textit{cnt}$ to find the prefix sum that appears the most times, which represents the situation where the least number of bricks are crossed. The final answer is the number of rows in the brick wall minus the number of bricks crossed.

The time complexity is $O(m \times n)$, and the space complexity is $O(n)$. Here, $m$ and $n$ are the number of rows and the number of bricks in the brick wall, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def leastBricks(self, wall: List[List[int]]) -> int:
        cnt = Counter()
        for row in wall:
            s = 0
            for x in row[:-1]:
                s += x
                cnt[s] += 1
        return len(wall) - max(cnt.values(), default=0)
```

#### Java

```java
class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (var row : wall) {
            int s = 0;
            for (int i = 0; i + 1 < row.size(); ++i) {
                s += row.get(i);
                cnt.merge(s, 1, Integer::sum);
            }
        }
        int mx = 0;
        for (var x : cnt.values()) {
            mx = Math.max(mx, x);
        }
        return wall.size() - mx;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int leastBricks(vector<vector<int>>& wall) {
        unordered_map<int, int> cnt;
        for (const auto& row : wall) {
            int s = 0;
            for (int i = 0; i + 1 < row.size(); ++i) {
                s += row[i];
                cnt[s]++;
            }
        }
        int mx = 0;
        for (const auto& [_, x] : cnt) {
            mx = max(mx, x);
        }
        return wall.size() - mx;
    }
};
```

#### Go

```go
func leastBricks(wall [][]int) int {
	cnt := map[int]int{}
	for _, row := range wall {
		s := 0
		for _, x := range row[:len(row)-1] {
			s += x
			cnt[s]++
		}
	}
	mx := 0
	for _, x := range cnt {
		mx = max(mx, x)
	}
	return len(wall) - mx
}
```

#### TypeScript

```ts
function leastBricks(wall: number[][]): number {
    const cnt: Map<number, number> = new Map();
    for (const row of wall) {
        let s = 0;
        for (let i = 0; i + 1 < row.length; ++i) {
            s += row[i];
            cnt.set(s, (cnt.get(s) || 0) + 1);
        }
    }
    const mx = Math.max(...cnt.values(), 0);
    return wall.length - mx;
}
```

#### JavaScript

```js
/**
 * @param {number[][]} wall
 * @return {number}
 */
var leastBricks = function (wall) {
    const cnt = new Map();
    for (const row of wall) {
        let s = 0;
        for (let i = 0; i + 1 < row.length; ++i) {
            s += row[i];
            cnt.set(s, (cnt.get(s) || 0) + 1);
        }
    }
    const mx = Math.max(...cnt.values(), 0);
    return wall.length - mx;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
