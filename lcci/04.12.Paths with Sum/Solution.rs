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
    pub fn path_sum(root: Option<Rc<RefCell<TreeNode>>>, sum: i32) -> i32 {
        let mut cnt = HashMap::new();
        cnt.insert(0, 1);
        return Self::dfs(root, sum, 0, &mut cnt);
    }

    fn dfs(
        root: Option<Rc<RefCell<TreeNode>>>,
        sum: i32,
        s: i32,
        cnt: &mut HashMap<i32, i32>
    ) -> i32 {
        if let Some(node) = root {
            let node = node.borrow();
            let s = s + node.val;
            let mut ans = *cnt.get(&(s - sum)).unwrap_or(&0);
            *cnt.entry(s).or_insert(0) += 1;
            ans += Self::dfs(node.left.clone(), sum, s, cnt);
            ans += Self::dfs(node.right.clone(), sum, s, cnt);
            *cnt.entry(s).or_insert(0) -= 1;
            return ans;
        }
        return 0;
    }
}
