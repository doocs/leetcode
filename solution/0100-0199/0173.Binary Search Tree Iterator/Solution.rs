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
struct BSTIterator {
    stack: Vec<Rc<RefCell<TreeNode>>>,
}

use std::cell::RefCell;
use std::rc::Rc;
/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl BSTIterator {
    fn new(root: Option<Rc<RefCell<TreeNode>>>) -> Self {
        let mut iterator = BSTIterator { stack: Vec::new() };
        iterator.push_left_spine(root);
        iterator
    }

    fn next(&mut self) -> i32 {
        let node = self.stack.pop().unwrap();
        let (value, right) = {
            let node = node.borrow();
            (node.val, node.right.clone())
        };
        self.push_left_spine(right);
        value
    }

    fn has_next(&self) -> bool {
        !self.stack.is_empty()
    }

    fn push_left_spine(&mut self, mut root: Option<Rc<RefCell<TreeNode>>>) {
        while let Some(node) = root {
            root = node.borrow().left.clone();
            self.stack.push(node);
        }
    }
}
