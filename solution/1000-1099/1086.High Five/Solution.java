class Solution {
    public int[][] highFive(int[][] items) {
        int size = 0;
        List[] s = new List[101];
        for (int[] item : items) {
            int i = item[0], score = item[1];
            if (s[i] == null) {
                ++size;
                s[i] = new ArrayList<>();
            }
            s[i].add(score);
        }
        int[][] res = new int[size][2];
        int j = 0;
        for (int i = 0; i < 101; ++i) {
            if (s[i] == null) {
                continue;
            }
            int avg = sumTop5(s[i]) / 5;
            res[j][0] = i;
            res[j++][1] = avg;
        }
        return res;
    }

    private int sumTop5(List<Integer> scores) {
        PriorityQueue<Integer> q = new PriorityQueue<>(5);
        for (int score : scores) {
            q.offer(score);
            if (q.size() > 5) {
                q.poll();
            }
        }
        int s = 0;
        while (!q.isEmpty()) {
            s += q.poll();
        }
        return s;
    }
}