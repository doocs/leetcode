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
        preorder: &Vec<i32>,
        next: &Vec<usize>,
        left: usize,
        right: usize,
    ) -> Option<Rc<RefCell<TreeNode>>> {
        if left >= right {
            return None;
        }
        Some(Rc::new(RefCell::new(TreeNode {
            val: preorder[left],
            left: Self::dfs(preorder, next, left + 1, next[left]),
            right: Self::dfs(preorder, next, next[left], right),
        })))
    }

    pub fn bst_from_preorder(preorder: Vec<i32>) -> Option<Rc<RefCell<TreeNode>>> {
        let n = preorder.len();
        let mut stack = Vec::new();
        let mut next = vec![n; n];
        for i in (0..n).rev() {
            while !stack.is_empty() && preorder[*stack.last().unwrap()] < preorder[i] {
                stack.pop();
            }
            if !stack.is_empty() {
                next[i] = *stack.last().unwrap();
            }
            stack.push(i);
        }
        Self::dfs(&preorder, &next, 0, n)
    }
}
