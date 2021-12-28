# [508. 出现次数最多的子树元素和](https://leetcode-cn.com/problems/most-frequent-subtree-sum)

[English Version](/solution/0500-0599/0508.Most%20Frequent%20Subtree%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二叉树的根结点，请你找出出现次数最多的子树元素和。一个结点的「子树元素和」定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。</p>

<p>你需要返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong><br>
输入:</p>

<pre>  5
 /  \
2   -3
</pre>

<p>返回&nbsp;[2, -3, 4]，所有的值均只出现一次，以任意顺序返回所有值。</p>

<p><strong>示例&nbsp;2：</strong><br>
输入：</p>

<pre>  5
 /  \
2   -5
</pre>

<p>返回&nbsp;[2]，只有 2 出现两次，-5 只出现 1 次。</p>

<p>&nbsp;</p>

<p><strong>提示：</strong>&nbsp;假设任意子树元素和均可以用 32 位有符号整数表示。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

后序遍历获取每个子树元素和，同时用哈希表记录每个子树元素和出现的次数，以及最大的次数 mx。最后判断哈希表中出现次数为 mx 的，获取对应的子树元素，组成结果列表 ans。

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
    def findFrequentTreeSum(self, root: TreeNode) -> List[int]:
        def dfs(root):
            if root is None:
                return 0
            left, right = dfs(root.left), dfs(root.right)
            s = root.val + left + right
            counter[s] += 1
            return s

        counter = Counter()
        dfs(root)
        mx = max(counter.values())
        return [k for k, v in counter.items() if v == mx]
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
    private Map<Integer, Integer> counter;
    private int mx;

    public int[] findFrequentTreeSum(TreeNode root) {
        counter = new HashMap<>();
        mx = Integer.MIN_VALUE;
        dfs(root);
        List<Integer> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            if (entry.getValue() == mx) {
                res.add(entry.getKey());
            }
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); ++i) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int s = root.val + dfs(root.left) + dfs(root.right);
        counter.put(s, counter.getOrDefault(s, 0) + 1);
        mx = Math.max(mx, counter.get(s));
        return s;
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
    unordered_map<int, int> counter;
    int mx = 0;

    vector<int> findFrequentTreeSum(TreeNode* root) {
        mx = INT_MIN;
        dfs(root);
        vector<int> ans;
        for (auto& entry : counter)
            if (entry.second == mx)
                ans.push_back(entry.first);
        return ans;
    }

    int dfs(TreeNode* root) {
        if (!root) return 0;
        int s = root->val + dfs(root->left) + dfs(root->right);
        ++counter[s];
        mx = max(mx, counter[s]);
        return s;
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
func findFrequentTreeSum(root *TreeNode) []int {
	counter := make(map[int]int)
	mx := 0
	var dfs func(root *TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		s := root.Val + dfs(root.Left) + dfs(root.Right)
		counter[s]++
		if mx < counter[s] {
			mx = counter[s]
		}
		return s
	}
	dfs(root)
	var ans []int
	for k, v := range counter {
		if v == mx {
			ans = append(ans, k)
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
