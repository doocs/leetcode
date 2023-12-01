struct BinaryIndexedTree {
    n: usize,
    c: Vec<i32>,
    d: Vec<i32>,
}

impl BinaryIndexedTree {
    fn new(n: usize) -> BinaryIndexedTree {
        BinaryIndexedTree {
            n,
            c: vec![0; n + 1],
            d: vec![0; n + 1],
        }
    }

    fn update(&mut self, x: usize, v: i32, cnt: i32) {
        let mut x = x as usize;
        while x <= self.n {
            if self.c[x] < v {
                self.c[x] = v;
                self.d[x] = cnt;
            } else if self.c[x] == v {
                self.d[x] += cnt;
            }
            x += x & x.wrapping_neg();
        }
    }

    fn query(&self, mut x: usize) -> (i32, i32) {
        let (mut v, mut cnt) = (0, 0);
        while x > 0 {
            if self.c[x] > v {
                v = self.c[x];
                cnt = self.d[x];
            } else if self.c[x] == v {
                cnt += self.d[x];
            }
            x -= x & x.wrapping_neg();
        }
        (v, cnt)
    }
}

impl Solution {
    pub fn find_number_of_lis(nums: Vec<i32>) -> i32 {
        let mut arr: Vec<i32> = nums.iter().cloned().collect();
        arr.sort();
        let m = arr.len();
        let mut tree = BinaryIndexedTree::new(m);
        for x in nums.iter() {
            if let Ok(i) = arr.binary_search(x) {
                let (v, cnt) = tree.query(i);
                tree.update(i + 1, v + 1, cnt.max(1));
            }
        }
        let (_, ans) = tree.query(m);
        ans
    }
}
