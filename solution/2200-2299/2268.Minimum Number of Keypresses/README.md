# [2268. 最少按键次数](https://leetcode.cn/problems/minimum-number-of-keypresses)

[English Version](/solution/2200-2299/2268.Minimum%20Number%20of%20Keypresses/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有一个 9 键键盘，按键按 1 到 9 编号，每个按键对应着几个英文小写字母。你可以决定每个按键对应哪些英文字母，但要满足如下条件：</p>

<ul>
	<li>26 个英文小写字母必须全部映射到这 9 个按键上。</li>
	<li>每个英文字母只能映射到 <strong>恰好</strong> 一个按键上。</li>
	<li>每个按键 <strong>最多</strong> 对应 3 个英文字母。</li>
</ul>

<p>如果想打出按键上的第一个字母，只需要按一次。如果想打出按键上的第二个字母，则需要按两次，依次类推。</p>

<p>给你一个字符串 <code>s</code> ，返回基于你设计的键盘打出 <code>s</code> 需要的<strong> 最少</strong> 按键次数。</p>

<p><b>注意：</b>字母映射到每个按键上，映射的顺序无法进行更改。</p>

<p>&nbsp;</p>

<p><strong>示例 1 ：</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2268.Minimum%20Number%20of%20Keypresses/images/image-20220505184346-1.png" style="width: 300px; height: 293px;" />
<pre>
<strong>输入：</strong>s = "apple"
<strong>输出：</strong>5
<strong>解释：</strong>上图所示为设置键盘的最佳方法之一。
按按键 1 一次输入 'a' 。
按按键 6 一次输入 'p' 。
按按键 6 一次输入 'p' 。
按按键 5 一次输入 'l' 。
按按键 3 一次输入 'e' 。
总共按按键 5 次，所以返回 5 。</pre>

<p><strong>示例 2 ：</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2268.Minimum%20Number%20of%20Keypresses/images/image-20220505203823-1.png" style="width: 300px; height: 288px;" />
<pre>
<strong>输入：</strong>s = "abcdefghijkl"
<strong>输出：</strong>15
<strong>解释：</strong>上图所示为设置键盘的最佳方法之一。
字母 'a' 到 'i' 每个只需要按一次按键。
按按键 1 两次输入 'j' 。
按按键 2 两次输入 'k' 。
按按键 3 两次输入 'l' 。
总共按按键 15 次，所以返回 15 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数 + 贪心**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumKeypresses(self, s: str) -> int:
        cnt = Counter(s)
        ans = 0
        i, j = 0, 1
        for v in sorted(cnt.values(), reverse=True):
            i += 1
            ans += j * v
            if i % 9 == 0:
                j += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumKeypresses(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        Arrays.sort(cnt);
        int ans = 0;
        for (int i = 1, j = 1; i <= 26; ++i) {
            ans += j * cnt[26 - i];
            if (i % 9 == 0) {
                ++j;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumKeypresses(string s) {
        vector<int> cnt(26);
        for (char& c : s) ++cnt[c - 'a'];
        sort(cnt.begin(), cnt.end());
        int ans = 0;
        for (int i = 1, j = 1; i <= 26; ++i) {
            ans += j * cnt[26 - i];
            if (i % 9 == 0) ++j;
        }
        return ans;
    }
};
```

### **Go**

```go
func minimumKeypresses(s string) int {
	cnt := make([]int, 26)
	for _, c := range s {
		cnt[c-'a']++
	}
	sort.Ints(cnt)
	ans := 0
	for i, j := 1, 1; i <= 26; i++ {
		ans += j * cnt[26-i]
		if i%9 == 0 {
			j++
		}
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
