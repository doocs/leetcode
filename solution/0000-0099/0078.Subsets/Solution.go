func subsets(nums []int) [][]int {
    n := len(nums)
    ans := make([][]int,0)
    tmp := make([]int,0)   
    dfs(&ans,tmp,nums,0,n)
    
    return ans
}


func dfs(ans *[][]int,tmp []int,nums []int,k,n int) {
    tmpVec := make([]int,len(tmp))
    copy(tmpVec,tmp)
    *ans = append(*ans,tmpVec)
    
    for i:=k;i < n;i++{
        tmp = append(tmp,nums[i])
        dfs(ans,tmp,nums,i+1,n)
        tmp = tmp[:len(tmp) - 1]
    }
}
