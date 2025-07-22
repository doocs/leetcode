---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1345.Jump%20Game%20IV/README.md
rating: 1809
source: 第 19 场双周赛 Q4
tags:
    - 广度优先搜索
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [1345. 跳跃游戏 IV](https://leetcode.cn/problems/jump-game-iv)

[English Version](/solution/1300-1399/1345.Jump%20Game%20IV/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>arr</code>&nbsp;，你一开始在数组的第一个元素处（下标为 0）。</p>

<p>每一步，你可以从下标&nbsp;<code>i</code>&nbsp;跳到下标&nbsp;<code>i + 1</code> 、<code>i - 1</code> 或者 <code>j</code> ：</p>

<ul>
	<li><code>i + 1</code> 需满足：<code>i + 1 &lt; arr.length</code></li>
	<li><code>i - 1</code>&nbsp;需满足：<code>i - 1 &gt;= 0</code></li>
	<li><code>j</code>&nbsp;需满足：<code>arr[i] == arr[j]</code>&nbsp;且&nbsp;<code>i != j</code></li>
</ul>

<p>请你返回到达数组最后一个元素的下标处所需的&nbsp;<strong>最少操作次数</strong>&nbsp;。</p>

<p>注意：任何时候你都不能跳到数组外面。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [100,-23,-23,404,100,23,23,23,3,404]
<strong>输出：</strong>3
<strong>解释：</strong>那你需要跳跃 3 次，下标依次为 0 --&gt; 4 --&gt; 3 --&gt; 9 。下标 9 为数组的最后一个元素的下标。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [7]
<strong>输出：</strong>0
<strong>解释：</strong>一开始就在最后一个元素处，所以你不需要跳跃。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>arr = [7,6,9,6,9,6,9,7]
<strong>输出：</strong>1
<strong>解释：</strong>你可以直接从下标 0 处跳到下标 7 处，也就是数组的最后一个元素处。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>
<meta charset="UTF-8" />

<ul>
	<li><code>1 &lt;= arr.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>8</sup>&nbsp;&lt;= arr[i] &lt;= 10<sup>8</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

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
