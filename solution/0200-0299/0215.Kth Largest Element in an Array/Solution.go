func adjustHeap(heap []int,i,k int){
    child := i*2+1
    for child < k{
        if child + 1 < k && heap[child] > heap[child+1]{
            child++
        }
        if heap[child] > heap[i]{
            break
        }
        
        heap[child],heap[i] = heap[i],heap[child]
        i = child
        child = i * 2 + 1
    }
}


func findKthLargest(nums []int, k int) int {
    
    minHeap := make([]int,k)
    copy(minHeap,nums)
    
    for i := k/2-1;i>=0;i--{
        adjustHeap(minHeap,i,k)
    }
      
    for i := k;i < len(nums);i++{
        if nums[i] > minHeap[0]{
            minHeap[0] = nums[i]
            adjustHeap(minHeap,0,k)
        }
    }
        
    return minHeap[0]   
}