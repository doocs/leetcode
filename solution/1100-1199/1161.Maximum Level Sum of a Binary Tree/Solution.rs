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
use std::collections::VecDeque;
impl Solution {
    pub fn max_level_sum(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        let mut q = VecDeque::new();
        if let Some(r) = root {
            q.push_back(r);
        }

        let mut i = 0;
        let mut mx = i32::MIN;
        let mut ans = 0;

        while !q.is_empty() {
            i += 1;
            let mut s = 0;
            let sz = q.len();

            for _ in 0..sz {
                let node = q.pop_front().unwrap();
                let node = node.borrow();

                s += node.val;

                if let Some(left) = node.left.clone() {
                    q.push_back(left);
                }
                if let Some(right) = node.right.clone() {
                    q.push_back(right);
                }
            }

            if s > mx {
                mx = s;
                ans = i;
            }
        }

        ans
    }
}
