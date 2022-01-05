# [1305. All Elements in Two Binary Search Trees](https://leetcode.com/problems/all-elements-in-two-binary-search-trees)

[中文文档](/solution/1300-1399/1305.All%20Elements%20in%20Two%20Binary%20Search%20Trees/README.md)

## Description

<p>Given two binary search trees <code>root1</code> and <code>root2</code>.</p>

<p>Return a list containing <em>all the integers</em> from <em>both trees</em> sorted in <strong>ascending</strong> order.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1305.All%20Elements%20in%20Two%20Binary%20Search%20Trees/images/q2-e1.png" style="width: 457px; height: 207px;" />
<pre>
<strong>Input:</strong> root1 = [2,1,4], root2 = [1,0,3]
<strong>Output:</strong> [0,1,1,2,3,4]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> root1 = [0,-10,10], root2 = [5,1,7,0,2]
<strong>Output:</strong> [-10,0,0,1,2,5,7,10]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> root1 = [], root2 = [5,1,7,0,2]
<strong>Output:</strong> [0,1,2,5,7]
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> root1 = [0,-10,10], root2 = []
<strong>Output:</strong> [-10,0,10]
</pre>

<p><strong>Example 5:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1305.All%20Elements%20in%20Two%20Binary%20Search%20Trees/images/q2-e5-.png" style="width: 352px; height: 197px;" />
<pre>
<strong>Input:</strong> root1 = [1,null,8], root2 = [8,1]
<strong>Output:</strong> [1,1,8,8]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>Each tree has at most <code>5000</code> nodes.</li>
	<li>Each node&#39;s value is between <code>[-10^5, 10^5]</code>.</li>
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
    def getAllElements(self, root1: TreeNode, root2: TreeNode) -> List[int]:
        def dfs(root, t):
            if root is None:
                return
            dfs(root.left, t)
            t.append(root.val)
            dfs(root.right, t)

        def merge(t1, t2):
            ans = []
            i = j = 0
            while i < len(t1) and j < len(t2):
                if t1[i] <= t2[j]:
                    ans.append(t1[i])
                    i += 1
                else:
                    ans.append(t2[j])
                    j += 1
            while i < len(t1):
                ans.append(t1[i])
                i += 1
            while j < len(t2):
                ans.append(t2[j])
                j += 1
            return ans

        t1, t2 = [], []
        dfs(root1, t1)
        dfs(root2, t2)
        return merge(t1, t2)
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
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> t1 = new ArrayList<>();
        List<Integer> t2 = new ArrayList<>();
        dfs(root1, t1);
        dfs(root2, t2);
        return merge(t1, t2);
    }

    private void dfs(TreeNode root, List<Integer> t) {
        if (root == null) {
            return;
        }
        dfs(root.left, t);
        t.add(root.val);
        dfs(root.right, t);
    }

    private List<Integer> merge(List<Integer> t1, List<Integer> t2) {
        List<Integer> ans = new ArrayList<>();
        int i = 0, j = 0;
        while (i < t1.size() && j < t2.size()) {
            if (t1.get(i) <= t2.get(j)) {
                ans.add(t1.get(i++));
            } else {
                ans.add(t2.get(j++));
            }
        }
        while (i < t1.size()) {
            ans.add(t1.get(i++));
        }
        while (j < t2.size()) {
            ans.add(t2.get(j++));
        }
        return ans;
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
    vector<int> getAllElements(TreeNode* root1, TreeNode* root2) {
        vector<int> t1;
        vector<int> t2;
        dfs(root1, t1);
        dfs(root2, t2);
        return merge(t1, t2);
    }

    void dfs(TreeNode* root, vector<int>& t) {
        if (!root) return;
        dfs(root->left, t);
        t.push_back(root->val);
        dfs(root->right, t);
    }

    vector<int> merge(vector<int>& t1, vector<int>& t2) {
        vector<int> ans;
        int i = 0, j = 0;
        while (i < t1.size() && j < t2.size())
        {
            if (t1[i] <= t2[j]) ans.push_back(t1[i++]);
            else ans.push_back(t2[j++]);
        }
        while (i < t1.size()) ans.push_back(t1[i++]);
        while (j < t2.size()) ans.push_back(t2[j++]);
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
func getAllElements(root1 *TreeNode, root2 *TreeNode) []int {
	var dfs func(root *TreeNode) []int
	dfs = func(root *TreeNode) []int {
		if root == nil {
			return []int{}
		}
		left := dfs(root.Left)
		right := dfs(root.Right)
		left = append(left, root.Val)
		left = append(left, right...)
		return left
	}
	merge := func(t1, t2 []int) []int {
		var ans []int
		i, j := 0, 0
		for i < len(t1) && j < len(t2) {
			if t1[i] <= t2[j] {
				ans = append(ans, t1[i])
				i++
			} else {
				ans = append(ans, t2[j])
				j++
			}
		}
		for i < len(t1) {
			ans = append(ans, t1[i])
			i++
		}
		for j < len(t2) {
			ans = append(ans, t2[j])
			j++
		}
		return ans
	}
	t1, t2 := dfs(root1), dfs(root2)
	return merge(t1, t2)
}
```

### **...**

```

```

<!-- tabs:end -->
