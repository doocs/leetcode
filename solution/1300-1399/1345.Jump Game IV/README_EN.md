---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1345.Jump%20Game%20IV/README_EN.md
rating: 1809
source: Biweekly Contest 19 Q4
tags:
    - Breadth-First Search
    - Array
    - Hash Table
---

<!-- problem:start -->

# [1345. Jump Game IV](https://leetcode.com/problems/jump-game-iv)

[中文文档](/solution/1300-1399/1345.Jump%20Game%20IV/README.md)

## Description

<!-- description:start -->

<p>Given an array of&nbsp;integers <code>arr</code>, you are initially positioned at the first index of the array.</p>

<p>In one step you can jump from index <code>i</code> to index:</p>

<ul>
	<li><code>i + 1</code> where:&nbsp;<code>i + 1 &lt; arr.length</code>.</li>
	<li><code>i - 1</code> where:&nbsp;<code>i - 1 &gt;= 0</code>.</li>
	<li><code>j</code> where: <code>arr[i] == arr[j]</code> and <code>i != j</code>.</li>
</ul>

<p>Return <em>the minimum number of steps</em> to reach the <strong>last index</strong> of the array.</p>

<p>Notice that you can not jump outside of the array at any time.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [100,-23,-23,404,100,23,23,23,3,404]
<strong>Output:</strong> 3
<strong>Explanation:</strong> You need three jumps from index 0 --&gt; 4 --&gt; 3 --&gt; 9. Note that index 9 is the last index of the array.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [7]
<strong>Output:</strong> 0
<strong>Explanation:</strong> Start index is the last index. You do not need to jump.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [7,6,9,6,9,6,9,7]
<strong>Output:</strong> 1
<strong>Explanation:</strong> You can jump directly from index 0 to index 7 which is last index of the array.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>8</sup> &lt;= arr[i] &lt;= 10<sup>8</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minJumps(self, arr: List[int]) -> int:
        g = defaultdict(list)
        for i, x in enumerate(arr):
            g[x].append(i)
        q = deque([0])
        vis = {0}
        ans = 0
        while 1:
            for _ in range(len(q)):
                i = q.popleft()
                if i == len(arr) - 1:
                    return ans
                for j in (i + 1, i - 1, *g.pop(arr[i], [])):
                    if 0 <= j < len(arr) and j not in vis:
                        q.append(j)
                        vis.add(j)
            ans += 1
```

#### Java

```java
class Solution {
    public int minJumps(int[] arr) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            g.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
        boolean[] vis = new boolean[n];
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        vis[0] = true;
        for (int ans = 0;; ++ans) {
            for (int k = q.size(); k > 0; --k) {
                int i = q.poll();
                if (i == n - 1) {
                    return ans;
                }
                for (int j : g.get(arr[i])) {
                    if (!vis[j]) {
                        vis[j] = true;
                        q.offer(j);
                    }
                }
                g.get(arr[i]).clear();
                for (int j : new int[] {i - 1, i + 1}) {
                    if (0 <= j && j < n && !vis[j]) {
                        vis[j] = true;
                        q.offer(j);
                    }
                }
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minJumps(vector<int>& arr) {
        unordered_map<int, vector<int>> g;
        int n = arr.size();
        for (int i = 0; i < n; ++i) {
            g[arr[i]].push_back(i);
        }
        vector<bool> vis(n);
        queue<int> q{{0}};
        vis[0] = true;
        for (int ans = 0;; ++ans) {
            for (int k = q.size(); k; --k) {
                int i = q.front();
                q.pop();
                if (i == n - 1) {
                    return ans;
                }
                for (int j : g[arr[i]]) {
                    if (!vis[j]) {
                        vis[j] = true;
                        q.push(j);
                    }
                }
                g[arr[i]].clear();
                for (int j : {i - 1, i + 1}) {
                    if (0 <= j && j < n && !vis[j]) {
                        vis[j] = true;
                        q.push(j);
                    }
                }
            }
        }
    }
};
```

#### Go

```go
func minJumps(arr []int) int {
	g := map[int][]int{}
	for i, x := range arr {
		g[x] = append(g[x], i)
	}
	n := len(arr)
	q := []int{0}
	vis := make([]bool, n)
	vis[0] = true
	for ans := 0; ; ans++ {
		for k := len(q); k > 0; k-- {
			i := q[0]
			q = q[1:]
			if i == n-1 {
				return ans
			}
			for _, j := range g[arr[i]] {
				if !vis[j] {
					vis[j] = true
					q = append(q, j)
				}
			}
			g[arr[i]] = nil
			for _, j := range []int{i - 1, i + 1} {
				if 0 <= j && j < n && !vis[j] {
					vis[j] = true
					q = append(q, j)
				}
			}
		}
	}
}
```

#### TypeScript

```ts
function minJumps(arr: number[]): number {
    const g: Map<number, number[]> = new Map();
    const n = arr.length;
    for (let i = 0; i < n; ++i) {
        if (!g.has(arr[i])) {
            g.set(arr[i], []);
        }
        g.get(arr[i])!.push(i);
    }
    let q: number[] = [0];
    const vis: boolean[] = Array(n).fill(false);
    vis[0] = true;
    for (let ans = 0; ; ++ans) {
        const nq: number[] = [];
        for (const i of q) {
            if (i === n - 1) {
                return ans;
            }
            for (const j of g.get(arr[i])!) {
                if (!vis[j]) {
                    vis[j] = true;
                    nq.push(j);
                }
            }
            g.get(arr[i])!.length = 0;
            for (const j of [i - 1, i + 1]) {
                if (j >= 0 && j < n && !vis[j]) {
                    vis[j] = true;
                    nq.push(j);
                }
            }
        }
        q = nq;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
