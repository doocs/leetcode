func countDaysTogether(arriveAlice string, leaveAlice string, arriveBob string, leaveBob string) int {
	days := []int{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
	f := func(s string) int {
		m, _ := strconv.Atoi(s[:2])
		d, _ := strconv.Atoi(s[3:])
		res := 0
		for i := 0; i < m-1; i++ {
			res += days[i]
		}
		res += d
		return res
	}
	a, b := arriveAlice, leaveBob
	if arriveAlice < arriveBob {
		a = arriveBob
	}
	if leaveAlice < leaveBob {
		b = leaveAlice
	}
	x, y := f(a), f(b)
	ans := y - x + 1
	if ans < 0 {
		return 0
	}
	return ans
}