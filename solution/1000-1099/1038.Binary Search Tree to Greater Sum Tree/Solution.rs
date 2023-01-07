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
    fn dfs(root: &mut Option<Rc<RefCell<TreeNode>>>, mut sum: i32) -> i32 {
        if let Some(node) = root {
            let mut node = node.as_ref().borrow_mut();
            sum = Self::dfs(&mut node.right, sum) + node.val;
            node.val = sum;
            sum = Self::dfs(&mut node.left, sum);
        }
        sum
    }

    pub fn bst_to_gst(mut root: Option<Rc<RefCell<TreeNode>>>) -> Option<Rc<RefCell<TreeNode>>> {
        Self::dfs(&mut root, 0);
        root
    }
}
