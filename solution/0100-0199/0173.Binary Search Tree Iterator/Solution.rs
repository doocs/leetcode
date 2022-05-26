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
    stack: Vec<Option<Rc<RefCell<TreeNode>>>>,
}

use std::rc::Rc;
use std::cell::RefCell;
/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl BSTIterator {
    fn dfs(
        mut root: Option<Rc<RefCell<TreeNode>>>,
        stack: &mut Vec<Option<Rc<RefCell<TreeNode>>>>,
    ) {
        if root.is_some() {
            let left = root.as_mut().unwrap().borrow_mut().left.take();
            stack.push(root);
            Self::dfs(left, stack);
        }
    }

    fn new(root: Option<Rc<RefCell<TreeNode>>>) -> Self {
        let mut stack = vec![];
        Self::dfs(root, &mut stack);
        BSTIterator { stack }
    }

    fn next(&mut self) -> i32 {
        let node = self.stack.pop().unwrap().unwrap();
        let mut node = node.borrow_mut();
        if node.right.is_some() {
            Self::dfs(node.right.take(), &mut self.stack)
        }
        node.val
    }

    fn has_next(&self) -> bool {
        self.stack.len() != 0
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * let obj = BSTIterator::new(root);
 * let ret_1: i32 = obj.next();
 * let ret_2: bool = obj.has_next();
 */