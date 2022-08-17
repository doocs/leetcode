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
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, depth: i32, max_depth: &mut i32, res: &mut i32) {
        if let Some(node) = root {
            let node = node.borrow();
            if node.left.is_none() && node.right.is_none() {
                if depth == *max_depth {
                    *res += node.val;
                } else if depth > *max_depth {
                    *max_depth = depth;
                    *res = node.val
                }
                return;
            }
            Self::dfs(&node.left, depth + 1, max_depth, res);
            Self::dfs(&node.right, depth + 1, max_depth, res);
        }
    }

    pub fn deepest_leaves_sum(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        let mut res = 0;
        let mut max_depth = 0;
        Self::dfs(&root, 0, &mut max_depth, &mut res);
        res
    }
}
