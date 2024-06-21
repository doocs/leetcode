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
    fn dfs(t1: &Option<Rc<RefCell<TreeNode>>>, t2: &Option<Rc<RefCell<TreeNode>>>) -> bool {
        match (t1, t2) {
            (Some(node1), Some(node2)) => {
                let n1 = node1.borrow();
                let n2 = node2.borrow();
                n1.val == n2.val
                    && Solution::dfs(&n1.left, &n2.left)
                    && Solution::dfs(&n1.right, &n2.right)
            }
            (None, Some(_)) => false,
            (Some(_), None) => false,
            _ => true, // Both are None
        }
    }

    pub fn check_sub_tree(
        t1: Option<Rc<RefCell<TreeNode>>>,
        t2: Option<Rc<RefCell<TreeNode>>>,
    ) -> bool {
        match (t1, t2) {
            (Some(node1), Some(node2)) => {
                let n1 = node1.borrow();
                let n2 = node2.borrow();
                Solution::dfs(&Some(Rc::clone(&node1)), &Some(Rc::clone(&node2)))
                    || Solution::check_sub_tree(n1.left.clone(), Some(Rc::clone(&node2)))
                    || Solution::check_sub_tree(n1.right.clone(), Some(Rc::clone(&node2)))
            }
            (Some(_), None) => true,
            (None, Some(_)) => false,
            _ => true, // Both are None or t1 is None
        }
    }
}
