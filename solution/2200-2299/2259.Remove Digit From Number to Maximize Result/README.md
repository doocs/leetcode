# [2259. 移除指定数字得到的最大结果](https://leetcode.cn/problems/remove-digit-from-number-to-maximize-result)

[English Version](/solution/2200-2299/2259.Remove%20Digit%20From%20Number%20to%20Maximize%20Result/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个表示某个正整数的字符串 <code>number</code> 和一个字符 <code>digit</code> 。</p>

<p>从 <code>number</code> 中 <strong>恰好</strong> 移除 <strong>一个</strong> 等于&nbsp;<code>digit</code> 的字符后，找出并返回按 <strong>十进制</strong> 表示 <strong>最大</strong> 的结果字符串。生成的测试用例满足 <code>digit</code> 在 <code>number</code> 中出现至少一次。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>number = "123", digit = "3"
<strong>输出：</strong>"12"
<strong>解释：</strong>"123" 中只有一个 '3' ，在移除 '3' 之后，结果为 "12" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>number = "1231", digit = "1"
<strong>输出：</strong>"231"
<strong>解释：</strong>可以移除第一个 '1' 得到 "231" 或者移除第二个 '1' 得到 "123" 。
由于 231 &gt; 123 ，返回 "231" 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>number = "551", digit = "5"
<strong>输出：</strong>"51"
<strong>解释：</strong>可以从 "551" 中移除第一个或者第二个 '5' 。
两种方案的结果都是 "51" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= number.length &lt;= 100</code></li>
	<li><code>number</code> 由数字 <code>'1'</code> 到 <code>'9'</code> 组成</li>
	<li><code>digit</code> 是 <code>'1'</code> 到 <code>'9'</code> 中的一个数字</li>
	<li><code>digit</code> 在 <code>number</code> 中出现至少一次</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def removeDigit(self, number: str, digit: str) -> str:
        return max(
            number[:i] + number[i + 1 :] for i, d in enumerate(number) if d == digit
        )
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String removeDigit(String number, char digit) {
        String ans = "0";
        for (int i = 0, n = number.length(); i < n; ++i) {
            char d = number.charAt(i);
            if (d == digit) {
                String t = number.substring(0, i) + number.substring(i + 1);
                if (ans.compareTo(t) < 0) {
                    ans = t;
                }
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
    string removeDigit(string number, char digit) {
        string ans = "0";
        for (int i = 0, n = number.size(); i < n; ++i) {
            char d = number[i];
            if (d == digit) {
                string t = number.substr(0, i) + number.substr(i + 1, n - i);
                if (ans < t) ans = t;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func removeDigit(number string, digit byte) string {
	ans := "0"
	for i, d := range number {
		if d == rune(digit) {
			t := number[:i] + number[i+1:]
			if strings.Compare(ans, t) < 0 {
				ans = t
			}
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function removeDigit(number: string, digit: string): string {
    let nums = number.split('');
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n; i++) {
        if (nums[i] != digit) continue;
        ans = i;
        if (i + 1 < n && nums[i + 1] > nums[i]) break;
    }
    nums.splice(ans, 1);
    return nums.join('');
}
```

### **...**

```

```

<!-- tabs:end -->
