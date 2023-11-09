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
    pub fn generate_trees(n: i32) -> Vec<Option<Rc<RefCell<TreeNode>>>> {
        Self::generate_trees_inner(1, n)
    }

    #[allow(dead_code)]
    fn generate_trees_inner(left: i32, right: i32) -> Vec<Option<Rc<RefCell<TreeNode>>>> {
        let mut ret = Vec::new();

        if left > right {
            // If there is no possible BST matching
            // Then this should be consider a nullptr
            ret.push(None);
        } else {
            // Otherwise, let's generate the BST
            for i in left..=right {
                // First get the two vectors containing the possible left trees & right trees
                let left_trees = Self::generate_trees_inner(left, i - 1);
                let right_trees = Self::generate_trees_inner(i + 1, right);

                // Then construct the final results
                for left_tree in &left_trees {
                    for right_tree in &right_trees {
                        // Construct the current node
                        let mut node = Some(Rc::new(RefCell::new(TreeNode::new(i))));
                        // Set the connection
                        node.as_ref().unwrap().borrow_mut().left = left_tree.clone();
                        node.as_ref().unwrap().borrow_mut().right = right_tree.clone();
                        // Update the result vector
                        ret.push(node);
                    }
                }
            }
        }

        ret
    }
}
