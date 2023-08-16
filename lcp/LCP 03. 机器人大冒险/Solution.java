class Solution {
    public boolean robot(String command, int[][] obstacles, int x, int y) {
        Set<List<Integer>> vis = new HashSet<>();
        int i = 0, j = 0;
        vis.add(List.of(i, j));
        for (char c : command.toCharArray()) {
            if (c == 'U') {
                ++j;
            } else {
                ++i;
            }
            vis.add(List.of(i, j));
        }
        int k = Math.min(x / i, y / j);
        if (!vis.contains(List.of(x - k * i, y - k * j))) {
            return false;
        }
        for (int[] ob : obstacles) {
            if (ob[0] > x || ob[1] > y) {
                continue;
            }
            k = Math.min(ob[0] / i, ob[1] / j);
            if (vis.contains(List.of(ob[0] - k * i, ob[1] - k * j))) {
                return false;
            }
        }
        return true;
    }
}