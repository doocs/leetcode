class Solution {
    public int magicalString(int n) {
        List<Integer> s = new ArrayList<>(Arrays.asList(1, 2, 2));
        int i = 2;
        while (s.size() < n) {
            int pre = s.get(s.size() - 1);
            int cur = 3 - pre;
            for (int j = 0; j < s.get(i); ++j) {
                s.add(cur);
            }
            ++i;
        }
        int ans = 0;
        for (int j = 0; j < n; ++j) {
            if (s.get(j) == 1) {
                ++ans;
            }
        }
        return ans;
    }
}