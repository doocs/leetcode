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
    pub fn get_kth_from_end(head: Option<Box<ListNode>>, k: i32) -> Option<Box<ListNode>> {
        let mut fast = &head;
        for _ in 0..k {
            fast = &fast.as_ref().unwrap().next;
        }
        let mut slow = &head;
        while let (Some(nf), Some(ns)) = (fast, slow) {
            fast = &nf.next;
            slow = &ns.next;
        }
        slow.to_owned()
    }
}
