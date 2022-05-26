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
use std::collections::VecDeque;
impl Solution {
    pub fn average_of_levels(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<f64> {
        if root.is_none() {
            return Vec::new();
        }

        let mut q = VecDeque::new();
        q.push_back(Rc::clone(&root.unwrap()));
        let mut ans = Vec::new();
        while !q.is_empty() {
            let n = q.len();
            let mut sum = 0.0;
            for _ in 0..n {
                let node = q.pop_front().unwrap();
                sum += node.borrow().val as f64;
                if node.borrow().left.is_some() {
                    q.push_back(Rc::clone(node.borrow().left.as_ref().unwrap()));
                }
                if node.borrow().right.is_some() {
                    q.push_back(Rc::clone(node.borrow().right.as_ref().unwrap()));
                }
            }
            ans.push(sum / n as f64);
        }
        ans
    }
}
