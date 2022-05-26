# [841. Keys and Rooms](https://leetcode.com/problems/keys-and-rooms)

[中文文档](/solution/0800-0899/0841.Keys%20and%20Rooms/README.md)

## Description

<p>There are <code>n</code> rooms labeled from <code>0</code> to <code>n - 1</code>&nbsp;and all the rooms are locked except for room <code>0</code>. Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.</p>

<p>When you visit a room, you may find a set of <strong>distinct keys</strong> in it. Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.</p>

<p>Given an array <code>rooms</code> where <code>rooms[i]</code> is the set of keys that you can obtain if you visited room <code>i</code>, return <code>true</code> <em>if you can visit <strong>all</strong> the rooms, or</em> <code>false</code> <em>otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> rooms = [[1],[2],[3],[]]
<strong>Output:</strong> true
<strong>Explanation:</strong> 
We visit room 0 and pick up key 1.
We then visit room 1 and pick up key 2.
We then visit room 2 and pick up key 3.
We then visit room 3.
Since we were able to visit every room, we return true.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> rooms = [[1,3],[3,0,1],[2],[0]]
<strong>Output:</strong> false
<strong>Explanation:</strong> We can not enter room number 2 since the only key that unlocks it is in that room.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == rooms.length</code></li>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
	<li><code>0 &lt;= rooms[i].length &lt;= 1000</code></li>
	<li><code>1 &lt;= sum(rooms[i].length) &lt;= 3000</code></li>
	<li><code>0 &lt;= rooms[i][j] &lt; n</code></li>
	<li>All the values of <code>rooms[i]</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def canVisitAllRooms(self, rooms: List[List[int]]) -> bool:
        def dfs(u):
            if u in vis:
                return
            vis.add(u)
            for v in rooms[u]:
                dfs(v)

        vis = set()
        dfs(0)
        return len(vis) == len(rooms)
```

### **Java**

```java
class Solution {
    private List<List<Integer>> rooms;
    private Set<Integer> vis;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        vis = new HashSet<>();
        this.rooms = rooms;
        dfs(0);
        return vis.size() == rooms.size();
    }

    private void dfs(int u) {
        if (vis.contains(u)) {
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

    bool canVisitAllRooms(vector<vector<int>>& rooms) {
        vis.clear();
        this->rooms = rooms;
        dfs(0);
        return vis.size() == rooms.size();
    }

    void dfs(int u) {
        if (vis.count(u)) return;
        vis.insert(u);
        for (int v : rooms[u]) dfs(v);
    }
};
```

### **Go**

```go
func canVisitAllRooms(rooms [][]int) bool {
	vis := make(map[int]bool)
	var dfs func(u int)
	dfs = func(u int) {
		if vis[u] {
			return
		}
		vis[u] = true
		for _, v := range rooms[u] {
			dfs(v)
		}
	}
	dfs(0)
	return len(vis) == len(rooms)
}
```

### **TypeScript**

```ts
function canVisitAllRooms(rooms: number[][]): boolean {
    const n = rooms.length;
    const isOpen = new Array(n).fill(false);
    const dfs = (i: number) => {
        if (isOpen[i]) {
            return;
        }
        isOpen[i] = true;
        rooms[i].forEach(k => dfs(k));
    };
    dfs(0);
    return isOpen.every(v => v);
}
```

```ts
function canVisitAllRooms(rooms: number[][]): boolean {
    const n = rooms.length;
    const isOpen = new Array(n).fill(false);
    const keys = [0];
    while (keys.length !== 0) {
        const i = keys.pop();
        if (isOpen[i]) {
            continue;
        }
        isOpen[i] = true;
        keys.push(...rooms[i]);
    }
    return isOpen.every(v => v);
}
```

### **Rust**

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

### **...**

```

```

<!-- tabs:end -->
