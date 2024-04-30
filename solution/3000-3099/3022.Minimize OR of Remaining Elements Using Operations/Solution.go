func minOrAfterOperations(nums []int, k int) int {
    ans := 0
    rans := 0
    for i := 29; i >= 0; i-- {
        test := ans + (1 << i)
        cnt := 0
        val := 0
        for _, num := range nums {
            if val == 0 {
                val = test & num
            } else {
                val &= test & num
            }
            if val != 0 {
                cnt++
            }
        }
        if cnt > k {
            rans += (1 << i)
        } else {
            ans += (1 << i)
        }
    }
    return rans
}
