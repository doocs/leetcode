class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int n = words.length;
        for (int i = 0; i < n; ) {
            List<String> t = new ArrayList<>();
            int cnt = words[i].length();
            t.add(words[i++]);
            while (i < n && cnt + 1 + words[i].length() <= maxWidth) {
                cnt += 1 + words[i].length();
                t.add(words[i++]);
            }
            if (i == n || t.size() == 1) {
                // this is the last line or only one word in a line
                String left = String.join(" ", t);
                String right = " ".repeat(maxWidth - left.length());
                ans.add(left + right);
                if (i == n) {
                    break;
                }
                continue;
            }

            int wordsWidth = cnt - t.size() + 1;
            int spaceWidth = maxWidth - wordsWidth;
            List<String> spaces = partition(spaceWidth, t.size() - 1);
            StringBuilder sb = new StringBuilder(t.get(0));
            for (int j = 0; j < t.size() - 1; ++j) {
                sb.append(spaces.get(j));
                sb.append(t.get(j + 1));
            }
            ans.add(sb.toString());
        }
        return ans;
    }

    private List<String> partition(int n, int cnt) {
        List<String> ans = new ArrayList<>();
        int base = n / cnt;
        int mod = n % cnt;
        for (int i = 0, j = 0; i < cnt; ++i, ++j) {
            StringBuilder sb = new StringBuilder(" ".repeat(base));
            if (j < mod) {
                sb.append(' ');
            }
            ans.add(sb.toString());
        }
        return ans;
    }
}