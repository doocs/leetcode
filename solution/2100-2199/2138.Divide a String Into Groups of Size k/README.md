# [2138. 将字符串拆分为若干长度为 k 的组](https://leetcode.cn/problems/divide-a-string-into-groups-of-size-k)

[English Version](/solution/2100-2199/2138.Divide%20a%20String%20Into%20Groups%20of%20Size%20k/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>字符串 <code>s</code> 可以按下述步骤划分为若干长度为 <code>k</code> 的组：</p>

<ul>
	<li>第一组由字符串中的前 <code>k</code> 个字符组成，第二组由接下来的 <code>k</code> 个字符串组成，依此类推。每个字符都能够成为 <strong>某一个</strong> 组的一部分。</li>
	<li>对于最后一组，如果字符串剩下的字符 <strong>不足</strong> <code>k</code> 个，需使用字符 <code>fill</code> 来补全这一组字符。</li>
</ul>

<p>注意，在去除最后一个组的填充字符 <code>fill</code>（如果存在的话）并按顺序连接所有的组后，所得到的字符串应该是 <code>s</code> 。</p>

<p>给你一个字符串 <code>s</code> ，以及每组的长度 <code>k</code> 和一个用于填充的字符 <code>fill</code> ，按上述步骤处理之后，返回一个字符串数组，该数组表示 <code>s</code> 分组后 <strong>每个组的组成情况</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = "abcdefghi", k = 3, fill = "x"
<strong>输出：</strong>["abc","def","ghi"]
<strong>解释：</strong>
前 3 个字符是 "abc" ，形成第一组。
接下来 3 个字符是 "def" ，形成第二组。
最后 3 个字符是 "ghi" ，形成第三组。
由于所有组都可以由字符串中的字符完全填充，所以不需要使用填充字符。
因此，形成 3 组，分别是 "abc"、"def" 和 "ghi" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = "abcdefghij", k = 3, fill = "x"
<strong>输出：</strong>["abc","def","ghi","jxx"]
<strong>解释：</strong>
与前一个例子类似，形成前三组 "abc"、"def" 和 "ghi" 。
对于最后一组，字符串中仅剩下字符 'j' 可以用。为了补全这一组，使用填充字符 'x' 两次。
因此，形成 4 组，分别是 "abc"、"def"、"ghi" 和 "jxx" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 仅由小写英文字母组成</li>
	<li><code>1 &lt;= k &lt;= 100</code></li>
	<li><code>fill</code> 是一个小写英文字母</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def divideString(self, s: str, k: int, fill: str) -> List[str]:
        return [s[i : i + k].ljust(k, fill) for i in range(0, len(s), k)]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String[] divideString(String s, int k, char fill) {
        int n = s.length();
        String[] ans = new String[(n + k - 1) / k];
        if (n % k != 0) {
            s += String.valueOf(fill).repeat(k - n % k);
        }
        for (int i = 0; i < ans.length; ++i) {
            ans[i] = s.substring(i * k, (i + 1) * k);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> divideString(string s, int k, char fill) {
        int n = s.size();
        if (n % k)
            for (int i = 0; i < k - n % k; ++i) s.push_back(fill);
        vector<string> ans;
        for (int i = 0; i < s.size() / k; ++i) ans.push_back(s.substr(i * k, k));
        return ans;
    }
};
```

### **Go**

```go
func divideString(s string, k int, fill byte) []string {
	n := len(s)
	if n%k != 0 {
		s += strings.Repeat(string(fill), k-n%k)
	}
	var ans []string
	for i := 0; i < len(s)/k; i++ {
		ans = append(ans, s[i*k:(i+1)*k])
	}
	return ans
}
```

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

### **...**

```

```

<!-- tabs:end -->
