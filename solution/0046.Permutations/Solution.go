func permute(nums []int) [][]int {
    
    n := len(nums)
    tmp := make([]int,n)
    ans := make([][]int,0)
    idx := make([]bool,n)    //默认0
    dfs(&ans,idx,tmp,nums,n,0)
    return ans
}


func dfs(ans *[][]int,idx []bool,tmp []int,nums []int,n int,cur int) {
    
    if cur == n {
        t := make([]int,n)
        copy(t,tmp)
        *ans = append(*ans,t)
        return
    }
    
    for i := 0;i < n;i++{
        if idx[i] == true{
            continue
        }
        
        idx[i] = true
        tmp[cur] = nums[i]
        dfs(ans,idx,tmp,nums,n,cur+1)
        idx[i] = false
    }
}

