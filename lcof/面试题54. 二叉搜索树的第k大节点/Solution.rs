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
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, arr: &mut Vec<i32>) {
        if let Some(node) = root {
            let node = node.borrow();
            Solution::dfs(&node.right, arr);
            arr.push(node.val);
            Solution::dfs(&node.left, arr)
        }
    }
    pub fn kth_largest(root: Option<Rc<RefCell<TreeNode>>>, k: i32) -> i32 {
        let mut arr = vec![];
        Solution::dfs(&root, &mut arr);
        arr[(k - 1) as usize]
    }
}
