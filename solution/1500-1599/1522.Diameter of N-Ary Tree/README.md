---
comments: true
difficulty: 中等
tags:
    - 树
    - 深度优先搜索
    - 树形 DP
---

<!-- problem:start -->

# [1522. N 叉树的直径 🔒](https://leetcode.cn/problems/diameter-of-n-ary-tree)

[English Version](/solution/1500-1599/1522.Diameter%20of%20N-Ary%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一棵 <code>N 叉树</code> 的根节点&nbsp;<code>root</code>&nbsp;，计算这棵树的直径长度。</p>

<p>N 叉树的直径指的是树中任意两个节点间路径中<strong> 最长 </strong>路径的长度。这条路径可能经过根节点，也可能不经过根节点。</p>

<p><em>（N 叉树的输入序列以层序遍历的形式给出，每组子节点用 null 分隔）</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1522.Diameter%20of%20N-Ary%20Tree/images/sample_2_1897.png" style="height:173px; width:324px" /></p>

<pre>
<strong>输入：</strong>root = [1,null,3,2,4,null,5,6]
<strong>输出：</strong>3
<strong>解释：</strong>直径如图中红线所示。</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1522.Diameter%20of%20N-Ary%20Tree/images/sample_1_1897.png" style="height:246px; width:253px" /></strong></p>

<pre>
<strong>输入：</strong>root = [1,null,2,null,3,4,null,5,null,6]
<strong>输出：</strong>4
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1522.Diameter%20of%20N-Ary%20Tree/images/sample_3_1897.png" style="height:326px; width:369px" /></p>

<pre>
<strong>输入:</strong> root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
<strong>输出:</strong> 7
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>N 叉树的深度小于或等于&nbsp;<code>1000</code>&nbsp;。</li>
	<li>节点的总个数在&nbsp;<code>[0,&nbsp;10^4]</code>&nbsp;间。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS（后序求直径）

<!-- thinking:start -->

> **思考**
>
> 求 $N$ 叉树直径，即最远两点的距离。树的规模可达数千，两次任意遍历若实现不当会重复走边。直径必经过某节点的两条最深子树路径。
>
> 后序 DFS 对每个节点收集子节点高度，用最大的两个高度之和更新全局直径，并返回该节点高度。一次遍历同时得到所有候选，无需建额外图。

<!-- thinking:end -->

后序遍历每个节点，记录两棵最深子树的高度 $m_1$、 $m_2$，用 $m_1+m_2$ 更新直径。子树高度为最深孩子高度加 $1$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为节点个数。

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：建图 + 两次 DFS

<!-- thinking:start -->

> **思考**
>
> 方法一把直径计算耦合在树形递归里，若输入只保证邻接关系、或希望复用一般图的直径算法，则不够直接。先把 $N$ 叉树展开为无向图，任选一点找最远点，再从该点找最远点，第二次距离即为直径。两次 DFS 与树形 DP 渐近相同，只是换了一种实现。

<!-- thinking:end -->

把 $N$ 叉树建成无向图，从任意节点 DFS 找到最远点，再从该点 DFS 一次，两次搜索得到的最远距离即为树的直径。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为节点个数。

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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
        if (ans < t) {
            ans = t;
            next = u;
        }
        if (g.count(u))
            for (Node* v : g[u])
                dfs(v, t + 1);
    }

    void build(Node* root) {
        if (!root) return;
        for (Node* child : root->children) {
            g[root].insert(child);
            g[child].insert(root);
            build(child);
        }
    }
};
```

#### Go

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法三：显式栈（后序求直径）

<!-- thinking:start -->

> **思考**
>
> 求 $N$ 叉树直径，即任意两点间的最长路径长度。深度可达 $1000$，沿一条单孩子链做后序递归时，调用深度等于节点数，链长达到 $1000$ 就会超出 Python 的递归上限。每个节点只依赖孩子已经算完的高度：用最大的两个孩子高度之和更新直径，自身高度是最深孩子高度加 $1$。因此用栈保存节点和状态。状态 $0$ 先压入结束标记再压入孩子，孩子先完成；状态 $1$ 读取孩子高度，更新直径，并记下当前节点的高度。

<!-- thinking:end -->

从根开始做后序遍历。栈里存放节点和状态。状态为 $0$ 时，把同一节点以状态 $1$ 压回，再把非空孩子压入。状态为 $1$ 时，孩子高度都已写入表中，取出最大的两个 $m_1$、$m_2$，用 $m_1+m_2$ 更新直径，并把当前高度记为 $m_1+1$。根为空时直径为 $0$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为节点个数。

<!-- tabs:start -->

#### Python3

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
        if root is None:
            return 0
        ans = 0
        height = {}
        stk = [(root, 0)]
        while stk:
            node, state = stk.pop()
            if state == 0:
                stk.append((node, 1))
                for child in reversed(node.children):
                    if child is not None:
                        stk.append((child, 0))
            else:
                m1 = m2 = 0
                for child in node.children:
                    t = height.get(child, 0)
                    if t > m1:
                        m2, m1 = m1, t
                    elif t > m2:
                        m2 = t
                ans = max(ans, m1 + m2)
                height[node] = m1 + 1
        return ans
```

