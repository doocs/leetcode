class Solution {
    func bestLine(_ points: [[Int]]) -> [Int] {
        let n = points.count
        var mx = 0
        var ans = [0, 1]
        for i in 0..<n {
            let x1 = points[i][0], y1 = points[i][1]
            for j in i + 1..<n {
                let x2 = points[j][0], y2 = points[j][1]
                if x1 == x2 && y1 == y2 {
                    continue
                }
                var cnt = 0
                var a = -1
                var b = -1
                for k in 0..<n {
                    let x3 = points[k][0], y3 = points[k][1]
                    let c1 = (y2 - y1) * (x3 - x1)
                    let c2 = (y3 - y1) * (x2 - x1)
                    if c1 == c2 {
                        cnt += 1
                        if a < 0 {
                            a = k
                        } else if b < 0 {
                            b = k
                        }
                    }
                }
                if cnt > mx || (cnt == mx && (a < ans[0] || (a == ans[0] && b < ans[1]))) {
                    mx = cnt
                    ans = [a, b]
                }
            }
        }
        return ans
    }
}
