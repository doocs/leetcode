class Solution {
    public int nearestDrone(int[][] drones, int[] target) {
        int ans = -1;
        int mn = Integer.MAX_VALUE;
        int tx = target[0], ty = target[1];

        for (int i = 0; i < drones.length; i++) {
            int x = drones[i][0];
            int y = drones[i][1];
            int r = drones[i][2];

            int d = Math.abs(x - tx) + Math.abs(y - ty);

            if (d <= r && mn > d) {
                ans = i;
                mn = d;
            }
        }

        return ans;
    }
}