#### Java

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
    public int diameter(Node root) {
        if (root == null) {
            return 0;
        }
        int ans = 0;
        Map<Node, Integer> height = new HashMap<>();
        Deque<Node> nodes = new ArrayDeque<>();
        Deque<Integer> states = new ArrayDeque<>();
        nodes.push(root);
        states.push(0);
        while (!nodes.isEmpty()) {
            Node node = nodes.pop();
            int state = states.pop();
            if (state == 0) {
                nodes.push(node);
                states.push(1);
                List<Node> children = node.children;
                for (int i = children.size() - 1; i >= 0; --i) {
                    Node child = children.get(i);
                    if (child != null) {
                        nodes.push(child);
                        states.push(0);
                    }
                }
            } else {
                int m1 = 0, m2 = 0;
                for (Node child : node.children) {
                    int t = height.getOrDefault(child, 0);
                    if (t > m1) {
                        m2 = m1;
                        m1 = t;
                    } else if (t > m2) {
                        m2 = t;
                    }
                }
                ans = Math.max(ans, m1 + m2);
                height.put(node, m1 + 1);
            }
        }
        return ans;
    }
}
```

#### C++

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
    int diameter(Node* root) {
        if (!root) {
            return 0;
        }
        int ans = 0;
        unordered_map<Node*, int> height;
        vector<pair<Node*, int>> stk{{root, 0}};
        while (!stk.empty()) {
            auto [node, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                stk.push_back({node, 1});
                for (int i = (int) node->children.size() - 1; i >= 0; --i) {
                    Node* child = node->children[i];
                    if (child) {
                        stk.push_back({child, 0});
                    }
                }
            } else {
                int m1 = 0, m2 = 0;
                for (Node* child : node->children) {
                    int t = child ? height[child] : 0;
                    if (t > m1) {
                        m2 = m1;
                        m1 = t;
                    } else if (t > m2) {
                        m2 = t;
                    }
                }
                ans = max(ans, m1 + m2);
                height[node] = m1 + 1;
            }
        }
        return ans;
    }
};
```

#### Go

```go
/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Children []*Node
 * }
 */

func diameter(root *Node) int {
	if root == nil {
		return 0
	}
	type frame struct {
		node  *Node
		state int
	}
	ans := 0
	height := map[*Node]int{}
	stk := []frame{{root, 0}}
	for len(stk) > 0 {
		f := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		if f.state == 0 {
			stk = append(stk, frame{f.node, 1})
			children := f.node.Children
			for i := len(children) - 1; i >= 0; i-- {
				if children[i] != nil {
					stk = append(stk, frame{children[i], 0})
				}
			}
		} else {
			m1, m2 := 0, 0
			for _, child := range f.node.Children {
				t := height[child]
				if t > m1 {
					m2, m1 = m1, t
				} else if t > m2 {
					m2 = t
				}
			}
			ans = max(ans, m1+m2)
			height[f.node] = m1 + 1
		}
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法四：建图 + 两次显式栈

<!-- thinking:start -->

> **思考**
>
> 方法一已经在树上用孩子高度求出直径。若希望沿用一般树的两次最远点搜索，需要先把 $N$ 叉树展开成无向图。建图和两次搜索若沿链递归，深度同样达到 $1000$。建图用栈按父子边连成无向边，搜索时在压栈时标记，避免从无向边走回父亲。第一次从根走到最远点，第二次从该点再走到最远点，第二次的距离就是直径。

<!-- thinking:end -->

先用栈遍历整棵树，把每条父子边写成两条无向边。然后做两次显式栈搜索：从起点出发，弹出节点时用当前距离更新最远点，并把尚未访问的邻居连同距离加一压入栈。第一次起点是根，第二次起点是第一次得到的最远点。第二次的最远距离即为直径。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为节点个数。

<!-- tabs:start -->

#### Python3

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
        if root is None:
            return 0
        g = defaultdict(list)
        seen = {root}
        stk = [root]
        while stk:
            u = stk.pop()
            for child in u.children:
                if child is None or child in seen:
                    continue
                seen.add(child)
                g[u].append(child)
                g[child].append(u)
                stk.append(child)

        def farthest(start):
            vis = {start}
            walk = [(start, 0)]
            best, node = 0, start
            while walk:
                u, t = walk.pop()
                if t > best:
                    best, node = t, u
                for v in g[u]:
                    if v not in vis:
                        vis.add(v)
                        walk.append((v, t + 1))
            return best, node

        _, nxt = farthest(root)
        ans, _ = farthest(nxt)
        return ans
```

