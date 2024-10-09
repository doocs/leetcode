class Solution {
    public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> ans = new HashSet<>();
        Set<Integer> s = new HashSet<>();
        for (int x : arr) {
            Set<Integer> t = new HashSet<>();
            for (int y : s) {
                t.add(x | y);
            }
            t.add(x);
            ans.addAll(t);
            s = t;
        }
        return ans.size();
    }
}