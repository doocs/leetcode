func miceAndCheese(reward1 []int, reward2 []int, k int) (ans int) {
	n := len(reward1)
	idx := make([]int, n)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool {
		i, j = idx[i], idx[j]
		return reward1[j]-reward2[j] < reward1[i]-reward2[i]
	})
	for i := 0; i < k; i++ {
		ans += reward1[idx[i]]
	}
	for i := k; i < n; i++ {
		ans += reward2[idx[i]]
	}
	return
}