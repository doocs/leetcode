func minOperations(nums []int, sum int) int {
	const inf = int(1e9)

	f := make([]int, sum+1)
	for i := range f {
		f[i] = inf
	}
	f[0] = 0

	for _, x := range nums {
		for w := sum; w >= 0; w-- {
			i, y := 0, x
			for y <= w {
				f[w] = min(f[w], f[w-y]+i)
				i++
				y <<= 1
			}

			i, y = 1, x>>1
			for y > 0 {
				if y <= w {
					f[w] = min(f[w], f[w-y]+i)
				}
				i++
				y >>= 1
			}
		}
	}

	if f[sum] == inf {
		return -1
	}
	return f[sum]
}
