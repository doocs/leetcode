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
            let i = (c as usize) - ('a' as usize);
            if node.children[i].is_none() {
                node.children[i] = Some(Box::new(Trie::new()));
            }
            node = node.children[i].as_mut().unwrap();
        }
        node.is_end = true;
    }

    fn search(&self, w: &str) -> bool {
        self.dfs(w, 0, 0)
    }

    fn dfs(&self, w: &str, i: usize, diff: usize) -> bool {
        if i == w.len() {
            return diff == 1 && self.is_end;
        }

        let j = (w.chars().nth(i).unwrap() as usize) - ('a' as usize);

        if let Some(child) = &self.children[j] {
            if child.dfs(w, i + 1, diff) {
                return true;
            }
        }

        if diff == 0 {
            for k in 0..26 {
                if k != j {
                    if let Some(child) = &self.children[k] {
                        if child.dfs(w, i + 1, 1) {
                            return true;
                        }
                    }
                }
            }
        }
        false
    }
}

struct MagicDictionary {
    trie: Trie,
}

impl MagicDictionary {
    fn new() -> Self {
        MagicDictionary { trie: Trie::new() }
    }

    fn build_dict(&mut self, dictionary: Vec<String>) {
        for w in dictionary {
            self.trie.insert(&w);
        }
    }

    fn search(&self, search_word: String) -> bool {
        self.trie.search(&search_word)
    }
}
