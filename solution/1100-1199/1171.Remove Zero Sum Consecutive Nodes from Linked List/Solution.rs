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
    pub fn remove_zero_sum_sublists(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let dummy = Some(Box::new(ListNode { val: 0, next: head }));
        let mut last = std::collections::HashMap::new();
        let mut s = 0;
        let mut p = dummy.as_ref();
        while let Some(node) = p {
            s += node.val;
            last.insert(s, node);
            p = node.next.as_ref();
        }

        let mut dummy = Some(Box::new(ListNode::new(0)));
        let mut q = dummy.as_mut();
        s = 0;
        while let Some(cur) = q {
            s += cur.val;
            if let Some(node) = last.get(&s) {
                cur.next = node.next.clone();
            }
            q = cur.next.as_mut();
        }
        dummy.unwrap().next
    }
}
