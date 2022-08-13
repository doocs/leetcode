# [1305. 两棵二叉搜索树中的所有元素](https://leetcode.cn/problems/all-elements-in-two-binary-search-trees)

[English Version](/solution/1300-1399/1305.All%20Elements%20in%20Two%20Binary%20Search%20Trees/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你&nbsp;<code>root1</code> 和 <code>root2</code>&nbsp;这两棵二叉搜索树。请你返回一个列表，其中包含&nbsp;<strong>两棵树&nbsp;</strong>中的所有整数并按 <strong>升序</strong> 排序。.</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1305.All%20Elements%20in%20Two%20Binary%20Search%20Trees/images/q2-e1.png" /></p>

<pre>
<strong>输入：</strong>root1 = [2,1,4], root2 = [1,0,3]
<strong>输出：</strong>[0,1,1,2,3,4]
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1305.All%20Elements%20in%20Two%20Binary%20Search%20Trees/images/q2-e5-.png" /></p>

<pre>
<strong>输入：</strong>root1 = [1,null,8], root2 = [8,1]
<strong>输出：</strong>[1,1,8,8]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>每棵树的节点数在&nbsp;<code>[0, 5000]</code> 范围内</li>
	<li><code>-10<sup>5</sup>&nbsp;&lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

二叉树中序遍历 + 有序列表归并。

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
        while (i < t1.size() && j < t2.size()) {
            if (t1[i] <= t2[j])
                ans.push_back(t1[i++]);
            else
                ans.push_back(t2[j++]);
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

### **Rust**

```rust
// Definition for a binary tree node.
// #[derive(Debug, PartialEq, Eq)]
// pub struct TreeNode {
//   pub val: i32,
//   pub left: Option<Rc<RefCell<TreeNode>>>,
//   pub right: Option<Rc<RefCell<TreeNode>>>,
// }
//
// impl TreeNode {
//   #[inline]
//   pub fn new(val: i32) -> Self {
//     TreeNode {
//       val,
//       left: None,
//       right: None
//     }
//   }
// }
use std::cell::RefCell;
use std::rc::Rc;
impl Solution {
    pub fn get_all_elements(
        root1: Option<Rc<RefCell<TreeNode>>>,
        root2: Option<Rc<RefCell<TreeNode>>>,
    ) -> Vec<i32> {
        fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, t: &mut Vec<i32>) {
            if let Some(root) = root {
                dfs(&root.borrow().left, t);
                t.push(root.borrow().val);
                dfs(&root.borrow().right, t);
            }
        }

        let mut t1 = Vec::new();
        let mut t2 = Vec::new();
        dfs(&root1, &mut t1);
        dfs(&root2, &mut t2);

        let mut ans = Vec::new();
        let mut i = 0;
        let mut j = 0;
        while i < t1.len() && j < t2.len() {
            if t1[i] < t2[j] {
                ans.push(t1[i]);
                i += 1;
            } else {
                ans.push(t2[j]);
                j += 1;
            }
        }
        while i < t1.len() {
            ans.push(t1[i]);
            i += 1;
        }
        while j < t2.len() {
            ans.push(t2[j]);
            j += 1;
        }
        ans
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

function getAllElements(
    root1: TreeNode | null,
    root2: TreeNode | null,
): number[] {
    const res = [];
    const stacks = [[], []];
    while (
        root1 != null ||
        stacks[0].length !== 0 ||
        root2 != null ||
        stacks[1].length !== 0
    ) {
        if (root1 != null) {
            stacks[0].push(root1);
            root1 = root1.left;
        } else if (root2 != null) {
            stacks[1].push(root2);
            root2 = root2.left;
        } else {
            if (
                (stacks[0][stacks[0].length - 1] ?? { val: Infinity }).val <
                (stacks[1][stacks[1].length - 1] ?? { val: Infinity }).val
            ) {
                const { val, right } = stacks[0].pop();
                res.push(val);
                root1 = right;
            } else {
                const { val, right } = stacks[1].pop();
                res.push(val);
                root2 = right;
            }
        }
    }
    return res;
}
```

### **...**

```

```

<!-- tabs:end -->
