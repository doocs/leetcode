class Solution {
    public int[] constructRectangle(int area) {
        int sr = (int) Math.sqrt(area);
        int l = sr, w = sr;
        while (l <= area && w >= 1) {
            int s = l * w;
            if (s == area) break;
            if (s > area) --w;
            else ++l;
        }
        return new int[]{l, w};
    }
}