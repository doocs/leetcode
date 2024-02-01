class MedianFinder {
    private PriorityQueue<Integer> q1 = new PriorityQueue<>();
    private PriorityQueue<Integer> q2 = new PriorityQueue<>(Collections.reverseOrder());
    private final int mod = (int) 1e9 + 7;
    private long s1;
    private long s2;

    public MedianFinder() {
    }

    public void addNum(int num) {
        q1.offer(num);
        s1 += num;
        num = q1.poll();
        q2.offer(num);
        s1 -= num;
        s2 += num;
        if (q2.size() - q1.size() > 1) {
            num = q2.poll();
            q1.offer(num);
            s1 += num;
            s2 -= num;
        }
    }

    public long findMedian() {
        if (q2.size() > q1.size()) {
            return q2.peek();
        }
        return (q1.peek() + q2.peek()) / 2;
    }

    public int cal() {
        long x = findMedian();
        return (int) ((s1 - x * q1.size() + x * q2.size() - s2) % mod);
    }
}

class Solution {
    public int[] numsGame(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        MedianFinder finder = new MedianFinder();
        for (int i = 0; i < n; ++i) {
            finder.addNum(nums[i] - i);
            ans[i] = finder.cal();
        }
        return ans;
    }
}