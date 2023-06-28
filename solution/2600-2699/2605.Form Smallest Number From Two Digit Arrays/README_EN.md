# [2605. Form Smallest Number From Two Digit Arrays](https://leetcode.com/problems/form-smallest-number-from-two-digit-arrays)

[中文文档](/solution/2600-2699/2605.Form%20Smallest%20Number%20From%20Two%20Digit%20Arrays/README.md)

## Description

Given two arrays of <strong>unique</strong> digits <code>nums1</code> and <code>nums2</code>, return <em>the <strong>smallest</strong> number that contains <strong>at least</strong> one digit from each array</em>.

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [4,1,3], nums2 = [5,7]
<strong>Output:</strong> 15
<strong>Explanation:</strong> The number 15 contains the digit 1 from nums1 and the digit 5 from nums2. It can be proven that 15 is the smallest number we can have.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [3,5,2,6], nums2 = [3,1,7]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The number 3 contains the digit 3 which exists in both arrays.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 9</code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 9</code></li>
	<li>All digits in each array are <strong>unique</strong>.</li>
</ul>

## Solutions

**Solution 1: Enumeration**

We observe that if there are the same numbers in the arrays $nums1$ and $nums2$, then the minimum of the same numbers is the smallest number. Otherwise, we take the number $a$ in the array $nums1$ and the number $b$ in the array $nums2$, and concatenate the two numbers $a$ and $b$ into two numbers, and take the smaller number.

The time complexity is $O(m \times n)$, and the space complexity is $O(1)$, where $m$ and $n$ are the lengths of the arrays $nums1$ and $nums2$.

**Solution 2: Hash Table or Array + Enumeration**

We can use a hash table or array to record the numbers in the arrays $nums1$ and $nums2$, and then enumerate $1 \sim 9$. If $i$ appears in both arrays, then $i$ is the smallest number. Otherwise, we take the number $a$ in the array $nums1$ and the number $b$ in the array $nums2$, and concatenate the two numbers $a$ and $b$ into two numbers, and take the smaller number.

The time complexity is $(m + n)$, and the space complexity is $O(C)$. Where $m$ and $n$ are the lengths of the arrays $nums1$ and $nums2$ respectively; and $C$ is the range of the numbers in the arrays $nums1$ and $nums2$, and the range in this problem is $C = 10$.

**Solution 3: Bit Operation**

Since the range of the numbers is $1 \sim 9$, we can use a binary number with a length of $10$ to represent the numbers in the arrays $nums1$ and $nums2$. We use $mask1$ to represent the numbers in the array $nums1$, and use $mask2$ to represent the numbers in the array $nums2$.

If the number $mask$ obtained by performing a bitwise AND operation on $mask1$ and $mask2$ is not equal to $0$, then we extract the position of the last $1$ in the number $mask$, which is the smallest number.

Otherwise, we extract the position of the last $1$ in $mask1$ and $mask2$ respectively, and denote them as $a$ and $b$, respectively. Then the smallest number is $min(a \times 10 + b, b \times 10 + a)$.

The time complexity is $O(m + n)$, and the space complexity is $O(1)$. Where $m$ and $n$ are the lengths of the arrays $nums1$ and $nums2$ respectively.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minNumber(self, nums1: List[int], nums2: List[int]) -> int:
        ans = 100
        for a in nums1:
            for b in nums2:
                if a == b:
                    ans = min(ans, a)
                else:
                    ans = min(ans, 10 * a + b, 10 * b + a)
        return ans
```

```python
class Solution:
    def minNumber(self, nums1: List[int], nums2: List[int]) -> int:
        s = set(nums1) & set(nums2)
        if s:
            return min(s)
        a, b = min(nums1), min(nums2)
        return min(a * 10 + b, b * 10 + a)
```

```python
class Solution:
    def minNumber(self, nums1: List[int], nums2: List[int]) -> int:
        mask1 = mask2 = 0
        for x in nums1:
            mask1 |= 1 << x
        for x in nums2:
            mask2 |= 1 << x
        mask = mask1 & mask2
        if mask:
            return (mask & -mask).bit_length() - 1
        a = (mask1 & -mask1).bit_length() - 1
        b = (mask2 & -mask2).bit_length() - 1
        return min(a * 10 + b, b * 10 + a)
