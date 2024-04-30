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
    pub fn build_tree(inorder: Vec<i32>, postorder: Vec<i32>) -> Option<Rc<RefCell<TreeNode>>> {
        let n = inorder.len();
        let mut d: HashMap<i32, usize> = HashMap::new();
        for i in 0..n {
            d.insert(inorder[i], i);
        }
        fn dfs(
            postorder: &[i32],
            d: &HashMap<i32, usize>,
            i: usize,
            j: usize,
            n: usize
        ) -> Option<Rc<RefCell<TreeNode>>> {
            if n <= 0 {
                return None;
            }
            let val = postorder[j + n - 1];
            let k = *d.get(&val).unwrap();
            let left = dfs(postorder, d, i, j, k - i);
            let right = dfs(postorder, d, k + 1, j + k - i, n - 1 - (k - i));
            Some(Rc::new(RefCell::new(TreeNode { val, left, right })))
        }
        dfs(&postorder, &d, 0, 0, n)
    }
}
