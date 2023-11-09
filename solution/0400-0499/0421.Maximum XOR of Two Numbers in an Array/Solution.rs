struct Trie {
    children: [Option<Box<Trie>>; 2],
}

impl Trie {
    fn new() -> Trie {
        Trie {
            children: [None, None],
        }
    }

    fn insert(&mut self, x: i32) {
        let mut node = self;
        for i in (0..=30).rev() {
            let v = ((x >> i) & 1) as usize;
            if node.children[v].is_none() {
                node.children[v] = Some(Box::new(Trie::new()));
            }
            node = node.children[v].as_mut().unwrap();
        }
    }

    fn search(&self, x: i32) -> i32 {
        let mut node = self;
        let mut ans = 0;
        for i in (0..=30).rev() {
            let v = ((x >> i) & 1) as usize;
            if let Some(child) = &node.children[v ^ 1] {
                ans |= 1 << i;
                node = child.as_ref();
            } else {
                node = node.children[v].as_ref().unwrap();
            }
        }
        ans
    }
}

impl Solution {
    pub fn find_maximum_xor(nums: Vec<i32>) -> i32 {
        let mut trie = Trie::new();
        let mut ans = 0;
        for &x in nums.iter() {
            trie.insert(x);
            ans = ans.max(trie.search(x));
        }
        ans
    }
}
