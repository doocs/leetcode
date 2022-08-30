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
    pub fn insert_into_max_tree(
        mut root: Option<Rc<RefCell<TreeNode>>>,
        val: i32,
    ) -> Option<Rc<RefCell<TreeNode>>> {
        if root.is_none() || root.as_ref().unwrap().as_ref().borrow().val < val {
            return Some(Rc::new(RefCell::new(TreeNode {
                val,
                left: root.take(),
                right: None,
            })));
        }
        {
            let mut root = root.as_ref().unwrap().as_ref().borrow_mut();
            root.right = Self::insert_into_max_tree(root.right.take(), val);
        }
        root
    }
}
