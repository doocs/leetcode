class MagicDictionary {
    List<char[]> dict;
    /** Initialize your data structure here. */
    public MagicDictionary() {
        dict = new ArrayList<>();
    }

    public void buildDict(String[] dictionary) {
        for (String item : dictionary) {
            dict.add(item.toCharArray());
        }
    }

    public boolean search(String searchWord) {
        char[] target = searchWord.toCharArray();
        for (char[] item : dict) {
            if (item.length != target.length) {
                continue;
            }
            int diff = 0;
            for (int i = 0; i < target.length; i++) {
                if (target[i] != item[i]) {
                    diff += 1;
                }
            }
            if (diff == 1) {
                return true;
            }
        }
        return false;
    }
}