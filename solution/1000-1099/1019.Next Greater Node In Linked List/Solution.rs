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
use std::collections::VecDeque;
impl Solution {
    pub fn next_larger_nodes(head: Option<Box<ListNode>>) -> Vec<i32> {
        let mut nums = Vec::new();
        let mut current = &head;
        while let Some(node) = current {
            nums.push(node.val);
            current = &node.next;
        }

        let mut stk = VecDeque::new();
        let n = nums.len();
        let mut ans = vec![0; n];
        for i in (0..n).rev() {
            while !stk.is_empty() && stk.back().copied().unwrap() <= nums[i] {
                stk.pop_back();
            }
            if let Some(&top) = stk.back() {
                ans[i] = top;
            }
            stk.push_back(nums[i]);
        }
        ans
    }
}
