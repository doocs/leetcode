/**
 * // This is the FontInfo's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface FontInfo {
 *     // Return the width of char ch when fontSize is used.
 *     public int getWidth(int fontSize, char ch) {}
 *     // Return Height of any char when fontSize is used.
 *     public int getHeight(int fontSize)
 * }
 */
class Solution {
    public int maxFont(String text, int w, int h, int[] fonts, FontInfo fontInfo) {
        int left = 0, right = fonts.length - 1;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            int fontSize = fonts[mid];
            if (check(text, fontSize, w, h, fontInfo)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return check(text, fonts[left], w, h, fontInfo) ? fonts[left] : -1;
    }

    private boolean check(String s, int fontSize, int w, int h, FontInfo fontInfo) {
        if (fontInfo.getHeight(fontSize) > h) {
            return false;
        }
        int width = 0;
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            width += fontInfo.getWidth(fontSize, ch);
            if (width > w) {
                return false;
            }
        }
        return true;
    }
}