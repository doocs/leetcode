class Solution {
    public String reformatNumber(String number) {
        number = number.replace("-", "").replace(" ", "");
        int n = number.length();
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < n / 3; ++i) {
            ans.add(number.substring(i * 3, i * 3 + 3));
        }
        if (n % 3 == 1) {
            ans.set(ans.size() - 1, ans.get(ans.size() - 1).substring(0, 2));
            ans.add(number.substring(n - 2));
        } else if (n % 3 == 2) {
            ans.add(number.substring(n - 2));
        }
        return String.join("-", ans);
    }
}