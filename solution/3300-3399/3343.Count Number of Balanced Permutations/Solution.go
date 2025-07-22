const (
	MX  = 80
	MOD = 1_000_000_007
)

var c [MX][MX]int

func init() {
	c[0][0] = 1
	for i := 1; i < MX; i++ {
		c[i][0] = 1
		for j := 1; j <= i; j++ {
			c[i][j] = (c[i-1][j] + c[i-1][j-1]) % MOD
		}
	}
}

func countBalancedPermutations(num string) int {
	var cnt [10]int
	s := 0
	for _, ch := range num {
		cnt[ch-'0']++
		s += int(ch - '0')
	}

	if s%2 != 0 {
		return 0
	}

	n := len(num)
	m := n/2 + 1
	f := make([][][][]int, 10)
	for i := range f {
		f[i] = make([][][]int, s/2+1)
		for j := range f[i] {
			f[i][j] = make([][]int, m)
			for k := range f[i][j] {
				f[i][j][k] = make([]int, m+1)
				for l := range f[i][j][k] {
					f[i][j][k][l] = -1
				}
			}
		}
	}

	var dfs func(i, j, a, b int) int
	dfs = func(i, j, a, b int) int {
		if i > 9 {
			if j == 0 && a == 0 && b == 0 {
				return 1
			}
			return 0
		}
		if a == 0 && j > 0 {
			return 0
		}
		if f[i][j][a][b] != -1 {
			return f[i][j][a][b]
		}
		ans := 0
		for l := 0; l <= min(cnt[i], a); l++ {
			r := cnt[i] - l
			if r >= 0 && r <= b && l*i <= j {
				t := c[a][l] * c[b][r] % MOD * dfs(i+1, j-l*i, a-l, b-r) % MOD
				ans = (ans + t) % MOD
			}
		}
		f[i][j][a][b] = ans
		return ans
	}

	return dfs(0, s/2, n/2, (n+1)/2)
}
