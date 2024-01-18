class Solution {
    public boolean checkIfExist(int[] arr) {
        Set<Integer> s = new HashSet<>();
        for (int v : arr) {
            if (s.contains(v * 2) || (v % 2 == 0 && s.contains(v / 2))) {
                return true;
            }
            s.add(v);
        }
        return false;
    }
}