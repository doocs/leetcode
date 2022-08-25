func peopleIndexes(favoriteCompanies [][]string) []int {
	d := map[string]int{}
	idx, n := 0, len(favoriteCompanies)
	t := make([]map[int]bool, n)
	for i, v := range favoriteCompanies {
		for _, c := range v {
			if _, ok := d[c]; !ok {
				d[c] = idx
				idx++
			}
		}
		s := map[int]bool{}
		for _, c := range v {
			s[d[c]] = true
		}
		t[i] = s
	}
	ans := []int{}
	check := func(nums1, nums2 map[int]bool) bool {
		for v, _ := range nums1 {
			if _, ok := nums2[v]; !ok {
				return false
			}
		}
		return true
	}
	for i := 0; i < n; i++ {
		ok := true
		for j := 0; j < n; j++ {
			if i == j {
				continue
			}
			if check(t[i], t[j]) {
				ok = false
				break
			}
		}
		if ok {
			ans = append(ans, i)
		}
	}
	return ans
}