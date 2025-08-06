---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3363.Find%20the%20Maximum%20Number%20of%20Fruits%20Collected/README_EN.md
rating: 2404
source: Biweekly Contest 144 Q4
tags:
    - Array
    - Dynamic Programming
    - Matrix
---

<!-- problem:start -->

# [3363. Find the Maximum Number of Fruits Collected](https://leetcode.com/problems/find-the-maximum-number-of-fruits-collected)

[中文文档](/solution/3300-3399/3363.Find%20the%20Maximum%20Number%20of%20Fruits%20Collected/README.md)

## Description

<!-- description:start -->

<p>There is a game dungeon comprised of&nbsp;<code>n x n</code> rooms arranged in a grid.</p>

<p>You are given a 2D array <code>fruits</code> of size <code>n x n</code>, where <code>fruits[i][j]</code> represents the number of fruits in the room <code>(i, j)</code>. Three children will play in the game dungeon, with <strong>initial</strong> positions at the corner rooms <code>(0, 0)</code>, <code>(0, n - 1)</code>, and <code>(n - 1, 0)</code>.</p>

<p>The children will make <strong>exactly</strong> <code>n - 1</code> moves according to the following rules to reach the room <code>(n - 1, n - 1)</code>:</p>

<ul>
	<li>The child starting from <code>(0, 0)</code> must move from their current room <code>(i, j)</code> to one of the rooms <code>(i + 1, j + 1)</code>, <code>(i + 1, j)</code>, and <code>(i, j + 1)</code> if the target room exists.</li>
	<li>The child starting from <code>(0, n - 1)</code> must move from their current room <code>(i, j)</code> to one of the rooms <code>(i + 1, j - 1)</code>, <code>(i + 1, j)</code>, and <code>(i + 1, j + 1)</code> if the target room exists.</li>
	<li>The child starting from <code>(n - 1, 0)</code> must move from their current room <code>(i, j)</code> to one of the rooms <code>(i - 1, j + 1)</code>, <code>(i, j + 1)</code>, and <code>(i + 1, j + 1)</code> if the target room exists.</li>
</ul>

<p>When a child enters a room, they will collect all the fruits there. If two or more children enter the same room, only one child will collect the fruits, and the room will be emptied after they leave.</p>

<p>Return the <strong>maximum</strong> number of fruits the children can collect from the dungeon.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">fruits = [[1,2,3,4],[5,6,8,7],[9,10,11,12],[13,14,15,16]]</span></p>

<p><strong>Output:</strong> <span class="example-io">100</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3363.Find%20the%20Maximum%20Number%20of%20Fruits%20Collected/images/example_1.gif" style="width: 250px; height: 214px;" /></p>

<p>In this example:</p>

<ul>
	<li>The 1<sup>st</sup> child (green) moves on the path <code>(0,0) -&gt; (1,1) -&gt; (2,2) -&gt; (3, 3)</code>.</li>
	<li>The 2<sup>nd</sup> child (red) moves on the path <code>(0,3) -&gt; (1,2) -&gt; (2,3) -&gt; (3, 3)</code>.</li>
	<li>The 3<sup>rd</sup> child (blue) moves on the path <code>(3,0) -&gt; (3,1) -&gt; (3,2) -&gt; (3, 3)</code>.</li>
</ul>

<p>In total they collect <code>1 + 6 + 11 + 16 + 4 + 8 + 12 + 13 + 14 + 15 = 100</code> fruits.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">fruits = [[1,1],[1,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>In this example:</p>

<ul>
	<li>The 1<sup>st</sup> child moves on the path <code>(0,0) -&gt; (1,1)</code>.</li>
	<li>The 2<sup>nd</sup> child moves on the path <code>(0,1) -&gt; (1,1)</code>.</li>
	<li>The 3<sup>rd</sup> child moves on the path <code>(1,0) -&gt; (1,1)</code>.</li>
</ul>

<p>In total they collect <code>1 + 1 + 1 + 1 = 4</code> fruits.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == fruits.length == fruits[i].length &lt;= 1000</code></li>
	<li><code>0 &lt;= fruits[i][j] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

