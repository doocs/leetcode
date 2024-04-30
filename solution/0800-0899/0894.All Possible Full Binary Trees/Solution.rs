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
    pub fn all_possible_fbt(n: i32) -> Vec<Option<Rc<RefCell<TreeNode>>>> {
        let mut f: Vec<Option<Vec<Option<Rc<RefCell<TreeNode>>>>>> = vec![None; (n + 1) as usize];
        Self::dfs(n, &mut f)
    }

    fn dfs(
        n: i32,
        f: &mut Vec<Option<Vec<Option<Rc<RefCell<TreeNode>>>>>>
    ) -> Vec<Option<Rc<RefCell<TreeNode>>>> {
        if let Some(ref result) = f[n as usize] {
            return result.clone();
        }

        let mut ans = Vec::new();
        if n == 1 {
            ans.push(Some(Rc::new(RefCell::new(TreeNode::new(0)))));
            return ans;
        }

        for i in 0..n - 1 {
            let j = n - 1 - i;
            for left in Self::dfs(i, f).iter() {
                for right in Self::dfs(j, f).iter() {
                    let new_node = Some(
                        Rc::new(
                            RefCell::new(TreeNode {
                                val: 0,
                                left: left.clone(),
                                right: right.clone(),
                            })
                        )
                    );
                    ans.push(new_node);
                }
            }
        }
        f[n as usize] = Some(ans.clone());
        ans
    }
}
