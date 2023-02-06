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
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>) -> bool {
        let root = root.as_ref().unwrap().as_ref().borrow();
        if root.left.is_none() {
            return root.val == 1;
        }
        if root.val == 2 {
            return Self::dfs(&root.left) || Self::dfs(&root.right);
        }
        Self::dfs(&root.left) && Self::dfs(&root.right)
    }

    pub fn evaluate_tree(root: Option<Rc<RefCell<TreeNode>>>) -> bool {
        Self::dfs(&root)
    }
}
