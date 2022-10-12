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
use std::collections::HashSet;
impl Solution {
    pub fn num_components(head: Option<Box<ListNode>>, nums: Vec<i32>) -> i32 {
        let set = nums.into_iter().collect::<HashSet<i32>>();
        let mut res = 0;
        let mut in_set = false;
        let mut cur = &head;
        while let Some(node) = cur {
            if set.contains(&node.val) {
                if !in_set {
                    in_set = true;
                    res += 1;
                }
            } else {
                in_set = false;
            }
            cur = &node.next;
        }
        res
    }
}
