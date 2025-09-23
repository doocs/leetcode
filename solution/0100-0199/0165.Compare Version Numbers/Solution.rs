impl Solution {
    pub fn compare_version(version1: String, version2: String) -> i32 {
        let (bytes1, bytes2) = (version1.as_bytes(), version2.as_bytes());
        let (m, n) = (bytes1.len(), bytes2.len());
        let (mut i, mut j) = (0, 0);

        while i < m || j < n {
            let mut a = 0;
            let mut b = 0;

            while i < m && bytes1[i] != b'.' {
                a = a * 10 + (bytes1[i] - b'0') as i32;
                i += 1;
            }
            while j < n && bytes2[j] != b'.' {
                b = b * 10 + (bytes2[j] - b'0') as i32;
                j += 1;
            }

            if a != b {
                return if a < b { -1 } else { 1 };
            }

            i += 1;
            j += 1;
        }

        0
    }
}
