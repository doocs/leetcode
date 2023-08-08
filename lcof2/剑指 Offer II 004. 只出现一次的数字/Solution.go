func singleNumber(nums []int) int {
	var ans int32
	for i := 0; i < 32; i++ {
		cnt := 0
		for _, x := range nums {
			cnt += x >> i & 1
		}
		cnt %= 3
		ans |= int32(cnt) << i
	}
	return int(ans)
}