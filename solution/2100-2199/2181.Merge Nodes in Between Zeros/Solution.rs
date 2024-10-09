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
    pub fn merge_nodes(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let mut dummy = Box::new(ListNode::new(0));
        let mut tail = &mut dummy;
        let mut s = 0;
        let mut cur = head.unwrap().next;

        while let Some(mut node) = cur {
            if node.val != 0 {
                s += node.val;
            } else {
                tail.next = Some(Box::new(ListNode::new(s)));
                tail = tail.next.as_mut().unwrap();
                s = 0;
            }
            cur = node.next.take();
        }

        dummy.next
    }
}
