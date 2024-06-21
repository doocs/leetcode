impl Solution {
    pub fn magical_string(n: i32) -> i32 {
        let n = n as usize;
        let mut s = String::from("1221121");
        let mut i = 5;
        while s.len() < n {
            let c = s.as_bytes()[s.len() - 1];
            s.push(if c == b'1' { '2' } else { '1' });
            if s.as_bytes()[i] != b'1' {
                s.push(if c == b'1' { '2' } else { '1' });
            }
            i += 1;
        }
        s.as_bytes()[0..n].iter().filter(|&v| v == &b'1').count() as i32
    }
}
