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
    pub fn build_tree(preorder: Vec<i32>, inorder: Vec<i32>) -> Option<Rc<RefCell<TreeNode>>> {
        if inorder.is_empty() {
            return None;
        }
        let val = preorder[0];
        let i = inorder.iter().position(|num| *num == val).unwrap();
        Some(Rc::new(RefCell::new(TreeNode {
            val,
            left: Self::build_tree(preorder[1..i + 1].to_vec(), inorder[..i].to_vec()),
            right: Self::build_tree(preorder[i + 1..].to_vec(), inorder[i + 1..].to_vec()),
        })))
    }
}
