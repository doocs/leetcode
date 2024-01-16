# [663. 均匀树划分](https://leetcode.cn/problems/equal-tree-partition)

[English Version](/solution/0600-0699/0663.Equal%20Tree%20Partition/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵二叉树的根节点 <code>root</code>，如果你可以通过去掉原始树上的一条边将树分成两棵节点值之和相等的子树，则返回 <code>true</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0663.Equal%20Tree%20Partition/images/split1-tree.jpg" style="width: 500px; height: 204px;" />
<pre>
<strong>输入：</strong>root = [5,10,10,null,null,2,3]
<strong>输出：</strong>true
</pre>

<p><strong class="example">示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0663.Equal%20Tree%20Partition/images/split2-tree.jpg" style="width: 277px; height: 302px;" />
<pre>
<strong>输入：</strong>root = [1,2,10,null,null,2,20]
<strong>输出：</strong>false
<strong>解释：</strong>在树上移除一条边无法将树分成两棵节点值之和相等的子树。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数目在 <code>[1, 10<sup>4</sup>]</code> 范围内。</li>
	<li><code>-10<sup>5</sup> &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def checkEqualTree(self, root: TreeNode) -> bool:
        def sum(root):
            if root is None:
                return 0
            l, r = sum(root.left), sum(root.right)
            seen.append(l + r + root.val)
            return seen[-1]

        seen = []
        s = sum(root)
        if s % 2 == 1:
            return False
        seen.pop()
        return s // 2 in seen
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
    private List<Integer> seen;

    public boolean checkEqualTree(TreeNode root) {
        seen = new ArrayList<>();
        int s = sum(root);
        if (s % 2 != 0) {
            return false;
        }
        seen.remove(seen.size() - 1);
        return seen.contains(s / 2);
    }

    private int sum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = sum(root.left);
        int r = sum(root.right);
        int s = l + r + root.val;
        seen.add(s);
        return s;
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
    vector<int> seen;

    bool checkEqualTree(TreeNode* root) {
        int s = sum(root);
        if (s % 2 != 0) return false;
        seen.pop_back();
        return count(seen.begin(), seen.end(), s / 2);
    }

    int sum(TreeNode* root) {
        if (!root) return 0;
        int l = sum(root->left), r = sum(root->right);
        int s = l + r + root->val;
        seen.push_back(s);
        return s;
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
func checkEqualTree(root *TreeNode) bool {
	var seen []int
	var sum func(root *TreeNode) int
	sum = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		l, r := sum(root.Left), sum(root.Right)
		s := l + r + root.Val
		seen = append(seen, s)
		return s
	}

	s := sum(root)
	if s%2 != 0 {
		return false
	}
	seen = seen[:len(seen)-1]
	for _, v := range seen {
		if v == s/2 {
			return true
		}
	}
	return false
}
```

<!-- tabs:end -->

<!-- end -->
