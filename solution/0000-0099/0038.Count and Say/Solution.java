class Solution {
    public String countAndSay(int n) {
        String one = "1";
        while (n > 1) {
            one = say(one);
            n--;
        }
        return one;
    }

    private String say(String las) {
        StringBuilder sBuilder = new StringBuilder();
        int l = 1;
        for (int i = 0; i < las.length(); i++) {
            if (i < las.length() - 1 && las.charAt(i) == las.charAt(i + 1)) l++;
            else {
                sBuilder.append(l).append(las.charAt(i));
                l = 1;
            }
        }
        return sBuilder.toString();
    }
}