---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2269.Find%20the%20K-Beauty%20of%20a%20Number/README.md
rating: 1279
source: 第 78 场双周赛 Q1
tags:
    - 数学
    - 字符串
    - 滑动窗口
---

# [2269. 找到一个数字的 K 美丽值](https://leetcode.cn/problems/find-the-k-beauty-of-a-number)

[English Version](/solution/2200-2299/2269.Find%20the%20K-Beauty%20of%20a%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个整数 <code>num</code>&nbsp;的&nbsp;<strong>k&nbsp;</strong>美丽值定义为&nbsp;<code>num</code>&nbsp;中符合以下条件的&nbsp;<strong>子字符串</strong>&nbsp;数目：</p>

<ul>
	<li>子字符串长度为&nbsp;<code>k</code>&nbsp;。</li>
	<li>子字符串能整除 <code>num</code> 。</li>
</ul>

<p>给你整数&nbsp;<code>num</code> 和&nbsp;<code>k</code>&nbsp;，请你返回<em>&nbsp;</em><code>num</code>&nbsp;的 k 美丽值。</p>

<p>注意：</p>

<ul>
	<li>允许有&nbsp;<strong>前缀</strong>&nbsp;<strong>0</strong>&nbsp;。</li>
	<li><code>0</code>&nbsp;不能整除任何值。</li>
</ul>

<p>一个 <strong>子字符串</strong>&nbsp;是一个字符串里的连续一段字符序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>num = 240, k = 2
<b>输出：</b>2
<b>解释：</b>以下是 num 里长度为 k 的子字符串：
- "<em><strong>24</strong></em>0" 中的 "24" ：24 能整除 240 。
- "2<em><strong>40</strong></em>" 中的 "40" ：40 能整除 240 。
所以，k 美丽值为 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>num = 430043, k = 2
<b>输出：</b>2
<b>解释：</b>以下是 num 里长度为 k 的子字符串：
- "<em><strong>43</strong></em>0043" 中的 "43" ：43 能整除 430043 。
- "4<em><strong>30</strong></em>043" 中的 "30" ：30 不能整除 430043 。
- "43<em><strong>00</strong></em>43" 中的 "00" ：0 不能整除 430043 。
- "430<em><strong>04</strong></em>3" 中的 "04" ：4 不能整除 430043 。
- "4300<em><strong>43</strong></em>" 中的 "43" ：43 能整除 430043 。
所以，k 美丽值为 2 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= num.length</code>&nbsp;（将&nbsp;<code>num</code>&nbsp;视为字符串）</li>
</ul>

## 解法

### 方法一：枚举

我们可以将 $num$ 转换为字符串 $s$，然后枚举 $s$ 的所有长度为 $k$ 的子字符串，将其转换为整数 $t$，判断 $t$ 是否能整除 $num$，如果能则答案加一。

时间复杂度 $O(\log num \times k)$，空间复杂度 $O(\log num + k)$。

<!-- tabs:start -->

```python
class Solution:
    def divisorSubstrings(self, num: int, k: int) -> int:
        ans = 0
        s = str(num)
        for i in range(len(s) - k + 1):
            t = int(s[i : i + k])
            if t and num % t == 0:
                ans += 1
        return ans
```

```java
class Solution {
    public int divisorSubstrings(int num, int k) {
        int ans = 0;
        String s = "" + num;
        for (int i = 0; i < s.length() - k + 1; ++i) {
            int t = Integer.parseInt(s.substring(i, i + k));
            if (t != 0 && num % t == 0) {
                ++ans;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int divisorSubstrings(int num, int k) {
        int ans = 0;
        string s = to_string(num);
        for (int i = 0; i < s.size() - k + 1; ++i) {
            int t = stoi(s.substr(i, k));
            ans += t && num % t == 0;
        }
        return ans;
    }
};
```

```go
func divisorSubstrings(num int, k int) int {
	ans := 0
	s := strconv.Itoa(num)
	for i := 0; i < len(s)-k+1; i++ {
		t, _ := strconv.Atoi(s[i : i+k])
		if t > 0 && num%t == 0 {
			ans++
		}
	}
	return ans
}
```

```ts
function divisorSubstrings(num: number, k: number): number {
    let ans = 0;
    const s = num.toString();
    for (let i = 0; i < s.length - k + 1; ++i) {
        const t = parseInt(s.substring(i, i + k));
        if (t !== 0 && num % t === 0) {
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

### 方法二：滑动窗口

我们可以维护一个长度为 $k$ 的滑动窗口，初始时窗口中包含 $num$ 的最低 $k$ 位数字，然后每次向右移动一位，更新窗口中的数字，判断窗口中的数字是否能整除 $num$，如果能则答案加一。

时间复杂度 $O(\log num)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def divisorSubstrings(self, num: int, k: int) -> int:
        x, p = 0, 1
        t = num
        for _ in range(k):
            t, v = divmod(t, 10)
            x = p * v + x
            p *= 10
        ans = int(x != 0 and num % x == 0)
        p //= 10
        while t:
            x //= 10
            t, v = divmod(t, 10)
            x = p * v + x
            ans += int(x != 0 and num % x == 0)
        return ans
```

```java
class Solution {
    public int divisorSubstrings(int num, int k) {
        int x = 0, p = 1;
        int t = num;
        for (; k > 0; --k) {
            int v = t % 10;
            t /= 10;
            x = p * v + x;
            p *= 10;
        }
        int ans = x != 0 && num % x == 0 ? 1 : 0;
        for (p /= 10; t > 0; t /= 10) {
            x /= 10;
            int v = t % 10;
            x = p * v + x;
            ans += (x != 0 && num % x == 0 ? 1 : 0);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int divisorSubstrings(int num, int k) {
        int x = 0;
        long long p = 1;
        int t = num;
        for (; k > 0; --k) {
            int v = t % 10;
            t /= 10;
            x = p * v + x;
            p *= 10;
        }
        int ans = x != 0 && num % x == 0 ? 1 : 0;
        for (p /= 10; t > 0; t /= 10) {
            x /= 10;
            int v = t % 10;
            x = p * v + x;
            ans += (x != 0 && num % x == 0 ? 1 : 0);
        }
        return ans;
    }
};
```

```go
func divisorSubstrings(num int, k int) (ans int) {
	x, p, t := 0, 1, num
	for ; k > 0; k-- {
		v := t % 10
		t /= 10
		x = p*v + x
		p *= 10
	}
	if x != 0 && num%x == 0 {
		ans++
	}
	for p /= 10; t > 0; t /= 10 {
		x /= 10
		v := t % 10
		x = p*v + x
		if x != 0 && num%x == 0 {
			ans++
		}
	}
	return
}
```

```ts
function divisorSubstrings(num: number, k: number): number {
    let [x, p, t] = [0, 1, num];
    for (; k > 0; k--) {
        const v = t % 10;
        t = Math.floor(t / 10);
        x = p * v + x;
        p *= 10;
    }
    let ans = x !== 0 && num % x === 0 ? 1 : 0;
    for (p = Math.floor(p / 10); t > 0; t = Math.floor(t / 10)) {
        x = Math.floor(x / 10);
        x = p * (t % 10) + x;
        ans += x !== 0 && num % x === 0 ? 1 : 0;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
