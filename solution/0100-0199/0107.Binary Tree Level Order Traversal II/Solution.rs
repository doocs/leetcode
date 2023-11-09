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
use std::{ rc::Rc, cell::RefCell, collections::VecDeque };
impl Solution {
    #[allow(dead_code)]
    pub fn level_order_bottom(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<Vec<i32>> {
        if root.is_none() {
            return vec![];
        }
        let mut ret_vec = Vec::new();
        let mut q = VecDeque::new();

        q.push_back(root);

        while !q.is_empty() {
            let mut cur_vec = Vec::new();
            let mut next_q = VecDeque::new();
            while !q.is_empty() {
                let cur_front = q.front().unwrap().clone();
                q.pop_front();
                cur_vec.push(cur_front.as_ref().unwrap().borrow().val);
                let left = cur_front.as_ref().unwrap().borrow().left.clone();
                let right = cur_front.as_ref().unwrap().borrow().right.clone();
                if !left.is_none() {
                    next_q.push_back(left);
                }
                if !right.is_none() {
                    next_q.push_back(right);
                }
            }
            ret_vec.push(cur_vec);
            q = next_q;
        }

        ret_vec.reverse();
        ret_vec
    }
}
