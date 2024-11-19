class Solution {
    func maximumScore(_ cards: [Int], _ cnt: Int) -> Int {
        let sortedCards = cards.sorted()
        let n = sortedCards.count
        var ans = 0

        for i in 0..<cnt {
            ans += sortedCards[n - i - 1]
        }

        if ans % 2 == 0 {
            return ans
        }

        var smallestOddInside = Int.max
        var smallestEvenInside = Int.max
        var largestOddOutside = Int.min
        var largestEvenOutside = Int.min

        for i in (n - cnt)..<n {
            if sortedCards[i] % 2 == 1 {
                smallestOddInside = min(smallestOddInside, sortedCards[i])
            } else {
                smallestEvenInside = min(smallestEvenInside, sortedCards[i])
            }
        }

        for i in 0..<(n - cnt) {
            if sortedCards[i] % 2 == 1 {
                largestOddOutside = max(largestOddOutside, sortedCards[i])
            } else {
                largestEvenOutside = max(largestEvenOutside, sortedCards[i])
            }
        }

        var maxScore = -1
        if smallestOddInside != Int.max && largestEvenOutside != Int.min {
            maxScore = max(maxScore, ans - smallestOddInside + largestEvenOutside)
        }
        if smallestEvenInside != Int.max && largestOddOutside != Int.min {
            maxScore = max(maxScore, ans - smallestEvenInside + largestOddOutside)
        }

        return maxScore >= 0 ? maxScore : 0
    }
}
