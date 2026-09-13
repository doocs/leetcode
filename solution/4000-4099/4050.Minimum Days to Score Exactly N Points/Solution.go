const mx = 100001

var f = func() []int {
	f := make([]int, mx)

	for i := range f {
		f[i] = int(^uint(0) >> 1)
	}

	f[0] = -1

	for i := 1; i < mx; i++ {
		for j := 1; j*(j+1)/2 <= i; j++ {
			s := j * (j + 1) / 2
			f[i] = min(f[i], f[i-s]+j+1)
		}
	}

	return f
}()

func minDays(n int) int {
	return f[n]
}
