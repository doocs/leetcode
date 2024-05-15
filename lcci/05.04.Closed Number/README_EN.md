---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/05.04.Closed%20Number/README_EN.md
---

# [05.04. Closed Number](https://leetcode.cn/problems/closed-number-lcci)

[中文文档](/lcci/05.04.Closed%20Number/README.md)

## Description

<p>Given a positive integer, print the next smallest and the next largest number that have the same number of 1 bits in their binary representation.</p>
<p><strong>Example1:</strong></p>
<pre>

<strong> Input</strong>: num = 2 (0b10)

<strong> Output</strong>: [4, 1] ([0b100, 0b1])

</pre>
<p><strong>Example2:</strong></p>
<pre>

<strong> Input</strong>: num = 1

<strong> Output</strong>: [2, -1]

</pre>
<p><strong>Note:</strong></p>
<ol>
	<li><code>1 &lt;= num &lt;=&nbsp;2147483647</code></li>
	<li>If there is no next smallest or next largest number, output -1.</li>
</ol>

## Solutions

### Solution 1: Bit Manipulation

First, let's consider how to find the first number that is larger than $num$ and has the same number of $1$s in its binary representation.

We can traverse the adjacent two binary bits of $num$ from low to high. If the lower bit is $1$ and the adjacent higher bit is $0$, then we have found a position where we can change the $0$ at this position to $1$ and change the $1$ at this position to $0$. Then we move all the remaining lower bits of $1$ to the lowest bit, so we get a number that is larger than $num$ and has the same number of $1$s in its binary representation.

Similarly, we can find the first number that is smaller than $num$ and has the same number of $1$s in its binary representation.

We can traverse the adjacent two binary bits of $num$ from low to high. If the lower bit is $0$ and the adjacent higher bit is $1$, then we have found a position where we can change the $1$ at this position to $0$ and change the $0$ at this position to $1$. Then we move all the remaining lower bits of $0$ to the lowest bit, so we get a number that is smaller than $num$ and has the same number of $1$s in its binary representation.

In implementation, we can use a piece of code to handle the above two situations uniformly.

The time complexity is $O(\log n)$, where $n$ is the size of $num$. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def findClosedNumbers(self, num: int) -> List[int]:
        ans = [-1] * 2
        dirs = (0, 1, 0)
        for p in range(2):
            a, b = dirs[p], dirs[p + 1]
            x = num
            for i in range(1, 31):
                if (x >> i & 1) == a and (x >> (i - 1) & 1) == b:
                    x ^= 1 << i
                    x ^= 1 << (i - 1)
                    j, k = 0, i - 2
                    while j < k:
                        while j < k and (x >> j & 1) == b:
                            j += 1
                        while j < k and (x >> k & 1) == a:
                            k -= 1
                        if j < k:
                            x ^= 1 << j
                            x ^= 1 << k
                    ans[p] = x
                    break
        return ans
```

```java
class Solution {
    public int[] findClosedNumbers(int num) {
        int[] ans = {-1, -1};
        int[] dirs = {0, 1, 0};
        for (int p = 0; p < 2; ++p) {
            int a = dirs[p], b = dirs[p + 1];
            int x = num;
            for (int i = 1; i < 31; ++i) {
                if ((x >> i & 1) == a && (x >> (i - 1) & 1) == b) {
                    x ^= 1 << i;
                    x ^= 1 << (i - 1);
                    int j = 0, k = i - 2;
                    while (j < k) {
                        while (j < k && (x >> j & 1) == b) {
                            ++j;
                        }
                        while (j < k && (x >> k & 1) == a) {
                            --k;
                        }
                        if (j < k) {
                            x ^= 1 << j;
                            x ^= 1 << k;
                        }
                    }
                    ans[p] = x;
                    break;
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> findClosedNumbers(int num) {
        vector<int> ans(2, -1);
        int dirs[3] = {0, 1, 0};
        for (int p = 0; p < 2; ++p) {
            int a = dirs[p], b = dirs[p + 1];
            int x = num;
            for (int i = 1; i < 31; ++i) {
                if ((x >> i & 1) == a && (x >> (i - 1) & 1) == b) {
                    x ^= 1 << i;
                    x ^= 1 << (i - 1);
                    int j = 0, k = i - 2;
                    while (j < k) {
                        while (j < k && (x >> j & 1) == b) {
                            ++j;
                        }
                        while (j < k && (x >> k & 1) == a) {
                            --k;
                        }
                        if (j < k) {
                            x ^= 1 << j;
                            x ^= 1 << k;
                        }
                    }
                    ans[p] = x;
                    break;
                }
            }
        }
        return ans;
    }
};
```

```go
func findClosedNumbers(num int) []int {
	ans := []int{-1, -1}
	dirs := [3]int{0, 1, 0}
	for p := 0; p < 2; p++ {
		a, b := dirs[p], dirs[p+1]
		x := num
		for i := 1; i < 31; i++ {
			if x>>i&1 == a && x>>(i-1)&1 == b {
				x ^= 1 << i
				x ^= 1 << (i - 1)
				j, k := 0, i-2
				for j < k {
					for j < k && x>>j&1 == b {
						j++
					}
					for j < k && x>>k&1 == a {
						k--
					}
					if j < k {
						x ^= 1 << j
						x ^= 1 << k
					}
				}
				ans[p] = x
				break
			}
		}
	}
	return ans
}
```

```ts
function findClosedNumbers(num: number): number[] {
    const ans: number[] = [-1, -1];
    const dirs: number[] = [0, 1, 0];
    for (let p = 0; p < 2; ++p) {
        const [a, b] = [dirs[p], dirs[p + 1]];
        let x = num;
        for (let i = 1; i < 31; ++i) {
            if (((x >> i) & 1) === a && ((x >> (i - 1)) & 1) === b) {
                x ^= 1 << i;
                x ^= 1 << (i - 1);
                let [j, k] = [0, i - 2];
                while (j < k) {
                    while (j < k && ((x >> j) & 1) === b) {
                        ++j;
                    }
                    while (j < k && ((x >> k) & 1) === a) {
                        --k;
                    }
                    if (j < k) {
                        x ^= 1 << j;
                        x ^= 1 << k;
                    }
                }
                ans[p] = x;
                break;
            }
        }
    }
    return ans;
}
```

```swift
class Solution {
    func findClosedNumbers(_ num: Int) -> [Int] {
        var ans = [-1, -1]
        let dirs = [0, 1, 0]

        for p in 0..<2 {
            let a = dirs[p], b = dirs[p + 1]
            var x = num
            var found = false

            for i in 1..<31 {
                if ((x >> i) & 1) == a && ((x >> (i - 1)) & 1) == b {
                    x ^= (1 << i)
                    x ^= (1 << (i - 1))

                    var j = 0, k = i - 2
                    while j < k {
                        while j < k && ((x >> j) & 1) == b {
                            j += 1
                        }
                        while j < k && ((x >> k) & 1) == a {
                            k -= 1
                        }
                        if j < k {
                            x ^= (1 << j)
                            x ^= (1 << k)
                        }
                    }
                    ans[p] = x
                    found = true
                    break
                }
            }
            if !found {
                ans[p] = -1
            }
        }

        return ans
    }
}
```

<!-- tabs:end -->

<!-- end -->
