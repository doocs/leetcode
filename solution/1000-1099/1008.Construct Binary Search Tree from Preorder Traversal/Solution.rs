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
    pub fn bst_from_preorder(preorder: Vec<i32>) -> Option<Rc<RefCell<TreeNode>>> {
        fn dfs(preorder: &Vec<i32>, i: usize, j: usize) -> Option<Rc<RefCell<TreeNode>>> {
            if i > j {
                return None;
            }
            let root = Rc::new(RefCell::new(TreeNode::new(preorder[i])));
            let mut l = i + 1;
            let mut r = j + 1;
            while l < r {
                let mid = (l + r) >> 1;
                if preorder[mid] > preorder[i] {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            let mut root_ref = root.borrow_mut();
            root_ref.left = dfs(preorder, i + 1, l - 1);
            root_ref.right = dfs(preorder, l, j);
            Some(root.clone())
        }

        dfs(&preorder, 0, preorder.len() - 1)
    }
}
