# [841. 钥匙和房间](https://leetcode.cn/problems/keys-and-rooms)

[English Version](/solution/0800-0899/0841.Keys%20and%20Rooms/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有 <code>n</code> 个房间，房间按从 <code>0</code> 到 <code>n - 1</code> 编号。最初，除 <code>0</code> 号房间外的其余所有房间都被锁住。你的目标是进入所有的房间。然而，你不能在没有获得钥匙的时候进入锁住的房间。</p>

<p>当你进入一个房间，你可能会在里面找到一套不同的钥匙，每把钥匙上都有对应的房间号，即表示钥匙可以打开的房间。你可以拿上所有钥匙去解锁其他房间。</p>

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

## 解法

### 方法一：DFS

我们可以使用深度优先搜索的方法遍历整张图，统计可以到达的节点个数，并利用数组 `vis` 标记当前节点是否访问过，以防止重复访问。

最后统计访问过的节点个数，若与节点总数相同则说明可以访问所有节点，否则说明存在无法到达的节点。

时间复杂度 $O(n + m)$，空间复杂度 $O(n)$，其中 $n$ 为节点个数，而 $m$ 为边的个数。

<!-- tabs:start -->

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

<!-- end -->
