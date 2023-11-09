impl Solution {
    pub fn pick_gifts(gifts: Vec<i32>, k: i32) -> i64 {
        let mut h = std::collections::BinaryHeap::from(gifts);
        let mut ans = 0;

        for _ in 0..k {
            if let Some(mut max_gift) = h.pop() {
                max_gift = (max_gift as f64).sqrt().floor() as i32;
                h.push(max_gift);
            }
        }

        for x in h {
            ans += x as i64;
        }

        ans
    }
}
