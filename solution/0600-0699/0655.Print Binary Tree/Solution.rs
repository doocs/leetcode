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
    fn get_height(root: &Option<Rc<RefCell<TreeNode>>>, h: u32) -> u32 {
        if let Some(node) = root {
            let node = node.borrow();
            return Self::get_height(&node.left, h + 1).max(Self::get_height(&node.right, h + 1));
        }
        h - 1
    }

    fn dfs(
        root: &Option<Rc<RefCell<TreeNode>>>,
        i: usize,
        j: usize,
        res: &mut Vec<Vec<String>>,
        height: u32,
    ) {
        if root.is_none() {
            return;
        }
        let node = root.as_ref().unwrap().borrow();
        res[i][j] = node.val.to_string();
        Self::dfs(
            &node.left,
            i + 1,
            j - 2usize.pow(height - (i as u32) - 1),
            res,
            height,
        );
        Self::dfs(
            &node.right,
            i + 1,
            j + 2usize.pow(height - (i as u32) - 1),
            res,
            height,
        );
    }

    pub fn print_tree(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<Vec<String>> {
        let height = Self::get_height(&root, 0);
        let m = (height + 1) as usize;
        let n = 2usize.pow(height + 1) - 1;
        let mut res = vec![vec![String::new(); n]; m];
        Self::dfs(&root, 0, (n - 1) >> 1, &mut res, height);
        res
    }
}
