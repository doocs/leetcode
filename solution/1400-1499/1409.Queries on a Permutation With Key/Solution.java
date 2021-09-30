class Solution {
    public int[] processQueries(int[] queries, int m) {
        List<Integer> nums = new LinkedList<>();
        for (int i = 0; i < m; ++i) {
            nums.add(i + 1);
        }
        int[] res = new int[queries.length];
        int i = 0;
        for (int num : queries) {
            res[i++] = nums.indexOf(num);
            nums.remove(Integer.valueOf(num));
            nums.add(0, num);
        }
        return res;
    }
}