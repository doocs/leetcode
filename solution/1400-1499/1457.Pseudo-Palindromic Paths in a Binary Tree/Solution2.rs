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
    pub fn pseudo_palindromic_paths(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        let mut ans = 0;
        let mut stk = vec![(root, 0)];
        while let Some((node, mask)) = stk.pop() {
            if let Some(node) = node {
                let node = node.borrow();
                let mask = mask ^ (1 << node.val);
                if node.left.is_none() && node.right.is_none() {
                    if mask & (mask - 1) == 0 {
                        ans += 1;
                    }
                } else {
                    stk.push((node.right.clone(), mask));
                    stk.push((node.left.clone(), mask));
                }
            }
        }
        ans
    }
}
