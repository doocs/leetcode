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
    pub fn leaf_similar(
        root1: Option<Rc<RefCell<TreeNode>>>,
        root2: Option<Rc<RefCell<TreeNode>>>,
    ) -> bool {
        let mut l1 = Vec::new();
        let mut l2 = Vec::new();
        Self::dfs(&root1, &mut l1);
        Self::dfs(&root2, &mut l2);
        l1 == l2
    }

    fn dfs(node: &Option<Rc<RefCell<TreeNode>>>, nums: &mut Vec<i32>) {
        if let Some(n) = node {
            let n = n.borrow();
            if n.left.is_none() && n.right.is_none() {
                nums.push(n.val);
                return;
            }
            if n.left.is_some() {
                Self::dfs(&n.left, nums);
            }
            if n.right.is_some() {
                Self::dfs(&n.right, nums);
            }
        }
    }
}
