impl Solution {
    pub fn lex_palindromic_permutation(s: String, target: String) -> String {
        let mut freq = [0usize; 26];
        s.bytes().for_each(|ch| freq[(ch - b'a') as usize] += 1);
        if freq.iter().filter(|&&cnt| cnt & 1 != 0).count() > 1 {
            return String::new();
        }
        let mid = freq.iter().position(|cnt| cnt & 1 != 0);
        freq.iter_mut().for_each(|cnt| *cnt /= 2);
        let mut ans = s.into_bytes();
        let tgt = target.as_bytes();
        let half = ans.len() / 2;
        let make = |buf: &mut [u8]| {
            if let Some(ch) = mid {
                buf[half] = b'a' + ch as u8;
            }
            let len = buf.len();
            for idx in 0..half {
                let ch = buf[idx];
                buf[len - 1 - idx] = ch;
            }
        };
        let mut pos = 0;
        while pos < half {
            let ch = (tgt[pos] - b'a') as usize;
            if freq[ch] == 0 {
                break;
            }
            ans[pos] = tgt[pos];
            freq[ch] -= 1;
            pos += 1;
        }
        if pos == half {
            make(&mut ans);
            if ans.as_slice() > tgt {
                return String::from_utf8(ans).unwrap();
            }
        }
        loop {
            if pos < half {
                let min = (tgt[pos] - b'a' + 1) as usize;
                if let Some(ch) = (min..26).find(|&ch| freq[ch] != 0) {
                    ans[pos] = b'a' + ch as u8;
                    freq[ch] -= 1;
                    let mut dst = pos + 1;
                    for (ch, &cnt) in freq.iter().enumerate() {
                        for off in 0..cnt {
                            ans[dst + off] = b'a' + ch as u8;
                        }
                        dst += cnt;
                    }
                    make(&mut ans);
                    return String::from_utf8(ans).unwrap();
                }
            }
            if pos == 0 {
                return String::new();
            }
            pos -= 1;
            freq[(tgt[pos] - b'a') as usize] += 1;
        }
    }
}
