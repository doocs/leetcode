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
    #[allow(dead_code)]
    pub fn flatten(root: &mut Option<Rc<RefCell<TreeNode>>>) {
        if root.is_none() {
            return;
        }
        let mut v: Vec<Option<Rc<RefCell<TreeNode>>>> = Vec::new();
        // Initialize the vector
        Self::pre_order_traverse(&mut v, root);
        // Traverse the vector
        let n = v.len();
        for i in 0..n - 1 {
            v[i].as_ref().unwrap().borrow_mut().left = None;
            v[i].as_ref().unwrap().borrow_mut().right = v[i + 1].clone();
        }
    }

    #[allow(dead_code)]
    fn pre_order_traverse(
        v: &mut Vec<Option<Rc<RefCell<TreeNode>>>>,
        root: &Option<Rc<RefCell<TreeNode>>>
    ) {
        if root.is_none() {
            return;
        }
        v.push(root.clone());
        let left = root.as_ref().unwrap().borrow().left.clone();
        let right = root.as_ref().unwrap().borrow().right.clone();
        Self::pre_order_traverse(v, &left);
        Self::pre_order_traverse(v, &right);
    }
}
