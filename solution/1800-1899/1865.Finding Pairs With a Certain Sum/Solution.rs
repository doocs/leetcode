use std::collections::HashMap;

struct FindSumPairs {
    nums1: Vec<i32>,
    nums2: Vec<i32>,
    cnt: HashMap<i32, i32>,
}

impl FindSumPairs {
    fn new(nums1: Vec<i32>, nums2: Vec<i32>) -> Self {
        let mut cnt = HashMap::new();
        for &x in &nums2 {
            *cnt.entry(x).or_insert(0) += 1;
        }
        Self { nums1, nums2, cnt }
    }

    fn add(&mut self, index: i32, val: i32) {
        let i = index as usize;
        let old_val = self.nums2[i];
        *self.cnt.entry(old_val).or_insert(0) -= 1;
        if self.cnt[&old_val] == 0 {
            self.cnt.remove(&old_val);
        }

        self.nums2[i] += val;
        let new_val = self.nums2[i];
        *self.cnt.entry(new_val).or_insert(0) += 1;
    }

    fn count(&self, tot: i32) -> i32 {
        let mut ans = 0;
        for &x in &self.nums1 {
            let target = tot - x;
            if let Some(&c) = self.cnt.get(&target) {
                ans += c;
            }
        }
        ans
    }
}
