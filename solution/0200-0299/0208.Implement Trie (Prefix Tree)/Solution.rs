use std::{ rc::Rc, cell::RefCell, collections::HashMap };

struct TrieNode {
    pub val: Option<char>,
    pub flag: bool,
    pub child: HashMap<char, Rc<RefCell<TrieNode>>>,
}

impl TrieNode {
    fn new() -> Self {
        Self {
            val: None,
            flag: false,
            child: HashMap::new(),
        }
    }

    fn new_with_val(val: char) -> Self {
        Self {
            val: Some(val),
            flag: false,
            child: HashMap::new(),
        }
    }
}

struct Trie {
    root: Rc<RefCell<TrieNode>>,
}

/// Your Trie object will be instantiated and called as such:
/// let obj = Trie::new();
/// obj.insert(word);
/// let ret_2: bool = obj.search(word);
/// let ret_3: bool = obj.starts_with(prefix);
impl Trie {
    fn new() -> Self {
        Self {
            root: Rc::new(RefCell::new(TrieNode::new())),
        }
    }

    fn insert(&self, word: String) {
        let char_vec: Vec<char> = word.chars().collect();
        // Get the clone of current root node
        let mut root = Rc::clone(&self.root);
        for c in &char_vec {
            if !root.borrow().child.contains_key(c) {
                // We need to manually create the entry
                root.borrow_mut().child.insert(*c, Rc::new(RefCell::new(TrieNode::new())));
            }
            // Get the child node
            let root_clone = Rc::clone(root.borrow().child.get(c).unwrap());
            root = root_clone;
        }
        {
            root.borrow_mut().flag = true;
        }
    }

    fn search(&self, word: String) -> bool {
        let char_vec: Vec<char> = word.chars().collect();
        // Get the clone of current root node
        let mut root = Rc::clone(&self.root);
        for c in &char_vec {
            if !root.borrow().child.contains_key(c) {
                return false;
            }
            // Get the child node
            let root_clone = Rc::clone(root.borrow().child.get(c).unwrap());
            root = root_clone;
        }
        let flag = root.borrow().flag;
        flag
    }

    fn starts_with(&self, prefix: String) -> bool {
        let char_vec: Vec<char> = prefix.chars().collect();
        // Get the clone of current root node
        let mut root = Rc::clone(&self.root);
        for c in &char_vec {
            if !root.borrow().child.contains_key(c) {
                return false;
            }
            // Get the child node
            let root_clone = Rc::clone(root.borrow().child.get(c).unwrap());
            root = root_clone;
        }
        true
    }
}
