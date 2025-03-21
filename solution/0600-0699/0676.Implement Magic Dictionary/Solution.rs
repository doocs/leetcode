struct Trie {
    children: Vec<Option<Box<Trie>>>,
    val: i32,
}

impl Trie {
    fn new() -> Self {
        Trie {
            children: (0..26).map(|_| None).collect(),
            val: 0,
        }
    }

    fn insert(&mut self, w: &str, x: i32) {
        let mut node = self;
        for c in w.chars() {
            let idx = (c as usize) - ('a' as usize);
            if node.children[idx].is_none() {
                node.children[idx] = Some(Box::new(Trie::new()));
            }
            node = node.children[idx].as_mut().unwrap();
            node.val += x;
        }
    }

    fn search(&self, w: &str) -> i32 {
        let mut node = self;
        for c in w.chars() {
            let idx = (c as usize) - ('a' as usize);
            if node.children[idx].is_none() {
                return 0;
            }
            node = node.children[idx].as_ref().unwrap();
        }
        node.val
    }
}

struct MapSum {
    d: std::collections::HashMap<String, i32>,
    trie: Trie,
}

impl MapSum {
    fn new() -> Self {
        MapSum {
            d: std::collections::HashMap::new(),
            trie: Trie::new(),
        }
    }

    fn insert(&mut self, key: String, val: i32) {
        let x = val - self.d.get(&key).unwrap_or(&0);
        self.d.insert(key.clone(), val);
        self.trie.insert(&key, x);
    }

    fn sum(&self, prefix: String) -> i32 {
        self.trie.search(&prefix)
    }
}
