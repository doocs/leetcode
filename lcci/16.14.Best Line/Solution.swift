class Solution {
    func bestLine(_ points: [[Int]]) -> [Int] {
        let n = points.count
        var maxCount = 0
        var answer = [Int](repeating: 0, count: 2)
        
        for i in 0..<n {
            let x1 = points[i][0], y1 = points[i][1]
            for j in i + 1..<n {
                let x2 = points[j][0], y2 = points[j][1]
                var count = 2
                
                for k in j + 1..<n {
                    let x3 = points[k][0], y3 = points[k][1]
                    let a = (y2 - y1) * (x3 - x1)
                    let b = (y3 - y1) * (x2 - x1)
                    if a == b {
                        count += 1
                    }
                }
                
                if maxCount < count {
                    maxCount = count
                    answer = [i, j]
                }
            }
        }
        return answer
    }
}
