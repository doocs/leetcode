/**
 * // This is the FontInfo's API interface.
 * // You should not implement it, or speculate about its implementation
 * function FontInfo() {
 *
 *		@param {number} fontSize
 *		@param {char} ch
 *     	@return {number}
 *     	this.getWidth = function(fontSize, ch) {
 *      	...
 *     	};
 *
 *		@param {number} fontSize
 *     	@return {number}
 *     	this.getHeight = function(fontSize) {
 *      	...
 *     	};
 * };
 */
/**
 * @param {string} text
 * @param {number} w
 * @param {number} h
 * @param {number[]} fonts
 * @param {FontInfo} fontInfo
 * @return {number}
 */
var maxFont = function (text, w, h, fonts, fontInfo) {
    const check = function (size) {
        if (fontInfo.getHeight(size) > h) {
            return false;
        }
        let width = 0;
        for (const c of text) {
            width += fontInfo.getWidth(size, c);
        }
        return width <= w;
    };
    let left = 0;
    let right = fonts.length - 1;
    while (left < right) {
        const mid = (left + right + 1) >> 1;
        if (check(fonts[mid])) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    return check(fonts[left]) ? fonts[left] : -1;
};
