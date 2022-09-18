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
use std::collections::VecDeque;
impl Solution {
    fn create_tree(vals: &Vec<Vec<i32>>, i: usize, j: usize) -> Option<Rc<RefCell<TreeNode>>> {
        if i == vals.len() {
            return None;
        }
        Some(Rc::new(RefCell::new(TreeNode {
            val: vals[i][j],
            left: Self::create_tree(vals, i + 1, j * 2),
            right: Self::create_tree(vals, i + 1, j * 2 + 1),
        })))
    }

    pub fn reverse_odd_levels(
        root: Option<Rc<RefCell<TreeNode>>>,
    ) -> Option<Rc<RefCell<TreeNode>>> {
        let mut queue = VecDeque::new();
        queue.push_back(root);
        let mut d = 0;
        let mut vals = Vec::new();
        while !queue.is_empty() {
            let mut val = Vec::new();
            for _ in 0..queue.len() {
                let mut node = queue.pop_front().unwrap();
                let mut node = node.as_mut().unwrap().borrow_mut();
                val.push(node.val);
                if node.left.is_some() {
                    queue.push_back(node.left.take());
                }
                if node.right.is_some() {
                    queue.push_back(node.right.take());
                }
            }
            if d % 2 == 1 {
                val.reverse();
            }
            vals.push(val);
            d += 1;
        }
        Self::create_tree(&vals, 0, 0)
    }
}
