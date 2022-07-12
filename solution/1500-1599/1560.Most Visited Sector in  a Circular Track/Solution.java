class Solution {
    public List<Integer> mostVisited(int n, int[] rounds) {
        int m = rounds.length - 1;
        List<Integer> ans = new ArrayList<>();
        if (rounds[0] <= rounds[m]) {
            for (int i = rounds[0]; i <= rounds[m]; ++i) {
                ans.add(i);
            }
        } else {
            for (int i = 1; i <= rounds[m]; ++i) {
                ans.add(i);
            }
            for (int i = rounds[0]; i <= n; ++i) {
                ans.add(i);
            }
        }
        return ans;
    }
}