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
        t: &mut Vec<i32>,
        ans: &mut Vec<Vec<i32>>
    ) {
        if let Some(node) = root.as_ref() {
            let node = node.borrow();
            t.push(node.val);
            target -= node.val;
            if node.left.is_none() && node.right.is_none() && target == 0 {
                ans.push(t.clone());
            }
            Self::dfs(&node.left, target, t, ans);
            Self::dfs(&node.right, target, t, ans);
            t.pop();
        }
    }

    pub fn path_sum(root: Option<Rc<RefCell<TreeNode>>>, target: i32) -> Vec<Vec<i32>> {
        let mut ans = vec![];
        Self::dfs(&root, target, &mut vec![], &mut ans);
        ans
    }
}
