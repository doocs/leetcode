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
    pub fn find_frequent_tree_sum(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<i32> {
        fn dfs(root: Option<Rc<RefCell<TreeNode>>>, cnt: &mut HashMap<i32, i32>) -> i32 {
            if let Some(node) = root {
                let l = dfs(node.borrow().left.clone(), cnt);
                let r = dfs(node.borrow().right.clone(), cnt);
                let s = l + r + node.borrow().val;
                *cnt.entry(s).or_insert(0) += 1;
                s
            } else {
                0
            }
        }

        let mut cnt = HashMap::new();
        dfs(root, &mut cnt);

        let mx = cnt.values().cloned().max().unwrap_or(0);
        cnt.into_iter()
            .filter(|&(_, v)| v == mx)
            .map(|(k, _)| k)
            .collect()
    }
}
