---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2197.Replace%20Non-Coprime%20Numbers%20in%20Array/README_EN.md
rating: 2057
source: Weekly Contest 283 Q4
tags:
    - Stack
    - Array
    - Math
    - Number Theory
---

<!-- problem:start -->

# [2197. Replace Non-Coprime Numbers in Array](https://leetcode.com/problems/replace-non-coprime-numbers-in-array)

[中文文档](/solution/2100-2199/2197.Replace%20Non-Coprime%20Numbers%20in%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given an array of integers <code>nums</code>. Perform the following steps:</p>

<ol>
	<li>Find <strong>any</strong> two <strong>adjacent</strong> numbers in <code>nums</code> that are <strong>non-coprime</strong>.</li>
	<li>If no such numbers are found, <strong>stop</strong> the process.</li>
	<li>Otherwise, delete the two numbers and <strong>replace</strong> them with their <strong>LCM (Least Common Multiple)</strong>.</li>
	<li><strong>Repeat</strong> this process as long as you keep finding two adjacent non-coprime numbers.</li>
</ol>

<p>Return <em>the <strong>final</strong> modified array.</em> It can be shown that replacing adjacent non-coprime numbers in <strong>any</strong> arbitrary order will lead to the same result.</p>

<p>The test cases are generated such that the values in the final array are <strong>less than or equal</strong> to <code>10<sup>8</sup></code>.</p>

<p>Two values <code>x</code> and <code>y</code> are <strong>non-coprime</strong> if <code>GCD(x, y) &gt; 1</code> where <code>GCD(x, y)</code> is the <strong>Greatest Common Divisor</strong> of <code>x</code> and <code>y</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [6,4,3,2,7,6,2]
<strong>Output:</strong> [12,7,6]
<strong>Explanation:</strong> 
- (6, 4) are non-coprime with LCM(6, 4) = 12. Now, nums = [<strong><u>12</u></strong>,3,2,7,6,2].
- (12, 3) are non-coprime with LCM(12, 3) = 12. Now, nums = [<strong><u>12</u></strong>,2,7,6,2].
- (12, 2) are non-coprime with LCM(12, 2) = 12. Now, nums = [<strong><u>12</u></strong>,7,6,2].
- (6, 2) are non-coprime with LCM(6, 2) = 6. Now, nums = [12,7,<u><strong>6</strong></u>].
There are no more adjacent non-coprime numbers in nums.
Thus, the final modified array is [12,7,6].
Note that there are other ways to obtain the same resultant array.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2,1,1,3,3,3]
<strong>Output:</strong> [2,1,1,3]
<strong>Explanation:</strong> 
- (3, 3) are non-coprime with LCM(3, 3) = 3. Now, nums = [2,2,1,1,<u><strong>3</strong></u>,3].
- (3, 3) are non-coprime with LCM(3, 3) = 3. Now, nums = [2,2,1,1,<u><strong>3</strong></u>].
- (2, 2) are non-coprime with LCM(2, 2) = 2. Now, nums = [<u><strong>2</strong></u>,1,1,3].
There are no more adjacent non-coprime numbers in nums.
Thus, the final modified array is [2,1,1,3].
Note that there are other ways to obtain the same resultant array.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li>The test cases are generated such that the values in the final array are <strong>less than or equal</strong> to <code>10<sup>8</sup></code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Stack

If there exist three adjacent numbers $x$, $y$, $z$ that can be merged, then the result of first merging $x$ and $y$, then merging $z$, is the same as the result of first merging $y$ and $z$, then merging $x$. Both results are $\textit{LCM}(x, y, z)$.

Therefore, we can always prefer to merge the adjacent numbers on the left, and then merge the result with the adjacent number on the right.

We use a stack to simulate this process. We traverse the array, and for each number, we push it into the stack. Then we continuously check whether the top two numbers of the stack are coprime. If they are not coprime, we pop these two numbers out of the stack, and then push their least common multiple into the stack, until the top two numbers of the stack are coprime, or there are less than two elements in the stack.

