class Solution {
    public String[] findWords(String[] words) {
        String s1 = "qwertyuiopQWERTYUIOP";
        String s2 = "asdfghjklASDFGHJKL";
        String s3 = "zxcvbnmZXCVBNM";
        List<String> res = new ArrayList<>();
        for (String word : words) {
            int n1 = 0, n2 = 0, n3 = 0;
            int n = word.length();
            for (int i = 0; i < n; ++i) {
                if (s1.contains(String.valueOf(word.charAt(i)))) {
                    ++n1;
                } else if (s2.contains(String.valueOf(word.charAt(i)))) {
                    ++n2;
                } else {
                    ++n3;
                }
            }
            if (n1 == n || n2 == n || n3 == n) {
                res.add(word);
            }
        }
        return res.toArray(new String[0]);
    }
}