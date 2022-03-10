class Solution {
    private Set<String> words;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        words = new HashSet<>(wordList);
        if (!words.contains(endWord)) {
            return 0;
        }
        Queue<String> q1 = new ArrayDeque<>();
        Queue<String> q2 = new ArrayDeque<>();
        Map<String, Integer> m1 = new HashMap<>();
        Map<String, Integer> m2 = new HashMap<>();
        q1.offer(beginWord);
        q2.offer(endWord);
        m1.put(beginWord, 0);
        m2.put(endWord, 0);
        while (!q1.isEmpty() && !q2.isEmpty()) {
            int t = q1.size() <= q2.size() ? extend(m1, m2, q1) : extend(m2, m1, q2);
            if (t != -1) {
                return t + 1;
            }
        }
        return 0;
    }

    private int extend(Map<String, Integer> m1, Map<String, Integer> m2, Queue<String> q) {
        for (int i = q.size(); i > 0; --i) {
            String s = q.poll();
            int step = m1.get(s);
            char[] chars = s.toCharArray();
            for (int j = 0; j < chars.length; ++j) {
                char ch = chars[j];
                for (char k = 'a'; k <= 'z'; ++k) {
                    chars[j] = k;
                    String t = new String(chars);
                    if (!words.contains(t) || m1.containsKey(t)) {
                        continue;
                    }
                    if (m2.containsKey(t)) {
                        return step + 1 + m2.get(t);
                    }
                    q.offer(t);
                    m1.put(t, step + 1);
                }
                chars[j] = ch;
            }
        }
        return -1;
    }
}