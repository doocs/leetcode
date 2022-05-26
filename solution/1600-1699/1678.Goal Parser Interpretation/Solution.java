class Solution {
    public String interpret(String command) {
        StringBuilder sb = new StringBuilder();
        int p = 0, q = 1;
        for (; p < command.length(); p++, q++) {
            char c = command.charAt(p);
            if (c == 'G')
                sb.append('G');
            if (c == '(') {
                if (command.charAt(q) == ')') {
                    sb.append("o");
                    p++;
                    q++;
                } else {
                    sb.append("al");
                    p += 2;
                    q += 2;
                }
            }
        }
        return sb.toString();
    }
}