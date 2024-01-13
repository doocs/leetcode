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
impl Solution {
    pub fn preorder_traversal(mut root: Option<Rc<RefCell<TreeNode>>>) -> Vec<i32> {
        let mut res = vec![];
        if root.is_none() {
            return res;
        }
        let mut stack = vec![];
        while root.is_some() || stack.len() != 0 {
            if root.is_some() {
                let val = root.as_ref().unwrap().as_ref().borrow().val;
                let left = root.as_ref().unwrap().as_ref().borrow_mut().left.take();
                res.push(val);
                stack.push(root);
                root = left;
            } else {
                root = stack.pop().unwrap().as_ref().unwrap().as_ref().borrow_mut().right.take();
            }
        }
        res
    }
}
