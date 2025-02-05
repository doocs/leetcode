---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1947.Maximum%20Compatibility%20Score%20Sum/README_EN.md
rating: 1704
source: Weekly Contest 251 Q3
tags:
    - Bit Manipulation
    - Array
    - Dynamic Programming
    - Backtracking
    - Bitmask
---

<!-- problem:start -->

# [1947. Maximum Compatibility Score Sum](https://leetcode.com/problems/maximum-compatibility-score-sum)

[中文文档](/solution/1900-1999/1947.Maximum%20Compatibility%20Score%20Sum/README.md)

## Description

<!-- description:start -->

<p>There is a survey that consists of <code>n</code> questions where each question&#39;s answer is either <code>0</code> (no) or <code>1</code> (yes).</p>

<p>The survey was given to <code>m</code> students numbered from <code>0</code> to <code>m - 1</code> and <code>m</code> mentors numbered from <code>0</code> to <code>m - 1</code>. The answers of the students are represented by a 2D integer array <code>students</code> where <code>students[i]</code> is an integer array that contains the answers of the <code>i<sup>th</sup></code> student (<strong>0-indexed</strong>). The answers of the mentors are represented by a 2D integer array <code>mentors</code> where <code>mentors[j]</code> is an integer array that contains the answers of the <code>j<sup>th</sup></code> mentor (<strong>0-indexed</strong>).</p>

<p>Each student will be assigned to <strong>one</strong> mentor, and each mentor will have <strong>one</strong> student assigned to them. The <strong>compatibility score</strong> of a student-mentor pair is the number of answers that are the same for both the student and the mentor.</p>

<ul>
	<li>For example, if the student&#39;s answers were <code>[1, <u>0</u>, <u>1</u>]</code> and the mentor&#39;s answers were <code>[0, <u>0</u>, <u>1</u>]</code>, then their compatibility score is 2 because only the second and the third answers are the same.</li>
</ul>

<p>You are tasked with finding the optimal student-mentor pairings to <strong>maximize</strong> the<strong> sum of the compatibility scores</strong>.</p>

