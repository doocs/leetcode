# [645. Set Mismatch](https://leetcode.com/problems/set-mismatch)

[中文文档](/solution/0600-0699/0645.Set%20Mismatch/README.md)

## Description

<p>You have a set of integers <code>s</code>, which originally contains all the numbers from <code>1</code> to <code>n</code>. Unfortunately, due to some error, one of the numbers in <code>s</code> got duplicated to another number in the set, which results in <strong>repetition of one</strong> number and <strong>loss of another</strong> number.</p>

<p>You are given an integer array <code>nums</code> representing the data status of this set after the error.</p>

<p>Find the number that occurs twice and the number that is missing and return <em>them in the form of an array</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [1,2,2,4]
<strong>Output:</strong> [2,3]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [1,1]
<strong>Output:</strong> [1,2]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findErrorNums(self, nums: List[int]) -> List[int]:
        eor, n = 0, len(nums)
        for i in range(1, n + 1):
            eor ^= i ^ nums[i - 1]
        diff = eor & (~eor + 1)
        a = 0
        for i in range(1, n + 1):
            if (nums[i - 1] & diff) == 0:
                a ^= nums[i - 1]
            if (i & diff) == 0:
                a ^= i
        b = eor ^ a
        for num in nums:
            if a == num:
                return [a, b]
        return [b, a]
```

### **Java**

```java
class Solution {
    public int[] findErrorNums(int[] nums) {
        int eor = 0;
        for (int i = 1; i <= nums.length; ++i) {
            eor ^= (i ^ nums[i - 1]);
        }
        int diff = eor & (~eor + 1);
        int a = 0;
        for (int i = 1; i <= nums.length; ++i) {
            if ((nums[i - 1] & diff) == 0) {
                a ^= nums[i - 1];
            }
            if ((i & diff) == 0) {
                a ^= i;
            }
        }
        int b = eor ^ a;
        for (int num : nums) {
            if (a == num) {
                return new int[]{a, b};
            }
        }
        return new int[]{b, a};
    }
}
```

### **TypeScript**

```ts
function findErrorNums(nums: number[]): number[] {
    let xor = 0;
    for (let i = 0; i < nums.length; ++i) {
        xor ^= (i + 1) ^ nums[i];
    }

    let divide = 1;
    while ((xor & divide) == 0) {
        divide <<= 1;
    }

    let ans1 = 0,
        ans2 = 0;
    for (let i = 0; i < nums.length; ++i) {
        let cur = nums[i];
        if (divide & cur) {
            ans1 ^= cur;
        } else {
            ans2 ^= cur;
        }

        let idx = i + 1;
        if (divide & idx) {
            ans1 ^= idx;
        } else {
            ans2 ^= idx;
        }
    }
    return nums.includes(ans1) ? [ans1, ans2] : [ans2, ans1];
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> findErrorNums(vector<int>& nums) {
        int eor = 0, n = nums.size();
        for (int i = 1; i <= n; ++i) {
            eor ^= (i ^ nums[i - 1]);
        }
        int diff = eor & (~eor + 1);
        int a = 0;
        for (int i = 1; i <= n; ++i) {
            if ((nums[i - 1] & diff) == 0) {
                a ^= nums[i - 1];
            }
            if ((i & diff) == 0) {
                a ^= i;
            }
        }
        int b = eor ^ a;
        for (int num : nums) {
            if (a == num) {
                return {a, b};
            }
        }
        return {b, a};
    }
};
```

### **Go**

把每个数都放到它应该在的位置，最后出现“异常”的就是重复的数和丢失的数。

```go
func findErrorNums(nums []int) []int {
	n := len(nums)
	for i := 0; i < n; i++ {
		for nums[i] != i+1 && nums[nums[i]-1] != nums[i] {
			nums[i], nums[nums[i]-1] = nums[nums[i]-1], nums[i]
		}
	}
	for i := 0; i < n; i++ {
		if nums[i] != i+1 {
			return []int{nums[i], i + 1}
		}
	}
	return []int{-1, -1}
}
```

也可以使用位运算。

```go
func findErrorNums(nums []int) []int {
	eor, n := 0, len(nums)
	for i := 1; i <= n; i++ {
		eor ^= (i ^ nums[i-1])
	}
	diff := eor & (-eor)
	a := 0
	for i := 1; i <= n; i++ {
		if (nums[i-1] & diff) == 0 {
			a ^= nums[i-1]
		}
		if (i & diff) == 0 {
			a ^= i
		}
	}
	b := eor ^ a
	for _, num := range nums {
		if a == num {
			return []int{a, b}
		}
	}
	return []int{b, a}
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> findErrorNums(vector<int>& nums) {
        int eor = 0, n = nums.size();
        for (int i = 1; i <= n; ++i) {
            eor ^= (i ^ nums[i - 1]);
        }
        int diff = eor & (~eor + 1);
        int a = 0;
        for (int i = 1; i <= n; ++i) {
            if ((nums[i - 1] & diff) == 0) {
                a ^= nums[i - 1];
            }
            if ((i & diff) == 0) {
                a ^= i;
            }
        }
        int b = eor ^ a;
        for (int num : nums) {
            if (a == num) {
                return {a, b};
            }
        }
        return {b, a};
    }
};
```

### **...**

```

```

<!-- tabs:end -->
