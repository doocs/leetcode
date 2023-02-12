func substringXorQueries(s string, queries [][]int) (ans [][]int) {
	d := map[int][]int{}
	for i := range s {
		x := 0
		for j := 0; j < 32 && i+j < len(s); j++ {
			x = x<<1 | int(s[i+j]-'0')
			if _, ok := d[x]; !ok {
				d[x] = []int{i, i + j}
			}
			if x == 0 {
				break
			}
		}
	}
	for _, q := range queries {
		first, second := q[0], q[1]
		val := first ^ second
		if v, ok := d[val]; ok {
			ans = append(ans, v)
		} else {
			ans = append(ans, []int{-1, -1})
		}
	}
	return
}