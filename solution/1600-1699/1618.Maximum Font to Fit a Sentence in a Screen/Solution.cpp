/**
 * // This is the FontInfo's API interface.
 * // You should not implement it, or speculate about its implementation
 * class FontInfo {
 *   public:
 *     // Return the width of char ch when fontSize is used.
 *     int getWidth(int fontSize, char ch);
 *     
 *     // Return Height of any char when fontSize is used.
 *     int getHeight(int fontSize)
 * };
 */
class Solution {
public:
    int maxFont(string text, int w, int h, vector<int>& fonts, FontInfo fontInfo) {
        int left = 0, right = fonts.size() - 1;
        while (left < right) {
            int mid = left + right + 1 >> 1;
            int fontSize = fonts[mid];
            if (check(text, fontSize, w, h, fontInfo)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return check(text, fonts[left], w, h, fontInfo) ? fonts[left] : -1;
    }

private:
    bool check(string s, int fontSize, int w, int h, FontInfo fontInfo) {
        if (fontInfo.getHeight(fontSize) > h) {
            return false;
        }
        int width = 0;
        for (auto ch : s) {
            width += fontInfo.getWidth(fontSize, ch);
            if (width > w) {
                return false;
            }
        }
        return true;
    }
};