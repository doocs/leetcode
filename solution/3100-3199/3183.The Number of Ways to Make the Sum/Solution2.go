const (
    m   = 100001
    mod = 1000000007
)

var f [m]int

func init() {
    f[0] = 1
    coins := []int{1, 2, 6}
    for _, x := range coins {
        for j := x; j < m; j++ {
            f[j] = (f[j] + f[j-x]) % mod
        }
    }
}

func numberOfWays(n int) int {
    ans := f[n]
    if n >= 4 {
        ans = (ans + f[n-4]) % mod
    }
    if n >= 8 {
        ans = (ans + f[n-8]) % mod
    }
    return ans
}