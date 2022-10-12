# [2231. Largest Number After Digit Swaps by Parity](https://leetcode.com/problems/largest-number-after-digit-swaps-by-parity)

[中文文档](/solution/2200-2299/2231.Largest%20Number%20After%20Digit%20Swaps%20by%20Parity/README.md)

## Description

<p>You are given a positive integer <code>num</code>. You may swap any two digits of <code>num</code> that have the same <strong>parity</strong> (i.e. both odd digits or both even digits).</p>

<p>Return<em> the <strong>largest</strong> possible value of </em><code>num</code><em> after <strong>any</strong> number of swaps.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = 1234
<strong>Output:</strong> 3412
<strong>Explanation:</strong> Swap the digit 3 with the digit 1, this results in the number 3214.
Swap the digit 2 with the digit 4, this results in the number 3412.
Note that there may be other sequences of swaps but it can be shown that 3412 is the largest possible number.
Also note that we may not swap the digit 4 with the digit 1 since they are of different parities.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = 65875
<strong>Output:</strong> 87655
<strong>Explanation:</strong> Swap the digit 8 with the digit 6, this results in the number 85675.
Swap the first digit 5 with the digit 7, this results in the number 87655.
Note that there may be other sequences of swaps but it can be shown that 87655 is the largest possible number.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def largestInteger(self, num: int) -> int:
        cnt = Counter()
        x = num
        while x:
            x, v = divmod(x, 10)
            cnt[v] += 1
        x = num
        ans = 0
        t = 1
        while x:
            x, v = divmod(x, 10)
            for y in range(10):
                if ((v ^ y) & 1) == 0 and cnt[y]:
                    ans += y * t
                    t *= 10
                    cnt[y] -= 1
                    break
        return ans
```

### **Java**

```java
class Solution {
    public int largestInteger(int num) {
        int[] cnt = new int[10];
        int x = num;
        while (x != 0) {
            cnt[x % 10]++;
            x /= 10;
        }
        x = num;
        int ans = 0;
        int t = 1;
        while (x != 0) {
            int v = x % 10;
            x /= 10;
            for (int y = 0; y < 10; ++y) {
                if (((v ^ y) & 1) == 0 && cnt[y] > 0) {
                    cnt[y]--;
                    ans += y * t;
                    t *= 10;
                    break;
                }
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
    int largestInteger(int num) {
        vector<int> cnt(10);
        int x = num;
        while (x) {
            cnt[x % 10]++;
            x /= 10;
        }
        x = num;
        int ans = 0;
        long t = 1;
        while (x) {
            int v = x % 10;
            x /= 10;
            for (int y = 0; y < 10; ++y) {
                if (((v ^ y) & 1) == 0 && cnt[y] > 0) {
                    cnt[y]--;
                    ans += y * t;
                    t *= 10;
                    break;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func largestInteger(num int) int {
	cnt := make([]int, 10)
	x := num
	for x != 0 {
		cnt[x%10]++
		x /= 10
	}
	x = num
	ans, t := 0, 1
	for x != 0 {
		v := x % 10
		x /= 10
		for y := 0; y < 10; y++ {
			if ((v^y)&1) == 0 && cnt[y] > 0 {
				cnt[y]--
				ans += y * t
				t *= 10
				break
			}
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function largestInteger(num: number): number {
    let arrs = String(num).split('').map(Number);
    let odds = []; // 奇数
    let evens = [];
    for (let i of arrs) {
        if ((i & 1) == 1) {
            odds.push(i);
        } else {
            evens.push(i);
        }
    }
    odds.sort((a, b) => a - b);
    evens.sort((a, b) => a - b);
    let ans = [];
    for (let i of arrs) {
        ans.push((i & 1) == 1 ? odds.pop() : evens.pop());
    }
    return Number(ans.join(''));
}
```

### **...**

```

```

<!-- tabs:end -->
