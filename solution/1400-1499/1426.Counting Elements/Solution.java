class Solution {
    public int countElements(int[] arr) {
        Set<Integer> s = new HashSet<>();
        for (int num : arr) {
            s.add(num);
        }
        int res = 0;
        for (int num : arr) {
            if (s.contains(num + 1)) {
                ++res;
            }
        }
        return res;
    }
}