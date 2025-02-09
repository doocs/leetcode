class Solution {
    public int[] assignElements(int[] groups, int[] elements) {
        int mx = Arrays.stream(groups).max().getAsInt();
        int[] d = new int[mx + 1];
        Arrays.fill(d, -1);
        for (int j = 0; j < elements.length; ++j) {
            int x = elements[j];
            if (x > mx || d[x] != -1) {
                continue;
            }
            for (int y = x; y <= mx; y += x) {
                if (d[y] == -1) {
                    d[y] = j;
                }
            }
        }
        int n = groups.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = d[groups[i]];
        }
        return ans;
    }
}
