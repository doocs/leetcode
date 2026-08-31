func minOperations(nums []int, sum int) int {
	const inf = int(1e9)

	f := make([]int, sum+1)
	for i := range f {
		f[i] = inf
	}
	f[0] = 0

	for _, x := range nums {
		for w := sum; w >= 0; w-- {
			for i, y := 0, x; y <= w; i, y = i+1, y*2 {
				f[w] = min(f[w], f[w-y]+i)
			}

			for i, y := 1, x/2; y > 0; i, y = i+1, y/2 {
				for j, z := 0, y; z <= w; j, z = j+1, z*2 {
					f[w] = min(f[w], f[w-z]+i+j)
				}
			}
		}
	}

	if f[sum] == inf {
		return -1
	}
	return f[sum]
}
