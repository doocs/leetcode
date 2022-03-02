impl Solution {
    pub fn add_two_numbers(
        mut l1: Option<Box<ListNode>>,
        mut l2: Option<Box<ListNode>>,
    ) -> Option<Box<ListNode>> {
        let mut dummy = Some(Box::new(ListNode::new(0)));
        let mut cur = dummy.as_mut();
        while l1.is_some() || l2.is_some() {
            let mut val = 0;
            if let Some(node) = l1 {
                val += node.val;
                l1 = node.next;
            }
            if let Some(node) = l2 {
                val += node.val;
                l2 = node.next;
            }
            if let Some(node) = cur {
                if node.val >= 10 {
                    val += 1;
                    node.val %= 10;
                }
                node.next = Some(Box::new(ListNode::new(val)));
                cur = node.next.as_mut();
            }
        }
        if let Some(node) = cur {
            if node.val >= 10 {
                node.val %= 10;
                node.next = Some(Box::new(ListNode::new(1)));
            }
        }
        dummy.unwrap().next
    }
}
