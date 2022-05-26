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
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, pre: &mut Option<i32>) -> bool {
        if root.is_none() {
            return true;
        }
        let root = root.as_ref().unwrap().borrow();
        if !Self::dfs(&root.left, pre) {
            return false;
        }
        if pre.is_some() && pre.unwrap() >= root.val {
            return false;
        }
        *pre = Some(root.val);
        Self::dfs(&root.right, pre)
    }

    pub fn is_valid_bst(root: Option<Rc<RefCell<TreeNode>>>) -> bool {
        Self::dfs(&root, &mut None)
    }
}
