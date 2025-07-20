func totalReplacements(ranks []int) (ans int) {
	cur := ranks[0]
	for _, x := range ranks {
		if x < cur {
			cur = x
			ans++
		}
	}
	return
}