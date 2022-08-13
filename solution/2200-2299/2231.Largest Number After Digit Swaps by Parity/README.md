# [2231. 按奇偶性交换后的最大数字](https://leetcode.cn/problems/largest-number-after-digit-swaps-by-parity)

[English Version](/solution/2200-2299/2231.Largest%20Number%20After%20Digit%20Swaps%20by%20Parity/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数 <code>num</code> 。你可以交换 <code>num</code> 中 <strong>奇偶性</strong> 相同的任意两位数字（即，都是奇数或者偶数）。</p>

<p>返回交换 <strong>任意</strong> 次之后 <code>num</code> 的 <strong>最大</strong> 可能值<em>。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>num = 1234
<strong>输出：</strong>3412
<strong>解释：</strong>交换数字 3 和数字 1 ，结果得到 3214 。
交换数字 2 和数字 4 ，结果得到 3412 。
注意，可能存在其他交换序列，但是可以证明 3412 是最大可能值。
注意，不能交换数字 4 和数字 1 ，因为它们奇偶性不同。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>num = 65875
<strong>输出：</strong>87655
<strong>解释：</strong>交换数字 8 和数字 6 ，结果得到 85675 。
交换数字 5 和数字 7 ，结果得到 87655 。
注意，可能存在其他交换序列，但是可以证明 87655 是最大可能值。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数**

**方法二：分组 + 排序**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
