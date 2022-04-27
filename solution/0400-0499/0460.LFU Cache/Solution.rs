use std::cell::RefCell;
use std::collections::HashMap;
use std::rc::Rc;

struct Node {
    key: i32,
    value: i32,
    freq: i32,
    prev: Option<Rc<RefCell<Node>>>,
    next: Option<Rc<RefCell<Node>>>,
}

impl Node {
    fn new(key: i32, value: i32) -> Self {
        Self {
            key,
            value,
            freq: 1,
            prev: None,
            next: None,
        }
    }
}

struct LinkedList {
    head: Option<Rc<RefCell<Node>>>,
    tail: Option<Rc<RefCell<Node>>>,
}

impl LinkedList {
    fn new() -> Self {
        Self {
            head: None,
            tail: None,
        }
    }

    fn push_front(&mut self, node: &Rc<RefCell<Node>>) {
        match self.head.take() {
            Some(head) => {
                head.borrow_mut().prev = Some(Rc::clone(node));
                node.borrow_mut().prev = None;
                node.borrow_mut().next = Some(head);
                self.head = Some(Rc::clone(node));
            }
            None => {
                node.borrow_mut().prev = None;
                node.borrow_mut().next = None;
                self.head = Some(Rc::clone(node));
                self.tail = Some(Rc::clone(node));
            }
        };
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
        };
    }

    fn pop_back(&mut self) -> Option<Rc<RefCell<Node>>> {
        match self.tail.take() {
            Some(tail) => {
                self.remove(&tail);
                Some(tail)
            }
            None => None,
        }
    }

    fn is_empty(&self) -> bool {
        self.head.is_none()
    }
}

struct LFUCache {
    cache: HashMap<i32, Rc<RefCell<Node>>>,
    freq_map: HashMap<i32, LinkedList>,
    min_freq: i32,
    capacity: usize,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl LFUCache {
    fn new(capacity: i32) -> Self {
        Self {
            cache: HashMap::new(),
            freq_map: HashMap::new(),
            min_freq: 0,
            capacity: capacity as usize,
        }
    }

    fn get(&mut self, key: i32) -> i32 {
        if self.capacity == 0 {
            return -1;
        }

        match self.cache.get(&key) {
            Some(node) => {
                let node = Rc::clone(node);
                self.incr_freq(&node);
                let value = node.borrow().value;
                value
            }
            None => -1,
        }
    }

    fn put(&mut self, key: i32, value: i32) {
        if self.capacity == 0 {
            return;
        }

        match self.cache.get(&key) {
            Some(node) => {
                let node = Rc::clone(node);
                node.borrow_mut().value = value;
                self.incr_freq(&node);
            }
            None => {
                if self.cache.len() == self.capacity {
                    let list = self.freq_map.get_mut(&self.min_freq).unwrap();
                    self.cache.remove(&list.pop_back().unwrap().borrow().key);
                }
                let node = Rc::new(RefCell::new(Node::new(key, value)));
                self.add_node(&node);
                self.cache.insert(key, node);
                self.min_freq = 1;
            }
        };
    }

    fn incr_freq(&mut self, node: &Rc<RefCell<Node>>) {
        let freq = node.borrow().freq;
        let list = self.freq_map.get_mut(&freq).unwrap();
        list.remove(node);
        if list.is_empty() {
            self.freq_map.remove(&freq);
            if freq == self.min_freq {
                self.min_freq += 1;
            }
        }
        node.borrow_mut().freq += 1;
        self.add_node(node);
    }

    fn add_node(&mut self, node: &Rc<RefCell<Node>>) {
        let freq = node.borrow().freq;
        match self.freq_map.get_mut(&freq) {
            Some(list) => {
                list.push_front(node);
            }
            None => {
                let mut list = LinkedList::new();
                list.push_front(node);
                self.freq_map.insert(node.borrow().freq, list);
            }
        };
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * let obj = LFUCache::new(capacity);
 * let ret_1: i32 = obj.get(key);
 * obj.put(key, value);
 */
