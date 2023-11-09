impl Solution {
    pub fn make_smallest_palindrome(s: String) -> String {
        let mut b: Vec<u8> = s.bytes().collect();
        let mut i = 0;
        let mut j = b.len() - 1;

        while i < j {
            if b[i] != b[j] {
                if b[i] < b[j] {
                    b[j] = b[i];
                } else {
                    b[i] = b[j];
                }
            }

            i += 1;
            j -= 1;
        }

        String::from_utf8(b).unwrap()
    }
}
