# [2048. 下一个更大的数值平衡数](https://leetcode.cn/problems/next-greater-numerically-balanced-number)

[English Version](/solution/2000-2099/2048.Next%20Greater%20Numerically%20Balanced%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果整数&nbsp; <code>x</code> 满足：对于每个数位&nbsp;<code>d</code> ，这个数位&nbsp;<strong>恰好</strong> 在 <code>x</code> 中出现 <code>d</code> 次。那么整数 <code>x</code> 就是一个 <strong>数值平衡数</strong> 。</p>

<p>给你一个整数 <code>n</code> ，请你返回 <strong>严格大于</strong> <code>n</code> 的 <strong>最小数值平衡数</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>22
<strong>解释：</strong>
22 是一个数值平衡数，因为：
- 数字 2 出现 2 次 
这也是严格大于 1 的最小数值平衡数。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1000
<strong>输出：</strong>1333
<strong>解释：</strong>
1333 是一个数值平衡数，因为：
- 数字 1 出现 1 次。
- 数字 3 出现 3 次。 
这也是严格大于 1000 的最小数值平衡数。
注意，1022 不能作为本输入的答案，因为数字 0 的出现次数超过了 0 。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 3000
<strong>输出：</strong>3133
<strong>解释：</strong>
3133 是一个数值平衡数，因为：
- 数字 1 出现 1 次。
- 数字 3 出现 3 次。 
这也是严格大于 3000 的最小数值平衡数。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def nextBeautifulNumber(self, n: int) -> int:
        def check(num):
            counter = [0] * 10
            for c in str(num):
                counter[int(c)] += 1

            for c in str(num):
                if counter[int(c)] != int(c):
                    return False
            return True

        for i in range(n + 1, 10**7):
            if check(i):
                return i
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int nextBeautifulNumber(int n) {
        for (int i = n + 1; i < 10000000; ++i) {
            if (check(i)) {
                return i;
            }
        }
        return -1;
    }

    private boolean check(int num) {
        int[] counter = new int[10];
        char[] chars = String.valueOf(num).toCharArray();
        for (char c : chars) {
            ++counter[c - '0'];
        }
        for (char c : chars) {
            if (counter[c - '0'] != c - '0') {
                return false;
            }
        }
        return true;
    }
}
```

## **TypeScript**

```ts
function nextBeautifulNumber(n: number): number {
    for (let ans = n + 1; ; ans++) {
        if (isValid(ans)) {
            return ans;
        }
    }
}

function isValid(n: number): boolean {
    let record = new Array(10).fill(0);
    while (n > 0) {
        const idx = n % 10;
        record[idx]++;
        n = Math.floor(n / 10);
    }
    for (let i = 0; i < 10; i++) {
        if (record[i] && record[i] != i) return false;
    }
    return true;
}
```

### **C++**

```cpp
class Solution {
public:
    int nextBeautifulNumber(int n) {
        for (int i = n + 1; i < 10000000; ++i) {
            if (check(i)) return i;
        }
        return -1;
    }

    bool check(int num) {
        string s = to_string(num);
        vector<int> counter(10);
        for (char c : s) ++counter[c - '0'];
        for (char c : s) {
            if (counter[c - '0'] != c - '0') return false;
        }
        return true;
    }
};
```

### **Go**

```go
func nextBeautifulNumber(n int) int {
	check := func(num int) bool {
		s := strconv.Itoa(num)
		counter := make([]int, 10)
		for _, c := range s {
			counter[int(c-'0')]++
		}
		for _, c := range s {
			if counter[int(c-'0')] != int(c-'0') {
				return false
			}
		}
		return true
	}

	for i := n + 1; i <= 10000000; i++ {
		if check(i) {
			return i
		}
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
