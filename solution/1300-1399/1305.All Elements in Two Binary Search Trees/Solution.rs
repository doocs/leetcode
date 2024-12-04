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
        let mut a = Vec::new();
        let mut b = Vec::new();

        Solution::dfs(&root1, &mut a);
        Solution::dfs(&root2, &mut b);

        let mut ans = Vec::new();
        let (mut i, mut j) = (0, 0);

        while i < a.len() && j < b.len() {
            if a[i] <= b[j] {
                ans.push(a[i]);
                i += 1;
            } else {
                ans.push(b[j]);
                j += 1;
            }
        }

        while i < a.len() {
            ans.push(a[i]);
            i += 1;
        }

        while j < b.len() {
            ans.push(b[j]);
            j += 1;
        }

        ans
    }

    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, nums: &mut Vec<i32>) {
        if let Some(node) = root {
            let node = node.borrow();
            Solution::dfs(&node.left, nums);
            nums.push(node.val);
            Solution::dfs(&node.right, nums);
        }
    }
}
