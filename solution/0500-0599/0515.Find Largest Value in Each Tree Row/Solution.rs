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
    pub fn largest_values(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<i32> {
        let mut res = Vec::new();
        let mut queue = VecDeque::new();
        if root.is_some() {
            queue.push_back(root.clone());
        }
        while !queue.is_empty() {
            let mut max = i32::MIN;
            for _ in 0..queue.len() {
                let node = queue.pop_front().unwrap();
                let node = node.as_ref().unwrap().borrow();
                max = max.max(node.val);
                if node.left.is_some() {
                    queue.push_back(node.left.clone());
                }
                if node.right.is_some() {
                    queue.push_back(node.right.clone());
                }
            }
            res.push(max);
        }
        res
    }
}
