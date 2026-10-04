class Solution {
    func verifyPostorder(_ postorder: [Int]) -> Bool {
        var stk = [(0, postorder.count - 1)]
        while let (l, r) = stk.popLast() {
            if l >= r {
                continue
            }
            let v = postorder[r]
            var i = l
            while i < r && postorder[i] < v {
                i += 1
            }
            if i < r && postorder[i..<r].contains(where: { $0 < v }) {
                return false
            }
            stk.append((i, r - 1))
            stk.append((l, i - 1))
        }
        return true
    }
}
