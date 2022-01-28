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
use std::mem;
use std::rc::Rc;
use std::cell::RefCell;

impl Solution {
    pub fn mirror_tree(mut root: Option<Rc<RefCell<TreeNode>>>) -> Option<Rc<RefCell<TreeNode>>> {
        fn dfs(root: &Option<Rc<RefCell<TreeNode>>>) {
            if let Some(node) = root {
                let mut node = node.borrow_mut();
                let lt = mem::replace(&mut node.left, None);
                let rt = mem::replace(&mut node.right, lt);
                mem::replace(&mut node.left, rt);
                dfs(&node.left);
                dfs(&node.right);
            }
        }
        dfs(&root);
        root
    }
}
