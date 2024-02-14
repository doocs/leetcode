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
use std::collections::{ VecDeque };
impl Solution {
    pub fn level_order(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<Vec<i32>> {
        let mut ans = Vec::new();
        if let Some(root_node) = root {
            let mut q = VecDeque::new();
            q.push_back(root_node);
            while !q.is_empty() {
                let mut t = Vec::new();
                for _ in 0..q.len() {
                    if let Some(node) = q.pop_front() {
                        let node_ref = node.borrow();
                        t.push(node_ref.val);
                        if let Some(ref left) = node_ref.left {
                            q.push_back(Rc::clone(left));
                        }
                        if let Some(ref right) = node_ref.right {
                            q.push_back(Rc::clone(right));
                        }
                    }
                }
                ans.push(t);
            }
        }
        ans
    }
}
