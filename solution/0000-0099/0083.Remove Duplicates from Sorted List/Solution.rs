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
    pub fn delete_duplicates(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let mut dummy = Some(Box::new(ListNode::new(i32::MAX)));
        let mut p = &mut dummy;

        let mut current = head;
        while let Some(mut node) = current {
            current = node.next.take();
            if p.as_mut().unwrap().val != node.val {
                p.as_mut().unwrap().next = Some(node);
                p = &mut p.as_mut().unwrap().next;
            }
        }
        dummy.unwrap().next
    }
}
