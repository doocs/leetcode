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
    pub fn right_side_view(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<i32> {
        let mut ans = vec![];
        if root.is_none() {
            return ans;
        }
        let mut q = VecDeque::new();
        q.push_back(root);
        while !q.is_empty() {
            let k = q.len();
            ans.push(q[0].as_ref().unwrap().borrow().val);
            for _ in 0..k {
                if let Some(node) = q.pop_front().unwrap() {
                    let mut node = node.borrow_mut();
                    if node.right.is_some() {
                        q.push_back(node.right.take());
                    }
                    if node.left.is_some() {
                        q.push_back(node.left.take());
                    }
                }
            }
        }
        ans
    }
}
