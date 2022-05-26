class Solution {
    public int[] arrayRankTransform(int[] arr) {
        Set<Integer> s = new HashSet<>();
        for (int v : arr) {
            s.add(v);
        }
        List<Integer> alls = new ArrayList<>(s);
        alls.sort((a, b) -> a - b);
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < alls.size(); ++i) {
            m.put(alls.get(i), i + 1);
        }
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            ans[i] = m.get(arr[i]);
        }
        return ans;
    }
}