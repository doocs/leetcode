func waysToBuyPensPencils(total int, cost1 int, cost2 int) int64 {
	var ans int64
	for x := 0; x <= total/cost1; x++ {
		v := total - x*cost1
		ans += int64(v/cost2 + 1)
	}
	return ans
}