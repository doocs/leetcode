func beautySum(s string) (ans int) {
	for i := range s {
		cnt := [26]int{}
		for j := i; j < len(s); j++ {
			cnt[s[j]-'a']++
			mi, mx := 1000, 0
			for _, v := range cnt {
				if v > 0 {
					if mi > v {
						mi = v
					}
					if mx < v {
						mx = v
					}
				}
			}
			ans += mx - mi
		}
	}
	return
}