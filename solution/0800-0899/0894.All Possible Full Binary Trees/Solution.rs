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

impl TreeNode {
    pub fn new_with_node(
        left: Option<Rc<RefCell<TreeNode>>>,
        right: Option<Rc<RefCell<TreeNode>>>
    ) -> Self {
        Self {
            val: 0,
            left,
            right,
        }
    }
}

use std::rc::Rc;
use std::cell::RefCell;
impl Solution {
    #[allow(dead_code)]
    pub fn all_possible_fbt(n: i32) -> Vec<Option<Rc<RefCell<TreeNode>>>> {
        let mut record_vec = vec![vec![]; n as usize + 1];
        Self::dfs(n, &mut record_vec)
    }

    #[allow(dead_code)]
    fn dfs(
        n: i32,
        record_vec: &mut Vec<Vec<Option<Rc<RefCell<TreeNode>>>>>
    ) -> Vec<Option<Rc<RefCell<TreeNode>>>> {
        if record_vec[n as usize].len() != 0 {
            return record_vec[n as usize].clone();
        }
        if n == 1 {
            // Just directly return a single node
            return vec![Some(Rc::new(RefCell::new(TreeNode::new(0))))];
        }
        // Otherwise, need to construct return vector
        let mut ret_vec = Vec::new();

        // Enumerate the node number for left subtree from 0 -> n - 1
        for i in 0..n - 1 {
            // The number of right subtree node
            let j = n - i - 1;
            for left in Self::dfs(i, record_vec) {
                for right in Self::dfs(j, record_vec) {
                    // Construct the ret vector
                    ret_vec.push(
                        Some(
                            Rc::new(
                                RefCell::new(TreeNode::new_with_node(left.clone(), right.clone()))
                            )
                        )
                    );
                }
            }
        }

        record_vec[n as usize] = ret_vec;

        record_vec[n as usize].clone()
    }
}
