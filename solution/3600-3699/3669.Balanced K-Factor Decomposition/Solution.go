const MX = 100001

var g [][]int

func init() {
	g = make([][]int, MX)
	for i := 1; i < MX; i++ {
		for j := i; j < MX; j += i {
			g[j] = append(g[j], i)
		}
	}
}

var (
	cur  int
	ans  []int
	path []int
)

func minDifference(n int, k int) []int {
	cur = math.MaxInt32
	ans = nil
	path = make([]int, k)
	dfs(k-1, n, math.MaxInt32, 0)
	return ans
}

func dfs(i, x, mi, mx int) {
	if i == 0 {
		d := max(mx, x) - min(mi, x)
		if d < cur {
			cur = d
			path[i] = x
			ans = slices.Clone(path)
		}
		return
	}
	for _, y := range g[x] {
		path[i] = y
		dfs(i-1, x/y, min(mi, y), max(mx, y))
	}
}
