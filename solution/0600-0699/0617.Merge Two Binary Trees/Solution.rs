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
    pub fn merge_trees(
        root1: Option<Rc<RefCell<TreeNode>>>,
        root2: Option<Rc<RefCell<TreeNode>>>,
    ) -> Option<Rc<RefCell<TreeNode>>> {
        match (root1.is_some(), root2.is_some()) {
            (false, false) => None,
            (true, false) => root1,
            (false, true) => root2,
            (true, true) => {
                {
                    let mut r1 = root1.as_ref().unwrap().borrow_mut();
                    let mut r2 = root2.as_ref().unwrap().borrow_mut();
                    r1.val += r2.val;
                    r1.left = Self::merge_trees(r1.left.take(), r2.left.take());
                    r1.right = Self::merge_trees(r1.right.take(), r2.right.take());
                }
                root1
            }
        }
    }
}
