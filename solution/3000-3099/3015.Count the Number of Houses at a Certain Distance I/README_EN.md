---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3015.Count%20the%20Number%20of%20Houses%20at%20a%20Certain%20Distance%20I/README_EN.md
rating: 1657
source: Weekly Contest 381 Q2
tags:
    - Breadth-First Search
    - Graph
    - Prefix Sum
---

<!-- problem:start -->

# [3015. Count the Number of Houses at a Certain Distance I](https://leetcode.com/problems/count-the-number-of-houses-at-a-certain-distance-i)

[中文文档](/solution/3000-3099/3015.Count%20the%20Number%20of%20Houses%20at%20a%20Certain%20Distance%20I/README.md)

## Description

<!-- description:start -->

<p>You are given three <strong>positive</strong> integers <code>n</code>, <code>x</code>, and <code>y</code>.</p>

<p>In a city, there exist houses numbered <code>1</code> to <code>n</code> connected by <code>n</code> streets. There is a street connecting the house numbered <code>i</code> with the house numbered <code>i + 1</code> for all <code>1 &lt;= i &lt;= n - 1</code> . An additional street connects the house numbered <code>x</code> with the house numbered <code>y</code>.</p>

<p>For each <code>k</code>, such that <code>1 &lt;= k &lt;= n</code>, you need to find the number of <strong>pairs of houses</strong> <code>(house<sub>1</sub>, house<sub>2</sub>)</code> such that the <strong>minimum</strong> number of streets that need to be traveled to reach <code>house<sub>2</sub></code> from <code>house<sub>1</sub></code> is <code>k</code>.</p>

<p>Return <em>a <strong>1-indexed</strong> array </em><code>result</code><em> of length </em><code>n</code><em> where </em><code>result[k]</code><em> represents the <strong>total</strong> number of pairs of houses such that the <strong>minimum</strong> streets required to reach one house from the other is </em><code>k</code>.</p>

<p><strong>Note</strong> that <code>x</code> and <code>y</code> can be <strong>equal</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3015.Count%20the%20Number%20of%20Houses%20at%20a%20Certain%20Distance%20I/images/example2.png" style="width: 474px; height: 197px;" />
<pre>
<strong>Input:</strong> n = 3, x = 1, y = 3
<strong>Output:</strong> [6,0,0]
<strong>Explanation:</strong> Let&#39;s look at each pair of houses:
- For the pair (1, 2), we can go from house 1 to house 2 directly.
- For the pair (2, 1), we can go from house 2 to house 1 directly.
- For the pair (1, 3), we can go from house 1 to house 3 directly.
- For the pair (3, 1), we can go from house 3 to house 1 directly.
- For the pair (2, 3), we can go from house 2 to house 3 directly.
- For the pair (3, 2), we can go from house 3 to house 2 directly.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3015.Count%20the%20Number%20of%20Houses%20at%20a%20Certain%20Distance%20I/images/example3.png" style="width: 668px; height: 174px;" />
<pre>
<strong>Input:</strong> n = 5, x = 2, y = 4
<strong>Output:</strong> [10,8,2,0,0]
<strong>Explanation:</strong> For each distance k the pairs are:
- For k == 1, the pairs are (1, 2), (2, 1), (2, 3), (3, 2), (2, 4), (4, 2), (3, 4), (4, 3), (4, 5), and (5, 4).
- For k == 2, the pairs are (1, 3), (3, 1), (1, 4), (4, 1), (2, 5), (5, 2), (3, 5), and (5, 3).
- For k == 3, the pairs are (1, 5), and (5, 1).
- For k == 4 and k == 5, there are no pairs.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3015.Count%20the%20Number%20of%20Houses%20at%20a%20Certain%20Distance%20I/images/example5.png" style="width: 544px; height: 130px;" />
<pre>
<strong>Input:</strong> n = 4, x = 1, y = 1
<strong>Output:</strong> [6,4,2,0]
<strong>Explanation:</strong> For each distance k the pairs are:
- For k == 1, the pairs are (1, 2), (2, 1), (2, 3), (3, 2), (3, 4), and (4, 3).
- For k == 2, the pairs are (1, 3), (3, 1), (2, 4), and (4, 2).
- For k == 3, the pairs are (1, 4), and (4, 1).
- For k == 4, there are no pairs.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= x, y &lt;= n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We can enumerate each pair of points $(i, j)$. The shortest distance from $i$ to $j$ is $min(|i - j|, |i - x| + 1 + |j - y|, |i - y| + 1 + |j - x|)$. We add $2$ to the count of this distance because both $(i, j)$ and $(j, i)$ are valid pairs of points.

The time complexity is $O(n^2)$, where $n$ is the $n$ given in the problem. Ignoring the space consumption of the answer array, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countOfPairs(self, n: int, x: int, y: int) -> List[int]:
        x, y = x - 1, y - 1
        ans = [0] * n
        for i in range(n):
            for j in range(i + 1, n):
                a = j - i
                b = abs(i - x) + 1 + abs(j - y)
                c = abs(i - y) + 1 + abs(j - x)
                ans[min(a, b, c) - 1] += 2
        return ans
```

#### Java

```java
class Solution {
    public int[] countOfPairs(int n, int x, int y) {
        int[] ans = new int[n];
        x--;
        y--;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int a = j - i;
                int b = Math.abs(i - x) + 1 + Math.abs(j - y);
                int c = Math.abs(i - y) + 1 + Math.abs(j - x);
                ans[Math.min(a, Math.min(b, c)) - 1] += 2;
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
    vector<int> countOfPairs(int n, int x, int y) {
        vector<int> ans(n);
        x--;
        y--;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int a = j - i;
                int b = abs(x - i) + abs(y - j) + 1;
                int c = abs(y - i) + abs(x - j) + 1;
                ans[min({a, b, c}) - 1] += 2;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countOfPairs(n int, x int, y int) []int {
	ans := make([]int, n)
	x, y = x-1, y-1
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			a := j - i
			b := abs(x-i) + abs(y-j) + 1
			c := abs(x-j) + abs(y-i) + 1
			ans[min(a, min(b, c))-1] += 2
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

#### TypeScript

```ts
function countOfPairs(n: number, x: number, y: number): number[] {
    const ans: number[] = Array(n).fill(0);
    x--;
    y--;
    for (let i = 0; i < n; ++i) {
        for (let j = i + 1; j < n; ++j) {
            const a = j - i;
            const b = Math.abs(x - i) + Math.abs(y - j) + 1;
            const c = Math.abs(y - i) + Math.abs(x - j) + 1;
            ans[Math.min(a, b, c) - 1] += 2;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
