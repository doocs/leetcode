# [841. 钥匙和房间](https://leetcode-cn.com/problems/keys-and-rooms)

[English Version](/solution/0800-0899/0841.Keys%20and%20Rooms/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有 <code>N</code> 个房间，开始时你位于 <code>0</code> 号房间。每个房间有不同的号码：<code>0，1，2，...，N-1</code>，并且房间里可能有一些钥匙能使你进入下一个房间。</p>

<p>在形式上，对于每个房间 <code>i</code> 都有一个钥匙列表 <code>rooms[i]</code>，每个钥匙 <code>rooms[i][j]</code> 由 <code>[0,1，...，N-1]</code> 中的一个整数表示，其中 <code>N = rooms.length</code>。 钥匙 <code>rooms[i][j] = v</code> 可以打开编号为 <code>v</code> 的房间。</p>

<p>最初，除 <code>0</code> 号房间外的其余所有房间都被锁住。</p>

<p>你可以自由地在房间之间来回走动。</p>

<p>如果能进入每个房间返回 <code>true</code>，否则返回 <code>false</code>。</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre><strong>输入: </strong>[[1],[2],[3],[]]
<strong>输出: </strong>true
<strong>解释:  </strong>
我们从 0 号房间开始，拿到钥匙 1。
之后我们去 1 号房间，拿到钥匙 2。
然后我们去 2 号房间，拿到钥匙 3。
最后我们去了 3 号房间。
由于我们能够进入每个房间，我们返回 true。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>[[1,3],[3,0,1],[2],[0]]
<strong>输出：</strong>false
<strong>解释：</strong>我们不能进入 2 号房间。
</pre>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= rooms.length &lt;=&nbsp;1000</code></li>
	<li><code>0 &lt;= rooms[i].length &lt;= 1000</code></li>
	<li>所有房间中的钥匙数量总计不超过&nbsp;<code>3000</code>。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

DFS。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canVisitAllRooms(self, rooms: List[List[int]]) -> bool:
        def dfs(u):
            if u == n or u in vis:
                return
            vis.add(u)
            for v in rooms[u]:
                dfs(v)

        n = len(rooms)
        vis = set()
        dfs(0)
        return len(vis) == n
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private List<List<Integer>> rooms;
    private Set<Integer> vis;
    private int n;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        vis = new HashSet<>();
        this.rooms = rooms;
        n = rooms.size();
        dfs(0);
        return vis.size() == n;
    }

    private void dfs(int u) {
        if (u == n || vis.contains(u)) {
            return;
        }
        vis.add(u);
        for (int v : rooms.get(u)) {
            dfs(v);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> rooms;
    unordered_set<int> vis;
    int n;

    bool canVisitAllRooms(vector<vector<int>>& rooms) {
        vis.clear();
        this->rooms = rooms;
        n = rooms.size();
        dfs(0);
        return vis.size() == n;
    }

    void dfs(int u) {
        if (u == n || vis.count(u)) return;
        vis.insert(u);
        for (int v : rooms[u]) dfs(v);
    }
};
```

### **Go**

```go
func canVisitAllRooms(rooms [][]int) bool {
	n := len(rooms)
	vis := make(map[int]bool)
	var dfs func(u int)
	dfs = func(u int) {
		if u == n || vis[u] {
			return
		}
		vis[u] = true
		for _, v := range rooms[u] {
			dfs(v)
		}
	}
	dfs(0)
	return len(vis) == n
}
```

### **...**

```

```

<!-- tabs:end -->
