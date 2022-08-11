# [894. 所有可能的真二叉树](https://leetcode.cn/problems/all-possible-full-binary-trees)

[English Version](/solution/0800-0899/0894.All%20Possible%20Full%20Binary%20Trees/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数 <code>n</code> ，请你找出所有可能含 <code>n</code> 个节点的 <strong>真二叉树</strong> ，并以列表形式返回。答案中每棵树的每个节点都必须符合 <code>Node.val == 0</code> 。</p>

<p>答案的每个元素都是一棵真二叉树的根节点。你可以按 <strong>任意顺序</strong> 返回最终的真二叉树列表<strong>。</strong></p>

<p><strong>真二叉树</strong> 是一类二叉树，树中每个节点恰好有 <code>0</code> 或 <code>2</code> 个子节点。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0894.All%20Possible%20Full%20Binary%20Trees/images/fivetrees.png" style="width: 700px; height: 400px;" />
<pre>
<strong>输入：</strong>n = 7
<strong>输出：</strong>[[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>[[0,0,0]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 20</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

对于 $n=1$，直接返回单个节点的列表。

对于 $n\gt1$，若 $n$ 为奇数，按照左右子树遍历不同的排列组合，得到结果列表；若 $n$ 为偶数，返回空列表。

此过程可以用记忆化搜索，避免重复计算。

时间复杂度 $O(2^n)$，空间复杂度 $O(2^n)$。

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
