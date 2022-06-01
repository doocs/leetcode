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
    fn dfs(t1: &Option<Rc<RefCell<TreeNode>>>, t2: &Option<Rc<RefCell<TreeNode>>>) -> bool {
        if t1.is_none() && t2.is_none() {
            return true;
        }
        if t1.is_none() || t2.is_none() {
            return false;
        }
        let r1 = t1.as_ref().unwrap().borrow();
        let r2 = t2.as_ref().unwrap().borrow();
        if r1.val == r2.val {
            return Self::dfs(&r1.left, &r2.left) && Self::dfs(&r1.right, &r2.right);
        }
        Self::dfs(&r1.left, t2) || Self::dfs(&r1.right, t2)
    }

    pub fn check_sub_tree(
        t1: Option<Rc<RefCell<TreeNode>>>,
        t2: Option<Rc<RefCell<TreeNode>>>,
    ) -> bool {
        Self::dfs(&t1, &t2)
    }
}
