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
    pub fn pair_sum(head: Option<Box<ListNode>>) -> i32 {
        let mut arr = Vec::new();
        let mut node = &head;
        while node.is_some() {
            let t = node.as_ref().unwrap();
            arr.push(t.val);
            node = &t.next;
        }
        let n = arr.len();
        let mut ans = 0;
        for i in 0..n >> 1 {
            ans = ans.max(arr[i] + arr[n - 1 - i]);
        }
        ans
    }
}
