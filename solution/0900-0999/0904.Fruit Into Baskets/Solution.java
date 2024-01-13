class Solution {
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int ans = 0;
        for (int i = 0, j = 0; i < fruits.length; ++i) {
            int x = fruits[i];
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
            while (cnt.size() > 2) {
                int y = fruits[j++];
                cnt.put(y, cnt.get(y) - 1);
                if (cnt.get(y) == 0) {
                    cnt.remove(y);
                }
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}