public class MedianFinder {
    private List<int> nums;
    private int curIndex;

    /** initialize your data structure here. */
    public MedianFinder() {
        nums = new List<int>();
    }

    private int FindIndex(int val) {
        int left = 0;
        int right = nums.Count - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (val > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
    
    public void AddNum(int num) {
        if (nums.Count == 0) {
            nums.Add(num);
            curIndex = 0;
        } else {
            curIndex = FindIndex(num);
            if (curIndex == nums.Count) {
                nums.Add(num);
            } else {
                nums.Insert(curIndex, num);
            }
        }
    }
    
    public double FindMedian() {
        if (nums.Count % 2 == 1) {
            return (double)nums[nums.Count / 2];
        } else {
            if (nums.Count == 0) {
                return 0;
            } else {
                return (double) (nums[nums.Count / 2 - 1] + nums[nums.Count / 2]) / 2;
            }
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.AddNum(num);
 * double param_2 = obj.FindMedian();
 */