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
        if a.is_none() || b.is_none() {
            return false;
        }
        let a_ref = a.as_ref().unwrap().borrow();
        let b_ref = b.as_ref().unwrap().borrow();

        if a_ref.val == b_ref.val
            && Solution::exam(&a_ref.left, &b_ref.left)
            && Solution::exam(&a_ref.right, &b_ref.right)
        {
            return true;
        }
        Solution::is_sub_structure(a_ref.left.clone(), b.clone())
            || Solution::is_sub_structure(a_ref.right.clone(), b.clone())
    }

    fn exam(a: &Option<Rc<RefCell<TreeNode>>>, b: &Option<Rc<RefCell<TreeNode>>>) -> bool {
        if a.is_none() && b.is_some() {
            return false;
        }
        if b.is_none() || a.is_none() && b.is_none() {
            return true;
        }
        let a = a.as_ref().unwrap().borrow();
        let b = b.as_ref().unwrap().borrow();
        a.val == b.val && Solution::exam(&a.left, &b.left) && Solution::exam(&a.right, &b.right)
    }
}
