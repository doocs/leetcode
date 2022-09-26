class MedianFinder {
    private nums: number[];

    constructor() {
        this.nums = [];
    }

    addNum(num: number): void {
        const { nums } = this;
        let l = 0;
        let r = nums.length;
        while (l < r) {
            const mid = (l + r) >>> 1;
            if (nums[mid] < num) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        nums.splice(l, 0, num);
    }

    findMedian(): number {
        const { nums } = this;
        const n = nums.length;
        if ((n & 1) === 1) {
            return nums[n >> 1];
        }
        return (nums[n >> 1] + nums[(n >> 1) - 1]) / 2;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * var obj = new MedianFinder()
 * obj.addNum(num)
 * var param_2 = obj.findMedian()
 */
