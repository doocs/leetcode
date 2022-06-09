class Solution {
    public int expectNumber(int[] scores) {
        Set<Integer> s = new HashSet<>();
        for (int v : scores) {
            s.add(v);
        }
        return s.size();
    }
}
