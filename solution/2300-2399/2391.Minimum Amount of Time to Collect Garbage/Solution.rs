impl Solution {
    pub fn garbage_collection(garbage: Vec<String>, travel: Vec<i32>) -> i32 {
        let n = garbage.len();
        let cs = [b'M', b'P', b'G'];
        let mut count = [0, 0, 0];
        for s in garbage.iter() {
            for c in s.as_bytes().iter() {
                count[if c == &b'M' {
                    0
                } else if c == &b'P' {
                    1
                } else {
                    2
                }] += 1;
            }
        }

        let mut res = 0;
        for i in 0..3 {
            for j in 0..n {
                let s = &garbage[j];
                for c in s.as_bytes().iter() {
                    if c == &cs[i] {
                        res += 1;
                        count[i] -= 1;
                    }
                }
                if count[i] == 0 {
                    break;
                }

                res += travel[j];
            }
        }
        res
    }
}
