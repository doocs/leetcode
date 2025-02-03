func canCompleteCircuit(gas []int, cost []int) int {
	totalGas, totalCost := 0, 0
	tank := 0
	startStation := 0

	for i := 0; i < len(gas); i++ {
		totalGas += gas[i]
		totalCost += cost[i]
		tank += gas[i] - cost[i]

		if tank < 0 {

			startStation = i + 1
			tank = 0
		}
	}

	if totalGas < totalCost {
		return -1
	}
	return startStation
}