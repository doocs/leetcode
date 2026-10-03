---
comments: true
difficulty: Medium
tags:
    - Tree
    - Depth-First Search
    - Breadth-First Search
    - Array
    - Hash Table
---

<!-- problem:start -->

# [582. Kill Process 🔒](https://leetcode.com/problems/kill-process)

[中文文档](/solution/0500-0599/0582.Kill%20Process/README.md)

## Description

<!-- description:start -->

<p>You have <code>n</code> processes forming a rooted tree structure. You are given two integer arrays <code>pid</code> and <code>ppid</code>, where <code>pid[i]</code> is the ID of the <code>i<sup>th</sup></code> process and <code>ppid[i]</code> is the ID of the <code>i<sup>th</sup></code> process&#39;s parent process.</p>

<p>Each process has only <strong>one parent process</strong> but may have multiple children processes. Only one process has <code>ppid[i] = 0</code>, which means this process has <strong>no parent process</strong> (the root of the tree).</p>

<p>When a process is <strong>killed</strong>, all of its children processes will also be killed.</p>

<p>Given an integer <code>kill</code> representing the ID of a process you want to kill, return <em>a list of the IDs of the processes that will be killed. You may return the answer in <strong>any order</strong>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0582.Kill%20Process/images/ptree.jpg" style="width: 207px; height: 302px;" />
<pre>
<strong>Input:</strong> pid = [1,3,10,5], ppid = [3,0,5,3], kill = 5
<strong>Output:</strong> [5,10]
<strong>Explanation:</strong>&nbsp;The processes colored in red are the processes that should be killed.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> pid = [1], ppid = [0], kill = 1
<strong>Output:</strong> [1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == pid.length</code></li>
	<li><code>n == ppid.length</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= pid[i] &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= ppid[i] &lt;= 5 * 10<sup>4</sup></code></li>
	<li>Only one process has no parent.</li>
	<li>All the values of <code>pid</code> are <strong>unique</strong>.</li>
	<li><code>kill</code> is <strong>guaranteed</strong> to be in <code>pid</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS

<!-- thinking:start -->

> **Thinking**
>
> Killing a process kills its whole descendant tree. The parent arrays should become an adjacency list first.
>
> $g[p]$ stores children of $p$. DFS or BFS from `kill` gathers every reachable id. Each process is entered once.

<!-- thinking:end -->

We first construct a graph $g$ based on $pid$ and $ppid$, where $g[i]$ represents all child processes of process $i$. Then, starting from the process $kill$, we perform depth-first search to obtain all killed processes.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of processes.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def killProcess(self, pid: List[int], ppid: List[int], kill: int) -> List[int]:
        def dfs(i: int):
            ans.append(i)
            for j in g[i]:
                dfs(j)

        g = defaultdict(list)
        for i, p in zip(pid, ppid):
            g[p].append(i)
        ans = []
        dfs(kill)
        return ans
```

#### Java

```java
class Solution {
    private Map<Integer, List<Integer>> g = new HashMap<>();
    private List<Integer> ans = new ArrayList<>();

    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        int n = pid.size();
        for (int i = 0; i < n; ++i) {
            g.computeIfAbsent(ppid.get(i), k -> new ArrayList<>()).add(pid.get(i));
        }
        dfs(kill);
        return ans;
    }

    private void dfs(int i) {
        ans.add(i);
        for (int j : g.getOrDefault(i, Collections.emptyList())) {
            dfs(j);
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> killProcess(vector<int>& pid, vector<int>& ppid, int kill) {
        unordered_map<int, vector<int>> g;
        int n = pid.size();
        for (int i = 0; i < n; ++i) {
            g[ppid[i]].push_back(pid[i]);
        }
        vector<int> ans;
        function<void(int)> dfs = [&](int i) {
            ans.push_back(i);
            for (int j : g[i]) {
                dfs(j);
            }
        };
        dfs(kill);
        return ans;
    }
};
```

#### Go

```go
func killProcess(pid []int, ppid []int, kill int) (ans []int) {
	g := map[int][]int{}
	for i, p := range ppid {
		g[p] = append(g[p], pid[i])
	}
	var dfs func(int)
	dfs = func(i int) {
		ans = append(ans, i)
		for _, j := range g[i] {
			dfs(j)
		}
	}
	dfs(kill)
	return
}
```

#### TypeScript

```ts
function killProcess(pid: number[], ppid: number[], kill: number): number[] {
    const g: Map<number, number[]> = new Map();
    for (let i = 0; i < pid.length; ++i) {
        if (!g.has(ppid[i])) {
            g.set(ppid[i], []);
        }
        g.get(ppid[i])?.push(pid[i]);
    }
    const ans: number[] = [];
    const dfs = (i: number) => {
        ans.push(i);
        for (const j of g.get(i) ?? []) {
            dfs(j);
        }
    };
    dfs(kill);
    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn kill_process(pid: Vec<i32>, ppid: Vec<i32>, kill: i32) -> Vec<i32> {
        let mut g: HashMap<i32, Vec<i32>> = HashMap::new();
        let mut ans: Vec<i32> = Vec::new();

        let n = pid.len();
        for i in 0..n {
            g.entry(ppid[i]).or_insert(Vec::new()).push(pid[i]);
        }

        Self::dfs(&mut ans, &g, kill);
        ans
    }

    fn dfs(ans: &mut Vec<i32>, g: &HashMap<i32, Vec<i32>>, i: i32) {
        ans.push(i);
        if let Some(children) = g.get(&i) {
            for &j in children {
                Self::dfs(ans, g, j);
            }
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Explicit Stack

<!-- thinking:start -->

> **Thinking**
>
> Killing a process also kills every descendant. The parent links arrive as two parallel arrays, and $n$ can be $5 \times 10^4$, so each process should first store its children. Collecting descendants by recursion along a parent chain uses a call depth equal to the process count and overflows Python once the chain reaches length $1000$. A stack therefore holds the processes still to visit: a popped process is recorded, and its children are pushed in reverse so the collection order matches a preorder walk of the child lists. The process tree has no cycle, so each process is pushed once.

<!-- thinking:end -->

Build a graph $g$ from $pid$ and $ppid$, where $g[i]$ is the list of child processes of process $i$. Start at process $kill$ and walk with an explicit stack. When process $i$ is popped, append it to the answer and push its children in reverse order, so the earlier child is popped next. Every descendant is collected once. The problem accepts the ids in any order.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of processes.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def killProcess(self, pid: List[int], ppid: List[int], kill: int) -> List[int]:
        g = defaultdict(list)
        for i, p in zip(pid, ppid):
            g[p].append(i)
        ans = []
        stk = [kill]
        while stk:
            i = stk.pop()
            ans.append(i)
            for j in reversed(g[i]):
                stk.append(j)
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        int n = pid.size();
        for (int i = 0; i < n; ++i) {
            g.computeIfAbsent(ppid.get(i), k -> new ArrayList<>()).add(pid.get(i));
        }
        List<Integer> ans = new ArrayList<>();
        Deque<Integer> stk = new ArrayDeque<>();
        stk.push(kill);
        while (!stk.isEmpty()) {
            int i = stk.pop();
            ans.add(i);
            List<Integer> children = g.getOrDefault(i, List.of());
            for (int k = children.size() - 1; k >= 0; --k) {
                stk.push(children.get(k));
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
    vector<int> killProcess(vector<int>& pid, vector<int>& ppid, int kill) {
        unordered_map<int, vector<int>> g;
        int n = pid.size();
        for (int i = 0; i < n; ++i) {
            g[ppid[i]].push_back(pid[i]);
        }
        vector<int> ans;
        vector<int> stk = {kill};
        while (!stk.empty()) {
            int i = stk.back();
            stk.pop_back();
            ans.push_back(i);
            auto it = g.find(i);
            if (it == g.end()) {
                continue;
            }
            auto& children = it->second;
            for (int k = (int) children.size() - 1; k >= 0; --k) {
                stk.push_back(children[k]);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func killProcess(pid []int, ppid []int, kill int) (ans []int) {
	g := map[int][]int{}
	for i, p := range ppid {
		g[p] = append(g[p], pid[i])
	}
	stk := []int{kill}
	for len(stk) > 0 {
		i := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		ans = append(ans, i)
		children := g[i]
		for k := len(children) - 1; k >= 0; k-- {
			stk = append(stk, children[k])
		}
	}
	return
}
```

#### TypeScript

```ts
function killProcess(pid: number[], ppid: number[], kill: number): number[] {
    const g: Map<number, number[]> = new Map();
    for (let i = 0; i < pid.length; ++i) {
        if (!g.has(ppid[i])) {
            g.set(ppid[i], []);
        }
        g.get(ppid[i])!.push(pid[i]);
    }
    const ans: number[] = [];
    const stk: number[] = [kill];
    while (stk.length) {
        const i = stk.pop()!;
        ans.push(i);
        const children = g.get(i) ?? [];
        for (let k = children.length - 1; k >= 0; --k) {
            stk.push(children[k]);
        }
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn kill_process(pid: Vec<i32>, ppid: Vec<i32>, kill: i32) -> Vec<i32> {
        let mut g: HashMap<i32, Vec<i32>> = HashMap::new();
        let n = pid.len();
        for i in 0..n {
            g.entry(ppid[i]).or_insert(Vec::new()).push(pid[i]);
        }
        let mut ans = Vec::new();
        let mut stk = vec![kill];
        while let Some(i) = stk.pop() {
            ans.push(i);
            if let Some(children) = g.get(&i) {
                for &j in children.iter().rev() {
                    stk.push(j);
                }
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
