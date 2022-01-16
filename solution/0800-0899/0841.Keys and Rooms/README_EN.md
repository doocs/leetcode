# [841. Keys and Rooms](https://leetcode.com/problems/keys-and-rooms)

[中文文档](/solution/0800-0899/0841.Keys%20and%20Rooms/README.md)

## Description

<p>There are <code>N</code> rooms and you start in room <code>0</code>.&nbsp; Each room has a distinct number in <code>0, 1, 2, ..., N-1</code>, and each room may have&nbsp;some keys to access the next room.&nbsp;</p>

<p>Formally, each room <code>i</code>&nbsp;has a list of keys <code>rooms[i]</code>, and each key <code>rooms[i][j]</code> is an integer in <code>[0, 1, ..., N-1]</code> where <code>N = rooms.length</code>.&nbsp; A key <code>rooms[i][j] = v</code>&nbsp;opens the room with number <code>v</code>.</p>

<p>Initially, all the rooms start locked (except for room <code>0</code>).&nbsp;</p>

<p>You can walk back and forth between rooms freely.</p>

<p>Return <code>true</code>&nbsp;if and only if you can enter&nbsp;every room.</p>

<ol>

</ol>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>[[1],[2],[3],[]]

<strong>Output: </strong>true

<strong>Explanation:  </strong>

We start in room 0, and pick up key 1.

We then go to room 1, and pick up key 2.

We then go to room 2, and pick up key 3.

We then go to room 3.  Since we were able to go to every room, we return true.

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>[[1,3],[3,0,1],[2],[0]]

<strong>Output: </strong>false

<strong>Explanation: </strong>We can&#39;t enter the room with number 2.

</pre>

<p><b>Note:</b></p>

<ol>
	<li><code>1 &lt;= rooms.length &lt;=&nbsp;1000</code></li>
	<li><code>0 &lt;= rooms[i].length &lt;= 1000</code></li>
	<li>The number of keys in all rooms combined is at most&nbsp;<code>3000</code>.</li>
</ol>

## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

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
