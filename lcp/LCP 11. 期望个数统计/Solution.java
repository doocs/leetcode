class Solution {
    public int expectNumber(int[] scores) {
        Set<Integer> s = new HashSet<>();
        for (int x : scores) {
            s.add(x);
        }
        return s.size();
    }
}