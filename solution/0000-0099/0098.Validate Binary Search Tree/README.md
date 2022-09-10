# [98. 验证二叉搜索树](https://leetcode.cn/problems/validate-binary-search-tree)

[English Version](/solution/0000-0099/0098.Validate%20Binary%20Search%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二叉树的根节点 <code>root</code> ，判断其是否是一个有效的二叉搜索树。</p>

<p><strong>有效</strong> 二叉搜索树定义如下：</p>

<ul>
	<li>节点的左子树只包含<strong> 小于 </strong>当前节点的数。</li>
	<li>节点的右子树只包含 <strong>大于</strong> 当前节点的数。</li>
	<li>所有左子树和右子树自身必须也是二叉搜索树。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0098.Validate%20Binary%20Search%20Tree/images/tree1.jpg" style="width: 302px; height: 182px;" />
<pre>
<strong>输入：</strong>root = [2,1,3]
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0098.Validate%20Binary%20Search%20Tree/images/tree2.jpg" style="width: 422px; height: 292px;" />
<pre>
<strong>输入：</strong>root = [5,1,4,null,null,3,6]
<strong>输出：</strong>false
<strong>解释：</strong>根节点的值是 5 ，但是右子节点的值是 4 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数目范围在<code>[1, 10<sup>4</sup>]</code> 内</li>
	<li><code>-2<sup>31</sup> &lt;= Node.val &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：递归**

中序遍历，若是一个有效的二叉搜索树，那么遍历到的序列应该是单调递增的。所以只要比较判断遍历到的当前数是否大于上一个数即可。

或者考虑以 `root` 为根的子树，所有节点的值是否都在合法范围内，递归判断即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是树中节点的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isValidBST(self, root: Optional[TreeNode]) -> bool:
        def dfs(root):
            nonlocal prev
            if root is None:
                return True
            if not dfs(root.left):
                return False
            if prev >= root.val:
                return False
            prev = root.val
            if not dfs(root.right):
                return False
            return True

        prev = -inf
        return dfs(root)
```

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isValidBST(self, root: Optional[TreeNode]) -> bool:
        def dfs(root, l, r):
            if root is None:
                return True
            if root.val <= l or root.val >= r:
                return False
            return dfs(root.left, l, root.val) and dfs(root.right, root.val, r)

        return dfs(root, -inf, inf)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
    private Integer prev;

    public boolean isValidBST(TreeNode root) {
        prev = null;
        return dfs(root);
    }

    private boolean dfs(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!dfs(root.left)) {
            return false;
        }
        if (prev != null && prev >= root.val) {
            return false;
        }
        prev = root.val;
        if (!dfs(root.right)) {
            return false;
        }
        return true;
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
    public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean dfs(TreeNode root, long l, long r) {
        if (root == null) {
            return true;
        }
        if (root.val <= l || root.val >= r) {
            return false;
        }
        return dfs(root.left, l, root.val) && dfs(root.right, root.val, r);
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
    TreeNode* prev;

    bool isValidBST(TreeNode* root) {
        prev = nullptr;
        return dfs(root);
    }

    bool dfs(TreeNode* root) {
        if (!root) return true;
        if (!dfs(root->left)) return false;
        if (prev && prev->val >= root->val) return false;
        prev = root;
        if (!dfs(root->right)) return false;
        return true;
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
    bool isValidBST(TreeNode* root) {
        return dfs(root, LONG_MIN, LONG_MAX);
    }

    bool dfs(TreeNode* root, long long l, long long r) {
        if (!root) return true;
        if (root->val <= l || root->val >= r) return false;
        return dfs(root->left, l, root->val) && dfs(root->right, root->val, r);
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
func isValidBST(root *TreeNode) bool {
	var prev *TreeNode

	var dfs func(root *TreeNode) bool
	dfs = func(root *TreeNode) bool {
		if root == nil {
			return true
		}
		if !dfs(root.Left) {
			return false
		}
		if prev != nil && prev.Val >= root.Val {
			return false
		}
		prev = root
		if !dfs(root.Right) {
			return false
		}
		return true
	}

	return dfs(root)
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
func isValidBST(root *TreeNode) bool {
	return dfs(root, math.MinInt64, math.MaxInt64)
}

func dfs(root *TreeNode, l, r int64) bool {
	if root == nil {
		return true
	}
	v := int64(root.Val)
	if v <= l || v >= r {
		return false
	}
	return dfs(root.Left, l, v) && dfs(root.Right, v, r)
}
```

### **JavaScript**

```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @return {boolean}
 */
var isValidBST = function (root) {
    let prev = null;

    let dfs = function (root) {
        if (!root) {
            return true;
        }
        if (!dfs(root.left)) {
            return false;
        }
        if (prev && prev.val >= root.val) {
            return false;
        }
        prev = root;
        if (!dfs(root.right)) {
            return false;
        }
        return true;
    };

    return dfs(root);
};
```

```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @return {boolean}
 */
var isValidBST = function (root) {
    function dfs(root, l, r) {
        if (!root) {
            return true;
        }
        if (root.val <= l || root.val >= r) {
            return false;
        }
        return dfs(root.left, l, root.val) && dfs(root.right, root.val, r);
    }
    return dfs(root, -Infinity, Infinity);
};
```

### **C#**

```cs
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int val=0, TreeNode left=null, TreeNode right=null) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class Solution {
    private TreeNode prev;

    public bool IsValidBST(TreeNode root) {
        prev = null;
        return dfs(root);
    }

    private bool dfs(TreeNode root) {
        if (root == null)
        {
            return true;
        }
        if (!dfs(root.left))
        {
            return false;
        }
        if (prev != null && prev.val >= root.val)
        {
            return false;
        }
        prev = root;
        if (!dfs(root.right))
        {
            return false;
        }
        return true;
    }
}
```

```cs
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int val=0, TreeNode left=null, TreeNode right=null) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class Solution {
    public bool IsValidBST(TreeNode root) {
        return dfs(root, long.MinValue, long.MaxValue);
    }

    public bool dfs(TreeNode root, long l, long r) {
        if (root == null) {
            return true;
        }
        if (root.val <= l || root.val >= r) {
            return false;
        }
        return dfs(root.left, l, root.val) && dfs(root.right, root.val, r);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
