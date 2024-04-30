# [257. 二叉树的所有路径](https://leetcode.cn/problems/binary-tree-paths)

[English Version](/solution/0200-0299/0257.Binary%20Tree%20Paths/README_EN.md)

<!-- tags:树,深度优先搜索,字符串,回溯,二叉树 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二叉树的根节点 <code>root</code> ，按 <strong>任意顺序</strong> ，返回所有从根节点到叶子节点的路径。</p>

<p><strong>叶子节点</strong> 是指没有子节点的节点。</p>
&nbsp;

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0257.Binary%20Tree%20Paths/images/paths-tree.jpg" style="width: 207px; height: 293px;" />
<pre>
<strong>输入：</strong>root = [1,2,3,null,5]
<strong>输出：</strong>["1-&gt;2-&gt;5","1-&gt;3"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = [1]
<strong>输出：</strong>["1"]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数目在范围 <code>[1, 100]</code> 内</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
</ul>

## 解法

### 方法一：DFS

我们可以使用深度优先搜索的方法遍历整棵二叉树，每一次我们将当前的节点添加到路径中。如果当前的节点是叶子节点，则我们将整个路径加入到答案中。否则我们继续递归遍历节点的孩子节点。最后当我们递归结束返回到当前节点时，我们需要将当前节点从路径中删除。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点数。

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def binaryTreePaths(self, root: Optional[TreeNode]) -> List[str]:
        def dfs(root: Optional[TreeNode]):
            if root is None:
                return
            t.append(str(root.val))
            if root.left is None and root.right is None:
                ans.append("->".join(t))
            else:
                dfs(root.left)
                dfs(root.right)
            t.pop()

        ans = []
        t = []
        dfs(root)
        return ans
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
    private List<String> ans = new ArrayList<>();
    private List<String> t = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        t.add(root.val + "");
        if (root.left == null && root.right == null) {
            ans.add(String.join("->", t));
        } else {
            dfs(root.left);
            dfs(root.right);
        }
        t.remove(t.size() - 1);
    }
}
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
    vector<string> binaryTreePaths(TreeNode* root) {
        vector<string> ans;
        vector<string> t;
        function<void(TreeNode*)> dfs = [&](TreeNode* root) {
            if (!root) {
                return;
            }
            t.push_back(to_string(root->val));
            if (!root->left && !root->right) {
                ans.push_back(join(t));
            } else {
                dfs(root->left);
                dfs(root->right);
            }
            t.pop_back();
        };
        dfs(root);
        return ans;
    }

    string join(vector<string>& t, string sep = "->") {
        string ans;
        for (int i = 0; i < t.size(); ++i) {
            if (i > 0) {
                ans += sep;
            }
            ans += t[i];
        }
        return ans;
    }
};
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
func binaryTreePaths(root *TreeNode) (ans []string) {
	t := []string{}
	var dfs func(*TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		t = append(t, strconv.Itoa(root.Val))
		if root.Left == nil && root.Right == nil {
			ans = append(ans, strings.Join(t, "->"))
		} else {
			dfs(root.Left)
			dfs(root.Right)
		}
		t = t[:len(t)-1]
	}
	dfs(root)
	return
}
```

```ts
/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */

function binaryTreePaths(root: TreeNode | null): string[] {
    const ans: string[] = [];
    const t: number[] = [];
    const dfs = (root: TreeNode | null) => {
        if (!root) {
            return;
        }
        t.push(root.val);
        if (!root.left && !root.right) {
            ans.push(t.join('->'));
        } else {
            dfs(root.left);
            dfs(root.right);
        }
        t.pop();
    };
    dfs(root);
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
