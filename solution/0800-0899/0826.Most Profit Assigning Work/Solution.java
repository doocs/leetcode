class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        List<int[]> job = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            job.add(new int[] {difficulty[i], profit[i]});
        }
        job.sort(Comparator.comparing(a -> a[0]));
        Arrays.sort(worker);
        int res = 0;
        int i = 0, t = 0;
        for (int w : worker) {
            while (i < n && job.get(i)[0] <= w) {
                t = Math.max(t, job.get(i++)[1]);
            }
            res += t;
        }
        return res;
    }
}