---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1583.Count%20Unhappy%20Friends/README_EN.md
rating: 1658
source: Weekly Contest 206 Q2
tags:
    - Array
    - Simulation
---

<!-- problem:start -->

# [1583. Count Unhappy Friends](https://leetcode.com/problems/count-unhappy-friends)

[中文文档](/solution/1500-1599/1583.Count%20Unhappy%20Friends/README.md)

## Description

<!-- description:start -->

<p>You are given a list of&nbsp;<code>preferences</code>&nbsp;for&nbsp;<code>n</code>&nbsp;friends, where <code>n</code> is always <strong>even</strong>.</p>

<p>For each person <code>i</code>,&nbsp;<code>preferences[i]</code>&nbsp;contains&nbsp;a list of friends&nbsp;<strong>sorted</strong> in the <strong>order of preference</strong>. In other words, a friend earlier in the list is more preferred than a friend later in the list.&nbsp;Friends in&nbsp;each list are&nbsp;denoted by integers from <code>0</code> to <code>n-1</code>.</p>

<p>All the friends are divided into pairs.&nbsp;The pairings are&nbsp;given in a list&nbsp;<code>pairs</code>,&nbsp;where <code>pairs[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> denotes <code>x<sub>i</sub></code>&nbsp;is paired with <code>y<sub>i</sub></code> and <code>y<sub>i</sub></code> is paired with <code>x<sub>i</sub></code>.</p>

<p>However, this pairing may cause some of the friends to be unhappy.&nbsp;A friend <code>x</code>&nbsp;is unhappy if <code>x</code>&nbsp;is paired with <code>y</code>&nbsp;and there exists a friend <code>u</code>&nbsp;who&nbsp;is paired with <code>v</code>&nbsp;but:</p>

<ul>
	<li><code>x</code>&nbsp;prefers <code>u</code>&nbsp;over <code>y</code>,&nbsp;and</li>
	<li><code>u</code>&nbsp;prefers <code>x</code>&nbsp;over <code>v</code>.</li>
</ul>

<p>Return <em>the number of unhappy friends</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 4, preferences = [[1, 2, 3], [3, 2, 0], [3, 1, 0], [1, 2, 0]], pairs = [[0, 1], [2, 3]]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
Friend 1 is unhappy because:
- 1 is paired with 0 but prefers 3 over 0, and
- 3 prefers 1 over 2.
Friend 3 is unhappy because:
- 3 is paired with 2 but prefers 1 over 2, and
- 1 prefers 3 over 0.
Friends 0 and 2 are happy.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2, preferences = [[1], [0]], pairs = [[1, 0]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> Both friends 0 and 1 are happy.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 4, preferences = [[1, 3, 2], [2, 3, 0], [1, 3, 0], [0, 2, 1]], pairs = [[1, 3], [0, 2]]
<strong>Output:</strong> 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 500</code></li>
	<li><code>n</code>&nbsp;is even.</li>
	<li><code>preferences.length&nbsp;== n</code></li>
	<li><code>preferences[i].length&nbsp;== n - 1</code></li>
	<li><code>0 &lt;= preferences[i][j] &lt;= n - 1</code></li>
	<li><code>preferences[i]</code>&nbsp;does not contain <code>i</code>.</li>
	<li>All values in&nbsp;<code>preferences[i]</code>&nbsp;are unique.</li>
	<li><code>pairs.length&nbsp;== n/2</code></li>
	<li><code>pairs[i].length&nbsp;== 2</code></li>
	<li><code>x<sub>i</sub> != y<sub>i</sub></code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub>&nbsp;&lt;= n - 1</code></li>
	<li>Each person is contained in <strong>exactly one</strong> pair.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We use an array $\textit{d}$ to record the closeness between each pair of friends, where $\textit{d}[i][j]$ represents the closeness of friend $i$ to friend $j$ (the smaller the value, the closer they are). Additionally, we use an array $\textit{p}$ to record the paired friend for each friend.

We enumerate each friend $x$. For $x$'s paired friend $y$, we find the closeness $\textit{d}[x][y]$ of $x$ to $y$. Then, we enumerate other friends $u$ who are closer than $\textit{d}[x][y]$. If there exists a friend $u$ such that the closeness $\textit{d}[u][x]$ of $u$ to $x$ is higher than $\textit{d}[u][y]$, then $x$ is an unhappy friend, and we increment the result by one.

After the enumeration, we obtain the number of unhappy friends.

The time complexity is $O(n^2)$, and the space complexity is $O(n^2)$. Here, $n$ is the number of friends.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def unhappyFriends(
        self, n: int, preferences: List[List[int]], pairs: List[List[int]]
    ) -> int:
        d = [{x: j for j, x in enumerate(p)} for p in preferences]
        p = {}
        for x, y in pairs:
            p[x] = y
            p[y] = x
        ans = 0
        for x in range(n):
            y = p[x]
            for i in range(d[x][y]):
                u = preferences[x][i]
                v = p[u]
                if d[u][x] < d[u][v]:
                    ans += 1
                    break
        return ans
```

#### Java

```java
class Solution {
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        int[][] d = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n - 1; ++j) {
                d[i][preferences[i][j]] = j;
            }
        }
        int[] p = new int[n];
        for (var e : pairs) {
            int x = e[0], y = e[1];
            p[x] = y;
            p[y] = x;
        }
        int ans = 0;
        for (int x = 0; x < n; ++x) {
            int y = p[x];
            for (int i = 0; i < d[x][y]; ++i) {
                int u = preferences[x][i];
                int v = p[u];
                if (d[u][x] < d[u][v]) {
                    ++ans;
                    break;
                }
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
    int unhappyFriends(int n, vector<vector<int>>& preferences, vector<vector<int>>& pairs) {
        vector<vector<int>> d(n, vector<int>(n));
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n - 1; ++j) {
                d[i][preferences[i][j]] = j;
            }
        }
        vector<int> p(n, 0);
        for (auto& e : pairs) {
            int x = e[0], y = e[1];
            p[x] = y;
            p[y] = x;
        }
        int ans = 0;
        for (int x = 0; x < n; ++x) {
            int y = p[x];
            for (int i = 0; i < d[x][y]; ++i) {
                int u = preferences[x][i];
                int v = p[u];
                if (d[u][x] < d[u][v]) {
                    ++ans;
                    break;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func unhappyFriends(n int, preferences [][]int, pairs [][]int) (ans int) {
	d := make([][]int, n)
	for i := range d {
		d[i] = make([]int, n)
	}

	for i := 0; i < n; i++ {
		for j := 0; j < n-1; j++ {
			d[i][preferences[i][j]] = j
		}
	}

	p := make([]int, n)
	for _, e := range pairs {
		x, y := e[0], e[1]
		p[x] = y
		p[y] = x
	}

	for x := 0; x < n; x++ {
		y := p[x]
		for i := 0; i < d[x][y]; i++ {
			u := preferences[x][i]
			v := p[u]
			if d[u][x] < d[u][v] {
				ans++
				break
			}
		}
	}

	return
}
```

#### TypeScript

```ts
function unhappyFriends(n: number, preferences: number[][], pairs: number[][]): number {
    const d: number[][] = Array.from({ length: n }, () => Array(n).fill(0));
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n - 1; ++j) {
            d[i][preferences[i][j]] = j;
        }
    }
    const p: number[] = Array(n).fill(0);
    for (const [x, y] of pairs) {
        p[x] = y;
        p[y] = x;
    }
    let ans = 0;
    for (let x = 0; x < n; ++x) {
        const y = p[x];
        for (let i = 0; i < d[x][y]; ++i) {
            const u = preferences[x][i];
            const v = p[u];
            if (d[u][x] < d[u][v]) {
                ++ans;
                break;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
