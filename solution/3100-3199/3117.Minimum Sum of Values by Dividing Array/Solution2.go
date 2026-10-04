func minimumValueSum(nums []int, andValues []int) int {
	n, m := len(nums), len(andValues)
	const stride = 100001
	f := map[int]int{0: 0}
	for i, x := range nums {
		g := map[int]int{}
		for key, cost := range f {
			j := key / stride
			a := key%stride - 1
			if n-i < m-j {
				continue
			}
			na := a & x
			if na < andValues[j] {
				continue
			}
			nk := j*stride + na + 1
			if v, ok := g[nk]; !ok || v > cost {
				g[nk] = cost
			}
			if na == andValues[j] {
				t := cost + x
				if j+1 == m {
					if i == n-1 {
						done := m * stride
						if v, ok := g[done]; !ok || v > t {
							g[done] = t
						}
					}
				} else {
					nk2 := (j + 1) * stride
					if v, ok := g[nk2]; !ok || v > t {
						g[nk2] = t
					}
				}
			}
		}
		f = g
	}
	if ans, ok := f[m*stride]; ok {
		return ans
	}
	return -1
}
