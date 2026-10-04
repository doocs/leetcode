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
    pub fn get_minimum_difference(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        const INF: i32 = 1 << 30;
        let mut ans = INF;
        let mut pre = -INF;
        let mut stk = vec![(root, 0)];
        while let Some((node, state)) = stk.pop() {
            if let Some(node) = node {
                if state == 0 {
                    let left = node.borrow().left.clone();
                    stk.push((Some(node), 1));
                    if left.is_some() {
                        stk.push((left, 0));
                    }
                } else {
                    let right = {
                        let b = node.borrow();
                        ans = ans.min(b.val - pre);
                        pre = b.val;
                        b.right.clone()
                    };
                    if right.is_some() {
                        stk.push((right, 0));
                    }
                }
            }
        }
        ans
    }
}
