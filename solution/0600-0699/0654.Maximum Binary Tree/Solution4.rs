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
    pub fn construct_maximum_binary_tree(nums: Vec<i32>) -> Option<Rc<RefCell<TreeNode>>> {
        let n = nums.len();
        let mut root: Option<Rc<RefCell<TreeNode>>> = None;
        let mut stk: Vec<(usize, usize, Option<Rc<RefCell<TreeNode>>>, u8)> = vec![(0, n, None, 0)];
        while let Some((l, r, parent, side)) = stk.pop() {
            if l >= r {
                continue;
            }
            let mut idx = l;
            for i in l + 1..r {
                if nums[i] > nums[idx] {
                    idx = i;
                }
            }
            let node = Rc::new(RefCell::new(TreeNode {
                val: nums[idx],
                left: None,
                right: None,
            }));
            if let Some(p) = parent {
                if side == 0 {
                    p.borrow_mut().left = Some(Rc::clone(&node));
                } else {
                    p.borrow_mut().right = Some(Rc::clone(&node));
                }
            } else {
                root = Some(Rc::clone(&node));
            }
            stk.push((idx + 1, r, Some(Rc::clone(&node)), 1));
            stk.push((l, idx, Some(Rc::clone(&node)), 0));
        }
        root
    }
}
