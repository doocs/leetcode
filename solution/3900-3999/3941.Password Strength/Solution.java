class Solution {
    public int passwordStrength(String password) {
        var st = password.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toSet());

        int ans = 0;

        for (char ch : st) {
            if (Character.isLowerCase(ch)) {
                ans += 1;
            } else if (Character.isUpperCase(ch)) {
                ans += 2;
            } else if (Character.isDigit(ch)) {
                ans += 3;
            } else {
                ans += 5;
            }
        }

        return ans;
    }
}