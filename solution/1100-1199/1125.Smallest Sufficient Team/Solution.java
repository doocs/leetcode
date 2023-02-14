class Solution {
    private int m;
    private int n;
    private int[] ps;
    private int[][][] f;
    private static final int MX = 100;

    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        m = req_skills.length;
        n = people.size();
        ps = new int[n];
        f = new int[n][1 << m][];
        Map<String, Integer> d = new HashMap<>();
        for (int i = 0; i < m; ++i) {
            d.put(req_skills[i], i);
        }
        for (int i = 0; i < n; ++i) {
            for (String skill : people.get(i)) {
                ps[i] |= 1 << d.get(skill);
            }
        }
        return dfs(0, 0);
    }

    private int[] dfs(int i, int state) {
        if (i == n) {
            return state == (1 << m) - 1 ? new int[0] : add(new int[0], MX);
        }
        if (f[i][state] != null) {
            return f[i][state];
        }
        int[] ans1 = dfs(i + 1, state);
        int[] ans2 = dfs(i + 1, state | ps[i]);
        if (ans1.length > 0 && ans1[0] == MX && ans2.length > 0 && ans2[0] == MX) {
            return f[i][state] = ans1;
        }
        if (ans1.length > 0 && ans1[0] == MX) {
            return f[i][state] = add(ans2, i);
        }
        if (ans2.length > 0 && ans2[0] == MX) {
            return f[i][state] = ans1;
        }
        if (ans1.length < ans2.length + 1) {
            return f[i][state] = ans1;
        }
        return f[i][state] = add(ans2, i);
    }

    private int[] add(int[] nums, int x) {
        int[] copy = Arrays.copyOf(nums, nums.length + 1);
        copy[copy.length - 1] = x;
        return copy;
    }
}