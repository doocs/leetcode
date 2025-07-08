class Solution {
    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        List<Integer> idx = new ArrayList<>();
        Set<String> bs
            = new HashSet<>(Arrays.asList("electronics", "grocery", "pharmacy", "restaurant"));

        for (int i = 0; i < code.length; i++) {
            if (isActive[i] && bs.contains(businessLine[i]) && check(code[i])) {
                idx.add(i);
            }
        }

        idx.sort((i, j) -> {
            int cmp = businessLine[i].compareTo(businessLine[j]);
            if (cmp != 0) {
                return cmp;
            }
            return code[i].compareTo(code[j]);
        });

        List<String> ans = new ArrayList<>();
        for (int i : idx) {
            ans.add(code[i]);
        }
        return ans;
    }

    private boolean check(String s) {
        if (s.isEmpty()) {
            return false;
        }
        for (char c : s.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != '_') {
                return false;
            }
        }
        return true;
    }
}
