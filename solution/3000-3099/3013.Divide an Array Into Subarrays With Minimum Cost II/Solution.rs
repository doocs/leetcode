use std::collections::BTreeMap;

impl Solution {
    pub fn minimum_cost(nums: Vec<i32>, k: i32, dist: i32) -> i64 {
        let n = nums.len();
        let k = (k - 1) as usize;
        let dist = dist as usize;

        let mut l: BTreeMap<i32, i32> = BTreeMap::new();
        let mut r: BTreeMap<i32, i32> = BTreeMap::new();

        let mut s: i64 = nums[0] as i64;
        let mut size: usize = 0;

        for i in 1..=dist + 1 {
            s += nums[i] as i64;
            *l.entry(nums[i]).or_insert(0) += 1;
        }
        size = dist + 1;

        while size > k {
            l2r(&mut l, &mut r, &mut s, &mut size);
        }

        let mut ans = s;

        for i in dist + 2..n {
            let x = nums[i - dist - 1];
            if let Some(v) = l.get_mut(&x) {
                *v -= 1;
                if *v == 0 {
                    l.remove(&x);
                }
                s -= x as i64;
                size -= 1;
            } else {
                let v = r.get_mut(&x).unwrap();
                *v -= 1;
                if *v == 0 {
                    r.remove(&x);
                }
            }

            let y = nums[i];
            let l_max = *l.iter().next_back().unwrap().0;
            if y < l_max {
                *l.entry(y).or_insert(0) += 1;
                size += 1;
                s += y as i64;
            } else {
                *r.entry(y).or_insert(0) += 1;
            }

            while size < k {
                r2l(&mut l, &mut r, &mut s, &mut size);
            }
            while size > k {
                l2r(&mut l, &mut r, &mut s, &mut size);
            }

            ans = ans.min(s);
        }

        ans
    }
}

fn l2r(
    l: &mut BTreeMap<i32, i32>,
    r: &mut BTreeMap<i32, i32>,
    s: &mut i64,
    size: &mut usize,
) {
    let x = *l.iter().next_back().unwrap().0;
    *s -= x as i64;

    if let Some(v) = l.get_mut(&x) {
        *v -= 1;
        if *v == 0 {
            l.remove(&x);
        }
    }

    *size -= 1;
    *r.entry(x).or_insert(0) += 1;
}

fn r2l(
    l: &mut BTreeMap<i32, i32>,
    r: &mut BTreeMap<i32, i32>,
    s: &mut i64,
    size: &mut usize,
) {
    let x = *r.iter().next().unwrap().0;

    if let Some(v) = r.get_mut(&x) {
        *v -= 1;
        if *v == 0 {
            r.remove(&x);
        }
    }

    *l.entry(x).or_insert(0) += 1;
    *s += x as i64;
    *size += 1;
}
