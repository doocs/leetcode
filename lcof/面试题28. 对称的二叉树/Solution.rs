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
    pub fn is_symmetric(root: Option<Rc<RefCell<TreeNode>>>) -> bool {
        match root {
            None => true,
            Some(root) => {
                fn dfs(
                    l: &Option<Rc<RefCell<TreeNode>>>,
                    r: &Option<Rc<RefCell<TreeNode>>>,
                ) -> bool {
                    if l.is_none() && r.is_none() {
                        return true;
                    }
                    if l.is_none() || r.is_none() {
                        return false;
                    }
                    let l = l.as_ref().unwrap().borrow();
                    let r = r.as_ref().unwrap().borrow();
                    l.val == r.val && dfs(&l.left, &r.right) && dfs(&l.right, &r.left) && true
                }
                let node = root.borrow();
                dfs(&node.left, &node.right)
            }
        }
    }
}
