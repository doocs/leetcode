class Solution {
    public String clearStars(String s) {
        Deque<Integer>[] g = new Deque[26];
        Arrays.setAll(g, k -> new ArrayDeque<>());
        int n = s.length();
        boolean[] rem = new boolean[n];
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '*') {
                rem[i] = true;
                for (int j = 0; j < 26; ++j) {
                    if (!g[j].isEmpty()) {
                        rem[g[j].pop()] = true;
                        break;
                    }
                }
            } else {
                g[s.charAt(i) - 'a'].push(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            if (!rem[i]) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}