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
    pub fn inorder_traversal(mut root: Option<Rc<RefCell<TreeNode>>>) -> Vec<i32> {
        let mut ans = vec![];
        let mut stk = vec![];
        while root.is_some() || !stk.is_empty() {
            if root.is_some() {
                let next = root.as_mut().unwrap().borrow_mut().left.take();
                stk.push(root);
                root = next;
            } else {
                let mut node = stk.pop().unwrap();
                let mut node = node.as_mut().unwrap().borrow_mut();
                ans.push(node.val);
                root = node.right.take();
            }
        }
        ans
    }
}
