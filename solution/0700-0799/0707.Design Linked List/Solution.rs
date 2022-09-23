#[derive(Default)]
struct MyLinkedList {
    head: Option<Box<ListNode>>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl MyLinkedList {
    fn new() -> Self {
        Default::default()
    }

    fn get(&self, mut index: i32) -> i32 {
        if self.head.is_none() {
            return -1;
        }
        let mut cur = self.head.as_ref().unwrap();
        while index > 0 {
            match cur.next {
                None => return -1,
                Some(ref next) => {
                    cur = next;
                    index -= 1;
                }
            }
        }
        cur.val
    }

    fn add_at_head(&mut self, val: i32) {
        self.head = Some(Box::new(ListNode {
            val,
            next: self.head.take(),
        }));
    }

    fn add_at_tail(&mut self, val: i32) {
        let new_node = Some(Box::new(ListNode { val, next: None }));
        if self.head.is_none() {
            self.head = new_node;
            return;
        }
        let mut cur = self.head.as_mut().unwrap();
        while let Some(ref mut next) = cur.next {
            cur = next;
        }
        cur.next = new_node;
    }

    fn add_at_index(&mut self, mut index: i32, val: i32) {
        let mut dummy = Box::new(ListNode {
            val: 0,
            next: self.head.take(),
        });
        let mut cur = &mut dummy;
        while index > 0 {
            if cur.next.is_none() {
                return;
            }
            cur = cur.next.as_mut().unwrap();
            index -= 1;
        }
        cur.next = Some(Box::new(ListNode {
            val,
            next: cur.next.take(),
        }));
        self.head = dummy.next;
    }

    fn delete_at_index(&mut self, mut index: i32) {
        let mut dummy = Box::new(ListNode {
            val: 0,
            next: self.head.take(),
        });
        let mut cur = &mut dummy;
        while index > 0 {
            if let Some(ref mut next) = cur.next {
                cur = next;
            }
            index -= 1;
        }
        cur.next = cur.next.take().and_then(|n| n.next);
        self.head = dummy.next;
    }
}


/**
 * Your MyLinkedList object will be instantiated and called as such:
 * let obj = MyLinkedList::new();
 * let ret_1: i32 = obj.get(index);
 * obj.add_at_head(val);
 * obj.add_at_tail(val);
 * obj.add_at_index(index, val);
 * obj.delete_at_index(index);
 */