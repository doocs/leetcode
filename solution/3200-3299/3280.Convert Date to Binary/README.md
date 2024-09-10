---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3280.Convert%20Date%20to%20Binary/README.md
tags:
    - 数学
    - 字符串
---

<!-- problem:start -->

# [3280. 将日期转换为二进制表示](https://leetcode.cn/problems/convert-date-to-binary)

[English Version](/solution/3200-3299/3280.Convert%20Date%20to%20Binary/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>date</code>，它的格式为 <code>yyyy-mm-dd</code>，表示一个公历日期。</p>

<p><code>date</code> 可以重写为二进制表示，只需要将年、月、日分别转换为对应的二进制表示（不带前导零）并遵循 <code>year-month-day</code> 的格式。</p>

<p>返回 <code>date</code> 的 <strong>二进制</strong> 表示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">date = "2080-02-29"</span></p>

<p><strong>输出：</strong> <span class="example-io">"100000100000-10-11101"</span></p>

<p><strong>解释：</strong></p>

<p><span class="example-io">100000100000, 10 和 11101 分别是 2080, 02 和 29 的二进制表示。</span></p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">date = "1900-01-01"</span></p>

<p><strong>输出：</strong> <span class="example-io">"11101101100-1-1"</span></p>

<p><strong>解释：</strong></p>

<p><span class="example-io">11101101100, 1 和 1 分别是 1900, 1 和 1 的二进制表示。</span></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>date.length == 10</code></li>
	<li><code>date[4] == date[7] == '-'</code>，其余的 <code>date[i]</code> 都是数字。</li>
	<li>输入保证 <code>date</code> 代表一个有效的公历日期，日期范围从 1900 年 1 月 1 日到 2100 年 12 月 31 日（包括这两天）。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们先将字符串 $\textit{date}$ 按照 `-` 分割，然后将每个部分转换为二进制表示，最后将这三个部分用 `-` 连接起来即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $\textit{date}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def convertDateToBinary(self, date: str) -> str:
        return "-".join(f"{int(s):b}" for s in date.split("-"))
```

#### Java

```java
class Solution {
    public String convertDateToBinary(String date) {
        List<String> ans = new ArrayList<>();
        for (var s : date.split("-")) {
            int x = Integer.parseInt(s);
            ans.add(Integer.toBinaryString(x));
        }
        return String.join("-", ans);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string convertDateToBinary(string date) {
        auto bin = [](string s) -> string {
            string t = bitset<32>(stoi(s)).to_string();
            return t.substr(t.find('1'));
        };
        return bin(date.substr(0, 4)) + "-" + bin(date.substr(5, 2)) + "-" + bin(date.substr(8, 2));
    }
};
```

#### Go

```go
func convertDateToBinary(date string) string {
	ans := []string{}
	for _, s := range strings.Split(date, "-") {
		x, _ := strconv.Atoi(s)
		ans = append(ans, strconv.FormatUint(uint64(x), 2))
	}
	return strings.Join(ans, "-")
}
```

#### TypeScript

```ts
function convertDateToBinary(date: string): string {
    return date
        .split('-')
        .map(s => (+s).toString(2))
        .join('-');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
