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

use std::collections::HashSet;

impl Solution {
    pub fn modified_list(nums: Vec<i32>, head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let s: HashSet<i32> = nums.into_iter().collect();
        let mut dummy = Box::new(ListNode { val: 0, next: head });
        let mut pre = &mut dummy;

        while let Some(ref mut node) = pre.next {
            if s.contains(&node.val) {
                pre.next = node.next.take();
            } else {
                pre = pre.next.as_mut().unwrap();
            }
        }

        dummy.next
    }
}
