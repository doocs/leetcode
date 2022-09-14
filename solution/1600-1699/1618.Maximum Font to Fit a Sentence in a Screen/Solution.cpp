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
        auto check = [&](int size) {
            if (fontInfo.getHeight(size) > h) return false;
            int width = 0;
            for (char& c : text) {
                width += fontInfo.getWidth(size, c);
            }
            return width <= w;
        };
        int left = 0, right = fonts.size() - 1;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (check(fonts[mid])) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return check(fonts[left]) ? fonts[left] : -1;
    }
};
