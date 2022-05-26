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
use std::collections::HashMap;
impl Solution {
    fn dfs(val: i32, map: &HashMap<i32, [i32; 2]>) -> Option<Rc<RefCell<TreeNode>>> {
        if val == 0 {
            return None;
        }
        let mut left = None;
        let mut right = None;
        if let Some(&[l_val, r_val]) = map.get(&val) {
            left = Self::dfs(l_val, map);
            right = Self::dfs(r_val, map);
        }
        Some(Rc::new(RefCell::new(TreeNode { val, left, right })))
    }

    pub fn create_binary_tree(descriptions: Vec<Vec<i32>>) -> Option<Rc<RefCell<TreeNode>>> {
        let mut map = HashMap::new();
        let mut is_root = HashMap::new();
        for description in descriptions.iter() {
            let (parent, child, is_left) = (description[0], description[1], description[2] == 1);
            let [mut left, mut right] = map.get(&parent).unwrap_or(&[0, 0]);
            if is_left {
                left = child;
            } else {
                right = child;
            }
            if !is_root.contains_key(&parent) {
                is_root.insert(parent, true);
            }
            is_root.insert(child, false);
            map.insert(parent, [left, right]);
        }
        for key in is_root.keys() {
            if *is_root.get(key).unwrap() {
                return Self::dfs(*key, &map);
            }
        }
        None
    }
}
