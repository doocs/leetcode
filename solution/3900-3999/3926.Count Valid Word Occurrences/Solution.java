class Solution {
    public int[] countWordOccurrences(String[] chunks, String[] queries) {
        StringBuilder sb = new StringBuilder();
        for (String chunk : chunks) {
            sb.append(chunk);
        }
        String s = sb.toString();
        int n = s.length();
        Map<String, Integer> cnt = new HashMap<>();
        int i = 0;
        while (i < n) {
            char c = s.charAt(i);
            if (c == ' ' || c == '-') {
                i++;
                continue;
            }
            int j = i;
            while (j < n) {
                char cj = s.charAt(j);
                if (cj == ' ') {
                    break;
                }
                if (cj == '-') {
                    if (j + 1 < n) {
                        char cnext = s.charAt(j + 1);
                        if (cnext == ' ' || cnext == '-') {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                j++;
            }
            String word = s.substring(i, j);
            cnt.put(word, cnt.getOrDefault(word, 0) + 1);
            i = j;
        }
        int[] ans = new int[queries.length];
        for (int k = 0; k < queries.length; k++) {
            ans[k] = cnt.getOrDefault(queries[k], 0);
        }
        return ans;
    }
}
