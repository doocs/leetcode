# [面试题 04.02. 最小高度树](https://leetcode.cn/problems/minimum-height-tree-lcci)

[English Version](/lcci/04.02.Minimum%20Height%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。</p><strong>示例:</strong><pre>给定有序数组: [-10,-3,0,5,9],<br><br>一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：<br><br>          0 <br>         / &#92 <br>       -3   9 <br>       /   / <br>     -10  5 <br></pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先找到数组的中间点，作为二叉搜索树的根节点，然后递归左右子树即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def sortedArrayToBST(self, nums: List[int]) -> TreeNode:
        def dfs(i, j):
            if i > j:
                return None
            if i == j:
                return TreeNode(nums[i])
            mid = (i + j) >> 1
            node = TreeNode(nums[mid])
            node.left = dfs(i, mid - 1)
            node.right = dfs(mid + 1, j)
            return node

        return dfs(0, len(nums) - 1)
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
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private int[] nums;

    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;
        return dfs(0, nums.length - 1);
    }

    private TreeNode dfs(int i, int j) {
        if (i > j) {
            return null;
        }
        if (i == j) {
            return new TreeNode(nums[i]);
        }
        int mid = (i + j) >>> 1;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = dfs(i, mid - 1);
        node.right = dfs(mid + 1, j);
        return node;
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
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    vector<int> nums;

    TreeNode* sortedArrayToBST(vector<int>& nums) {
        this->nums = nums;
        return dfs(0, nums.size() - 1);
    }

    TreeNode* dfs(int i, int j) {
        if (i > j) return nullptr;
        if (i == j) return new TreeNode(nums[i]);
        int mid = i + j >> 1;
        TreeNode* node = new TreeNode(nums[mid]);
        node->left = dfs(i, mid - 1);
        node->right = dfs(mid + 1, j);
        return node;
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
func sortedArrayToBST(nums []int) *TreeNode {
	var dfs func(i, j int) *TreeNode
	dfs = func(i, j int) *TreeNode {
		if i > j {
			return nil
		}
		if i == j {
			return &TreeNode{Val: nums[i]}
		}
		mid := (i + j) >> 1
		return &TreeNode{Val: nums[mid], Left: dfs(i, mid-1), Right: dfs(mid+1, j)}
	}

	return dfs(0, len(nums)-1)
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

function sortedArrayToBST(nums: number[]): TreeNode | null {
    const dfs = (start: number, end: number): TreeNode | null => {
        if (start >= end) {
            return null;
        }
        const mid = Math.floor(start + (end - start) / 2);
        return new TreeNode(nums[mid], dfs(start, mid), dfs(mid + 1, end));
    };
    return dfs(0, nums.length);
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
use std::rc::Rc;
use std::cell::RefCell;
impl Solution {
    fn dfs(nums: &Vec<i32>, start: usize, end: usize) -> Option<Rc<RefCell<TreeNode>>> {
        if start >= end {
            return None;
        }
        let mid = start + (end - start) / 2;
        Some(Rc::new(RefCell::new(TreeNode {
            val: nums[mid],
            left: Self::dfs(nums, start, mid),
            right: Self::dfs(nums, mid + 1, end),
        })))
    }
    pub fn sorted_array_to_bst(nums: Vec<i32>) -> Option<Rc<RefCell<TreeNode>>> {
        let end = nums.len();
        Self::dfs(&nums, 0, end)
    }
}
```

### **...**

```

```

<!-- tabs:end -->
