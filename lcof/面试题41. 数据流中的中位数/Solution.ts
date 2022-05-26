class MedianFinder {
    public arr: number[] = [];

    constructor() {}

    addNum(num: number): void {
        const { arr } = this;
        let l = 0;
        let r = arr.length - 1;
        while (l <= r) {
            const mid = (l + r) >> 1;
            if (arr[mid] < num) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        arr.splice(l, 0, num);
    }

    findMedian(): number {
        const { arr } = this;
        const n = arr.length;
        if (n % 2 === 0) {
            return (arr[n >> 1] + arr[(n >> 1) - 1]) / 2;
        }
        return arr[n >> 1];
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * var obj = new MedianFinder()
 * obj.addNum(num)
 * var param_2 = obj.findMedian()
 */
