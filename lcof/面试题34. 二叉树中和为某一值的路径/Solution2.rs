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
    fn dfs(
        root: &Option<Rc<RefCell<TreeNode>>>,
        mut target: i32,
        paths: &mut Vec<i32>
    ) -> Vec<Vec<i32>> {
        let node = root.as_ref().unwrap().borrow();
        paths.push(node.val);
        target -= node.val;
        let mut res = vec![];
        // 确定叶结点身份
        if node.left.is_none() && node.right.is_none() {
            if target == 0 {
                res.push(paths.clone());
            }
        } else {
            if node.left.is_some() {
                let res_l = Self::dfs(&node.left, target, paths);
                if !res_l.is_empty() {
                    res = [res, res_l].concat();
                }
            }
            if node.right.is_some() {
                let res_r = Self::dfs(&node.right, target, paths);
                if !res_r.is_empty() {
                    res = [res, res_r].concat();
                }
            }
        }
        paths.pop();
        res
    }
    pub fn path_sum(root: Option<Rc<RefCell<TreeNode>>>, target: i32) -> Vec<Vec<i32>> {
        if root.is_none() {
            return vec![];
        }
        Self::dfs(&root, target, &mut vec![])
    }
}
