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
struct Item {
    index: usize,
    val: i32,
}

impl Solution {
    pub fn next_larger_nodes(head: Option<Box<ListNode>>) -> Vec<i32> {
        let mut res = Vec::new();
        let mut stack: Vec<Item> = Vec::new();
        let mut cur = &head;
        for i in 0..usize::MAX {
            if cur.is_none() {
                break;
            }
            res.push(0);
            let node = cur.as_ref().unwrap();
            while !stack.is_empty() && stack.last().unwrap().val < node.val {
                res[stack.pop().unwrap().index] = node.val;
            }
            stack.push(Item {
                index: i,
                val: node.val,
            });
            cur = &node.next;
        }
        res
    }
}
