class Solution {
    public List<Integer> mostVisited(int n, int[] rounds) {
        int[] ans = new int[n];
        for (int i = 0; i < rounds.length; i++) {
            rounds[i]--;
        }
        ans[rounds[0]]++;
        for (int i = 0; i < rounds.length - 1; i++) {
            int start = rounds[i];
            int end = rounds[i + 1];
            if (end <= start) {
                end += n;
            }
            for (int j = start + 1; j <= end; j++) {
                ans[j % n]++;
            }
        }

        int max = Arrays.stream(ans).max().orElse(-1);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < ans.length; i++) {
            if (ans[i] == max) {
                list.add(i + 1);
            }
        }
        return list;
    }
}