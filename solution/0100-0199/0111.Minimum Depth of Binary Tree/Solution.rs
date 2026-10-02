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
    pub fn min_depth(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        let Some(root) = root else {
            return 0;
        };
        let mut queue = VecDeque::from([root]);
        let mut depth = 0;
        while !queue.is_empty() {
            depth += 1;
            for _ in 0..queue.len() {
                let node = queue.pop_front().unwrap();
                let node = node.borrow();
                if node.left.is_none() && node.right.is_none() {
                    return depth;
                }
                if let Some(left) = node.left.as_ref() {
                    queue.push_back(left.clone());
                }
                if let Some(right) = node.right.as_ref() {
                    queue.push_back(right.clone());
                }
            }
        }
        depth
    }
}
