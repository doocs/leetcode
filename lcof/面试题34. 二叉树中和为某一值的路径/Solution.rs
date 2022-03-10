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
    fn dfs(
        root: &Option<Rc<RefCell<TreeNode>>>,
        mut target: i32,
        paths: &mut Vec<i32>,
        res: &mut Vec<Vec<i32>>,
    ) {
        if let Some(node) = root.as_ref() {
            let node = node.borrow();
            paths.push(node.val);
            target -= node.val;
            if node.left.is_none() && node.right.is_none() && target == 0 {
                res.push(paths.clone());
            }
            Solution::dfs(&node.left, target, paths, res);
            Solution::dfs(&node.right, target, paths, res);
            paths.pop();
        }
    }
    pub fn path_sum(root: Option<Rc<RefCell<TreeNode>>>, target: i32) -> Vec<Vec<i32>> {
        let mut res = vec![];
        Solution::dfs(&root, target, &mut vec![], &mut res);
        res
    }
}
