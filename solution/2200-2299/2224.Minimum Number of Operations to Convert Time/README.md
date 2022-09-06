# [2224. 转化时间需要的最少操作数](https://leetcode.cn/problems/minimum-number-of-operations-to-convert-time)

[English Version](/solution/2200-2299/2224.Minimum%20Number%20of%20Operations%20to%20Convert%20Time/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串 <code>current</code> 和 <code>correct</code> ，表示两个 <strong>24 小时制时间</strong> 。</p>

<p><strong>24 小时制时间</strong> 按 <code>"HH:MM"</code> 进行格式化，其中 <code>HH</code> 在 <code>00</code> 和 <code>23</code> 之间，而 <code>MM</code> 在 <code>00</code> 和 <code>59</code> 之间。最早的 24 小时制时间为 <code>00:00</code> ，最晚的是 <code>23:59</code> 。</p>

<p>在一步操作中，你可以将 <code>current</code> 这个时间增加 <code>1</code>、<code>5</code>、<code>15</code> 或 <code>60</code> 分钟。你可以执行这一操作 <strong>任意</strong> 次数。</p>

<p>返回将&nbsp;<code>current</code><em> </em>转化为<em> </em><code>correct</code> 需要的 <strong>最少操作数</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>current = "02:30", correct = "04:35"
<strong>输出：</strong>3
<strong>解释：
</strong>可以按下述 3 步操作将 current 转换为 correct ：
- 为 current 加 60 分钟，current 变为 "03:30" 。
- 为 current 加 60 分钟，current 变为 "04:30" 。 
- 为 current 加 5 分钟，current 变为 "04:35" 。
可以证明，无法用少于 3 步操作将 current 转化为 correct 。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>current = "11:00", correct = "11:01"
<strong>输出：</strong>1
<strong>解释：</strong>只需要为 current 加一分钟，所以最小操作数是 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>current</code> 和 <code>correct</code> 都符合 <code>"HH:MM"</code> 格式</li>
	<li><code>current &lt;= correct</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def convertTime(self, current: str, correct: str) -> int:
        a = int(current[:2]) * 60 + int(current[3:])
        b = int(correct[:2]) * 60 + int(correct[3:])
        ans, d = 0, b - a
        for i in [60, 15, 5, 1]:
            ans += d // i
            d %= i
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int convertTime(String current, String correct) {
        int a = Integer.parseInt(current.substring(0, 2)) * 60
            + Integer.parseInt(current.substring(3));
        int b = Integer.parseInt(correct.substring(0, 2)) * 60
            + Integer.parseInt(correct.substring(3));
        int ans = 0, d = b - a;
        for (int i : Arrays.asList(60, 15, 5, 1)) {
            ans += d / i;
            d %= i;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int convertTime(string current, string correct) {
        int a = stoi(current.substr(0, 2)) * 60 + stoi(current.substr(3, 2));
        int b = stoi(correct.substr(0, 2)) * 60 + stoi(correct.substr(3, 2));
        int ans = 0, d = b - a;
        vector<int> inc = {60, 15, 5, 1};
        for (int i : inc) {
            ans += d / i;
            d %= i;
        }
        return ans;
    }
};
```

### **Go**

```go
func convertTime(current string, correct string) int {
    parse := func(s string) int {
        h := int(s[0] - '0') * 10 + int(s[1] - '0')
        m := int(s[3] - '0') * 10 + int(s[4] - '0')
        return h * 60 + m
    }
    a, b := parse(current), parse(correct)
    ans, d := 0, b - a
    for _, i := range []int{60, 15, 5, 1} {
        ans += d / i
        d %= i
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
