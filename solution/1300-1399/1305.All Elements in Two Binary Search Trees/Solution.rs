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
    pub fn get_all_elements(
        root1: Option<Rc<RefCell<TreeNode>>>,
        root2: Option<Rc<RefCell<TreeNode>>>,
    ) -> Vec<i32> {
        fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, t: &mut Vec<i32>) {
            if let Some(root) = root {
                dfs(&root.borrow().left, t);
                t.push(root.borrow().val);
                dfs(&root.borrow().right, t);
            }
        }

        let mut t1 = Vec::new();
        let mut t2 = Vec::new();
        dfs(&root1, &mut t1);
        dfs(&root2, &mut t2);

        let mut ans = Vec::new();
        let mut i = 0;
        let mut j = 0;
        while i < t1.len() && j < t2.len() {
            if t1[i] < t2[j] {
                ans.push(t1[i]);
                i += 1;
            } else {
                ans.push(t2[j]);
                j += 1;
            }
        }
        while i < t1.len() {
            ans.push(t1[i]);
            i += 1;
        }
        while j < t2.len() {
            ans.push(t2[j]);
            j += 1;
        }
        ans
    }
}
