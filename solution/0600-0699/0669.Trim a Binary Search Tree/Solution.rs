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
    pub fn trim_bst(
        mut root: Option<Rc<RefCell<TreeNode>>>,
        low: i32,
        high: i32,
    ) -> Option<Rc<RefCell<TreeNode>>> {
        if root.is_none() {
            return root;
        }
        {
            let mut node = root.as_mut().unwrap().borrow_mut();
            if node.val < low {
                return Self::trim_bst(node.right.take(), low, high);
            }
            if node.val > high {
                return Self::trim_bst(node.left.take(), low, high);
            }
            node.left = Self::trim_bst(node.left.take(), low, high);
            node.right = Self::trim_bst(node.right.take(), low, high);
        }
        root
    }
}
