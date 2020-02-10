class Solution {

    public List<String> letterCombinations(String digits) {
        Map<String, String> map = new HashMap<>();
        for (int i = 2, index = 0; i < 7; i ++) {
            for (int j = 0; j < 3; j ++) {
                map.put(String.valueOf(i) + String.valueOf(j), (char)('a' + (index++)) + "");
            }
        }
        map.put("70", "p");
        map.put("71", "q");
        map.put("72", "r");
        map.put("73", "s");

        map.put("80", "t");
        map.put("81", "u");
        map.put("82", "v");

        map.put("90", "w");
        map.put("91", "x");
        map.put("92", "y");
        map.put("93", "z");
        List<String> res = new ArrayList<>();
        if (digits.length() > 0) {
            solution(res, map, digits, "", 0);
        }
        return res;
    }

    private void solution(List<String> res, Map<String, String> map, String digits, String target, int index) {
        if (index == digits.length()) {
            res.add(target);
            return;
        }
        String snet = String.valueOf(digits.charAt(index));
        int length = ("7".equals(snet) || "9".equals(snet)) ? 4 : 3;
        for (int i = 0; i < length; i++) {
            solution(res, map, digits, target + map.get(snet + i), index + 1);
        }
    }
}