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
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, sub_root: &Option<Rc<RefCell<TreeNode>>>) -> bool {
        if root.is_none() && sub_root.is_none() {
            return true;
        }
        if root.is_none() || sub_root.is_none() {
            return false;
        }
        let root = root.as_ref().unwrap().borrow();
        let sub_root = sub_root.as_ref().unwrap().borrow();
        root.val == sub_root.val
            && Self::dfs(&root.left, &sub_root.left)
            && Self::dfs(&root.right, &sub_root.right)
    }

    fn help(
        root: &Option<Rc<RefCell<TreeNode>>>,
        sub_root: &Option<Rc<RefCell<TreeNode>>>,
    ) -> bool {
        if root.is_none() {
            return false;
        }
        Self::dfs(root, sub_root)
            || Self::help(&root.as_ref().unwrap().borrow().left, sub_root)
            || Self::help(&root.as_ref().unwrap().borrow().right, sub_root)
    }

    pub fn is_subtree(
        root: Option<Rc<RefCell<TreeNode>>>,
        sub_root: Option<Rc<RefCell<TreeNode>>>,
    ) -> bool {
        Self::help(&root, &sub_root)
    }
}
