class Solution {
    public String decodeString(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ']') {
                stack.push(chars[i]);
            } else {
                // 找[]内的内容
                String t = "";
                while (stack.peek() != '[') {
                    t = stack.pop() + t;
                }
                // 弹出[
                stack.pop();
                // 找前面的数字
                String n = "";
                while (!stack.isEmpty() && stack.peek() >= '0' && stack.peek() <= '9') {
                    n = stack.pop() + n;
                }
                int c = Integer.valueOf(n);

                String tmpCombine = "";
                // 把字母重复c次
                for (int j = 0; j < c; j++) {
                    tmpCombine += t;
                }

                // 放回stack
                char[] tmp = tmpCombine.toCharArray();
                for (int j = 0; j < tmp.length; j++) {
                    stack.push(tmp[j]);
                }
            }
        }

        // stack即为结果
        String ans = "";
        while (!stack.isEmpty()) {
            ans = stack.pop() + ans;
        }
        return ans;
    }
}