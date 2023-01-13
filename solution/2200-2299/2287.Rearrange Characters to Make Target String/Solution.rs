impl Solution {
    pub fn rearrange_characters(s: String, target: String) -> i32 {
        let mut count1 = [0; 26];
        let mut count2 = [0; 26];
        for c in s.as_bytes() {
            count1[(c - b'a') as usize] += 1;
        }
        for c in target.as_bytes() {
            count2[(c - b'a') as usize] += 1;
        }
        let mut ans = i32::MAX;
        for i in 0..26 {
            if count2[i] != 0 {
                ans = ans.min(count1[i] / count2[i]);
            }
        }
        ans
    }
}