#### Java

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
    public int diameter(Node root) {
        if (root == null) {
            return 0;
        }
        Map<Node, List<Node>> g = new HashMap<>();
        Set<Node> seen = new HashSet<>();
        Deque<Node> stk = new ArrayDeque<>();
        stk.push(root);
        seen.add(root);
        while (!stk.isEmpty()) {
            Node u = stk.pop();
            for (Node child : u.children) {
                if (child == null || !seen.add(child)) {
                    continue;
                }
                g.computeIfAbsent(u, k -> new ArrayList<>()).add(child);
                g.computeIfAbsent(child, k -> new ArrayList<>()).add(u);
                stk.push(child);
            }
        }
        Node[] nxt = new Node[] {root};
        farthest(g, root, nxt);
        return farthest(g, nxt[0], nxt);
    }

    private int farthest(Map<Node, List<Node>> g, Node start, Node[] nxt) {
        Set<Node> vis = new HashSet<>();
        Deque<Node> nodes = new ArrayDeque<>();
        Deque<Integer> dist = new ArrayDeque<>();
        nodes.push(start);
        dist.push(0);
        vis.add(start);
        int best = 0;
        nxt[0] = start;
        while (!nodes.isEmpty()) {
            Node u = nodes.pop();
            int t = dist.pop();
            if (t > best) {
                best = t;
                nxt[0] = u;
            }
            List<Node> vs = g.get(u);
            if (vs == null) {
                continue;
            }
            for (Node v : vs) {
                if (vis.add(v)) {
                    nodes.push(v);
                    dist.push(t + 1);
                }
            }
        }
        return best;
    }
}
```

#### C++

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
    int diameter(Node* root) {
        if (!root) {
            return 0;
        }
        unordered_map<Node*, vector<Node*>> g;
        unordered_set<Node*> seen{root};
        vector<Node*> stk{root};
        while (!stk.empty()) {
            Node* u = stk.back();
            stk.pop_back();
            for (Node* child : u->children) {
                if (!child || seen.count(child)) {
                    continue;
                }
                seen.insert(child);
                g[u].push_back(child);
                g[child].push_back(u);
                stk.push_back(child);
            }
        }
        auto farthest = [&](Node* start) {
            unordered_set<Node*> vis{start};
            vector<pair<Node*, int>> walk{{start, 0}};
            int best = 0;
            Node* node = start;
            while (!walk.empty()) {
                auto [u, t] = walk.back();
                walk.pop_back();
                if (t > best) {
                    best = t;
                    node = u;
                }
                for (Node* v : g[u]) {
                    if (!vis.count(v)) {
                        vis.insert(v);
                        walk.push_back({v, t + 1});
                    }
                }
            }
            return pair<int, Node*>{best, node};
        };
        Node* nxt = farthest(root).second;
        return farthest(nxt).first;
    }
};
```

#### Go

```go
/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Children []*Node
 * }
 */

func diameter(root *Node) int {
	if root == nil {
		return 0
	}
	g := map[*Node][]*Node{}
	seen := map[*Node]bool{root: true}
	stk := []*Node{root}
	for len(stk) > 0 {
		u := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		for _, child := range u.Children {
			if child == nil || seen[child] {
				continue
			}
			seen[child] = true
			g[u] = append(g[u], child)
			g[child] = append(g[child], u)
			stk = append(stk, child)
		}
	}
	farthest := func(start *Node) (int, *Node) {
		type frame struct {
			node *Node
			dist int
		}
		vis := map[*Node]bool{start: true}
		walk := []frame{{start, 0}}
		best, node := 0, start
		for len(walk) > 0 {
			f := walk[len(walk)-1]
			walk = walk[:len(walk)-1]
			if f.dist > best {
				best, node = f.dist, f.node
			}
			for _, v := range g[f.node] {
				if !vis[v] {
					vis[v] = true
					walk = append(walk, frame{v, f.dist + 1})
				}
			}
		}
		return best, node
	}
	_, nxt := farthest(root)
	ans, _ := farthest(nxt)
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
