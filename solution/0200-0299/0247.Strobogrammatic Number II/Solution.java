class Solution {
    Map<Character, Character> map = new HashMap<>();
    {
        map.put('1', '1');
        map.put('0', '0');
        map.put('6', '9');
        map.put('9', '6');
        map.put('8', '8');
    }

    public List<String> findStrobogrammatic(int n) {
        if (n == 1) {
            return Arrays.asList("0", "1", "8");
        }
        List<String> ans = new ArrayList<>();
        dfs(n, ans, "");
        return ans;
    }

    private void dfs(int n, List<String> ans, String tmp) {
        if (tmp.length() == (n + 1) / 2) {
            fillAns(n, ans, tmp);
            return;
        }

        for (char c : map.keySet()) {
            int num = c - '0';
            // 首位不能是0
            if (tmp.length() == 0 && num == 0) {
                continue;
            }
            // 奇数的中间位只能是 0 1 8
            if (n % 2 != 0 && tmp.length() == n / 2 && !(num == 0 || num == 1 || num == 8)) {
                continue;
            }
            dfs(n, ans, tmp + num);
        }
    }

    private void fillAns(int n, List<String> ans, String tmp) {
        char[] a = new char[n];
        for (int i = 0; i < tmp.length(); i++) {
            a[i] = tmp.charAt(i);
            a[n - i - 1] = map.get(tmp.charAt(i));
        }
        if (n % 2 != 0) {
            a[tmp.length() - 1] = tmp.charAt(tmp.length() - 1);
        }
        ans.add(new String(a));
    }
}
