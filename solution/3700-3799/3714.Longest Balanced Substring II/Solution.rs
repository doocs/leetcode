use std::collections::HashMap;

impl Solution {
    pub fn longest_balanced(s: String) -> i32 {
        let x = Self::calc1(&s);
        let y = Self::calc2(&s, 'a', 'b')
            .max(Self::calc2(&s, 'b', 'c'))
            .max(Self::calc2(&s, 'a', 'c'));
        let z = Self::calc3(&s);
        x.max(y).max(z)
    }

    fn calc1(s: &str) -> i32 {
        let bytes = s.as_bytes();
        let mut res = 0i32;
        let mut i = 0usize;
        let n = bytes.len();

        while i < n {
            let mut j = i + 1;
            while j < n && bytes[j] == bytes[i] {
                j += 1;
            }
            res = res.max((j - i) as i32);
            i = j;
        }
        res
    }

    fn calc2(s: &str, a: char, b: char) -> i32 {
        let bytes = s.as_bytes();
        let a = a as u8;
        let b = b as u8;

        let mut res = 0i32;
        let mut i = 0usize;
        let n = bytes.len();

        while i < n {
            while i < n && bytes[i] != a && bytes[i] != b {
                i += 1;
            }

            let mut pos: HashMap<i32, i32> = HashMap::new();
            pos.insert(0, i as i32 - 1);

            let mut d = 0i32;
            while i < n && (bytes[i] == a || bytes[i] == b) {
                if bytes[i] == a {
                    d += 1;
                } else {
                    d -= 1;
                }

                if let Some(&pre) = pos.get(&d) {
                    res = res.max(i as i32 - pre);
                } else {
                    pos.insert(d, i as i32);
                }
                i += 1;
            }
        }

        res
    }

    fn f(x: i32, y: i32) -> i64 {
        (((x + 100000) as i64) << 20) | ((y + 100000) as i64)
    }

    fn calc3(s: &str) -> i32 {
        let mut pos: HashMap<i64, i32> = HashMap::new();
        pos.insert(Self::f(0, 0), -1);

        let mut cnt = [0i32; 3];
        let mut res = 0i32;

        for (i, c) in s.bytes().enumerate() {
            cnt[(c - b'a') as usize] += 1;

            let x = cnt[0] - cnt[1];
            let y = cnt[1] - cnt[2];
            let k = Self::f(x, y);

            if let Some(&pre) = pos.get(&k) {
                res = res.max(i as i32 - pre);
            } else {
                pos.insert(k, i as i32);
            }
        }

        res
    }
}
