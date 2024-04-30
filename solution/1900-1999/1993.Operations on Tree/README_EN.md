# [1993. Operations on Tree](https://leetcode.com/problems/operations-on-tree)

[中文文档](/solution/1900-1999/1993.Operations%20on%20Tree/README.md)

<!-- tags:Tree,Depth-First Search,Breadth-First Search,Design,Array,Hash Table -->

## Description

<p>You are given a tree with <code>n</code> nodes numbered from <code>0</code> to <code>n - 1</code> in the form of a parent array <code>parent</code> where <code>parent[i]</code> is the parent of the <code>i<sup>th</sup></code> node. The root of the tree is node <code>0</code>, so <code>parent[0] = -1</code> since it has no parent. You want to design a data structure that allows users to lock, unlock, and upgrade nodes in the tree.</p>

<p>The data structure should support the following functions:</p>

<ul>
	<li><strong>Lock:</strong> <strong>Locks</strong> the given node for the given user and prevents other users from locking the same node. You may only lock a node using this function if the node is unlocked.</li>
	<li><strong>Unlock: Unlocks</strong> the given node for the given user. You may only unlock a node using this function if it is currently locked by the same user.</li>
	<li><b>Upgrade</b><strong>: Locks</strong> the given node for the given user and <strong>unlocks</strong> all of its descendants <strong>regardless</strong> of who locked it. You may only upgrade a node if <strong>all</strong> 3 conditions are true:
	<ul>
		<li>The node is unlocked,</li>
		<li>It has at least one locked descendant (by <strong>any</strong> user), and</li>
		<li>It does not have any locked ancestors.</li>
	</ul>
	</li>
</ul>

<p>Implement the <code>LockingTree</code> class:</p>

<ul>
	<li><code>LockingTree(int[] parent)</code> initializes the data structure with the parent array.</li>
	<li><code>lock(int num, int user)</code> returns <code>true</code> if it is possible for the user with id <code>user</code> to lock the node <code>num</code>, or <code>false</code> otherwise. If it is possible, the node <code>num</code> will become<strong> locked</strong> by the user with id <code>user</code>.</li>
	<li><code>unlock(int num, int user)</code> returns <code>true</code> if it is possible for the user with id <code>user</code> to unlock the node <code>num</code>, or <code>false</code> otherwise. If it is possible, the node <code>num</code> will become <strong>unlocked</strong>.</li>
	<li><code>upgrade(int num, int user)</code> returns <code>true</code> if it is possible for the user with id <code>user</code> to upgrade the node <code>num</code>, or <code>false</code> otherwise. If it is possible, the node <code>num</code> will be <strong>upgraded</strong>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1993.Operations%20on%20Tree/images/untitled.png" style="width: 375px; height: 246px;" />
<pre>
<strong>Input</strong>
[&quot;LockingTree&quot;, &quot;lock&quot;, &quot;unlock&quot;, &quot;unlock&quot;, &quot;lock&quot;, &quot;upgrade&quot;, &quot;lock&quot;]
[[[-1, 0, 0, 1, 1, 2, 2]], [2, 2], [2, 3], [2, 2], [4, 5], [0, 1], [0, 1]]
<strong>Output</strong>
[null, true, false, true, true, true, false]

<strong>Explanation</strong>
LockingTree lockingTree = new LockingTree([-1, 0, 0, 1, 1, 2, 2]);
lockingTree.lock(2, 2); // return true because node 2 is unlocked.
// Node 2 will now be locked by user 2.
lockingTree.unlock(2, 3); // return false because user 3 cannot unlock a node locked by user 2.
lockingTree.unlock(2, 2); // return true because node 2 was previously locked by user 2.
// Node 2 will now be unlocked.
lockingTree.lock(4, 5); // return true because node 4 is unlocked.
// Node 4 will now be locked by user 5.
lockingTree.upgrade(0, 1); // return true because node 0 is unlocked and has at least one locked descendant (node 4).
// Node 0 will now be locked by user 1 and node 4 will now be unlocked.
lockingTree.lock(0, 1); // return false because node 0 is already locked.

