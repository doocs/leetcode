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
    fn dfs(root: Option<Rc<RefCell<TreeNode>>>, res: &mut Vec<i32>, k: usize) {
        if let Some(node) = root {
            let mut node = node.borrow_mut();
            Self::dfs(node.left.take(), res, k);
            res.push(node.val);
            if res.len() >= k {
                return;
            }
            Self::dfs(node.right.take(), res, k);
        }
    }
    pub fn kth_smallest(root: Option<Rc<RefCell<TreeNode>>>, k: i32) -> i32 {
        let k = k as usize;
        let mut res: Vec<i32> = Vec::with_capacity(k);
        Self::dfs(root, &mut res, k);
        res[k - 1]
    }
}
