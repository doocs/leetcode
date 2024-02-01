class Solution {
    public int magicTower(int[] nums) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        long blood = 1, v = 0;
        int ans = 0;
        for (int x : nums) {
            if (x < 0) {
                q.offer(x);
            }
            blood += x;
            if (blood <= 0) {
                ++ans;
                v += q.peek();
                blood -= q.poll();
            }
        }
        blood += v;
        return blood <= 0 ? -1 : ans;
    }
}