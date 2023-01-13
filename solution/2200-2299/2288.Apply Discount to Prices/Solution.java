class Solution {
    public String discountPrices(String sentence, int discount) {
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; ++i) {
            if (check(words[i])) {
                double t = Long.parseLong(words[i].substring(1)) * (1 - discount / 100.0);
                words[i] = String.format("$%.2f", t);
            }
        }
        return String.join(" ", words);
    }

    private boolean check(String s) {
        if (s.charAt(0) != '$' || s.length() == 1) {
            return false;
        }
        for (int i = 1; i < s.length(); ++i) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}