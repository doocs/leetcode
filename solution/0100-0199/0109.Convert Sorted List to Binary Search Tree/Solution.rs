// Definition for singly-linked list.
// #[derive(PartialEq, Eq, Clone, Debug)]
// pub struct ListNode {
//   pub val: i32,
//   pub next: Option<Box<ListNode>>
// }
//
// impl ListNode {
//   #[inline]
//   fn new(val: i32) -> Self {
//     ListNode {
//       next: None,
//       val
//     }
//   }
// }
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
    fn build(vals: &Vec<i32>, start: usize, end: usize) -> Option<Rc<RefCell<TreeNode>>> {
        if (start == end) {
            return None;
        }
        let mid = (start + end) >> 1;
        Some(Rc::new(RefCell::new(TreeNode {
            val: vals[mid],
            left: Self::build(vals, start, mid),
            right: Self::build(vals, mid + 1, end),
        })))
    }

    pub fn sorted_list_to_bst(head: Option<Box<ListNode>>) -> Option<Rc<RefCell<TreeNode>>> {
        let mut vals = Vec::new();
        let mut cur = &head;
        while let Some(node) = cur {
            vals.push(node.val);
            cur = &node.next;
        }
        Self::build(&vals, 0, vals.len())
    }
}
