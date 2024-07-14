class Solution {
    private let factors = [3, 5, 7]

    func getKthMagicNumber(_ k: Int) -> Int {
        var heap: [Int] = [1]
        var seen = Set<Int>()
        seen.insert(1)

        var value = 1
        for _ in 1...k {
            value = heap.removeFirst()
            for factor in factors {
                let nextValue = value * factor
                if !seen.contains(nextValue) {
                    heap.append(nextValue)
                    seen.insert(nextValue)
                }
            }
            heap.sort()
        }
        return value
    }
}