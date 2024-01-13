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
    pub fn merge_two_lists(
        mut l1: Option<Box<ListNode>>,
        mut l2: Option<Box<ListNode>>
    ) -> Option<Box<ListNode>> {
        match (l1.is_some(), l2.is_some()) {
            (false, false) => None,
            (true, false) => l1,
            (false, true) => l2,
            (true, true) => {
                let mut dummy = Box::new(ListNode::new(0));
                let mut cur = &mut dummy;
                while l1.is_some() && l2.is_some() {
                    cur.next = if l1.as_ref().unwrap().val < l2.as_ref().unwrap().val {
                        let mut res = l1.take();
                        l1 = res.as_mut().unwrap().next.take();
                        res
                    } else {
                        let mut res = l2.take();
                        l2 = res.as_mut().unwrap().next.take();
                        res
                    };
                    cur = cur.next.as_mut().unwrap();
                }
                cur.next = if l1.is_some() { l1.take() } else { l2.take() };
                dummy.next.take()
            }
        }
    }
}
