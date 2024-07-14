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
    pub fn leaf_similar(
        root1: Option<Rc<RefCell<TreeNode>>>,
        root2: Option<Rc<RefCell<TreeNode>>>,
    ) -> bool {
        let mut one_vec: Vec<i32> = Vec::new();
        let mut two_vec: Vec<i32> = Vec::new();

        // Initialize the two vector
        Self::traverse(&mut one_vec, root1);
        Self::traverse(&mut two_vec, root2);

        one_vec == two_vec
    }

    #[allow(dead_code)]
    fn traverse(v: &mut Vec<i32>, root: Option<Rc<RefCell<TreeNode>>>) {
        if root.is_none() {
            return;
        }
        if Self::is_leaf_node(&root) {
            v.push(root.as_ref().unwrap().borrow().val);
        }
        let left = root.as_ref().unwrap().borrow().left.clone();
        let right = root.as_ref().unwrap().borrow().right.clone();
        Self::traverse(v, left);
        Self::traverse(v, right);
    }

    #[allow(dead_code)]
    fn is_leaf_node(node: &Option<Rc<RefCell<TreeNode>>>) -> bool {
        node.as_ref().unwrap().borrow().left.is_none()
            && node.as_ref().unwrap().borrow().right.is_none()
    }
}
