const MX = 1330

var f [MX]int64

func init() {
	f[0] = 0
	for i := 1; i < MX; i++ {
		f[i] = f[i-1] + int64(i)
		for j := 0; j < i; j++ {
			f[i] += 2 * int64(i|j)
		}
	}
}

func maxSizedArray(s int64) int {
	l, r := 1, MX
	for l < r {
		m := (l + r + 1) >> 1
		if f[m-1]*int64(m-1)*int64(m)/2 <= s {
			l = m
		} else {
			r = m - 1
		}
	}
	return l
}
