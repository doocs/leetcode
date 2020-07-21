func verifyPostorder(postorder []int) bool {
    if len(postorder) < 2 {
        return true
    }
    return helper(postorder, 0, len(postorder)-1)
}
//递归
func helper(postorder []int , left,right int) bool {
    if left >= right {
        return true
    }
    //最后一位即根
    rootValue := postorder[right]
    //从左开始往右遍历，直到大于根停止,小于部分是左子树
    i := left
    for i < right && postorder[i] < rootValue {
        i++
    }
    //剩下部分是右子树，检查是否都大于根值
    for j := i; j < right; j++ {
        if postorder[j] < rootValue {
            return false
        }
    }
    l := helper(postorder,left,i-1) //检查左子树，左子树i要减一
    r := helper(postorder,i,right-1)//检查右子树，剔除最后一位是根
    return l && r
}