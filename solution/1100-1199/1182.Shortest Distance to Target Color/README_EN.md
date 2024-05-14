---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1182.Shortest%20Distance%20to%20Target%20Color/README_EN.md
rating: 1626
tags:
    - Array
    - Binary Search
    - Dynamic Programming
---

# [1182. Shortest Distance to Target Color 🔒](https://leetcode.com/problems/shortest-distance-to-target-color)

[中文文档](/solution/1100-1199/1182.Shortest%20Distance%20to%20Target%20Color/README.md)

## Description

<p>You are given an array <code>colors</code>, in which there are three colors: <code>1</code>, <code>2</code> and&nbsp;<code>3</code>.</p>

<p>You are also given some queries. Each query consists of two integers <code>i</code>&nbsp;and <code>c</code>, return&nbsp;the shortest distance between the given index&nbsp;<code>i</code> and the target color <code>c</code>. If there is no solution return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
<strong>Output:</strong> [3,0,3]
<strong>Explanation: </strong>
The nearest 3 from index 1 is at index 4 (3 steps away).
The nearest 2 from index 2 is at index 2 itself (0 steps away).
The nearest 1 from index 6 is at index 3 (3 steps away).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> colors = [1,2], queries = [[0,3]]
<strong>Output:</strong> [-1]
<strong>Explanation: </strong>There is no 3 in the array.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= colors.length &lt;= 5*10^4</code></li>
	<li><code>1 &lt;= colors[i] &lt;= 3</code></li>
	<li><code>1&nbsp;&lt;= queries.length &lt;= 5*10^4</code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 &lt;= queries[i][0] &lt;&nbsp;colors.length</code></li>
	<li><code>1 &lt;= queries[i][1] &lt;= 3</code></li>
</ul>

## Solutions

### Solution 1: Preprocessing

We can preprocess the distance from each position to the nearest color $1$, $2$, $3$ on the left, and the distance from each position to the nearest color $1$, $2$, $3$ on the right, and record them in the arrays $left$ and $right$. Initially, $left[0][0] = left[0][1] = left[0][2] = -\infty$, and $right[n][0] = right[n][1] = right[n][2] = \infty$, where $n$ is the length of the array `colors`.

Then for each query $(i, c)$, the minimum distance is $d = \min(i - left[i + 1][c - 1], right[i][c - 1] - i)$. If $d > n$, there is no solution, and the answer to this query is $-1$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array `colors`.

<!-- tabs:start -->

```python
class Solution:
    def shortestDistanceColor(
        self, colors: List[int], queries: List[List[int]]
    ) -> List[int]:
        n = len(colors)
        right = [[inf] * 3 for _ in range(n + 1)]
        for i in range(n - 1, -1, -1):
            for j in range(3):
                right[i][j] = right[i + 1][j]
            right[i][colors[i] - 1] = i
        left = [[-inf] * 3 for _ in range(n + 1)]
        for i, c in enumerate(colors, 1):
            for j in range(3):
                left[i][j] = left[i - 1][j]
            left[i][c - 1] = i - 1
        ans = []
        for i, c in queries:
            d = min(i - left[i + 1][c - 1], right[i][c - 1] - i)
            ans.append(-1 if d > n else d)
        return ans
```

```java
class Solution {
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        int n = colors.length;
        final int inf = 1 << 30;
        int[][] right = new int[n + 1][3];
        Arrays.fill(right[n], inf);
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j < 3; ++j) {
                right[i][j] = right[i + 1][j];
            }
            right[i][colors[i] - 1] = i;
        }
        int[][] left = new int[n + 1][3];
        Arrays.fill(left[0], -inf);
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < 3; ++j) {
                left[i][j] = left[i - 1][j];
            }
            left[i][colors[i - 1] - 1] = i - 1;
        }
        List<Integer> ans = new ArrayList<>();
        for (int[] q : queries) {
            int i = q[0], c = q[1] - 1;
            int d = Math.min(i - left[i + 1][c], right[i][c] - i);
            ans.add(d > n ? -1 : d);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> shortestDistanceColor(vector<int>& colors, vector<vector<int>>& queries) {
        int n = colors.size();
        int right[n + 1][3];
        const int inf = 1 << 30;
        fill(right[n], right[n] + 3, inf);
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j < 3; ++j) {
                right[i][j] = right[i + 1][j];
            }
            right[i][colors[i] - 1] = i;
        }
        int left[n + 1][3];
        fill(left[0], left[0] + 3, -inf);
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < 3; ++j) {
                left[i][j] = left[i - 1][j];
            }
            left[i][colors[i - 1] - 1] = i - 1;
        }
        vector<int> ans;
        for (auto& q : queries) {
            int i = q[0], c = q[1] - 1;
            int d = min(i - left[i + 1][c], right[i][c] - i);
            ans.push_back(d > n ? -1 : d);
        }
        return ans;
    }
};
```

```go
func shortestDistanceColor(colors []int, queries [][]int) (ans []int) {
	n := len(colors)
	const inf = 1 << 30
	right := make([][3]int, n+1)
	left := make([][3]int, n+1)
	right[n] = [3]int{inf, inf, inf}
	left[0] = [3]int{-inf, -inf, -inf}
	for i := n - 1; i >= 0; i-- {
		for j := 0; j < 3; j++ {
			right[i][j] = right[i+1][j]
		}
		right[i][colors[i]-1] = i
	}
	for i := 1; i <= n; i++ {
		for j := 0; j < 3; j++ {
			left[i][j] = left[i-1][j]
		}
		left[i][colors[i-1]-1] = i - 1
	}
	for _, q := range queries {
		i, c := q[0], q[1]-1
		d := min(i-left[i+1][c], right[i][c]-i)
		if d > n {
			d = -1
		}
		ans = append(ans, d)
	}
	return
}
```

```ts
function shortestDistanceColor(colors: number[], queries: number[][]): number[] {
    const n = colors.length;
    const inf = 1 << 30;
    const right: number[][] = Array(n + 1)
        .fill(0)
        .map(() => Array(3).fill(inf));
    const left: number[][] = Array(n + 1)
        .fill(0)
        .map(() => Array(3).fill(-inf));
    for (let i = n - 1; i >= 0; --i) {
        for (let j = 0; j < 3; ++j) {
            right[i][j] = right[i + 1][j];
        }
        right[i][colors[i] - 1] = i;
    }
    for (let i = 1; i <= n; ++i) {
        for (let j = 0; j < 3; ++j) {
            left[i][j] = left[i - 1][j];
        }
        left[i][colors[i - 1] - 1] = i - 1;
    }
    const ans: number[] = [];
    for (const [i, c] of queries) {
        const d = Math.min(i - left[i + 1][c - 1], right[i][c - 1] - i);
        ans.push(d > n ? -1 : d);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
