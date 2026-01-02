package main

import "strconv"

func countNoZeroPairs(n int64) int64 {
	s := []byte(strconv.FormatInt(n, 10))
	m := len(s)
	digits := make([]int, m+1)
	for i := 0; i < m; i++ {
		digits[i] = int(s[m-1-i] - '0')
	}
	digits[m] = 0

	var dp [2][2][2]int64
	dp[0][1][1] = 1

	for pos := 0; pos < m+1; pos++ {
		var ndp [2][2][2]int64
		target := digits[pos]
		for carry := 0; carry <= 1; carry++ {
			for aliveA := 0; aliveA <= 1; aliveA++ {
				for aliveB := 0; aliveB <= 1; aliveB++ {
					ways := dp[carry][aliveA][aliveB]
					if ways == 0 {
						continue
					}
					var A [10][2]int
					aLen := 0
					if aliveA == 1 {
						for d := 1; d <= 9; d++ {
							A[aLen][0] = d
							A[aLen][1] = 1
							aLen++
						}
						if pos > 0 {
							A[aLen][0] = 0
							A[aLen][1] = 0
							aLen++
						}
					} else {
						A[0][0] = 0
						A[0][1] = 0
						aLen = 1
					}

					var B [10][2]int
					bLen := 0
					if aliveB == 1 {
						for d := 1; d <= 9; d++ {
							B[bLen][0] = d
							B[bLen][1] = 1
							bLen++
						}
						if pos > 0 {
							B[bLen][0] = 0
							B[bLen][1] = 0
							bLen++
						}
					} else {
						B[0][0] = 0
						B[0][1] = 0
						bLen = 1
					}

					for ai := 0; ai < aLen; ai++ {
						da, na := A[ai][0], A[ai][1]
						for bi := 0; bi < bLen; bi++ {
							db, nb := B[bi][0], B[bi][1]
							sum := da + db + carry
							if sum%10 != target {
								continue
							}
							ncarry := sum / 10
							ndp[ncarry][na][nb] += ways
						}
					}
				}
			}
		}
		dp = ndp
	}

	return dp[0][0][0]
}

func countPairs(n int64) int64 {
	return countNoZeroPairs(n)
}
