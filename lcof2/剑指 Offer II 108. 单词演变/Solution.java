class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        int ans = 1;
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; --i) {
                String s = q.poll();
                char[] chars = s.toCharArray();
                for (int j = 0; j < chars.length; ++j) {
                    char ch = chars[j];
                    for (char k = 'a'; k <= 'z'; ++k) {
                        chars[j] = k;
                        String t = new String(chars);
                        if (!words.contains(t)) {
                            continue;
                        }
                        if (endWord.equals(t)) {
                            return ans + 1;
                        }
                        q.offer(t);
                        words.remove(t);
                    }
                    chars[j] = ch;
                }
            }
            ++ans;
        }
        return 0;
    }
}