impl Solution {
    pub fn fib(n: i32) -> i32 {
        let a = vec![vec![1, 1], vec![1, 0]];
        pow(a, n as usize)[0][1]
    }
}

fn mul(a: Vec<Vec<i32>>, b: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
    let m = a.len();
    let n = b[0].len();
    let mut c = vec![vec![0; n]; m];

    for i in 0..m {
        for j in 0..n {
            for k in 0..b.len() {
                c[i][j] += a[i][k] * b[k][j];
            }
        }
    }
    c
}

fn pow(mut a: Vec<Vec<i32>>, mut n: usize) -> Vec<Vec<i32>> {
    let mut res = vec![vec![1, 0], vec![0, 1]];

    while n > 0 {
        if n & 1 == 1 {
            res = mul(res, a.clone());
        }
        a = mul(a.clone(), a);
        n >>= 1;
    }
    res
}