</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == parent.length</code></li>
	<li><code>2 &lt;= n &lt;= 2000</code></li>
	<li><code>0 &lt;= parent[i] &lt;= n - 1</code> for <code>i != 0</code></li>
	<li><code>parent[0] == -1</code></li>
	<li><code>0 &lt;= num &lt;= n - 1</code></li>
	<li><code>1 &lt;= user &lt;= 10<sup>4</sup></code></li>
	<li><code>parent</code> represents a valid tree.</li>
	<li>At most <code>2000</code> calls <strong>in total</strong> will be made to <code>lock</code>, <code>unlock</code>, and <code>upgrade</code>.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class LockingTree:
    def __init__(self, parent: List[int]):
        n = len(parent)
        self.locked = [-1] * n
        self.parent = parent
        self.children = [[] for _ in range(n)]
        for son, fa in enumerate(parent[1:], 1):
            self.children[fa].append(son)

    def lock(self, num: int, user: int) -> bool:
        if self.locked[num] == -1:
            self.locked[num] = user
            return True
        return False

    def unlock(self, num: int, user: int) -> bool:
        if self.locked[num] == user:
            self.locked[num] = -1
            return True
        return False

    def upgrade(self, num: int, user: int) -> bool:
        def dfs(x: int):
            nonlocal find
            for y in self.children[x]:
                if self.locked[y] != -1:
                    self.locked[y] = -1
                    find = True
                dfs(y)

        x = num
        while x != -1:
            if self.locked[x] != -1:
                return False
            x = self.parent[x]

        find = False
        dfs(num)
        if not find:
            return False
        self.locked[num] = user
        return True


# Your LockingTree object will be instantiated and called as such:
# obj = LockingTree(parent)
# param_1 = obj.lock(num,user)
# param_2 = obj.unlock(num,user)
# param_3 = obj.upgrade(num,user)
```

```java
class LockingTree {
    private int[] locked;
    private int[] parent;
    private List<Integer>[] children;

    public LockingTree(int[] parent) {
        int n = parent.length;
        locked = new int[n];
        this.parent = parent;
        children = new List[n];
        Arrays.fill(locked, -1);
        Arrays.setAll(children, i -> new ArrayList<>());
        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }
    }

    public boolean lock(int num, int user) {
        if (locked[num] == -1) {
            locked[num] = user;
            return true;
        }
        return false;
    }

    public boolean unlock(int num, int user) {
        if (locked[num] == user) {
            locked[num] = -1;
            return true;
        }
        return false;
    }

    public boolean upgrade(int num, int user) {
        int x = num;
        while (x != -1) {
            if (locked[x] != -1) {
                return false;
            }
            x = parent[x];
        }
        boolean[] find = new boolean[1];
        dfs(num, find);
        if (!find[0]) {
            return false;
        }
        locked[num] = user;
        return true;
    }

    private void dfs(int x, boolean[] find) {
        for (int y : children[x]) {
            if (locked[y] != -1) {
                locked[y] = -1;
                find[0] = true;
            }
            dfs(y, find);
        }
    }
}

/**
 * Your LockingTree object will be instantiated and called as such:
 * LockingTree obj = new LockingTree(parent);
 * boolean param_1 = obj.lock(num,user);
 * boolean param_2 = obj.unlock(num,user);
 * boolean param_3 = obj.upgrade(num,user);
 */
```

```cpp
class LockingTree {
public:
    LockingTree(vector<int>& parent) {
        int n = parent.size();
        locked = vector<int>(n, -1);
        this->parent = parent;
        children.resize(n);
        for (int i = 1; i < n; ++i) {
            children[parent[i]].push_back(i);
        }
    }

    bool lock(int num, int user) {
        if (locked[num] == -1) {
            locked[num] = user;
            return true;
        }
        return false;
    }

    bool unlock(int num, int user) {
        if (locked[num] == user) {
            locked[num] = -1;
            return true;
        }
        return false;
    }

