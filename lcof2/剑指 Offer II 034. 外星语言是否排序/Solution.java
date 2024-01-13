class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int[] index = new int[26];
        for (int i = 0; i < index.length; ++i) {
            index[order.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < words.length - 1; ++i) {
            String w1 = words[i];
            String w2 = words[i + 1];
            int l1 = w1.length(), l2 = w2.length();
            for (int j = 0; j < Math.max(l1, l2); ++j) {
                int i1 = j >= l1 ? -1 : index[w1.charAt(j) - 'a'];
                int i2 = j >= l2 ? -1 : index[w2.charAt(j) - 'a'];
                if (i1 > i2) {
                    // 说明不是按字典序排序，直接返回False
                    return false;
                }
                if (i1 < i2) {
                    // 说明当前两单词是按字典序排序，无需再往下进行循环比较
                    break;
                }
            }
        }
        return true;
    }
}