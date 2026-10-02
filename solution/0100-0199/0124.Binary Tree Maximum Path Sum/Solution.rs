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
use std::collections::HashMap;
use std::rc::Rc;
impl Solution {
    pub fn max_path_sum(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        let mut res = -1001;
        let Some(root) = root else {
            return res;
        };
        let mut stack = vec![(root, false)];
        let mut gains: HashMap<*const RefCell<TreeNode>, i32> = HashMap::new();
        while let Some((node, visited)) = stack.pop() {
            if !visited {
                stack.push((node.clone(), true));
                let node_ref = node.borrow();
                if let Some(right) = node_ref.right.as_ref() {
                    stack.push((right.clone(), false));
                }
                if let Some(left) = node_ref.left.as_ref() {
                    stack.push((left.clone(), false));
                }
                continue;
            }
            let node_ref = node.borrow();
            let left = node_ref
                .left
                .as_ref()
                .map_or(0, |child| 0.max(*gains.get(&Rc::as_ptr(child)).unwrap()));
            let right = node_ref
                .right
                .as_ref()
                .map_or(0, |child| 0.max(*gains.get(&Rc::as_ptr(child)).unwrap()));
            res = res.max(node_ref.val + left + right);
            gains.insert(Rc::as_ptr(&node), node_ref.val + left.max(right));
        }
        res
    }
}
