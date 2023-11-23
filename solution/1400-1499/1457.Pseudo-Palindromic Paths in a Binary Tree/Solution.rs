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
    pub fn pseudo_palindromic_paths(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        fn dfs(root: Option<Rc<RefCell<TreeNode>>>, mask: i32) -> i32 {
            if let Some(node) = root {
                let mut mask = mask;
                let val = node.borrow().val;
                mask ^= 1 << val;

                if node.borrow().left.is_none() && node.borrow().right.is_none() {
                    return if (mask & (mask - 1)) == 0 { 1 } else { 0 };
                }

                return (
                    dfs(node.borrow().left.clone(), mask) + dfs(node.borrow().right.clone(), mask)
                );
            }
            0
        }

        dfs(root, 0)
    }
}
