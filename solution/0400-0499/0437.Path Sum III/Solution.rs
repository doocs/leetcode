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
use std::collections::HashMap;
use std::rc::Rc;

impl Solution {
    pub fn path_sum(root: Option<Rc<RefCell<TreeNode>>>, target_sum: i32) -> i32 {
        let mut cnt = HashMap::new();
        cnt.insert(0, 1);

        fn dfs(
            node: Option<Rc<RefCell<TreeNode>>>,
            s: i64,
            target: i64,
            cnt: &mut HashMap<i64, i32>,
        ) -> i32 {
            if let Some(n) = node {
                let n = n.borrow();
                let s = s + n.val as i64;
                let ans = cnt.get(&(s - target)).copied().unwrap_or(0);
                *cnt.entry(s).or_insert(0) += 1;
                let ans = ans
                    + dfs(n.left.clone(), s, target, cnt)
                    + dfs(n.right.clone(), s, target, cnt);
                *cnt.get_mut(&s).unwrap() -= 1;
                ans
            } else {
                0
            }
        }

        dfs(root, 0, target_sum as i64, &mut cnt)
    }
}
