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
    fn dfs(
        root: &Option<Rc<RefCell<TreeNode>>>,
        map: &mut HashMap<String, i32>,
        res: &mut Vec<Option<Rc<RefCell<TreeNode>>>>,
    ) -> String {
        if root.is_none() {
            return String::from('#');
        }

        let s = {
            let root = root.as_ref().unwrap().as_ref().borrow();
            format!(
                "{},{},{}",
                root.val.to_string(),
                Self::dfs(&root.left, map, res),
                Self::dfs(&root.right, map, res)
            )
        };
        *map.entry(s.clone()).or_insert(0) += 1;
        if *map.get(&s).unwrap() == 2 {
            res.push(root.clone());
        }
        return s;
    }

    pub fn find_duplicate_subtrees(
        root: Option<Rc<RefCell<TreeNode>>>,
    ) -> Vec<Option<Rc<RefCell<TreeNode>>>> {
        let mut map = HashMap::new();
        let mut res = Vec::new();
        Self::dfs(&root, &mut map, &mut res);
        res
    }
}
