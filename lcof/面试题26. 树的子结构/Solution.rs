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
    pub fn is_sub_structure(
        a: Option<Rc<RefCell<TreeNode>>>,
        b: Option<Rc<RefCell<TreeNode>>>,
    ) -> bool {
        Self::is_sub_structure_help(&a, &b)
    }

    fn is_sub_structure_help(
        a: &Option<Rc<RefCell<TreeNode>>>,
        b: &Option<Rc<RefCell<TreeNode>>>,
    ) -> bool {
        if a.is_none() || b.is_none() {
            return false;
        }

        Self::dfs(a, b)
            || Self::is_sub_structure_help(&a.as_ref().unwrap().borrow().left, b)
            || Self::is_sub_structure_help(&a.as_ref().unwrap().borrow().right, b)
    }

    fn dfs(a: &Option<Rc<RefCell<TreeNode>>>, b: &Option<Rc<RefCell<TreeNode>>>) -> bool {
        if b.is_none() {
            return true;
        }
        if a.is_none() {
            return false;
        }
        let a = a.as_ref().unwrap().borrow();
        let b = b.as_ref().unwrap().borrow();
        a.val == b.val && Self::dfs(&a.left, &b.left) && Self::dfs(&a.right, &b.right)
    }
}
