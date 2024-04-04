func maxBottlesDrunk(numBottles int, numExchange int) int {
	ans := numBottles
	for numBottles >= numExchange {
		numBottles -= numExchange
		numExchange++
		ans++
		numBottles++
	}
	return ans
}