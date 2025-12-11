impl Solution {
    pub fn count_mentions(number_of_users: i32, mut events: Vec<Vec<String>>) -> Vec<i32> {
        let n = number_of_users as usize;

        events.sort_by(|a, b| {
            let x: i32 = a[1].parse().unwrap();
            let y: i32 = b[1].parse().unwrap();
            if x == y {
                a[0].as_bytes()[2].cmp(&b[0].as_bytes()[2])
            } else {
                x.cmp(&y)
            }
        });

        let mut ans = vec![0_i32; n];
        let mut online_t = vec![0_i32; n];
        let mut lazy = 0_i32;

        for e in events {
            let etype = &e[0];
            let cur: i32 = e[1].parse().unwrap();
            let s = &e[2];

            let c0 = etype.as_bytes()[0] as char;

            if c0 == 'O' {
                let uid: usize = s.parse().unwrap();
                online_t[uid] = cur + 60;

            } else if s.as_bytes()[0] as char == 'A' {
                lazy += 1;

            } else if s.as_bytes()[0] as char == 'H' {
                for i in 0..n {
                    if online_t[i] <= cur {
                        ans[i] += 1;
                    }
                }

            } else {
                for a in s.split(' ') {
                    let uid: usize = a[2..].parse().unwrap();
                    ans[uid] += 1;
                }
            }
        }

        if lazy > 0 {
            for i in 0..n {
                ans[i] += lazy;
            }
        }

        ans
    }
}
