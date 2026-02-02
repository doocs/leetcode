class Solution {
    public int[] bestTower(int[][] towers, int[] center, int radius) {
        int cx = center[0], cy = center[1];
        int idx = -1;
        for (int i = 0; i < towers.length; i++) {
            int x = towers[i][0], y = towers[i][1], q = towers[i][2];
            int dist = Math.abs(x - cx) + Math.abs(y - cy);
            if (dist > radius) {
                continue;
            }
            if (idx == -1 || towers[idx][2] < q
                || (towers[idx][2] == q
                    && (x < towers[idx][0] || (x == towers[idx][0] && y < towers[idx][1])))) {
                idx = i;
            }
        }
        return idx == -1 ? new int[] {-1, -1} : new int[] {towers[idx][0], towers[idx][1]};
    }
}
