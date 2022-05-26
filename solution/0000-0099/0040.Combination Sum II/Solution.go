func combinationSum2(candidates []int, target int) [][]int {
    //排序，然后递归搜索
    sort.Ints(candidates)
    return dfs(candidates,target)
}

func dfs(nums []int, target int) [][]int {
    ret := [][]int{}
    for i, n := range nums {
        //跳过与之前迭代相同的值
        if i > 0 && nums[i-1] == n {
            continue
        } else if target < n {
            break
        } else if target == n {
            ret = append(ret, []int{n})
            continue
        }
        //不能使用同一位置数字
        for _,v := range dfs(nums[i+1:], target - n) {
            ret = append(ret,append([]int{n}, v...))
        }
    }

    return ret
}