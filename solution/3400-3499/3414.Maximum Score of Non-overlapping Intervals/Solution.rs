impl Solution {
    pub fn maximum_weight(intervals: Vec<Vec<i32>>) -> Vec<i32> {
        let aa = intervals.len();
        let mut bb: Vec<(i32, i32, i64, i32)> = intervals
            .iter()
            .enumerate()
            .map(|(cc, dd)| (dd[0], dd[1], dd[2] as i64, cc as i32))
            .collect();
        bb.sort_unstable();
        let mut ee = vec![[(0i64, [0i32; 4], 0usize); 5]; aa + 1];
        for ff in (0..aa).rev() {
            let gg = bb.partition_point(|hh| hh.0 <= bb[ff].1);
            for ii in 0..5usize {
                let mut jj = ee[ff + 1][ii];
                if ii > 0 {
                    let kk = ee[gg][ii - 1];
                    let mut ll = kk.1;
                    let mut mm = kk.2;
                    while mm > 0 && ll[mm - 1] > bb[ff].3 {
                        ll[mm] = ll[mm - 1];
                        mm -= 1;
                    }
                    ll[mm] = bb[ff].3;
                    let nn = (kk.0 + bb[ff].2, ll, kk.2 + 1);
                    if nn.0 > jj.0 || (nn.0 == jj.0 && nn.1[..nn.2] < jj.1[..jj.2]) {
                        jj = nn;
                    }
                }
                ee[ff][ii] = jj;
            }
        }
        let oo = ee[0][4];
        oo.1[..oo.2].to_vec()
    }
}
