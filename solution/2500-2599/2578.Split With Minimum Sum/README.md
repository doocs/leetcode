# [2578. 最小和分割](https://leetcode.cn/problems/split-with-minimum-sum)

[English Version](/solution/2500-2599/2578.Split%20With%20Minimum%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数&nbsp;<code>num</code>&nbsp;，请你将它分割成两个非负整数&nbsp;<code>num1</code> 和&nbsp;<code>num2</code>&nbsp;，满足：</p>

<ul>
	<li><code>num1</code> 和&nbsp;<code>num2</code>&nbsp;直接连起来，得到&nbsp;<code>num</code>&nbsp;各数位的一个排列。
    <ul>
    	<li>换句话说，<code>num1</code> 和&nbsp;<code>num2</code>&nbsp;中所有数字出现的次数之和等于&nbsp;<code>num</code>&nbsp;中所有数字出现的次数。</li>
    </ul>
    </li>
    <li><code>num1</code> 和&nbsp;<code>num2</code>&nbsp;可以包含前导 0 。</li>
</ul>

<p>请你返回&nbsp;<code>num1</code> 和 <code>num2</code>&nbsp;可以得到的和的 <strong>最小</strong> 值。</p>

<p><strong>注意：</strong></p>

<ul>
	<li><code>num</code>&nbsp;保证没有前导 0 。</li>
	<li><code>num1</code> 和&nbsp;<code>num2</code>&nbsp;中数位顺序可以与&nbsp;<code>num</code>&nbsp;中数位顺序不同。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>num = 4325
<b>输出：</b>59
<b>解释：</b>我们可以将 4325 分割成 <code>num1 </code>= 24 和 num2<code> = </code>35 ，和为 59 ，59 是最小和。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>num = 687
<b>输出：</b>75
<b>解释：</b>我们可以将 687 分割成 <code>num1</code> = 68 和 <code>num2 </code>= 7 ，和为最优值 75 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>10 &lt;= num &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数 + 贪心**

我们先用哈希表或数组 $cnt$ 统计 $num$ 中各个数字出现的次数，用变量 $n$ 记录 $num$ 的位数。

接下来，枚举 $nums$ 所有位数 $i$，将 $cnt$ 中的数字按照从小到大的顺序交替地分配给 $num1$ 和 $num2$，记录在一个长度为 $2$ 的数组 $ans$ 中。最后，返回 $ans$ 中的两个数之和即可。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 为 $num$ 的位数；而 $C$ 为 $num$ 中不同数字的个数，本题中 $C \leq 10$。

**方法二：排序 + 贪心**

我们可以将 $num$ 转换成字符串或者字符数组，然后对其进行排序，接下来将排序后的数组中的数字按照从小到大的顺序交替地分配给 $num1$ 和 $num2$，最后返回 $num1$ 和 $num2$ 的和即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为 $num$ 的位数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def splitNum(self, num: int) -> int:
        cnt = Counter()
        n = 0
        while num:
            cnt[num % 10] += 1
            num //= 10
            n += 1
        ans = [0] * 2
        j = 0
        for i in range(n):
            while cnt[j] == 0:
                j += 1
            cnt[j] -= 1
            ans[i & 1] = ans[i & 1] * 10 + j
        return sum(ans)
```

```python
class Solution:
    def splitNum(self, num: int) -> int:
        s = sorted(str(num))
        return int(''.join(s[::2])) + int(''.join(s[1::2]))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int splitNum(int num) {
        int[] cnt = new int[10];
        int n = 0;
        for (; num > 0; num /= 10) {
            ++cnt[num % 10];
            ++n;
        }
        int[] ans = new int[2];
        for (int i = 0, j = 0; i < n; ++i) {
            while (cnt[j] == 0) {
                ++j;
            }
            --cnt[j];
            ans[i & 1] = ans[i & 1] * 10 + j;
        }
        return ans[0] + ans[1];
    }
}
```

```java
class Solution {
    public int splitNum(int num) {
        char[] s = (num + "").toCharArray();
        Arrays.sort(s);
        int[] ans = new int[2];
        for (int i = 0; i < s.length; ++i) {
            ans[i & 1] = ans[i & 1] * 10 + s[i] - '0';
        }
        return ans[0] + ans[1];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int splitNum(int num) {
        int cnt[10]{};
        int n = 0;
        for (; num; num /= 10) {
            ++cnt[num % 10];
            ++n;
        }
        int ans[2]{};
        for (int i = 0, j = 0; i < n; ++i) {
            while (cnt[j] == 0) {
                ++j;
            }
            --cnt[j];
            ans[i & 1] = ans[i & 1] * 10 + j;
        }
        return ans[0] + ans[1];
    }
};
```

```cpp
class Solution {
public:
    int splitNum(int num) {
        string s = to_string(num);
        sort(s.begin(), s.end());
        int ans[2]{};
        for (int i = 0; i < s.size(); ++i) {
            ans[i & 1] = ans[i & 1] * 10 + s[i] - '0';
        }
        return ans[0] + ans[1];
    }
};
```

### **Go**

```go
func splitNum(num int) int {
	cnt := [10]int{}
	n := 0
	for ; num > 0; num /= 10 {
		cnt[num%10]++
		n++
	}
	ans := [2]int{}
	for i, j := 0, 0; i < n; i++ {
		for cnt[j] == 0 {
			j++
		}
		cnt[j]--
		ans[i&1] = ans[i&1]*10 + j
	}
	return ans[0] + ans[1]
}
```

```go
func splitNum(num int) int {
	s := []byte(strconv.Itoa(num))
	sort.Slice(s, func(i, j int) bool { return s[i] < s[j] })
	ans := [2]int{}
	for i, c := range s {
		ans[i&1] = ans[i&1]*10 + int(c-'0')
	}
	return ans[0] + ans[1]
}
```

### **TypeScript**

```ts
function splitNum(num: number): number {
    const cnt = new Array(10).fill(0);
    let n = 0;
    for (; num > 0; num = Math.floor(num / 10)) {
        ++cnt[num % 10];
        ++n;
    }
    const ans = new Array(2).fill(0);
    for (let i = 0, j = 0; i < n; ++i) {
        while (cnt[j] === 0) {
            ++j;
        }
        --cnt[j];
        ans[i & 1] = ans[i & 1] * 10 + j;
    }
    return ans[0] + ans[1];
}
```

```ts
function splitNum(num: number): number {
    const s: string[] = String(num).split('');
    s.sort();
    const ans: number[] = new Array(2).fill(0);
    for (let i = 0; i < s.length; ++i) {
        ans[i & 1] = ans[i & 1] * 10 + Number(s[i]);
    }
    return ans[0] + ans[1];
}
```

### **...**

```

```

<!-- tabs:end -->
