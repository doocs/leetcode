impl Solution {
    pub fn minimum_deletions(word: String, k: i32) -> i32 {
        let mut freq = [0; 26];
        for c in word.chars() {
            freq[(c as u8 - b'a') as usize] += 1;
        }
        let mut nums = vec![];
        for &v in freq.iter() {
            if v > 0 {
                nums.push(v);
            }
        }
        let n = word.len() as i32;
        let mut ans = n;
        for i in 0..=n {
            let mut cur = 0;
            for &x in nums.iter() {
                if x < i {
                    cur += x;
                } else if x > i + k {
                    cur += x - i - k;
                }
            }
            ans = ans.min(cur);
        }
        ans
    }
}
