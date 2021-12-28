# [508. Most Frequent Subtree Sum](https://leetcode.com/problems/most-frequent-subtree-sum)

[中文文档](/solution/0500-0599/0508.Most%20Frequent%20Subtree%20Sum/README.md)

## Description

<p>

Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.

</p>

<p><b>Examples 1</b><br>

Input:

<pre>

  5

 /  \

2   -3

</pre>

return [2, -3, 4], since all the values happen only once, return all of them in any order.

</p>

<p><b>Examples 2</b><br>

Input:

<pre>

  5

 /  \

2   -5

</pre>

return [2], since 2 happens twice, however -5 only occur once.

</p>

<p><b>Note:</b>

You may assume the sum of values in any subtree is in the range of 32-bit signed integer.

</p>

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
