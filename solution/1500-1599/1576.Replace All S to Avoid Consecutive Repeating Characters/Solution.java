class Solution {
    public String modifyString(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '?') {
                // 前面的字符
                char ahead = i == 0 ? ' ' : chars[i - 1];
                // 后面的字符
                char behind = i == chars.length - 1 ? ' ' : chars[i + 1];
                char temp = 'a';
                while (temp == ahead || temp == behind) {
                    temp++;
                }
                chars[i] = temp;
            }
        }
        return new String(chars);
    }
}