class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        Pair[] t = new Pair[n];
        for (int i = 0; i < n; ++i) {
            t[i] = new Pair(quality[i], wage[i]);
        }
        Arrays.sort(t, (a, b) -> Double.compare(a.x, b.x));
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        double ans = 1e9;
        int tot = 0;
        for (var e : t) {
            tot += e.q;
            pq.offer(e.q);
            if (pq.size() == k) {
                ans = Math.min(ans, tot * e.x);
                tot -= pq.poll();
            }
        }
        return ans;
    }
}

class Pair {
    double x;
    int q;

    Pair(int q, int w) {
        this.q = q;
        this.x = (double) w / q;
    }
}