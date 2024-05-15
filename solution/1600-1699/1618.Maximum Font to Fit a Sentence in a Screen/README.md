---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1618.Maximum%20Font%20to%20Fit%20a%20Sentence%20in%20a%20Screen/README.md
tags:
    - 数组
    - 字符串
    - 二分查找
    - 交互
---

# [1618. 找出适应屏幕的最大字号 🔒](https://leetcode.cn/problems/maximum-font-to-fit-a-sentence-in-a-screen)

[English Version](/solution/1600-1699/1618.Maximum%20Font%20to%20Fit%20a%20Sentence%20in%20a%20Screen/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串&nbsp;<code>text</code>。并能够在 宽为 <code>w</code> 高为 <code>h</code> 的屏幕上显示该文本。</p>

<p>字体数组中包含按<strong>升序排列</strong>的可用字号，您可以从该数组中选择任何字体大小。</p>

<p>您可以使用<code>FontInfo</code>接口来获取任何<strong>可用字体大小</strong>的任何字符的宽度和高度。</p>

<p><code>FontInfo</code>接口定义如下：</p>

<pre>
interface FontInfo {
  // 返回 fontSize 大小的字符 ch 在屏幕上的宽度。
  // 每调用该函数复杂度为 O(1)
  public int getWidth(int fontSize, char ch);

  // 返回 fontSize 大小的任意字符在屏幕上的高度。
  // 每调用该函数复杂度为 O(1)
  public int getHeight(int fontSize);
}</pre>

<p>一串字符的文本宽度应该是 <strong>每一个字符 </strong>在对应字号<code>(fontSize)</code>下返回的宽度<code>getWidth(fontSize, text[i])</code>的 <strong>总和 </strong>。对应字号的文本高度可由 <code>getHeight(fontSize)</code> 计算得到。</p>

<p><strong>请注意：文本最多只能排放一排</strong></p>

<p>如果使用相同的参数调用 <code>getHeight</code>&nbsp;或&nbsp;<code>getWidth</code> ，则可以保证 <code>FontInfo</code> 将返回相同的值。</p>

<p>同时，对于任何字体大小的&nbsp;<code>fontSize</code> 和任何字符 <code>ch</code> ：</p>

<ul>
	<li><code>getHeight(fontSize) &lt;= getHeight(fontSize+1)</code></li>
	<li><code>getWidth(fontSize, ch) &lt;= getWidth(fontSize+1, ch)</code></li>
</ul>

<p>返回可用于在屏幕上显示文本的最大字体大小。<strong>如果文本不能以任何字体大小显示，则返回-1</strong>。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> text = "helloworld", w = 80, h = 20, fonts = [6,8,10,12,14,16,18,24,36]
<strong>输出:</strong> 6
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入:</strong> text = "leetcode", w = 1000, h = 50, fonts = [1,2,4]
<strong>输出:</strong> 4
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入:</strong> text = "easyquestion", w = 100, h = 100, fonts = [10,15,20,25]
<strong>输出:</strong> -1
</pre>

<p>&nbsp;</p>

<p><strong>注意:</strong></p>

<ul>
	<li><code>1 &lt;= text.length &lt;= 50000</code></li>
	<li><code>text</code> 只包含小写字母</li>
	<li><code>1 &lt;= w &lt;= 10<sup>7</sup></code></li>
	<li><code>1 &lt;= h &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= fonts.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= fonts[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>fonts&nbsp;</code>已经按升序排序，且不包含重复项。</li>
</ul>

## 解法

### 方法一：二分查找

根据题目描述，字体数组按升序排列。因此，我们可以二分枚举字体大小 `fontSize`，找到最大的并且能够在屏幕上显示文本字体大小即可。

时间复杂度 $O(m\log n)$。其中 $m$, $n$ 为文本 `text` 的长度以及字体大小 `fonts` 个数。

关于二分查找，见[整数二分算法模板 2](https://github.com/doocs/leetcode/blob/main/basic/searching/BinarySearch/README.md)。

<!-- tabs:start -->

```python
# """
# This is FontInfo's API interface.
# You should not implement it, or speculate about its implementation
# """
# class FontInfo(object):
#    Return the width of char ch when fontSize is used.
#    def getWidth(self, fontSize, ch):
#        """
#        :type fontSize: int
#        :type ch: char
#        :rtype int
#        """
#
#    def getHeight(self, fontSize):
#        """
#        :type fontSize: int
#        :rtype int
#        """
class Solution:
    def maxFont(
        self, text: str, w: int, h: int, fonts: List[int], fontInfo: 'FontInfo'
    ) -> int:
        def check(size):
            if fontInfo.getHeight(size) > h:
                return False
            return sum(fontInfo.getWidth(size, c) for c in text) <= w

        left, right = 0, len(fonts) - 1
        ans = -1
        while left < right:
            mid = (left + right + 1) >> 1
            if check(fonts[mid]):
                left = mid
            else:
                right = mid - 1
        return fonts[left] if check(fonts[left]) else -1
```

```java
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
            if (check(text, fonts[mid], w, h, fontInfo)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return check(text, fonts[left], w, h, fontInfo) ? fonts[left] : -1;
    }

    private boolean check(String text, int size, int w, int h, FontInfo fontInfo) {
        if (fontInfo.getHeight(size) > h) {
            return false;
        }
        int width = 0;
        for (char c : text.toCharArray()) {
            width += fontInfo.getWidth(size, c);
        }
        return width <= w;
    }
}
```

```cpp
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
```

```js
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
```

<!-- tabs:end -->

<!-- end -->
