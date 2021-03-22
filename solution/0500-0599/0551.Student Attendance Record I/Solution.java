class Solution {
    public boolean checkRecord(String s) {
        int i = s.indexOf("A");
        return (i == -1 || s.lastIndexOf("A") == i) && !s.contains("LLL");
    }
}