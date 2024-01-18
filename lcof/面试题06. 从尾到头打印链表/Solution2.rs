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
    pub fn reverse_print(head: Option<Box<ListNode>>) -> Vec<i32> {
        let mut cur = &head;
        let mut n = 0;
        while let Some(node) = cur {
            cur = &node.next;
            n += 1;
        }

        let mut arr = vec![0; n];
        let mut cur = head;
        while let Some(node) = cur {
            n -= 1;
            arr[n] = node.val;
            cur = node.next;
        }
        arr
    }
}
