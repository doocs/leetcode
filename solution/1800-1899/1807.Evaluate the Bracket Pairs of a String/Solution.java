class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> knowledgeDict = new HashMap<>();
        for (List<String> item : knowledge) {
            knowledgeDict.put(item.get(0), item.get(1));
        }
        StringBuilder res = new StringBuilder();
        int i = 0, n = s.length();
        while (i < n) {
            if (s.charAt(i) == '(') {
                int rightBracketPos = findRightBracket(s, i + 1, n);
                String key = s.substring(i + 1, rightBracketPos);
                res.append(knowledgeDict.getOrDefault(key, "?"));
                i = rightBracketPos + 1;
            } else {
                res.append(s.charAt(i));
                i += 1;
            }
        }
        return res.toString();
    }

    private int findRightBracket(String s, int start, int end) {
        for (int i =  start; i < end; ++i) {
            if (s.charAt(i) == ')') {
                return i;
            }
        }
        return -1;
    }
}