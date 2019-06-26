class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int res = 0;
        for (int x : houses) {
            int i = Arrays.binarySearch(heaters, x);
            if (i < 0) {
                i = ~i;
            }
            int dis1 = i > 0 ? x - heaters[i - 1] : Integer.MAX_VALUE;
            int dis2 = i < heaters.length ? heaters[i] - x : Integer.MAX_VALUE;
            res = Math.max(res, Math.min(dis1, dis2));
        }
        return res;
    }
}
