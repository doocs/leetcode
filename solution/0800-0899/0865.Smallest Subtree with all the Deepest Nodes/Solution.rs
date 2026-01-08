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
    pub fn subtree_with_all_deepest(
        root: Option<Rc<RefCell<TreeNode>>>,
    ) -> Option<Rc<RefCell<TreeNode>>> {
        fn dfs(
            root: Option<Rc<RefCell<TreeNode>>>,
        ) -> (Option<Rc<RefCell<TreeNode>>>, i32) {
            if let Some(node) = root {
                let left = node.borrow().left.clone();
                let right = node.borrow().right.clone();

                let (l, ld) = dfs(left);
                let (r, rd) = dfs(right);

                if ld > rd {
                    (l, ld + 1)
                } else if ld < rd {
                    (r, rd + 1)
                } else {
                    (Some(node), ld + 1)
                }
            } else {
                (None, 0)
            }
        }

        dfs(root).0
    }
}
