/**
 * Definition of commonBits API.
 * func commonBits(num int) int;
 */

func findNumber() (n int) {
	for i := 0; i < 32; i++ {
		count1 := commonBits(1 << i)
		count2 := commonBits(1 << i)
		if count1 > count2 {
			n |= 1 << i
		}
	}
	return
}