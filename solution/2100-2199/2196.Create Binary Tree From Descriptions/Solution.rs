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
use std::collections::{HashMap, HashSet};
use std::rc::Rc;
impl Solution {
    pub fn create_binary_tree(descriptions: Vec<Vec<i32>>) -> Option<Rc<RefCell<TreeNode>>> {
        let mut nodes = HashMap::new();
        let mut children = HashSet::new();

        for d in descriptions {
            let parent = d[0];
            let child = d[1];
            let is_left = d[2];

            nodes
                .entry(parent)
                .or_insert_with(|| Rc::new(RefCell::new(TreeNode::new(parent))));
            nodes
                .entry(child)
                .or_insert_with(|| Rc::new(RefCell::new(TreeNode::new(child))));

            if is_left == 1 {
                nodes.get(&parent).unwrap().borrow_mut().left =
                    Some(Rc::clone(nodes.get(&child).unwrap()));
            } else {
                nodes.get(&parent).unwrap().borrow_mut().right =
                    Some(Rc::clone(nodes.get(&child).unwrap()));
            }

            children.insert(child);
        }

        for (key, node) in &nodes {
            if !children.contains(key) {
                return Some(Rc::clone(node));
            }
        }

        None
    }
}
