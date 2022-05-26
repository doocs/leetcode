class Solution {
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        // 最小磁力的可能最小值
        int min = 1;
        // 最小磁力的可能最大值
        int max = (position[position.length - 1] - position[0]) / (m - 1);
        int ans = -1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (checkDistance(mid, position, m)) {
                ans = mid;
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return ans;
    }

    private boolean checkDistance(int mid, int[] position, int m) {
        int count = 1;
        int pre = position[0];
        for (int i = 1; i < position.length; i++) {
            if (position[i] - pre >= mid) {
                count++;
                if (count >= m) {
                    return true;
                }
                pre = position[i];
            }
        }
        return false;
    }
}