func maxPoints(technique1 []int, technique2 []int, k int) int64 {
	n := len(technique1)
	idx := make([]int, n)
	for i := 0; i < n; i++ {
		idx[i] = i
	}

	sort.Slice(idx, func(i, j int) bool {
		return technique1[idx[j]]-technique2[idx[j]] < technique1[idx[i]]-technique2[idx[i]]
	})

	var ans int64
	for _, x := range technique2 {
		ans += int64(x)
	}

	for i := 0; i < k; i++ {
		index := idx[i]
		ans -= int64(technique2[index])
		ans += int64(technique1[index])
	}

	for i := k; i < n; i++ {
		index := idx[i]
		if technique1[index] >= technique2[index] {
			ans -= int64(technique2[index])
			ans += int64(technique1[index])
		}
	}

	return ans
}
