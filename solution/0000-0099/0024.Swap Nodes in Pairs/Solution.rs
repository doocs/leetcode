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
    pub fn swap_pairs(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let mut dummy = Some(Box::new(ListNode { val: 0, next: head }));
        let mut cur = dummy.as_mut().unwrap();
        while cur.next.is_some() && cur.next.as_ref().unwrap().next.is_some() {
            cur.next = {
                let mut b = cur.next.as_mut().unwrap().next.take();
                cur.next.as_mut().unwrap().next = b.as_mut().unwrap().next.take();
                let a = cur.next.take();
                b.as_mut().unwrap().next = a;
                b
            };
            cur = cur.next.as_mut().unwrap().next.as_mut().unwrap();
        }
        dummy.unwrap().next
    }
}
