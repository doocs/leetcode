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
use std::collections::VecDeque;
impl Solution {
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, mut sum: i32) -> i32 {
        let mut res = 0;
        if root.is_none() {
            return res;
        }
        let root = root.as_ref().unwrap().borrow();
        sum -= root.val;
        if sum == 0 {
            res += 1;
        }
        res + Self::dfs(&root.left, sum) + Self::dfs(&root.right, sum)
    }

    pub fn path_sum(root: Option<Rc<RefCell<TreeNode>>>, sum: i32) -> i32 {
        let mut queue = VecDeque::new();
        if root.is_some() {
            queue.push_back(root);
        }
        let mut res = 0;
        while let Some(mut root) = queue.pop_front() {
            res += Self::dfs(&root, sum);
            let mut root = root.as_mut().unwrap().borrow_mut();
            if root.left.is_some() {
                queue.push_back(root.left.take());
            }
            if root.right.is_some() {
                queue.push_back(root.right.take());
            }
        }
        res
    }
}
