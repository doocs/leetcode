var f = [12]int{0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334}

func countDaysTogether(arriveAlice string, leaveAlice string, arriveBob string, leaveBob string) int {
	start := arriveAlice
	if arriveBob > start {
		start = arriveBob
	}
	end := leaveAlice
	if leaveBob < end {
		end = leaveBob
	}
	d1 := f[int(start[0]-'0')*10+int(start[1]-'0')-1] + int(start[3]-'0')*10 + int(start[4]-'0')
	d2 := f[int(end[0]-'0')*10+int(end[1]-'0')-1] + int(end[3]-'0')*10 + int(end[4]-'0')
	return max(d2-d1+1, 0)
}
