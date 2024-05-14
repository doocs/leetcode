# [429. N 叉树的层序遍历](https://leetcode.cn/problems/n-ary-tree-level-order-traversal)

[English Version](/solution/0400-0499/0429.N-ary%20Tree%20Level%20Order%20Traversal/README_EN.md)

<!-- tags:树,广度优先搜索 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个 N 叉树，返回其节点值的<em>层序遍历</em>。（即从左到右，逐层遍历）。</p>

<p>树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0429.N-ary%20Tree%20Level%20Order%20Traversal/images/narytreeexample.png" style="width: 100%; max-width: 300px;" /></p>

<pre>
<strong>输入：</strong>root = [1,null,3,2,4,null,5,6]
<strong>输出：</strong>[[1],[3,2,4],[5,6]]
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0429.N-ary%20Tree%20Level%20Order%20Traversal/images/sample_4_964.png" style="width: 296px; height: 241px;" /></p>

<pre>
<strong>输入：</strong>root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
<strong>输出：</strong>[[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>树的高度不会超过 <code>1000</code></li>
	<li>树的节点总数在 <code>[0, 10^4]</code> 之间</li>
</ul>

## 解法

### 方法一：BFS

我们首先判断根节点是否为空，若为空则直接返回空列表。

否则，我们创建一个队列 $q$，初始时将根节点加入队列。

当队列不为空时，我们循环以下操作：

1. 创建一个空列表 $t$，用于存放当前层的节点值。
2. 对于队列中的每个节点，将其值加入 $t$ 中，并将其子节点加入队列。
3. 将 $t$ 加入结果列表 $ans$。

最后返回结果列表 $ans$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为 N 叉树的节点数。

<!-- tabs:start -->

```python
"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children
"""


class Solution:
    def levelOrder(self, root: 'Node') -> List[List[int]]:
        ans = []
        if root is None:
            return ans
        q = deque([root])
        while q:
            t = []
            for _ in range(len(q)):
                root = q.popleft()
                t.append(root.val)
                q.extend(root.children)
            ans.append(t)
        return ans
```

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Deque<Node> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            List<Integer> t = new ArrayList<>();
            for (int n = q.size(); n > 0; --n) {
                root = q.poll();
                t.add(root.val);
                q.addAll(root.children);
            }
            ans.add(t);
        }
        return ans;
    }
}
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
    vector<vector<int>> levelOrder(Node* root) {
        vector<vector<int>> ans;
        if (!root) {
            return ans;
        }
        queue<Node*> q{{root}};
        while (!q.empty()) {
            vector<int> t;
            for (int n = q.size(); n; --n) {
                root = q.front();
                q.pop();
                t.push_back(root->val);
                for (auto& child : root->children) {
                    q.push(child);
                }
            }
            ans.push_back(t);
        }
        return ans;
    }
};
```

```go
/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Children []*Node
 * }
 */

func levelOrder(root *Node) (ans [][]int) {
	if root == nil {
		return
	}
	q := []*Node{root}
	for len(q) > 0 {
		var t []int
		for n := len(q); n > 0; n-- {
			root = q[0]
			q = q[1:]
			t = append(t, root.Val)
			for _, child := range root.Children {
				q = append(q, child)
			}
		}
		ans = append(ans, t)
	}
	return
}
```

```ts
/**
 * Definition for node.
 * class Node {
 *     val: number
 *     children: Node[]
 *     constructor(val?: number) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.children = []
 *     }
 * }
 */

function levelOrder(root: Node | null): number[][] {
    const ans: number[][] = [];
    if (!root) {
        return ans;
    }
    const q: Node[] = [root];
    while (q.length) {
        const qq: Node[] = [];
        const t: number[] = [];
        for (const { val, children } of q) {
            qq.push(...children);
            t.push(val);
        }
        ans.push(t);
        q.splice(0, q.length, ...qq);
    }
    return ans;
}
```

<!-- tabs:end -->

### 方法二：DFS

我们可以使用深度优先搜索的方法，遍历整棵树。

我们定义一个辅助函数 $dfs(root, i)$，其中 $root$ 表示当前节点，而 $i$ 表示当前层数。

在 $dfs$ 函数中，我们首先判断 $root$ 是否为空，若为空则直接返回。

否则，我们判断 $ans$ 的长度是否小于等于 $i$，若是则说明当前层还没有加入到 $ans$ 中，我们需要先加入一个空列表。然后将 $root$ 的值加入 $ans[i]$ 中。

接着，我们遍历 $root$ 的所有子节点，对于每个子节点，我们调用 $dfs(child, i + 1)$。

在主函数中，我们调用 $dfs(root, 0)$，并返回 $ans$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为 N 叉树的节点数。

<!-- tabs:start -->

```python
"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children
"""


class Solution:
    def levelOrder(self, root: 'Node') -> List[List[int]]:
        def dfs(root, i):
            if root is None:
                return
            if len(ans) <= i:
                ans.append([])
            ans[i].append(root.val)
            for child in root.children:
                dfs(child, i + 1)

        ans = []
        dfs(root, 0)
        return ans
```

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    private List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> levelOrder(Node root) {
        dfs(root, 0);
        return ans;
    }

    private void dfs(Node root, int i) {
        if (root == null) {
            return;
        }
        if (ans.size() <= i) {
            ans.add(new ArrayList<>());
        }
        ans.get(i++).add(root.val);
        for (Node child : root.children) {
            dfs(child, i);
        }
    }
}
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
    vector<vector<int>> levelOrder(Node* root) {
        vector<vector<int>> ans;
        function<void(Node*, int i)> dfs = [&](Node* root, int i) {
            if (!root) {
                return;
            }
            if (ans.size() <= i) {
                ans.push_back({});
            }
            ans[i++].push_back(root->val);
            for (auto& child : root->children) {
                dfs(child, i);
            }
        };
        dfs(root, 0);
        return ans;
    }
};
```

```go
/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Children []*Node
 * }
 */

func levelOrder(root *Node) (ans [][]int) {
	var dfs func(root *Node, i int)
	dfs = func(root *Node, i int) {
		if root == nil {
			return
		}
		if len(ans) <= i {
			ans = append(ans, []int{})
		}
		ans[i] = append(ans[i], root.Val)
		for _, child := range root.Children {
			dfs(child, i+1)
		}
	}
	dfs(root, 0)
	return
}
```

```ts
/**
 * Definition for node.
 * class Node {
 *     val: number
 *     children: Node[]
 *     constructor(val?: number) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.children = []
 *     }
 * }
 */

function levelOrder(root: Node | null): number[][] {
    const ans: number[][] = [];
    const dfs = (root: Node | null, i: number) => {
        if (root === null) {
            return;
        }
        if (ans.length <= i) {
            ans.push([]);
        }
        const { val, children } = root;
        ans[i++].push(val);
        children.forEach(node => dfs(node, i));
    };
    dfs(root, 0);
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
