---
comments: true
difficulty: Medium
tags:
    - Array
    - Math
    - Enumeration
    - Number Theory
    - Primality Test
    - Sieve
    - Sieve of Eratosthenes
---

<!-- problem:start -->

# [204. Count Primes](https://leetcode.com/problems/count-primes)

[中文文档](/solution/0200-0299/0204.Count%20Primes/README.md)

## Mô tả

<!-- description:start -->

<p>Với một số nguyên <code>n</code>, hãy trả về <em>số lượng số nguyên tố nhỏ hơn nghiêm ngặt</em> <code>n</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> n = 10
<strong>Đầu ra:</strong> 4
<strong>Giải thích:</strong> Có 4 số nguyên tố nhỏ hơn 10, đó là 2, 3, 5, 7.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> n = 0
<strong>Đầu ra:</strong> 0
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> n = 1
<strong>Đầu ra:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 5 * 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1

<!-- thinking:start -->

> **Tư duy**
>
> Phép chia thử đến $\sqrt{x}$ có thể đếm số nguyên tố, nhưng $n$ có thể lên tới $5\times 10^6$, nên việc kiểm tra lặp lại khá chậm. Nếu $x$ là số nguyên tố thì các bội của nó $2x,3x,\ldots$ là hợp số.
>
> Sàng Eratosthenes đánh dấu các bội đó khi chúng ta quét từ nhỏ đến lớn, sau đó đếm các giá trị chưa được đánh dấu. Mỗi hợp số bị loại bởi một thừa số, với thời gian khoảng $O(n\log\log n)$.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countPrimes(self, n: int) -> int:
        primes = [True] * n
        ans = 0
        for i in range(2, n):
            if primes[i]:
                ans += 1
                for j in range(i + i, n, i):
                    primes[j] = False
        return ans
```

#### Java

```java
class Solution {
    public int countPrimes(int n) {
        boolean[] primes = new boolean[n];
        Arrays.fill(primes, true);
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            if (primes[i]) {
                ++ans;
                for (int j = i + i; j < n; j += i) {
                    primes[j] = false;
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countPrimes(int n) {
        vector<bool> primes(n, true);
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            if (primes[i]) {
                ++ans;
                for (int j = i; j < n; j += i) primes[j] = false;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countPrimes(n int) int {
	primes := make([]bool, n)
	for i := range primes {
		primes[i] = true
	}
	ans := 0
	for i := 2; i < n; i++ {
		if primes[i] {
			ans++
			for j := i + i; j < n; j += i {
				primes[j] = false
			}
		}
	}
	return ans
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {number}
 */
var countPrimes = function (n) {
    let primes = new Array(n).fill(true);
    let ans = 0;
    for (let i = 2; i < n; ++i) {
        if (primes[i]) {
            ++ans;
            for (let j = i + i; j < n; j += i) {
                primes[j] = false;
            }
        }
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public int CountPrimes(int n) {
        var notPrimes = new bool[n];
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            if (!notPrimes[i]) {
                ++ans;
                for (int j = i + i; j < n; j += i) {
                    notPrimes[j] = true;
                }
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
