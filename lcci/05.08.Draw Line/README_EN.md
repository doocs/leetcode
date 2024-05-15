---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/05.08.Draw%20Line/README_EN.md
---

# [05.08. Draw Line](https://leetcode.cn/problems/draw-line-lcci)

[中文文档](/lcci/05.08.Draw%20Line/README.md)

## Description

<p>A monochrome screen is stored as a single array of int, allowing 32 consecutive pixels to be stored in one int. The screen has width <code>w</code>, where <code>w</code> is divisible by 32&nbsp;(that is, no byte will be split across rows). The height of the screen, of course, can be derived from the length of the array and the width. Implement a function that draws a horizontal line from <code>(x1, y)</code> to <code>(x2, y)</code>.</p>
<p>Given the length of the array, the width of the array (in bit), start position <code>x1</code>&nbsp;(in bit) of the line, end position <code>x2</code> (in bit) of the line and the row number&nbsp;<code>y</code> of the line, return the array after drawing.</p>
<p><strong>Example1:</strong></p>
<pre>

<strong> Input</strong>: length = 1, w = 32, x1 = 30, x2 = 31, y = 0

<strong> Output</strong>: [3]

<strong> Explanation</strong>: After drawing a line from (30, 0) to (31, 0), the screen becomes [0b000000000000000000000000000000011].

</pre>
<p><strong>Example2:</strong></p>
<pre>

<strong> Input</strong>: length = 3, w = 96, x1 = 0, x2 = 95, y = 0

<strong> Output</strong>: [-1, -1, -1]

</pre>

## Solutions

### Solution 1: Bit Manipulation

First, we calculate the positions of $x_1$ and $x_2$ in the result array, denoted as $i$ and $j$. Then, we set the elements between $i$ and $j$ to $-1$.

If $x_1 \bmod 32 \neq 0$, we need to set the first $x_1 \bmod 32$ bits of the element at position $i$ to $0$.

If $x_2 \bmod 32 \neq 31$, we need to set the last $31 - x_2 \bmod 32$ bits of the element at position $j$ to $0$.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def drawLine(self, length: int, w: int, x1: int, x2: int, y: int) -> List[int]:
        ans = [0] * length
        i = (y * w + x1) // 32
        j = (y * w + x2) // 32
        for k in range(i, j + 1):
            ans[k] = -1
        ans[i] = (ans[i] & 0xFFFFFFFF) >> (x1 % 32) if x1 % 32 else -1
        ans[j] &= -0x80000000 >> (x2 % 32)
        return ans
```

```java
class Solution {
    public int[] drawLine(int length, int w, int x1, int x2, int y) {
        int[] ans = new int[length];
        int i = (y * w + x1) / 32;
        int j = (y * w + x2) / 32;
        for (int k = i; k <= j; ++k) {
            ans[k] = -1;
        }
        ans[i] = ans[i] >>> (x1 % 32);
        ans[j] &= 0x80000000 >> (x2 % 32);
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> drawLine(int length, int w, int x1, int x2, int y) {
        vector<int> ans(length);
        int i = (y * w + x1) / 32;
        int j = (y * w + x2) / 32;
        for (int k = i; k <= j; ++k) {
            ans[k] = -1;
        }
        ans[i] = ans[i] & unsigned(-1) >> (x1 % 32);
        ans[j] = ans[j] & unsigned(-1) << (31 - x2 % 32);
        return ans;
    }
};
```

<!-- tabs:end -->

<!-- end -->
