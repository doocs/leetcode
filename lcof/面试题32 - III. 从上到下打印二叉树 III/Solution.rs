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
    pub fn level_order(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<Vec<i32>> {
        let mut res = Vec::new();
        if root.is_none() {
            return res;
        }
        let mut nodes = VecDeque::new();
        nodes.push_back(root.unwrap());
        let mut is_even = false;
        while !nodes.is_empty() {
            let mut values = Vec::new();
            for _ in 0..nodes.len() {
                let node = nodes.pop_front().unwrap();
                let mut node = node.borrow_mut();
                values.push(node.val);
                if node.left.is_some() {
                    nodes.push_back(node.left.take().unwrap())
                }
                if node.right.is_some() {
                    nodes.push_back(node.right.take().unwrap())
                }
            }
            if is_even {
                values.reverse()
            }
            res.push(values);
            is_even = !is_even
        }
        res
    }
}