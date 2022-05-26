class Solution {
    public int openLock(String[] deadends, String target) {
        Set<String> s = new HashSet<>(Arrays.asList(deadends));
        if (s.contains(target) || s.contains("0000")) {
            return -1;
        }
        if (Objects.equals(target, "0000")) {
            return 0;
        }
        Set<String> visited = new HashSet<>();
        Deque<String> q = new ArrayDeque<>();
        q.offerLast("0000");
        int step = 0;
        while (!q.isEmpty()) {
            ++step;
            for (int i = 0, n = q.size(); i < n; ++i) {
                String status = q.pollFirst();
                for (String t : get(status)) {
                    if (visited.contains(t) || s.contains(t)) {
                        continue;
                    }
                    if (Objects.equals(t, target)) {
                        return step;
                    }
                    q.offerLast(t);
                    visited.add(t);
                }
            }
        }
        return -1;
    }

    private char prev(char c)  {
        return c == '0' ? '9' : (char) (c - 1);
    }

    private char next(char c) {
        return c == '9' ? '0' : (char) (c + 1);
    }

    private List<String> get(String t) {
        List res = new ArrayList<>();
        char[] chars = t.toCharArray();
        for (int i = 0; i < 4; ++i) {
            char c = chars[i];
            chars[i] = prev(c);
            res.add(String.valueOf(chars));
            chars[i] = next(c);
            res.add(String.valueOf(chars));
            chars[i] = c;
        }
        return res;
    }
}