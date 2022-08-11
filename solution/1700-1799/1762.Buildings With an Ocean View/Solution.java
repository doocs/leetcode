class Solution {
    public int[] findBuildings(int[] heights) {
        int mx = 0;
        LinkedList<Integer> ans = new LinkedList<>();
        for (int i = heights.length - 1; i >= 0; --i) {
            int v = heights[i];
            if (mx < v) {
                ans.addFirst(i);
                mx = v;
            }
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }
}