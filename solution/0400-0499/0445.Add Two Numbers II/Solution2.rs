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
    fn create_stack(mut head: Option<Box<ListNode>>) -> Vec<i32> {
        let mut res = vec![];
        while let Some(node) = head {
            res.push(node.val);
            head = node.next;
        }
        res
    }

    pub fn add_two_numbers(
        l1: Option<Box<ListNode>>,
        l2: Option<Box<ListNode>>
    ) -> Option<Box<ListNode>> {
        let mut s1 = Self::create_stack(l1);
        let mut s2 = Self::create_stack(l2);

        let mut dummy = Box::new(ListNode::new(0));
        let mut carry = 0;
        while !s1.is_empty() || !s2.is_empty() || carry != 0 {
            if let Some(val) = s1.pop() {
                carry += val;
            }
            if let Some(val) = s2.pop() {
                carry += val;
            }
            dummy.next = Some(
                Box::new(ListNode {
                    val: carry % 10,
                    next: dummy.next.take(),
                })
            );
            carry /= 10;
        }
        dummy.next.take()
    }
}
