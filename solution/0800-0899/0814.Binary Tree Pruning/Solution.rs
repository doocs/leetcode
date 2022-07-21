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
    pub fn prune_tree(root: Option<Rc<RefCell<TreeNode>>>) -> Option<Rc<RefCell<TreeNode>>> {
        if root.is_none() {
            return None;
        }

        let root = root.unwrap();
        let left = Self::prune_tree(root.borrow_mut().left.take());
        let right = Self::prune_tree(root.borrow_mut().right.take());
        if root.borrow().val == 0 && left.is_none() && right.is_none() {
            return None;
        }

        root.borrow_mut().left = left;
        root.borrow_mut().right = right;
        Some(root)
    }
}
