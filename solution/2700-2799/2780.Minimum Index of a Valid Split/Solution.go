func minimumIndex(nums []int) int {
	x, cnt := 0, 0
	freq := map[int]int{}
	for _, v := range nums {
		freq[v]++
		if freq[v] > cnt {
			x, cnt = v, freq[v]
		}
	}
	cur := 0
	for i, v := range nums {
		i++
		if v == x {
			cur++
			if cur*2 > i && (cnt-cur)*2 > len(nums)-i {
				return i - 1
			}
		}
	}
	return -1
}