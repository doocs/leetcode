func kMirror(k int, n int) int64 {
	check := func(x int64, k int) bool {
		s := []int{}
		for x > 0 {
			s = append(s, int(x%int64(k)))
			x /= int64(k)
		}
		for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
			if s[i] != s[j] {
				return false
			}
		}
		return true
	}

	var ans int64 = 0
	for l := 1; ; l++ {
		x := pow10((l - 1) / 2)
		y := pow10((l + 1) / 2)
		for i := x; i < y; i++ {
			v := int64(i)
			j := i
			if l%2 != 0 {
				j = i / 10
			}
			for j > 0 {
				v = v*10 + int64(j%10)
				j /= 10
			}
			if check(v, k) {
				ans += v
				n--
				if n == 0 {
					return ans
				}
			}
		}
	}
}

func pow10(exp int) int {
	res := 1
	for i := 0; i < exp; i++ {
		res *= 10
	}
	return res
}
