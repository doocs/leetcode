struct Trie {
    children: [Option<Box<Trie>>; 26],
    is_end: bool,
}

impl Trie {
    fn new() -> Self {
        Trie {
            children: Default::default(),
            is_end: false,
        }
    }

    fn insert(&mut self, w: &str) {
        let mut node = self;
        for c in w.chars() {
            let idx = (c as usize) - ('a' as usize);
            node = node.children[idx].get_or_insert_with(|| Box::new(Trie::new()));
        }
        node.is_end = true;
    }

    fn search(&self, w: &str) -> bool {
        let mut node = self;
        for c in w.chars() {
            let idx = (c as usize) - ('a' as usize);
            if let Some(next_node) = &node.children[idx] {
                node = next_node.as_ref();
                if !node.is_end {
                    return false;
                }
            }
        }
        true
    }
}

impl Solution {
    pub fn longest_word(words: Vec<String>) -> String {
        let mut trie = Trie::new();
        for w in &words {
            trie.insert(w);
        }
        let mut ans = String::new();
        for w in &words {
            if (w.len() > ans.len() || (w.len() == ans.len() && w < &ans)) && trie.search(w) {
                ans = w.clone();
            }
        }
        ans
    }
}
