# [2048. Next Greater Numerically Balanced Number](https://leetcode.com/problems/next-greater-numerically-balanced-number)

[中文文档](/solution/2000-2099/2048.Next%20Greater%20Numerically%20Balanced%20Number/README.md)

## Description

<p>An integer <code>x</code> is <strong>numerically balanced</strong> if for every digit <code>d</code> in the number <code>x</code>, there are <strong>exactly</strong> <code>d</code> occurrences of that digit in <code>x</code>.</p>

<p>Given an integer <code>n</code>, return <em>the <strong>smallest numerically balanced</strong> number <strong>strictly greater</strong> than </em><code>n</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 22
<strong>Explanation:</strong> 
22 is numerically balanced since:
- The digit 2 occurs 2 times. 
It is also the smallest numerically balanced number strictly greater than 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1000
<strong>Output:</strong> 1333
<strong>Explanation:</strong> 
1333 is numerically balanced since:
- The digit 1 occurs 1 time.
- The digit 3 occurs 3 times. 
It is also the smallest numerically balanced number strictly greater than 1000.
Note that 1022 cannot be the answer because 0 appeared more than 0 times.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 3000
<strong>Output:</strong> 3133
<strong>Explanation:</strong> 
3133 is numerically balanced since:
- The digit 1 occurs 1 time.
- The digit 3 occurs 3 times.
It is also the smallest numerically balanced number strictly greater than 3000.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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

### **TypeScript**

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
