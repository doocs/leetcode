class Solution {
    public List<String> letterCombinations(String digits) {
        char[] cs = digits.toCharArray();
        List<String> result = new ArrayList<>();
        for (char a : cs) {
            char[] charArray;
            switch (a) {
                case '2': charArray = new char[]{'a','b','c'}; break;
                case '3': charArray = new char[]{'d','e','f'}; break;
                case '4': charArray = new char[]{'g','h','i'}; break;
                case '5': charArray = new char[]{'j','k','l'}; break;
                case '6': charArray = new char[]{'m','n','o'}; break;
                case '7': charArray = new char[]{'p','q','r','s'}; break;
                case '8': charArray = new char[]{'t','u','v'}; break;
                case '9': charArray = new char[]{'w','x','y','z'}; break;
                default: return null;
            }
            if (result.size() == 0) {
                for (char aCharArray : charArray) result.add(String.valueOf(aCharArray));
            } else {
                List<String> cache = new ArrayList<>();
                for (String string : result) {
                    for (char aCharArray : charArray) cache.add(string + aCharArray);
                }
                result = cache;
            }
        }
        return result;
    }
}
