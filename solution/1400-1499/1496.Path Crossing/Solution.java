class Solution {
    public boolean isPathCrossing(String path) {
        int x = 0;
        int y = 0;
        Set<Integer> vis = new HashSet<>();
        vis.add(0);
        for (char c : path.toCharArray()) {
            if (c == 'N') {
                ++y;
            } else if (c == 'S') {
                --y;
            } else if (c == 'E') {
                ++x;
            } else {
                --x;
            }
            int t = x * 20000 + y;
            if (vis.contains(t)) {
                return true;
            }
            vis.add(t);
        }
        return false;
    }
}