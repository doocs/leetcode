---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/04.02.Minimum%20Height%20Tree/README_EN.md
---

<!-- problem:start -->

# [04.02. Minimum Height Tree](https://leetcode.cn/problems/minimum-height-tree-lcci)

[中文文档](/lcci/04.02.Minimum%20Height%20Tree/README.md)

## Description

<!-- description:start -->

<p>Given a sorted (increasing order) array with unique integer elements, write an algo&shy;rithm to create a binary search tree with minimal height.</p>

<p><strong>Example:</strong></p>

<pre>

Given sorted array: [-10,-3,0,5,9],



One possible answer is: [0,-3,9,-10,null,5]，which represents the following tree: 



          0 

         / \ 

       -3   9 

       /   / 

     -10  5 

</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Recursion

We design a function `dfs(l, r)`, which constructs a subtree from `l` to `r`. Therefore, the answer is `dfs(0, len(nums) - 1)`.

The execution process of the function `dfs(l, r)` is as follows:

1. If `l > r`, return `None`.
2. Otherwise, we calculate the middle position `mid = (l + r) / 2`, then construct the root node, the left subtree is `dfs(l, mid - 1)`, and the right subtree is `dfs(mid + 1, r)`.
3. Finally, return the root node.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def sortedArrayToBST(self, nums: List[int]) -> TreeNode:
        def dfs(l: int, r: int) -> TreeNode:
            if l > r:
                return None
            mid = (l + r) >> 1
            return TreeNode(nums[mid], dfs(l, mid - 1), dfs(mid + 1, r))

        return dfs(0, len(nums) - 1)
```

#### Java

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

    private TreeNode dfs(int l, int r) {
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return new TreeNode(nums[mid], dfs(l, mid - 1), dfs(mid + 1, r));
    }
}
```

#### C++

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
    TreeNode* sortedArrayToBST(vector<int>& nums) {
        function<TreeNode*(int, int)> dfs = [&](int l, int r) -> TreeNode* {
            if (l > r) {
                return nullptr;
            }
            int mid = l + r >> 1;
            return new TreeNode(nums[mid], dfs(l, mid - 1), dfs(mid + 1, r));
        };
        return dfs(0, nums.size() - 1);
    }
};
```

#### Go

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
	var dfs func(int, int) *TreeNode
	dfs = func(l, r int) *TreeNode {
		if l > r {
			return nil
		}
		mid := (l + r) >> 1
		return &TreeNode{nums[mid], dfs(l, mid-1), dfs(mid+1, r)}
	}

	return dfs(0, len(nums)-1)
}
```

#### TypeScript

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
    const dfs = (l: number, r: number): TreeNode | null => {
        if (l > r) {
            return null;
        }
        const mid = (l + r) >> 1;
        return new TreeNode(nums[mid], dfs(l, mid - 1), dfs(mid + 1, r));
    };
    return dfs(0, nums.length - 1);
}
```

#### Rust

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
    fn dfs(nums: &Vec<i32>, l: usize, r: usize) -> Option<Rc<RefCell<TreeNode>>> {
        if l >= r {
            return None;
        }
        let mid = (l + r) >> 1;
        Some(Rc::new(RefCell::new(TreeNode {
            val: nums[mid],
            left: Self::dfs(nums, l, mid),
            right: Self::dfs(nums, mid + 1, r),
        })))
    }
    pub fn sorted_array_to_bst(nums: Vec<i32>) -> Option<Rc<RefCell<TreeNode>>> {
        Self::dfs(&nums, 0, nums.len())
    }
}
```

#### JavaScript

```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {number[]} nums
 * @return {TreeNode}
 */
var sortedArrayToBST = function (nums) {
    function dfs(l, r) {
        if (l > r) {
            return null;
        }
        const mid = (l + r) >> 1;
        return new TreeNode(nums[mid], dfs(l, mid - 1), dfs(mid + 1, r));
    }

    return dfs(0, nums.length - 1);
};
```

#### Swift

```swift
/**
* class TreeNode {
*     var val: Int
*     var left: TreeNode?
*     var right: TreeNode?
*
*     init(_ val: Int, _ left: TreeNode? = nil, _ right: TreeNode? = nil) {
*         self.val = val
*         self.left = left
*         self.right = right
*     }
* }
*/

class Solution {
    private var nums: [Int]!

    func sortedArrayToBST(_ nums: [Int]) -> TreeNode? {
        self.nums = nums
        return dfs(0, nums.count - 1)
    }

    private func dfs(_ l: Int, _ r: Int) -> TreeNode? {
        if l > r {
            return nil
        }
        let mid = (l + r) / 2
        return TreeNode(nums[mid], dfs(l, mid - 1), dfs(mid + 1, r))
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
