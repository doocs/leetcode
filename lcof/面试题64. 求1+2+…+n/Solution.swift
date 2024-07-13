class Solution {
    func sumNums(_ n: Int) -> Int {
        var s = n
        let _ = n > 0 && { s += sumNums(n - 1); return true }()
        return s
    }
}