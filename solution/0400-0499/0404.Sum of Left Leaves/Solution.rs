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
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, is_left: bool) -> i32 {
        let node = root.as_ref().unwrap().borrow();
        let left = &node.left;
        let right = &node.right;
        let mut res = 0;
        if left.is_none() && right.is_none() {
            if is_left {
                return node.val;
            }
            return res;
        }
        if left.is_some() {
            res += Self::dfs(left, true);
        }
        if right.is_some() {
            res += Self::dfs(right, false);
        }
        res
    }

    pub fn sum_of_left_leaves(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        Self::dfs(&root, false)
    }
}
