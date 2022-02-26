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
    pub fn reverse_list(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        match head {
            None => None,
            Some(mut head) => {
                let mut cur = head.next.take();
                let mut pre = Some(head);
                while let Some(mut node) = cur {
                    let next = node.next.take();
                    node.next = pre;
                    pre = Some(node);
                    cur = next;
                }
                pre
            }
        }
    }
}
