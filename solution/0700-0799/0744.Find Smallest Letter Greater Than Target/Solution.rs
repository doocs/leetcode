impl Solution {
    pub fn next_greatest_letter(letters: Vec<char>, target: char) -> char {
        let mut l = 0;
        let mut r = letters.len();
        while l < r {
            let mid = l + (r - l) / 2;
            if letters[mid] > target {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        letters[l % letters.len()]
    }
}
