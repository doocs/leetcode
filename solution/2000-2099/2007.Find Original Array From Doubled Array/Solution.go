func findOriginalArray(changed []int) []int {
	if len(changed)%2 != 0 {
		return []int{}
	}
	n := 100010
	counter := make([]int, n)
	for _, x := range changed {
		counter[x]++
	}
	if counter[0]%2 != 0 {
		return []int{}
	}
	var res []int
	for j := 0; j < counter[0]/2; j++ {
		res = append(res, 0)
	}
	for i := 1; i < n; i++ {
		if counter[i] == 0 {
			continue
		}
		if i*2 >= n || counter[i] > counter[i*2] {
			return []int{}
		}
		for j := 0; j < counter[i]; j++ {
			res = append(res, i)
		}
		counter[i*2] -= counter[i]
	}
	return res
}