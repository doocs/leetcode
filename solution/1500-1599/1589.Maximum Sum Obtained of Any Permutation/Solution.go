func maxSumRangeQuery(nums []int, requests [][]int) int {
	n := 100010
	delta := make([]int, n)
	for _, request := range requests {
		delta[request[0]]++
		delta[request[1]+1]--
	}
	for i := 1; i < n; i++ {
		delta[i] += delta[i-1]
	}
	sort.Ints(nums)
	sort.Ints(delta)
	i, j, res := n-1, len(nums)-1, 0
	for i >= 0 && delta[i] > 0 {
		res += delta[i] * nums[j]
		i--
		j--
	}
	return res % 1000000007
}