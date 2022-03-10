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
    pub fn delete_node(mut head: Option<Box<ListNode>>, val: i32) -> Option<Box<ListNode>> {
        let mut cur = &mut head;
        while let Some(node) = cur {
            if node.val == val {
                *cur = node.next.take();
                break;
            }
            cur = &mut cur.as_mut().unwrap().next;
        }
        head
    }
}