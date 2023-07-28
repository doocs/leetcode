func runeReserve(runes []int) (ans int) {
	sort.Ints(runes)
	i := 0
	for j, x := range runes {
		if j > 0 && x-runes[j-1] > 1 {
			i = j
		} else if t := j - i + 1; ans < t {
			ans = t
		}
	}
	return
}