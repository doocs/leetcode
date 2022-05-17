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
    fn dfs(left: &Option<Rc<RefCell<TreeNode>>>, right: &Option<Rc<RefCell<TreeNode>>>) -> bool {
        if left.is_none() && right.is_none() {
            return true;
        }
        if left.is_none() || right.is_none() {
            return false;
        }
        let l = left.as_ref().unwrap().borrow();
        let r = right.as_ref().unwrap().borrow();
        l.val == r.val && Self::dfs(&l.left, &r.right) && Self::dfs(&l.right, &r.left)
    }

    pub fn is_symmetric(root: Option<Rc<RefCell<TreeNode>>>) -> bool {
        if root.is_none() {
            return true;
        }
        let node = root.as_ref().unwrap().borrow();
        Self::dfs(&node.left, &node.right)
    }
}
