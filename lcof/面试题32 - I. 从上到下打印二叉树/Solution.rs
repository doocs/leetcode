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
use std::collections::VecDeque;
use std::rc::Rc;
impl Solution {
    pub fn level_order(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<i32> {
        let mut ans = Vec::new();
        let mut q = VecDeque::new();
        if let Some(node) = root {
            q.push_back(node);
        }
        while let Some(node) = q.pop_front() {
            let mut node = node.borrow_mut();
            ans.push(node.val);
            if let Some(l) = node.left.take() {
                q.push_back(l);
            }
            if let Some(r) = node.right.take() {
                q.push_back(r);
            }
        }
        ans
    }
}
