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
    pub fn delete_middle(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let mut slow = 0;
        let mut fast = head.as_ref();

        while let Some(node) = fast {
            if node.next.is_none() {
                break;
            }
            slow += 1;
            fast = node.next.as_ref().unwrap().next.as_ref();
        }

        let mut dummy = Some(Box::new(ListNode { val: 0, next: head }));
        let mut cur = dummy.as_mut();

        for _ in 0..slow {
            cur = cur.unwrap().next.as_mut();
        }

        let node = cur.unwrap();
        node.next = node.next.as_mut().unwrap().next.take();

        dummy.unwrap().next
    }
}
