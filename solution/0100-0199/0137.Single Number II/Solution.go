func singleNumber(nums []int) int {
	ans := int32(0)
	for i := 0; i < 32; i++ {
		cnt := int32(0)
		for _, num := range nums {
			cnt += int32(num) >> i & 1
		}
		cnt %= 3
		ans |= cnt << i
	}
	return int(ans)
}