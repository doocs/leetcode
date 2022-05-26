class Solution {
    private int[] counter;
    private char[][] board;
    
    public List<String> findWords(char[][] board, String[] words) {
        counter = new int[26];
        this.board = board;
        for (char[] b : board) {
            for (char c : b) {
                ++counter[c - 'a'];
            }
        }
        Set<String> s = new HashSet<>(Arrays.asList(words));
        List<String> ans = new ArrayList<>();
        for (String word : s) {
            if (find(word)) {
                ans.add(word);
            }
        }
        return ans;
    }

    private boolean find(String word) {
        if (!check(word)) {
            return false;
        }
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (dfs(i, j, 0, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int i, int j, int l, String word) {
        if (l == word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(l)) {
            return false;
        }
        char c = board[i][j];
        board[i][j] = '0';
        boolean ans = false;
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            ans = ans || dfs(x, y, l + 1, word);
        }
        board[i][j] = c;
        return ans;
    }

    private boolean check(String word) {
        int[] cnt = new int[26];
        for (char c : word.toCharArray()) {
            ++cnt[c - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            if (counter[i] < cnt[i]) {
                return false;
            }
        }
        return true;
    }
}