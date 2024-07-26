func isItPossible(word1 string, word2 string) bool {
	cnt1 := [26]int{}
	cnt2 := [26]int{}
	x, y := 0, 0
	for _, c := range word1 {
		cnt1[c-'a']++
		if cnt1[c-'a'] == 1 {
			x++
		}
	}
	for _, c := range word2 {
		cnt2[c-'a']++
		if cnt2[c-'a'] == 1 {
			y++
		}
	}
	for i := range cnt1 {
		for j := range cnt2 {
			if cnt1[i] > 0 && cnt2[j] > 0 {
				if i == j {
					if x == y {
						return true
					}
				} else {
					a := x
					if cnt1[i] == 1 {
						a--
					}
					if cnt1[j] == 0 {
						a++
					}

					b := y
					if cnt2[j] == 1 {
						b--
					}
					if cnt2[i] == 0 {
						b++
					}

					if a == b {
						return true
					}
				}
			}
		}
	}
	return false
}