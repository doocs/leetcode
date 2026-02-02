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
    pub fn max_product(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        const MOD: i64 = 1_000_000_007;
        let mut ans: i64 = 0;
        let s = Self::sum(&root);
        Self::dfs(&root, s, &mut ans);
        (ans % MOD) as i32
    }

    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, s: i64, ans: &mut i64) -> i64 {
        if root.is_none() {
            return 0;
        }
        let node = root.as_ref().unwrap().borrow();
        let t = node.val as i64
            + Self::dfs(&node.left, s, ans)
            + Self::dfs(&node.right, s, ans);
        if t < s {
            *ans = (*ans).max(t * (s - t));
        }
        t
    }

    fn sum(root: &Option<Rc<RefCell<TreeNode>>>) -> i64 {
        if root.is_none() {
            return 0;
        }
        let node = root.as_ref().unwrap().borrow();
        node.val as i64 + Self::sum(&node.left) + Self::sum(&node.right)
    }
}
