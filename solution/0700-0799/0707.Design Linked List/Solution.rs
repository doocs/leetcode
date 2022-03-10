struct Node {
    val: i32,
    next: Option<Box<Node>>,
}

#[derive(Default)]
struct MyLinkedList {
    head: Option<Box<Node>>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl MyLinkedList {
    fn new() -> Self {
        Default::default()
    }

    fn get(&self, index: i32) -> i32 {
        let mut cur = match self.head {
            None => return -1,
            Some(ref n) => n,
        };
        let mut idx_cur = 0;
        while idx_cur < index {
            match cur.next {
                None => return -1,
                Some(ref next) => {
                    cur = next;
                    idx_cur += 1;
                }
            }
        }
        cur.val
    }

    fn add_at_head(&mut self, val: i32) {
        self.head = Some(Box::new(Node {
            val,
            next: self.head.take(),
        }));
    }

    fn add_at_tail(&mut self, val: i32) {
        let new_node = Some(Box::new(Node { val, next: None }));
        let mut cur = match self.head {
            Some(ref mut n) => n,
            None => {
                self.head = new_node;
                return;
            }
        };
        while let Some(ref mut next) = cur.next {
            cur = next;
        }
        cur.next = new_node;
    }

    fn add_at_index(&mut self, index: i32, val: i32) {
        let mut dummy = Box::new(Node {
            val: 0,
            next: self.head.take()
        });
        let mut idx = 0;
        let mut cur = &mut dummy;
        while idx < index {
            if let Some(ref mut next) = cur.next {
                cur = next;
            } else {
                return
            }
            idx += 1;
        }
        cur.next = Some(Box::new(Node { 
            val, 
            next: cur.next.take() 
        }));
        self.head = dummy.next;
    }

    fn delete_at_index(&mut self, index: i32) {
        let mut dummy = Box::new(Node {
            val: 0,
            next: self.head.take(),
        });
        let mut idx = 0;
        let mut cur = &mut dummy;
        while idx < index {
            if let Some(ref mut next) = cur.next {
                cur = next;
            }
            idx += 1;
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