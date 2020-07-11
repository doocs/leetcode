func hammingWeight(num uint32) int {
    ans := 0 
    // num &=num-1 消除最右边的1
    for num != 0 {
        num &=num-1
        ans++
    }
    return ans
}