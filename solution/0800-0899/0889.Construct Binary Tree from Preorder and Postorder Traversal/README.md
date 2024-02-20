# [889. 根据前序和后序遍历构造二叉树](https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-postorder-traversal)

[English Version](/solution/0800-0899/0889.Construct%20Binary%20Tree%20from%20Preorder%20and%20Postorder%20Traversal/README_EN.md)

<!-- tags:树,数组,哈希表,分治,二叉树 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个整数数组，<code>preorder</code>&nbsp;和 <code>postorder</code> ，其中 <code>preorder</code> 是一个具有 <strong>无重复</strong> 值的二叉树的前序遍历，<code>postorder</code> 是同一棵树的后序遍历，重构并返回二叉树。</p>

<p>如果存在多个答案，您可以返回其中 <strong>任何</strong> 一个。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0889.Construct%20Binary%20Tree%20from%20Preorder%20and%20Postorder%20Traversal/images/lc-prepost.jpg" style="height: 265px; width: 304px;" /></p>

<pre>
<strong>输入：</strong>preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
<strong>输出：</strong>[1,2,3,4,5,6,7]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> preorder = [1], postorder = [1]
<strong>输出:</strong> [1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= preorder.length &lt;= 30</code></li>
	<li><code>1 &lt;= preorder[i] &lt;= preorder.length</code></li>
	<li><code>preorder</code>&nbsp;中所有值都 <strong>不同</strong></li>
	<li><code>postorder.length == preorder.length</code></li>
	<li><code>1 &lt;= postorder[i] &lt;= postorder.length</code></li>
	<li><code>postorder</code>&nbsp;中所有值都 <strong>不同</strong></li>
	<li>保证 <code>preorder</code>&nbsp;和 <code>postorder</code>&nbsp;是同一棵二叉树的前序遍历和后序遍历</li>
</ul>

## 解法

### 方法一：递归

前序遍历的顺序是：根节点 -> 左子树 -> 右子树，后序遍历的顺序是：左子树 -> 右子树 -> 根节点。

因此，二叉树的根节点一定是前序遍历的第一个节点，也是后序遍历的最后一个节点。

接下来，我们需要确定二叉树的左子树范围和右子树范围。

如果二叉树有左子树，那么左子树的根节点一定是前序遍历的第二个节点；如果二叉树没有左子树，那么前序遍历的第二个节点一定是右子树的根节点。由于这两种情况下，后序遍历的结果是一样的，因此，我们可以把前序遍历的第二个节点作为左子树的根节点，然后找到它在后序遍历中的位置，这样就确定了左子树的范围。

具体地，我们设计一个递归函数 $dfs(a, b, c, d)$，其中 $[a, b]$ 表示前序遍历的范围，而 $[c, d]$ 表示后序遍历的范围。这个函数的功能是根据前序遍历 $[a, b]$ 和后序遍历 $[c, d]$ 构造出二叉树的根节点。那么答案就是 $dfs(0, n - 1, 0, n - 1)$，其中 $n$ 是前序遍历的长度。

函数 $dfs(a, b, c, d)$ 的执行步骤如下：

1. 如果 $a > b$，说明范围为空，直接返回空节点。
1. 否则，我们构造一个新的节点 $root$，它的值为前序遍历中的第一个节点的值，也就是 $preorder[a]$。
1. 如果 $a$ 等于 $b$，说明 $root$ 没有左子树也没有右子树，直接返回 $root$。
1. 否则，左子树的根节点的值为 $preorder[a + 1]$，我们在后序遍历中找到 $preorder[a + 1]$ 的位置，记为 $i$。那么左子树的节点个数 $m = i - c + 1$，由此可知左子树在前序遍历中的范围是 $[a + 1, a + m]$，后序遍历中的范围是 $[c, i]$，右子树在前序遍历中的范围是 $[a + m + 1, b]$，后序遍历中的范围是 $[i + 1, d - 1]$。
1. 知道了左右子树的范围，我们就可以递归地重建左右子树，然后将左右子树的根节点分别作为 $root$ 的左右子节点。最后返回 $root$。

在函数 $dfs(a, b, c, d)$ 中，我们需要用到一个哈希表 $pos$，它存储了后序遍历中每个节点的位置。在函数的开头，我们可以先计算出这个哈希表，这样就可以在 $O(1)$ 的时间内找到任意节点在后序遍历中的位置。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是节点数。

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def constructFromPrePost(
        self, preorder: List[int], postorder: List[int]
    ) -> Optional[TreeNode]:
        def dfs(a: int, b: int, c: int, d: int) -> Optional[TreeNode]:
            if a > b:
                return None
            root = TreeNode(preorder[a])
            if a == b:
                return root
            i = pos[preorder[a + 1]]
            m = i - c + 1
            root.left = dfs(a + 1, a + m, c, i)
            root.right = dfs(a + m + 1, b, i + 1, d - 1)
            return root

        pos = {x: i for i, x in enumerate(postorder)}
        return dfs(0, len(preorder) - 1, 0, len(postorder) - 1)
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
    private Map<Integer, Integer> pos = new HashMap<>();
    private int[] preorder;
    private int[] postorder;

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        this.preorder = preorder;
        this.postorder = postorder;
        for (int i = 0; i < postorder.length; ++i) {
            pos.put(postorder[i], i);
        }
        return dfs(0, preorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode dfs(int a, int b, int c, int d) {
        if (a > b) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[a]);
        if (a == b) {
            return root;
        }
        int i = pos.get(preorder[a + 1]);
        int m = i - c + 1;
        root.left = dfs(a + 1, a + m, c, i);
        root.right = dfs(a + m + 1, b, i + 1, d - 1);
        return root;
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
    TreeNode* constructFromPrePost(vector<int>& preorder, vector<int>& postorder) {
        unordered_map<int, int> pos;
        int n = postorder.size();
        for (int i = 0; i < n; ++i) {
            pos[postorder[i]] = i;
        }
        function<TreeNode*(int, int, int, int)> dfs = [&](int a, int b, int c, int d) -> TreeNode* {
            if (a > b) {
                return nullptr;
            }
            TreeNode* root = new TreeNode(preorder[a]);
            if (a == b) {
                return root;
            }
            int i = pos[preorder[a + 1]];
            int m = i - c + 1;
            root->left = dfs(a + 1, a + m, c, i);
            root->right = dfs(a + m + 1, b, i + 1, d - 1);
            return root;
        };
        return dfs(0, n - 1, 0, n - 1);
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
func constructFromPrePost(preorder []int, postorder []int) *TreeNode {
	pos := map[int]int{}
	for i, x := range postorder {
		pos[x] = i
	}
	var dfs func(int, int, int, int) *TreeNode
	dfs = func(a, b, c, d int) *TreeNode {
		if a > b {
			return nil
		}
		root := &TreeNode{Val: preorder[a]}
		if a == b {
			return root
		}
		i := pos[preorder[a+1]]
		m := i - c + 1
		root.Left = dfs(a+1, a+m, c, i)
		root.Right = dfs(a+m+1, b, i+1, d-1)
		return root
	}
	return dfs(0, len(preorder)-1, 0, len(postorder)-1)
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

function constructFromPrePost(preorder: number[], postorder: number[]): TreeNode | null {
    const pos: Map<number, number> = new Map();
    const n = postorder.length;
    for (let i = 0; i < n; ++i) {
        pos.set(postorder[i], i);
    }
    const dfs = (a: number, b: number, c: number, d: number): TreeNode | null => {
        if (a > b) {
            return null;
        }
        const root = new TreeNode(preorder[a]);
        if (a === b) {
            return root;
        }
        const i = pos.get(preorder[a + 1])!;
        const m = i - c + 1;
        root.left = dfs(a + 1, a + m, c, i);
        root.right = dfs(a + m + 1, b, i + 1, d - 1);
        return root;
    };
    return dfs(0, n - 1, 0, n - 1);
}
```

<!-- tabs:end -->

<!-- end -->
