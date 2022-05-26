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
    pub fn zigzag_level_order(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<Vec<i32>> {
        let mut res = vec![];
        if root.is_none() {
            return res;
        }
        let mut is_desc = false;
        let mut q = VecDeque::new();
        q.push_back(root);
        while !q.is_empty() {
            let mut arr = vec![];
            for _ in 0..q.len() {
                if let Some(node) = q.pop_front().unwrap() {
                    let mut node = node.borrow_mut();
                    arr.push(node.val);
                    if node.left.is_some() {
                        q.push_back(node.left.take());
                    }
                    if node.right.is_some() {
                        q.push_back(node.right.take());
                    }
                }
            }
            if is_desc {
                arr.reverse();
            }
            is_desc = !is_desc;
            res.push(arr);
        }
        res
    }
}
