---
comment: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/05.08.Draw%20Line/README.md
---

# [面试题 05.08. 绘制直线](https://leetcode.cn/problems/draw-line-lcci)

[English Version](/lcci/05.08.Draw%20Line/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>绘制直线。有个单色屏幕存储在一个一维数组中，使得32个连续像素可以存放在一个 int 里。屏幕宽度为<code>w</code>，且<code>w</code>可被32整除（即一个 int 不会分布在两行上），屏幕高度可由数组长度及屏幕宽度推算得出。请实现一个函数，绘制从点<code>(x1, y)</code>到点<code>(x2, y)</code>的水平线。</p>
<p>给出数组的长度 <code>length</code>，宽度 <code>w</code>（以比特为单位）、直线开始位置 <code>x1</code>（比特为单位）、直线结束位置 <code>x2</code>（比特为单位）、直线所在行数&nbsp;<code>y</code>。返回绘制过后的数组。</p>
<p><strong>示例1:</strong></p>
<pre><strong> 输入</strong>：length = 1, w = 32, x1 = 30, x2 = 31, y = 0
<strong> 输出</strong>：[3]
<strong> 说明</strong>：在第0行的第30位到第31为画一条直线，屏幕表示为[0b000000000000000000000000000000011]
</pre>
<p><strong>示例2:</strong></p>
<pre><strong> 输入</strong>：length = 3, w = 96, x1 = 0, x2 = 95, y = 0
<strong> 输出</strong>：[-1, -1, -1]
</pre>

## 解法

### 方法一：位运算

我们先算出 $x_1$ 和 $x_2$ 在结果数组中的位置，记为 $i$ 和 $j$。然后将 $i$ 到 $j$ 之间的元素置为 $-1$。

如果 $x_1 \bmod 32 \neq 0$，我们需要将 $i$ 位置的元素的前 $x_1 \bmod 32$ 位置为 $0$。

如果 $x_2 \bmod 32 \neq 31$，我们需要将 $j$ 位置的元素的后 $31 - x_2 \bmod 32$ 位置为 $0$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

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
