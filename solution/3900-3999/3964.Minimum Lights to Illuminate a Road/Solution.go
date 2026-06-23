func minLights(lights []int) int {
	n := len(lights)
	d := make([]int, n)

	for i, v := range lights {
		if v > 0 {
			l := max(0, i-v)
			r := min(n-1, i+v)
			d[l]++
			if r+1 < n {
				d[r+1]--
			}
		}
	}

	s, cnt, ans := 0, 0, 0
	for _, x := range d {
		s += x
		if s == 0 {
			cnt++
		} else {
			ans += (cnt + 2) / 3
			cnt = 0
		}
	}

	ans += (cnt + 2) / 3
	return ans
}
