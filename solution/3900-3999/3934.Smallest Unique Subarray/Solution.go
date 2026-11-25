func smallestUniqueSubarray(nums []int) int {
	n := len(nums)
	names := [][]int{nums}                  // names[k][i] is equal for equal windows of length 2^k starting at i
	for half := 1; 2*half <= n; half *= 2 { // blocks of 2, 4, 8, ... while they fit
		ids := map[[2]int]int{} // pair of halves -> number of the whole window
		block := names[len(names)-1]
		whole := make([]int, len(block)-half)
		for i := half; i < len(block); i++ {
			pair := [2]int{block[i-half], block[i]}
			if _, seen := ids[pair]; !seen {
				ids[pair] = len(ids)
			}
			whole[i-half] = ids[pair]
		}
		names = append(names, whole)
	}

	found := func(length int) bool { // is some window of this length unique?
		k := bits.Len(uint(length)) - 1 // its first 2^k elements and its last 2^k cover it
		block, shift := names[k], length-(1<<k)
		count := map[[2]int]int{}
		for i := shift; i < len(block); i++ {
			count[[2]int{block[i-shift], block[i]}]++
		}
		for _, c := range count {
			if c == 1 {
				return true
			}
		}
		return false
	}

	return 1 + sort.Search(n-1, func(i int) bool { return found(i + 1) })
}
