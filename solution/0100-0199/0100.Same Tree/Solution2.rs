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
    pub fn is_same_tree(
        mut p: Option<Rc<RefCell<TreeNode>>>,
        mut q: Option<Rc<RefCell<TreeNode>>>,
    ) -> bool {
        let mut queue = VecDeque::new();
        if p.is_some() {
            queue.push_back(p.take());
        }
        if q.is_some() {
            queue.push_back(q.take());
        }
        if queue.len() == 1 {
            return false;
        }
        while queue.len() != 0 {
            if let (Some(mut node1), Some(mut node2)) = (queue.pop_front(), queue.pop_front()) {
                let mut node1 = node1.as_mut().unwrap().borrow_mut();
                let mut node2 = node2.as_mut().unwrap().borrow_mut();
                if node1.val != node2.val {
                    return false;
                }
                match (node1.left.is_some(), node2.left.is_some()) {
                    (false, false) => {}
                    (true, true) => {
                        queue.push_back(node1.left.take());
                        queue.push_back(node2.left.take());
                    }
                    (_, _) => {
                        return false;
                    }
                }
                match (node1.right.is_some(), node2.right.is_some()) {
                    (false, false) => {}
                    (true, true) => {
                        queue.push_back(node1.right.take());
                        queue.push_back(node2.right.take());
                    }
                    (_, _) => {
                        return false;
                    }
                }
            }
        }
        true
    }
}
