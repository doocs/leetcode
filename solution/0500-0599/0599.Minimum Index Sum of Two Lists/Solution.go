func findRestaurant(list1 []string, list2 []string) []string {
	mp := make(map[string]int)
	for i, v := range list2 {
		mp[v] = i
	}
	mi := 2000
	var ans []string
	for i, v := range list1 {
		if _, ok := mp[v]; ok {
			t := i + mp[v]
			if t < mi {
				ans = []string{v}
				mi = t
			} else if t == mi {
				ans = append(ans, v)
			}
		}
	}
	return ans
}