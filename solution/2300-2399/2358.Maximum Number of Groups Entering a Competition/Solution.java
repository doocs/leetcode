class Solution {
    public int maximumGroups(int[] grades) {
        Arrays.sort(grades);
        int ans = 1;
        int[] prev = new int[]{1, grades[0]};
        int[] curr = new int[]{0, 0};
        for (int i = 1; i < grades.length; ++i) {
            curr[0]++;
            curr[1] += grades[i];
            if (prev[0] < curr[0] && prev[1] < curr[1]) {
                prev = curr;
                curr = new int[]{0, 0};
                ++ans;
            }
        }
        return ans;
    }
}