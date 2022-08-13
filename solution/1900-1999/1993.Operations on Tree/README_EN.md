# [1993. Operations on Tree](https://leetcode.com/problems/operations-on-tree)

[中文文档](/solution/1900-1999/1993.Operations%20on%20Tree/README.md)

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
<p><strong>Example 1:</strong></p>
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

DFS.

<!-- tabs:start -->

### **Python3**

```python
class LockingTree:
    def __init__(self, parent: List[int]):
        self.nums = {}
        self.parent = parent
        self.children = defaultdict(list)
        for i, p in enumerate(parent):
            self.children[p].append(i)

    def lock(self, num: int, user: int) -> bool:
        if num in self.nums:
            return False
        self.nums[num] = user
        return True

    def unlock(self, num: int, user: int) -> bool:
        if num not in self.nums or self.nums[num] != user:
            return False
        self.nums.pop(num)
        return True

    def upgrade(self, num: int, user: int) -> bool:
        def dfs(num):
            nonlocal find
            for child in self.children[num]:
                if child in self.nums:
                    self.nums.pop(child)
                    find = True
                dfs(child)

        t = num
        while t != -1:
            if t in self.nums:
                return False
            t = self.parent[t]
        find = False
        dfs(num)
        if not find:
            return False
        self.nums[num] = user
        return True


# Your LockingTree object will be instantiated and called as such:
# obj = LockingTree(parent)
# param_1 = obj.lock(num,user)
# param_2 = obj.unlock(num,user)
# param_3 = obj.upgrade(num,user)
```

### **Java**

```java
class LockingTree {
    private Map<Integer, Integer> nums;
    private int[] parent;
    private List<Integer>[] children;

    public LockingTree(int[] parent) {
        nums = new HashMap<>();
        this.parent = parent;
        int n = parent.length;
        children = new List[n];
        for (int i = 0; i < n; ++i) {
            children[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; ++i) {
            if (parent[i] != -1) {
                children[parent[i]].add(i);
            }
        }
    }

    public boolean lock(int num, int user) {
        if (nums.containsKey(num)) {
            return false;
        }
        nums.put(num, user);
        return true;
    }

    public boolean unlock(int num, int user) {
        if (!nums.containsKey(num) || nums.get(num) != user) {
            return false;
        }
        nums.remove(num);
        return true;
    }

    public boolean upgrade(int num, int user) {
        int t = num;
        while (t != -1) {
            if (nums.containsKey(t)) {
                return false;
            }
            t = parent[t];
        }
        boolean[] find = new boolean[1];
        dfs(num, find);
        if (!find[0]) {
            return false;
        }
        nums.put(num, user);
        return true;
    }

    private void dfs(int num, boolean[] find) {
        for (int child : children[num]) {
            if (nums.containsKey(child)) {
                nums.remove(child);
                find[0] = true;
            }
            dfs(child, find);
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

### **C++**

```cpp
class LockingTree {
public:
    unordered_map<int, int> nums;
    vector<int> parent;
    vector<vector<int>> children;

    LockingTree(vector<int>& parent) {
        this->parent = parent;
        int n = parent.size();
        children.resize(n);
        for (int i = 0; i < n; ++i)
            if (parent[i] != -1)
                children[parent[i]].push_back(i);
    }

    bool lock(int num, int user) {
        if (nums.count(num)) return false;
        nums[num] = user;
        return true;
    }

    bool unlock(int num, int user) {
        if (!nums.count(num) || nums[num] != user) return false;
        nums.erase(num);
        return true;
    }

    bool upgrade(int num, int user) {
        for (int t = num; t != -1; t = parent[t])
            if (nums.count(t))
                return false;
        bool find = false;
        dfs(num, find);
        if (!find) return false;
        nums[num] = user;
        return true;
    }

    void dfs(int num, bool& find) {
        for (int child : children[num]) {
            if (nums.count(child)) {
                nums.erase(child);
                find = true;
            }
            dfs(child, find);
        }
    }
};

/**
 * Your LockingTree object will be instantiated and called as such:
 * LockingTree* obj = new LockingTree(parent);
 * bool param_1 = obj->lock(num,user);
 * bool param_2 = obj->unlock(num,user);
 * bool param_3 = obj->upgrade(num,user);
 */
```

### **Go**

```go
type LockingTree struct {
	nums     map[int]int
	parent   []int
	children [][]int
}

func Constructor(parent []int) LockingTree {
	n := len(parent)
	nums := make(map[int]int)
	children := make([][]int, n)
	for i, p := range parent {
		if p != -1 {
			children[p] = append(children[p], i)
		}
	}
	return LockingTree{nums, parent, children}
}

func (this *LockingTree) Lock(num int, user int) bool {
	if _, ok := this.nums[num]; ok {
		return false
	}
	this.nums[num] = user
	return true
}

func (this *LockingTree) Unlock(num int, user int) bool {
	if this.nums[num] != user {
		return false
	}
	delete(this.nums, num)
	return true
}

func (this *LockingTree) Upgrade(num int, user int) bool {
	for t := num; t != -1; t = this.parent[t] {
		if _, ok := this.nums[t]; ok {
			return false
		}
	}
	find := false
	var dfs func(int)
	dfs = func(num int) {
		for _, child := range this.children[num] {
			if _, ok := this.nums[child]; ok {
				delete(this.nums, child)
				find = true
			}
			dfs(child)
		}
	}
	dfs(num)
	if !find {
		return false
	}
	this.nums[num] = user
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

### **...**

```

```

<!-- tabs:end -->
