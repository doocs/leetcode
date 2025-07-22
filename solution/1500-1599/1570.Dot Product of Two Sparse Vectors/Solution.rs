use std::collections::HashMap;

#[derive(Clone)]
struct SparseVector {
    d: HashMap<usize, i32>,
}

impl SparseVector {
    fn new(nums: Vec<i32>) -> Self {
        let mut d = HashMap::new();
        for (i, &x) in nums.iter().enumerate() {
            if x != 0 {
                d.insert(i, x);
            }
        }
        SparseVector { d }
    }

    fn dot_product(&self, vec: SparseVector) -> i32 {
        let (a, b) = (&self.d, &vec.d);
        let mut ans = 0;

        if a.len() > b.len() {
            return vec.dot_product(self.clone());
        }

        for (&i, &x) in a.iter() {
            if let Some(&y) = b.get(&i) {
                ans += x * y;
            }
        }

        ans
    }
}
