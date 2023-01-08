func isItPossible(word1 string, word2 string) bool {
	cnt1 := [26]int{}
	cnt2 := [26]int{}
	for _, c := range word1 {
		cnt1[c-'a']++
	}
	for _, c := range word2 {
		cnt2[c-'a']++
	}
	for i := range cnt1 {
		for j := range cnt2 {
			if cnt1[i] > 0 && cnt2[j] > 0 {
				cnt1[i], cnt2[j] = cnt1[i]-1, cnt2[j]-1
				cnt1[j], cnt2[i] = cnt1[j]+1, cnt2[i]+1
				d := 0
				for k, a := range cnt1 {
					if a > 0 {
						d++
					}
					if cnt2[k] > 0 {
						d--
					}
				}
				if d == 0 {
					return true
				}
				cnt1[i], cnt2[j] = cnt1[i]+1, cnt2[j]+1
				cnt1[j], cnt2[i] = cnt1[j]-1, cnt2[i]-1
			}
		}
	}
	return false
}