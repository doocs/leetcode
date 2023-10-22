# [2678. 老人的数目](https://leetcode.cn/problems/number-of-senior-citizens)

[English Version](/solution/2600-2699/2678.Number%20of%20Senior%20Citizens/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的字符串&nbsp;<code>details</code>&nbsp;。<code>details</code>&nbsp;中每个元素都是一位乘客的信息，信息用长度为 <code>15</code>&nbsp;的字符串表示，表示方式如下：</p>

<ul>
	<li>前十个字符是乘客的手机号码。</li>
	<li>接下来的一个字符是乘客的性别。</li>
	<li>接下来两个字符是乘客的年龄。</li>
	<li>最后两个字符是乘客的座位号。</li>
</ul>

<p>请你返回乘客中年龄 <strong>严格大于 60 岁</strong>&nbsp;的人数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>details = ["7868190130M7522","5303914400F9211","9273338290F4010"]
<b>输出：</b>2
<b>解释：</b>下标为 0 ，1 和 2 的乘客年龄分别为 75 ，92 和 40 。所以有 2 人年龄大于 60 岁。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>details = ["1313579440F2036","2921522980M5644"]
<b>输出：</b>0
<b>解释：</b>没有乘客的年龄大于 60 岁。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= details.length &lt;= 100</code></li>
	<li><code>details[i].length == 15</code></li>
	<li><code>details[i]</code>&nbsp;中的数字只包含&nbsp;<code>'0'</code>&nbsp;到&nbsp;<code>'9'</code>&nbsp;。</li>
	<li><code>details[i][10]</code>&nbsp;是 <code>'M'</code>&nbsp;，<code>'F'</code>&nbsp;或者&nbsp;<code>'O'</code>&nbsp;之一。</li>
	<li>所有乘客的手机号码和座位号互不相同。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：遍历计数**

我们可以遍历 `details` 中的每个字符串 $x$，并将 $x$ 的第 $12$ 和第 $13$ 个字符（下标为 $11$, $12$）转换为整数，判断是否大于 $60$，如果是则将答案加一。

遍历结束后，返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 是 `details` 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countSeniors(self, details: List[str]) -> int:
        return sum(int(x[11:13]) > 60 for x in details)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countSeniors(String[] details) {
        int ans = 0;
        for (var x : details) {
            int age = Integer.parseInt(x.substring(11, 13));
            if (age > 60) {
                ++ans;
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
    int countSeniors(vector<string>& details) {
        int ans = 0;
        for (auto& x : details) {
            int age = stoi(x.substr(11, 2));
            ans += age > 60;
        }
        return ans;
    }
};
```

### **Go**

```go
func countSeniors(details []string) (ans int) {
	for _, x := range details {
		age, _ := strconv.Atoi(x[11:13])
		if age > 60 {
			ans++
		}
	}
	return
}
```

### **Rust**

```rust
impl Solution {
    pub fn count_seniors(details: Vec<String>) -> i32 {
        let mut ans = 0;

        for s in details.iter() {
            if let Ok(age) = s[11..13].parse::<i32>() {
                if age > 60 {
                    ans += 1;
                }
            }
        }

        ans
    }
}
```

```rust
impl Solution {
    pub fn count_seniors(details: Vec<String>) -> i32 {
        details
            .iter()
            .filter_map(|s| s[11..13].parse::<i32>().ok())
            .filter(|&age| age > 60)
            .count() as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
