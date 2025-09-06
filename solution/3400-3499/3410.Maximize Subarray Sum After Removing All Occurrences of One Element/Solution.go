func maxSubarraySum(nums []int) int64 {
	ans := int64(nums[0])
	prefix := int64(0)
	minPrefix := int64(0)

	modifiedMinPrefix := int64(0)
	count := make(map[int]int64)
	minPrefixPlusRemoval := make(map[int]int64)

	for _, num := range nums {
		n64 := int64(num)
		prefix += n64
		if prefix-modifiedMinPrefix > ans {
			ans = prefix - modifiedMinPrefix
		}
		if n64 < 0 {
			count[num]++
			prev := minPrefixPlusRemoval[num] // по умолчанию 0
			minPrefixPlusRemoval[num] = min(prev, minPrefix) + n64
			modifiedMinPrefix = min(modifiedMinPrefix, count[num]*n64)
			modifiedMinPrefix = min(modifiedMinPrefix, minPrefixPlusRemoval[num])
		}
		minPrefix = min(minPrefix, prefix)
		modifiedMinPrefix = min(modifiedMinPrefix, minPrefix)
	}

	return ans
}

func min(a, b int64) int64 {
	if a < b {
		return a
	}
	return b
}