According to the problem description, for the child starting from $(0, 0)$ to reach $(n - 1, n - 1)$ in exactly $n - 1$ steps, they can only move through the rooms on the main diagonal $(i, i)$, where $i = 0, 1, \ldots, n - 1$. The child starting from $(0, n - 1)$ can only move through rooms above the main diagonal, while the child starting from $(n - 1, 0)$ can only move through rooms below the main diagonal. This means that except for reaching the destination at $(n - 1, n - 1)$, no other rooms will be visited by multiple children.

We can use dynamic programming to calculate the number of fruits that the children starting from $(0, n - 1)$ and $(n - 1, 0)$ can collect when reaching $(i, j)$. Define $f[i][j]$ as the number of fruits a child can collect when reaching $(i, j)$.

For the child starting from $(0, n - 1)$, the state transition equation is:

$$
f[i][j] = \max(f[i - 1][j], f[i - 1][j - 1], f[i - 1][j + 1]) + \text{fruits}[i][j]
$$

Note that $f[i - 1][j + 1]$ is only valid when $j + 1 < n$.

For the child starting from $(n - 1, 0)$, the state transition equation is:

$$
f[i][j] = \max(f[i][j - 1], f[i - 1][j - 1], f[i + 1][j - 1]) + \text{fruits}[i][j]
$$

Similarly, $f[i + 1][j - 1]$ is only valid when $i + 1 < n$.

Finally, the answer is $\sum_{i=0}^{n-1} \text{fruits}[i][i] + f[n-2][n-1] + f[n-1][n-2]$, which is the sum of fruits on the main diagonal plus the fruits that the two children can collect when reaching $(n - 2, n - 1)$ and $(n - 1, n - 2)$.

The time complexity is $O(n^2)$, and the space complexity is $O(n^2)$, where $n$ is the side length of the room grid.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxCollectedFruits(self, fruits: List[List[int]]) -> int:
        n = len(fruits)
        f = [[-inf] * n for _ in range(n)]
        f[0][n - 1] = fruits[0][n - 1]
        for i in range(1, n):
            for j in range(i + 1, n):
                f[i][j] = max(f[i - 1][j], f[i - 1][j - 1]) + fruits[i][j]
                if j + 1 < n:
                    f[i][j] = max(f[i][j], f[i - 1][j + 1] + fruits[i][j])
        f[n - 1][0] = fruits[n - 1][0]
        for j in range(1, n):
            for i in range(j + 1, n):
                f[i][j] = max(f[i][j - 1], f[i - 1][j - 1]) + fruits[i][j]
                if i + 1 < n:
                    f[i][j] = max(f[i][j], f[i + 1][j - 1] + fruits[i][j])
        return sum(fruits[i][i] for i in range(n)) + f[n - 2][n - 1] + f[n - 1][n - 2]
