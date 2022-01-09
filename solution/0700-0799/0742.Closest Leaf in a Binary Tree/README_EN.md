# [742. Closest Leaf in a Binary Tree](https://leetcode.com/problems/closest-leaf-in-a-binary-tree)

[中文文档](/solution/0700-0799/0742.Closest%20Leaf%20in%20a%20Binary%20Tree/README.md)

## Description

<p>Given a binary tree <b>where every node has a unique value</b>, and a target key <code>k</code>, find the value of the nearest leaf node to target <code>k</code> in the tree.

</p><p>

Here, <i>nearest</i> to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree. Also, a node is called a <i>leaf</i> if it has no children.

</p><p>

In the following examples, the input tree is represented in flattened form row by row.

The actual <code>root</code> tree given will be a TreeNode object.

</p><p>

<b>Example 1:</b>

<pre>

<b>Input:</b>

root = [1, 3, 2], k = 1

Diagram of binary tree:

          1

         / \

        3   2



<b>Output:</b> 2 (or 3)



<b>Explanation:</b> Either 2 or 3 is the nearest leaf node to the target of 1.

</pre>

</p><p>

<b>Example 2:</b>

<pre>

<b>Input:</b>

root = [1], k = 1

<b>Output:</b> 1



<b>Explanation:</b> The nearest leaf node is the root node itself.

</pre>

</p>

<p>

<b>Example 3:</b>

<pre>

<b>Input:</b>

root = [1,2,3,4,null,null,null,5,null,6], k = 2

Diagram of binary tree:

             1

            / \

           2   3

          /

         4

        /

       5

      /

     6



<b>Output:</b> 3

<b>Explanation:</b> The leaf node with value 3 (and not the leaf node with value 6) is nearest to the node with value 2.

</pre>

</p>

<p><b>Note:</b><br>

<ol>

<li><code>root</code> represents a binary tree with at least <code>1</code> node and at most <code>1000</code> nodes.</li>

<li>Every node has a unique <code>node.val</code> in range <code>[1, 1000]</code>.</li>

<li>There exists some node in the given binary tree for which <code>node.val == k</code>.</li>

</ol>

</p>

## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def findClosestLeaf(self, root: TreeNode, k: int) -> int:
        def dfs(root, p):
            if root:
                g[root].append(p)
                g[p].append(root)
                dfs(root.left, root)
                dfs(root.right, root)

        g = defaultdict(list)
        dfs(root, None)
        q = deque([node for node in g if node and node.val == k])
        seen = set()
        while q:
            node = q.popleft()
            seen.add(node)
            if node:
                if node.left is None and node.right is None:
                    return node.val
                for next in g[node]:
                    if next not in seen:
                        q.append(next)
```

### **Java**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private Map<TreeNode, List<TreeNode>> g;

    public int findClosestLeaf(TreeNode root, int k) {
        g = new HashMap<>();
        dfs(root, null);
        Deque<TreeNode> q = new LinkedList<>();
        for (Map.Entry<TreeNode, List<TreeNode>> entry : g.entrySet()) {
            if (entry.getKey() != null && entry.getKey().val == k) {
                q.offer(entry.getKey());
                break;
            }
        }
        Set<TreeNode> seen = new HashSet<>();
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            seen.add(node);
            if (node != null) {
                if (node.left == null && node.right == null) {
                    return node.val;
                }
                for (TreeNode next : g.get(node)) {
                    if (!seen.contains(next)) {
                        q.offer(next);
                    }
                }
            }
        }
        return 0;
    }

    private void dfs(TreeNode root, TreeNode p) {
        if (root != null) {
            g.computeIfAbsent(root, k -> new ArrayList<>()).add(p);
            g.computeIfAbsent(p, k -> new ArrayList<>()).add(root);
            dfs(root.left, root);
            dfs(root.right, root);
        }
    }
}
```

### **C++**

```cpp
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    unordered_map<TreeNode*, vector<TreeNode*>> g;

    int findClosestLeaf(TreeNode* root, int k) {
        dfs(root, nullptr);
        queue<TreeNode*> q;
        for (auto& e : g)
        {
            if (e.first && e.first->val == k)
            {
                q.push(e.first);
                break;
            }
        }
        unordered_set<TreeNode*> seen;
        while (!q.empty())
        {
            auto node = q.front();
            q.pop();
            seen.insert(node);
            if (node)
            {
                if (!node->left && !node->right) return node->val;
                for (auto next : g[node])
                {
                    if (!seen.count(next))
                        q.push(next);
                }
            }
        }
        return 0;
    }

    void dfs(TreeNode* root, TreeNode* p) {
        if (!root) return;
        g[root].push_back(p);
        g[p].push_back(root);
        dfs(root->left, root);
        dfs(root->right, root);
    }
};
```

### **Go**

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func findClosestLeaf(root *TreeNode, k int) int {
	g := make(map[*TreeNode][]*TreeNode)
	var dfs func(root, p *TreeNode)
	dfs = func(root, p *TreeNode) {
		if root == nil {
			return
		}
		g[root] = append(g[root], p)
		g[p] = append(g[p], root)
		dfs(root.Left, root)
		dfs(root.Right, root)
	}
	dfs(root, nil)
	var q []*TreeNode
	for t, _ := range g {
		if t != nil && t.Val == k {
			q = append(q, t)
			break
		}
	}
	seen := make(map[*TreeNode]bool)
	for len(q) > 0 {
		node := q[0]
		q = q[1:]
		seen[node] = true
		if node != nil {
			if node.Left == nil && node.Right == nil {
				return node.Val
			}
			for _, next := range g[node] {
				if !seen[next] {
					q = append(q, next)
				}
			}
		}
	}
	return 0
}
```

### **...**

```

```

<!-- tabs:end -->
