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
    pub fn tree2str(root: Option<Rc<RefCell<TreeNode>>>) -> String {
        let mut res = String::new();
        let mut stk = vec![(root, 0)];
        while let Some((node, state)) = stk.pop() {
            if state == 0 {
                if let Some(node) = node {
                    let (val, left, leaf) = {
                        let b = node.borrow();
                        (b.val, b.left.clone(), b.left.is_none() && b.right.is_none())
                    };
                    res.push_str(&val.to_string());
                    if leaf {
                        continue;
                    }
                    res.push('(');
                    stk.push((Some(node), 1));
                    if left.is_some() {
                        stk.push((left, 0));
                    }
                }
            } else if state == 1 {
                if let Some(node) = node {
                    res.push(')');
                    let right = node.borrow().right.clone();
                    if right.is_some() {
                        res.push('(');
                        stk.push((Some(node), 2));
                        stk.push((right, 0));
                    }
                }
            } else {
                res.push(')');
            }
        }
        res
    }
}
