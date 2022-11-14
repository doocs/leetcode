# [863. All Nodes Distance K in Binary Tree](https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree)

[中文文档](/solution/0800-0899/0863.All%20Nodes%20Distance%20K%20in%20Binary%20Tree/README.md)

## Description

<p>Given the <code>root</code> of a binary tree, the value of a target node <code>target</code>, and an integer <code>k</code>, return <em>an array of the values of all nodes that have a distance </em><code>k</code><em> from the target node.</em></p>

<p>You can return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0863.All%20Nodes%20Distance%20K%20in%20Binary%20Tree/images/sketch0.png" style="width: 500px; height: 429px;" />
<pre>
<strong>Input:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
<strong>Output:</strong> [7,4,1]
Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [1], target = 1, k = 3
<strong>Output:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 500]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 500</code></li>
	<li>All the values <code>Node.val</code> are <strong>unique</strong>.</li>
	<li><code>target</code> is the value of one of the nodes in the tree.</li>
	<li><code>0 &lt;= k &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def distanceK(self, root: TreeNode, target: TreeNode, k: int) -> List[int]:
        def parents(root, prev):
            nonlocal p
            if root is None:
                return
            p[root] = prev
            parents(root.left, root)
            parents(root.right, root)

        def dfs(root, k):
            nonlocal ans, vis
            if root is None or root.val in vis:
                return
            vis.add(root.val)
            if k == 0:
                ans.append(root.val)
                return
            dfs(root.left, k - 1)
            dfs(root.right, k - 1)
            dfs(p[root], k - 1)

        p = {}
        parents(root, None)
        ans = []
        vis = set()
        dfs(target, k)
        return ans
```

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def distanceK(self, root: TreeNode, target: TreeNode, k: int) -> List[int]:
        def dfs1(root, fa):
            if root is None:
                return
            p[root] = fa
            dfs1(root.left, root)
            dfs1(root.right, root)

        def dfs2(root, fa, k):
            if root is None:
                return
            if k == 0:
                ans.append(root.val)
                return
            for nxt in (root.left, root.right, p[root]):
                if nxt != fa:
                    dfs2(nxt, root, k - 1)

        p = {}
        dfs1(root, None)
        ans = []
        dfs2(target, None, k)
        return ans
```

### **Java**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private Map<TreeNode, TreeNode> p;
    private Set<Integer> vis;
    private List<Integer> ans;

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        p = new HashMap<>();
        vis = new HashSet<>();
        ans = new ArrayList<>();
        parents(root, null);
        dfs(target, k);
        return ans;
    }

    private void parents(TreeNode root, TreeNode prev) {
        if (root == null) {
            return;
        }
        p.put(root, prev);
        parents(root.left, root);
        parents(root.right, root);
    }

    private void dfs(TreeNode root, int k) {
        if (root == null || vis.contains(root.val)) {
            return;
        }
        vis.add(root.val);
        if (k == 0) {
            ans.add(root.val);
            return;
        }
        dfs(root.left, k - 1);
        dfs(root.right, k - 1);
        dfs(p.get(root), k - 1);
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
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    unordered_map<TreeNode*, TreeNode*> p;
    unordered_set<int> vis;
    vector<int> ans;

    vector<int> distanceK(TreeNode* root, TreeNode* target, int k) {
        parents(root, nullptr);
        dfs(target, k);
        return ans;
    }

    void parents(TreeNode* root, TreeNode* prev) {
        if (!root) return;
        p[root] = prev;
        parents(root->left, root);
        parents(root->right, root);
    }

    void dfs(TreeNode* root, int k) {
        if (!root || vis.count(root->val)) return;
        vis.insert(root->val);
        if (k == 0) {
            ans.push_back(root->val);
            return;
        }
        dfs(root->left, k - 1);
        dfs(root->right, k - 1);
        dfs(p[root], k - 1);
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
func distanceK(root *TreeNode, target *TreeNode, k int) []int {
	p := make(map[*TreeNode]*TreeNode)
	vis := make(map[int]bool)
	var ans []int
	var parents func(root, prev *TreeNode)
	parents = func(root, prev *TreeNode) {
		if root == nil {
			return
		}
		p[root] = prev
		parents(root.Left, root)
		parents(root.Right, root)
	}
	parents(root, nil)
	var dfs func(root *TreeNode, k int)
	dfs = func(root *TreeNode, k int) {
		if root == nil || vis[root.Val] {
			return
		}
		vis[root.Val] = true
		if k == 0 {
			ans = append(ans, root.Val)
			return
		}
		dfs(root.Left, k-1)
		dfs(root.Right, k-1)
		dfs(p[root], k-1)
	}
	dfs(target, k)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
