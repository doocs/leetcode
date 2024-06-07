class Solution {
    private var postorder: [Int] = []

    func verifyPostorder(_ postorder: [Int]) -> Bool {
        self.postorder = postorder
        return dfs(0, postorder.count - 1)
    }

    private func dfs(_ l: Int, _ r: Int) -> Bool {
        if l >= r {
            return true
        }
        let rootValue = postorder[r]
        var i = l
        while i < r && postorder[i] < rootValue {
            i += 1
        }
        for j in i..<r {
            if postorder[j] < rootValue {
                return false
            }
        }
        return dfs(l, i - 1) && dfs(i, r - 1)
    }
}