```

#### Java

```java
class Solution {
    public int maxCollectedFruits(int[][] fruits) {
        int n = fruits.length;
        final int inf = 1 << 29;
        int[][] f = new int[n][n];
        for (var row : f) {
            Arrays.fill(row, -inf);
        }
        f[0][n - 1] = fruits[0][n - 1];
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - 1]) + fruits[i][j];
                if (j + 1 < n) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j + 1] + fruits[i][j]);
                }
            }
        }
        f[n - 1][0] = fruits[n - 1][0];
        for (int j = 1; j < n; j++) {
            for (int i = j + 1; i < n; i++) {
                f[i][j] = Math.max(f[i][j - 1], f[i - 1][j - 1]) + fruits[i][j];
                if (i + 1 < n) {
                    f[i][j] = Math.max(f[i][j], f[i + 1][j - 1] + fruits[i][j]);
                }
            }
        }
        int ans = f[n - 2][n - 1] + f[n - 1][n - 2];
        for (int i = 0; i < n; i++) {
            ans += fruits[i][i];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxCollectedFruits(vector<vector<int>>& fruits) {
        int n = fruits.size();
        const int inf = 1 << 29;
        vector<vector<int>> f(n, vector<int>(n, -inf));

        f[0][n - 1] = fruits[0][n - 1];
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                f[i][j] = max(f[i - 1][j], f[i - 1][j - 1]) + fruits[i][j];
                if (j + 1 < n) {
                    f[i][j] = max(f[i][j], f[i - 1][j + 1] + fruits[i][j]);
                }
            }
        }

        f[n - 1][0] = fruits[n - 1][0];
        for (int j = 1; j < n; j++) {
            for (int i = j + 1; i < n; i++) {
                f[i][j] = max(f[i][j - 1], f[i - 1][j - 1]) + fruits[i][j];
                if (i + 1 < n) {
                    f[i][j] = max(f[i][j], f[i + 1][j - 1] + fruits[i][j]);
                }
            }
        }

        int ans = f[n - 2][n - 1] + f[n - 1][n - 2];
        for (int i = 0; i < n; i++) {
            ans += fruits[i][i];
        }

        return ans;
    }
};
```

#### Go

```go
func maxCollectedFruits(fruits [][]int) int {
	n := len(fruits)
	const inf = 1 << 29
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = -inf
		}
	}

	f[0][n-1] = fruits[0][n-1]
	for i := 1; i < n; i++ {
		for j := i + 1; j < n; j++ {
			f[i][j] = max(f[i-1][j], f[i-1][j-1]) + fruits[i][j]
			if j+1 < n {
				f[i][j] = max(f[i][j], f[i-1][j+1]+fruits[i][j])
			}
		}
	}

	f[n-1][0] = fruits[n-1][0]
	for j := 1; j < n; j++ {
		for i := j + 1; i < n; i++ {
			f[i][j] = max(f[i][j-1], f[i-1][j-1]) + fruits[i][j]
			if i+1 < n {
				f[i][j] = max(f[i][j], f[i+1][j-1]+fruits[i][j])
			}
		}
	}

	ans := f[n-2][n-1] + f[n-1][n-2]
	for i := 0; i < n; i++ {
		ans += fruits[i][i]
	}

	return ans
}
```

#### TypeScript

```ts
function maxCollectedFruits(fruits: number[][]): number {
    const n = fruits.length;
    const inf = 1 << 29;
    const f: number[][] = Array.from({ length: n }, () => Array(n).fill(-inf));

    f[0][n - 1] = fruits[0][n - 1];
    for (let i = 1; i < n; i++) {
        for (let j = i + 1; j < n; j++) {
            f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - 1]) + fruits[i][j];
            if (j + 1 < n) {
                f[i][j] = Math.max(f[i][j], f[i - 1][j + 1] + fruits[i][j]);
            }
        }
    }

    f[n - 1][0] = fruits[n - 1][0];
    for (let j = 1; j < n; j++) {
        for (let i = j + 1; i < n; i++) {
            f[i][j] = Math.max(f[i][j - 1], f[i - 1][j - 1]) + fruits[i][j];
            if (i + 1 < n) {
                f[i][j] = Math.max(f[i][j], f[i + 1][j - 1] + fruits[i][j]);
            }
        }
    }

    let ans = f[n - 2][n - 1] + f[n - 1][n - 2];
    for (let i = 0; i < n; i++) {
        ans += fruits[i][i];
    }

    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_collected_fruits(fruits: Vec<Vec<i32>>) -> i32 {
        let n = fruits.len();
        let inf = 1 << 29;
        let mut f = vec![vec![-inf; n]; n];

        f[0][n - 1] = fruits[0][n - 1];
        for i in 1..n {
            for j in i + 1..n {
                f[i][j] = std::cmp::max(f[i - 1][j], f[i - 1][j - 1]) + fruits[i][j];
                if j + 1 < n {
                    f[i][j] = std::cmp::max(f[i][j], f[i - 1][j + 1] + fruits[i][j]);
                }
            }
        }

        f[n - 1][0] = fruits[n - 1][0];
        for j in 1..n {
            for i in j + 1..n {
                f[i][j] = std::cmp::max(f[i][j - 1], f[i - 1][j - 1]) + fruits[i][j];
                if i + 1 < n {
                    f[i][j] = std::cmp::max(f[i][j], f[i + 1][j - 1] + fruits[i][j]);
                }
            }
        }

        let mut ans = f[n - 2][n - 1] + f[n - 1][n - 2];
        for i in 0..n {
            ans += fruits[i][i];
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
