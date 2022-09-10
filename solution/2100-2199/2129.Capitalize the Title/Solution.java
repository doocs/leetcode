class Solution {
    public String capitalizeTitle(String title) {
        List<String> ans = new ArrayList<>();
        for (String s : title.split(" ")) {
            if (s.length() < 3) {
                ans.add(s.toLowerCase());
            } else {
                ans.add(s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase());
            }
        }
        return String.join(" ", ans);
    }
}