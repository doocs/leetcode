# [662. Maximum Width of Binary Tree](https://leetcode.com/problems/maximum-width-of-binary-tree)

[中文文档](/solution/0600-0699/0662.Maximum%20Width%20of%20Binary%20Tree/README.md)

## Description

<p>Given the <code>root</code> of a binary tree, return <em>the <strong>maximum width</strong> of the given tree</em>.</p>

<p>The <strong>maximum width</strong> of a tree is the maximum <strong>width</strong> among all levels.</p>

<p>The <strong>width</strong> of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted into the length calculation.</p>

<p>It is <strong>guaranteed</strong> that the answer will in the range of a <strong>32-bit</strong> signed integer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0662.Maximum%20Width%20of%20Binary%20Tree/images/width1-tree.jpg" style="width: 359px; height: 302px;" />
<pre>
<strong>Input:</strong> root = [1,3,2,5,3,null,9]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The maximum width exists in the third level with length 4 (5,3,null,9).
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0662.Maximum%20Width%20of%20Binary%20Tree/images/maximum-width-of-binary-tree-v3.jpg" style="width: 442px; height: 422px;" />
<pre>
<strong>Input:</strong> root = [1,3,2,5,null,null,9,6,null,7]
<strong>Output:</strong> 7
<strong>Explanation:</strong> The maximum width exists in the fourth level with length 7 (6,null,null,null,null,null,7).
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0662.Maximum%20Width%20of%20Binary%20Tree/images/width3-tree.jpg" style="width: 289px; height: 299px;" />
<pre>
<strong>Input:</strong> root = [1,3,2,5]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The maximum width exists in the second level with length 2 (3,2).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 3000]</code>.</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
</ul>

## Solutions

BFS or DFS.

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
    def widthOfBinaryTree(self, root: Optional[TreeNode]) -> int:
        ans = 0
        q = deque([(root, 1)])
        while q:
            ans = max(ans, q[-1][1] - q[0][1] + 1)
            for _ in range(len(q)):
                root, i = q.popleft()
                if root.left:
                    q.append((root.left, i << 1))
                if root.right:
                    q.append((root.right, i << 1 | 1))
        return ans
```

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def widthOfBinaryTree(self, root: Optional[TreeNode]) -> int:
        def dfs(root, depth, i):
            if root is None:
                return
            if len(t) == depth:
                t.append(i)
            else:
                nonlocal ans
                ans = max(ans, i - t[depth] + 1)
            dfs(root.left, depth + 1, i << 1)
            dfs(root.right, depth + 1, i << 1 | 1)

        ans = 1
        t = []
        dfs(root, 0, 1)
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
    public int widthOfBinaryTree(TreeNode root) {
        Deque<Pair<TreeNode, Integer>> q = new ArrayDeque<>();
        q.offer(new Pair<>(root, 1));
        int ans = 0;
        while (!q.isEmpty()) {
            ans = Math.max(ans, q.peekLast().getValue() - q.peekFirst().getValue() + 1);
            for (int n = q.size(); n > 0; --n) {
                var p = q.pollFirst();
                root = p.getKey();
                int i = p.getValue();
                if (root.left != null) {
                    q.offer(new Pair<>(root.left, i << 1));
                }
                if (root.right != null) {
                    q.offer(new Pair<>(root.right, i << 1 | 1));
                }
            }
        }
        return ans;
    }
}
```

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
    private int ans = 1;
    private List<Integer> t = new ArrayList<>();

    public int widthOfBinaryTree(TreeNode root) {
        dfs(root, 0, 1);
        return ans;
    }

    private void dfs(TreeNode root, int depth, int i) {
        if (root == null) {
            return;
        }
        if (t.size() == depth) {
            t.add(i);
        } else {
            ans = Math.max(ans, i - t.get(depth) + 1);
        }
        dfs(root.left, depth + 1, i << 1);
        dfs(root.right, depth + 1, i << 1 | 1);
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
    int widthOfBinaryTree(TreeNode* root) {
        queue<pair<TreeNode*, int>> q;
        q.push({root, 1});
        int ans = 0;
        while (!q.empty()) {
            ans = max(ans, q.back().second - q.front().second + 1);
            int i = q.front().second;
            for (int n = q.size(); n; --n) {
                auto p = q.front();
                q.pop();
                root = p.first;
                int j = p.second;
                if (root->left) q.push({root->left, (j << 1) - (i << 1)});
                if (root->right) q.push({root->right, (j << 1 | 1) - (i << 1)});
            }
        }
        return ans;
    }
};
```

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
    vector<int> t;
    int ans = 1;
    using ull = unsigned long long;

    int widthOfBinaryTree(TreeNode* root) {
        dfs(root, 0, 1);
        return ans;
    }

    void dfs(TreeNode* root, int depth, ull i) {
        if (!root) return;
        if (t.size() == depth) {
            t.push_back(i);
        } else {
            ans = max(ans, (int) (i - t[depth] + 1));
        }
        dfs(root->left, depth + 1, i << 1);
        dfs(root->right, depth + 1, i << 1 | 1);
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
func widthOfBinaryTree(root *TreeNode) int {
	q := []pair{{root, 1}}
	ans := 0
	for len(q) > 0 {
		ans = max(ans, q[len(q)-1].i-q[0].i+1)
		for n := len(q); n > 0; n-- {
			p := q[0]
			q = q[1:]
			root = p.node
			if root.Left != nil {
				q = append(q, pair{root.Left, p.i << 1})
			}
			if root.Right != nil {
				q = append(q, pair{root.Right, p.i<<1 | 1})
			}
		}
	}
	return ans
}

type pair struct {
	node *TreeNode
	i    int
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
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func widthOfBinaryTree(root *TreeNode) int {
	ans := 1
	t := []int{}
	var dfs func(root *TreeNode, depth, i int)
	dfs = func(root *TreeNode, depth, i int) {
		if root == nil {
			return
		}
		if len(t) == depth {
			t = append(t, i)
		} else {
			ans = max(ans, i-t[depth]+1)
		}
		dfs(root.Left, depth+1, i<<1)
		dfs(root.Right, depth+1, i<<1|1)
	}
	dfs(root, 0, 1)
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
