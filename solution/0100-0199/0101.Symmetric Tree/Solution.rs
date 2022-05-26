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
    fn dfs(root1: &Option<Rc<RefCell<TreeNode>>>, root2: &Option<Rc<RefCell<TreeNode>>>) -> bool {
        if root1.is_none() && root2.is_none() {
            return true;
        }
        if root1.is_none() || root2.is_none() {
            return false;
        }
        let node1 = root1.as_ref().unwrap().borrow();
        let node2 = root2.as_ref().unwrap().borrow();
        node1.val == node2.val
            && Self::dfs(&node1.left, &node2.right)
            && Self::dfs(&node1.right, &node2.left)
    }

    pub fn is_symmetric(root: Option<Rc<RefCell<TreeNode>>>) -> bool {
        let node = root.as_ref().unwrap().borrow();
        Self::dfs(&node.left, &node.right)
    }
}
