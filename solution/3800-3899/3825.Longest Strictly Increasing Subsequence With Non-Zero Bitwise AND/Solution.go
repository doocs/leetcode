func longestSubsequence(nums []int) int {
	ans := 0
	m := bits.Len(uint(slices.Max(nums)))
	for i := 0; i < m; i++ {
		arr := make([]int, 0)
		for _, x := range nums {
			if (x>>i)&1 == 1 {
				arr = append(arr, x)
			}
		}
		ans = max(ans, lis(arr))
	}
	return ans
}

func lis(arr []int) int {
	g := make([]int, 0)
	for _, x := range arr {
		j := sort.SearchInts(g, x)
		if j == len(g) {
			g = append(g, x)
		} else {
			g[j] = x
		}
	}
	return len(g)
}
