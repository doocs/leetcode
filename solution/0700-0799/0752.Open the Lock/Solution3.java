class Solution {
    private String target;

    public int openLock(String[] deadends, String target) {
        if ("0000".equals(target)) {
            return 0;
        }
        String start = "0000";
        this.target = target;
        Set<String> s = new HashSet<>();
        for (String d : deadends) {
            s.add(d);
        }
        if (s.contains(start)) {
            return -1;
        }
        PriorityQueue<Pair<Integer, String>> q
            = new PriorityQueue<>(Comparator.comparingInt(Pair::getKey));
        q.offer(new Pair<>(f(start), start));
        Map<String, Integer> dist = new HashMap<>();
        dist.put(start, 0);
        while (!q.isEmpty()) {
            String state = q.poll().getValue();
            int step = dist.get(state);
            if (target.equals(state)) {
                return step;
            }
            for (String t : next(state)) {
                if (s.contains(t)) {
                    continue;
                }
                if (!dist.containsKey(t) || dist.get(t) > step + 1) {
                    dist.put(t, step + 1);
                    q.offer(new Pair<>(step + 1 + f(t), t));
                }
            }
        }
        return -1;
    }

    private int f(String s) {
        int ans = 0;
        for (int i = 0; i < 4; ++i) {
            int a = s.charAt(i) - '0';
            int b = target.charAt(i) - '0';
            if (a > b) {
                int t = a;
                a = b;
                b = a;
            }
            ans += Math.min(b - a, a + 10 - b);
        }
        return ans;
    }

    private List<String> next(String t) {
        List res = new ArrayList<>();
        char[] chars = t.toCharArray();
        for (int i = 0; i < 4; ++i) {
            char c = chars[i];
            chars[i] = c == '0' ? '9' : (char) (c - 1);
            res.add(String.valueOf(chars));
            chars[i] = c == '9' ? '0' : (char) (c + 1);
            res.add(String.valueOf(chars));
            chars[i] = c;
        }
        return res;
    }
}