<p>Given <code>students</code> and <code>mentors</code>, return <em>the <strong>maximum compatibility score sum</strong> that can be achieved.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> students = [[1,1,0],[1,0,1],[0,0,1]], mentors = [[1,0,0],[0,0,1],[1,1,0]]
<strong>Output:</strong> 8
<strong>Explanation:</strong>&nbsp;We assign students to mentors in the following way:
- student 0 to mentor 2 with a compatibility score of 3.
- student 1 to mentor 0 with a compatibility score of 2.
- student 2 to mentor 1 with a compatibility score of 3.
The compatibility score sum is 3 + 2 + 3 = 8.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> students = [[0,0],[0,0],[0,0]], mentors = [[1,1],[1,1],[1,1]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The compatibility score of any student-mentor pair is 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == students.length == mentors.length</code></li>
	<li><code>n == students[i].length == mentors[j].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 8</code></li>
	<li><code>students[i][k]</code> is either <code>0</code> or <code>1</code>.</li>
	<li><code>mentors[j][k]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Preprocessing + Backtracking

We can first preprocess the compatibility score $g[i][j]$ between each student $i$ and mentor $j$, and then use a backtracking algorithm to solve the problem.

Define a function $\textit{dfs}(i, s)$, where $i$ represents the current student being processed, and $s$ represents the current sum of compatibility scores.

In $\textit{dfs}(i, s)$, if $i \geq m$, it means all students have been assigned, and we update the answer to $\max(\textit{ans}, s)$. Otherwise, we enumerate which mentor the $i$-th student can be assigned to, and then recursively process the next student. During the process, we use an array $\textit{vis}$ to record which mentors have already been assigned to avoid duplicate assignments.

We call $\textit{dfs}(0, 0)$ to get the maximum compatibility score sum.

The time complexity is $O(m!)$, and the space complexity is $O(m^2)$. Here, $m$ is the number of students and mentors.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxCompatibilitySum(
        self, students: List[List[int]], mentors: List[List[int]]
    ) -> int:
        def dfs(i: int, s: int):
            if i >= m:
                nonlocal ans
                ans = max(ans, s)
                return
            for j in range(m):
                if not vis[j]:
                    vis[j] = True
                    dfs(i + 1, s + g[i][j])
                    vis[j] = False

        ans = 0
        m = len(students)
        vis = [False] * m
        g = [[0] * m for _ in range(m)]
        for i, x in enumerate(students):
            for j, y in enumerate(mentors):
                g[i][j] = sum(a == b for a, b in zip(x, y))
        dfs(0, 0)
        return ans
```

#### Java

```java
class Solution {
    private int m;
    private int ans;
    private int[][] g;
    private boolean[] vis;

    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        m = students.length;
        g = new int[m][m];
        vis = new boolean[m];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < m; ++j) {
                for (int k = 0; k < students[i].length; ++k) {
                    if (students[i][k] == mentors[j][k]) {
                        ++g[i][j];
                    }
                }
            }
        }
        dfs(0, 0);
        return ans;
    }

    private void dfs(int i, int s) {
        if (i >= m) {
            ans = Math.max(ans, s);
            return;
        }
        for (int j = 0; j < m; ++j) {
            if (!vis[j]) {
                vis[j] = true;
                dfs(i + 1, s + g[i][j]);
                vis[j] = false;
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxCompatibilitySum(vector<vector<int>>& students, vector<vector<int>>& mentors) {
        int m = students.size();
        int n = students[0].size();
        vector<vector<int>> g(m, vector<int>(m));
        vector<bool> vis(m);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < m; ++j) {
                for (int k = 0; k < n; ++k) {
                    g[i][j] += students[i][k] == mentors[j][k];
                }
            }
        }
        int ans = 0;
        auto dfs = [&](this auto&& dfs, int i, int s) {
            if (i >= m) {
                ans = max(ans, s);
                return;
            }
            for (int j = 0; j < m; ++j) {
                if (!vis[j]) {
                    vis[j] = true;
                    dfs(i + 1, s + g[i][j]);
                    vis[j] = false;
                }
            }
        };
        dfs(0, 0);
        return ans;
    }
};
```

#### Go

```go
func maxCompatibilitySum(students [][]int, mentors [][]int) (ans int) {
	m, n := len(students), len(students[0])
	g := make([][]int, m)
	vis := make([]bool, m)
	for i, x := range students {
		g[i] = make([]int, m)
		for j, y := range mentors {
			for k := 0; k < n; k++ {
				if x[k] == y[k] {
					g[i][j]++
				}
			}
		}
	}
	var dfs func(int, int)
	dfs = func(i, s int) {
		if i == m {
			ans = max(ans, s)
			return
		}
		for j := 0; j < m; j++ {
			if !vis[j] {
				vis[j] = true
				dfs(i+1, s+g[i][j])
				vis[j] = false
			}
		}
	}
	dfs(0, 0)
	return
}
```

#### TypeScript

```ts
function maxCompatibilitySum(students: number[][], mentors: number[][]): number {
    let ans = 0;
    const m = students.length;
    const vis: boolean[] = Array(m).fill(false);
    const g: number[][] = Array.from({ length: m }, () => Array(m).fill(0));
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < m; ++j) {
            for (let k = 0; k < students[i].length; ++k) {
                if (students[i][k] === mentors[j][k]) {
                    g[i][j]++;
                }
            }
        }
    }
    const dfs = (i: number, s: number): void => {
        if (i >= m) {
            ans = Math.max(ans, s);
            return;
        }
        for (let j = 0; j < m; ++j) {
            if (!vis[j]) {
                vis[j] = true;
                dfs(i + 1, s + g[i][j]);
                vis[j] = false;
            }
        }
    };
    dfs(0, 0);
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_compatibility_sum(students: Vec<Vec<i32>>, mentors: Vec<Vec<i32>>) -> i32 {
        let mut ans = 0;
        let m = students.len();
        let mut vis = vec![false; m];
        let mut g = vec![vec![0; m]; m];

        for i in 0..m {
            for j in 0..m {
                for k in 0..students[i].len() {
                    if students[i][k] == mentors[j][k] {
                        g[i][j] += 1;
                    }
                }
            }
        }

        fn dfs(i: usize, s: i32, m: usize, g: &Vec<Vec<i32>>, vis: &mut Vec<bool>, ans: &mut i32) {
            if i >= m {
                *ans = (*ans).max(s);
                return;
            }
            for j in 0..m {
                if !vis[j] {
                    vis[j] = true;
                    dfs(i + 1, s + g[i][j], m, g, vis, ans);
                    vis[j] = false;
                }
            }
        }

        dfs(0, 0, m, &g, &mut vis, &mut ans);
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[][]} students
 * @param {number[][]} mentors
 * @return {number}
 */
var maxCompatibilitySum = function (students, mentors) {
    let ans = 0;
    const m = students.length;
    const vis = Array(m).fill(false);
    const g = Array.from({ length: m }, () => Array(m).fill(0));

    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < m; ++j) {
            for (let k = 0; k < students[i].length; ++k) {
                if (students[i][k] === mentors[j][k]) {
                    g[i][j]++;
                }
            }
        }
    }

    const dfs = function (i, s) {
        if (i >= m) {
            ans = Math.max(ans, s);
            return;
        }
        for (let j = 0; j < m; ++j) {
            if (!vis[j]) {
                vis[j] = true;
                dfs(i + 1, s + g[i][j]);
                vis[j] = false;
            }
        }
    };

    dfs(0, 0);
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
