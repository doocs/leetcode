func equalFrequency(word string) bool {
	for i := range word {
		cnt := make([]int, 26)
		for j, c := range word {
			if j != i {
				cnt[c-'a']++
			}
		}
		vis := map[int]bool{}
		for _, v := range cnt {
			if v > 0 {
				vis[v] = true
			}
		}
		if len(vis) == 1 {
			return true
		}
	}
	return false
}