# [1522. Diameter of N-Ary Tree](https://leetcode.com/problems/diameter-of-n-ary-tree)

[中文文档](/solution/1500-1599/1522.Diameter%20of%20N-Ary%20Tree/README.md)

## Description

<p>Given a <code>root</code> of an <a href="https://leetcode.com/articles/introduction-to-n-ary-trees/" target="_blank">N-ary tree</a>, you need to compute the length of the diameter of the tree.</p>

<p>The diameter of an N-ary tree is the length of the <strong>longest</strong> path between any two nodes in the tree. This path may or may not pass through the root.</p>

<p>(<em>Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value.)</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1522.Diameter%20of%20N-Ary%20Tree/images/sample_2_1897.png" style="width: 324px; height: 173px;" /></p>

<pre>
<strong>Input:</strong> root = [1,null,3,2,4,null,5,6]
<strong>Output:</strong> 3
<strong>Explanation: </strong>Diameter is shown in red color.</pre>

<p><strong class="example">Example 2:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1522.Diameter%20of%20N-Ary%20Tree/images/sample_1_1897.png" style="width: 253px; height: 246px;" /></strong></p>

<pre>
<strong>Input:</strong> root = [1,null,2,null,3,4,null,5,null,6]
<strong>Output:</strong> 4
</pre>

<p><strong class="example">Example 3:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1522.Diameter%20of%20N-Ary%20Tree/images/sample_3_1897.png" style="width: 369px; height: 326px;" /></p>

<pre>
<strong>Input:</strong> root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
<strong>Output:</strong> 7
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The depth of the n-ary tree is less than or equal to <code>1000</code>.</li>
	<li>The total number of nodes is between <code>[1, 10<sup>4</sup>]</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children if children is not None else []
"""


class Solution:
    def diameter(self, root: 'Node') -> int:
        """
        :type root: 'Node'
        :rtype: int
        """

        def dfs(root):
            if root is None:
                return 0
            nonlocal ans
            m1 = m2 = 0
            for child in root.children:
                t = dfs(child)
                if t > m1:
                    m2, m1 = m1, t
                elif t > m2:
                    m2 = t
            ans = max(ans, m1 + m2)
            return 1 + m1

        ans = 0
        dfs(root)
        return ans
```

```python
"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children if children is not None else []
"""


class Solution:
    def diameter(self, root: 'Node') -> int:
        """
        :type root: 'Node'
        :rtype: int
        """
        def build(root):
            nonlocal d
            if root is None:
                return
            for child in root.children:
                d[root].add(child)
                d[child].add(root)
                build(child)

        def dfs(u, t):
            nonlocal ans, vis, d, next
            if u in vis:
                return
            vis.add(u)
            for v in d[u]:
                dfs(v, t + 1)
            if ans < t:
                ans = t
                next = u

        d = defaultdict(set)
        vis = set()
        build(root)
        ans = 0
        next = None
        dfs(root, 0)
        vis.clear()
        dfs(next, 0)
        return ans
```

### **Java**

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;


    public Node() {
        children = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        children = new ArrayList<Node>();
    }

    public Node(int _val,ArrayList<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    private int ans;

    public int diameter(Node root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    private int dfs(Node root) {
        if (root == null) {
            return 0;
        }
        int m1 = 0, m2 = 0;
        for (Node child : root.children) {
            int t = dfs(child);
            if (t > m1) {
                m2 = m1;
                m1 = t;
            } else if (t > m2) {
                m2 = t;
            }
        }
        ans = Math.max(ans, m1 + m2);
        return 1 + m1;
    }
}
```

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;


    public Node() {
        children = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        children = new ArrayList<Node>();
    }

    public Node(int _val,ArrayList<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    private Map<Node, Set<Node>> g;
    private Set<Node> vis;
    private Node next;
    private int ans;

    public int diameter(Node root) {
        g = new HashMap<>();
        build(root);
        vis = new HashSet<>();
        next = root;
        ans = 0;
        dfs(next, 0);
        vis.clear();
        dfs(next, 0);
        return ans;
    }

    private void dfs(Node u, int t) {
        if (vis.contains(u)) {
            return;
        }
        vis.add(u);
        if (t > ans) {
            ans = t;
            next = u;
        }
        if (g.containsKey(u)) {
            for (Node v : g.get(u)) {
                dfs(v, t + 1);
            }
        }
    }

    private void build(Node root) {
        if (root == null) {
            return;
        }
        for (Node child : root.children) {
            g.computeIfAbsent(root, k -> new HashSet<>()).add(child);
            g.computeIfAbsent(child, k -> new HashSet<>()).add(root);
            build(child);
        }
    }
}
```

### **C++**

```cpp
/*
// Definition for a Node.
class Node {
public:
    int val;
    vector<Node*> children;

    Node() {}

    Node(int _val) {
        val = _val;
    }

    Node(int _val, vector<Node*> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
public:
    int ans;

    int diameter(Node* root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    int dfs(Node* root) {
        if (!root) return 0;
        int m1 = 0, m2 = 0;
        for (Node* child : root->children) {
            int t = dfs(child);
            if (t > m1) {
                m2 = m1;
                m1 = t;
            } else if (t > m2)
                m2 = t;
        }
        ans = max(ans, m1 + m2);
        return 1 + m1;
    }
};
```

```cpp
/*
// Definition for a Node.
class Node {
public:
    int val;
    vector<Node*> children;

    Node() {}

    Node(int _val) {
        val = _val;
    }

    Node(int _val, vector<Node*> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
public:
    unordered_map<Node*, unordered_set<Node*>> g;
    unordered_set<Node*> vis;
    Node* next;
    int ans;

    int diameter(Node* root) {
        build(root);
        next = root;
        ans = 0;
        dfs(next, 0);
        vis.clear();
        dfs(next, 0);
        return ans;
    }

    void dfs(Node* u, int t) {
        if (vis.count(u)) return;
        vis.insert(u);
        if (ans < t)
        {
            ans = t;
            next = u;
        }
        if (g.count(u))
            for (Node* v : g[u])
                dfs(v, t + 1);
    }

    void build(Node* root) {
        if (!root) return;
        for (Node* child : root->children)
        {
            g[root].insert(child);
            g[child].insert(root);
            build(child);
        }
    }
};
```

### **Go**

```go
/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Children []*Node
 * }
 */

func diameter(root *Node) int {
	ans := 0
	var dfs func(root *Node) int
	dfs = func(root *Node) int {
		if root == nil {
			return 0
		}
		m1, m2 := 0, 0
		for _, child := range root.Children {
			t := dfs(child)
			if t > m1 {
				m2, m1 = m1, t
			} else if t > m2 {
				m2 = t
			}
		}
		ans = max(ans, m1+m2)
		return 1 + m1
	}
	dfs(root)
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

```go
/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Children []*Node
 * }
 */

func diameter(root *Node) int {
	g := make(map[*Node][]*Node)
	vis := make(map[*Node]bool)
	next := root
	ans := 0
	var build func(root *Node)
	build = func(root *Node) {
		if root == nil {
			return
		}
		for _, child := range root.Children {
			g[root] = append(g[root], child)
			g[child] = append(g[child], root)
			build(child)
		}
	}
	build(root)
	var dfs func(u *Node, t int)
	dfs = func(u *Node, t int) {
		if vis[u] {
			return
		}
		vis[u] = true
		if t > ans {
			ans = t
			next = u
		}
		if vs, ok := g[u]; ok {
			for _, v := range vs {
				dfs(v, t+1)
			}
		}
	}
	dfs(next, 0)
	vis = make(map[*Node]bool)
	dfs(next, 0)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
