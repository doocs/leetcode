class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        Worker[] workers = new Worker[quality.length];
        for (int i = 0; i < quality.length; ++i) {
            workers[i] = new Worker((double) wage[i] / quality[i], quality[i]);
        }
        Arrays.sort(workers);
        double res = 1e9;
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        int s = 0;
        for (Worker worker : workers) {
            s += worker.quality;
            queue.offer(worker.quality);
            if (queue.size() > K) {
                s -= queue.poll();
            }
            if (queue.size() == K) {
                res = Math.min(res, s * worker.x);
            }
        }
        return res;
    }

    class Worker implements Comparable<Worker> {
        double x;
        int quality;

        public Worker(double x, int quality) {
            this.x = x;
            this.quality = quality;
        }

        @Override
        public int compareTo(Worker o) {
            return Double.compare(x, o.x);
        }
    }
}
