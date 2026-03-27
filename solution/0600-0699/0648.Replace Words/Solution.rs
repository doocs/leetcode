struct Trie {
    children: Vec<Option<Box<Trie>>>,
    is_end: bool,
}

impl Trie {
    fn new() -> Self {
        Self {
            children: (0..26).map(|_| None).collect(),
            is_end: false,
        }
    }

    fn insert(&mut self, w: String) {
        let mut node = self;
        for c in w.chars() {
            let idx = (c as u8 - b'a') as usize;
            node = node.children[idx].get_or_insert(Box::new(Trie::new()));
        }
        node.is_end = true;
    }

    fn search(&self, w: &str) -> String {
        let mut node = self;
        for (i, c) in w.chars().enumerate() {
            let idx = (c as u8 - b'a') as usize;
            if node.children[idx].is_none() {
                return w.to_string();
            }
            node = node.children[idx].as_ref().unwrap();
            if node.is_end {
                return w[..i + 1].to_string();
            }
        }
        w.to_string()
    }
}

impl Solution {
    pub fn replace_words(dictionary: Vec<String>, sentence: String) -> String {
        let mut trie = Trie::new();
        for w in dictionary {
            trie.insert(w);
        }

        sentence
            .split_whitespace()
            .map(|w| trie.search(w))
            .collect::<Vec<_>>()
            .join(" ")
    }
}
