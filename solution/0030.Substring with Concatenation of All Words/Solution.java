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