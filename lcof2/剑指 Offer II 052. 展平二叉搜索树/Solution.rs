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
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, vals: &mut Vec<i32>) {
        if root.is_none() {
            return;
        }
        let node = root.as_ref().unwrap().borrow();
        Self::dfs(&node.left, vals);
        vals.push(node.val);
        Self::dfs(&node.right, vals);
    }

    pub fn increasing_bst(root: Option<Rc<RefCell<TreeNode>>>) -> Option<Rc<RefCell<TreeNode>>> {
        let mut vals = Vec::new();
        Self::dfs(&root, &mut vals);
        let mut dummy = Rc::new(RefCell::new(TreeNode::new(0)));
        for &val in vals.iter().rev() {
            let mut dummy = dummy.as_ref().borrow_mut();
            dummy.right = Some(Rc::new(RefCell::new(TreeNode {
                val,
                left: None,
                right: dummy.right.take(),
            })));
        }
        let ans = dummy.as_ref().borrow_mut().right.take();
        ans
    }
}
