# [1618. Maximum Font to Fit a Sentence in a Screen](https://leetcode.com/problems/maximum-font-to-fit-a-sentence-in-a-screen)

[中文文档](/solution/1600-1699/1618.Maximum%20Font%20to%20Fit%20a%20Sentence%20in%20a%20Screen/README.md)

## Description

<p>You are given a string <code>text</code>. We want to display <code>text</code> on a screen of width <code>w</code> and height <code>h</code>. You can choose any font size from array <code>fonts</code>, which contains the available font sizes <strong>in ascending order</strong>.</p>

<p>You can use the <code>FontInfo</code> interface to get the width and height of any character at any available font size.</p>

<p>The <code>FontInfo</code> interface is defined as such:</p>

<pre>

interface FontInfo {

  // Returns the width of character ch on the screen using font size fontSize.

  // O(1) per call

  public int getWidth(int fontSize, char ch);



  // Returns the height of any character on the screen using font size fontSize.

  // O(1) per call

  public int getHeight(int fontSize);

}</pre>

<p>The calculated width of <code>text</code> for some <code>fontSize</code> is the <strong>sum</strong> of every <code>getWidth(fontSize, text[i])</code> call for each <code>0 &lt;= i &lt; text.length</code> (<strong>0-indexed</strong>). The calculated height of <code>text</code> for some <code>fontSize</code> is <code>getHeight(fontSize)</code>. Note that <code>text</code> is displayed on a <strong>single line</strong>.</p>

<p>It is guaranteed that <code>FontInfo</code> will return the same value if you call <code>getHeight</code> or <code>getWidth</code> with the same parameters.</p>

<p>It is also guaranteed that for any font size <code>fontSize</code> and any character <code>ch</code>:</p>

<ul>
    <li><code>getHeight(fontSize) &lt;= getHeight(fontSize+1)</code></li>
    <li><code>getWidth(fontSize, ch) &lt;= getWidth(fontSize+1, ch)</code></li>

</ul>

<p>Return <em>the maximum font size you can use to display </em><code>text</code><em> on the screen</em>. If <code>text</code> cannot fit on the display with any font size, return <code>-1</code>.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> text = &quot;helloworld&quot;, w = 80, h = 20, fonts = [6,8,10,12,14,16,18,24,36]

<strong>Output:</strong> 6

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> text = &quot;leetcode&quot;, w = 1000, h = 50, fonts = [1,2,4]

<strong>Output:</strong> 4

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> text = &quot;easyquestion&quot;, w = 100, h = 100, fonts = [10,15,20,25]

<strong>Output:</strong> -1

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
    <li><code>1 &lt;= text.length &lt;= 50000</code></li>
    <li><code>text</code> contains only lowercase English letters.</li>
    <li><code>1 &lt;= w &lt;= 10<sup>7</sup></code></li>
    <li><code>1 &lt;= h &lt;= 10<sup>4</sup></code></li>
    <li><code>1 &lt;= fonts.length &lt;= 10<sup>5</sup></code></li>
    <li><code>1 &lt;= fonts[i] &lt;= 10<sup>5</sup></code></li>
    <li><code>fonts</code> is sorted in ascending order and does not contain duplicates.</li>

</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
        def check(text, fontSize, w, h, fontInfo) -> bool:
            if fontInfo.getHeight(fontSize) > h:
                return False
            width = 0
            for ch in text:
                width += fontInfo.getWidth(fontSize, ch)
                if width > w:
                    return False
            return True

        left, right = 0, len(fonts) - 1
        while left < right:
            mid = (left + right + 1) >> 1
            fontSize = fonts[mid]
            if check(text, fontSize, w, h, fontInfo):
                left = mid
            else:
                right = mid - 1
        return fonts[left] if check(text, fonts[left], w, h, fontInfo) else -1
```

### **Java**

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
```

### **C++**

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
```

### **...**

```

```

<!-- tabs:end -->
