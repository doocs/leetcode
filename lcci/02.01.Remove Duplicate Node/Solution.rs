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
    pub fn remove_duplicate_nodes(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        match head {
            None => head,
            Some(mut head) => {
                let mut set = HashSet::new();
                set.insert(head.val);
                let mut pre = &mut head;
                while let Some(cur) = &pre.next {
                    if set.contains(&cur.val) {
                        pre.next = pre.next.take().unwrap().next;
                    } else {
                        set.insert(cur.val);
                        pre = pre.next.as_mut().unwrap();
                    }
                }
                Some(head)
            }
        }
    }
}
