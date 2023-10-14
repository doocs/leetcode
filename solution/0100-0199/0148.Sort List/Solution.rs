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
impl Solution {
    pub fn sort_list(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        fn merge(l1: Option<Box<ListNode>>, l2: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
            match (l1, l2) {
                (None, Some(node)) | (Some(node), None) => Some(node),
                (Some(mut node1), Some(mut node2)) => {
                    if node1.val < node2.val {
                        node1.next = merge(node1.next.take(), Some(node2));
                        Some(node1)
                    } else {
                        node2.next = merge(Some(node1), node2.next.take());
                        Some(node2)
                    }
                }
                _ => None,
            }
        }

        fn sort(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
            if head.is_none() || head.as_ref().unwrap().next.is_none() {
                return head;
            }
            let mut head = head;
            let mut length = 0;
            let mut cur = &head;
            while cur.is_some() {
                length += 1;
                cur = &cur.as_ref().unwrap().next;
            }
            let mut cur = &mut head;
            for _ in 0..(length / 2 - 1) {
                cur = &mut cur.as_mut().unwrap().next;
            }
            let right = cur.as_mut().unwrap().next.take();

            merge(sort(head), sort(right))
        }
        sort(head)
    }
}
