type BusBooking struct {
	tot      int
	minTot   int
	costData [][]int
}

func Constructor(n int) BusBooking {
	costData := make([][]int, n)
	for i := range costData {
		costData[i] = make([]int, 4)
	}
	return BusBooking{
		tot:      0,
		minTot:   0,
		costData: costData,
	}
}

func (this *BusBooking) Toggle(seat string) {
	i := 0
	for k := 1; k < len(seat); k++ {
		i = i*10 + int(seat[k]-'0')
	}
	i -= 1
	j := int(seat[0] - 'A')

	if this.costData[i][j] > 0 {
		this.minTot -= 1
		this.tot -= this.costData[i][j]
		this.costData[i][j] = 0
		return
	}

	cost := 1
	if (j == 0 && this.costData[i][1] > 0) || (j == 3 && this.costData[i][2] > 0) {
		cost = 3
	}
	this.minTot += 1
	this.tot += cost
	this.costData[i][j] = cost
}

func (this *BusBooking) GetTotalTime() int {
	return this.tot
}

func (this *BusBooking) GetMinTime() int {
	return this.minTot
}

/**
 * Your BusBooking object will be instantiated and called as such:
 * obj := Constructor(n);
 * obj.Toggle(seat);
 * param_2 := obj.GetTotalTime();
 * param_3 := obj.GetMinTime();
 */
