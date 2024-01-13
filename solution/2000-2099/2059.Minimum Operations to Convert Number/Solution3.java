class Solution {
    private int[] nums;

    public int minimumOperations(int[] nums, int start, int goal) {
        this.nums = nums;
        Map<Integer, Integer> m1 = new HashMap<>();
        Map<Integer, Integer> m2 = new HashMap<>();
        Deque<Integer> q1 = new ArrayDeque<>();
        Deque<Integer> q2 = new ArrayDeque<>();
        m1.put(start, 0);
        m2.put(goal, 0);
        q1.offer(start);
        q2.offer(goal);
        while (!q1.isEmpty() && !q2.isEmpty()) {
            int t = q1.size() <= q2.size() ? extend(m1, m2, q1) : extend(m2, m1, q2);
            if (t != -1) {
                return t;
            }
        }
        return -1;
    }

    private int extend(Map<Integer, Integer> m1, Map<Integer, Integer> m2, Deque<Integer> q) {
        for (int i = q.size(); i > 0; --i) {
            int x = q.poll();
            int step = m1.get(x);
            for (int y : next(x)) {
                if (m1.containsKey(y)) {
                    continue;
                }
                if (m2.containsKey(y)) {
                    return step + 1 + m2.get(y);
                }
                if (y >= 0 && y <= 1000) {
                    m1.put(y, step + 1);
                    q.offer(y);
                }
            }
        }
        return -1;
    }

    private List<Integer> next(int x) {
        List<Integer> res = new ArrayList<>();
        for (int num : nums) {
            res.add(x + num);
            res.add(x - num);
            res.add(x ^ num);
        }
        return res;
    }
}