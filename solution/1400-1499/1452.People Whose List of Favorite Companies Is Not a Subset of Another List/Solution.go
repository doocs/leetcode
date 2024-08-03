func peopleIndexes(favoriteCompanies [][]string) (ans []int) {
	n := len(favoriteCompanies)
	d := make(map[string]int)
	idx := 0
	nums := make([]map[int]struct{}, n)

	for i := 0; i < n; i++ {
		nums[i] = make(map[int]struct{})
		for _, s := range favoriteCompanies[i] {
			if _, ok := d[s]; !ok {
				d[s] = idx
				idx++
			}
			nums[i][d[s]] = struct{}{}
		}
	}

	check := func(a, b map[int]struct{}) bool {
		for x := range a {
			if _, ok := b[x]; !ok {
				return false
			}
		}
		return true
	}
	for i := 0; i < n; i++ {
		ok := true
		for j := 0; j < n && ok; j++ {
			if i != j && check(nums[i], nums[j]) {
				ok = false
			}
		}
		if ok {
			ans = append(ans, i)
		}
	}

	return
}
