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
    pub fn sum_root_to_leaf(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        fn dfs(node: Option<Rc<RefCell<TreeNode>>>, x: i32) -> i32 {
            if let Some(n) = node {
                let n_ref = n.borrow();
                let x = (x << 1) | n_ref.val;

                if n_ref.left.is_none() && n_ref.right.is_none() {
                    return x;
                }

                dfs(n_ref.left.clone(), x) + dfs(n_ref.right.clone(), x)
            } else {
                0
            }
        }

        dfs(root, 0)
    }
}
