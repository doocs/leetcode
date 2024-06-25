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
    #[allow(dead_code)]
    pub fn get_minimum_difference(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        let mut ret = i32::MAX;
        let mut prev = i32::MAX;
        Self::traverse(root, &mut prev, &mut ret);
        ret
    }

    #[allow(dead_code)]
    fn traverse(root: Option<Rc<RefCell<TreeNode>>>, prev: &mut i32, ans: &mut i32) {
        let left = root.as_ref().unwrap().borrow().left.clone();
        let right = root.as_ref().unwrap().borrow().right.clone();
        let val = root.as_ref().unwrap().borrow().val;
        if !left.is_none() {
            Self::traverse(left.clone(), prev, ans);
        }
        *ans = std::cmp::min(*ans, (*prev - val).abs());
        *prev = val;
        if !right.is_none() {
            Self::traverse(right.clone(), prev, ans);
        }
    }
}
