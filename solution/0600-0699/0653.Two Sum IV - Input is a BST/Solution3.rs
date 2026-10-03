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
use std::collections::HashSet;
use std::rc::Rc;

impl Solution {
    pub fn find_target(root: Option<Rc<RefCell<TreeNode>>>, k: i32) -> bool {
        let mut vis = HashSet::new();
        let mut stk = Vec::new();
        if root.is_some() {
            stk.push(root);
        }
        while let Some(node) = stk.pop() {
            if let Some(node) = node {
                let (val, left, right) = {
                    let b = node.borrow();
                    (b.val, b.left.clone(), b.right.clone())
                };
                if vis.contains(&(k - val)) {
                    return true;
                }
                vis.insert(val);
                if right.is_some() {
                    stk.push(right);
                }
                if left.is_some() {
                    stk.push(left);
                }
            }
        }
        false
    }
}
