impl Solution {
    pub fn maximum_removals(s: String, p: String, removable: Vec<i32>) -> i32 {
        let m = s.len();
        let n = p.len();
        let s: Vec<char> = s.chars().collect();
        let p: Vec<char> = p.chars().collect();
        let mut l = 0;
        let mut r = removable.len();

        let check = |k: usize| -> bool {
            let mut rem = vec![false; m];
            for i in 0..k {
                rem[removable[i] as usize] = true;
            }
            let mut i = 0;
            let mut j = 0;
            while i < m && j < n {
                if !rem[i] && s[i] == p[j] {
                    j += 1;
                }
                i += 1;
            }
            j == n
        };

        while l < r {
            let mid = (l + r + 1) / 2;
            if check(mid) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        l as i32
    }
}
