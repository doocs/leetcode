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
    pub fn find_frequent_tree_sum(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<i32> {
        let mut cnt: HashMap<i32, i32> = HashMap::new();
        let mut sub: HashMap<usize, i32> = HashMap::new();
        let mut stk = vec![(root, 0)];
        while let Some((node, state)) = stk.pop() {
            if let Some(node) = node {
                if state == 0 {
                    let (left, right) = {
                        let b = node.borrow();
                        (b.left.clone(), b.right.clone())
                    };
                    stk.push((Some(node), 1));
                    if right.is_some() {
                        stk.push((right, 0));
                    }
                    if left.is_some() {
                        stk.push((left, 0));
                    }
                } else {
                    let b = node.borrow();
                    let l = b
                        .left
                        .as_ref()
                        .map(|n| sub[&(Rc::as_ptr(n) as usize)])
                        .unwrap_or(0);
                    let r = b
                        .right
                        .as_ref()
                        .map(|n| sub[&(Rc::as_ptr(n) as usize)])
                        .unwrap_or(0);
                    let s = l + r + b.val;
                    *cnt.entry(s).or_insert(0) += 1;
                    drop(b);
                    sub.insert(Rc::as_ptr(&node) as usize, s);
                }
            }
        }
        let mx = cnt.values().cloned().max().unwrap_or(0);
        cnt.into_iter()
            .filter(|&(_, v)| v == mx)
            .map(|(k, _)| k)
            .collect()
    }
}
