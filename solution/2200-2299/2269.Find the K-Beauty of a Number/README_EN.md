---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2269.Find%20the%20K-Beauty%20of%20a%20Number/README_EN.md
rating: 1279
source: Biweekly Contest 78 Q1
tags:
    - Math
    - String
    - Sliding Window
---

<!-- problem:start -->

# [2269. Find the K-Beauty of a Number](https://leetcode.com/problems/find-the-k-beauty-of-a-number)

[中文文档](/solution/2200-2299/2269.Find%20the%20K-Beauty%20of%20a%20Number/README.md)

## Description

<p>The <strong>k-beauty</strong> of an integer <code>num</code> is defined as the number of <strong>substrings</strong> of <code>num</code> when it is read as a string that meet the following conditions:</p>

<ul>
	<li>It has a length of <code>k</code>.</li>
	<li>It is a divisor of <code>num</code>.</li>
</ul>

<p>Given integers <code>num</code> and <code>k</code>, return <em>the k-beauty of </em><code>num</code>.</p>

<p>Note:</p>

<ul>
	<li><strong>Leading zeros</strong> are allowed.</li>
	<li><code>0</code> is not a divisor of any value.</li>
</ul>

<p>A <strong>substring</strong> is a contiguous sequence of characters in a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = 240, k = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> The following are the substrings of num of length k:
- &quot;24&quot; from &quot;<strong><u>24</u></strong>0&quot;: 24 is a divisor of 240.
- &quot;40&quot; from &quot;2<u><strong>40</strong></u>&quot;: 40 is a divisor of 240.
Therefore, the k-beauty is 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = 430043, k = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> The following are the substrings of num of length k:
- &quot;43&quot; from &quot;<u><strong>43</strong></u>0043&quot;: 43 is a divisor of 430043.
- &quot;30&quot; from &quot;4<u><strong>30</strong></u>043&quot;: 30 is not a divisor of 430043.
- &quot;00&quot; from &quot;43<u><strong>00</strong></u>43&quot;: 0 is not a divisor of 430043.
- &quot;04&quot; from &quot;430<u><strong>04</strong></u>3&quot;: 4 is not a divisor of 430043.
- &quot;43&quot; from &quot;4300<u><strong>43</strong></u>&quot;: 43 is a divisor of 430043.
Therefore, the k-beauty is 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= num.length</code> (taking <code>num</code> as a string)</li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We can convert $num$ to a string $s$, then enumerate all substrings of $s$ with length $k$, convert them to an integer $t$, and check if $t$ is divisible by $num$. If it is, we increment the answer.

The time complexity is $O(\log num \times k)$, and the space complexity is $O(\log num + k)$.

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

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Sliding Window

We can maintain a sliding window of length $k$. Initially, the window contains the lowest $k$ digits of $num$. Then, for each iteration, we move the window one digit to the right, update the number in the window, and check if the number in the window is divisible by $num$. If it is, we increment the answer.

The time complexity is $O(\log num)$, and the space complexity is $O(1)$.

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

<!-- solution:end -->

<!-- problem:end -->
