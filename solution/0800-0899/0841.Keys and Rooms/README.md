---
comments: true
difficulty: 中等
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 图
---

<!-- problem:start -->

# [841. 钥匙和房间](https://leetcode.cn/problems/keys-and-rooms)

[English Version](/solution/0800-0899/0841.Keys%20and%20Rooms/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有 <code>n</code> 个房间，房间按从 <code>0</code> 到 <code>n - 1</code> 编号。最初，除 <code>0</code> 号房间外的其余所有房间都被锁住。你的目标是进入所有的房间。然而，你不能在没有获得钥匙的时候进入锁住的房间。</p>

<p>当你进入一个房间，你可能会在里面找到一套 <strong>不同的钥匙</strong>，每把钥匙上都有对应的房间号，即表示钥匙可以打开的房间。你可以拿上所有钥匙去解锁其他房间。</p>

<p>给你一个数组 <code>rooms</code> 其中 <code>rooms[i]</code> 是你进入 <code>i</code> 号房间可以获得的钥匙集合。如果能进入 <strong>所有</strong> 房间返回 <code>true</code>，否则返回 <code>false</code>。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>rooms = [[1],[2],[3],[]]
<strong>输出：</strong>true
<strong>解释：</strong>
我们从 0 号房间开始，拿到钥匙 1。
之后我们去 1 号房间，拿到钥匙 2。
然后我们去 2 号房间，拿到钥匙 3。
最后我们去了 3 号房间。
由于我们能够进入每个房间，我们返回 true。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>rooms = [[1,3],[3,0,1],[2],[0]]
<strong>输出：</strong>false
<strong>解释：</strong>我们不能进入 2 号房间。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == rooms.length</code></li>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
	<li><code>0 &lt;= rooms[i].length &lt;= 1000</code></li>
	<li><code>1 &lt;= sum(rooms[i].length) &lt;= 3000</code></li>
	<li><code>0 &lt;= rooms[i][j] &lt; n</code></li>
	<li>所有 <code>rooms[i]</code> 的值 <strong>互不相同</strong></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

<!-- thinking:start -->

> **思考**
>
> 房间构成有向图，钥匙即出边，问从 $0$ 出发能否到达全部房间。 $n\le 1000$，一次图遍历即可。
>
> DFS 从 $0$ 沿钥匙深入，访问集合大小等于房间数则成功。

<!-- thinking:end -->

我们可以使用深度优先搜索的方法遍历整张图，统计可以到达的节点个数，并利用数组 `vis` 标记当前节点是否访问过，以防止重复访问。

最后统计访问过的节点个数，若与节点总数相同则说明可以访问所有节点，否则说明存在无法到达的节点。

时间复杂度 $O(n + m)$，空间复杂度 $O(n)$，其中 $n$ 为节点个数，而 $m$ 为边的个数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canVisitAllRooms(self, rooms: List[List[int]]) -> bool:
        def dfs(i: int):
            if i in vis:
                return
            vis.add(i)
            for j in rooms[i]:
                dfs(j)

        vis = set()
        dfs(0)
        return len(vis) == len(rooms)
```

#### Java

```java
class Solution {
    private int cnt;
    private boolean[] vis;
    private List<List<Integer>> g;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        g = rooms;
        vis = new boolean[g.size()];
        dfs(0);
        return cnt == g.size();
    }

    private void dfs(int i) {
        if (vis[i]) {
            return;
        }
        vis[i] = true;
        ++cnt;
        for (int j : g.get(i)) {
            dfs(j);
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canVisitAllRooms(vector<vector<int>>& rooms) {
        int n = rooms.size();
        int cnt = 0;
        bool vis[n];
        memset(vis, false, sizeof(vis));
        function<void(int)> dfs = [&](int i) {
            if (vis[i]) {
                return;
            }
            vis[i] = true;
            ++cnt;
            for (int j : rooms[i]) {
                dfs(j);
            }
        };
        dfs(0);
        return cnt == n;
    }
};
```

#### Go

```go
func canVisitAllRooms(rooms [][]int) bool {
	n := len(rooms)
	cnt := 0
	vis := make([]bool, n)
	var dfs func(int)
	dfs = func(i int) {
		if vis[i] {
			return
		}
		vis[i] = true
		cnt++
		for _, j := range rooms[i] {
			dfs(j)
		}
	}
	dfs(0)
	return cnt == n
}
```

#### TypeScript

```ts
function canVisitAllRooms(rooms: number[][]): boolean {
    const n = rooms.length;
    const vis: boolean[] = Array(n).fill(false);
    const dfs = (i: number) => {
        if (vis[i]) {
            return;
        }
        vis[i] = true;
        for (const j of rooms[i]) {
            dfs(j);
        }
    };
    dfs(0);
    return vis.every(v => v);
}
```

#### Rust

```rust
impl Solution {
    pub fn can_visit_all_rooms(rooms: Vec<Vec<i32>>) -> bool {
        let n = rooms.len();
        let mut is_open = vec![false; n];
        let mut keys = vec![0];
        while !keys.is_empty() {
            let i = keys.pop().unwrap();
            if is_open[i] {
                continue;
            }
            is_open[i] = true;
            rooms[i].iter().for_each(|&key| keys.push(key as usize));
        }
        is_open.iter().all(|&v| v)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：BFS

<!-- thinking:start -->

> **思考**
>
> 方法一用显式栈沿钥匙扩展。同一可达性也可以用队列：从 $0$ 开始，取出一把钥匙后把尚未访问的房间入队。访问标记同样去掉重复钥匙，最后比较访问数与 $n$。

<!-- thinking:end -->

我们也可以使用广度优先搜索的方法遍历整张图，用一个哈希表或者数组 `vis` 标记当前节点是否访问过，以防止重复访问。

具体地，我们定义一个队列 $q$，初始时将节点 $0$ 放入队列中，然后不断遍历队列。每次取出队首节点 $i$，如果 $i$ 被访问过则直接跳过，否则我们将其标记为已访问，然后将 $i$ 可以到达的节点加入队列中。

最后统计访问过的节点个数，若与节点总数相同则说明可以访问所有节点，否则说明存在无法到达的节点。

时间复杂度 $O(n + m)$，空间复杂度 $O(n)$，其中 $n$ 为节点个数，而 $m$ 为边的个数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canVisitAllRooms(self, rooms: List[List[int]]) -> bool:
        vis = set()
        q = deque([0])
        while q:
            i = q.popleft()
            if i in vis:
                continue
            vis.add(i)
            q.extend(j for j in rooms[i])
        return len(vis) == len(rooms)
```

#### Java

```java
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] vis = new boolean[n];
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        int cnt = 0;
        while (!q.isEmpty()) {
            int i = q.poll();
            if (vis[i]) {
                continue;
            }
            vis[i] = true;
            ++cnt;
            for (int j : rooms.get(i)) {
                q.offer(j);
            }
        }
        return cnt == n;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canVisitAllRooms(vector<vector<int>>& rooms) {
        int n = rooms.size();
        vector<bool> vis(n);
        queue<int> q{{0}};
        int cnt = 0;
        while (q.size()) {
            int i = q.front();
            q.pop();
            if (vis[i]) {
                continue;
            }
            vis[i] = true;
            ++cnt;
            for (int j : rooms[i]) {
                q.push(j);
            }
        }
        return cnt == n;
    }
};
```

#### Go

```go
func canVisitAllRooms(rooms [][]int) bool {
	n := len(rooms)
	vis := make([]bool, n)
	cnt := 0
	q := []int{0}
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		if vis[i] {
			continue
		}
		vis[i] = true
		cnt++
		for _, j := range rooms[i] {
			q = append(q, j)
		}
	}
	return cnt == n
}
```

#### TypeScript

```ts
function canVisitAllRooms(rooms: number[][]): boolean {
    const vis = new Set<number>();
    const q: number[] = [0];

    while (q.length) {
        const i = q.pop()!;
        if (vis.has(i)) {
            continue;
        }
        vis.add(i);
        q.push(...rooms[i]);
    }

    return vis.size == rooms.length;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法三：显式栈

<!-- thinking:start -->

> **思考**
>
> 房间构成有向图，每把钥匙是一条出边，问从 $0$ 出发能否到达全部房间。$n \le 1000$，一次遍历就够。沿一条钥匙链递归时，调用深度等于房间数，链长达到 $1000$ 就会超出 Python 的递归上限。因此用栈保存待开的房间：弹出后若尚未访问就标记，再把该房间里的钥匙压入。同一房间可能被多把钥匙重复压入，标记保证只展开一次。访问过的房间数等于 $n$ 时就能打开全部门。

<!-- thinking:end -->

从房间 $0$ 开始，用显式栈遍历能到达的房间。栈里初始只有 $0$。弹出房间 $i$ 时，如果它已经访问过就跳过；否则标记为已访问，并把 $i$ 中的每把钥匙对应的房间压入栈。重复的钥匙只会让同一房间再次入栈，弹出时会被跳过。

最后检查是否每个房间都被访问过。全部访问过就返回 true，否则存在无法到达的房间。

时间复杂度 $O(n + m)$，空间复杂度 $O(n)$，其中 $n$ 为节点个数，而 $m$ 为边的个数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canVisitAllRooms(self, rooms: List[List[int]]) -> bool:
        n = len(rooms)
        vis = [False] * n
        stk = [0]
        while stk:
            i = stk.pop()
            if vis[i]:
                continue
            vis[i] = True
            for j in rooms[i]:
                stk.append(j)
        return all(vis)
```

#### Java

```java
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] vis = new boolean[n];
        Deque<Integer> stk = new ArrayDeque<>();
        stk.push(0);
        while (!stk.isEmpty()) {
            int i = stk.pop();
            if (vis[i]) {
                continue;
            }
            vis[i] = true;
            for (int j : rooms.get(i)) {
                stk.push(j);
            }
        }
        for (boolean v : vis) {
            if (!v) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canVisitAllRooms(vector<vector<int>>& rooms) {
        int n = rooms.size();
        vector<char> vis(n);
        vector<int> stk = {0};
        while (!stk.empty()) {
            int i = stk.back();
            stk.pop_back();
            if (vis[i]) {
                continue;
            }
            vis[i] = 1;
            for (int j : rooms[i]) {
                stk.push_back(j);
            }
        }
        for (char v : vis) {
            if (!v) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func canVisitAllRooms(rooms [][]int) bool {
	n := len(rooms)
	vis := make([]bool, n)
	stk := []int{0}
	for len(stk) > 0 {
		i := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		if vis[i] {
			continue
		}
		vis[i] = true
		for _, j := range rooms[i] {
			stk = append(stk, j)
		}
	}
	for _, v := range vis {
		if !v {
			return false
		}
	}
	return true
}
```

#### TypeScript

```ts
function canVisitAllRooms(rooms: number[][]): boolean {
    const n = rooms.length;
    const vis: boolean[] = Array(n).fill(false);
    const stk: number[] = [0];
    while (stk.length) {
        const i = stk.pop()!;
        if (vis[i]) {
            continue;
        }
        vis[i] = true;
        for (const j of rooms[i]) {
            stk.push(j);
        }
    }
    return vis.every(v => v);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
