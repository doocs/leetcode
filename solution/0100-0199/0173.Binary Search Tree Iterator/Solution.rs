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
    vals: Vec<i32>,
    index: usize,
}

use std::cell::RefCell;
use std::rc::Rc;
/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl BSTIterator {
    fn inorder(root: &Option<Rc<RefCell<TreeNode>>>, res: &mut Vec<i32>) {
        if let Some(node) = root {
            let node = node.as_ref().borrow();
            Self::inorder(&node.left, res);
            res.push(node.val);
            Self::inorder(&node.right, res);
        }
    }

    fn new(root: Option<Rc<RefCell<TreeNode>>>) -> Self {
        let mut vals = vec![];
        Self::inorder(&root, &mut vals);
        BSTIterator { vals, index: 0 }
    }

    fn next(&mut self) -> i32 {
        self.index += 1;
        self.vals[self.index - 1]
    }

    fn has_next(&self) -> bool {
        self.index != self.vals.len()
    }
}
