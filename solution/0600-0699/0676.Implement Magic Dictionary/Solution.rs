use std::collections::HashMap;

#[derive(Clone)]
struct Trie {
    children: Vec<Option<Box<Trie>>>,
    is_end: bool,
}

impl Trie {
    fn new() -> Self {
        Trie {
            children: vec![None; 26],
            is_end: false,
        }
    }

    fn insert(&mut self, word: &str) {
        let mut node = self;
        for &ch in word.as_bytes() {
            let index = (ch - b'a') as usize;
            node = node.children[index].get_or_insert_with(|| Box::new(Trie::new()));
        }
        node.is_end = true;
    }

    fn search(&self, word: &str, diff: i32) -> bool {
        if word.is_empty() {
            return diff == 1 && self.is_end;
        }

        let index = (word.as_bytes()[0] - b'a') as usize;
        if let Some(child) = &self.children[index] {
            if child.search(&word[1..], diff) {
                return true;
            }
        }

        if diff == 0 {
            for (i, child) in self.children.iter().enumerate() {
                if i != index && child.is_some() {
                    if
                        child
                            .as_ref()
                            .unwrap()
                            .search(&word[1..], 1)
                    {
                        return true;
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

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl MagicDictionary {
    fn new() -> Self {
        MagicDictionary { trie: Trie::new() }
    }

    fn build_dict(&mut self, dictionary: Vec<String>) {
        for word in dictionary {
            self.trie.insert(&word);
        }
    }

    fn search(&self, search_word: String) -> bool {
        self.trie.search(&search_word, 0)
    }
}/**
 * Your MagicDictionary object will be instantiated and called as such:
 * let obj = MagicDictionary::new();
 * obj.build_dict(dictionary);
 * let ret_2: bool = obj.search(searchWord);
 */
