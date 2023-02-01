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
    fn dfs(a: &Option<Rc<RefCell<TreeNode>>>, b: &Option<Rc<RefCell<TreeNode>>>) -> bool {
        if a.is_none() && b.is_none() {
            return true;
        }
        if a.is_none() || b.is_none() {
            return false;
        }
        let l = a.as_ref().unwrap().borrow();
        let r = b.as_ref().unwrap().borrow();
        l.val == r.val && Self::dfs(&l.left, &r.right) && Self::dfs(&l.right, &r.left)
    }

    pub fn is_symmetric(root: Option<Rc<RefCell<TreeNode>>>) -> bool {
        Self::dfs(&root, &root)
    }
}
