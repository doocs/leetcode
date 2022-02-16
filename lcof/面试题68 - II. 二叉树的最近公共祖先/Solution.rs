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
    pub fn lowest_common_ancestor(
        root: Option<Rc<RefCell<TreeNode>>>,
        p: Option<Rc<RefCell<TreeNode>>>,
        q: Option<Rc<RefCell<TreeNode>>>,
    ) -> Option<Rc<RefCell<TreeNode>>> {
        if root.is_none() || root == p || root == q {
            return root;
        }
        let left = Self::lowest_common_ancestor(
            root.as_ref().unwrap().borrow_mut().left.take(),
            p.clone(),
            q.clone(),
        );
        let right = Self::lowest_common_ancestor(
            root.as_ref().unwrap().borrow_mut().right.take(),
            p.clone(),
            q.clone(),
        );
        match (left.is_none(), right.is_none()) {
            (true, false) => right,
            (false, true) => left,
            (false, false) => root,
            (true, true) => None,
        }
    }
}
