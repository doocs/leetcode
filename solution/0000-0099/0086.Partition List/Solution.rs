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
    pub fn partition(head: Option<Box<ListNode>>, x: i32) -> Option<Box<ListNode>> {
        let mut head = head;
        let mut d1 = Some(Box::new(ListNode::new(0)));
        let mut d2 = Some(Box::new(ListNode::new(0)));
        let (mut t1, mut t2) = (&mut d1, &mut d2);
        while let Some(mut node) = head {
            head = node.next.take();
            if node.val < x {
                t1.as_mut().unwrap().next = Some(node);
                t1 = &mut t1.as_mut().unwrap().next;
            } else {
                t2.as_mut().unwrap().next = Some(node);
                t2 = &mut t2.as_mut().unwrap().next;
            }
        }
        t1.as_mut().unwrap().next = d2.unwrap().next;
        d1.unwrap().next
    }
}
