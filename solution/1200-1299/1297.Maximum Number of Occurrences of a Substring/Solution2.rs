impl Solution {
    pub fn max_freq(s: String, max_letters: i32, min_size: i32, _max_size: i32) -> i32 {
        let n = s.len();
        let bytes = s.as_bytes();
        let hashing = Hashing::new(bytes.to_vec());
        let mut freq = [0i32; 256];
        let mut k = 0;
        let mut ans = 0;
        let mut cnt: std::collections::HashMap<u64, i32> = std::collections::HashMap::new();

        for i in 1..=n {
            let c = bytes[i - 1] as usize;
            freq[c] += 1;
            if freq[c] == 1 {
                k += 1;
            }

            if i as i32 >= min_size {
                if k <= max_letters {
                    let x = hashing.query(i - min_size as usize, i - 1);
                    let v = cnt.entry(x).and_modify(|v| *v += 1).or_insert(1);
                    ans = ans.max(*v);
                }
                let j = i - min_size as usize;
                let c2 = bytes[j] as usize;
                freq[c2] -= 1;
                if freq[c2] == 0 {
                    k -= 1;
                }
            }
        }

        ans
    }
}

struct Hashing {
    p: Vec<u64>,
    h: Vec<u64>,
    base: u64,
    modv: u64,
}

impl Hashing {
    fn new(s: Vec<u8>) -> Self {
        Self::with_params(s, 13331, 998244353)
    }

    fn with_params(s: Vec<u8>, base: u64, modv: u64) -> Self {
        let n = s.len();
        let mut p = vec![0u64; n + 1];
        let mut h = vec![0u64; n + 1];
        p[0] = 1;
        for i in 1..=n {
            p[i] = p[i - 1].wrapping_mul(base) % modv;
            h[i] = (h[i - 1].wrapping_mul(base) + s[i - 1] as u64) % modv;
        }
        Self { p, h, base, modv }
    }

    fn query(&self, l: usize, r: usize) -> u64 {
        let mut res =
            self.h[r + 1] + self.modv - (self.h[l] * self.p[r - l + 1] % self.modv);
        res %= self.modv;
        res
    }
}
