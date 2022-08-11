# [894. All Possible Full Binary Trees](https://leetcode.com/problems/all-possible-full-binary-trees)

[中文文档](/solution/0800-0899/0894.All%20Possible%20Full%20Binary%20Trees/README.md)

## Description

<p>Given an integer <code>n</code>, return <em>a list of all possible <strong>full binary trees</strong> with</em> <code>n</code> <em>nodes</em>. Each node of each tree in the answer must have <code>Node.val == 0</code>.</p>

<p>Each element of the answer is the root node of one possible tree. You may return the final list of trees in <strong>any order</strong>.</p>

<p>A <strong>full binary tree</strong> is a binary tree where each node has exactly <code>0</code> or <code>2</code> children.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0894.All%20Possible%20Full%20Binary%20Trees/images/fivetrees.png" style="width: 700px; height: 400px;" />
<pre>
<strong>Input:</strong> n = 7
<strong>Output:</strong> [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> [[0,0,0]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 20</code></li>
</ul>

## Solutions

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
    def allPossibleFBT(self, n: int) -> List[Optional[TreeNode]]:
        @cache
        def dfs(n):
            if n == 1:
                return [TreeNode()]
            res = []
            if n % 2:
                for i in range(n - 1):
                    j = n - i - 1
                    for left in dfs(i):
                        for right in dfs(j):
                            res.append(TreeNode(0, left, right))
            return res

        return dfs(n)
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
    private List<TreeNode>[] f = new List[21];

    public List<TreeNode> allPossibleFBT(int n) {
        return dfs(n);
    }

    private List<TreeNode> dfs(int n) {
        if (f[n] != null) {
            return f[n];
        }
        if (n == 1) {
            return Collections.singletonList(new TreeNode());
        }
        List<TreeNode> res = new ArrayList<>();
        for (int i = 0; i < n - 1; ++i) {
            int j = n - i - 1;
            for (TreeNode left : dfs(i)) {
                for (TreeNode right : dfs(j)) {
                    res.add(new TreeNode(0, left, right));
                }
            }
        }
        f[n] = res;
        return res;
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
    unordered_map<int, vector<TreeNode*>> f;

    vector<TreeNode*> allPossibleFBT(int n) {
        return dfs(n);
    }

    vector<TreeNode*> dfs(int n) {
        if (f.count(n)) return f[n];
        if (n == 1) return {new TreeNode()};
        vector<TreeNode*> res;
        for (int i = 0; i < n - 1; ++i) {
            int j = n - i - 1;
            for (auto left : dfs(i)) {
                for (auto right : dfs(j)) {
                    res.push_back(new TreeNode(0, left, right));
                }
            }
        }
        f[n] = res;
        return res;
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
func allPossibleFBT(n int) []*TreeNode {
	f := map[int][]*TreeNode{}
	var dfs func(n int) []*TreeNode
	dfs = func(n int) []*TreeNode {
		if v, ok := f[n]; ok {
			return v
		}
		if n == 1 {
			return []*TreeNode{&TreeNode{Val: 0}}
		}
		res := []*TreeNode{}
		for i := 0; i < n-1; i++ {
			j := n - i - 1
			for _, left := range dfs(i) {
				for _, right := range dfs(j) {
					res = append(res, &TreeNode{0, left, right})
				}
			}
		}
		f[n] = res
		return res
	}
	return dfs(n)
}
```

### **...**

```

```

<!-- tabs:end -->
