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
struct BSTIterator {
    stack: Vec<i32>
}


/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl BSTIterator {
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, stack: &mut Vec<i32>) {
        let node = root.as_ref().unwrap().borrow();
        if node.right.is_some() {
            Self::dfs(&node.right, stack);
        }
        stack.push(node.val);
        if node.left.is_some() {
            Self::dfs(&node.left, stack);
        }
    }

    fn new(root: Option<Rc<RefCell<TreeNode>>>) -> Self {
        let mut stack = Vec::new();
        Self::dfs(&root, &mut stack);
        Self {
            stack
        }
    }

    fn next(&mut self) -> i32 {
        self.stack.pop().unwrap()
    }

    fn has_next(&self) -> bool {
        !self.stack.is_empty()
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * let obj = BSTIterator::new(root);
 * let ret_1: i32 = obj.next();
 * let ret_2: bool = obj.has_next();
 */
