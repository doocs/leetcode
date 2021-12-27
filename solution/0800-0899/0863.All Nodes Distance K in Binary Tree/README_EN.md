# [863. All Nodes Distance K in Binary Tree](https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree)

[中文文档](/solution/0800-0899/0863.All%20Nodes%20Distance%20K%20in%20Binary%20Tree/README.md)

## Description

<p>We are given a binary tree (with root node&nbsp;<code>root</code>), a <code>target</code> node, and an integer value <code>K</code>.</p>

<p>Return a list of the values of all&nbsp;nodes that have a distance <code>K</code> from the <code>target</code> node.&nbsp; The answer can be returned in any order.</p>

<p>&nbsp;</p>

<ol>

</ol>

<div>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>root = <span id="example-input-1-1">[3,5,1,6,2,0,8,null,null,7,4]</span>, target = <span id="example-input-1-2">5</span>, K = <span id="example-input-1-3">2</span>



<strong>Output: </strong><span id="example-output-1">[7,4,1]</span>



<strong>Explanation: </strong>

The nodes that are a distance 2 from the target node (with value 5)

have values 7, 4, and 1.



<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0863.All%20Nodes%20Distance%20K%20in%20Binary%20Tree/images/sketch0.png" style="width: 280px; height: 240px;" />



Note that the inputs &quot;root&quot; and &quot;target&quot; are actually TreeNodes.

The descriptions of the inputs above are just serializations of these objects.

</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li>The given tree is non-empty.</li>
	<li>Each node in the tree has unique values&nbsp;<code>0 &lt;= node.val &lt;= 500</code>.</li>
	<li>The <code>target</code>&nbsp;node is a node in the tree.</li>
	<li><code>0 &lt;= K &lt;= 1000</code>.</li>
</ol>

</div>

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
        if (k == 0)
        {
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
