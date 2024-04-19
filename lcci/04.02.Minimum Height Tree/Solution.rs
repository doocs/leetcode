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
    fn dfs(nums: &Vec<i32>, l: usize, r: usize) -> Option<Rc<RefCell<TreeNode>>> {
        if l >= r {
            return None;
        }
        let mid = (l + r) >> 1;
        Some(
            Rc::new(
                RefCell::new(TreeNode {
                    val: nums[mid],
                    left: Self::dfs(nums, l, mid),
                    right: Self::dfs(nums, mid + 1, r),
                })
            )
        )
    }
    pub fn sorted_array_to_bst(nums: Vec<i32>) -> Option<Rc<RefCell<TreeNode>>> {
        Self::dfs(&nums, 0, nums.len())
    }
}
