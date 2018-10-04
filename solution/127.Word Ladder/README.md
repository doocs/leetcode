## 单词接龙
### 题目描述

给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：

- 每次转换只能改变一个字母。
- 转换过程中的中间单词必须是字典中的单词。

说明:

- 如果不存在这样的转换序列，返回 0。
- 所有单词具有相同的长度。
- 所有单词只由小写字母组成。
- 字典中不存在重复的单词。
- 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。

示例 1:
```
输入:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

输出: 5

解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     返回它的长度 5。
```

示例 2:
```
输入:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

输出: 0

解释: endWord "cog" 不在字典中，所以无法进行转换。
```

### 解法
利用广度优先搜索，`level` 表示层数，`curNum` 表示当前层的单词数，`nextNum` 表示下一层的单词数。
队首元素出队，对于该字符串，替换其中每一个字符为[a...z] 中的任一字符。判断新替换后的字符串是否在字典中。若是，若该字符串与 endWord 相等，直接返回 level + 1；若不等，该字符串入队，nextNum + 1，并将 该字符串从 wordSet 移除。

注意，每次只能替换一个字符，因此，在下一次替换前，需恢复为上一次替换前的状态。

```java
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

```