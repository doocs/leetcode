class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        
        // 需要转hashSet
        Set<String> wordSet = new HashSet<>(wordList);
        queue.offer(beginWord);
        int level = 1;
        int curNum = 1;
        int nextNum = 0;
        while (!queue.isEmpty()) {
            String s = queue.poll();
            --curNum;
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; ++i) {
                char ch = chars[i];
                for (char j = 'a'; j <= 'z'; ++j) {
                    chars[i] = j;
                    String tmp = new String(chars);
                    // 字典中包含生成的中间字符串
                    if (wordSet.contains(tmp)) {
                        // 中间字符串与 endWord 相等
                        if (endWord.equals(tmp)) {
                            return level + 1;
                        }

                        // 中间字符串不是 endWord，则入队
                        queue.offer(tmp);
                        ++nextNum;

                        // 确保之后不会再保存 tmp 字符串
                        wordSet.remove(tmp);
                    }
                }
                chars[i] = ch;
            }

            if (curNum == 0) {
                curNum = nextNum;
                nextNum = 0;
                ++level;
            }

        }
        
        return 0;
    }
}
