# [2165. 重排数字的最小值](https://leetcode.cn/problems/smallest-value-of-the-rearranged-number)

[English Version](/solution/2100-2199/2165.Smallest%20Value%20of%20the%20Rearranged%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数 <code>num</code> 。<strong>重排</strong> <code>num</code> 中的各位数字，使其值 <strong>最小化</strong> 且不含 <strong>任何</strong> 前导零。</p>

<p>返回不含前导零且值最小的重排数字。</p>

<p>注意，重排各位数字后，<code>num</code> 的符号不会改变。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>num = 310
<strong>输出：</strong>103
<strong>解释：</strong>310 中各位数字的可行排列有：013、031、103、130、301、310 。
不含任何前导零且值最小的重排数字是 103 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>num = -7605
<strong>输出：</strong>-7650
<strong>解释：</strong>-7605 中各位数字的部分可行排列为：-7650、-6705、-5076、-0567。
不含任何前导零且值最小的重排数字是 -7650 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-10<sup>15</sup> &lt;= num &lt;= 10<sup>15</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def smallestNumber(self, num: int) -> int:
        if num == 0:
            return 0
        cnt = [0] * 10
        neg = num < 0
        num = abs(num)
        while num:
            num, v = divmod(num, 10)
            cnt[v] += 1
        ans = ""
        if neg:
            for i in range(9, -1, -1):
                if cnt[i]:
                    ans += str(i) * cnt[i]
            return -int(ans)
        if cnt[0]:
            for i in range(1, 10):
                if cnt[i]:
                    ans += str(i)
                    cnt[i] -= 1
                    break
        for i in range(10):
            if cnt[i]:
                ans += str(i) * cnt[i]
        return int(ans)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long smallestNumber(long num) {
        if (num == 0) {
            return 0;
        }
        int[] cnt = new int[10];
        boolean neg = num < 0;
        num = Math.abs(num);
        while (num != 0) {
            cnt[(int) (num % 10)]++;
            num /= 10;
        }
        long ans = 0;
        if (neg) {
            for (int i = 9; i >= 0; --i) {
                while (cnt[i]-- > 0) {
                    ans = ans * 10 + i;
                }
            }
            return -ans;
        }
        if (cnt[0] > 0) {
            for (int i = 1; i < 10; ++i) {
                if (cnt[i] > 0) {
                    ans = ans * 10 + i;
                    cnt[i]--;
                    break;
                }
            }
        }
        for (int i = 0; i < 10; ++i) {
            while (cnt[i]-- > 0) {
                ans = ans * 10 + i;
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
    long long smallestNumber(long long num) {
        if (num == 0) return 0;
        vector<int> cnt(10);
        bool neg = num < 0;
        num = abs(num);
        while (num) {
            cnt[num % 10]++;
            num /= 10;
        }
        long long ans = 0;
        if (neg) {
            for (int i = 9; i >= 0; --i)
                while (cnt[i]--) ans = ans * 10 + i;
            return -ans;
        }
        if (cnt[0]) {
            for (int i = 1; i < 10; ++i) {
                if (cnt[i]) {
                    ans = ans * 10 + i;
                    cnt[i]--;
                    break;
                }
            }
        }
        for (int i = 0; i < 10; ++i)
            while (cnt[i]--) ans = ans * 10 + i;
        return ans;
    }
};
```

### **Go**

```go
func smallestNumber(num int64) int64 {
	if num == 0 {
		return 0
	}
	cnt := make([]int, 10)
	neg := num < 0
	if neg {
		num = -num
	}
	for num != 0 {
		cnt[num%10]++
		num /= 10
	}
	ans := 0
	if neg {
		for i := 9; i >= 0; i-- {
			for j := 0; j < cnt[i]; j++ {
				ans = ans*10 + i
			}
		}
		return -int64(ans)
	}
	if cnt[0] > 0 {
		for i := 1; i < 10; i++ {
			if cnt[i] > 0 {
				ans = ans*10 + i
				cnt[i]--
				break
			}
		}
	}
	for i := 0; i < 10; i++ {
		for j := 0; j < cnt[i]; j++ {
			ans = ans*10 + i
		}
	}
	return int64(ans)
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
