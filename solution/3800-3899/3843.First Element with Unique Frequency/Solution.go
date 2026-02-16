func firstUniqueFreq(nums []int) int {
	cnt := make(map[int]int)
	for _, x := range nums {
		cnt[x]++
	}

	freq := make(map[int]int)
	for _, v := range cnt {
		freq[v]++
	}

	for _, x := range nums {
		if freq[cnt[x]] == 1 {
			return x
		}
	}

	return -1
}
