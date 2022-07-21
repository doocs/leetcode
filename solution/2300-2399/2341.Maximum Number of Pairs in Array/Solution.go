func numberOfPairs(nums []int) []int {
    cnt := make([]int, 101)
    for _, v := range nums {
        cnt[v]++
    }
    s := 0
    for _, v := range cnt {
        s += v / 2
    }
    return []int{s, len(nums) - s * 2}
}