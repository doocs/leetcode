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
    pub fn merge_k_lists(mut lists: Vec<Option<Box<ListNode>>>) -> Option<Box<ListNode>> {
        let n = lists.len();
        Self::dfs(&mut lists, 0, n)
    }

    fn dfs(
        lists: &mut Vec<Option<Box<ListNode>>>,
        start: usize,
        end: usize,
    ) -> Option<Box<ListNode>> {
        if end - start <= 1 {
            if lists.get(start).is_some() {
                return lists[start].take();
            }
            return None;
        }
        let mid = start + (end - start) / 2;
        let mut left = Self::dfs(lists, start, mid);
        let mut right = Self::dfs(lists, mid, end);
        let mut dummy = Box::new(ListNode::new(0));
        let mut cur = &mut dummy;
        while left.is_some() || right.is_some() {
            let mut next = None;
            if left.is_some()
                && (right.is_none() || left.as_ref().unwrap().val < right.as_ref().unwrap().val)
            {
                let t = left.as_mut().unwrap().next.take();
                next = left.take();
                left = t;
            } else {
                let t = right.as_mut().unwrap().next.take();
                next = right.take();
                right = t;
            }
            cur.next = next;
            cur = cur.next.as_mut().unwrap();
        }
        dummy.next
    }
}
