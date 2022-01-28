# [272. 最接近的二叉搜索树值 II](https://leetcode-cn.com/problems/closest-binary-search-tree-value-ii)

[English Version](/solution/0200-0299/0272.Closest%20Binary%20Search%20Tree%20Value%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个不为空的二叉搜索树和一个目标值 target，请在该二叉搜索树中找到最接近目标值 target 的 k 个值。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>给定的目标值 target 是一个浮点数</li>
	<li>你可以默认 k 值永远是有效的，即 k &le; 总结点数</li>
	<li>题目保证该二叉搜索树中只会存在一种&nbsp;k 个值集合最接近目标值</li>
</ul>

<p><strong>示例：</strong></p>

<pre><strong>输入:</strong> root = [4,2,5,1,3]，目标值 = 3.714286，且 <em>k</em> = 2

    4
   / \
  2   5
 / \
1   3

<strong>输出:</strong> [4,3]</pre>

<p><strong>拓展：</strong><br>
假设该二叉搜索树是平衡的，请问您是否能在小于&nbsp;<em>O</em>(<em>n</em>)（n 为总结点数）的时间复杂度内解决该问题呢？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

中序遍历，当结果元素个数小于 k 时，直接添加。否则，拿第一个元素与当前节点 root 各自与 target 的差值的绝对值进行比较。

-   若当前节点 root 与目标值的差值的绝对值大于等于第一个节点与目标值差值的绝对值，移除第一个元素，然后添加当前节点 root.val。
-   否则，无需再遍历后面的节点。

时间复杂度 O(n)，空间复杂度 O(k)。

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
    def closestKValues(self, root: TreeNode, target: float, k: int) -> List[int]:
        def dfs(root):
            if root is None:
                return
            dfs(root.left)
            if len(q) < k:
                q.append(root.val)
            else:
                if abs(root.val - target) >= abs(q[0] - target):
                    return
                q.popleft()
                q.append(root.val)
            dfs(root.right)

        q = deque()
        dfs(root)
        return list(q)
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

    private List<Integer> ans;
    private double target;
    private int k;

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        ans = new LinkedList<>();
        this.target = target;
        this.k = k;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (ans.size() < k) {
            ans.add(root.val);
        } else {
            if (Math.abs(root.val - target) >= Math.abs(ans.get(0) - target)) {
                return;
            }
            ans.remove(0);
            ans.add(root.val);
        }
        dfs(root.right);
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
    queue<int> q;
    double target;
    int k;

    vector<int> closestKValues(TreeNode* root, double target, int k) {
        this->target = target;
        this->k = k;
        dfs(root);
        vector<int> ans;
        while (!q.empty())
        {
            ans.push_back(q.front());
            q.pop();
        }
        return ans;
    }

    void dfs(TreeNode* root) {
        if (!root) return;
        dfs(root->left);
        if (q.size() < k) q.push(root->val);
        else
        {
            if (abs(root->val - target) >= abs(q.front() - target)) return;
            q.pop();
            q.push(root->val);
        }
        dfs(root->right);
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
func closestKValues(root *TreeNode, target float64, k int) []int {
	var ans []int
	var dfs func(root *TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		dfs(root.Left)
		if len(ans) < k {
			ans = append(ans, root.Val)
		} else {
			if math.Abs(float64(root.Val)-target) >= math.Abs(float64(ans[0])-target) {
				return
			}
			ans = ans[1:]
			ans = append(ans, root.Val)
		}
		dfs(root.Right)
	}
	dfs(root)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
