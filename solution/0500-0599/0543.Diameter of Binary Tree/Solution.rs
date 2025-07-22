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
    pub fn diameter_of_binary_tree(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        let mut ans = 0;
        fn dfs(root: Option<Rc<RefCell<TreeNode>>>, ans: &mut i32) -> i32 {
            match root {
                Some(node) => {
                    let node = node.borrow();
                    let l = dfs(node.left.clone(), ans);
                    let r = dfs(node.right.clone(), ans);

                    *ans = (*ans).max(l + r);

                    1 + l.max(r)
                }
                None => 0,
            }
        }
        dfs(root, &mut ans);
        ans
    }
}
