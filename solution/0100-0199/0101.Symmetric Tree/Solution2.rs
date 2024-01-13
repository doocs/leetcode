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
    pub fn is_symmetric(root: Option<Rc<RefCell<TreeNode>>>) -> bool {
        let root = root.unwrap();
        let mut node = root.as_ref().borrow_mut();
        let mut queue = VecDeque::new();
        queue.push_back([node.left.take(), node.right.take()]);
        while let Some([root1, root2]) = queue.pop_front() {
            if root1.is_none() && root2.is_none() {
                continue;
            }
            if root1.is_none() || root2.is_none() {
                return false;
            }
            if let (Some(node1), Some(node2)) = (root1, root2) {
                let mut node1 = node1.as_ref().borrow_mut();
                let mut node2 = node2.as_ref().borrow_mut();
                if node1.val != node2.val {
                    return false;
                }
                queue.push_back([node1.left.take(), node2.right.take()]);
                queue.push_back([node1.right.take(), node2.left.take()]);
            }
        }
        true
    }
}
