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
use std::collections::HashMap;
impl Solution {
    fn dfs(
        root: &Option<Rc<RefCell<TreeNode>>>,
        map: &mut HashMap<i32, i32>,
        max: &mut i32,
    ) -> i32 {
        if root.is_none() {
            return 0;
        }
        let node = root.as_ref().unwrap().borrow();
        let sum = node.val + Self::dfs(&node.left, map, max) + Self::dfs(&node.right, map, max);
        map.insert(sum, map.get(&sum).unwrap_or(&0) + 1);
        *max = (*max).max(map[&sum]);
        sum
    }

    pub fn find_frequent_tree_sum(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<i32> {
        let mut map = HashMap::new();
        let mut max = 0;
        let mut res = Vec::new();
        Self::dfs(&root, &mut map, &mut max);
        for (k, v) in map.into_iter() {
            if v == max {
                res.push(k);
            }
        }
        res
    }
}
