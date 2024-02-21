# [2938. 区分黑球与白球](https://leetcode.cn/problems/separate-black-and-white-balls)

[English Version](/solution/2900-2999/2938.Separate%20Black%20and%20White%20Balls/README_EN.md)

<!-- tags:贪心,双指针,字符串 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>桌子上有 <code>n</code> 个球，每个球的颜色不是黑色，就是白色。</p>

<p>给你一个长度为 <code>n</code> 、下标从 <strong>0</strong> 开始的二进制字符串 <code>s</code>，其中 <code>1</code> 和 <code>0</code> 分别代表黑色和白色的球。</p>

<p>在每一步中，你可以选择两个相邻的球并交换它们。</p>

<p>返回「将所有黑色球都移到右侧，所有白色球都移到左侧所需的 <strong>最小步数</strong>」。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "101"
<strong>输出：</strong>1
<strong>解释：</strong>我们可以按以下方式将所有黑色球移到右侧：
- 交换 s[0] 和 s[1]，s = "011"。
最开始，1 没有都在右侧，需要至少 1 步将其移到右侧。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "100"
<strong>输出：</strong>2
<strong>解释：</strong>我们可以按以下方式将所有黑色球移到右侧：
- 交换 s[0] 和 s[1]，s = "010"。
- 交换 s[1] 和 s[2]，s = "001"。
可以证明所需的最小步数为 2 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "0111"
<strong>输出：</strong>0
<strong>解释：</strong>所有黑色球都已经在右侧。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> 不是 <code>'0'</code>，就是 <code>'1'</code>。</li>
</ul>

## 解法

### 方法一：计数模拟

我们考虑将所有的 $1$ 移到最右边，用一个变量 $cnt$ 记录当前已经移动到最右边的 $1$ 的个数，用一个变量 $ans$ 记录移动的次数。

我们从右往左遍历字符串，如果当前位置是 $1$，那么我们将 $cnt$ 加一，同时将 $n - i - cnt$ 加到 $ans$ 中，其中 $n$ 是字符串的长度。最后返回 $ans$ 即可。

时间复杂度 $O(n)$，其中 $n$ 是字符串的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def minimumSteps(self, s: str) -> int:
        n = len(s)
        ans = cnt = 0
        for i in range(n - 1, -1, -1):
            if s[i] == '1':
                cnt += 1
                ans += n - i - cnt
        return ans
```

```java
class Solution {
    public long minimumSteps(String s) {
        long ans = 0;
        int cnt = 0;
        int n = s.length();
        for (int i = n - 1; i >= 0; --i) {
            if (s.charAt(i) == '1') {
                ++cnt;
                ans += n - i - cnt;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long minimumSteps(string s) {
        long long ans = 0;
        int cnt = 0;
        int n = s.size();
        for (int i = n - 1; i >= 0; --i) {
            if (s[i] == '1') {
                ++cnt;
                ans += n - i - cnt;
            }
        }
        return ans;
    }
};
```

```go
func minimumSteps(s string) (ans int64) {
	n := len(s)
	cnt := 0
	for i := n - 1; i >= 0; i-- {
		if s[i] == '1' {
			cnt++
			ans += int64(n - i - cnt)
		}
	}
	return
}
```

```ts
function minimumSteps(s: string): number {
    const n = s.length;
    let [ans, cnt] = [0, 0];
    for (let i = n - 1; ~i; --i) {
        if (s[i] === '1') {
            ++cnt;
            ans += n - i - cnt;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
