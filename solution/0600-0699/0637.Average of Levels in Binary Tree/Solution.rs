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
    pub fn average_of_levels(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<f64> {
        let mut ans = vec![];
        let mut q = VecDeque::new();
        if let Some(root_node) = root {
            q.push_back(root_node);
        }
        while !q.is_empty() {
            let n = q.len();
            let mut s: i64 = 0;
            for _ in 0..n {
                if let Some(node) = q.pop_front() {
                    let node_borrow = node.borrow();
                    s += node_borrow.val as i64;
                    if let Some(left) = node_borrow.left.clone() {
                        q.push_back(left);
                    }
                    if let Some(right) = node_borrow.right.clone() {
                        q.push_back(right);
                    }
                }
            }
            ans.push((s as f64) / (n as f64));
        }
        ans
    }
}
