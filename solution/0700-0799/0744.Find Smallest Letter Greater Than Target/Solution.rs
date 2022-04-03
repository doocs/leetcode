impl Solution {
    pub fn next_greatest_letter(letters: Vec<char>, target: char) -> char {
        let n = letters.len();
        let mut l = 0;
        let mut r = n;
        while l < r {
            let mid = l + r >> 1;
            if letters[mid] <= target {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        letters[l % n]
    }
}
