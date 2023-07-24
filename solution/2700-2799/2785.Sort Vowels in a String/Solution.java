class Solution {
    public String sortVowels(String s) {
        List<Character> vs = new ArrayList<>();
        char[] cs = s.toCharArray();
        for (char c : cs) {
            char d = Character.toLowerCase(c);
            if (d == 'a' || d == 'e' || d == 'i' || d == 'o' || d == 'u') {
                vs.add(c);
            }
        }
        Collections.sort(vs);
        for (int i = 0, j = 0; i < cs.length; ++i) {
            char d = Character.toLowerCase(cs[i]);
            if (d == 'a' || d == 'e' || d == 'i' || d == 'o' || d == 'u') {
                cs[i] = vs.get(j++);
            }
        }
        return String.valueOf(cs);
    }
}