func minAnagramLength(s string) int {
	n := len(s)
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	check := func(k int) bool {
		for i := 0; i < n; i += k {
			cnt1 := [26]int{}
			for j := i; j < i+k; j++ {
				cnt1[s[j]-'a']++
			}
			for j, v := range cnt {
				if cnt1[j]*(n/k) != v {
					return false
				}
			}
		}
		return true
	}
	for i := 1; ; i++ {
		if n%i == 0 && check(i) {
			return i
		}
	}
}