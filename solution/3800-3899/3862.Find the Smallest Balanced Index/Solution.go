import "math"

func smallestBalancedIndex(nums []int) int {
	s, p := 0, 1
	for _, x := range nums {
		s += x
	}
	for i := len(nums) - 1; i >= 0; i-- {
		s -= nums[i]
		if s == p {
			return i
		}
		if p > math.MaxInt64/nums[i] {
			break
		}
		p *= nums[i]
		if p >= s {
			break
		}
	}
	return -1
}
