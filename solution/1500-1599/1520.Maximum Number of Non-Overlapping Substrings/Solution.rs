impl Solution {
    pub fn max_num_of_substrings(s: String) -> Vec<String> {
        let n = s.len();
        let bs = s.as_bytes();
        let mut first = [-1; 26];
        let mut last = [0; 26];
        for i in 0..n {
            let x = (bs[i] - b'a') as usize;
            if first[x] == -1 {
                first[x] = i as i32;
            }
            last[x] = i as i32;
        }
        let mut segs = vec![];
        for x in 0..26 {
            if first[x] == -1 {
                continue;
            }
            let l = first[x];
            let mut r = last[x];
            let mut i = l;
            while i <= r {
                let y = (bs[i as usize] - b'a') as usize;
                if first[y] < l {
                    break;
                }
                r = r.max(last[y]);
                i += 1;
            }
            if i > r {
                segs.push((l, r));
            }
        }
        segs.sort_by_key(|&(_, r)| r);
        let mut ans = vec![];
        let mut end = -1;
        for (l, r) in segs {
            if l > end {
                ans.push(s[l as usize..=r as usize].to_string());
                end = r;
            }
        }
        ans
    }
}
