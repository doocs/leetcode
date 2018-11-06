## 与所有单词相关联的字串
### 题目描述
给定一个字符串 s 和一些长度相同的单词 words。在 s 中找出可以恰好串联 words 中所有单词的子串的起始位置。

注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。

```
示例 1:

输入:
  s = "barfoothefoobarman",
  words = ["foo","bar"]
输出: [0,9]
解释: 从索引 0 和 9 开始的子串分别是 "barfoor" 和 "foobar" 。
输出的顺序不重要, [9,0] 也是有效答案。
示例 2:

输入:
  s = "wordgoodstudentgoodword",
  words = ["word","good","good"] 
  (ps:原题的例子为 words = ["word","student"] 和题目描述不符，这里私自改了一下)
输出: []
```

### 解法
1. 用 HashMap< 单词, 出现次数 > map 来存储所有单词；
2. 设单词数量为 N ，每个单词长度为 len，则我们只需要对比到 **str.length() - N \* len** ,
再往后因为不足 N \* len 个字母，肯定不匹配；
3. 每次从 str 中选取连续的 N \* len 个字母进行匹配时，**从后向前匹配**，因为若后面的单词不匹配，
无论前面的单词是否匹配，当前选取的字串一定不匹配，且，最后一个匹配的单词前的部分一定不在匹配的字串中，
这样下一次选取长度为 N \* len 的字串时，可以**从上次匹配比较中最后一个匹配的单词开始**，减少了比较的次数；
4. 考虑到要点 3 中对前一次匹配结果的利用，遍历 str 时，采用间隔为 len 的形式。
例如示例 1 ，遍历顺序为：(0 3 6 9 12 15) (1 4 7 10 13)(2 5 8 11 14) 
 

```java
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
		
        List<Integer> re = new ArrayList<>();

        if(s == null || words == null || words.length == 0 || words[0] == null) {
            return re;
        }
        if(s.length() == 0 || words[0].length() == 0 || s.length() < words.length * words[0].length()) {
            return re;
        }
		// 用< 单词，出现次数 > 来存储 words 中的元素，方便查找
        HashMap<String,Integer> map = new HashMap();
        for (String string : words) {
            map.put(string, map.getOrDefault(string,0) + 1);
        }
        int len = words[0].length();
        int strLen = s.length();
        int lastStart = len * words.length - len;

        for (int i = 0; i < len; i++) {
            for (int j = i; j <= strLen - len - lastStart; j += len) {
                String tempStr = s.substring(j, j + len);
                if(map.containsKey(tempStr)) {                    
                    HashMap<String,Integer> searched = new HashMap<>();
					// 从后向前依次对比
					int tempIndex = j + lastStart;
                    String matchedStr = s.substring(tempIndex, tempIndex + len);
                    while (tempIndex >= j && map.containsKey(matchedStr)) {
                        // 正确匹配到单词
                        if(searched.getOrDefault(matchedStr,0) < map.get(matchedStr)) {
                            searched.put(matchedStr, searched.getOrDefault(matchedStr,0) + 1);
                        }
                        else {
                            break;
                        }
                        tempIndex -= len;
                        if(tempIndex < j) {
                            break;
                        }
                        matchedStr = s.substring(tempIndex, tempIndex + len);
                    }
					// 完全匹配所以单词
                    if(j > tempIndex) {
                        re.add(j);
                    }
					// 从tempIndex 到 tempIndex + len 这个单词不能正确匹配
                    else {
                        j = tempIndex;
                    }
                }
            }
        }
        return re;
    }
}
```