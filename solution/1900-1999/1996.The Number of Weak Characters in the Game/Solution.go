func numberOfWeakCharacters(properties [][]int) (ans int) {
	sort.Slice(properties, func(i, j int) bool {
		a, b := properties[i], properties[j]
		if a[0] == b[0] {
			return a[1] < b[1]
		}
		return a[0] > b[0]
	})
	mx := 0
	for _, x := range properties {
		if x[1] < mx {
			ans++
		} else {
			mx = x[1]
		}
	}
	return
}