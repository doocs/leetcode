class Solution {
    Map<String,String> map;

    public Solution() {
        map = new HashMap();
        map.put("(", ")");
        map.put(")", "(");
        map.put("{", "}");
        map.put("}", "{");
        map.put("[", "]");
        map.put("]", "[");
    }

    public boolean isValid(String s) {
        Stack<String> stack = new Stack();
        for (int i = 0; i < s.length(); i ++) {
            if (!stack.isEmpty() && map.get(String.valueOf(s.charAt(i))).equals(stack.peek())) {
                stack.pop();
            } else {
                stack.push(String.valueOf(s.charAt(i)));
            }
        }
        return stack.isEmpty();
    }
}