impl Solution {
    pub fn compress(chars: &mut Vec<char>) -> i32 {
        let (mut i, mut k, n) = (0, 0, chars.len());
        while i < n {
            let mut j = i + 1;
            while j < n && chars[j] == chars[i] {
                j += 1;
            }
            chars[k] = chars[i];
            k += 1;

            if j - i > 1 {
                let cnt = (j - i).to_string();
                for c in cnt.chars() {
                    chars[k] = c;
                    k += 1;
                }
            }
            i = j;
        }
        k as i32
    }
}
