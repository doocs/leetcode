impl Solution {
    pub fn check_palindrome_formation(a: String, b: String) -> bool {
        fn check1(a: &[u8], b: &[u8]) -> bool {
            let (mut i, mut j) = (0, b.len() - 1);
            while i < j && a[i] == b[j] {
                i += 1;
                j -= 1;
            }
            if i >= j {
                return true;
            }
            check2(a, i, j) || check2(b, i, j)
        }

        fn check2(a: &[u8], mut i: usize, mut j: usize) -> bool {
            while i < j && a[i] == a[j] {
                i += 1;
                j -= 1;
            }
            i >= j
        }

        let a = a.as_bytes();
        let b = b.as_bytes();
        check1(a, b) || check1(b, a)
    }
}
