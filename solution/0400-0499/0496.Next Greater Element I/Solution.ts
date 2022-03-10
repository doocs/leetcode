function nextGreaterElement(nums1: number[], nums2: number[]): number[] {
    const map = new Map<number, number>();
    const stack: number[] = [Infinity];
    for (const num of nums2) {
        while (num > stack[stack.length - 1]) {
            map.set(stack.pop(), num);
        }
        stack.push(num);
    }
    return nums1.map(num => map.get(num) || -1);
}
