class Solution {

func binarySearch<T:Comparable>(inputArr: [T], searchItem: T) -> Int? {
    var lowerIndex = 0
    var upperIndex = inputArr.count - 1

    while (lowerIndex < upperIndex) {
        let currentIndex = (lowerIndex + upperIndex)/2
        if (inputArr[currentIndex] <= searchItem) {
            lowerIndex = currentIndex + 1
        } else {
            upperIndex = currentIndex
        }}

    if (inputArr[upperIndex] <= searchItem) {return upperIndex + 1}
    return  lowerIndex

}


func jobScheduling(_ startTime: [Int], _ endTime: [Int], _ profit: [Int]) -> Int {
	let zipList = zip(zip(startTime, endTime), profit)
	var table: [(startTime:Int, endTime:Int, profit:Int, cumsum: Int)] = []

	for ((x,y),z) in  zipList {
	    table.append((x,y,z, 0))
	}
	table.sort(by: {$0.endTime < $1.endTime})
	let sortedEndTime = endTime.sorted()

	var profits: [Int] = [0]
	for iJob in table {
	    let index: Int! = binarySearch(inputArr: sortedEndTime, searchItem: iJob.startTime)
	    if profits.last! < profits[index] + iJob.profit {
	        profits.append(profits[index] + iJob.profit)
	        } else {
	        profits.append(profits.last!)
	    }
	}
	    return (profits.last!)
	}
}