    bool upgrade(int num, int user) {
        int x = num;
        while (x != -1) {
            if (locked[x] != -1) {
                return false;
            }
            x = parent[x];
        }
        bool find = false;
        function<void(int)> dfs = [&](int x) {
            for (int y : children[x]) {
                if (locked[y] != -1) {
                    find = true;
                    locked[y] = -1;
                }
                dfs(y);
            }
        };
        dfs(num);
        if (!find) {
            return false;
        }
        locked[num] = user;
        return true;
    }

private:
    vector<int> locked;
    vector<int> parent;
    vector<vector<int>> children;
};

/**
 * Your LockingTree object will be instantiated and called as such:
 * LockingTree* obj = new LockingTree(parent);
 * bool param_1 = obj->lock(num,user);
 * bool param_2 = obj->unlock(num,user);
 * bool param_3 = obj->upgrade(num,user);
 */
```

```go
type LockingTree struct {
	locked   []int
	parent   []int
	children [][]int
}

func Constructor(parent []int) LockingTree {
	n := len(parent)
	locked := make([]int, n)
	for i := range locked {
		locked[i] = -1
	}
	children := make([][]int, n)
	for i := 1; i < n; i++ {
		children[parent[i]] = append(children[parent[i]], i)
	}
	return LockingTree{locked, parent, children}
}

func (this *LockingTree) Lock(num int, user int) bool {
	if this.locked[num] == -1 {
		this.locked[num] = user
		return true
	}
	return false
}

func (this *LockingTree) Unlock(num int, user int) bool {
	if this.locked[num] == user {
		this.locked[num] = -1
		return true
	}
	return false
}

func (this *LockingTree) Upgrade(num int, user int) bool {
	x := num
	for ; x != -1; x = this.parent[x] {
		if this.locked[x] != -1 {
			return false
		}
	}
	find := false
	var dfs func(int)
	dfs = func(x int) {
		for _, y := range this.children[x] {
			if this.locked[y] != -1 {
				find = true
				this.locked[y] = -1
			}
			dfs(y)
		}
	}
	dfs(num)
	if !find {
		return false
	}
	this.locked[num] = user
	return true
}

/**
 * Your LockingTree object will be instantiated and called as such:
 * obj := Constructor(parent);
 * param_1 := obj.Lock(num,user);
 * param_2 := obj.Unlock(num,user);
 * param_3 := obj.Upgrade(num,user);
 */
```

```ts
class LockingTree {
    private locked: number[];
    private parent: number[];
    private children: number[][];

    constructor(parent: number[]) {
        const n = parent.length;
        this.locked = Array(n).fill(-1);
        this.parent = parent;
        this.children = Array(n)
            .fill(0)
            .map(() => []);
        for (let i = 1; i < n; i++) {
            this.children[parent[i]].push(i);
        }
    }

    lock(num: number, user: number): boolean {
        if (this.locked[num] === -1) {
            this.locked[num] = user;
            return true;
        }
        return false;
    }

    unlock(num: number, user: number): boolean {
        if (this.locked[num] === user) {
            this.locked[num] = -1;
            return true;
        }
        return false;
    }

    upgrade(num: number, user: number): boolean {
        let x = num;
        for (; x !== -1; x = this.parent[x]) {
            if (this.locked[x] !== -1) {
                return false;
            }
        }
        let find = false;
        const dfs = (x: number) => {
            for (const y of this.children[x]) {
                if (this.locked[y] !== -1) {
                    this.locked[y] = -1;
                    find = true;
                }
                dfs(y);
            }
        };
        dfs(num);
        if (!find) {
            return false;
        }
        this.locked[num] = user;
        return true;
    }
}

/**
 * Your LockingTree object will be instantiated and called as such:
 * var obj = new LockingTree(parent)
 * var param_1 = obj.lock(num,user)
 * var param_2 = obj.unlock(num,user)
 * var param_3 = obj.upgrade(num,user)
 */
```

<!-- tabs:end -->

<!-- end -->