The final elements in the stack are the final result.

The time complexity is $O(n \times \log M)$, and the space complexity is $O(n)$. Where $M$ is the maximum value in the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def replaceNonCoprimes(self, nums: List[int]) -> List[int]:
        stk = []
        for x in nums:
            stk.append(x)
            while len(stk) > 1:
                x, y = stk[-2:]
                g = gcd(x, y)
                if g == 1:
                    break
                stk.pop()
                stk[-1] = x * y // g
        return stk
```

#### Java

```java
class Solution {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        List<Integer> stk = new ArrayList<>();
        for (int x : nums) {
            stk.add(x);
            while (stk.size() > 1) {
                x = stk.get(stk.size() - 1);
                int y = stk.get(stk.size() - 2);
                int g = gcd(x, y);
                if (g == 1) {
                    break;
                }
                stk.remove(stk.size() - 1);
                stk.set(stk.size() - 1, (int) ((long) x * y / g));
            }
        }
        return stk;
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> replaceNonCoprimes(vector<int>& nums) {
        vector<int> stk;
        for (int x : nums) {
            stk.push_back(x);
            while (stk.size() > 1) {
                x = stk.back();
                int y = stk[stk.size() - 2];
                int g = __gcd(x, y);
                if (g == 1) {
                    break;
                }
                stk.pop_back();
                stk.back() = 1LL * x * y / g;
            }
        }
        return stk;
    }
};
```

#### Go

```go
func replaceNonCoprimes(nums []int) []int {
	stk := []int{}
	for _, x := range nums {
		stk = append(stk, x)
		for len(stk) > 1 {
			x = stk[len(stk)-1]
			y := stk[len(stk)-2]
			g := gcd(x, y)
			if g == 1 {
				break
			}
			stk = stk[:len(stk)-1]
			stk[len(stk)-1] = x * y / g
		}
	}
	return stk
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

#### TypeScript

```ts
function replaceNonCoprimes(nums: number[]): number[] {
    const gcd = (a: number, b: number): number => {
        if (b === 0) {
            return a;
        }
        return gcd(b, a % b);
    };
    const stk: number[] = [];
    for (let x of nums) {
        stk.push(x);
        while (stk.length > 1) {
            x = stk.at(-1)!;
            const y = stk.at(-2)!;
            const g = gcd(x, y);
            if (g === 1) {
                break;
            }
            stk.pop();
            stk.pop();
            stk.push(((x * y) / g) | 0);
        }
    }
    return stk;
}
```

#### Rust

```rust
impl Solution {
    pub fn replace_non_coprimes(nums: Vec<i32>) -> Vec<i32> {
        fn gcd(mut a: i64, mut b: i64) -> i64 {
            while b != 0 {
                let t = a % b;
                a = b;
                b = t;
            }
            a
        }

        let mut stk: Vec<i64> = Vec::new();
        for x in nums {
            stk.push(x as i64);
            while stk.len() > 1 {
                let x = *stk.last().unwrap();
                let y = stk[stk.len() - 2];
                let g = gcd(x, y);
                if g == 1 {
                    break;
                }
                stk.pop();
                let last = stk.last_mut().unwrap();
                *last = x / g * y;
            }
        }

        stk.into_iter().map(|v| v as i32).collect()
    }
}
```

#### C#

```cs
public class Solution {
    public IList<int> ReplaceNonCoprimes(int[] nums) {
        long Gcd(long a, long b) {
            while (b != 0) {
                long t = a % b;
                a = b;
                b = t;
            }
            return a;
        }

        var stk = new List<long>();
        foreach (int num in nums) {
            stk.Add(num);
            while (stk.Count > 1) {
                long x = stk[stk.Count - 1];
                long y = stk[stk.Count - 2];
                long g = Gcd(x, y);
                if (g == 1) {
                    break;
                }
                stk.RemoveAt(stk.Count - 1);
                stk[stk.Count - 1] = x / g * y;
            }
        }

        var ans = new List<int>();
        foreach (long v in stk) {
            ans.Add((int)v);
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
