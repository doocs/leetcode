class Solution {
    func pileBox(_ box: [[Int]]) -> Int {
        let boxes = box.sorted {
            if $0[0] == $1[0] {
                return $0[1] > $1[1]
            } else {
                return $0[0] < $1[0]
            }
        }
        
        let n = boxes.count
        var f = Array(repeating: 0, count: n)
        var ans = 0
        
        for i in 0..<n {
            f[i] = boxes[i][2]
            for j in 0..<i {
                if boxes[j][1] < boxes[i][1] && boxes[j][2] < boxes[i][2] {
                    f[i] = max(f[i], f[j] + boxes[i][2])
                }
            }
            ans = max(ans, f[i])
        }
        
        return ans
    }
}