func majorityElement(nums []int) int {
    var cnt, major int
    for _, num := range nums {
        if cnt == 0 {
            major = num
            cnt = 1
        } else {
            if major == num {
                cnt++
            } else {
                cnt--
            }
        }
    }
    return major
}