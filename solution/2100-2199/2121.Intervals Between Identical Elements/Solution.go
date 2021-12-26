func getDistances(arr []int) []int64 {
	d := make(map[int][]int)
	n := len(arr)
	for i, v := range arr {
		d[v] = append(d[v], i)
	}
	ans := make([]int64, n)
	for _, v := range d {
		m := len(v)
		val := 0
		for _, e := range v {
			val += e
		}
		val -= m * v[0]
		for i, p := range v {
			delta := 0
			if i >= 1 {
				delta = v[i] - v[i-1]
			}
			val += i*delta - (m-i)*delta
			ans[p] = int64(val)
		}
	}
	return ans
}