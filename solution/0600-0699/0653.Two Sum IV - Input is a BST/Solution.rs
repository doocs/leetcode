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
use std::cell::RefCell;
use std::collections::{HashSet, VecDeque};
use std::rc::Rc;
impl Solution {
    pub fn find_target(root: Option<Rc<RefCell<TreeNode>>>, k: i32) -> bool {
        let mut set = HashSet::new();
        let mut q = VecDeque::new();
        q.push_back(root);
        while let Some(node) = q.pop_front() {
            if let Some(node) = node {
                let mut node = node.as_ref().borrow_mut();
                if set.contains(&node.val) {
                    return true;
                }
                set.insert(k - node.val);
                q.push_back(node.left.take());
                q.push_back(node.right.take());
            }
        }
        false
    }
}
