func xorAfterQueries(nums []int, queries [][]int) int {
	const mod = int(1e9 + 7)
	for _, q := range queries {
		l, r, k, v := q[0], q[1], q[2], q[3]
		for idx := l; idx <= r; idx += k {
			nums[idx] = nums[idx] * v % mod
		}
	}
	ans := 0
	for _, x := range nums {
		ans ^= x
	}
	return ans
}
