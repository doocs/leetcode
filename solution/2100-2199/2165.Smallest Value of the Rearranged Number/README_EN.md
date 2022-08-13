# [2165. Smallest Value of the Rearranged Number](https://leetcode.com/problems/smallest-value-of-the-rearranged-number)

[中文文档](/solution/2100-2199/2165.Smallest%20Value%20of%20the%20Rearranged%20Number/README.md)

## Description

<p>You are given an integer <code>num.</code> <strong>Rearrange</strong> the digits of <code>num</code> such that its value is <strong>minimized</strong> and it does not contain <strong>any</strong> leading zeros.</p>

<p>Return <em>the rearranged number with minimal value</em>.</p>

<p>Note that the sign of the number does not change after rearranging the digits.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = 310
<strong>Output:</strong> 103
<strong>Explanation:</strong> The possible arrangements for the digits of 310 are 013, 031, 103, 130, 301, 310. 
The arrangement with the smallest value that does not contain any leading zeros is 103.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = -7605
<strong>Output:</strong> -7650
<strong>Explanation:</strong> Some possible arrangements for the digits of -7605 are -7650, -6705, -5076, -0567.
The arrangement with the smallest value that does not contain any leading zeros is -7650.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>-10<sup>15</sup> &lt;= num &lt;= 10<sup>15</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
