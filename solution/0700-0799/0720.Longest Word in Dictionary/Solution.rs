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
            if node.children[idx].is_none() {
                node.children[idx] = Some(Box::new(Trie::new()));
            }
            node = node.children[idx].as_mut().unwrap();
        }
        node.is_end = true;
    }

    fn search(&self, w: &str) -> bool {
        let mut node = self;
        for c in w.chars() {
            let idx = (c as usize) - ('a' as usize);
            if node.children[idx].is_none() || !node.children[idx].as_ref().unwrap().is_end {
                return false;
            }
            node = node.children[idx].as_ref().unwrap();
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
        for w in words {
            if trie.search(&w) && (ans.len() < w.len() || (ans.len() == w.len() && w < ans)) {
                ans = w;
            }
        }
        ans
    }
}
