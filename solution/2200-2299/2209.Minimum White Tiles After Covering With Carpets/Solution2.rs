impl Solution {
    pub fn minimum_white_tiles(floor: String, num_carpets: i32, carpet_len: i32) -> i32 {
        let n = floor.len();
        let a: Vec<u8> = floor.bytes().collect();
        let m = num_carpets as usize;
        let k = carpet_len as usize;
        let mut s = vec![0i32; n + 1];
        for i in 0..n {
            s[i + 1] = s[i] + if a[i] == b'1' { 1 } else { 0 };
        }
        let mut f = vec![vec![0i32; m + 1]; n + 1];
        for i in (0..n).rev() {
            for j in 0..=m {
                if a[i] == b'0' {
                    f[i][j] = f[i + 1][j];
                } else if j == 0 {
                    f[i][j] = s[n] - s[i];
                } else {
                    let cover = if i + k <= n { f[i + k][j - 1] } else { 0 };
                    f[i][j] = (1 + f[i + 1][j]).min(cover);
                }
            }
        }
        f[0][m]
    }
}
