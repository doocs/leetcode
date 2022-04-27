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
        let mut res = vec![];
        if root.is_none() {
            return res;
        }
        let mut queue: VecDeque<Option<Rc<RefCell<TreeNode>>>> = vec![root].into_iter().collect();
        while !queue.is_empty() {
            let n = queue.len();
            res.push(
                (0..n)
                    .into_iter()
                    .map(|_| {
                        let mut node = queue.pop_front().unwrap();
                        let mut node = node.as_mut().unwrap().borrow_mut();
                        if node.left.is_some() {
                            queue.push_back(node.left.take());
                        }
                        if node.right.is_some() {
                            queue.push_back(node.right.take());
                        }
                        node.val
                    })
                    .collect(),
            );
        }
        res
    }
}
