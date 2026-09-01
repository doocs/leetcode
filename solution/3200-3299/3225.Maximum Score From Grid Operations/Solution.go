import "math"

func maximumScore(grid [][]int) int64 {
	n := len(grid)
	const inf = math.MinInt64 / 2
	s := make([][]int64, n)
	for j := 0; j < n; j++ {
		s[j] = make([]int64, n+1)
		for i := 0; i < n; i++ {
			s[j][i+1] = s[j][i] + int64(grid[i][j])
		}
	}
	f := make([][]int64, n+1)
	for i := range f {
		f[i] = make([]int64, n+1)
		for k := range f[i] {
			f[i][k] = inf
		}
	}
	for h := 0; h <= n; h++ {
		f[h][0] = 0
	}
	for j := 0; j < n-1; j++ {
		g := make([][]int64, n+1)
		for i := range g {
			g[i] = make([]int64, n+1)
			for k := range g[i] {
				g[i][k] = inf
			}
		}
		for h1 := 0; h1 <= n; h1++ {
			pre := make([]int64, n+2)
			pre[0] = f[h1][0]
			for h2 := 1; h2 <= n; h2++ {
				pre[h2] = max(pre[h2-1], f[h1][h2])
			}
			suf := make([]int64, n+2)
			for i := range suf {
				suf[i] = inf
			}
			for h2 := n; h2 >= 0; h2-- {
				v := int64(inf)
				if f[h1][h2] != inf {
					v = f[h1][h2] + max(int64(0), s[j][h2]-s[j][h1])
				}
				suf[h2] = max(suf[h2+1], v)
			}
			for hp := 0; hp <= n; hp++ {
				add := max(int64(0), s[j][hp]-s[j][h1])
				v1 := int64(inf)
				if pre[hp] != inf {
					v1 = pre[hp] + add
				}
				g[hp][h1] = max(v1, suf[hp+1])
			}
		}
		f = g
	}
	var ans int64
	for h1 := 0; h1 <= n; h1++ {
		for h2 := 0; h2 <= n; h2++ {
			if f[h1][h2] != inf {
				ans = max(ans, f[h1][h2]+max(int64(0), s[n-1][h2]-s[n-1][h1]))
			}
		}
	}
	return ans
}
