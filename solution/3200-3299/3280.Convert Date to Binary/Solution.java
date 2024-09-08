class Solution {
    public String convertDateToBinary(String date) {
        List<String> ans = new ArrayList<>();
        for (var s : date.split("-")) {
            int x = Integer.parseInt(s);
            ans.add(Integer.toBinaryString(x));
        }
        return String.join("-", ans);
    }
}
