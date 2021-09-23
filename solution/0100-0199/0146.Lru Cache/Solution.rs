use std::cell::RefCell;
use std::collections::hash_map::HashMap;
use std::rc::Rc;

struct Node {
    key: i32,
    value: i32,
    prev: Option<Rc<RefCell<Node>>>,
    next: Option<Rc<RefCell<Node>>>,
}

impl Node {
    #[inline]
    fn new(key: i32, value: i32) -> Node {
        Node {
            key,
            value,
            prev: None,
            next: None,
        }
    }
}

struct LRUCache {
    capacity: usize,
    cache: HashMap<i32, Rc<RefCell<Node>>>,
    head: Option<Rc<RefCell<Node>>>,
    tail: Option<Rc<RefCell<Node>>>,
}

impl LRUCache {
    fn new(capacity: i32) -> Self {
        LRUCache {
            capacity: capacity as usize,
            cache: HashMap::new(),
            head: None,
            tail: None,
        }
    }

    fn get(&mut self, key: i32) -> i32 {
        if let Some(node) = self.cache.get(&key) {
            let node = Rc::clone(node);
            self.remove(&node);
            self.push_front(&node);
            let value = node.borrow().value;
            value
        } else {
            -1
        }
    }

    fn put(&mut self, key: i32, value: i32) {
        if let Some(node) = self.cache.get(&key) {
            let node = Rc::clone(node);
            node.borrow_mut().value = value;
            self.remove(&node);
            self.push_front(&node);
        } else {
            let node = Rc::new(RefCell::new(Node::new(key, value)));
            self.cache.insert(key, Rc::clone(&node));
            self.push_front(&node);
            if self.cache.len() > self.capacity {
                if let Some(back) = self.pop_back() {
                    self.cache.remove(&back.borrow().key);
                }
            }
        }
    }

    fn push_front(&mut self, node: &Rc<RefCell<Node>>) {
        let mut node_borrow_mut = node.borrow_mut();
        if let Some(head) = self.head.take() {
            head.borrow_mut().prev = Some(Rc::clone(node));
            node_borrow_mut.next = Some(head);
            node_borrow_mut.prev = None;
            self.head = Some(Rc::clone(node));
        } else {
            self.head = Some(Rc::clone(node));
            self.tail = Some(Rc::clone(node));
        }
    }

    fn remove(&mut self, node: &Rc<RefCell<Node>>) {
        match (node.borrow().prev.as_ref(), node.borrow().next.as_ref()) {
            (None, None) => {
                self.head = None;
                self.tail = None;
            }
            (None, Some(next)) => {
                self.head = Some(Rc::clone(next));
                next.borrow_mut().prev = None;
            }
            (Some(prev), None) => {
                self.tail = Some(Rc::clone(prev));
                prev.borrow_mut().next = None;
            }
            (Some(prev), Some(next)) => {
                next.borrow_mut().prev = Some(Rc::clone(prev));
                prev.borrow_mut().next = Some(Rc::clone(next));
            }
        }
    }

    fn pop_back(&mut self) -> Option<Rc<RefCell<Node>>> {
        if let Some(tail) = self.tail.take() {
            match tail.borrow().prev.as_ref() {
                Some(prev) => {
                    prev.borrow_mut().next = None;
                    self.tail = Some(Rc::clone(prev));
                }
                None => {
                    self.head = None;
                    self.tail = None;
                }
            }
            Some(tail)
        } else {
            None
        }
    }
}
