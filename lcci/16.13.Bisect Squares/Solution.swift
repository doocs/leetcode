class Solution {
    func cutSquares(_ square1: [Int], _ square2: [Int]) -> [Double] {
        let x1 = Double(square1[0]) + Double(square1[2]) / 2.0
        let y1 = Double(square1[1]) + Double(square1[2]) / 2.0
        let x2 = Double(square2[0]) + Double(square2[2]) / 2.0
        let y2 = Double(square2[1]) + Double(square2[2]) / 2.0

        if x1 == x2 {
            let y3 = min(Double(square1[1]), Double(square2[1]))
            let y4 = max(Double(square1[1]) + Double(square1[2]), Double(square2[1]) + Double(square2[2]))
            return [x1, y3, x2, y4]
        }

        let k = (y2 - y1) / (x2 - x1)
        let b = y1 - k * x1

        if abs(k) > 1 {
            let y3 = min(Double(square1[1]), Double(square2[1]))
            let x3 = (y3 - b) / k
            let y4 = max(Double(square1[1]) + Double(square1[2]), Double(square2[1]) + Double(square2[2]))
            let x4 = (y4 - b) / k
            if x3 > x4 || (x3 == x4 && y3 > y4) {
                return [x4, y4, x3, y3]
            }
            return [x3, y3, x4, y4]
        } else {
            let x3 = min(Double(square1[0]), Double(square2[0]))
            let y3 = k * x3 + b
            let x4 = max(Double(square1[0]) + Double(square1[2]), Double(square2[0]) + Double(square2[2]))
            let y4 = k * x4 + b
            return [x3, y3, x4, y4]
        }
    }
}