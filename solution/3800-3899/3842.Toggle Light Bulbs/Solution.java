class Solution {
    public List<Integer> toggleLightBulbs(List<Integer> bulbs) {
        int[] st = new int[101];
        for (int x : bulbs) {
            st[x] ^= 1;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < st.length; ++i) {
            if (st[i] == 1) {
                ans.add(i);
            }
        }
        return ans;
    }
}
