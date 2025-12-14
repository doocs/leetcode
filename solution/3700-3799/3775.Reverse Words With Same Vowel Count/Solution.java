class Solution {
    public String reverseWords(String s) {
        String[] words = s.split("\\s+");

        int cnt = calc(words[0]);
        List<String> ans = new ArrayList<>();
        ans.add(words[0]);

        for (int i = 1; i < words.length; i++) {
            String w = words[i];
            if (calc(w) == cnt) {
                ans.add(new StringBuilder(w).reverse().toString());
            } else {
                ans.add(w);
            }
        }
        return String.join(" ", ans);
    }

    private int calc(String w) {
        int res = 0;
        for (char c : w.toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                res++;
            }
        }
        return res;
    }
}
