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
    fn reset(
        inorder: &Vec<i32>,
        i_left: usize,
        i_right: usize,
        postorder: &Vec<i32>,
        p_left: usize,
        p_right: usize,
    ) -> Option<Rc<RefCell<TreeNode>>> {
        if i_left == i_right {
            return None;
        }
        let val = postorder[p_right - 1];
        let index = inorder.iter().position(|&v| v == val).unwrap();
        Some(Rc::new(RefCell::new(TreeNode {
            val,
            left: Self::reset(
                inorder,
                i_left,
                index,
                postorder,
                p_left,
                p_left + index - i_left,
            ),
            right: Self::reset(
                inorder,
                index + 1,
                i_right,
                postorder,
                p_left + index - i_left,
                p_right - 1,
            ),
        })))
    }

    pub fn build_tree(inorder: Vec<i32>, postorder: Vec<i32>) -> Option<Rc<RefCell<TreeNode>>> {
        let n = inorder.len();
        Self::reset(&inorder, 0, n, &postorder, 0, n)
    }
}
