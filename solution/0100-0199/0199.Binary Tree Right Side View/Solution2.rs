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
    pub fn right_side_view(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<i32> {
        let mut ans = Vec::new();
        fn dfs(node: Option<Rc<RefCell<TreeNode>>>, depth: usize, ans: &mut Vec<i32>) {
            if let Some(node_ref) = node {
                let node = node_ref.borrow();
                if ans.len() == depth {
                    ans.push(node.val);
                }
                dfs(node.right.clone(), depth + 1, ans);
                dfs(node.left.clone(), depth + 1, ans);
            }
        }
        dfs(root, 0, &mut ans);
        ans
    }
}
