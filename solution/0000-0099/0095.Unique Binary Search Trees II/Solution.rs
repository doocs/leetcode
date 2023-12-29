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
    pub fn generate_trees(n: i32) -> Vec<Option<Rc<RefCell<TreeNode>>>> {
        Self::dfs(1, n)
    }

    fn dfs(i: i32, j: i32) -> Vec<Option<Rc<RefCell<TreeNode>>>> {
        let mut ans = Vec::new();
        if i > j {
            ans.push(None);
            return ans;
        }
        for v in i..=j {
            let left = Self::dfs(i, v - 1);
            let right = Self::dfs(v + 1, j);
            for l in &left {
                for r in &right {
                    ans.push(
                        Some(
                            Rc::new(
                                RefCell::new(TreeNode {
                                    val: v,
                                    left: l.clone(),
                                    right: r.clone(),
                                })
                            )
                        )
                    );
                }
            }
        }
        ans
    }
}
