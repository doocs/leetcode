class Solution {
    public int countCollisions(String directions) {
        int l = 0, r = directions.length() -1, count = 0;
        while (l <= r && directions.substring(l, l+1).equals("L")) {
            l++;
        }
        while (l <= r && directions.substring(r, r+1).equals("R")) {
            r--;
        }
        for (int i = l; i <=r; i++) {
            if (!directions.substring(i, i+1).equals("S")) count += 1;
        }
        return count;
    }
}