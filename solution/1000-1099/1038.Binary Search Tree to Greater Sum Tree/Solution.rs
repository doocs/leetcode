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
use std::rc::Rc;

impl Solution {
    pub fn bst_to_gst(root: Option<Rc<RefCell<TreeNode>>>) -> Option<Rc<RefCell<TreeNode>>> {
        let mut s = 0;
        Self::dfs(&root, &mut s);
        root
    }

    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, s: &mut i32) {
        if let Some(node) = root {
            let mut node = node.borrow_mut();
            Self::dfs(&node.right, s);
            *s += node.val;
            node.val = *s;
            Self::dfs(&node.left, s);
        }
    }
}
