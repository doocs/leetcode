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
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>) -> i32 {
        let node = root.as_ref().unwrap().borrow();
        if node.left.is_none() {
            return node.val;
        }
        Self::dfs(&node.left)
    }

    pub fn delete_node(
        mut root: Option<Rc<RefCell<TreeNode>>>,
        key: i32,
    ) -> Option<Rc<RefCell<TreeNode>>> {
        if root.is_some() {
            let mut node = root.as_mut().unwrap().borrow_mut();
            match node.val.cmp(&key) {
                std::cmp::Ordering::Less => {
                    node.right = Self::delete_node(node.right.take(), key);
                }
                std::cmp::Ordering::Greater => {
                    node.left = Self::delete_node(node.left.take(), key);
                }
                std::cmp::Ordering::Equal => {
                    match (node.left.is_some(), node.right.is_some()) {
                        (false, false) => return None,
                        (true, false) => return node.left.take(),
                        (false, true) => return node.right.take(),
                        (true, true) => {
                            if node.right.as_ref().unwrap().borrow().left.is_none() {
                                let mut r = node.right.take();
                                r.as_mut().unwrap().borrow_mut().left = node.left.take();
                                return r;
                            } else {
                                let val = Self::dfs(&node.right);
                                node.val = val;
                                node.right = Self::delete_node(node.right.take(), val);
                            }
                        }
                    };
                }
            }
        }
        root
    }
}
