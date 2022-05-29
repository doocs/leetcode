class Solution {
    public String discountPrices(String sentence, int discount) {
        List<String> ans = new ArrayList<>();
        for (String w : sentence.split(" ")) {
            if (w.charAt(0) == '$' && isNumber(w.substring(1))) {
                double t = Long.parseLong(w.substring(1)) * (1 - discount / 100.0);
                ans.add("$" + String.format("%.2f", t));
            } else {
                ans.add(w);
            }
        }
        return String.join(" ", ans);
    }

    private boolean isNumber(String s) {
        if (s == null || "".equals(s)) {
            return false;
        }
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}