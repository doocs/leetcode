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
    pub fn remove_duplicate_nodes(mut head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let mut vis = HashSet::new();
        let mut pre = ListNode::new(0);
        pre.next = head;
        let mut cur = &mut pre;
        while let Some(node) = cur.next.take() {
            if vis.contains(&node.val) {
                cur.next = node.next;
            } else {
                vis.insert(node.val);
                cur.next = Some(node);
                cur = cur.next.as_mut().unwrap();
            }
        }
        pre.next
    }
}