```

### **Java**

```java
class Solution {
    public int minNumber(int[] nums1, int[] nums2) {
        int ans = 100;
        for (int a : nums1) {
            for (int b : nums2) {
                if (a == b) {
                    ans = Math.min(ans, a);
                } else {
                    ans = Math.min(ans, Math.min(a * 10 + b, b * 10 + a));
                }
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int minNumber(int[] nums1, int[] nums2) {
        boolean[] s1 = new boolean[10];
        boolean[] s2 = new boolean[10];
        for (int x : nums1) {
            s1[x] = true;
        }
        for (int x : nums2) {
            s2[x] = true;
        }
        int a = 0, b = 0;
        for (int i = 1; i < 10; ++i) {
            if (s1[i] && s2[i]) {
                return i;
            }
            if (a == 0 && s1[i]) {
                a = i;
            }
            if (b == 0 && s2[i]) {
                b = i;
            }
        }
        return Math.min(a * 10 + b, b * 10 + a);
    }
}
```

```java
class Solution {
    public int minNumber(int[] nums1, int[] nums2) {
        int mask1 = 0, mask2 = 0;
        for (int x : nums1) {
            mask1 |= 1 << x;
        }
        for (int x : nums2) {
            mask2 |= 1 << x;
        }
        int mask = mask1 & mask2;
        if (mask != 0) {
            return Integer.numberOfTrailingZeros(mask);
        }
        int a = Integer.numberOfTrailingZeros(mask1);
        int b = Integer.numberOfTrailingZeros(mask2);
        return Math.min(a * 10 + b, b * 10 + a);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minNumber(vector<int>& nums1, vector<int>& nums2) {
        int ans = 100;
        for (int a : nums1) {
            for (int b : nums2) {
                if (a == b) {
                    ans = min(ans, a);
                } else {
                    ans = min({ans, a * 10 + b, b * 10 + a});
                }
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int minNumber(vector<int>& nums1, vector<int>& nums2) {
        bitset<10> s1;
        bitset<10> s2;
        for (int x : nums1) {
            s1[x] = 1;
        }
        for (int x : nums2) {
            s2[x] = 1;
        }
        int a = 0, b = 0;
        for (int i = 1; i < 10; ++i) {
            if (s1[i] && s2[i]) {
                return i;
            }
            if (!a && s1[i]) {
                a = i;
            }
            if (!b && s2[i]) {
                b = i;
            }
        }
        return min(a * 10 + b, b * 10 + a);
    }
};
```

```cpp
class Solution {
public:
    int minNumber(vector<int>& nums1, vector<int>& nums2) {
        int mask1 = 0, mask2 = 0;
        for (int x : nums1) {
            mask1 |= 1 << x;
        }
        for (int x : nums2) {
            mask2 |= 1 << x;
        }
        int mask = mask1 & mask2;
        if (mask) {
            return __builtin_ctz(mask);
        }
        int a = __builtin_ctz(mask1);
        int b = __builtin_ctz(mask2);
        return min(a * 10 + b, b * 10 + a);
    }
};
```

### **Go**

```go
func minNumber(nums1 []int, nums2 []int) int {
	ans := 100
	for _, a := range nums1 {
		for _, b := range nums2 {
			if a == b {
				ans = min(ans, a)
			} else {
				ans = min(ans, min(a*10+b, b*10+a))
			}
		}
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

```go
func minNumber(nums1 []int, nums2 []int) int {
	s1 := [10]bool{}
	s2 := [10]bool{}
	for _, x := range nums1 {
		s1[x] = true
	}
	for _, x := range nums2 {
		s2[x] = true
	}
	a, b := 0, 0
	for i := 1; i < 10; i++ {
		if s1[i] && s2[i] {
			return i
		}
		if a == 0 && s1[i] {
			a = i
		}
		if b == 0 && s2[i] {
			b = i
		}
	}
	return min(a*10+b, b*10+a)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

```go
func minNumber(nums1 []int, nums2 []int) int {
	var mask1, mask2 uint
	for _, x := range nums1 {
		mask1 |= 1 << x
	}
	for _, x := range nums2 {
		mask2 |= 1 << x
	}
	if mask := mask1 & mask2; mask != 0 {
		return bits.TrailingZeros(mask)
	}
	a, b := bits.TrailingZeros(mask1), bits.TrailingZeros(mask2)
	return min(a*10+b, b*10+a)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function minNumber(nums1: number[], nums2: number[]): number {
    let ans = 100;
    for (const a of nums1) {
        for (const b of nums2) {
            if (a == b) {
                ans = Math.min(ans, a);
            } else {
                ans = Math.min(ans, a * 10 + b, b * 10 + a);
            }
        }
    }
    return ans;
}
```

```ts
function minNumber(nums1: number[], nums2: number[]): number {
    const s1: boolean[] = new Array(10).fill(false);
    const s2: boolean[] = new Array(10).fill(false);
    for (const x of nums1) {
        s1[x] = true;
    }
    for (const x of nums2) {
        s2[x] = true;
    }
    let a = 0;
    let b = 0;
    for (let i = 1; i < 10; ++i) {
        if (s1[i] && s2[i]) {
            return i;
        }
        if (a == 0 && s1[i]) {
            a = i;
        }
        if (b == 0 && s2[i]) {
            b = i;
        }
    }
    return Math.min(a * 10 + b, b * 10 + a);
}
```

```ts
function minNumber(nums1: number[], nums2: number[]): number {
    let mask1: number = 0;
    let mask2: number = 0;
    for (const x of nums1) {
        mask1 |= 1 << x;
    }
    for (const x of nums2) {
        mask2 |= 1 << x;
    }
    const mask = mask1 & mask2;
    if (mask !== 0) {
        return numberOfTrailingZeros(mask);
    }
    const a = numberOfTrailingZeros(mask1);
    const b = numberOfTrailingZeros(mask2);
    return Math.min(a * 10 + b, b * 10 + a);
}

function numberOfTrailingZeros(i: number): number {
    let y = 0;
    if (i === 0) {
        return 32;
    }
    let n = 31;
    y = i << 16;
    if (y != 0) {
        n = n - 16;
        i = y;
    }
    y = i << 8;
    if (y != 0) {
        n = n - 8;
        i = y;
    }
    y = i << 4;
    if (y != 0) {
        n = n - 4;
        i = y;
    }
    y = i << 2;
    if (y != 0) {
        n = n - 2;
        i = y;
    }
    return n - ((i << 1) >>> 31);
}
```

### **...**

```

```

<!-- tabs:end -->
