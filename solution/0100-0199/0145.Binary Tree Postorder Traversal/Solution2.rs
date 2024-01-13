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
    pub fn postorder_traversal(mut root: Option<Rc<RefCell<TreeNode>>>) -> Vec<i32> {
        let mut res = vec![];
        let mut stack = vec![];
        while root.is_some() || !stack.is_empty() {
            if root.is_some() {
                let next = root.as_mut().unwrap().borrow_mut().left.take();
                stack.push(root);
                root = next;
            } else {
                root = stack.pop().unwrap();
                let next = root.as_mut().unwrap().borrow_mut().right.take();
                if next.is_some() {
                    stack.push(root);
                } else {
                    res.push(root.as_ref().unwrap().borrow().val);
                }
                root = next;
            }
        }
        res
    }
}
