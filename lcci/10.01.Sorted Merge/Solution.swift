class Solution {
    func merge(_ A: inout [Int], _ m: Int, _ B: [Int], _ n: Int) {
        var i = m - 1, j = n - 1
        for k in stride(from: m + n - 1, through: 0, by: -1) {
            if j < 0 || (i >= 0 && A[i] > B[j]) {
                A[k] = A[i]
                i -= 1
            } else {
                A[k] = B[j]
                j -= 1
            }
        }
    }
}
