/*
198.House Robber
动态规划的思路，找到状态转移方程 `f(n) = max(f(n-2)+nums[n], f(n-1))` ，初始值为 `f(0)=0, f(1)=nums[1]`
*/

func rob(nums []int) int {
	x, y := 0, 0
	for _, n := range nums {
		x, y = y, x+y
		if x > y {
			y = x
		}
	}
	return y
}