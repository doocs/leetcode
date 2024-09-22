---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1945.Sum%20of%20Digits%20of%20String%20After%20Convert/README.md
rating: 1254
source: 第 251 场周赛 Q1
tags:
    - 字符串
    - 模拟
---

<!-- problem:start -->

# [1945. 字符串转化后的各位数字之和](https://leetcode.cn/problems/sum-of-digits-of-string-after-convert)

[English Version](/solution/1900-1999/1945.Sum%20of%20Digits%20of%20String%20After%20Convert/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由小写字母组成的字符串 <code>s</code> ，以及一个整数 <code>k</code> 。你的任务是通过一种特殊处理将字符串转为整数，然后通过重复对它的数位求和&nbsp;<code>k</code> 次来进行转换。更具体地说，执行以下步骤：</p>

<ol>
	<li>用字母在字母表中的位置&nbsp;<strong>替换&nbsp;</strong>该字母，将 <code>s</code> <strong>转化</strong> 为一个整数（也就是，<code>'a'</code> 用 <code>1</code> 替换，<code>'b'</code> 用 <code>2</code> 替换，... <code>'z'</code> 用 <code>26</code> 替换）。</li>
	<li>接着，将整数 <strong>转换</strong> 为其 <strong>各位数字之和</strong> 。</li>
	<li>共重复 <strong>转换</strong> 操作（第 2 步）&nbsp;<code>k</code><strong> 次</strong> 。</li>
</ol>

<p>例如，如果 <code>s = "zbax"</code> 且 <code>k = 2</code> ，那么执行下述步骤后得到的结果是整数 <code>8</code> ：</p>

<ul>
	<li><strong>转化：</strong><code>"zbax" ➝ "(26)(2)(1)(24)" ➝ "262124" ➝ 262124</code></li>
	<li><strong>转换 #1</strong>：<code>262124&nbsp;➝ 2 + 6 + 2 + 1 + 2 + 4&nbsp;➝ 17</code></li>
	<li><strong>转换 #2</strong>：<code>17 ➝ 1 + 7 ➝ 8</code></li>
</ul>

<p>返回执行上述 <strong>操作</strong> 后得到的 <strong>结果整数</strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<div class="example-block"><strong>输入：</strong>s = "iiii", k = 1</div>

<div class="example-block"><strong>输出：</strong>36</div>

<div class="example-block"><strong>解释：</strong></div>

<div class="example-block">操作如下：</div>

<ul>
	<li class="example-block">转化："iiii" ➝ "(9)(9)(9)(9)" ➝ "9999" ➝ 9999</li>
	<li class="example-block">转换 #1：9999 ➝ 9 + 9 + 9 + 9 ➝ 36</li>
</ul>

<div class="example-block">因此，结果整数为 36 。</div>

<div class="example-block">&nbsp;</div>

<p><strong>示例 2：</strong></p>

<div class="example-block"><strong>输入：</strong>s = "leetcode", k = 2</div>

<div class="example-block"><strong>输出：</strong>6</div>

<div class="example-block"><strong>解释：</strong></div>

<div class="example-block">操作如下：</div>

<ul>
	<li class="example-block">转化："leetcode" ➝ "(12)(5)(5)(20)(3)(15)(4)(5)" ➝ "12552031545" ➝ 12552031545</li>
	<li class="example-block">转换 #1：12552031545 ➝ 1 + 2 + 5 + 5 + 2 + 0 + 3 + 1 + 5 + 4 + 5 ➝ 33</li>
	<li class="example-block">转换 #2：33 ➝ 3 + 3 ➝ 6</li>
</ul>

<p class="example-block">因此，结果整数为 6 。</p>

<p class="example-block">&nbsp;</p>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">s = "zbax", k = 2</span></p>

<p><span class="example-io"><b>输出：</b>8</span></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 10</code></li>
	<li><code>s</code> 由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

根据题目描述进行模拟即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getLucky(self, s: str, k: int) -> int:
        s = ''.join(str(ord(c) - ord('a') + 1) for c in s)
        for _ in range(k):
            t = sum(int(c) for c in s)
            s = str(t)
        return int(s)
```

#### Java

```java
class Solution {
    public int getLucky(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(c - 'a' + 1);
        }
        s = sb.toString();
        while (k-- > 0) {
            int t = 0;
            for (char c : s.toCharArray()) {
                t += c - '0';
            }
            s = String.valueOf(t);
        }
        return Integer.parseInt(s);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int getLucky(string s, int k) {
        string t;
        for (char c : s) t += to_string(c - 'a' + 1);
        s = t;
        while (k--) {
            int t = 0;
            for (char c : s) t += c - '0';
            s = to_string(t);
        }
        return stoi(s);
    }
};
```

#### Go

```go
func getLucky(s string, k int) int {
	var t strings.Builder
	for _, c := range s {
		t.WriteString(strconv.Itoa(int(c - 'a' + 1)))
	}
	s = t.String()
	for k > 0 {
		k--
		t := 0
		for _, c := range s {
			t += int(c - '0')
		}
		s = strconv.Itoa(t)
	}
	ans, _ := strconv.Atoi(s)
	return ans
}
```

#### TypeScript

```ts
function getLucky(s: string, k: number): number {
    let ans = '';
    for (const c of s) {
        ans += c.charCodeAt(0) - 'a'.charCodeAt(0) + 1;
    }
    for (let i = 0; i < k; i++) {
        let t = 0;
        for (const v of ans) {
            t += Number(v);
        }
        ans = `${t}`;
    }
    return Number(ans);
}
```

#### Rust

```rust
impl Solution {
    pub fn get_lucky(s: String, k: i32) -> i32 {
        let mut ans = String::new();
        for c in s.as_bytes() {
            ans.push_str(&(c - b'a' + 1).to_string());
        }
        for _ in 0..k {
            let mut t = 0;
            for c in ans.as_bytes() {
                t += (c - b'0') as i32;
            }
            ans = t.to_string();
        }
        ans.parse().unwrap()
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param String $s
     * @param Integer $k
     * @return Integer
     */
    function getLucky($s, $k) {
        $rs = '';
        for ($i = 0; $i < strlen($s); $i++) {
            $num = ord($s[$i]) - 96;
            $rs = $rs . strval($num);
        }
        while ($k != 0) {
            $sum = 0;
            for ($j = 0; $j < strlen($rs); $j++) {
                $sum += intval($rs[$j]);
            }
            $rs = strval($sum);
            $k--;
        }
        return intval($rs);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
