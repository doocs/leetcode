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
    pub fn merge_nodes(mut head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let mut dummy = Box::new(ListNode::new(-1));
        let mut cur = &mut dummy;
        let mut sum = 0;
        while let Some(node) = head {
            if node.val == 0 && sum != 0 {
                cur.next = Some(Box::new(ListNode::new(sum)));
                cur = cur.as_mut().next.as_mut().unwrap();
                sum = 0;
            }
            sum += node.val;
            head = node.next;
        }
        dummy.next.take()
    }
}
