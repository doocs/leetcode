class Solution {
    public int expectNumber(int[] scores) {
      Set<Integer> set = new HashSet<>();
        for (int i = 0; i < scores.length; i++) {
            set.add(scores[i]);
        }
        return set.size();
    }
}
