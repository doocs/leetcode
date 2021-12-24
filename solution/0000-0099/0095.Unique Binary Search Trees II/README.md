# [95. 不同的二叉搜索树 II](https://leetcode-cn.com/problems/unique-binary-search-trees-ii)

[English Version](/solution/0000-0099/0095.Unique%20Binary%20Search%20Trees%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数 <em>n</em>，生成所有由 1 ...&nbsp;<em>n</em> 为节点所组成的<strong> 二叉搜索树 </strong>。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>3
<strong>输出：</strong>
[
&nbsp; [1,null,3,2],
&nbsp; [3,2,null,1],
&nbsp; [3,1,null,null,2],
&nbsp; [2,1,3],
&nbsp; [1,null,2,null,3]
]
<strong>解释：</strong>
以上的输出对应以下 5 种不同结构的二叉搜索树：

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 8</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

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
    def generateTrees(self, n: int) -> List[TreeNode]:
        def gen(left, right):
            ans = []
            if left > right:
                ans.append(None)
            else:
                for i in range(left, right + 1):
                    left_trees = gen(left, i - 1)
                    right_trees = gen(i + 1, right)
                    for l in left_trees:
                        for r in right_trees:
                            node = TreeNode(i, l, r)
                            ans.append(node)
            return ans

        return gen(1, n)
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
    public List<TreeNode> generateTrees(int n) {
        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int left, int right) {
        List<TreeNode> ans = new ArrayList<>();
        if (left > right) {
            ans.add(null);
        } else {
            for (int i = left; i <= right; ++i) {
                List<TreeNode> leftTrees = generateTrees(left, i - 1);
                List<TreeNode> rightTrees = generateTrees(i + 1, right);
                for (TreeNode l : leftTrees) {
                    for (TreeNode r : rightTrees) {
                        TreeNode node = new TreeNode(i, l, r);
                        ans.add(node);
                    }
                }
            }
        }
        return ans;
    }
}
```

### **TypeScript**

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

function generateTrees(n: number): Array<TreeNode | null> {
    if (n == 0) return [];
    return helper(1, n);
}

function helper(start: number, end: number): Array<TreeNode | null> {
    let ans = [];
    if (start > end) {
        ans.push(null);
        return ans;
    }
    for (let i = start; i <= end; i++) {
        let lefts = helper(start, i - 1);
        let rights = helper(i + 1, end);
        for (let left of lefts) {
            for (let right of rights) {
                let root = new TreeNode(i, left, right);
                ans.push(root);
            }
        }
    }
    return ans;
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
    vector<TreeNode*> generateTrees(int n) {
        return gen(1, n);
    }

    vector<TreeNode*> gen(int left, int right) {
        vector<TreeNode*> ans;
        if (left > right)
        {
            ans.push_back(nullptr);
        }
        else
        {
            for (int i = left; i <= right; ++i)
            {
                auto leftTrees = gen(left, i - 1);
                auto rightTrees = gen(i + 1, right);
                for (auto& l : leftTrees)
                {
                    for (auto& r : rightTrees)
                    {
                        TreeNode* node = new TreeNode(i, l, r);
                        ans.push_back(node);
                    }
                }
            }
        }
        return ans;
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
func generateTrees(n int) []*TreeNode {
	var gen func(left, right int) []*TreeNode
	gen = func(left, right int) []*TreeNode {
		var ans []*TreeNode
		if left > right {
			ans = append(ans, nil)
		} else {
			for i := left; i <= right; i++ {
				leftTrees := gen(left, i-1)
				rightTrees := gen(i+1, right)
				for _, l := range leftTrees {
					for _, r := range rightTrees {
						node := &TreeNode{i, l, r}
						ans = append(ans, node)
					}
				}
			}
		}
		return ans
	}

	return gen(1, n)
}
```

### **...**

```

```

<!-- tabs:end -->
